package edu.neu;

import java.util.LinkedList;
import java.util.Queue;


// Print Height of the tree
// Print all node of the tree level by level
public class PrintTreeByLevel {

	static class Node {
		int data;
		Node left;
		Node right;
		
		public Node (int data) {
			this.data = data;
		}
	}
	public static void main (String args[]) {		

	       Node root= new Node(1);
	       root.left= new Node(2);
	       root.right= new Node(3);
	       root.left.left= new Node(4);
	       root.left.right= new Node(5);
	        
	       System.out.println("Tree Height: " + findHeight(root));;
	       
	       System.out.println("Level order traversal of binary tree is ");
	       printLevelOrder(root);
	       

	}
	static void printUsingQueue(Node root) {
	
		Queue<Node> q = new LinkedList<PrintTreeByLevel.Node>();
		q.add(root);
		
		while (!q.isEmpty()) {
			
			Node tempNode = q.poll();
			System.out.print(tempNode.data+ " ");
			
			if (tempNode.left!=null) {
				q.add(tempNode.left);
			}
			if (tempNode.right!=null) {
				q.add(tempNode.right);
			}
		}
		
		
	}
	
	
	 static void printLevelOrder(Node root)
	    {
	        int h = findHeight(root);
	        int i;
	        for (i=1; i<=h; i++){
	        	printGivenLevel(root, i);
	        }
	        	System.out.println();
	    }
	
	/* Print nodes at the given level */
    static void printGivenLevel (Node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }
	
	
	

	public static int findHeight(Node root) {
		
		if (root == null) {
			return 0;
		}
		else {
			int leftHeight = findHeight(root.left);
			int rightHeight = findHeight(root.right);
			
			if (leftHeight > rightHeight) {
				return (leftHeight+1);
			}
			else {
				return (rightHeight+1);
			}
		}
		
		
	}
	
}
