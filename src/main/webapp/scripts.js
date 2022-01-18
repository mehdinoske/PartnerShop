$(document).ready(function () {
    $('#categorie_choice').on('change',function () {
        $('form#form_categorie').submit();
    });
});

function toggleForm() {
    if(document.getElementById("form_login").style.display == "none") {
        document.getElementById("form_login").style.display = "flex";
    }else{
        document.getElementById("form_login").style.display = "none";
    }
}

function ricerca(str) {
    var datalist = document.getElementById('datalist');
    if(str.length == 0){
        datalist.innerHTML = "";
    }

    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.responseType = 'json';
    xmlHttpRequest.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200){
            datalist.innerHTML = "";
            for(var i in this.response){
                var option = document.createElement('option');
                option.value = this.response[i];
                datalist.appendChild(option);
            }
        }
    }
    xmlHttpRequest.open("GET","RicercaAjax?p="+encodeURIComponent(str),true);
    xmlHttpRequest.send();
}



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

function validaUsername() {
    var input = document.getElementById("username");
    if (input.value.length >= 6) {

        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
                usernameOk = true;
                input.style.borderBottom = borderOk;
            } else {
                alert('entrato in ajax falso '+this.status+this.responseText)
                usernameOk = false;
                input.style.borderBottom = borderNo;
            }
            cambiaStatoSubmit();
            cambiaStatoSubmit2();
        }
        xmlHttpReq.open("GET", "usernameServlet?username=" + encodeURIComponent(input.value), true);
        xmlHttpReq.send();
    } else {
        usernameOk = false;
        input.style.borderBottom = borderNo;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}

function validaPassword() {
    var input = document.getElementById("pass");
    var inputConf = document.getElementById("passConf");
    var password = input.value;

    if (input.value.length >= 8 && password.toLowerCase() != password && password.toUpperCase() != password && /[0-9]/.test(password)) {
        input.style.borderBottom = borderOk;
        if (password == inputConf.value) {
            inputConf.style.borderBottom = borderOk;
            passwordOk = true;
        } else {
            inputConf.style.borderbottom = borderNo;
            passwordOk = false;
        }
    } else {
        passwordOk = false;
        input.style.borderBottom = borderNo;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}

function validaNome() {
    var input = document.getElementById("nome");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.borderBottom = borderOk;
        nomeOk = true;
    } else {
        input.style.borderBottom = borderNo;
        nomeOk = false;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}

function validaCognome() {
    var input = document.getElementById("cognome");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.borderBottom = borderOk;
        cognomeOk = true;
    } else {
        input.style.borderBottom = borderNo;
        cognomeOk = false;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}


function validaEmail() {
    var input = document.getElementById("email");
    if (input.value.trim().length > 0 && input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
        input.style.borderBottom = borderOk;
        emailOk = true;
    } else {
        input.style.borderBottom = borderNo;
        emailOk = false;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}

function validaCellulare() {
    var input = document.getElementById("cell");
    if (input.value.trim().length > 0 && input.value.match(/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/)) {
        input.style.borderBottom = borderOk;
        cellulareOk = true;
    } else {
        input.style.borderBottom = borderNo;
        cellulareOk = false;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}

function validaIndirizzo() {
    var input = document.getElementById("indir");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.borderBottom = borderOk;
        indirizzoOk = true;
    } else {
        input.style.borderBottom = borderNo;
        indirizzoOk = false;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}


function validaNomeDelNegozio() {
    var input = document.getElementById("nomedelnegozio");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.borderBottom = borderOk;
        nomenegozioOk = true;
    } else {
        input.style.borderBottom = borderNo;
       nomenegozioOk = false;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}

function validaPIVA() {
    var input = document.getElementById("p.iva");
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.borderBottom = borderOk;
        pivaOk = true;
    } else {
        input.style.borderBottom = borderNo;
        pivaOk = false;
    }
    cambiaStatoSubmit();
    cambiaStatoSubmit2();
}

function cambiaStatoSubmit() {
    if (!usernameOk && passwordOk && emailOk && nomeOk && cognomeOk && cellulareOk && indirizzoOk) {
        document.getElementById('registrami').disabled = false;
        document.getElementById('notificaMes').innerHTML = '';
    } else {
        document.getElementById('registrami').disabled = true;
        document.getElementById('notificaMes').innerHTML = ' Verifica che tutti i campi siano in verde';
    }
}

function cambiaStatoSubmit2() {
    if (!usernameOk && passwordOk && emailOk && nomeOk && cognomeOk && cellulareOk && indirizzoOk && nomenegozioOk && pivaOk) {
        document.getElementById('registrami2').disabled = false;
        document.getElementById('notificaMes2').innerHTML = '';
    } else {
        document.getElementById('registrami2').disabled = true;
        document.getElementById('notificaMes2').innerHTML = ' Verifica che tutti i campi siano in verde';
    }
}