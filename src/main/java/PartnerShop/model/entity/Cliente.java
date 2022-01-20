package PartnerShop.model.entity;

public class Cliente extends UtenteRegistrato{
   private int cartaDiCredito;

    public Cliente(String nome, String cognome, String dataDiNascita, String username, String password, String email, String indirizzo,String cellulare,int tipo, int cartaDiCredito) {
        super(nome, cognome, dataDiNascita, username, password, email, indirizzo,cellulare,tipo);
        this.cartaDiCredito = cartaDiCredito;
    }
    public Cliente(){

    }

    public int getCartaDiCredito() {
        return cartaDiCredito;
    }

    public void setCartaDiCredito(int cartaDiCredito) {
        this.cartaDiCredito = cartaDiCredito;
    }
}
