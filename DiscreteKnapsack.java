package edu.neu;

public class DiscreteKnapsack {

	public static void main (String args[]) {
		int Z = 50;
		int w[] = {0,10,20,30,50};
		int v[]  ={0,60,100,120,150};
		int n = 4;
		int C[][] = dynamic_Discrete_Knapsack (Z,w,v, n);
		
		for (int i = 0; i<=Z;i++) {
			System.out.print(i+" -- ");
			for (int j = 0;j <= n; j++) {
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}
		
		itemsAdded(C,Z,n,w);
		
	}
	
	public static int[][] dynamic_Discrete_Knapsack (int Z,int w[], int v[], int n) {
		int C[][]= new int [Z+1][n+1];
		
		
		for (int z=0;z<=Z;z++) {
			C[z][0] = 0;
		}
		for (int i=1;i<=n;i++) {
			C[0][i] = 0;
			for (int z=1;z<=Z;z++) {
				if (w[i] <= z) {
					C[z][i] = Math.max(C[z][i-1], v[i] + C[z-w[i]][i-1]);
				}
				else {
					C[z][i] = C[z][i-1];
				}
			}
		}
		return C;
	}
	
	
	public static void itemsAdded(int C[][],int Z,int n,int w[]) {
		
		while (n>0 && Z>0) {
			if (C[Z][n]>C[Z][n-1]) {
				System.out.println("Item at index " + n +" is added");
				Z=Z-w[n];
 			}
			n--;
		}
	}
}
