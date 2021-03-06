package second.searchEngine;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class searchEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public searchEngine() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("searchEngine");
		switch(name){
			case "Google":
			case "Yahoo":
			case "Bing":
				response.sendRedirect("http://www." + name.toLowerCase() + ".ca");
			default:
				response.sendError(HttpServletResponse.SC_NOT_FOUND,  "The search engine is fake and cannot be found.");
		}
		
	}

}
