import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dataStructures.Iterator;
import database.AlreadyVisitedException;
import database.BothTravellerAndOwnerException;
import database.HomeAway;
import database.HomeAwayClass;
import database.InexistentPropertyException;
import database.InexistentUserException;
import database.InvalidDataException;
import database.NoResultsException;
import database.OwnerIsntTravellerException;
import database.PropertyInfo;
import database.PropertyAlreadyExistsException;
import database.UserInfo;
import database.UserAlreadyExistsException;
import database.UserDidntTravelledException;
import database.UserHasPropertiesException;
import database.UserNotOwnerException;

/**
 * 
 */

/**
 * @author Rafael Gameiro (50677) rr.gameiro@campus.fct.unl.pt
 * @author Rui Santos (50833) rfc.santos@campus.fct.unl.pt
 *
 */
public class Main {
	
	/**
	 * System commands
	 */
	public static final String INSERTUSER = "IU";
	public static final String UPDATE = "UU";
	public static final String REMOVEUSER = "RU";
	public static final String PROFILE = "GU";
	public static final String INSERTPROPERTY = "AH";
	public static final String REMOVEPROPERTY = "RH";
	public static final String PROPERTY = "GH";
	public static final String STAYING = "AT";
	public static final String USERPROPERTIES = "LH";
	public static final String USERSTAYS = "LT";
	public static final String SEARCH = "PH";
	public static final String BESTPROPERTIES = "LB";
	public static final String EXIT = "XS";
	
	/**
	 * System messages
	 */
	public static final String INSURTSUCC = "Insercao de utilizador com sucesso.";
	public static final String USERACTSUCC = "Utilizador atualizado com sucesso.";
	public static final String USERREMSUC = "Utilizador removido com sucesso.";
	public static final String PROPADDSUCC = "Propriedade adicionada com sucesso.";
	public static final String PROPREMSUCC = "Propriedade removida com sucesso.";
	public static final String STAYADDSUCC = "Estadia adicionada com sucesso.";
	public static final String LEAVE = "Gravando e terminando...";
	
	/**
	 * System errors
	 */
	public static final String USEREXIST = "Utilizador existente.";
	public static final String USERINEX = "Utilizador inexistente.";
	public static final String USERISPROP = "Utilizador e proprietario.";
	public static final String USERISNOTOWNER ="Utilizador nao e proprietario.";
	public static final String USERNOTTRAVEL = "Utilizador nao viajou.";
	public static final String PROPINEX = "Propriedade inexistente.";
	public static final String PROPEXIST = "Propriedade existente.";
	public static final String PROPALREADYVISIT = "Propriedade ja foi visitada.";
	public static final String INVDATA = "Dados invalidos.";
	public static final String NORESULTS = "Pesquisa nao devolveu resultados.";
	public static final String TRAVISPROP = "Viajante e o proprietario.";
	public static final String TRAVISNOTPROP = "Viajante nao e o proprietario.";

	public static final String FILENAME = "storedHome.dat";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HomeAway home = load();
		Scanner in = new Scanner(System.in);
		String option = "";
		
		while(!option.equalsIgnoreCase(EXIT)) {
			option = in.next().toUpperCase();
			switch(option) {
				case INSERTUSER: insertUser(home,in);
					break;
				case UPDATE: updateInfo(home,in);
					break;
				case REMOVEUSER: removeUser(home,in);
					break;
				case PROFILE: checkProfile(home,in);
					break;
				case INSERTPROPERTY: insertProperty(home,in);
					break;
				case REMOVEPROPERTY: removeProperty(home,in);
					break;
				case PROPERTY: checkProperty(home,in);
					break;
				case STAYING: userStaying(home,in);
					break;
				case USERPROPERTIES: showUserProperties(home, in);
					break;
				case USERSTAYS: showUserStays(home, in);
					break;
				case SEARCH: searchProperties(home,in);
					break;
				case BESTPROPERTIES: showBestProperties(home,in);
					break;
				case EXIT: System.out.println(LEAVE);
					break;
			}
			System.out.println();
		}
		
		store(home);
		in.close();
		
	}

