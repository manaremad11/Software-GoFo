import java.util.ArrayList;

public class User {

	protected String userName, phoneNumber, location, email, password, userType;
	int id; //User ID
	/*Array lists holds the objects of owners and players*/
	ArrayList<PlaygroundOwner> playgroundOwners = new ArrayList<PlaygroundOwner>();
	ArrayList<Player> players = new ArrayList<Player>();
	
	User(String un, String em, String pass, int id) {	//Create New User
		userName = un;
		email = em;
		password = pass;
		this.id = id;
	}

	/*Checks if the Email or Username is available.*/
	public boolean checkAuthorization(String name, String em) {
		for(int i = 0; i < playgroundOwners.size(); i++) {
			if(playgroundOwners.get(i).userName.equals(name) || playgroundOwners.get(i).email.equals(em)) {
				return false;	//Not available.
			}
		}
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).userName.equals(name) || players.get(i).email.equals(em)) {
				return false;	//Not available.
			}
		}
		return true;
	}
	
	
	
	
	
	/*Login function checks if the user exists the list or not
	 *Returns "1" if the user is a playground owner
	 *Returns "2" if the user is a player
	 *Returns "0" if the user does not exist in the list*/
	public int login(String em, String pass) {

		for (int i = 0; i < playgroundOwners.size(); i++) {
			if ((playgroundOwners.get(i).email.equals(em)) && (playgroundOwners.get(i).password.equals(pass))) {
				return 1;	//1 --> Playground Owner
			}
		}

		for (int i = 0; i < players.size(); i++) {
			if ((players.get(i).email.equals(em)) && (players.get(i).password.equals(pass))) {
				return 2;	//2 --> Player
			}
		}
		
		return 0; //0 --> Neither of them.
	}
	
	public int getIndexForPlaygroundOwner(String em) {	//Returning an index of specific playground owner.
		for (int i = 0; i < playgroundOwners.size(); i++) {
			if ((playgroundOwners.get(i).email.equals(em))) {
				return i;	//Return index
			}
		}
		return 0;
	}
	
	public int getIndexForPlayer(String em) {			//Returning an index of specific player.
		for (int i = 0; i < players.size(); i++) {
			if ((players.get(i).email.equals(em))) {
				return i;	//Return index
			}
		}
		return 0;
	}
	
	void addPlaygroundOwner(PlaygroundOwner p) {		//Adding new playground owner object to the list.
		playgroundOwners.add(p);	
	}
	
	void addPlayer(Player p) {							//Adding new playground owner object to the list.
		players.add(p);	
	}
}