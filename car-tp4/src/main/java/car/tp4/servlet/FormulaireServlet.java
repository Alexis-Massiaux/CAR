package car.tp4.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/formulaire")
public class FormulaireServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/formulaire.html");
	    dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String nameAuteur 	= (String) request.getParameter("auteur");
		String nameTitre 	= (String) request.getParameter("titre");
		String date			= (String) request.getParameter("date");
		
		request.setAttribute("auteur", nameAuteur);
        request.setAttribute("titre", nameTitre);
        request.setAttribute("date", date);
        
        // Redirection vers la page recapitulatif.jsp
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/recapitulatif.jsp");
        dispatcher.forward(request, response);
		
		
	}

}
