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
@WebServlet("/RichiestaLogin")
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
		u.setUsername((String) request.getAttribute("username"));
		u.setPassword((String) request.getAttribute("password"));
		boolean esiste = uDAO.login(u);
		if(esiste) {
			Utente loggato = uDAO.getUtente(u);
			if(loggato.isAutorita()) {
				//dispatcho alla index amministratore
			}
			else {
				//dispatcho alla index di utente loggato
			}
		}
		else {
			//Mando un errore
		}
		
	}

}
