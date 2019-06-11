package database;

import dataStructures.Iterator;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */

interface Locations {
	
	/**
	 * Insert the property in the LocationByCapacity, through the property capacity
	 * 
	 * @param property its the property, the user wants to add
	 */
	void insertPropertyCapacity(Property property);
	
	/**
	 * Removes the property from the LocationByCapacity
	 * 
	 * @param property its the property, the user wants to remove
	 */
	void removePropertyCapacity(Property property);
	
	/**
	 * Insert the property in the LocationByPoints
	 * if there is not a node with the same points as the property
	 * It will create a new node(Binary Search Tree), and put the property inside
	 * 
	 * @param property its the property the user wants to add
	 */
	void insertPropertyPoints(Property property);
	
	/**
	 * Removes the property from the LocationByPoints
	 * if there is no more properties in the the property Binary Search Tree
	 * It will remove the node from the main Binary Search Tree
	 * 
	 * @param property its the property the user wants to remove
	 */
	void removePropertyPoints(Property property);
	
	/**
	 * Verifies if the LocationByCapacity has properties
	 * from the index capacity, to the end of the array
	 * 
	 * @param capacity its an index of the array, from where the user wants to start the cycle
	 * @return true, if there are properties, otherwise false
	 */
	boolean hasResultsForCapacity(int capacity);
	
	/**
	 * Verifies if the LocationByPoints has properties
	 * 
	 * @return true, if has properties inside, otherwise false
	 */
	boolean hasResultsForPoints();
	
	/**
	 * Creates an object of the class ArrayBSTIterator
	 * 
	 * @param capacity index from where the user wants to start its iteration
	 * @return an iterator for the LocationByCapacity
	 */
	Iterator<PropertyInfo> iteratorCapacity(int capacity);
	
	/**
	 * Creates an object of the class ChainedBSTIterator
	 * 
	 * @return an iterator for the LocationByPoints
	 */
	Iterator<PropertyInfo> iteratorPoints();

}
