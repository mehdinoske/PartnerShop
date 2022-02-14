var borderOk = '4px solid green';
var borderNo = '4px solid red';
var usernameOk = false;
var passwordOk = false;
var emailOk = false;
var nomeOk = false;
var cognomeOk = false;
var cellulareOk = false;
var indirizzoOk = false;
var nomenegozioOk = false;
var pivaOk = false;
var cardcOk = false;
var commentoOk = false;

function validaUsername2() {
    var input = document.getElementById("username");
    if (input.value.length >= 6 && input.value.length <= 30) {

        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
                usernameOk = true;
                input.style.borderBottom = borderOk;
            } else {
                //alert('entrato in ajax falso '+this.status+this.responseText)
                usernameOk = false;
                input.style.borderBottom = borderNo;
            }
            cambiaStatoSubmit2();
        }
        xmlHttpReq.open("GET", "usernameAjax?username=" + encodeURIComponent(input.value), true);
        xmlHttpReq.send();
    } else {
        usernameOk = false;
        input.style.borderBottom = borderNo;
    }
    cambiaStatoSubmit2();
}

function validaPassword2() {
    var input = document.getElementById("pass");
    var inputConf = document.getElementById("passConf");
    var password = input.value;

    if (password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_.,\-+*!#@?])([a-zA-Z0-9_.,\-+*!#@?]{6,25})$/)) {
        input.style.borderBottom = borderOk;
        if (password == inputConf.value) {
            inputConf.style.borderBottom = borderOk;
            passwordOk = true;
        } else {
            inputConf.style.borderBottom = borderNo;
            passwordOk = false;
        }
    } else {
        passwordOk = false;
        inputConf.style.borderBottom = borderNo;
        input.style.borderBottom = borderNo;
    }
    cambiaStatoSubmit2();
}

function validaNome2() {
    var input = document.getElementById("nome");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z]+$/)) {
        input.style.borderBottom = borderOk;
        nomeOk = true;
    } else {
        input.style.borderBottom = borderNo;
        nomeOk = false;
    }
    cambiaStatoSubmit2();
}

function validaCognome2() {
    var input = document.getElementById("cognome");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z]+$/)) {
        input.style.borderBottom = borderOk;
        cognomeOk = true;
    } else {
        input.style.borderBottom = borderNo;
        cognomeOk = false;
    }
    cambiaStatoSubmit2();
}


function validaEmail2() {
    var input = document.getElementById("email");
    if (input.value.match(/^[A-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]{2,10}$/)) {

        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
                emailOk = true;
                input.style.borderBottom = borderOk;
            } else {
                //alert('entrato in ajax falso '+this.status+this.responseText)
                emailOk = false;
                input.style.borderBottom = borderNo;
            }
            cambiaStatoSubmit2();
        }
        xmlHttpReq.open("GET", "emailAjax?email=" + encodeURIComponent(input.value), true);
        xmlHttpReq.send();
    } else {
        emailOk = false;
        input.style.borderBottom = borderNo;
    }
    cambiaStatoSubmit2();
}

function validaCellulare2() {
    var input = document.getElementById("cell");
    if (input.value.trim().length > 0 && input.value.match(/^[0-9]\d{9}$/)) {
        input.style.borderBottom = borderOk;
        cellulareOk = true;
    } else {
        input.style.borderBottom = borderNo;
        cellulareOk = false;
    }
    cambiaStatoSubmit2();
}

function validaIndirizzo2() {
    var input = document.getElementById("indir");
    if (input.value.trim().length >=5 ) {
        input.style.borderBottom = borderOk;
        indirizzoOk = true;
    } else {
        input.style.borderBottom = borderNo;
        indirizzoOk = false;
    }
    cambiaStatoSubmit2();
}


function validaNomeDelNegozio() {
    var input = document.getElementById("nomednegozio");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z]+$/)) {
        input.style.borderBottom = borderOk;
        nomenegozioOk = true;
    } else {
        input.style.borderBottom = borderNo;
        nomenegozioOk = false;
    }
    cambiaStatoSubmit2();
}

function validaPIVA() {
    var input = document.getElementById("piva");
    if (input.value.trim().length > 0 && input.value.match(/^[A-Z]{2}[A-Z0-9]{9}$/)) {
        input.style.borderBottom = borderOk;
        pivaOk = true;
    } else {
        input.style.borderBottom = borderNo;
        pivaOk = false;
    }
    cambiaStatoSubmit2();
}

function validaCartaDiCredito(){
    var input = document.getElementById("cartadc");
    if (input.value.trim().length > 0 && input.value.match(/^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$/)) {
        input.style.borderBottom = borderOk;
        cardcOk = true;
    } else {
        input.style.borderBottom = borderNo;
        cardcOk = false;
    }
    cambiaStatoSubmit3();
}


function cambiaStatoSubmit2() {
    if (usernameOk && passwordOk && emailOk && nomeOk && cognomeOk && cellulareOk && indirizzoOk && nomenegozioOk && pivaOk) {
        document.getElementById('registrami2').disabled = false;
        document.getElementById('notificaMes2').innerHTML = '';
    } else {
        document.getElementById('registrami2').disabled = true;
        document.getElementById('notificaMes2').innerHTML = ' Verifica che tutti i campi siano in verde';
    }
}

function validaNome3() {
    var input = document.getElementById("nome");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z]+$/)) {
        input.style.borderBottom = borderOk;
        nomeOk = true;
    } else {
        input.style.borderBottom = borderNo;
        nomeOk = false;
    }
    cambiaStatoSubmit3();
}

function validaCognome3() {
    var input = document.getElementById("cognome");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z]+$/)) {
        input.style.borderBottom = borderOk;
        cognomeOk = true;
    } else {
        input.style.borderBottom = borderNo;
        cognomeOk = false;
    }
    cambiaStatoSubmit3();
}

function validaIndirizzo3() {
    var input = document.getElementById("indir");
    if (input.value.trim().length >=5 ) {
        input.style.borderBottom = borderOk;
        indirizzoOk = true;
    } else {
        input.style.borderBottom = borderNo;
        indirizzoOk = false;
    }
    cambiaStatoSubmit3();
}


function cambiaStatoSubmit3() {
    if (nomeOk && cognomeOk && indirizzoOk && cardcOk) {
        document.getElementById('completaAcquisto').disabled = false;
        document.getElementById('notificaMes3').innerHTML = '';
    } else {
        document.getElementById('completaAcquisto').disabled = true;
        document.getElementById('notificaMes3').innerHTML = ' Verifica che tutti i campi siano in verde';
    }
}


function validaCommentoAggiuntivo() {
    var input = document.getElementById("commentiAggiuntivi");
    if (input.value.trim().length <= 100) {
        input.style.border = "2px solid green";
        commentoOk = true;
    } else {
        input.style.border = "2px solid red";
        commentoOk = false;
    }
    cambiaStatoSubmit4();
}

function cambiaStatoSubmit4() {
    if (commentoOk) {
        document.getElementById('segnalazioneInvio').disabled = false;
        document.getElementById('notificaMes4').innerHTML = '';
    } else {
        document.getElementById('segnalazioneInvio').disabled = true;
        document.getElementById('notificaMes4').innerHTML = ' Verifica che tutti i campi siano in verde';
    }
}