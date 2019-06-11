/**
 * 
 */
package database;

import java.io.Serializable;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
class RatingClass implements Serializable, Comparable<RatingClass> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Points of the property
	 */
	private int compareInt; 

	/**
	 * 
	 */
	public RatingClass(int num) {
		// TODO Auto-generated constructor stub
		compareInt = num;
	}
	
	/**
	 * 
	 * @return the value in compareInt
	 */
	public int getInt() {
		return compareInt;
	}
	
	/*
	 * Changes the value in compareInt, to the value in number
	 */
	public void setInt(int number) {
		compareInt = number;
	}

	@Override
	public int compareTo(RatingClass number) {
		// TODO Auto-generated method stub
		if(number.compareInt == compareInt)
			return 0;
		else if(compareInt > number.compareInt)
			return -1;
		else
			return 1;
	}

}
