package servlet.login;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Login Servlet for an online journal
*/

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		LoginForm html = new LoginForm();
		response.getWriter().println(html.print());
		if(request.getParameter("username") != null){
			response.getWriter().println("<script>\ndocument.getElementById('username').value = '" + 
					request.getParameter("username") + "';\n" +
					"document.getElementById('errorMessage').innerHTML = 'The username and password is invalid<br>';\n</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
