package biblio.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblio.dao.LivreDao;
import biblio.dao.DaoFactory;

/**
 * Servlet implementation class ListLivre
 */
@WebServlet("/ListLivres")
public class ListLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListLivre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivreDao livreDao = DaoFactory.getInstance().getLivreDao();
        
        try {
        	request.setAttribute("listLivre", livreDao.lister());
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ListLivre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
