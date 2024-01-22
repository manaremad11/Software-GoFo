import java.util.Scanner;
import java.util.ArrayList;

public class main {

	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		String username, email, password, userType, firstTimeSlot, secondTimeSlot, price, ownerSlotChoice;
		int mainChoice, ownerChoice, bookChoice, playerSlotChoice;

		int playerID = 0;
		int ownerID = 0;
		int playgroundID = 0;
		int index = 0; // index of user in the list.
		int PlaygroundIndex = 0;
		int timeslotCounter = 0;
		boolean checkPlayground = false;

		ArrayList<String> Timeslot; // Array takes time slots from owner and saves it on his playground.

		// Initializing objects of classes.
		User user = new User("", "", "", 0);
		PlaygroundOwner owner = null;
		Player player = null;
		Playground playground = null;

		while (true) {

			System.out.println("Welcome to GoFo");
			System.out.println("----------------------");
			System.out.println("1. Register 2. Login");
			mainChoice = Integer.parseInt(scan.nextLine());

			/* Register */
			if (mainChoice == 1) {
				System.out.println("Enter Username: ");
				username = scan.nextLine();
				System.out.println("Enter Email: ");
				email = scan.nextLine();
				
				/*Checks if the Email or Username is available.*/
				if(!user.checkAuthorization(username, email)) {
					System.out.println("Username or Email is not available, try again, please");
					continue;
				}
				
				System.out.println("Enter Password: ");
				password = scan.nextLine();
				System.out.println("1- Player 2- Playground Owner");
				userType = scan.nextLine();

				if (userType.equals("1")) { // Register Player
					playerID++;
					player = new Player(username, email, password, playerID);
					user.addPlayer(player); // Adding New Player to the Player List.
				} else if (userType.equals("2")) { // Register Playground Owner
					ownerID++;
					owner = new PlaygroundOwner(username, email, password, ownerID);
					user.addPlaygroundOwner(owner); // Adding New Owner to the Owner List.
				}

			}
			/* Login */
			else if (mainChoice == 2) {
				System.out.println("Enter Email: ");
				email = scan.nextLine();
				System.out.println("Enter Password: ");
				password = scan.nextLine();

				if ((user.login(email, password)) == 1) { // Logs in as a playground owner.
					index = user.getIndexForPlaygroundOwner(email); // Index of current owner.
					System.out.println("----------------------");
					System.out.println("Welcome " + user.playgroundOwners.get(index).userName);
					System.out.println("----------------------");

					// Playground Owner Profile
					while (true) { 

						System.out.println("1- Add Playground  2- My Playgrounds 3- Sign out");
						ownerChoice = Integer.parseInt(scan.nextLine());

						/* Add Playground */
						if (ownerChoice == 1) {
							// Creating new array each time the owner adds a new playground to his account.
							Timeslot = new ArrayList<String>();

							// Entering time slots for each playground
							while (true) {
								System.out.println("Enter time slot");
								System.out.println("----------------");
								System.out.print("Beginning hour: ");
								firstTimeSlot = scan.nextLine();
								System.out.print("Ending hour: ");
								secondTimeSlot = scan.nextLine();

								/* Adding these values to the array */
								Timeslot.add(String.valueOf(++timeslotCounter) + "--> (" + firstTimeSlot + ":"
										+ secondTimeSlot + ")");

								System.out.println("Enter another time slot ? (y/n)");
								ownerSlotChoice = scan.nextLine();

								if (ownerSlotChoice.contains("y"))
									continue;

								break; // Breaks from the loop if the owner doesn't want to add another time slots.
							}
							timeslotCounter = 0; // Resetting time slots counter for each playground.

							System.out.println("Enter price per hour: ");
							price = scan.nextLine();

							playgroundID++;	//Incrementing playground ID.
							
							/* Creating new playground for the owner 
							 * and adding it to the array of playgrounds of his account */
							playground = new Playground(Timeslot, Double.parseDouble(price), 
									user.playgroundOwners.get(index).id, user.playgroundOwners.get(index).userName,
									playgroundID);

							user.playgroundOwners.get(index).addPlayground(playground);	
						} 
						/*Display all playgrounds*/
						else if (ownerChoice == 2) { 
							index = user.getIndexForPlaygroundOwner(email); // Index of current owner.
							//Size of playground list of owner's account
							int size = user.playgroundOwners.get(index).playgrounds.size();	

							/*Displaying all owner's playgrounds*/
							for (int i = 0; i < size; i++) {
								System.out.println(user.playgroundOwners.get(index).playgrounds.get(i).toString() + "\n");
							}	
						}
						/*Sign Out*/
						else if (ownerChoice == 3) break;
						
						else System.out.println("Please, choose one of the following options.");
					}

				} else if ((user.login(email, password)) == 2) { // Logs in as a playground owner.
					index = user.getIndexForPlaygroundOwner(email); // Index of current user.
					System.out.println("----------------------");
					System.out.println("Welcome " + user.players.get(index).userName);
					System.out.println("----------------------");

					/*Player Profile*/
					while (true) { 							
						System.out.println("1- Display Available Playgrounds   2- Sign out");
						ownerChoice = Integer.parseInt(scan.nextLine());

						/*Book playground*/
						if (ownerChoice == 1) {		
							//Displaying all playgrounds.
							for (int i = 0; i < user.playgroundOwners.size(); i++) {
								//Size of playground list of each owner's account.
								int size = user.playgroundOwners.get(i).playgrounds.size(); 
								
								for (int j = 0; j < size; j++) {
									//Check if the playground is not booked.
									if (!user.playgroundOwners.get(i).playgrounds.get(j).getPlaygroundStatus()) {
										System.out.println(user.playgroundOwners.get(i).playgrounds.get(j).toString() + "\n");
									}
									//If it's booked, the playground won't be displayed.
								}
							}
							
							
							
							System.out.println("Enter the 'Playground ID' that you would like to book: ");
							bookChoice = Integer.parseInt(scan.nextLine());
							
							
							//Displaying the selected playground.
							for (int i = 0; i < user.playgroundOwners.size(); i++) {
								//Size of playground list of each owner's account.
								int size = user.playgroundOwners.get(i).playgrounds.size(); 

								for (int j = 0; j < size; j++) {
									//If the player chooses an available playground.
									if (user.playgroundOwners.get(i).playgrounds.get(j).getPlaygroundID() == bookChoice
											&& !user.playgroundOwners.get(i).playgrounds.get(j).getPlaygroundStatus()) {
										
										//Displaying the playground that the player selected.
										System.out.println(user.playgroundOwners.get(i).playgrounds.get(j).toString() + "\n");
										index = i;				//Saving the current index of owner's list.
										PlaygroundIndex = j;	//Saving the current index of playground's list.
										checkPlayground = true;	//Playground is found.
										break;
									}
								}
								if (checkPlayground)
									break;
							}
							if (!checkPlayground || user.playgroundOwners.get(index).playgrounds.get(PlaygroundIndex).getPlaygroundStatus()) {
								System.out.println("Playground was not found \n");
								continue;	//Going back to player's profile.
							}
							
							System.out.println("Choose time slot: ");
							playerSlotChoice = Integer.parseInt(scan.nextLine());
							
							//Size of time slots list of the selected playground. 
							int timeslotArraySize = user.playgroundOwners.get(index).playgrounds.get(PlaygroundIndex).Timeslot.size();

							/*Removing the available time slot of a playground 
							*when a player books a time slot of a playground.*/
							if (playerSlotChoice <= timeslotArraySize && playerSlotChoice > 0) {
								user.playgroundOwners.get(index).playgrounds.get(PlaygroundIndex).Timeslot.remove(playerSlotChoice - 1);
								System.out.println("Booking request has been sent to the playground owner... \n");
							}
						
							/*The playground becomes booked when all available time slots are booked
							 *and will not be displayed to the players*/
							if (user.playgroundOwners.get(index).playgrounds.get(PlaygroundIndex).Timeslot.size() == 0) {
								user.playgroundOwners.get(index).playgrounds.get(PlaygroundIndex).setPlaygroundStatus(true);
							}

						}
						/*Sign Out*/
						else if (ownerChoice == 2) break; 
					}
				} 
				
				/*Email or Password is not correct*/
				else {
					System.out.println("Incorrect Email or Password");
				}

			} else if (mainChoice == 3) { // Exit program.			
				scan.close();
				break;
			}
			else {
				System.out.println("Please, choose one of the following options.");
			}
		}
	}
}