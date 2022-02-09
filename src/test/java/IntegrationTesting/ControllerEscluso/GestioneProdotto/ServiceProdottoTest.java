package IntegrationTesting.ControllerEscluso.GestioneProdotto;

import PartnerShop.GestioneUtenti.service.GestioneUtenteService;
import PartnerShop.GestioneUtenti.service.GestioneUtenteServiceImp;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ServiceProdottoTest {
    GestioneProdottoService gps;
    GestioneUtenteService gus;
    ArrayList<Prodotto> prodotti;
    Prodotto prodotto = new Prodotto();
    UtenteRegistrato utente;
    ArrayList<UtenteRegistrato> utenti;

    @Before
    public void setUp() {
        gps = new GestioneProdottoServiceImp();
        gus = new GestioneUtenteServiceImp();
        prodotti = gps.getAllProdotti();
        utenti = gus.VisualizzaUtenti();
    }

    @Test
    public void getProdottoOk() {
        int id = prodotti.get(0).getId();
        Prodotto p = gps.getProdottoById(id);
        assertNotNull(p);
    }

    @Test
    public void deleteProdottoOk() {
        int id = prodotti.get(0).getId();
        assertEquals(true, gps.deleteProdottoById(id));
    }

    @Test
    public void saveProdottoOk() {
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "Utensili";
        int prezzo_Cent = 11;
        int disponibilita = 22;
        for(int i = 0; i < utenti.size(); i++) {
            utente = utenti.get(i);
            if(utente.getTipo() == 1)
                break;
        }
        prodotto.setEmail_Venditore(utente.getEmail());
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setCategoria(categoria);
        prodotto.setPrezzo_Cent(prezzo_Cent);
        prodotto.setDisponibilita(disponibilita);
        assertEquals(true, gps.doSaveProdotto(prodotto));
    }

    @Test
    public void updateProdottoOk() {
        int id = prodotti.get(0).getId();
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "Utensili";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        prodotto.setId(id);
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setCategoria(categoria);
        prodotto.setPrezzo_Cent(prezzo_Cent);
        prodotto.setDisponibilita(disponibilita);
        assertEquals(true, gps.doUpdateProdotto(prodotto));
    }

    @Test
    public void getProdottiByVenditore() {
        String email = prodotti.get(0).getEmail_Venditore();
        ArrayList<Prodotto> p = gps.getProdottiByVenditore(email);
        assertNotNull(p);
    }

    @Test
    public void getAllProdotti() {
        ArrayList<Prodotto> p = gps.getAllProdotti();
        assertNotNull(p);
    }

    @Test
    public void getProdottiByCategoria() {
        String categoria = prodotti.get(0).getCategoria();
        ArrayList<Prodotto> p = gps.getProdottiByCategoria(categoria);
        assertNotNull(p);
    }

    @Test
    public void getProdottiByNome() {
        String nome = prodotti.get(0).getNome();
        ArrayList<Prodotto> p = gps.getProdottiByNome(nome);
        assertNotNull(p);
    }
}