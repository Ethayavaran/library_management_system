package snippet;
import java.sql.*;
import java.io.PrintStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import com.mysql.cj.protocol.Resultset;
public class  Library {
						static Connection con = null;
						public void  addBook (String title, String author, String genre,String date,int tct,int act){
						try {
						con = getConnect();
//						System.out.println(title+" "+author+" "+genre+" "+date);
						String query="insert into books values('"+title+"','"+author+"','"+genre+"','"+date+"',"+tct+","+act+");";
//						System.out.println(query);
						Statement  st=con.createStatement();
						int rs=st.executeUpdate(query);
						if(rs==0)
							System.out.print("Error");
						else
						 System.out.println("Book added successfully.");
						}
						catch(Exception e)
						{
							System.out.println(e);
						}
	    }
	    public static Connection getConnect() throws Exception{
			con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			return con;
	}	 
	    public static void removeBook(String title) throws Exception {
	    	con = getConnect();
	    	String query="DELETE FROM books WHERE title='"+title+"'";
	    	Statement  st=con.createStatement();
	    	int rs=st.executeUpdate(query);
	    	if(rs==0)
	    		System.out.print("Error");
	    	else
	        System.out.println("Book deleted successfully.");
	    }
	    public static void borrow (int id,String title,String CurrentDate,String returndate) throws Exception {
			con = getConnect();
			String query="INSERT INTO lend_details values ('"+id+"','"+title+"','"+CurrentDate+"','"+returndate+"');";
			Statement  st=con.createStatement();
			int rs=st.executeUpdate(query);
			if(rs>0)
				System.out.println("book borrowed sucessfully");
			else
				System.out.println("Error");
			System.out.println();
		}
	    public static void displayBooks() throws Exception {
	    	con = getConnect();
//	    	String query="select * from books";
//	        Statement st=con.createStatement(); 
//	       ResultSet rs=st.executeQuery(query);
//	       while(rs.next()) {
//	    	   System.out.println(rs.getString(1) +"\t" +rs.getString(2) +"\t" +rs.getString(3) +"\t" +rs.getString(4));
//	       }
	       new PrintTable().printResult("books");
	    }
	    public  static boolean check (String username,String password) throws Exception{
	    	con = getConnect();
	    	String query="SELECT Username,password FROM admin WHERE Username ='"+username+"' AND password ='"+password+"';";
	    	Statement st=con.createStatement();
	    	ResultSet rs=st.executeQuery(query);
	    	if(rs.next()) {
	    		return true;
	    	}
	    	else
	    		return false;
	    }
	    public  static boolean check1 (String username,String password) throws Exception{
	    	con = getConnect();
	    	String query="SELECT * FROM user WHERE username ='"+username+"' AND password ='"+password+"';";
	    	Statement st=con.createStatement();
	    	ResultSet rs=st.executeQuery(query);
	    	if(rs.next()) {
	    		return true;
	    	}
	    	else 
	    		return false;
	    }
	    public static void main(String[] args) throws Exception {
	        Library lms = new Library();
	        Scanner sc = new Scanner(System.in);
	        String input;
	        boolean loggedIn = false;
	        System.out.println("1.Admin  2.User");
	        int ch=sc.nextInt();
	        sc.nextLine();
	        if(ch==1) {
	        	int f=0;
	        while (!loggedIn) {
	        	if(f==0) {
	        	System.out.println("******************************************");
	        	System.out.println();
	            System.out.println("Welcome to the Library Management System!");
	            System.out.println();
	            System.out.println("******************************************");
	            System.out.println();
	            f=1;
	        	}
	        	System.out.println();
	            System.out.print("Admin name: ");
	            String username = sc.nextLine();
	            System.out.print("Password: ");
	            String password = sc.nextLine();
	            if (check(username,password)) {
	            	System.out.println();
	                System.out.println("Login successful.");
	                System.out.println();
	                loggedIn = true;
	            } else {
	                System.out.println("Invalid username or password. Please try again.");
	                
	            }
	        }
	        while (true) {
	            System.out.println("Select an option:");
	            System.out.println();
	            System.out.println("1. Add book"  );
	            System.out.println("2. Remove book");
	            System.out.println("3. Display all books");
	            System.out.println("4. Exit");
	            input = sc.nextLine();
	            
	            switch (input) {
	                case "1":
	                    System.out.println("Enter title: ");
	                    String title = sc.nextLine();
	                    System.out.println("Enter author: ");
	                    String author = sc.nextLine();
	                    System.out.println("Enter genre: ");
	                    String genre = sc.nextLine();
	                    System.out.println("Enter BookPublishing Date: ");
	                    String date=sc.nextLine();
	                    System.out.println("Enter total Count: ");
	                    int tct=sc.nextInt();
	                    System.out.println("Enter Available Count: ");
	                    int act=sc.nextInt();
	                    lms.addBook(title, author, genre,date,tct,act);
	                    break;
	                case "2":
	                    System.out.print("Enter title: ");
	                    String removeTitle = sc.nextLine();
	                    lms.removeBook(removeTitle);
	                    break;
	                case "3":
	                    displayBooks();
	                    break;
	                case "4":
	                    System.exit(0);  
	                default:
	                    System.out.println("Invalid input.");
	            }
	        }
	    }
	        else {
	        	int temp=0;
	        do {
	        	System.out.println("1.Login");
	        	System.out.println("2.Signup");
	        	System.out.println("3.Back");
	        	int log=sc.nextInt();
	        	sc.nextLine();
	        	if(log==1) {
		       	 System.out.println("User name");
		       	 String sq=sc.nextLine();
		       	 System.out.println("password");
		       	 String k=sc.nextLine();
		       	 if(check1(sq,k)){
		       		 temp=1;
		       	 }
		       	 else {
		       		 System.out.println("invalid password");
		       		 continue;
		       	 }
	       	 //else temp = 0;
			       	 if(temp==1) {
				       		 System.out.println("Logged in");
				       		System.out.println("******************************************");
				        	System.out.println();
				            System.out.println("Welcome to the Library Management System!");
				            System.out.println();
				            System.out.println("******************************************");
				            System.out.println();
				            new User(sq).operation();
				       		 //System.out.println("Enter 1 to view books");
				       	 }
			       	
		        	}
	        	else if (log==2) {
					        		System.out.println("Enter Username");
					        		String user=sc.nextLine();
					        		System.out.println("User id");
					        		int use=sc.nextInt();
					        		sc.nextLine();
					        		System.out.println("User email");
					        		String email=sc.nextLine();
					        		System.out.println("Userno");
					        		String uno=sc.nextLine();
					        		System.out.println("Password");
					        		String passw=sc.nextLine();
					        		System.out.println("Signup Sucessfully");
					        		System.out.println("******************************************");
						        	System.out.println();
						            System.out.println("Welcome to the Library Management System!");
						            System.out.println();
						            System.out.println("******************************************");
						            System.out.println();
					        		User sign=new User(user,use,email,uno,passw);
					        		sign.userSignUp(user,use,email,user,passw);
					        		
					        		sign.operation();
	        	}
	        	else {
	        		main(new String[1]);
	        		return;
	        	}
	        	int key = 0;
	   		 
//	      		 do {
//	   			 	  Library.displayBooks();
//	   			 	  System.out.println();
//	   			    	 System.out.println("Choose Books");
//	   			    	// new PrintTable().printResult("books");
//	   			    	 System.out.println();
//	   			    	 System.out.println("Enter book name:");
//	   				 String su=sc.nextLine();
//	   			    	 System.out.println("Enter id:");
//	   			    	 int sm=sc.nextInt();
//	   			    	 sc.nextLine();
//	   			    	 LocalDate currentDate = LocalDate.now();
//	   			    	 LocalDate returndate=currentDate.plusDays(7);
//	   			    	 System.out.println("Borrow Date: " +currentDate);
//	   			    	 System.out.println();
//	   			    	 String gh=String.valueOf(currentDate);
//	   			    	 System.out.println("Return Date: " +returndate);
//	   			 System.out.println();
//	   			 String jh=String.valueOf(returndate);
//	   			 borrow(sm,su,gh,jh);
//	   			 System.out.println();
//	   			 System.out.println();
//	   			 key = sc.nextInt();
//	   			 sc.nextLine();
//	   			 new Fine().SubmitBook(jh);
//	   			 System.out.println();
//	   			 System.out.println("Do you want to Continue? [1/0]");
//	      		 }
//	      		 while(key!=1);
	       }
	       while(temp!=1);
	    }
}
}


	