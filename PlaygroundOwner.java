import java.util.ArrayList;

public class PlaygroundOwner extends User {
	
    ArrayList<Playground> playgrounds = new ArrayList<Playground>();	//List that holds the objects of playgrounds.
    
    //Creating new playground owner.
    PlaygroundOwner(String un, String em, String pass, int id) {
        super(un, em, pass, id);
    }

    //Adding new object of playground to the list.
    void addPlayground(Playground p) {
        playgrounds.add(p);
    }
}