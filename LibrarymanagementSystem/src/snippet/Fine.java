package snippet;
	import java.util.*;
	import java.time.LocalDate;
	import java.time.temporal.ChronoUnit;
	public class Fine{
		static void SubmitBook(String requiredReturnDate,String returnDate)
		{
//			Scanner sc=new Scanner(System.in);
//			System.out.println("Enter the Book name");
//			String book=sc.next();
//			System.out.println("Enter curent date");
//			String date=sc.next();
			  LocalDate date1 = LocalDate.parse(requiredReturnDate);
		        LocalDate date2 = LocalDate.parse(returnDate);

		        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
		        if(daysBetween<=0) {
		        	System.out.println();
		        	System.out.println("No Fine allocated");
		        	System.out.println();
		        }
//		        System.out.println("Days between " + date1 + " and " + date2 + " = " + daysBetween);
//			String rdate=returndate.substring(returndate.length()-2);
//			String cdate=date.substring(date.length()-2);
//			int fine=Integer.parseInt(cdate)-Integer.parseInt(rdate);
		        else {
			System.out.println("Fine amount is " +daysBetween);
		       }
		}
		        static void SubmitBook(String requiredReturnDate)
				{
					Scanner sc=new Scanner(System.in);
					System.out.println("Enter the Book name");
					String book=sc.next();
					System.out.println("Enter curent date");
					String date=sc.next();
					  LocalDate date1 = LocalDate.parse(requiredReturnDate);
				        LocalDate date2 = LocalDate.parse(date);

				        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
				        if(daysBetween<=0) {
				        	System.out.println();
				        	System.out.println("No Fine allocated");
				        	System.out.println();
				        }
//				        System.out.println("Days between " + date1 + " and " + date2 + " = " + daysBetween);
//					String rdate=returndate.substring(returndate.length()-2);
//					String cdate=date.substring(date.length()-2);
//					int fine=Integer.parseInt(cdate)-Integer.parseInt(rdate);
				        else {
					System.out.println("Fine amount is " +daysBetween);
					System.out.println();
				        }
		}
	}