<html>
<head></head>
<body>
<div>
    <form method="post" action="customerVaultMandates">
        <fieldset>
            <legend>Customer Vault Mandates</legend>
            <div>

                <div>
                    <input type="radio" name="account" value="sepa" checked>SEPA<br>
                    <input type="radio" name="account" value="bacs">BACS<br>
                </div>

                <div>
                    <label>Reference</label>
                    <input name="reference"><br>
                </div>
                <div>
                    <label>IBAN</label>
                    <input name="iban"><br>
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
