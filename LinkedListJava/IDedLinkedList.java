//linked list class that uses generic type that implements IDedObject
public class IDedLinkedList <Type extends IDedObject>{
	private Node<Type> head;     //front on linked list
	public IDedLinkedList() {
		head=null;             //empty linked list
	}
	
	public Type findID(int ID) {
		Node<Type> n=head;    //temp value to point at head and traverse the list
		while(n!=null) {
			if(n.data.getID()==ID)     //return value when found
				return n.data;
		n=n.next;
		}
        return null;         //otherwise return null
	}
	public boolean insertAtFront(Type t) {
		if(findID(t.getID())!=null)     //make sure ID isn't already in the list
			return false;
		head=new Node<Type>(t,head);         
		return true;
	}
	
	
	public int printTotal() {
		if(head==null)
			return -1;
		Node<Type> n=head;
		int sum=0;            //sums all the IDs
		while(n!=null) {
			sum+=n.data.getID();
			n=n.next;
		}
		return sum;
	}
	
	public void makeEmpty() {
		head=null;
	}
	
	public Type deleteFromFront() {
		if(head==null)       //can't delete from empty list
			return null;
		Type t=head.data;    //keeps track of head's current data to return it
		head=head.next;
		return t;
	}
	
	public Type delete(int ID) {
		if(head.data.getID()==ID)      //if it's the head, just use deleteFromFront
			return deleteFromFront(); 
		Node<Type> n=head;
		while(n!=null&&n.next!=null) {       //traverse until you get the node before the deleted node
			if(n.next.data.getID()==ID) {
				Type t=n.next.data;
				n.next=n.next.next;      //changes the link to point to the node two nodes ahead
				return t;         //return the value if found
			}
			n=n.next;
		}
		return null;       //otherwise return null
	}
	//inner Node class
	class Node <Type extends IDedObject>{
		Type data;
		Node<Type> next;
		public Node(Type d, Node<Type> n) {
			data=d;
			next=n;
		}
	}
}