private static HomeAway load() {
		// TODO Auto-generated method stub
		try {
			
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(FILENAME));
			
			HomeAway home = (HomeAway) file.readObject();
			file.close();
			
			return home;
			
		}catch(IOException e){
			
			return new HomeAwayClass();
			
		}catch(ClassNotFoundException e){ }
		
		return null;
		
	}


	private static void store(HomeAway home) {
		// TODO Auto-generated method stub
		try {
			
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(FILENAME));
			
			file.writeObject(home);
			file.flush();
			file.close();
			
		}catch(IOException e){ }
	}


	private static void insertUser(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idUser = in.next().trim();
			String email = in.next().trim();
			String phoneNumber = in.next().trim();
			String name = in.nextLine().trim();
			String nationality = in.nextLine();
			String address = in.nextLine();
			
			home.addUser(idUser, email, phoneNumber, name, nationality, address);
			
			System.out.println(INSURTSUCC);
		}catch(UserAlreadyExistsException e){
			
			System.out.println(USEREXIST);
			
		}
	}
	
	private static void updateInfo(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idUser = in.next().trim();
			String mail = in.next().trim();
			String phoneNumber = in.nextLine().trim();
			String address = in.nextLine();
			
			home.updateUserInfo(idUser, mail, phoneNumber, address);
			
			System.out.println(USERACTSUCC);
			
		}catch(InexistentUserException e){
			
			System.out.println(USERINEX);
			
		}
	}
	
	private static void removeUser(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idUser = in.nextLine().trim();
			
			home.removeUser(idUser);
			
			System.out.println(USERREMSUC);
			
		}catch(InexistentUserException e){
			
			System.out.println(USERINEX);
			
		}catch(UserHasPropertiesException e){
			
			System.out.println(USERISPROP);
		}
	}
	
	private static void checkProfile(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idUser = in.nextLine().trim();
			
			UserInfo user = home.getUserInfo(idUser);
			
			System.out.println(user.getName() + ": " + user.getAddress() + ", " + 
			user.getNationality() + ", " + user.getEmail() + ", " + user.getPhoneNumber());
			
		}catch(InexistentUserException e){
			
			System.out.println(USERINEX);
			
		}
	}
	
	private static void insertProperty(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idHome = in.next().trim();
			String idUser = in.next().trim();
			int price = in.nextInt();
			int capacity = in.nextInt();
			String location = in.nextLine().trim();
			String description = in.nextLine();
			String address = in.nextLine();
			
			home.addProperty(idHome, idUser, price, capacity, location, description, address);
			
			System.out.println(PROPADDSUCC);
			
		}catch(InvalidDataException e){
			
			System.out.println(INVDATA);
			
		}catch(InexistentUserException e){
			
			System.out.println(USERINEX);
			
		}catch(PropertyAlreadyExistsException e){
			
			System.out.println(PROPEXIST);
			
		}
	}
	
	private static void removeProperty(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idHome = in.nextLine().trim();
			
			home.removeProperty(idHome);
			
			System.out.println(PROPREMSUCC);
			
		}catch(InexistentPropertyException e){
			
			System.out.println(PROPINEX);
			
		}catch(AlreadyVisitedException e){
			
			System.out.println(PROPALREADYVISIT);
			
		}
	}
	
	private static void checkProperty(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idHome = in.nextLine().trim();
			
			PropertyInfo property = home.getPropertyInfo(idHome);
			
			System.out.println(property.getDescription() + ": " + property.getAddress() + ", " + property.getLocation() + ", " + 
			property.getDailyPrice() + ", " + property.getCapacity() + ", " + property.getPoints() + ", " + property.getOwner().getName());
			
		}catch(InexistentPropertyException e){
			
			System.out.println(PROPINEX);
			
		}
	}
	
	private static void userStaying(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idUser = in.next().trim();
			String idHome = in.next().trim();
			String points = in.nextLine().trim();
			
			if(!points.isEmpty()){
				int score = Integer.parseInt(points);
				home.travellerStaying(idHome, idUser, score);
			}else
				home.ownerStaying(idHome, idUser);
			
			System.out.println(STAYADDSUCC);
			
		}catch(InvalidDataException e){
			
			System.out.println(INVDATA);
			
		}catch(InexistentUserException e){
			
			System.out.println(USERINEX);
			
		}catch(InexistentPropertyException e){
			
			System.out.println(PROPINEX);
			
		}catch(BothTravellerAndOwnerException e){
			
			System.out.println(TRAVISPROP);
			
		}catch(OwnerIsntTravellerException e){
			
			System.out.println(TRAVISNOTPROP);
		}
	}
	
	private static void showUserProperties(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idUser = in.nextLine().trim();
			
			Iterator<PropertyInfo> it= home.showUserProperties(idUser);
			
			while(it.hasNext()) {
				PropertyInfo property = it.next();
			
				System.out.println(property.getIdHome() + " " + property.getDescription() + " " + property.getAddress() + " " + 
				property.getLocation() + " " + property.getDailyPrice() + " " + property.getCapacity() + " " + property.getPoints());
			}
			
		}catch(InexistentUserException e){
			
			System.out.println(USERINEX);
			
		}catch(UserNotOwnerException e){
			
			System.out.println(USERISNOTOWNER);
			
		}
	}
	
	private static void showUserStays(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String idUser = in.nextLine().trim();
			
			Iterator<PropertyInfo> it = home.showUserStays(idUser);
			
			while(it.hasNext()){
				PropertyInfo current = it.next();
				
				System.out.println(current.getIdHome() + " " + current.getDescription() + " " + current.getAddress() + " " + 
				current.getLocation() + " " + current.getDailyPrice() + " " + current.getCapacity() + " " + current.getPoints());
				
			}
			
		}catch(InexistentUserException e){
			
			System.out.println(USERINEX);
			
		}catch(UserDidntTravelledException e){
			
			System.out.println(USERNOTTRAVEL);
			
		}
	}

	private static void searchProperties(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			int capacity = in.nextInt();
			String location = in.nextLine().trim();
			
			Iterator<PropertyInfo> it = home.searchProperties(capacity, location);
			
			while(it.hasNext()) {
				PropertyInfo property = it.next();
			
				System.out.println(property.getIdHome() + " " + property.getDescription() + " " + property.getAddress() + " " + 
				property.getLocation() + " " + property.getDailyPrice() + " " + property.getCapacity() + " " + property.getPoints());
			}
			
		}catch(InvalidDataException e){
			
			System.out.println(INVDATA);
			
		}catch(NoResultsException e){
			
			System.out.println(NORESULTS);
			
		}
	}


	private static void showBestProperties(HomeAway home, Scanner in) {
		// TODO Auto-generated method stub
		try {
			String location = in.nextLine().trim();
			
			Iterator<PropertyInfo> it = home.showBestProperties(location);
			
			while(it.hasNext()) {
				PropertyInfo property = it.next();
				
				System.out.println(property.getIdHome() + " " + property.getDescription() + " " + property.getAddress() + " " + 
				property.getLocation() + " " + property.getDailyPrice() + " " + property.getCapacity() + " " + property.getPoints());
			}
			
		}catch(NoResultsException e){
			
			System.out.println(NORESULTS);
			
		}
	}

}
