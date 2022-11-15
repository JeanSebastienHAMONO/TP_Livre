package biblio.model;

public class Livre extends GenericBean {

	private Long id;
	private String titre;
	private int nbPages;
	private String categorie;
	private Auteur auteur;
	
	public static int TITRE_MAX_LENGTH = 50;
	public static int CATEGORIE_MAX_LENGTH = 20;
	
	public Livre() {
		
	}
	
	public Livre(String titre, int nbPages, String categorie, Auteur auteur) {
		super();
		this.setTitre(titre);
		this.setNbPages(nbPages);
		this.setCategorie(categorie);
		this.setAuteur(auteur);
	}

	@Override
	public String toString() {
		return this.getId() + " : " + this.getTitre() + " - Nb pages" + this.getNbPages() + " - " + this.getAuteur().getNom() + " " + this.getAuteur().getPrenom();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = this.ctrlLength(titre, Livre.TITRE_MAX_LENGTH);
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = this.ctrlLength(categorie, Livre.CATEGORIE_MAX_LENGTH);
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	
	
	
}
