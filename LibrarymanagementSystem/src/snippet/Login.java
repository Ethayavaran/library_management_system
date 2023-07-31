package snippet;
import java.util.*;
public class Login {

	    public static void main(String[] args) {
	        Scanner sc = new Scanner (System.in);
	        System.out.println("Welcome to the Library Management System!");
	        System.out.println("Please log in.");
	        System.out.print("Username: ");
	        String username = sc.nextLine();
	        System.out.print("Password: ");
	        String password = sc.nextLine();
	        if (username.equals("admin") && password.equals("password")) {
	            System.out.println("Login successful.");
	        } 
	        else {
	            System.out.println("Invalid username or password. Please try again.");
	        }
	    }
	}
