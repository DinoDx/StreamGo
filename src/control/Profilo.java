package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteBean;
import model.UtenteModelDM;

@WebServlet("/Profilo")
public class Profilo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtenteModelDM model = new UtenteModelDM();
		String username = (String) request.getSession().getAttribute("username");
		UtenteBean utente = null;
		try {
			utente = model.doRetrieveByKey(username);
			request.setAttribute("utente", utente);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String action = request.getParameter("action");
		System.out.println("ACTION = " + action);

		try {
			if (action != null)
				if (action.equals("update")) {
					String newUsername = request.getParameter("username");
					String password = request.getParameter("password");
					String nome = request.getParameter("nome");
					String cognome = request.getParameter("cognome");
					String[] numeri = request.getParameterValues("number");

					if (newUsername != null && newUsername != "")
						utente.setUsername(newUsername);
					if (password != null && password != "")
						utente.setPassword(password);
					if (nome != null && nome != "")
						utente.setNome(nome);
					if (cognome != null && cognome != "")
						utente.setCognome(cognome);

					model.doUpdate(username, utente);
					for (String n : numeri) {
						if (n.length() == 10) {
							model.addNumber(username, n);
							utente.addTelefono(n);
						}
					}
					request.setAttribute("message", "Utente " + utente.getUsername() + " updated");
				} else if (action.equals("deleteNumber")) {
					String numero = request.getParameter("numero");

					model.deleteNumber(numero);
					utente.removeTelefono(numero);
//					utente = model.doRetrieveByKey(username);
					request.removeAttribute("utente");
					request.setAttribute("utente", utente);
				}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}

//		request.setAttribute("utente", utente);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/authFilter/profilo.jsp");
		dispatcher.forward(request, response);
	}
}
