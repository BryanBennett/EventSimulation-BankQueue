public class Teller {

    //Field Variables
    private int tellerNum; //The tenth teller would have tellerNum = 9. arrayTellers[0] would have tellerNum = 0
    private int timeStartOfService;
    private int timeEndOfService;
    private boolean isOccupied;

    //Constructor
    public Teller(int tellerNumber){
        tellerNum = tellerNumber; //The tenth teller would have tellerNum = 9 (and is the 9th position in ArrayTellers)
        isOccupied = false;
        timeStartOfService = -1;
        timeEndOfService = -1;
    }

    //Methods
    public boolean getStatus(){return isOccupied;}
    public void setStatus(boolean occupationAssignment){isOccupied = occupationAssignment;}
    public int getTimeEndOfService(){return timeEndOfService;}
    public void setTimeStartOfService(int serviceStartTime, int serviceDuration){
        timeStartOfService = serviceStartTime;
        timeEndOfService = timeStartOfService + serviceDuration;
    }
}
