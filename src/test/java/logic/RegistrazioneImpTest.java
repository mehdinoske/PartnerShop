package logic;

import org.junit.Test;

public class RegistrazioneImpTest {
    @Test
    public void registraEmailNonRispettaIlFormatoTest(){
        String username = "Peppe2699"; //corretta
        String email = "peppe.abbatiello!hotmail.it"; // non corretta
        String passwd = "Peppe123.", passwdCheck = "Peppe123."; //corretta
        String nome = "Giuseppe";  //corretta
        String cognome = "abatielo";  //corretta
        String cellulare = "3332211000";  //corretta
        String DataDiNascita = "2000-01-01";  //corretta
        String indirizzo = "via Bologna, Napoli";  //corretta


    }
}
