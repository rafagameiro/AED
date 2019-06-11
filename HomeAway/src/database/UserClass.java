/**
 * 
 */
package database;

import dataStructures.BinarySearchTree;
import dataStructures.IterableStack;
import dataStructures.IterableStackInList;
import dataStructures.Iterator;
import dataStructures.IteratorWFilter;
import dataStructures.OrderedDictionary;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class UserClass implements User {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
	
    /**
     * User's identifier
     */
	private String idUser;
	
	/**
	 * Name of the user
	 */
	private String name;
	
	/**
	 * Nationality of the user
	 */
	private String nationality;
	
	/**
	 * Address of the user
	 */
	private String address;
	
	/**
	 * Email of the user
	 */
	private String email;
	
	/**
	 * Phone number of the user
	 */
	private String phoneNumber;
	
	/**
	 * tells if the user has visited some property
	 */
	private boolean traveller;
	
	/**
	 * tells if the user own any property
	 */
	private boolean owner;
	
	/**
	 * Number of properties the user owns
	 */
	private int numOfProperties;
	
	
	/**
	 * DoublyLinkedList with all the user's stays
	 */
	private IterableStack<PropertyInfo> stays;
	
	/**
	 * Properties the user owns
	 */
	private OrderedDictionary<String, PropertyInfo> properties;
	
	
	public UserClass(String idUser, String email, String phoneNumber, String name, String nationality, String address){
		this.idUser = idUser;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.nationality = nationality;
		this.address = address;
		numOfProperties = 0;
		traveller = false;
		owner = false;
		properties = new BinarySearchTree<String, PropertyInfo>();
		stays = new IterableStackInList<PropertyInfo>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.User#addProperty(database.Property)
	 */
	@Override
	public void addProperty(Property property){
		this.properties.insert(property.getIdHome(), property);
		numOfProperties++;
		owner = true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.User#addStay(database.Property)
	 */
	@Override
	public void addStay(Property stay){
		stays.push(stay);
		traveller = true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.User#removeProperty()
	 */
	@Override
	public void removeProperty(String idHome){
		properties.remove(idHome);
		numOfProperties--;
		if(numOfProperties == 0)
			owner = false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.User#updateProfile(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateProfile(String mail, String phoneNumber, String address){
		this.email = mail;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.UserInfo#getIdUser()
	 */
	@Override
	public String getIdUser() {
		// TODO Auto-generated method stub
		return idUser;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.UserInfo#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.UserInfo#getNationality()
	 */
	@Override
	public String getNationality() {
		// TODO Auto-generated method stub
		return nationality;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.UserInfo#getAddress()
	 */
	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.UserInfo#getEmail()
	 */
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.UserInfo#getPhoneNumber()
	 */
	@Override
	public String getPhoneNumber() {
		// TODO Auto-generated method stub
		return phoneNumber;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.User#getNumOfProperties()
	 */
	@Override
	public int getNumOfProperties() {
		// TODO Auto-generated method stub
		return numOfProperties;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.User#isTraveller()
	 */
	@Override
	public boolean isTraveller() {
		// TODO Auto-generated method stub
		return traveller;
	}

	/*
	 * (non-Javadoc)
	 * @see database.User#isOwner()
	 */
	@Override
	public boolean isOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.User#getProperties()
	 */
	@Override
	public Iterator<PropertyInfo> getProperties() {
		// TODO Auto-generated method stub
		return new IteratorWFilter<String, PropertyInfo>(properties.iterator());
	}

	/*
	 * (non-Javadoc)
	 * @see database.User#getStays()
	 */
	@Override
	public Iterator<PropertyInfo> getStays() {
		// TODO Auto-generated method stub
		 return stays.iterator(); 
	}

}
