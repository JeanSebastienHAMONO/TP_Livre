package biblio.model;

/**
 * Bean generique 
 *
 */
public class GenericBean {
	
	/**
	 * Constructeur
	 */
	public GenericBean() {
		
	}
	
	/**
	 * Retourne la chaine de caracteres avec un controle sur sa taille
	 * @param chaine chaine à controler
	 * @param maxLength taille maximum de la chaine à retourner
	 * @return la chaine si sa taille <= maxLength, sinon retourne les {maxLength} caracteres de {chaine}
	 */
	protected String ctrlLength(String chaine, int maxLength) {
		return (chaine.length() > maxLength ? chaine.substring(0, maxLength) : chaine);
	}

	
}
