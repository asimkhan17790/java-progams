package edu.neu;


// Sorting using Insertion Sort
public class InsertionSort {

	
	public static void main(String args[]) {
		int arr[]= {54,4,2,56,8,4,7,9,5,2,4,5,6,7,84534,765};
		
		
		for (int j = 1 ; j<arr.length;j++) {
			int key = arr[j];
			int i = j-1;
			while(i>=0 && arr[i]>key) {
				arr[i+1] = arr[i];
				i = i-1;
			}
			arr[i+1] = key;
		}
		
		for (int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
}
