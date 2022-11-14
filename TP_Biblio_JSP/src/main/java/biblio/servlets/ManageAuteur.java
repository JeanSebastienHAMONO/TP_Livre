package biblio.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblio.dao.AuteurDao;
import biblio.dao.DaoException;
import biblio.dao.DaoFactory;
import biblio.model.Auteur;

/**
 * Servlet implementation class ManageAuteur
 */
@WebServlet("/manageAuteur")
public class ManageAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageAuteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idAuteur = request.getParameter("idauteur");
		String libelleAction = "";
		
		if ((idAuteur == null) || (idAuteur.trim().length() <= 0)) {
			libelleAction = "Ajout";
		} else {
			libelleAction = "Modification";
			AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
			int id = 0;
			try {
				id = Integer.parseInt(idAuteur);
			} catch (Exception e) {
				System.out.println("Erreur sur l'identifiant de l'auteur : " + idAuteur);
				response.sendRedirect(request.getContextPath() + "/ListAuteurs");
			}
			
			if (id > 0) {
				try {
					Auteur auteur = auteurDao.trouver(id);
					request.setAttribute("idauteur", auteur.getId());
					request.setAttribute("nom", auteur.getNom());
					request.setAttribute("prenom", auteur.getPrenom());
					request.setAttribute("telephone", auteur.getTelephone());
					request.setAttribute("email", auteur.getEmail());
				} catch (Exception e) {
					System.out.println("Erreur sur l'identifiant de l'auteur : " + idAuteur);
					e.printStackTrace();
					response.sendRedirect(request.getContextPath() + "/ListAuteurs");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/ListAuteurs");
			}
		}
		
		request.setAttribute("libelleAction", libelleAction);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ManageAuteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idAuteur = request.getParameter("idauteur");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		
		String messageErreur = "";
		String message = "";
		String libelleAction = "Modification";
		boolean ctrl = true;
		
		AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
		Auteur auteur = null;
		
		if ((idAuteur == null) || (idAuteur.trim().length() <= 0)) {
			
			// Ajout d'un auteur
			libelleAction = "Ajout";
			if ((nom == null) || (nom.trim().length() <= 0)) {
				ctrl = false;
				messageErreur = messageErreur + "Veuillez saisir le nom<br/>";
			}
			if ((telephone == null) || (telephone.trim().length() <= 0)) {
				ctrl = false;
				messageErreur = messageErreur + "Veuillez saisir le numéro de téléphone<br/>";
			}
            if (ctrl) {
            	try {
            		 auteur = new Auteur(nom, prenom, telephone, email);
            		 auteurDao.creer(auteur);
            	     message = "Auteur enregitré !";
            	     request.setAttribute("idauteur", auteur.getId().toString());
            	     libelleAction = "Modification";
            	} catch (DaoException d) {
            		messageErreur = messageErreur + "Erreur lors de l'enregistrement !";
            		d.printStackTrace();
            	}
            }
		} else {
			if ((nom == null) || (nom.trim().length() <= 0)) {
				ctrl = false;
				messageErreur = messageErreur + "Veuillez saisir le nom<br/>";
			}
			if ((telephone == null) || (telephone.trim().length() <= 0)) {
				ctrl = false;
				messageErreur = messageErreur + "Veuillez saisir le numéro de téléphone<br/>";
			}
			request.setAttribute("idauteur", idAuteur);
            if (ctrl) {
            	try {
            	     auteur = new Auteur(nom, prenom, telephone, email);
            	     auteur.setId((long) Integer.parseInt(idAuteur));
            	     auteurDao.miseAJour(auteur);
            	     message = "Auteur modifié !";
            	} catch (DaoException d) {
            		messageErreur = messageErreur + "Erreur lors de la mise à jour !";
            		d.printStackTrace();
            	}
            }
		}
		
        request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);
		request.setAttribute("telephone", telephone);
		request.setAttribute("email", email);
		request.setAttribute("message", message);
		request.setAttribute("messageErreur", messageErreur);
		
		request.setAttribute("libelleAction", libelleAction);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ManageAuteur.jsp").forward(request, response);
	}

}