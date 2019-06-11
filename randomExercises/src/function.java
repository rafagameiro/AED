/**
 * 
 */

import java.math.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * @author thinkpad
 *
 */
public class function {
	
	/*
	 * complexidade da funçãp potenciaR:
	 * 
	 * recorrencia 1
	 * 
	 * T(n) 
	 * a	n=0 || n=1
	 * 
	 * bT(n/2)+O(1)	n>=1 || n>=2
	 * 
	 * 
	 * T(n)
	 * 
	 * O(log n)	se b=1
	 * 
	 * O(n)	se b=2
	 */
	
	
	public static double potenciaR(double x, int n) {	//10 b)
		
		if(n == 0)
			return 1;
		if(n % 2 == 0)
			return potenciaR(x*x, n/2);
		else
			return potenciaR(x*x, n/2) * x;
		
	}
	
	public double potenciaI(double x ,int n) {	//11
		double res = 1;
		if(n % 2 == 0)
			return -1;
		while(n > 0) {
			res *= x;
			n--;
		}
		return res;
	}
	
	//requires: vector.length > 0
	public static int max(int[] vector) {	//12
		return maxRec(vector, vector.length-1);
	}
	
	/*
	 * complexidade temporal:
	 * 
	 */
	
	private static int maxRec(int[] vector, int pos) {
		if(pos == 0)
			return vector[0];
		else
			return Math.max(vector[pos], maxRec(vector, --pos));
	}
	
	/*
	 * complexidade temporal:
	 * 
	 */
	
	public static int multiplyRec(int n1, int n2) {
		if(n2 == 0 || n1 == 0)
			return 0;
		else
			return n1 + multiplyRec(n1, --n2);
	}
	
	public static void main(String[] args) {
		
		int[] array = {12, 34, 67, 132, 2, -2 , 43};
		
		System.out.println(multiplyRec(23, 13));
		System.out.println(max(array));
		System.out.println(potenciaR(2,5));
		
	}

}
