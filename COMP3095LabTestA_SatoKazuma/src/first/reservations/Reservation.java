package first.reservations;
/*
	COMP3095 Web Application Development with Java
	Lab Test 1
	Instructor : Sergio Santilli sergio.santilli@georgebrown.ca
	
	by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
	Date: Monday, October 11, 2016

	Description:
		Servlet for Classroom Reservation Form Validation
*/
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Reservation")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String studentNum;
	private String roomNum;
	private Date date;
	private Time time;
	
	
    public Reservation() {
        super();
    }

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		response.sendRedirect("\reservationForm.html");
	}

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		if(request.getParameter("firstName") != null)
			firstName = request.getParameter("firstName");
		else {
			
		}
		if(request.getParameter("lastName") != null)
			lastName = request.getParameter("lastName");
		else {
			
		}
		if(request.getParameter("studentNum") != null)
			studentNum = request.getParameter("studentNum");
		else {
			
		}
		if((request.getParameter("roomNum") == "C416") 
				|| (request.getParameter("roomNum") == "C418") 
				|| (request.getParameter("roomNum") == "C412")
			)
			roomNum = request.getParameter("roomNum");
		else roomNum = "C416";
		
		if((request.getParameter("month") != null)
				&& (request.getParameter("day") != null)
				&& (request.getParameter("year") != null)){
			date = new Date(Integer.parseInt(request.getParameter("year")),
					Integer.parseInt(request.getParameter("month")),
							Integer.parseInt(request.getParameter("day")));
		} else {
			date = new Date(2016, 9, 2);
		}
		if((request.getParameter("hour") != null)
				&& (request.getParameter("minutes") != null)){
			time = new Time(Integer.parseInt(request.getParameter("hour")) + 
					(request.getParameter("ampm")=="pm")?12:0), 
					request.getParameter("minutes"))
		}
			
			
			
		
			
		
	}

}
