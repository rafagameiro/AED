/**
 * 
 */
package database;

import java.io.Serializable;

import dataStructures.ArrayBSTIterator;
import dataStructures.BinarySearchTree;
import dataStructures.ChainedBSTIterator;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class LocationsClass implements Locations, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Array of Binary Search Trees
	 * Its the properties of a local, firstly, ordered by capacity,
	 * Then, ordered alphabetically by idHome
	 */
	private OrderedDictionary<String, PropertyInfo>[] LocationByCapacity;
	
	/*
	 * Binary Search Tree of Binary Search Trees, where each node, from the main Tree, its a Binary Search Tree
	 * Its the properties of a local, firstly, ordered by points,
	 * Then, ordered alphabetical by idHome
	 */
	private OrderedDictionary<RatingClass, OrderedDictionary<String, PropertyInfo>> LocationByPoints;
	
	
	@SuppressWarnings("unchecked")
	public LocationsClass() {
		LocationByCapacity = (OrderedDictionary<String,PropertyInfo>[]) new OrderedDictionary[20];
		LocationByPoints = new BinarySearchTree<RatingClass, OrderedDictionary<String, PropertyInfo>>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#insertPropertyCapacity(database.Property)
	 */
	@Override
	public void insertPropertyCapacity(Property property) {
		int capacity = property.getCapacity() - 1;
		
		if(LocationByCapacity[capacity] == null)
			LocationByCapacity[capacity] = new BinarySearchTree<String, PropertyInfo>();
		LocationByCapacity[capacity].insert(property.getIdHome().toUpperCase(), property);
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#removePropertyCapacity(database.Property)
	 */
	@Override
	public void removePropertyCapacity(Property property) {		
		LocationByCapacity[property.getCapacity()-1].remove(property.getIdHome().toUpperCase());
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#insertPropertyPoints(database.Property)
	 */
	@Override
	public void insertPropertyPoints(Property property) {
		// TODO Auto-generated method stub
		if(LocationByPoints.find(property.getRating()) == null)
			LocationByPoints.insert(new RatingClass(property.getPoints()), new BinarySearchTree<String, PropertyInfo>());
		LocationByPoints.find(property.getRating()).insert(property.getIdHome().toUpperCase(), property);
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#removePropertyPoints(database.Property)
	 */
	@Override
	public void removePropertyPoints(Property property) {
		// TODO Auto-generated method stub
		if(LocationByPoints.find(property.getRating()).find(property.getIdHome().toUpperCase()) != null)
			LocationByPoints.find(property.getRating()).remove(property.getIdHome().toUpperCase());
		
		if(LocationByPoints.find(property.getRating()).isEmpty()) 
			LocationByPoints.remove(property.getRating());
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#hasResultsForCapacity(int)
	 */
	@Override
	public boolean hasResultsForCapacity(int capacity) {
		for(int i = capacity-1; i < LocationByCapacity.length; i++) 
			if(LocationByCapacity[i] != null && !LocationByCapacity[i].isEmpty())
				return true;
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#hasResultsForPoints()
	 */
	@Override
	public boolean hasResultsForPoints() {
		return !LocationByPoints.isEmpty();
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#iteratorCapacity(int)
	 */
	@Override
	public Iterator<PropertyInfo> iteratorCapacity(int capacity){
		return new ArrayBSTIterator<String, PropertyInfo>(capacity, LocationByCapacity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Locations#iteratorPoints()
	 */
	@Override
	public Iterator<PropertyInfo> iteratorPoints() {
		return new ChainedBSTIterator<RatingClass, String, PropertyInfo>(LocationByPoints);
	}

}
