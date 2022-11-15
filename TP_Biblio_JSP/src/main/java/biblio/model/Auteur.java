package biblio.model;

public class Auteur {

	private Long   id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    
    public Auteur() { 
    }
    
    public Auteur(String nom, String prenom, String telephone, String email) { 
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
		this.nom = nom.substring(0, 20);
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom.substring(0, 20);
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone.substring(0, 10);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.substring(0, 60);
	}

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getPrenom() + " - " + getTelephone() + " / " + getEmail();
	}


}
