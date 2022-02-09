package PartnerShop.model.entity;


public class Prodotto {

    private int id;
    private String email_Venditore;
    private String nome;
    private String descrizione;
    private String categoria;
    private long prezzo_Cent;
    private int disponibilita;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail_Venditore() {
            return email_Venditore;
        }

        public void setEmail_Venditore(String email_Venditore) {
            this.email_Venditore = email_Venditore;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescrizione() {
            return descrizione;
        }

        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public long getPrezzo_Cent() {
            return prezzo_Cent;
        }

        public void setPrezzo_Cent(long prezzoCent) {
            this.prezzo_Cent = prezzoCent;
        }

        public float getPrezzo_Euro() {
            return  ((float) prezzo_Cent / 100);
        }

		public int getDisponibilita() {
			return disponibilita;
		}

		public void setDisponibilita(int disponibilita) {
			this.disponibilita = disponibilita;
		}

		@Override
		public String toString() {
			return "Prodotto{" +
					"id=" + id +
					", email_Venditore='" + email_Venditore + '\'' +
					", nome='" + nome + '\'' +
					", descrizione='" + descrizione + '\'' +
					", categoria='" + categoria + '\'' +
					", prezzoCent=" + prezzo_Cent +
					", disponibilità=" + disponibilita +
					'}';
		}
    }


