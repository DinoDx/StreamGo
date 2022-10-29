package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FilmBean;
import model.FilmModelDM;
import model.ProdottoBean;
import model.SerieTvBean;
import model.SerieTvModelDM;
import model.Watchlist;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmModelDM film = new FilmModelDM();
		SerieTvModelDM serieTv = new SerieTvModelDM();
		
		String sortFilm = request.getParameter("sortFilm");
		String sortSerie = request.getParameter("sortSerie");
		String orderFilm = request.getParameter("orderFilm");
		String orderSerie = request.getParameter("orderSerie");

		try {
			request.removeAttribute("movies");
			request.setAttribute("movies", film.doRetrieveAll(sortFilm, orderFilm));
			request.removeAttribute("shows");
			request.setAttribute("shows", serieTv.doRetrieveAll(sortSerie, orderSerie));
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/catalogo.jsp");
		dispatcher.forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
