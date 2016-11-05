package servlet.register;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Registration Class for login servlet for an online journal
*/

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.register.FormError;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.getWriter().print(getHtml(new FormError(), false, null));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		FormError errors = formValidation(request);
		
		final String FIRST_NAME = request.getParameter("firstName").trim();
		final String LAST_NAME = request.getParameter("lastName").trim();
		final String EMAIL = request.getParameter("email").trim().toLowerCase();
		final String PHONE1 = request.getParameter("phone1").trim();
		final String PHONE2 = request.getParameter("phone2").trim();
		final String PHONE3 = request.getParameter("phone3").trim();
		final String PHONE = PHONE1 + PHONE2 + PHONE3;
		final String YEAR = request.getParameter("year").trim();
		final String MAJOR = request.getParameter("major").trim();
		final String USERNAME = request.getParameter("username").trim().toLowerCase();
		final String PASSWORD = request.getParameter("password").trim();
		
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
		final String DB_USER = "root";
		final String DB_PASS = "";
		
		if(!errors.isEmpty()){
			response.getWriter().print(getHtml(errors,false, null) +
					fillFormWithJS(request));
			return;
		}
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			
			PreparedStatement prepSelectStatement = conn.prepareStatement(
					"SELECT count(*) FROM USERS WHERE username = ?;");
			prepSelectStatement.setString(1, USERNAME);
			final ResultSet selectResult  = prepSelectStatement.executeQuery();
			selectResult.next();
			if (selectResult.getInt(1) == 1){
				System.out.println(selectResult.getString(1));
				
				errors.setUsername("Username already exists. Please try another one.");
				response.getWriter().print(getHtml(errors, false, null) 
						+ fillFormWithJS(request));
				
				prepSelectStatement.close();
				selectResult.close();
				conn.close();
				return;
			}
			
			PreparedStatement prepInsertStatement = conn.prepareStatement(
					"INSERT INTO `USERS" +
					"`(`firstname`, `lastname`, `email`, `phone`, `year`, `major`, `username`, `password`)" + 
							"VALUES (?,?,?,?,?,?,?,?)");
			prepInsertStatement.setString(1, FIRST_NAME);
			prepInsertStatement.setString(2, LAST_NAME);
			prepInsertStatement.setString(3, EMAIL);
			prepInsertStatement.setString(4, PHONE);
			prepInsertStatement.setString(5, YEAR);
			prepInsertStatement.setString(6, MAJOR);
			prepInsertStatement.setString(7, USERNAME);
			prepInsertStatement.setString(8, PASSWORD);
			

			System.out.println(prepInsertStatement.toString());
			
			if(prepInsertStatement.executeUpdate() == 0) {
				errors.setMain("Your registration didn't make it to the database.");
				response.getWriter().print(getHtml(errors, false, null) 
						+ fillFormWithJS(request));
				prepInsertStatement.close();
				prepSelectStatement.close();
				selectResult.close();
				conn.close();
				return;
			}
			
			prepInsertStatement.close();
			prepSelectStatement.close();
			selectResult.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} 
			response.getWriter().print(getHtml(errors, true, FIRST_NAME));
	}
	
	private FormError formValidation(HttpServletRequest request) {
		
		FormError errors = new FormError();
		final String FIRST_NAME = request.getParameter("firstName").trim();
		final String LAST_NAME = request.getParameter("lastName").trim();
		final String EMAIL = request.getParameter("email").trim().toLowerCase();
		final String CONFIRM_EMAIL = request.getParameter("confirmEmail").trim().toLowerCase();
		final String PHONE1 = request.getParameter("phone1").trim();
		final String PHONE2 = request.getParameter("phone2").trim();
		final String PHONE3 = request.getParameter("phone3").trim();
		final String YEAR = request.getParameter("year").trim();
		final String MAJOR = request.getParameter("major").trim();
		final String USERNAME = request.getParameter("username").trim().toLowerCase();
		final String PASSWORD = request.getParameter("password").trim();
		final String CONFIRM_PASSWORD = request.getParameter("confirmPassword").trim();
		
		errors.setFirstName(
				FIRST_NAME.isEmpty() ? "This field should not be empty. ":"");
		errors.setFirstName(
				FIRST_NAME.matches("^[\\p{L} .'-]+$") ? "" : "Invalid charators used. ");
		
		errors.setLastName(
				LAST_NAME.isEmpty() ? "This field should not be empty.":"");
		errors.setLastName(
				LAST_NAME.matches("^[\\p{L} .'-]+$") ? "" : "Invalid charators used. ");
		
		errors.setEmail(
				EMAIL.isEmpty() ? "This field should not be empty.":"");
		if(!EMAIL.matches(
				"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"))
			errors.setEmail("Invalid email address");
		
		if(!EMAIL.equals(CONFIRM_EMAIL))
			errors.setEmail("Email does not match Confirm Email.");
		
		if(PHONE1.isEmpty() || 
				PHONE2.isEmpty() ||
				PHONE3.isEmpty())
			errors.setPhone("All 3 fields must be filled");
		if(!tryParseInt(PHONE1) || 
				!tryParseInt(PHONE2) ||
				!tryParseInt(PHONE3))
			errors.setPhone("These fields only accept numbers.");
		if(PHONE1.length() != 3 || 
				PHONE2.length() != 3 ||
				PHONE3.length() != 4)
			errors.setPhone("Not the correct format (XXX)XXX-XXXX.");
		
		errors.setYear(
				YEAR.isEmpty() ? "Please make a selection." : "");
		
		errors.setMajor(
				MAJOR.isEmpty() ? "Please make a selection." : "");
		
		errors.setUsername(
				USERNAME.isEmpty() ? "This field cannot be blank." : "");

		errors.setPassword(
				PASSWORD.isEmpty() ? "This field connot be blank." : "");
		if(!PASSWORD.equals(CONFIRM_PASSWORD))
				errors.setPassword("Password and Confirm Password did not match.");
		
		return errors;
	}
	
	private boolean tryParseInt(String value){
		try{
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	
	private String getHtml(
			FormError err, Boolean regComplete, String firstName) {
		String errorStart = "";
		String errorEnd ="";
		
		String html =
				"<!DOCTYPE html>\r\n"+
				"<html>\r\n"+
				"<!--\r\n\tCOMP3095 Web Application Development with Java\r\n\t"+
				"Assignment 1 - Servlets\r\n\t"+
				"Instructor : Sergio Santilli sergio.santilli@georgebrown.ca\r\n\t\r\n\t"+
				"by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca\r\n\t"+
				"Date: Monday, October 10, 2016\r\n\r\n\t"+
				"Description:\r\n\t\t"+
				"HTML file for registration page for an online journal\r\n-->\r\n"+
				"<head>\r\n\t<meta charset='UTF-8'>\r\n\t"+
				"<title>Registration | Journal</title>\r\n"+
				"\t<link rel=\'stylesheet\' type=\'text/css\' href=\'http://fonts.googleapis.com/css?family=Roboto\'>\n"+
				"\t<link rel=\'stylesheet\' type=\'text/css\' href=\'http://fonts.googleapis.com/css?family=Roboto+Slab\'>\n"+
				"\t<link rel=\'stylesheet\' type=\'text/css\' href=\'http://fonts.googleapis.com/css?family=Source+Code+Pro\'>\n"+
				"\t<style>\n"+
				"* {\n"+
				"\tmargin: 0;\n"+
				"\tpadding: 0;\n"+
				"}\n"+
				"body {\n"+
				"\twidth:85%;\n"+
				"\tmax-width:96em;\n"+
				"\tfont-size:62.5%;\n"+
				"\tbackground-color: #333333;\n"+
				"\tmargin: 2em auto 2em auto;\n"+
				"}\n"+
				"form{\n"+
				" margin-left: auto;\n"+
				" margin-right: auto;\n"+
				"}\n"+
				"fieldset{\n"+
				"\tborder-width: 1px;\n"+
				"\tborder-top-style: solid;\n"+
				"\tborder-color: white;\n"+
				"\tpadding: .5em;\n"+
				"}\n"+
				"legend, h2{\n"+
				"\ttext-align: center;\n"+
				"\tfont-family: \'Roboto Slab\', Times, serif;\n"+
				"\tfont-size: 36px;\n"+
				"\tcolor: white;\n"+
				"}\n"+
				"label, h3{\n"+
				"\ttext-align: right;\n"+
				"\tfont-family: \'Roboto Slab\', Times, serif;\n"+
				"\tfont-size: 24px;\n"+
				"\tcolor: #75a3d1;\n"+
				"}\n"+
				".error{\n"+
				"\tfont-family: \'Roboto Slab\', Times, serif;\n"+
				"\tfont-size: 24px;\n"+
				"\tcolor: #E05151;\n"+
				"}\n"+
				"input, select{\n"+
				"\tborder: solid;\n"+
				"\tborder-width: 1px;\n"+
				"\tborder-color: white;\n"+
				"\tbackground-color: #555555;\n"+
				"\tpadding: .25em;\n"+
				"\tmargin: .25em;\n"+
				"\tfont-family: \'Source Code Pro\', Courier, monospace;\n"+
				"\tfont-size: 20px;\n"+
				"\tcolor: #FFD452;\n"+
				"}\n"+
				"#phone1, #phone2{\n"+
				"\twidth: 3em;\n"+
				"}\n"+
				"#phone3{\n"+
				"\twidth: 4em;\n"+
				"}\n"+
				"#phoneformat{\n"+
				"\tfont-family: \'Source Code Pro\', Courier, monospace;\n"+
				"\tfont-size: 20px;\n"+
				"\tcolor: #FFD452;\n"+
				"}\n"+
				"\t</style>\n</head>";
		if(regComplete) {
			html += "<body>\r\n\t<section><h2>Congratulations, ";
			html += firstName;
			html += "!</h2>\r\n\t\t<h3>Your registration is now complete.</h3>\r\n\t" +
					"</section>\r\n</body></html>";
		} else {
			html += "<body>\r\n"+
					"\t<form method=\"post\"action=\"RegisterServlet\">\r\n"+ 
					"\t\t<fieldset id=\"personal\">\r\n"+ 
					"\t\t\t<legend>Personal Information</legend>\r\n";
			html += !err.isEmpty()? errorStart+ err.getMain() + errorEnd : "";
			html += "\t\t\t\t<label for=\"firstName\">First Name: </label>\r\n"+ 
					"\t\t\t\t\t<input id='firstname' type=\"text\"name=\"firstName\"/><br />\r\n\n";
			html += (err.getFirstName()!=null)? errorStart+ err.getFirstName() + errorEnd : "";
			html += "\t\t\t\t<label for=\"lastName\">Last Name: </label>\r\n"+ 
					"\t\t\t\t\t<input id='lastname' type=\"text\"name=\"lastName\"/><br />\r\n\n";
			html += (err.getLastName()!=null)? errorStart+ err.getLastName() + errorEnd : "";
			html += "\t\t\t\t<label for=\"email\">E-mail: </label>\r\n"+ 
					"\t\t\t\t\t<input id='email' type=\"email\"name=\"email\"><br />\r\n";
			html += (err.getEmail()!=null)? errorStart+ err.getEmail() + errorEnd : "";
			html += "\t\t\t\t<label for=\"confirmEmail\">Confirm E-mail:</label>\r\n"+ 
					"\t\t\t\t\t<input id='confirmEmail' type=\"email\"name=\"confirmEmail\"><br />\r\n\n"+ 
					"\t\t\t\t<label for=\"phone1\">Telephone: </label>\r\n"+ 
					"\t\t\t\t\t<span id='phoneformat'>(</span><input id='phone1' type=\"text\"name=\"phone1\"><span id='phoneformat'>)</span>\r\n"+ 
					"\t\t\t\t\t<input id='phone2' type=\"text\"name=\"phone2\"><span id='phoneformat'> - </span>\r\n"+ 
					"\t\t\t\t\t<input id='phone3' type=\"text\"name=\"phone3\"><br />\r\n\n";
			html += (err.getPhone()!=null)? errorStart+ err.getPhone() + errorEnd : "";
			html += "\t\t\t\t<label for=\"year\">Year: </label>\r\n"+ 
					"\t\t\t\t\t<select name='year'>\r\n"+ 
					"\t\t\t\t\t\t<option value =\"\">Select One...</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"1\">One</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"2\">Two</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"3\">Three</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"4\">Four</option>\r\n"+ 
					"\t\t\t\t\t</select><br />\r\n\n";
			html += (err.getYear()!=null)? errorStart+ err.getYear() + errorEnd : "";
			html +=	"\t\t\t\t<label for=\"major\">Major: </label>\r\n"+ 
					"\t\t\t\t\t<select name='major'>\r\n"+ 
					"\t\t\t\t\t\t<option value =\"\">Select One...</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"T127\">Computer Programmer Analyst Program (T127)</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"T141\">Computer Systems Technician Program (T141)</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"T147\">Computer Systems Technology Program (T147)</option>\r\n"+ 
					"\t\t\t\t\t\t<option value=\"T163\">Game Programming Program (T163)</option>\r\n"+ 
					"\t\t\t\t\t</select><br />\r\n";
			html += (err.getMajor()!=null)? errorStart+ err.getMajor() + errorEnd : "";
			html += "\t\t</fieldset>\r\n\n"+ 
					"\t\t<fieldset id=\"userfield\">\r\n"+
					"\t\t\t<legend>Username</legend>\r\n"+ 
					"\t\t\t\t<label for=\"username\">Username: </label>\r\n"+ 
					"\t\t\t\t\t<input id='username' type=\"text\"name=\"username\"/><br />\r\n\n"; 
			html += (err.getUsername()!=null)? errorStart+ err.getUsername() + errorEnd : "";
			html += "\t\t\t\t<label for=\"password\">Password: </label>\r\n"+ 
					"\t\t\t\t\t<input type=\"password\"name=\"password\"/><br />\r\n";
			html += "\t\t\t\t<label for=\"confirmPassword\">Confirm Password: </label>\r\n" + 
					"\t\t\t\t\t<input type=\"password\"name=\"confirmPassword\"/><br />\r\n";
			html += (err.getPassword()!=null)? errorStart+ err.getPassword() + errorEnd : "";
			html += "\t\t</fieldset>\r\n\n"+ 
					"\t\t<fieldset id=\"register\">\r\n"+ 
					"\t\t\t<legend>Register</legend>\r\n"+ 
					"\t\t\t\t<table id=\"form\">\r\n"+ 
					"\t\t\t\t</table>\r\n"+ 
					"\t\t\t\t<input type=\"submit\"value=\"Register\">\r\n"+ 
					"\t\t</fieldset>\r\n"+ 
					"\t</form>\r\n"+ 
					"</body>\r\n"+ 
					"</html>";
		}
		return html;
	}
	private String fillFormWithJS(HttpServletRequest request) {
		
		return "\n<script>"
				+ "document.getElementById('firstname').value = '" + 
					request.getParameter("firstName").trim() + "';\n" +
				"document.getElementById('lastname').value = '" + 
					request.getParameter("lastName").trim() + "';\n" + 
				"document.getElementById('email').value = '" + 
					request.getParameter("email").trim().toLowerCase() + "';\n" + 
				"document.getElementById('confirmEmail').value = '" + 
					request.getParameter("confirmEmail").trim().toLowerCase() + "';\n" + 
				"document.getElementById('phone1').value = '" + 
					request.getParameter("phone1").trim() + "';\n" + 
				"document.getElementById('phone2').value = '" + 
					request.getParameter("phone2").trim() + "';\n" + 
				"document.getElementById('phone3').value = '" + 
					request.getParameter("phone3").trim() + "';\n" +
				"document.getElementById('username').value = '" + 
					request.getParameter("username").trim().toLowerCase() + 
				"';\n</script>";
	}
}
