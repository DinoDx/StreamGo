package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteModelDM;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String redirectedPage;
		try {
			String ruolo = checkLogin(username, password);
			
			if(ruolo.equalsIgnoreCase("Amministratore"))
				request.getSession().setAttribute("adminAuthorization", new Boolean(true));
			request.getSession().setAttribute("loginAuthorization", new Boolean(true));
			
			request.getSession().setAttribute("username", username);
			
			redirectedPage = "/authFilter/homepage.jsp";
			
		} catch (Exception e) {
			System.out.println("LOGIN ERROR");
			request.getSession().setAttribute("loginError", "Credenziali non valide");
			redirectedPage = "/login.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}
	
	private String checkLogin(String username, String password) throws Exception {
		UtenteModelDM utente = new UtenteModelDM();
		String ruolo = utente.checkLogin(username, password);
		
		if (ruolo != null) {
			return ruolo;
		} else {
			throw new Exception("Invalid login and password");
		}
	}

}
