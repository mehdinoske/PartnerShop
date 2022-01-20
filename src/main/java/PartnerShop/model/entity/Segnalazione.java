package PartnerShop.model.entity;

public class Segnalazione {
    private int id;
    private String email;
    private int stato;
    private String motivazione;
    private String commento;

    public Segnalazione(String email, int stato, String motivazione, String commento) {
        this.email = email;
        this.stato = stato;
        this.motivazione = motivazione;
        this.commento = commento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    @Override
    public String toString() {
        return "Segnalazione{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", stato=" + stato +
                ", motivazione='" + motivazione + '\'' +
                ", commento='" + commento + '\'' +
                '}';
    }


}
