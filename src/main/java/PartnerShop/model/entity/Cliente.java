package PartnerShop.model.entity;

public class Cliente extends UtenteRegistrato{
   private String cartaDiCredito;

    public Cliente(String nome, String cognome, String dataDiNascita, String username, String password, String email, String indirizzo,String cellulare,int tipo, String cartaDiCredito) {
        super(nome, cognome, dataDiNascita, username, password, email, indirizzo,cellulare,tipo);
        this.cartaDiCredito = cartaDiCredito;
    }
    public Cliente(){

    }

    public String getCartaDiCredito() {
        return cartaDiCredito;
    }

    public void setCartaDiCredito(String cartaDiCredito) {
        this.cartaDiCredito = cartaDiCredito;
    }
}
