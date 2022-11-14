package biblio.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblio.dao.AuteurDao;
import biblio.dao.DaoFactory;
import biblio.dao.LivreDao;
import biblio.model.Livre;
import biblio.model.Auteur;

/**
 * Servlet implementation class DeleteAuteur
 */
@WebServlet("/deleteAuteur")
public class DeleteAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAuteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String messageErreur = null;
		String message = null;
		String supprimer = "N";

		try {
			// On recupere l'id de l'auteur
			long id = (long) Integer.parseInt(request.getParameter("idauteur"));

			// On recupere l'auteur
			Auteur auteur = DaoFactory.getInstance().getAuteurDao().trouver(id);

			request.setAttribute("auteur", auteur);

		} catch (Exception e) {
			e.printStackTrace();
			messageErreur = "Erreur lors de la préparation (GET) de la suppression de l'auteur !<br/>";
			supprimer = "Y";
		}

		request.setAttribute("supprimer", supprimer);
		request.setAttribute("message", message);
		request.setAttribute("messageErreur", messageErreur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteAuteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String messageErreur = null;
		String message = null;
		String supprimer = "N";

		try {
			AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
			LivreDao livreDao = DaoFactory.getInstance().getLivreDao();

			// On recupere l'id de l'auteur
			long id = (long) Integer.parseInt(request.getParameter("idauteur"));

			// On recupere l'auteur
			Auteur auteur = auteurDao.trouver(id);
			// On recupere la liste de ses livres
			List<Livre> listLivres = livreDao.getBooksByAutor(auteur);
			// On recupere le nombre de livres de la liste
			int nbLivres = listLivres.size();

			if (nbLivres == 0) {
				auteurDao.supprimer(id);
				message = "Auteur supprimé !";
				supprimer = "Y";
			} else {
				supprimer = "Y";
				messageErreur = "Pour supprimer l'auteur " + auteur.getNom() + " " + auteur.getPrenom()
						+ ", veuillez supprimer les livres (" + nbLivres + ") qui lui sont associés :<br/>";
				for (Livre livre : listLivres) {
					messageErreur = messageErreur + "- " + livre.getTitre() + "<br/>";
				}
			}

			request.setAttribute("auteur", auteur);
			request.setAttribute("listAuteur", auteurDao.lister());

		} catch (Exception e) {
			e.printStackTrace();
			messageErreur = " Erreur lors de la suppression de l'auteur !<br/>";
		}

		request.setAttribute("message", message);
		request.setAttribute("supprimer", supprimer);
		request.setAttribute("messageErreur", messageErreur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteAuteur.jsp").forward(request, response);
	}

}
