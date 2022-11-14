package biblio.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblio.dao.DaoFactory;
import biblio.dao.LivreDao;
import biblio.model.Livre;

/**
 * Servlet implementation class DeleteLivre
 */
@WebServlet("/deleteLivre")
public class DeleteLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteLivre() {
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
			// On recupere l'id du livre
			long id = (long) Integer.parseInt(request.getParameter("idlivre"));

			// On recupere le livre
			Livre livre = DaoFactory.getInstance().getLivreDao().trouver(id);

			request.setAttribute("idlivre", id);
			request.setAttribute("livre", livre);

		} catch (Exception e) {
			e.printStackTrace();
			messageErreur = "Erreur lors de la préparation (GET) de la suppression du livre !<br/>";
			supprimer = "Y";
		}

		request.setAttribute("supprimer", supprimer);
		request.setAttribute("message", message);
		request.setAttribute("messageErreur", messageErreur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteLivre.jsp").forward(request, response);
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
			LivreDao livreDao = DaoFactory.getInstance().getLivreDao();

			// On recupere l'id du livre
			long id = (long) Integer.parseInt(request.getParameter("idlivre"));

			Livre livre = livreDao.trouver(id);
			livreDao.supprimer(id);
			message = "Livre supprimé !";
			supprimer = "Y";

			request.setAttribute("livre", livre);

		} catch (Exception e) {
			e.printStackTrace();
			messageErreur = " Erreur lors de la suppression du livre !<br/>";
		}

		request.setAttribute("message", message);
		request.setAttribute("supprimer", supprimer);
		request.setAttribute("messageErreur", messageErreur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteLivre.jsp").forward(request, response);
	}

}
