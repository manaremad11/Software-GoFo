import java.util.ArrayList;

class Playground {
    private int ownerID;
    private double pricePerHour;
    private String ownerUserName;
    private String bookedStatus;
    private boolean playgroundBooked; //New playgrounds are 'Not Booked' by default.
    private int playgroundID;
    ArrayList<String> Timeslot = new ArrayList<String>();	//List holds the values of time slots.

    public Playground(ArrayList<String> timeslot, double p, int ownerID, String un , int ID) {
        pricePerHour = p;
        this.ownerID = ownerID;
        ownerUserName = un;
        playgroundID = ID ;
        for(int i =0 ; i < timeslot.size(); i++){	//Copying values of time slots to the list.
            Timeslot.add(timeslot.get(i));
        }
    }

    public void setPlaygroundStatus(boolean p){
        playgroundBooked = p;
    }

    public boolean getPlaygroundStatus(){
        return playgroundBooked;
    }

    public int getPlaygroundID(){
        return playgroundID;
    }

    public ArrayList<String> DisplaySlots(){	//Returns values of
        return Timeslot;
    }

    public String checkPlaygroundStatus() {	//Returns the playground status (Booked or Not Booked).

        if(playgroundBooked) {
            bookedStatus = "Booked";
            return bookedStatus;
        }
        else {
            bookedStatus = "Not Booked";
            return bookedStatus;
        }
    }

    public String toString() {			//Displaying the playground info.
        return "Playground " + playgroundID + "\n"
                + "--------------------------------- \n"
                + "- Playground Owner ID: " + ownerID + "\n"
                + "- Playground Owner Username: " + ownerUserName + "\n"
                + "- Price per Hour: " + pricePerHour + "\n"
                + "- Available Time Slots: " + DisplaySlots() + "\n"
                + "- Playground Status: " + checkPlaygroundStatus();
    }
}