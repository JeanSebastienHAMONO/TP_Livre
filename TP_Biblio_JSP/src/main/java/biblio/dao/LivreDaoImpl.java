package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblio.model.Auteur;
import biblio.model.Livre;

public class LivreDaoImpl implements LivreDao {

	public static final String SQL_INSERT = "INSERT INTO LIVRE(ID_AUTEUR, TITRE, NB_PAGES, CATEGORIE) VALUES(?,?,?,?)";
	public static final String SQL_SELECT_SIMPLE = "SELECT ID, ID_AUTEUR, TITRE, NB_PAGES, CATEGORIE FROM LIVRE";
	public static final String SQL_SELECT = SQL_SELECT_SIMPLE + " ORDER BY TITRE";
	public static final String SQL_SELECT_BY_ID = SQL_SELECT_SIMPLE + " WHERE ID = ?";
	public static final String SQL_SELECT_BY_ID_AUTEUR = SQL_SELECT_SIMPLE + " WHERE ID_AUTEUR=?";
	public static final String SQL_DELETE_BY_ID = "DELETE FROM LIVRE WHERE ID = ?";
	public static final String SQL_UPDATE = "UPDATE LIVRE SET ID_AUTEUR=?, TITRE=?, NB_PAGES=?, CATEGORIE=? WHERE ID=?";

	private DaoFactory factory;

	/**
	 * Constructeur. Utiliser par le DaoFactory
	 * @param factory le DaoFactory
	 */
	protected LivreDaoImpl(DaoFactory factory) {
		this.setFactory(factory);
	}

	@Override
	public void creer(Livre livre) throws DaoException {
		Connection con = null;

		try {
			con = this.getFactory().getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, livre.getAuteur().getId());
			ps.setString(2, livre.getTitre());
			ps.setInt(3, livre.getNbPages());
			ps.setString(4, livre.getCategorie());

			int resultat = ps.executeUpdate();

			if (resultat == 0)
				throw new DaoException("Echec création Livre (aucun ajout)");

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				// On récupère l'identifiant généré par la BDD
				livre.setId(rs.getLong(1));
			} else {
				throw new DaoException("Echec création Livre (ID non retourné)");
			}
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec création Livre", ex);
		} finally {
			this.getFactory().releaseConnection(con);

		}

	};

	/**
	 * Retourne un ResultSet au format d'un livre
	 * 
	 * @param rs ResulSet contenant les informations sur le livre
	 * @return le livre contenu dans le ResultSet
	 * @throws SQLException
	 * @throws DaoException
	 */
	private static Livre map(ResultSet rs) throws SQLException, DaoException {
		Livre livre = new Livre();
		livre.setId(rs.getLong("id"));
		livre.setAuteur(DaoFactory.getInstance().getAuteurDao().trouver(rs.getLong("id_auteur")));
		livre.setTitre(rs.getString("titre"));
		livre.setNbPages(rs.getInt("nb_pages"));
		livre.setCategorie(rs.getString("categorie"));
		return livre;
	}

	@Override
	public Livre trouver(long id) throws DaoException {
		Livre livre = new Livre();
		Connection con = null;

		try {
			con = this.getFactory().getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				livre = LivreDaoImpl.map(rs);
			}

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Erreur lors de la recherche du livre : " + id, ex);
		} finally {
			this.getFactory().releaseConnection(con);
		}

		return livre;
	};

	@Override
	public List<Livre> lister() throws DaoException {
		List<Livre> listLivre = new ArrayList<Livre>();
		Connection con = null;

		try {
			con = this.getFactory().getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				listLivre.add(LivreDaoImpl.map(rs));

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Erreur lors du listage des livres", ex);
		} finally {
			this.getFactory().releaseConnection(con);
		}

		return listLivre;
	};

	@Override
	public void supprimer(long id) throws DaoException {
		Connection con = null;

		try {
			con = this.getFactory().getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_DELETE_BY_ID);
			ps.setLong(1, id);

			int resultat = ps.executeUpdate();

			if (resultat == 0) {
				throw new DaoException("Erreur de suppression Livre(" + id + ")");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Erreur lors de la suppression du livre : " + id, ex);
		} finally {
			this.getFactory().releaseConnection(con);
		}

	};

	@Override
	public void miseAJour(Livre livre) throws DaoException {
		Connection con = null;

		try {
			con = this.getFactory().getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setLong(1, livre.getAuteur().getId());
			ps.setString(2, livre.getTitre());
			ps.setInt(3, livre.getNbPages());
			ps.setString(4, livre.getCategorie());
			ps.setLong(5, livre.getId());

			int resultat = ps.executeUpdate();

			if (resultat == 0) {
				throw new DaoException("Erreur de mise à jour Livre(" + livre.getId() + ")");
			}

			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Erreur lors de la maj du livre : " + livre.getId(), ex);
		} finally {
			this.getFactory().releaseConnection(con);
		}
	}

	@Override
	public List<Livre> getBooksByAutor(Auteur auteur) throws DaoException {
		List<Livre> listLivre = new ArrayList<Livre>();
		Connection con = null;

		try {
			con = this.getFactory().getConnection();

			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID_AUTEUR);
			ps.setLong(1, auteur.getId());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				listLivre.add(LivreDaoImpl.map(rs));

			rs.close();
			ps.close();

		} catch (SQLException ex) {
			throw new DaoException("Erreur lors du listage des livres d'un auteur", ex);
		} finally {
			this.getFactory().releaseConnection(con);
		}

		return listLivre;
	};
	
	
	
	public DaoFactory getFactory() {
		return factory;
	}

	public void setFactory(DaoFactory factory) {
		this.factory = factory;
	};
}
