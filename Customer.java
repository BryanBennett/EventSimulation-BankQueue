public class Customer {

    //Field Variables
    private int serviceTimeRequired;
    private int timeOfStartService;
    private int timeOfEndService;
    private int timeAtArrivalQueue;
    private int timeAtDepartureQueue;
    private int timeWaitedQueue;

    //Constructor
    public Customer(int serviceDurationAssignment, int arrivalTime){
        serviceTimeRequired = serviceDurationAssignment;
        timeAtArrivalQueue = arrivalTime;
    }

    //Methods (including get and set methods)
    public int getServiceTimeRequired(){return serviceTimeRequired;}
    public void setTimeOfStartService(int timeOfStart){
        timeOfStartService = timeOfStart;
        timeOfEndService = timeOfStartService + serviceTimeRequired;
        timeAtDepartureQueue = timeOfStart;
        timeWaitedQueue = timeAtDepartureQueue - timeAtArrivalQueue;
    }
    public int getTimeOfEndService(){return timeOfEndService;}
    public int getTimeWaitedQueue(){return timeWaitedQueue;}
}
