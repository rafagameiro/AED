/**
 * 
 */
package database;

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class PropertyClass implements Property {
	
	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
	
    /**
     * Has the information this property's owner
     */
	private UserInfo owner;
	
	/**
	 * Property's identifier
	 */
	private String idHome;
	
	/**
	 * Property's description
	 */
	private String description;
	
	/**
	 * Property's address
	 */
	private String address;
	
	/**
	 * Property's location
	 */
	private String location;
	
	/**
	 * Price per day to say at this property
	 */
	private int dailyPrice;
	
	/**
	 * Maximum amount of people the property can hold
	 */
	private int maxCapacity;
	
	/**
	 * Total points given by all the travelers
	 */
	private int points;
	
	/*
	 * Object used to compare the property points with other properties
	 */
	private RatingClass rating;
	
	/**
	 * Tells if the property was already visited
	 */
	private boolean visited;
	
	public PropertyClass(UserInfo user, String idHome, int price, int capacity, String location, String description, String address) {
		owner = user;
		this.idHome = idHome;
		dailyPrice = price;
		maxCapacity = capacity;
		this.location = location;
		this.description = description;
		this.address = address;
		points = 0;
		rating = new RatingClass(points);
		visited = false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Property#setVisit()
	 */
	@Override
	public void setVisit() {
		// TODO Auto-generated method stub
		visited = true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Property#setPoints(int)
	 */
	@Override
	public void setPoints(int points){
		this.points += points;
		rating.setInt(this.points);
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getOwner()
	 */
	@Override
	public UserInfo getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getIdHome()
	 */
	@Override
	public String getIdHome() {
		// TODO Auto-generated method stub
		return idHome;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getAddress()
	 */
	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getLocation()
	 */
	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getDailyPrice()
	 */
	@Override
	public int getDailyPrice() {
		// TODO Auto-generated method stub
		return dailyPrice;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getCapacity()
	 */
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return maxCapacity;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.Property#wasVisited()
	 */
	@Override
	public boolean wasVisited() {
		// TODO Auto-generated method stub
		return visited;
	}
	
	/*
	 * (non-Javadoc)
	 * @see database.PropertyInfo#getPoints()
	 */
	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}

	@Override
	public RatingClass getRating() {
		// TODO Auto-generated method stub
		return rating;
	}
	
}
