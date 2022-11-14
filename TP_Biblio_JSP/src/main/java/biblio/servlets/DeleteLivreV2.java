package biblio.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblio.dao.DaoFactory;

/**
 * Servlet implementation class DeleteLivreV2
 */
@WebServlet("/deleteLivreV2")
public class DeleteLivreV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLivreV2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// On recupere l'id du livre
			String idlivre = request.getParameter("idlivre");
			if ((idlivre != null) && (idlivre.length() > 0)) {
				long id = Long.parseLong(idlivre);

     			DaoFactory.getInstance().getLivreDao().supprimer(id);

			} else {
				System.out.println("L'identifant du livre est null");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la préparation (GET) de la suppression du livre !");
		}

		response.sendRedirect(request.getContextPath() + "/ListLivres");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
