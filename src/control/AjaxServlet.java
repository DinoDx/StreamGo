package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeProdotto = request.getParameter("nome");
		String type = request.getParameter("type");
		Watchlist watchlist = (Watchlist) request.getSession().getAttribute("watchlist");
		
//		System.out.println("WATCHLIST PRE : " + watchlist);
		
		response.setContentType("text/html");
		if (type.equals("film")) {
			FilmModelDM filmModel = new FilmModelDM();
			FilmBean film = null;
			try {
				film = filmModel.doRetrieveByKey(nomeProdotto);
				watchlist.addItem(film);
				request.getSession().setAttribute("watchlist", watchlist);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.getWriter().write(generateJSONFilmData(film));
		} else if (type.equals("serie")) {
			SerieTvModelDM serieModel = new SerieTvModelDM();
			SerieTvBean serie = null;
			try {
				serie = serieModel.doRetrieveByKey(nomeProdotto);
				watchlist.addItem(serie);
				request.getSession().setAttribute("watchlist", watchlist);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.getWriter().write(generateJSONSerieData(serie));
		}
		System.out.println("WATCHLIST POST : " + watchlist);
	}

	public String generateJSONFilmData(FilmBean film) {
		StringBuffer data = null;

		data = new StringBuffer("{");
		data.append("\"nome\" : \"" + film.getNome() + "\", ");
		data.append("\"durata\" : \"" + film.getDurata() + "\", ");
		data.append("\"link\" : \"" + film.getLink() + "\", ");
		data.append("\"img\" : \"" + film.getImg() + "\", ");
		data.append("\"genere\" : \"" + film.getGenere() + "\"");
		data.append("}");

		return data.toString();
	}
	
	public String generateJSONSerieData(SerieTvBean film) {
		StringBuffer data = null;

		data = new StringBuffer("{");
		data.append("\"nome\" : \"" + film.getNome() + "\", ");
		data.append("\"durata\" : \"" + film.getDurata() + "\", ");
		data.append("\"link\" : \"" + film.getLink() + "\", ");
		data.append("\"img\" : \"" + film.getImg() + "\", ");
		data.append("\"episodi\" : \"" + film.getNumEpisodi() + "\"");
		data.append("}");

		return data.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
