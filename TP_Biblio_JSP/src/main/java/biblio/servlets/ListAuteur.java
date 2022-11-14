package biblio.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblio.dao.AuteurDao;
import biblio.dao.DaoFactory;

/**
 * Servlet implementation class ListAuteur
 */
@WebServlet("/ListAuteurs")
public class ListAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAuteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
        
        try {
        	request.setAttribute("listAuteur", auteurDao.lister());
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ListAuteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
