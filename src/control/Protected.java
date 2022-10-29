package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/Protected")
public class Protected extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtenteModelDM utenteModel = new UtenteModelDM();
		ArrayList<UtenteBean> utenti = null;
		try {
			utenti = utenteModel.doRetrieveAll("");
			request.removeAttribute("utenti");
			request.setAttribute("utenti", utenti);
		} catch (SQLException e1) {
			System.out.println("Error: " + e1.getMessage());
			request.setAttribute("error", e1.getMessage());
		}
		
		String action = request.getParameter("action");
		String type = request.getParameter("type");
		String deleteType = request.getParameter("deleteType");
		
		try {
			if (action != null) {
				switch (action) {
				case "aggiungi": {
					if (type.equals("film")) {
						FilmModelDM filmModel = new FilmModelDM();
						FilmBean film = new FilmBean();

						film.setNome(request.getParameter("nome"));
						film.setDurata(Integer.parseInt(request.getParameter("durata")));
						film.setLink(request.getParameter("link"));
						film.setImg(request.getParameter("copertina"));
						film.setGenere(request.getParameter("genere"));

						filmModel.doSave(film);
					} else if (type.equals("serie")) {
						SerieTvModelDM serieModel = new SerieTvModelDM();
						SerieTvBean serie = new SerieTvBean();

						serie.setNome(request.getParameter("nome"));
						serie.setDurata(Integer.parseInt(request.getParameter("durata")));
						serie.setLink(request.getParameter("link"));
						serie.setImg(request.getParameter("copertina"));
						serie.setNumEpisodi(Integer.parseInt(request.getParameter("numEpisodi")));

						serieModel.doSave(serie);
					}
				}
					break;
				case "elimina": {
					if (deleteType.equals("film")) {
						FilmModelDM filmModel = new FilmModelDM();
						FilmBean film = new FilmBean();

						film.setNome(request.getParameter("nomeDelete"));
						filmModel.doDelete(film);
					} else if (deleteType.equals("serie")) {
						SerieTvModelDM serieModel = new SerieTvModelDM();
						SerieTvBean serie = new SerieTvBean();

						serie.setNome(request.getParameter("nomeDelete"));
						serieModel.doDelete(serie);
					}
				}
					break;
				case "promuovi": {
					String username = request.getParameter("username");
					UtenteBean utente = utenteModel.doRetrieveByKey(username);

					utente.setAdmin(true);
					utenteModel.doUpdate(utente);
					
					utenti = utenteModel.doRetrieveAll("");
					request.removeAttribute("utenti");
				}
					break;
				case "rimuovi": {
					String username = request.getParameter("username");
					UtenteBean utente = utenteModel.doRetrieveByKey(username);
					utenteModel.doDelete(utente);
					
					utenti = utenteModel.doRetrieveAll("");
					request.removeAttribute("utenti");
				}
					break;
				}
			}
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/authFilter/adminFilter/protected.jsp");
		dispatcher.forward(request, response);
	}
}
