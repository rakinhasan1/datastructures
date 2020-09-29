
/**
 * 
 * 
 *
 * @author Rakin Hasan
 *This is a red black tree
 * 
 */
public class RedBlackTree <Type extends Comparable>{

	private static boolean RED=false;
	private static boolean BLACK=true;
	private Node<Type> root;
	
	
	/**
	 * Default constructor- makes an empty tree
	 */
	public RedBlackTree() {  //construct empty tree
		root=null;
	}
	
	/**
	 * insert- puts a new element in the tree
	 * @param t the data that will be inserted
	 * @return whether the value was sucessfully inserted
	 * @throws NullPointerException when trying to insert a null element
	 */
	public boolean insert(Type t) throws NullPointerException{
		if(t==null)
			throw new NullPointerException();
		if(root==null) {	//if tree is empty, insert new black node as root
			root=new Node(t);
			root.color=BLACK;
			return true;
		}
		
		else {
		Node temp=root;
		while(temp!=null) {  //traverse tree until empty space to insert element is found
			if(temp.t.compareTo(t)==0)	//cant insert if element is already in the tree
				return false;
			else if(t.compareTo(temp.t)>0) {
				if(temp.rightChild==null) { //inserting new Node to the right
					Node ins=new Node(t); //node to be inserted
					ins.parent=temp; 
					temp.rightChild=ins; 
					if(temp.color==BLACK) //there isn't two straight reds, so the tree is preserved
						return true;
					Node uncle=getUncle(ins); //get uncle
					if(uncle!=null&&uncle.color==RED) {  //if uncle is red
						temp.color=BLACK;
						uncle.color=BLACK;
						ins.parent.parent.color=RED; //make grandparent red, grandparent's children black
						if(root.color==RED)  
							root.color=BLACK;   //make sure the root is black
						return true;
					}
					
					else { //case when uncle is null or black
						Node grandParent=temp.parent;
						if(grandParent.rightChild==temp) {  //right, right case, only need one left rotation
							leftRotation(grandParent);
							grandParent.color=RED;
							grandParent.parent.color=BLACK;
						}
						else {							//left right case
							leftRotation(temp);      //left rotate the parent to make it a left left case
							rightRotation(grandParent); //only need a right rotation on grandparent
							grandParent.color=RED;     //adjust colors as needed
							grandParent.parent.color=BLACK;
						}
						return true;
						
					}
					
					
					
					
				}
				else
					temp=temp.rightChild;
			}
			
			else {
				if(temp.leftChild==null) { //works similar to the right child insertion case, some slight differences
					Node ins=new Node(t);
					ins.parent=temp;
					temp.leftChild=ins;
					if(temp.color==BLACK)
						return true;
					Node uncle=getUncle(ins);
					if(uncle!=null&&uncle.color==RED) {
						temp.color=BLACK;
						uncle.color=BLACK;
						ins.parent.parent.color=RED;
						if(root.color==RED)
							root.color=BLACK;
						return true;
					}
					
					else {
						Node grandParent=temp.parent;
						if(grandParent.leftChild==temp) {
							rightRotation(grandParent);
							grandParent.color=RED;
							grandParent.parent.color=BLACK;
						}
						else {
							rightRotation(temp);
							leftRotation(grandParent);
							grandParent.color=RED;
							grandParent.parent.color=BLACK;
						}
						return true;
						
					}
					
				}
				else
					temp=temp.leftChild;
			}
		}
		
		
		}
		
		
		return false;
	}
	/**
	 * contains-checks whether a certain element is in the tree
	 * @param t the element to be searched for
	 * @return whether the element was found
	 */
	public boolean contains(Type t) {
		if(t==null)      //tree cant have a null element
			return false;
		Node temp=root;
		while(temp!=null) {   //traverse tree until element is found or node is null
			if(t.compareTo(temp.t)==0)
				return true;
			else if (t.compareTo(temp.t)>0)
				temp=temp.rightChild;
			else
				temp=temp.leftChild;
		}
		
		return false;
	}
	
	private Node getUncle(Node n) {	//gets uncle of current node
		Node g=n.parent.parent;	//grandparent node
		if(g.leftChild==n.parent) //find child of gp that isn't the parent
		return g.rightChild;
		if(g.rightChild==n.parent)
		return g.leftChild;
		return null;
	}
	
	private void rightRotation(Node n) {  //makes n's leftchild take the place of n
		if(n==null)
			return;
		if(n.leftChild==null) //rotation not possible
			return;
		if(n==root) {
			Node tempLeft=n.leftChild;
			n.leftChild=tempLeft.rightChild;
			if(n.leftChild!=null)
			n.leftChild.parent=n;
			tempLeft.rightChild=n;
			n.parent=tempLeft;
			root=tempLeft;
			return;
		}
		Node tempLeft=n.leftChild;
		n.leftChild=tempLeft.rightChild;
		if(n.leftChild!=null)
		n.leftChild.parent=n;
		tempLeft.rightChild=n;
		if(n.parent.leftChild!=null&&n.parent.leftChild==n)
			n.parent.leftChild=tempLeft;
		else
			n.parent.rightChild=tempLeft;
		tempLeft.parent=n.parent;
		n.parent=tempLeft;
	}
	

	
	private void leftRotation(Node n) { //makes n's right child take the place of n
		if(n==null)
			return;
		if(n.rightChild==null) //rotation not possible
			return;
		if(n==root) {
			Node tempright=n.rightChild;
			n.rightChild=tempright.leftChild;
			if(n.rightChild!=null)
			n.rightChild.parent=n;
			tempright.leftChild=n;
			n.parent=tempright;
			root=tempright;
			return;
		}
		Node tempright=n.rightChild;
		n.rightChild=tempright.leftChild;
		if(n.rightChild!=null)
		n.rightChild.parent=n;
		tempright.leftChild=n;
		if(n.parent.leftChild!=null&&n.parent.leftChild==n)
			n.parent.leftChild=tempright;
		else
			n.parent.rightChild=tempright;
		tempright.parent=n.parent;
		n.parent=tempright;
	}
	
	
	
	/**
	 * toString- returns preorder traversal of the tree
	 * @return the string representation of the tree
	 */
	public String toString() {
		return toString(root);
	}
	private String toString(Node n) {
		if(n==null)
			return "";
		if(n.color==BLACK) //black node case
		return n.t+" "+toString(n.leftChild)+toString(n.rightChild);
		else //red node case
		return "*"+n.t+" "+toString(n.leftChild)+toString(n.rightChild);  
	}
	
	class Node<Type extends Comparable>{  //Node class
		Type t;
		Node<Type> leftChild;
		Node<Type> rightChild;
		Node<Type> parent;
		boolean color;
		
		public Node(Type e) {
			t=e;
			leftChild=null;
			rightChild=null;
			parent=null;
		}
	}
}
