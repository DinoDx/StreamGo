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

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmModelDM film = new FilmModelDM();
		SerieTvModelDM serieTv = new SerieTvModelDM();
		UtenteModelDM utenteModel = new UtenteModelDM();
		
		Watchlist watchlist = (Watchlist) request.getSession().getAttribute("watchlist");
		if (watchlist == null) {
			watchlist = new Watchlist();
			request.getSession().setAttribute("watchlist", watchlist);
		}

		String action = request.getParameter("action");
		
		String sortFilm = request.getParameter("sortFilm");
		String sortSerie = request.getParameter("sortSerie");
		String orderFilm = request.getParameter("orderFilm");
		String orderSerie = request.getParameter("orderSerie");
		boolean flag = false;

		try {
			if (action != null) {
				switch (action) {
				case "clear": {
					watchlist.clearWatchlist();
				}
					break;
				case "addFilmWatchlist": {
					String nome = request.getParameter("nome");
					for(ProdottoBean bean : watchlist.getItems()) 
						if(bean.getNome().equals(nome))
							flag = true;
					if(flag)
						break;
					FilmBean bean = film.doRetrieveByKey(nome);
					watchlist.addItem(bean);
				}
					break;
				case "addSerieTvWatchlist": {
					String nome = request.getParameter("nome");
					for(ProdottoBean bean : watchlist.getItems()) 
						if(bean.getNome().equals(nome))
							flag = true;
					if(flag)
						break;
					SerieTvBean bean = serieTv.doRetrieveByKey(nome);
					watchlist.addItem(bean);
				}
					break;
				case "deleteWatchlist": {
					String nome = request.getParameter("nome");
					watchlist.deleteItem(nome);
				}
					break;
				case "watch": {
					String nome = request.getParameter("nome");
					try {
						FilmModelDM filmModel = new FilmModelDM();
						ProdottoBean prodFilm = filmModel.doRetrieveByKey(nome);
						FilmModelDM serieModel = new FilmModelDM();
						ProdottoBean prodSerie = serieModel.doRetrieveByKey(nome);
						if(prodFilm != null) {
							System.out.println("FILM = " + prodFilm);
							request.getSession().setAttribute("prodotto", prodFilm);
						}
						else if(prodSerie != null)
							request.getSession().setAttribute("prodotto", prodSerie);
					} catch(SQLException e) {
						
					}
					
					response.sendRedirect(request.getContextPath() + "/authFilter/player.jsp");
					return;
				}
				}
			}
		} catch (SQLException | NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}

		request.getSession().setAttribute("watchlist", watchlist);
		String username = (String)request.getSession().getAttribute("username");
 		
		try {
			request.removeAttribute("movies");
			request.setAttribute("movies", film.doRetrieveAll(sortFilm, orderFilm));
			request.removeAttribute("shows");
			request.setAttribute("shows", serieTv.doRetrieveAll(sortSerie, orderSerie));
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		try {
			ArrayList<ProdottoBean> consigliati = new ArrayList<ProdottoBean>();

			for(String nome : utenteModel.doRetrieveConsigliati(username)) {
				FilmBean filmCons = film.doRetrieveByKey(nome);
				if(filmCons != null)
					consigliati.add(filmCons);
				else {
					SerieTvBean serieCons = serieTv.doRetrieveByKey(nome);
					if(serieCons != null)
						consigliati.add(serieCons);
				}
			}
			
			request.removeAttribute("consigliati");
			request.setAttribute("consigliati", consigliati);
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/authFilter/homepage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
