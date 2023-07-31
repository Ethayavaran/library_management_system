package snippet;


	import java.util.*;
	public class read{
	    Node head,tail;
	    class Node{
	        int data;
	        int data2;
	        Node next;
	        Node(int val,int val2){
	            data=val;
	            data2=val2;
	            next=null;
	        }
	    }
	    public void add(int val,int val2){
	        Node newnode=new Node(val,val2);
	        if(head==null){
	            head=newnode;
	            tail=newnode;
	        }
	        else{
	            tail.next=newnode;
	            tail=newnode;
	        }
	    }
	    public void dis(int n,int m){
	        Node temp=head;
	        while(temp!=null){
	        if(temp.data==0&&temp.data2==0){
	            return;
	        }
	        else{
	            if(temp.data>n&&temp.data2<m){
	                System.out.print(temp.data+" "+temp.data2+" ");
	            }
	        }
	        temp=temp.next;
	        }
	    }
	    public static void main(String args[]){
	        Scanner s=new Scanner(System.in);
	        read list=new read();
	        int n=0,m=0;
	        while(s.hasNext()){
	        n=s.nextInt();
	        m=s.nextInt();
	        list.add(n,m);
	        }
	        list.dis(n,m);
	        
	    }
}
