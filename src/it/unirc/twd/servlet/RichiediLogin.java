package it.unirc.twd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.twd.beans.Utente;
import it.unirc.twd.beans.UtenteDAO;

/**
 * Servlet implementation class RichiestaLogin
 */
@WebServlet("/RichiediLogin")
public class RichiediLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utente u = new Utente();
		UtenteDAO uDAO = new UtenteDAO();
		u.setUsername((String) request.getParameter("username"));
		u.setPassword((String) request.getParameter("password"));
		System.out.println(u.getUsername() + " " + u.getPassword());
		response.getWriter().append("Ho preso i parametri");
		boolean esiste = uDAO.login(u);
		if(esiste) {
			Utente loggato = uDAO.getUtente(u);
			response.getWriter().append("Ho controllato che esiste");
			if(loggato.getAutorita().equals("si")) {
				//dispatcho alla index amministratore
			}
			else if(loggato.getAutorita().equals("no")){
				response.getWriter().append("Sono all'autorità");
				request.getRequestDispatcher("/WebContent/privato/utente/indexutente.jsp").forward(request, response);
			}
		}
		else {
			//Mando un errore
		}
		
	}

}
