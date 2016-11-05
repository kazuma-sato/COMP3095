package servlet.register;

//Object for validation errors and output logic
public class FormError {
	
	//Properties
	private String main;
	private String firstName; 
	private String lastName;
	private String email;
	private String phone;
	private String year;
	private String major;
	private String username;
	private String password;
	
	//Accessors: if null will return null, else will return error message wrapped in a span
	public String getMain() {
		
		if(!isEmpty()) {
			String output;
			output = "<span class='error'>Please try again.<br>";
			output += (main == null)? "" : main+ "<br>";
			return output + "</span>";
		} else
			return null;
	}
	public String getFirstName() {
		return (firstName != null)?"<span class='error'>" + firstName + "</span>":null;
	}
	public String getLastName() {
		return (lastName != null)?"<span class='error'>" + lastName + "</span>":null;
	}
	public String getEmail() {
		return (email != null)?"<span class='error'>" + email + "</span>":null;
	}
	public String getPhone() {
		return (phone != null)?"<span class='error'>" + phone + "</span>":null;
	}
	public String getYear() {
		return (year != null)?"<span class='error'>" + year + "</span>":null;
	}
	public String getMajor() {
		return (major != null)?"<span class='error'>" + major + "</span>":null;
	}
	public String getUsername() {
		return (username != null)?"<span class='error'>" + username + "</span>":null;
	}
	public String getPassword() {
		return (password != null)?"<span class='error'>" + password + "</span>":null;
	}
	
	//Mutators all concats a br tag to the end. If property already has a value, appends to the value.
	public void setMain(String main) {
		if(main != "")
			this.main = (this.main != null) ? this.main+main+"<br>" : main+"<br>";
	}
	public void setFirstName(String firstName) {
		if(firstName != "")
			this.firstName = (this.firstName != null) ? this.firstName+firstName+"<br>" : firstName+"<br>";
	}
	public void setLastName(String lastName) {
		if(lastName != "")
			this.lastName = (this.lastName != null) ? this.lastName+lastName+"<br>" : lastName+"<br>";
	}
	public void setEmail(String email) {
		if(email != "")
			this.email = (this.email != null) ? this.email+email+"<br>" : email+"<br>";
	}
	public void setPhone(String phone) {
		if(phone != "")
			this.phone = (this.phone != null) ? this.phone+phone+"<br>": phone+"<br>";
	}
	public void setYear(String year) {
		if(year != "") 
			this.year = (this.year != null) ? this.year+year+"<br>" : year+"<br>";
	}
	public void setMajor(String major) {
		if(major != "")
			this.major = (this.major != null) ? this.major+major+"<br>" : major+"<br>";
	}
	public void setUsername(String username) {
		if(username != "")
			this.username = (this.username != null) ? this.username+"<br>"+username+"<br>" : username+"<br>";
	}
	public void setPassword(String password) {
		if(password != "")
			this.password = (this.password != null) ? this.password+"<br>"+password+"<br>" : password+"<br>";
	}
	
	//Checks if all the properties are empty.
	public boolean isEmpty() {
		
		return ((main == null || main == "") &&
				(firstName == null || firstName == "") &&
				(lastName == null || lastName == "") &&
				(email == null || email == "") &&
				(phone == null || phone== "") &&
				(year == null || year == "") &&
				(major == null || major == "") &&
				(username == null || username == "") &&
				(password == null || password == ""));
	}

}