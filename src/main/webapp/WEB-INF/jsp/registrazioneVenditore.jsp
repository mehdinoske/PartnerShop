<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrazione Venditore"/>
</jsp:include>

<main>
    <div class="form_container_registrazione">
        <div class="inner_form_container_registrazione">
            <form  action="Registrazione?id=venditore" method="post">
                <label for="nome">Nome (Solo lettere e spazi)</label>
                <input type="text" name="nome" id="nome" value="" oninput="validaNome2()"/>
                <label for="cognome">Cognome (Solo lettere e spazi)</label>
                <input type="text" name="cognome" id="cognome" value="" oninput="validaCognome2()"/>
                <input type="date" name="ddn" value="1999-12-07"/>
                <label for="email">Email</label>
                <input type="text" name="email" id="email" value="" oninput="validaEmail2()"/>
                <label for="username">Username (almeno 6 caratteri,solo lettere e numeri)</label>
                <input type="text" name="username" value="" id="username" oninput="validaUsername2()"/>
                <label for="nomednegozio">Nome del negozio</label>
                <input type="text" name="nomedelnegozio" id="nomednegozio" value="" oninput="validaNomeDelNegozio()"/>
                <label for="piva">Partita IVA</label>
                <input type="text" name="p.iva" id="piva" value="" oninput="validaPIVA()"/>
                <label for="cell">Cellulare</label>
                <input type="text" name="cellulare" id="cell" value="" oninput="validaCellulare2()"/>
                <label for="indir">Indirizzo</label>
                <input type="text" name="indirizzo" id="indir" value="" oninput="validaIndirizzo2()"/>
                <label for="pass">Password (almeno 8 caratteri tra cui: una lettera minuscola, una lettera maiuscola, un numero)</label>
                <input type="password" name="password" id="pass" value="" oninput="validaPassword2()"/>
                <label for="passConf">Conferma Password</label>
                <input type="password" name="passwordConferma" id="passConf" value="" oninput="validaPassword2()"/>
                <input type="submit" value="Registrami" id="registrami2" disabled><span id="notificaMes2"></span>

            </form>
        </div>
    </div>
</main>

<%@include file="footer.jsp"%>