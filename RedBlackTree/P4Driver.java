/**
 * 
 * @author Rakin Hasan
 * This is a driver class for RedBlackTree
 */
import java.io.*;
import java.util.*;
public class P4Driver {

	public static void main(String[] args) {
		Scanner in;
		if(args.length!=2) {
			System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
	        System.exit(0);
		}
		try {
			 RedBlackTree tree;
			 File input_file = new File(args[0]);
	            in = new Scanner(input_file);
	         File output_file = new File(args[1]); 
	           PrintWriter  out;
	        out = new PrintWriter(output_file);
	        
	        String type=in.next();
	        in.nextLine();
	        if(type.equals("Integer")) {
	        	tree=new RedBlackTree<Integer>();
		        while(in.hasNext()) {
		        	String operation=in.nextLine().trim();
		        	if(operation.contains(":")) {    //: implies insertion,deletion, or contains
		        		String num=operation.substring(operation.indexOf(":")+1); //isolate operation
		        		operation=operation.substring(0,operation.indexOf(":"));  //isolate the number
		        		switch(operation) {
		        		
		        		case "Insert":   //inserts number into tree
		        			try {
		        				int x=Integer.parseInt(num);   //make sure the number part is valid
		        				try {
		        					out.println((tree.insert(x))?"True":"False");
		        				}
		        				catch(Exception e) { //make sure number is not null
		        					out.println("Error in insert: "+e.getMessage());
		        				}
		        			}
		        			catch(Exception e) { //if the part after the insert isn't a number
		        				   out.println("Invalid expression for insert: "+num); 
		        			   }
		        		
		        		break;
		        		case "Contains":  //checks if number is in tree
		        			try {
		        				int x=Integer.parseInt(num);
		        					out.println((tree.contains(x))?"True":"False");
		        			}
		        			catch(Exception e) {
		        				   out.println("Invalid expression for contains: "+num);
		        			   }
		        		
		        		break;
		        		default:
		        			out.println("Error in Line: "+ operation);
		        		
		        		
		        		
		        		
		        		}
		        	}
		        	else {   //all other operations
		        		switch(operation) {
		        		case "PrintTree":  //toString of tree
		        			out.println(tree.toString());
		        			break;
		        		default:     //all other lines will be an error
		        			out.println("Error in Line: "+ operation);
		        		}
		        }
	        }
	        }
	        else if(type.equals("String")){
	        	tree=new RedBlackTree<String>();
		        while(in.hasNext()) {
		        	String operation=in.nextLine().trim();
		        	if(operation.contains(":")) {    //: implies insertion,deletion, or contains
		        		String str=operation.substring(operation.indexOf(":")+1); //isolate operation
		        		operation=operation.substring(0,operation.indexOf(":"));  //isolate the number
		        		switch(operation) {
		        		
		        		case "Insert":   //inserts string into tree
		        			try {
		        			out.println(tree.insert(str)?"True":"False");
		        			}
		        			catch(Exception e) { //make sure number is not null
	        					out.println("Error in insert: "+e.getMessage());
	        				}
		        		break;
		        		case "Contains":  //checks if number is in tree
		        					out.println((tree.contains(str))?"True":"False");
		        		
		        		break;
		        		default:
		        			out.println("Error in Line: "+ operation);
		        		
		        		
		        		
		        		
		        		}
		        	}
		        	else {   //all other operations
		        		switch(operation) {
		        		case "PrintTree":  //toString of tree
		        			out.println(tree.toString());
		        			break;
		        		default:     //all other lines will be an error
		        			out.println("Error in Line: "+ operation);
		        		}
		        }
	        }
	        }
	        else {
	        	out.println("Only works for objects Integers and Strings");
	        }
	        
	        
	        in.close();
	        out.close();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		/*RedBlackTree tree=new RedBlackTree<Integer>();
		tree.insert(98);
		tree.insert(-68);
		tree.insert(55);
		tree.insert(45);
		tree.insert(84);
		tree.insert(32);
		tree.insert(132);
		tree.insert(45);
		System.out.println(tree);*/
		/*RedBlackTree tree=new RedBlackTree<String>();
		tree.insert("Ana");
		tree.insert("Owen");
		tree.insert("Pete");
		tree.insert("Leo");
		tree.insert("Nick");
		tree.insert("Maya");
		tree.insert("Leo");
		System.out.println(tree);
		System.out.println(tree.contains("Ana"));
		System.out.println(tree.contains("Owen"));
		System.out.println(tree.contains("Varad"));*/
		
	}

}
