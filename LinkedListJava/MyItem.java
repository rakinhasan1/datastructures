import java.util.*;
public class MyItem implements IDedObject{
	private int itemID;
	private int itemPrice;
	private List<Integer> itemDescription;
	
	
	public MyItem(int id, int price, List<Integer> des) {
		itemID=id;
		itemPrice=price;
		itemDescription=new LinkedList<Integer>();        
		for(int n:des)                 //this makes sure itemDescription copies the values of des
			itemDescription.add(n);     //instead of just aliasing its address to des
	}
	public int getID() {
		return itemID;
	}
	public String printID() {    //prints ID, price, and description of item
		String str=itemID+" ";
		str+= itemPrice+" ";
		for(int n:itemDescription)
			str+=n+" ";
		return str;
	}
}
