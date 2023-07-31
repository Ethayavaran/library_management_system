package snippet;
	/******************************************************************************

	Welcome to GDB Online.
	GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
	C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
	Code, Compile, Run and Debug online from anywhere in world.

	*******************************************************************************/
import java.util.*;
	public class Main
	{
		public static void main(String[] args) {
		    Scanner s = new Scanner (System.in);
			int n=s.nextInt();
			int arr []=new int [n];
			for(int i=0;i<arr.length;i++){
			    arr[i]=s.nextInt();
			}
			int c=0;
			Set<Integer> s1 = new LinkedHashSet <> ();
			for(int i=0;i<arr.length;i++){
			    s1.add(arr[i]);
			}
			int arr1[]=new int[s1.size()];
			int arr2[]=new int[s1.size()];
			int h=0;
			for(Integer se:s1){
			     c=0;
			   for(int i=0;i<arr.length;i++){
			       if(se==arr[i]){
			           c++;
			       }
			   }
			   arr1[h]=c;
			   arr2[h]=c;
			   h++;
			   
			}
			Arrays.sort(arr2);
			int ans1=0;
			int ans=arr2[arr2.length-2];
			for(int i=0;i<arr1.length;i++){
			    if(arr1[i]==ans){
			        ans1=i;
			    }
			}
			for(int i=0;i<arr.length;i++){
			    if(i==ans1){
			        System.out.print(arr[i]);
			    }
			}
			
		}
	}


