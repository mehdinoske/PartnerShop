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

        public String getPrezzo_Euro() {
            return String.format("%.2f", prezzo_Cent / 100.);
        }

		public int getDisponibilita() {
			return disponibilita;
		}

		public void setDisponibilita(int disponibilità) {
			this.disponibilita = disponibilità;
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

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Prodotto other = (Prodotto) obj;
			if (categoria == null) {
				if (other.categoria != null)
					return false;
			} else if (!categoria.equals(other.categoria))
				return false;
			if (descrizione == null) {
				if (other.descrizione != null)
					return false;
			} else if (!descrizione.equals(other.descrizione))
				return false;
			if (id != other.id)
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (prezzo_Cent != other.prezzo_Cent)
				return false;
			if (email_Venditore == null) {
				if (other.email_Venditore != null)
					return false;
			} else if (!email_Venditore.equals(other.email_Venditore))
				return false;
			if (disponibilita != other.disponibilita)
				return false;
			return true;
		}

    }

}
