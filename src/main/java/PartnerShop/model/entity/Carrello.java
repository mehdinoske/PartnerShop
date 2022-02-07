package PartnerShop.model.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Carrello {
    private LinkedHashMap<Integer, Prodotto> prodottoHash = new LinkedHashMap();
    private LinkedHashMap<Integer, Integer> quantHash = new LinkedHashMap();

    public Carrello() {
    }

    public Collection<Prodotto> getProdotti() {
        return this.getProdottoHash().values();
    }

    public Prodotto getProdotto(int idProdotto) {
        return (Prodotto) this.getProdottoHash().get(idProdotto);
    }

    public int getQuant(int idProdotto) {
        return (Integer)this.getQuantHash().get(idProdotto);
    }

    public void remove(int idProdotto) {
        this.getProdottoHash().remove(idProdotto);
        this.getQuantHash().remove(idProdotto);
    }

    public float getPrezzoEuro(int idProdotto) {
        return (float)(Integer)this.getQuantHash().get(idProdotto) * (float)((Prodotto)this.getProdottoHash().get(idProdotto)).getPrezzo_Euro();
    }

    public float sommaTot() {
        float somma = 0.0F;
        Iterator it = this.getProdottoHash().keySet().iterator();

        Integer key;
        for(Iterator var3 = this.getProdottoHash().keySet().iterator(); var3.hasNext(); somma += (float)this.getPrezzoEuro(key)) {
            key = (Integer)var3.next();
        }

        return somma;
    }

    public void setProdottoHash(Prodotto pr) {
        this.getProdottoHash().put(pr.getId(), pr);
    }

    public void setQuantHash(int idProdotto, int quant) {
        this.getQuantHash().put(idProdotto, quant);
    }

    public LinkedHashMap<Integer, Prodotto> getProdottoHash() {
        return this.prodottoHash;
    }

    public LinkedHashMap<Integer, Integer> getQuantHash() {
        return this.quantHash;
    }
}
