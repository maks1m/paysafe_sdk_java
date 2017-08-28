<html>
<head></head>
<body>
<div>
    <form method="post" action="direct-debit-standalone-sample-payment-token">

        <div>
            <input type="radio" name="account" value="ach" checked>ACH<br>
            <input type="radio" name="account" value="eft">EFT<br>
            <input type="radio" name="account" value="bacs">BACS<br>
        </div>
        <div>

            <label>MerchantRefNo</label><input name="merchantRefNum"
                                               value="<%=java.util.UUID.randomUUID().toString()%>"><br>
            <label>Amount</label><input name="amount" value="10098"><br>
            <label>Payment Token</label><input name="paymentToken"><br>
        </div>

        <input type="submit" value="Submit">

    </form>
</div>

<%=(String) request.getAttribute("error")%>


</body>
</html>
