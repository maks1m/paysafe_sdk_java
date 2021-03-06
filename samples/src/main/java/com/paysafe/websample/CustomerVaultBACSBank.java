/*
 * Copyright (c) 2016 Paysafe
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.paysafe.websample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paysafe.Environment;
import com.paysafe.PaysafeApiClient;
import com.paysafe.common.Locale;
import com.paysafe.common.PaysafeException;
import com.paysafe.customervault.Address;
import com.paysafe.customervault.BACSBankAccounts;
import com.paysafe.customervault.Profile;
import com.paysafe.directdebit.Purchases;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class CustomerVaultBACSBank extends PaysafeServletBase {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static String sixDigitRandomNumber() {
        Random rand = new Random();

        int num = rand.nextInt(900000) + 100000;
        return "" + num;

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

        request.setAttribute("isPost", "false");
        request.setAttribute("currency", this.currencyCode);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

        // Initiate a new instance of the CustomerVault Service
        //Initialize the PaysafeApiClient

        PrintWriter out = response.getWriter();
        boolean wasSuccessfull = false;
        Purchases purchasesResponse = null;
        PaysafeApiClient apiClient = new PaysafeApiClient(this.apiKey, this.apiPassword, Environment.TEST, this.accountNumber);
        try {


            Profile profile =
                    Profile.builder()
                            .merchantCustomerId(java.util.UUID.randomUUID().toString())
                            .locale(Locale.EN_US)
                            .firstName("John")
                            .lastName("Smith")
                            .email("john.smith@somedomain.com")
                            .phone("713-444-5555")
                            .build();

            Profile profileRes = apiClient.customerVaultService().create(profile);

            Address address =
                    Address.builder()
                            .nickName("home")
                            .street("100 Queen Street West")
                            .street2("Unit 201")
                            .city("Toronto")
                            .country("CA")
                            .state("ON")
                            .zip("M5H 2N2")
                            .recipientName("Jane Doe")
                            .phone("647-788-3901")
                            .profileId(profileRes.getId())
                            .build();

            Address addressRes = apiClient.customerVaultService().create(address);

            BACSBankAccounts bacsBankAccounts =
                    BACSBankAccounts.builder()
                            .nickName(request.getParameter("nickName"))
                            .sortCode(request.getParameter("sortCode"))
                            .accountNumber(request.getParameter("accountNumber"))
                            .accountHolderName(request.getParameter("accountHolderName"))
                            .billingAddressId(addressRes.getId())
                            .profileId(profileRes.getId())
                            .build();


            BACSBankAccounts bacsBankAccountsRes = apiClient.customerVaultService().create(bacsBankAccounts);

            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            final Gson gson = gsonBuilder.create();


            out.println("response" + gson.toJson(bacsBankAccountsRes));


            wasSuccessfull = true;

        } catch (PaysafeException e) {
            // TODO Auto-generated catch block
            request.setAttribute("error", e.getMessage());
            out.println(e.getMessage());
        }


        request.setAttribute("purchases", purchasesResponse);

        // Create a new CustomerVault request
        request.setAttribute("isPost", "true");
        request.setAttribute("currency", this.currencyCode);

        if (wasSuccessfull) {
            RequestDispatcher view = request.getRequestDispatcher("status.jsp");
            view.forward(request, response);
        } else {
            RequestDispatcher view = request.getRequestDispatcher("CustomerVaultBACSBank.jsp");
            view.forward(request, response);
        }

    }

}
