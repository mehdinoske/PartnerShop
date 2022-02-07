package UnitTesting.GestioneUtente;

import PartnerShop.GestioneUtenti.model.GestioneUtenteServiceImp;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.registrazione.service.RegistrazioneServiceImp;
import org.junit.Before;
import org.mockito.Mockito;

public class GestioneUtenteServiceImpTest {

    GestioneUtenteServiceImp utService;
    UtenteRegistrato utMock;
    UtenteRegistratoDAO utDBMock;

    @Before
    public void setup(){
        utMock = Mockito.mock(UtenteRegistrato.class);
        utDBMock = Mockito.mock(UtenteRegistratoDAO.class);
        this.utService = new GestioneUtenteServiceImp();

    }
}
