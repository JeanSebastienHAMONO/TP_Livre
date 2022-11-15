package biblio.model;

public class Auteur extends GenericBean {

	private Long   id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    
    public static int NOM_MAX_LENGTH = 20;
    public static int PRENOM_MAX_LENGTH = 20;
    public static int TELEPHONE_MAX_LENGTH = 10;
    public static int EMAIL_MAX_LENGTH = 60;
    
    public Auteur() { 
    }
    
    public Auteur(String nom, String prenom, String telephone, String email) { 
    	super();
    	this.setNom(nom);
    	this.setPrenom(prenom);
    	this.setTelephone(telephone);
    	this.setEmail(email);
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = this.ctrlLength(nom, Auteur.NOM_MAX_LENGTH);
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom =  this.ctrlLength(prenom, Auteur.PRENOM_MAX_LENGTH);
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = this.ctrlLength(telephone, Auteur.TELEPHONE_MAX_LENGTH);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = this.ctrlLength(email, Auteur.EMAIL_MAX_LENGTH);
	}

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getPrenom() + " - " + getTelephone() + " / " + getEmail();
	}


}
