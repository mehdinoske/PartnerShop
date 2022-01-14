package model.entity;

public class Cliente extends UtenteRegistrato{
   private int cartaDiCredito;

    public Cliente(String nome, String cognome, String dataDiNascita, String username, String password, String email, String indirizzo, int cartaDiCredito) {
        super(nome, cognome, dataDiNascita, username, password, email, indirizzo);
        this.cartaDiCredito = cartaDiCredito;
    }


}
