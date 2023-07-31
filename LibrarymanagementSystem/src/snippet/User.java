package snippet;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;
public class User {
	String username;
	int userid;
	String useremail;
	String userno;
	String password;
	static Scanner sc=new Scanner(System.in); 
	static Connection con = null;
public  User (String user,int id,String mail,String userno,String pass){
	 this.username=user;
	 this.userid=id;
	 this.useremail=mail;
	 this.userno=userno;
	 this.password=pass;
}
	public User(String user) {
		this.username=user;
		try {
			getConnect();
			Statement stmt=con.createStatement();
			String query="select userid from user where username=\'"+user+"\'";
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			userid=rs.getInt("userid");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static void getConnect() throws Exception{
	con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
}	

	public  void userSignUp(String user,int id,String mail,String userno,String pass) throws Exception {
		getConnect();
		String query="insert into user values('"+username+"','"+userid+"','"+useremail+"','"+userno+"','"+password+"')";
		Statement  st=con.createStatement();
		int rs=st.executeUpdate(query);
	}
	
	boolean checkValidBook(String title) {
		Statement stmt;
		
		try {
			stmt = con.createStatement();
			String query="select * from lend_details where userid="+userid+" and title=\'"+title+"\' and returndate is null";
			ResultSet rs=stmt.executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void returnBook(){
		String title;
	   	 System.out.println("Enter book title that you need to return:");
	   	 title=sc.next();
	   	 if(checkValidBook(title)) {
	   		 Statement stmt;
			try {
				stmt = con.createStatement();
	   		 String query="select bordate from lend_details where userid="+userid+" and title=\'"+title+"\'";
	   		 ResultSet rs=stmt.executeQuery(query);
	   		 rs.next();
	   		 LocalDate borrowDate =Instant.ofEpochMilli(rs.getDate("bordate").getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			 LocalDate requiredReturnDate=LocalDate.now().plusDays(7);
			//LocalDate requiredReturnDate=LocalDate.now().plusDays(7);
			 System.out.println("Required Return Date: " +requiredReturnDate);
			 System.out.println();
			 System.out.println("Enter Userreturn date:");
			 LocalDate returnDate=LocalDate.parse(sc.next());
			 String curDate=String.valueOf(borrowDate);
			 System.out.println("Return Date: " +requiredReturnDate);
			 System.out.println();
			 query="update lend_details set returndate=\'"+String.valueOf(returnDate)+"\'";
			 stmt.executeUpdate(query);
			 String retDate=String.valueOf(requiredReturnDate);
			 new Fine().SubmitBook(retDate,String.valueOf(returnDate));
			 stmt=con.createStatement();
			 query="update books set available_count=available_count+1 where title='"+title+"'";
			 stmt.executeUpdate(query);
	   	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   	}
		
	   	 }
	   	 else {
	   		 System.out.println("Invalid book title");
	   	 }
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	boolean isBookAvailable(String title) {
		try {
			Statement stmt=con.createStatement();
			String query="select available_count from books where title=\'"+title+"\'";
			ResultSet rs=stmt.executeQuery(query);
			if(!rs.next())
				return false;
			int availableCount=rs.getInt(1);
			if(availableCount==0)
				return false;
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	void insertLendDetail(String title,String borDate) {
		try {
			Statement stmt=con.createStatement();
			//System.out.println(userid+" "+title+" "+borDate);
			String query="insert into lend_details(userid,title,bordate) values("+userid+",\'"+title+"\',\'"+borDate+"\')";
			stmt.executeUpdate(query);
			query="update books set available_count=available_count-1 where title='"+title+"'";
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void reserveBook() {
		System.out.println("Choose Books");
		 new PrintTable().printResult("books");
		 System.out.println();
		 sc.nextLine();
		 System.out.println("Enter book name:");
		 
		 String titles=sc.nextLine();
		 LocalDate currentDate = LocalDate.now();
		 LocalDate returnDate=currentDate.plusDays(7);
		 System.out.println("Borrow Date: " +currentDate);
		 System.out.println();
		 if(isBookAvailable(titles)) {
			 insertLendDetail(titles,String.valueOf(returnDate));
			 System.out.println("Lend details are successfully added");
		 }
		 else {
			 System.out.println("Book is not available.");
		 }
	}
	public void operation() throws Exception {
		 while(true) {
			 System.out.println("1.Reserve book");
			 System.out.println("2.Return book");
			 System.out.println("3.back");
			 int choice=sc.nextInt();
			 if(choice==1) {
				 reserveBook();
			 }
			 else if(choice==2) {
				 returnBook();
				 System.out.println("THANK YOU FOR VISITING LIBRARY!");
				 break;
			 }
			 else if(choice==3) {
				 return;
			 }
			 else {
				 System.out.println("Invalid option");
			 }
		 }
//   		 do {
//			 Library.displayBooks();
//			 System.out.println();
//			 System.out.println("Choose Books");
//			 // new PrintTable().printResult("books");
//			 System.out.println();
//			 System.out.println("Enter book name:");
//			 String su=sc.nextLine();
//			 System.out.println("Enter id:");
//			 int sm=sc.nextInt();
//			 sc.nextLine();
//			 LocalDate currentDate = LocalDate.now();
//			 LocalDate returndate=currentDate.plusDays(7);
//			 System.out.println("Borrow Date: " +currentDate);
//			 System.out.println();
//			 String gh=String.valueOf(currentDate);
//			 System.out.println("Return Date: " +returndate);
//			 System.out.println();
//			 String jh=String.valueOf(returndate);
//			 borrow(sm,su,gh,jh);
//			 System.out.println();
//			 System.out.println();
//			 key = sc.nextInt();
//			 sc.nextLine();
//			 Fine.SubmitBook(jh);
//			 System.out.println();
//			 System.out.println("Do you want to Continue? [1/0]");
//   		 }
//   		 while(key!=1);
	}
}
