package register.servlet;

/*
	COMP3095 Web Application Development with Java
	Assignment 1 - Servlets
	Instructor : Sergio Santilli sergio.santilli@georgebrown.ca
	
	by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
	Date: Monday, October 10, 2016

	Description:
		register Class for registration servlet for an online journal
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({ "/register", "/register.html" })
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public register() {
        super();
    }

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		doGet(request, response);
	}

}