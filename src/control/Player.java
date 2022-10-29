package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FilmModelDM;
import model.ProdottoBean;
import model.RecensioneBean;
import model.RecensioneModelDM;
import model.SerieTvModelDM;

@WebServlet("/Player")
public class Player extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = (String)request.getSession().getAttribute("username");
		String nomeProdotto = request.getParameter("nomeProdotto");

		String action = request.getParameter("action");
		String type = request.getParameter("type");
		try {
			if (action != null) {
				switch (action) {
				case "modificaRecensione": {
					RecensioneModelDM recensioneModel = new RecensioneModelDM();
					RecensioneBean recensione = recensioneModel.doRetrieveByUserAndProduct(username, nomeProdotto);
					
					int voto = Integer.parseInt(request.getParameter("number"));
					String descrizione = request.getParameter("descrizione");
					
					if(recensione == null) {
						recensione = new RecensioneBean();
						recensione.setNomeUtente(username);
						recensione.setNomeProdotto(nomeProdotto);
						recensione.setVoto(voto);
						recensione.setDescrizione(descrizione);
						
						recensioneModel.doSave(recensione);
					} else {
						recensione.setDescrizione(descrizione);
						recensione.setVoto(voto);
						
						recensioneModel.doUpdate(recensione);
					}
				} 
				case "watch": {
					if(type.equals("film")) {
						FilmModelDM filmModel = new FilmModelDM();
						ProdottoBean prodFilm = filmModel.doRetrieveByKey(nomeProdotto);
						request.getSession().setAttribute("prodotto", prodFilm);
					} else if(type.equals("serie")) {
						SerieTvModelDM serieModel = new SerieTvModelDM();
						ProdottoBean prodSerie = serieModel.doRetrieveByKey(nomeProdotto);
						request.getSession().setAttribute("prodotto", prodSerie);
					}	
				}
				}
			}
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/authFilter/player.jsp");
		dispatcher.forward(request, response);
	}
}
