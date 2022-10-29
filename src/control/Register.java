package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteBean;
import model.UtenteModelDM;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Register() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String[] numeri = request.getParameterValues("number");
		
		String redirectedPage;
		try {
			UtenteBean bean = new UtenteBean();
			bean.setUsername(username);
			bean.setPassword(password);
			bean.setNome(nome);
			bean.setCognome(cognome);
			
			UtenteModelDM utente = new UtenteModelDM();
			utente.doSave(bean);
			for(String n : numeri) {
				if(n.length() == 10)
					utente.addNumber(username, n);
			}
			
			redirectedPage = "/login.jsp";
		} catch (Exception e) {
			System.out.println(e);
			request.getSession().setAttribute("adminRoles", new Boolean(false));
			redirectedPage = "/register.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
