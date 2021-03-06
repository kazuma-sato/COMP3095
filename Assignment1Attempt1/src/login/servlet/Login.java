package login.servlet;

/*
	COMP3095 Web Application Development with Java
	Assignment 1 - Servlets
	Instructor : Sergio Santilli sergio.santilli@georgebrown.ca
	
	by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
	Date: Monday, October 10, 2016

	Description:
		Login Class for login servlet for an online journal
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		
		
	}

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		doGet(request, response);
	}

}
