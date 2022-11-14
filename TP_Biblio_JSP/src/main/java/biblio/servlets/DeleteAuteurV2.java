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
import biblio.model.Auteur;
import biblio.model.Livre;

/**
 * Servlet implementation class DeleteAuteurV2
 */
@WebServlet("/deleteAuteurV2")
public class DeleteAuteurV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAuteurV2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// On recupere l'id de l'auteur
			String idauteur = request.getParameter("idauteur");
			if ((idauteur != null) && (idauteur.length() > 0)) {
				long id = Long.parseLong(idauteur);

				AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
				LivreDao livreDao = DaoFactory.getInstance().getLivreDao();

				Auteur auteur = auteurDao.trouver(id);

				// On recupere la liste de ses livres
				List<Livre> listLivres = livreDao.getBooksByAutor(auteur);

				// On recupere le nombre de livres de la liste
				int nbLivres = listLivres.size();

				if (nbLivres == 0) {
					auteurDao.supprimer(id);
				} else {
					System.out.println("L'auteur est relié à des livres ! Nombre livres associés : " + nbLivres);
				}
			} else {
				System.out.println("L'identifant de l'auteur est null");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la préparation (GET) de la suppression de l'auteur !");
		}

		response.sendRedirect(request.getContextPath() + "/ListAuteurs");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
