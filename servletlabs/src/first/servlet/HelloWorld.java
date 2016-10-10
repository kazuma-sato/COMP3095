package first.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public HelloWorld() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		pw.println("");
		pw.println("");
		pw.println("");
		pw.println("<h1>Hello World</h1>");
		pw.println("");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		pw.println("<h2>");
		pw.println("Username: "+userName);
		pw.println("</h2><h2>");
		pw.println("Inputed password: "+password);
		pw.println("</h2>");
		pw.println("");
	}
}
