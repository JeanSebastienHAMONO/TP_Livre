package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Donné par le formateur
 *
 */
public class DaoFactory {
	/**
	 * Driver JDBC
	 */
	public static String DRIVER_LOCAL = "org.mariadb.jdbc.Driver";

	/**
	 * URL de la base de données
	 */
	public static String URL = "jdbc:mariadb://localhost/biblio";

	/**
	 * Utilisateur de la base de données
	 */
	public static String UTILISATEUR = "jshamono";

	/**
	 * Mot de passe de l'utilisateur
	 */
	public static String MOTDEPASSE = "jshamono";

	private String url;
	private String username;
	private String passwd;
	private Connection con = null;

	private static DaoFactory instanceSingleton = null;

	// Constructeur priv� (usage limit� � la classe elle m�me : Cf. "getInstance()")
	private DaoFactory(String url, String username, String passwd) {
		this.url = url;
		this.username = username;
		this.passwd = passwd;
	}

	public static DaoFactory getInstance() {
		if (DaoFactory.instanceSingleton == null) {
			try {
				Class.forName(DRIVER_LOCAL);
				DaoFactory.instanceSingleton = new DaoFactory(URL, UTILISATEUR, MOTDEPASSE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return DaoFactory.instanceSingleton;
	}

	public AuteurDao getAuteurDao() {
		return new AuteurDaoImpl(this);
	}

	/**
	 * Retourne un livreDao
	 * 
	 * @return un livreDao
	 */
	public LivreDao getLivreDao() {
		return new LivreDaoImpl(this);
	}


	Connection getConnection() throws SQLException {
		if (this.con == null) {
			this.con = DriverManager.getConnection(url, username, passwd);
		}
		return this.con;
	}

	// cette m�thode prend une connection en parametre en pr�sagent que l'on
	// pourrait en utiliser plusieurs
	// mais par construction actuellement la seule connection existante est stock�e
	// dans "this.con"
	void releaseConnection(Connection connectionRendue) {
		if (this.con == null) {
			return;
		}
		try {
			if (!this.con.isValid(10)) {
				this.con.close();
				this.con = null;
			}
		} catch (SQLException e) {
			con = null;
		}
	}

}
