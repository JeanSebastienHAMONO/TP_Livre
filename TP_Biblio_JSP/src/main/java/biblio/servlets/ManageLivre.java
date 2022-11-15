package biblio.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblio.dao.AuteurDao;
import biblio.dao.DaoException;
import biblio.dao.DaoFactory;
import biblio.dao.LivreDao;
import biblio.model.Auteur;
import biblio.model.Livre;

/**
 * Servlet implementation class ManageLivre
 */
@WebServlet("/manageLivre")
public class ManageLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageLivre() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idLivre = request.getParameter("idlivre");
		String libelleAction = "";

		if ((idLivre == null) || (idLivre.trim().length() <= 0)) {
			libelleAction = "Ajout";
		} else {
			libelleAction = "Modification";
			LivreDao livreDao = DaoFactory.getInstance().getLivreDao();
			int id = 0;
			try {
				id = Integer.parseInt(idLivre);
			} catch (Exception e) {
				System.out.println("Erreur sur l'identifiant du livre : " + idLivre);
				response.sendRedirect(request.getContextPath() + "/ListLivres");
			}

			if (id > 0) {
				try {
					Livre livre = livreDao.trouver(id);
					request.setAttribute("livre", livre);
				} catch (Exception e) {
					System.out.println("Erreur sur l'identifiant du livre : " + idLivre);
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/ListLivres");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/ListLivres");
			}
		}

		try {
			List<Auteur> listAuteur = DaoFactory.getInstance().getAuteurDao().lister();
			request.setAttribute("listAuteur", listAuteur);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("idlivre", idLivre);
		request.setAttribute("libelleAction", libelleAction);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ManageLivre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idlivre = request.getParameter("idlivre");
		String idauteur = request.getParameter("idauteur");
		String titre = request.getParameter("titre");
		String nbpages = request.getParameter("nbpages");
		String categorie = request.getParameter("categorie");

		// Gestion des messages d'erreurs
		String messageErreur = "";
		String message = "";
		String libelleAction = "Modification";
		boolean ctrl = true;

		AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
		LivreDao livreDao = DaoFactory.getInstance().getLivreDao();
		Auteur auteur = null;
		Livre livre = null;
		int nbpa = 0;

		// Controle des valeurs des champs obligatoires
		if ((titre == null) || (titre.trim().length() <= 0)) {
			ctrl = false;
			messageErreur = messageErreur + "Le titre est obligatoire<br/>";
		}
		if ((nbpages == null) || (nbpages.trim().length() <= 0)) {
			ctrl = false;
			messageErreur = messageErreur + "Le nombre de pages est obligatoire<br/>";
		}
		try {
			nbpa = Integer.parseInt(nbpages);
		} catch (Exception e) {
			e.printStackTrace();
			ctrl = false;
			messageErreur = messageErreur + "Le format du nombre de pages n'est pas valide<br/>";
		}

		// Les controles sont Ok
		if (ctrl) {
						
			if ((idlivre == null) || (idlivre.trim().length() <= 0)) {

				// Ajout d'un livre
				libelleAction = "Ajout";

				try {

					auteur = auteurDao.trouver(Integer.parseInt(idauteur));
					livre = new Livre(titre, nbpa, categorie, auteur);
					livreDao.creer(livre);
					message = "Livre enregitré !";
					request.setAttribute("idlivre", livre.getId().toString());
					request.setAttribute("livre", livre);
					libelleAction = "Modification";
				} catch (DaoException d) {
					ctrl = false;
					messageErreur = messageErreur + "Erreur lors de l'enregistrement !";
					d.printStackTrace();
				}

			} else {

				// Modification d'un livre  
				try {
					auteur = auteurDao.trouver(Integer.parseInt(idauteur));
					livre = new Livre(titre, nbpa, categorie, auteur);
					livre.setId((long) Integer.parseInt(idlivre));
					livreDao.miseAJour(livre);
					request.setAttribute("idlivre", livre.getId().toString());
					request.setAttribute("livre", livre);
					message = "Livre modifié !";
				} catch (DaoException d) {
					ctrl = false;
					messageErreur = messageErreur + "Erreur lors de la mise à jour !";
					d.printStackTrace();
				}
			}
		}
		
		// Cas si controle Ko en ajout ou modif
		if (!ctrl) {
			livre = new Livre();
			try {
				livre = livreDao.trouver((long) Integer.parseInt(idlivre));
			} catch (Exception e) {
				livre.setTitre(titre);
				livre.setCategorie(categorie);
				livre.setNbPages(nbpa);
			}
			request.setAttribute("livre", livre);
			request.setAttribute("idlivre", idlivre);
		}

		request.setAttribute("idauteur", idauteur);
		request.setAttribute("message", message);
		request.setAttribute("messageErreur", messageErreur);

		request.setAttribute("libelleAction", libelleAction);

		try {
			List<Auteur> listAuteur = DaoFactory.getInstance().getAuteurDao().lister();
			request.setAttribute("listAuteur", listAuteur);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/ManageLivre.jsp").forward(request, response);

	}

}
