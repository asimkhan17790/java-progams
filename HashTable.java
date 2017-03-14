package edu.neu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Asim Khan
 *
 */
public class HashTable {
	private boolean positionStore = false;
	int position = 0;
	TableElement table[] = new TableElement [701];
	public HashTable (boolean positionStore) {
		this.positionStore = positionStore;
	}
	
	
	static class Position {
		int pos;
		Position next;
		public Position(int pos) {
			this.pos = pos;
			next = null;
		}
	}
	
	static class TableElement {
		String key;
		int value;
		Position p;
		TableElement next;
		
		TableElement(String key, int value, int position) {
			this.key = key;
			this.value = value;
			next = null;
			p = new Position(position);
		}
		
		public void addPosition (int position) {
			Position newPosition = new Position(position);
			Position temp = p;
			if (temp == null) {
				temp = newPosition;				
			}
			else {
				while (temp.next!=null) {
					temp=temp.next;
				}
				temp.next = newPosition;
			}
		}
		public StringBuilder printPositions() {
			StringBuilder s = new StringBuilder("");
			if (p!=null) {
				Position temp = p;
				
				while (temp!=null) {
					s.append(temp.pos + (temp.next!=null?",":""));
					temp = temp.next;
				}	
			}			
			return s;			
		}
	}
		
	public void insert(String key, int value) {
		position ++;
		int index = hash(key);
		if ("look".equals(key)) {
			System.out.print("");
		}
		
		if (table[index] == null) {
			table[index] = new TableElement(key, value, position);
		}
		else {

			TableElement entry = table[index];
			while (entry.next != null && !entry.key.equals(key))
			{
				entry = entry.next;
			}				
			if (entry.key.equals(key)) {
				entry.value ++;
				if ("look".equals(key)) {
					System.out.print("");
				}
				entry.addPosition(position);
			}				
			else {
				entry.next = new TableElement(key, value, position);
			}				

		}
	}
	public int find (String key) {
		int index = hash(key);
		
		if (table[index] == null) {
			return -9999;
		}
		
		TableElement e = table[index];
		while (e!=null && !e.key.equals(key)) {
			e=e.next;
		}
		if (e ==null) {
			return -9999;
		}
		else {
			return e.value;
		}
	}
	
	/**
	 * @param key
	 * @return -1 if key is not found else returns the 1 if entry is deleted
	 */
	public int delete(String key) {
		int index = hash(key);
		if (table[index] == null) {
			return -9999;
		}
		else {
			TableElement p = null;
			TableElement e = table[index];
			
			while (e.next != null && e.key.equals(key)) {
				p = e;
				e = e.next;
			}
			if (e.key.equals(key)) {
				if (p == null) {
					table[index] = e.next;
					
				}
				else {
					p.next = e.next;
				}
				
			}
			return 1;
		}
		
	}
	public int increase(String key) {
		int index = hash(key);
		if (table[index] == null) {
			return -9999;
		}
		TableElement e = table[index];
		while (e!=null && !e.key.equals(key)) {
			e=e.next;
		}
		if (e ==null) {
			return -9999;
		}
		else {
			e.value ++;
			return e.value;
		}
		
		
	}
	public void listAllKeys() {
		for (int i = 0; i < table.length; i++)
		{
			System.out.print("\n HashIndex "+ i +": ");

			TableElement t = table[i];
			while (t != null){
				if (positionStore) {
					if ("look".equals(t.key)) {
						System.out.print("");
					}
					System.out.print("{"+ t.key+","+t.value + ":("+ t.printPositions() +")} ");
						
				}else {
					System.out.print("{"+ t.key+","+t.value + "} ");
				}
				
				t = t.next;
			}            
		}
	}
	
	
	private int hash(String key) {
		// Horners rule
		char[] charArray = key.toCharArray();
		
		 int h = 0	;
	         for (int i = 0; i < charArray.length; i++) {
	                h = 31 * h + charArray[i];
	            }
	        h = h % table.length;
	        h = Math.abs(h);
	        return h;
	}
	
	
	
	public static void main (String args[]) throws IOException {


		String line = null;
		StringBuilder text = new StringBuilder("");
		
		// Your text file path
		String fileLocation = "C:\\Users\\Asim\\Dropbox\\Northeastern_University\\Spring2017\\ALGORITHMS\\Assignments\\CS5800_MohdAsim_Khan\\HW8\\alice_in_wonderland.txt";
		//String fileLocation = "C:\\Users\\Asim\\Dropbox\\Northeastern_University\\Spring2017\\ALGORITHMS\\Assignments\\CS5800_MohdAsim_Khan\\HW8\\testFile.txt";
		
		BufferedReader br = new BufferedReader(
				new FileReader(fileLocation));

		while ((line = br.readLine()) != null) {
			text.append(line + " ");			
		}
		String str = text.toString();
		str = str.replaceAll("\n", " ");
		str = str.replaceAll("\t", " ");
		StringBuilder s = new StringBuilder("");
		String pattern = "(\\b(\\d*)?[a-zA-Z]+(\\d+)?(\\-[a-zA-Z]+|\\-\\d+)?(\\d*|[a-zA-Z]*)?\\b|\\d+((\\.|\\,)?\\d+)?|([a-zA-Z]+\\-[a-zA-Z]+))";
		Matcher m = Pattern.compile(pattern).matcher(str);
		while (m.find()) {
			s.append(m.group(1) + " ");
		}

		str = s.toString().trim();
		List<String> stringList = Arrays.asList(str.split("\\s+"));
		HashTable h = new HashTable(true);
		for (String item : stringList) {
			h.insert(item, 1);
		}
		
		//System.out.println("Count of asim:"+ h.find("Asim"));
		//System.out.println(h.increase("Asim"));
		//System.out.println("Count of asim:"+ h.find("Asim"));		
		h.listAllKeys();
		
		br.close();
	}

}
