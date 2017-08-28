<%@page import="com.paysafe.websample.CustomerVaultACHBank" %>
<html>
<head></head>
<body>
<div>
    <form method="post" action="direct-debit-sample">

        <fieldset>
            <legend>Direct Debit Purchase</legend>
            <div>

                <div>
                    <label>MerchantRefNo</label>
                    <input name="merchantRefNum" value="<%=java.util.UUID.randomUUID().toString()%>"><br>
                </div>
                <div>
                    <label>Amount</label>
                    <input name="amount" value="10098"><br>
                </div>
                <div>
                    <fieldset>
                        <legend>ACH</legend>
                        <div>
                            <label>Account Holder Name</label>
                            <input name="accountHolderName" value="XYZ Company"><br>
                        </div>
                        <!-- <div>
                            <label>Account Type</label>
                            <input type="text" name="accountType"><br>
                        </div> -->
                        <div>
                            <label>Account Number</label>
                            <input name="accountNumber"
                                   value="<%= new CustomerVaultACHBank().sixDigitRandomNumber()%>"><br>
                        </div>
                        <div>
                            <label>Routing Number</label>
                            <input name="routingNumber" value="211589828"><br>
                        </div>
                    </fieldset>
                </div>
                <div>
                    <label>Customer Ip</label>
                    <input name="customerIp" value="192.0.126.111"><br>
                </div>

                <div>
                    <fieldset>
                        <legend>Profile</legend>

                        <div>
                            <label>First Name</label>
                            <input name="firstName" value="Joe"><br>
                        </div>
                        <div>
                            <label>Last Name</label>
                            <input name="lastName" value="Smith"><br>
                        </div>
                        <div>
                            <label>Email</label>
                            <input name="email" value="Joe.Smith@hotmail.com"><br>
                        </div>
                    </fieldset>
                </div>
                <div>
                    <fieldset>
                        <legend>Billing Address</legend>

                        <div>
                            <label>Street</label><input name="street" value="100 Queen Street West"><br>
                        </div>
                        <div>
                            <label>City</label>
                            <input name="city" value="Los Angeles"><br>
                        </div>
                        <div>
                            <label>State</label>
                            <input name="state" value="CA"><br>
                        </div>
                        <div>
                            <label>Country</label>
                            <input name="country" value="US"><br>
                        </div>
                        <div>
                            <label>Zip</label>
                            <input name="zip" value="90210"><br>
                        </div>
                        <div>
                            <label>Phone</label>
                            <input name="phone" value="3102649010"><br>
                        </div>
                    </fieldset>
                </div>


            </div>
            <div>
                <input type="submit" value="Submit">
            </div>


        </fieldset>
    </form>
</div>


</body>
</html>
