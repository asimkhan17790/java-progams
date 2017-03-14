package edu.neu.rbTree;

import java.util.Scanner;

public class RedBlackTree {

	private static final String RED = "RED";
	private static final String BLACK = "BLACK";
	private static Node nilNode = new Node(null,null,-999,BLACK,null); 
	private Node root;
	static class Node {
		Node left;
		Node right;
		Node parent;
		int key;
		String color;
		
		
		public Node (Node left, Node right, int key, String color,Node parent) {
			this.left = left;
			this.right = right;
			this.key = key;
			this.color = color;
			this.parent = parent;
					
		}
		@Override
		public String toString () {
				return "(" + (this.left!=null?this.left.key:this.left) + "<- " 
						+ this.key + " ->" 
						+ (this.right!=null?this.right.key:this.right) + ","
						+ this.color+")"; 					
		}		
		
	}
	private boolean isNilNode (Node n) {
		if (n.key == nilNode.key
				&& n.left == null
				&& n.right == null
				&& n.color == nilNode.color) {
			return true;
			
		}
		return false;
	}
	
	public void inOrderTraversal (Node x) {
		if (x!=null && !isNilNode(x)) {
			inOrderTraversal (x.left);
			System.out.println(x);
			inOrderTraversal (x.right);
		}
	}
	
	// Searching in a tree
	public Node treeSearch (Node x, int key) {
		
		System.out.println("Tree Search start");
		if (x == null || isNilNode(x) || x.key == key) {
			return x;
		}
		if (key < x.key)
			return treeSearch (x.left, key);
		else 
			return treeSearch (x.right, key);
	}
	
	public Node treeSuccessor (Node x) {
		System.out.println("Tree Sucessor start");
		if (x.right  != null && !(isNilNode(x.right))) {
			return treeMinimum(x.right);
		}
		Node y = x.parent;
		// should  use equals of ==? to check
		while (y != null && x == y.right) {
			x=y;
			y=y.parent;
		}
		return y;
	}
	public Node treePredecessor (Node x) {
		System.out.println("Tree predecessor start");
		if (x.left  != null && !(isNilNode(x.left))) {
			return treeMaximum(x.left);
		}
		Node y = x.parent;
		// should  use equals of ==? to check
		while (y != null && x == y.left) {
			x=y;
			y=y.parent;
		}
		return y;
	}
	
	// To be used for deletion
	private void transplantTree (Node u,Node v) {
		System.out.println("Tree Search start");
		if (u.parent == null) {
			root = v;
		}
		else if (u == u.parent.left) {
			u.parent.left = v;
		}
		else {
			u.parent.right = v;
		}
		if (v != null || !isNilNode(v)) {
			v.parent = u.parent;
		}
	}
	
	
	
	
	private Node treeMinimum(Node x) {
		while (!isNilNode(x.left)) {
			x = x.left;
		}
		return x;
	}
	private Node treeMaximum(Node x) {
		while (!isNilNode(x.right)) {
			x = x.right;
		}
		return x;
	}
	
	private void leftRotate (Node x) {
		Node y = x.right;
		x.right = y.left;
		if (!isNilNode(y.left)) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (isNilNode(x.parent)) {
			root = y;
		}
		else if (x == x.parent.left) {
			x.parent.left = y;
		}
		else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	
	private void rightRotate (Node x) {
		Node y = x.left;
		x.left = y.right;
		if (!isNilNode(y.right)) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (isNilNode(x.parent)) {
			root = y;
		}
		else if (x == x.parent.right) {
			x.parent.right = y;
		}
		else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}
	
	public void rebBlackTreeInsert (Node z) {
		Node y = nilNode;
		Node x = root;
		while (!isNilNode(x)) {
			y=x;
			if (z.key < x.key) {
				x = x.left;
			}else {
					x=x.right;
				}
			}
		z.parent = y;
		if (isNilNode(y)) {
			root = z;
		} 
		else if (z.key < y.key) {
			y.left = z;
		}
		else {
			y.right = z;
		}
		z.left = nilNode;
		z.right = nilNode;
		z.color = RED;
		redBlackInsertFixup(z);
	}
	private void redBlackInsertFixup (Node z) {
		while (RED.equals(z.parent.color)) {
			if (z.parent == z.parent.parent.left) {
				Node y = z.parent.parent.right;
				if (RED.equals(y.color)) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				}
				else if (z == z.parent.right) {
					z = z.parent;
					leftRotate(z);
				}
				else {
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					rightRotate(z.parent.parent);
				}
								
			}else {

				Node y = z.parent.parent.left;
				if (RED.equals(y.color)) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				}
				else if (z == z.parent.left) {
					z = z.parent;
					rightRotate(z);
				}
				else {
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					leftRotate(z.parent.parent);
				}
			}
		}
		root.color = BLACK;
	}
	
	public void sortedPrint(Node node) {
		inOrderTraversal(node);
	}
