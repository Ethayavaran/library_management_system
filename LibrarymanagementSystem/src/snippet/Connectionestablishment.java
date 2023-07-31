package snippet;
	import java.sql.*;

	import java.util.Scanner;
	public class Connectionestablishment {
			Connection con=null;
			 int uname;
			 String pswd;
			public void createTable() throws Exception{
				String query="CREATE TABLE if not exists Library (Name VARCHAR(20), id int,roll_no VARCHAR (20))";
				Statement stmt=con.createStatement();
				stmt.execute(query);
			}
			public void insertValues() throws Exception{
				String query1="INSERT INTO Library VALUES('branesh',4,'20cs020')";
				Statement stmt=con.createStatement();
				stmt.executeUpdate(query1);
			}
			
			public void updateValues() throws Exception
			{
				String query2="UPDATE Library Set Name='tom' WHERE Name='branesh'";
				Statement stmt=con.createStatement();
				stmt.executeUpdate(query2);
			}
			public void print() throws Exception{
				String query="SELECT * FROM Library";
				Statement stmt=con.createStatement();
				ResultSet r=stmt.executeQuery(query);
				while(r.next()) {
					System.out.println(r.getInt(1)+" "+r.getString(2));
				}
			}
			public void getConnect() throws Exception{
					//Connection_Establishment.forName("com.mysql.cj.jdbc.Driver");
					con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","root");
			}	
			public static void main(String[] args) throws Exception{
				Connectionestablishment ob=new Connectionestablishment();
				ob.getConnect();
				ob.createTable();
				Scanner sc=new Scanner(System.in);
				System.out.print("Enter the id:");
				int n=sc.nextInt();
				sc.nextLine();
				System.out.print("Enter the Name:");
				String str=sc.nextLine();
				System.out.print("Enter the roll no:");
				String str1=sc.nextLine();
				ob.insertValues();
				//ob.updateValues();
				ob.print();	
			}
			}
	
	
	
