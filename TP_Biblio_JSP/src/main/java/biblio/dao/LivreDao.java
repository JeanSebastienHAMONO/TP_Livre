package biblio.dao;

import java.util.List;

import biblio.model.Auteur;
import biblio.model.Livre;

public interface LivreDao {

	/**
	 * Création d'un livre en BDD 
	 * @param livre livre à mettre en BDD
	 * @throws DaoException
	 */
	void creer(Livre livre) throws DaoException;

	/**
	 * Retourne un livre à partir de son identifiant
	 * @param id identifiant du livre
	 * @return le livre identifié
	 * @throws DaoException
	 */
	Livre trouver(long id) throws DaoException;

	/**
	 * Retourne la liste des livres 
	 * @return la liste des livres
	 * @throws DaoException
	 */
	List<Livre> lister() throws DaoException;

	/**
	 * Supprime un livre en BDD en fonction de son identifiant
	 * @param id identifiant du livre à supprimer
	 * @throws DaoException
	 */
	void supprimer(long id) throws DaoException;

	/**
	 * Met à jour un livre en BDD en fonction de son identifiant
	 * @param livre identifiant du livre à mettre à jour
	 * @throws DaoException
	 */
	void miseAJour(Livre livre) throws DaoException;
	
	/**
	 * Retourne la liste des livres d'un auteur
	 * @param auteur auteur dont on veut la liste des livres
	 * @return la liste des livres d'un auteur
	 * @throws DaoException
	 */
	List<Livre> getBooksByAutor(Auteur auteur) throws DaoException;
}