public  int findHeight(Node node) {
		
		if (node == null || isNilNode(node)) {
			return -1;
		}
		else {
			int leftHeight = findHeight(node.left);
			int rightHeight = findHeight(node.right);
			
			if (leftHeight > rightHeight) {
				return (leftHeight+1);
			}
			else {
				return (rightHeight+1);
			}
		}
		
		
	}
	
	public static void main (String args[]) {
		
		int keys[] = {1,2,3,4,5};
		
		RedBlackTree tree = new RedBlackTree();
		tree.root = nilNode;		
		for (int i = 0; i < keys.length; i++) {
			Node n = new Node (null,null,keys[i],null,null);			
			tree.rebBlackTreeInsert(n);			
		}
		
		System.out.println("Initial Tree Height :> " + tree.findHeight(tree.root));
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter your choice\n");
			System.out.println("1. INSERT KEY");
			System.out.println("2. DELETE KEY");
			System.out.println("3. PRINT SORTED TREE");
			System.out.println("4. SEARCH FOR A KEY");
			System.out.println("5. FIND SUCCESOR");
			System.out.println("6. FIND PREDECESSOR");
			System.out.println("7. FIND Mininum");
			System.out.println("8. FIND Maximum");
			System.out.println("9. End Program");
			
			
			int choice = scanner.nextInt();
			if (choice == 9) {
				System.out.println("Exiting");
				break;
			}
			
			switch (choice) {
			case 1: 
					System.out.println("Enter key to be inserted (Integer)");
					int key = scanner.nextInt();
					Node n = new Node (null,null,key,null,null);					
					tree.rebBlackTreeInsert(n);
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			case 2: System.out.println("Call to deleting a Key");
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			case 3: tree.sortedPrint(tree.root);
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			case 4: System.out.println("Enter key value to be searched for");
					key = scanner.nextInt();
					Node searchedNode = tree.treeSearch(tree.root, key);
					if (tree.isNilNode(searchedNode)) {
						System.out.println("Key "+ key +" not present in the tree");
						continue;
					}
					System.out.println("Searching for key "+ key +":>" + searchedNode);
					System.out.println("Node Height :> " + tree.findHeight(searchedNode));
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			case 5: System.out.println("Enter key whose successor is to be found");
					key = scanner.nextInt();
					
					searchedNode = tree.treeSearch(tree.root, key);
					if (tree.isNilNode(searchedNode)) {
						System.out.println("Key "+ key +" not present in the tree");
						continue;
					}
					System.out.println("Node Successor of "+ key +":>" + tree.treeSuccessor(searchedNode));
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			case 6: System.out.println("Enter key whose predecessor is to be found");
					key = scanner.nextInt();
					
					searchedNode = tree.treeSearch(tree.root, key);
					if (tree.isNilNode(searchedNode)) {
						System.out.println("Key "+ key +" not present in the tree");
						continue;
					}
					System.out.println("Node Predecessor of "+ key +" :>" + tree.treePredecessor(searchedNode));
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			case 7: System.out.println("Minimum Value in Tree :>" + tree.treeMinimum(tree.root).key);
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			case 8: System.out.println("Maximum Value in Tree :>" + tree.treeMaximum(tree.root).key);
					System.out.println("Tree Height :> " + tree.findHeight(tree.root));
					break;
			default: System.out.println("Wrong choice entered. Please try again!!");
			}
			
			
		}
		scanner.close();
	
		/*System.out.println("Node Successor :>" + tree.treeSuccessor(tree.root.left));
		System.out.println("Node Predecessor :>" + tree.treePredecessor(tree.root.left));
		
		
		System.out.println("---- Tree Traversal INORDER Start ----\n");
		tree.sortedPrint(tree.root);
		System.out.println("\n---- Tree Traversal INORDER End ----\n");
		
		System.out.println("Minimum Value in Tree :>" + tree.treeMinimum(tree.root).key);
		System.out.println("Maximum Value in Tree :>" + tree.treeMaximum(tree.root).key);
		
		int searchKey = 4;
		System.out.println("Searching for key"+ searchKey +":>" + tree.treeSearch(tree.root, searchKey));*/
		
		// Considering height of the leaf as nodes as 0
		System.out.println("Finding height of the tree:");
		//System.out.println(tree.findHeight(tree.root));
		
	}
	
}
