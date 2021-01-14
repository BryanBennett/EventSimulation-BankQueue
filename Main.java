/*Bryan Bennett
CSC201 Fall 2020
Programming Assignment 1 – Part 1b
September 15, 2020

Decription: This main class creates an array of tellers and a queue of customers. It dynamically assigns customers to tellers
when a teller becomes available, and creates customers based off of the probability provided by the user as a command line argument.
This class tracks the current time in minutes and runs these commands until the clock reaches MAXCLOCK.

Note: Command line arguments must be provided to this program. The first argument is a probability bw 0 and 1,
the second is the number of tellers and the third is the maximum amount of time any customer may require for service.
Send these arguments, separated by spaces, to the main (String[] args)
 */

import java.util.Random;

public class Main {
    public static void main(String[] args){

        //Takes command-line inputs and converts into correct dimensions/types
        double p = Double.parseDouble(args[0]); Double probabilityDynamic = new Double(p);
        int numTellers = Integer.parseInt(args[1]);
        int maxService = Integer.parseInt(args[2]);

        //MAXCLOCK. This can be changed to any constant and affects the number of loops
        int MAXCLOCK = 120;
        //New random object for generating random values in loop
        Random randNum = new Random();

        //Variables to track QLength with each interval, print at the end
        double averageQLength; int maxQlength = 0; int timeOfMaxQLength = 0; int thisQLength; double sumOfQLength = 0;
        //Variables to track waitTime with each interval, print at the end
        int maxWaitTime = 0; double averageWaitTime; int thisWaitTime; double sumWaitTime = 0;

        //Create an array of teller objects, depending on command-line input for numTellers
        Teller[] arrayTellers = new Teller[numTellers];
        for(int i = 0; i<numTellers; i++) {arrayTellers[i] = new Teller(i);}

        //Create queue of type customer for those waiting to be serviced and those already serviced
        Queue<Customer> bankQueue = new Queue<Customer>();
        Queue<Customer> alreadyServicedQueue = new Queue<Customer>();

        //"i" is the current time in minutes. Run clock until MAXCLOCK
        for (int i = 0; i < MAXCLOCK; i++) {

            //Check to see if another customer has arrived based on probability p

            //Generate random double between 0 and 1
            Double randomProbabilityDynamic = new Double(randNum.nextDouble());
            //Compare to "p" from command-line input
            int isGreaterThan = probabilityDynamic.compareTo(randomProbabilityDynamic);
            //"p" is greater than or equal to random probability, customer has arrived
            if((isGreaterThan == 1) || (isGreaterThan == 0)){
                //When customer arrives, assign that customer a length of service needed by generating random number bw 1 and maxService
                int serviceDuration = randNum.nextInt(maxService) + 1;
                //Create customer object, initiate clock and add service duration. Push to Queue
                Customer customer = new Customer(serviceDuration,i);
                bankQueue.push(customer);
                System.out.println("Customer Arriving. This customer will require "+ serviceDuration +" min of service.");
            }
            //random probability > p, so do not create customer
            else if(isGreaterThan == -1){}
            else {System.out.println("THERE IS AN ERROR! CompareTO did not determine if P is >,<, or = to randomProbability");}

            //Navigate Tellers array
            for(int j = 0; j < numTellers; j++){
                //If this this teller just finished with a customer, mark teller as not occupied and reset their clocks
                if(arrayTellers[j].getTimeEndOfService()==i){
                    arrayTellers[j].setStatus(false);
                    arrayTellers[j].setTimeStartOfService(-1,-1);
                }
                //If this teller is available AND there is someone waiting in the queue
                if((arrayTellers[j].getStatus() == false)&&(!bankQueue.isEmpty())){
                    //Mark teller as occupied and start their service clock
                    arrayTellers[j].setStatus(true);
                    arrayTellers[j].setTimeStartOfService(i,bankQueue.peek().getServiceTimeRequired());

                    //Assign start of service time to customer object. This will automatically update timeAtDepartureQueue and timeWaitedQueue
                    bankQueue.peek().setTimeOfStartService(i);

                    //update waitTIME values for tracking maxWaitTime, averageWaitTime
                    thisWaitTime = bankQueue.peek().getTimeWaitedQueue();
                    if(thisWaitTime>maxWaitTime){maxWaitTime = thisWaitTime;}
                    sumWaitTime = sumWaitTime + thisWaitTime;

                    System.out.println("Customer going to teller "+ j +". Customer had to wait for "+ thisWaitTime +" minutes.");
                    //Move customer from bankQueue to alreadyServicedQueue. We could delete them altogether but this helps for record-keeping
                    alreadyServicedQueue.push(bankQueue.pop());
                }
            } //end FOR that navigates tellers

            //These commands are used to track maxQLength, timeOfMaxQLength, and averageQLength
            thisQLength = bankQueue.lengthOfQueue();
            if(thisQLength>maxQlength){maxQlength = thisQLength; timeOfMaxQLength = i;}
            sumOfQLength = sumOfQLength + thisQLength;
        } //END OF FOR LOOP (MAXCLOCK REACHED)

        //Determine averageQLength and averageWaitTime
        averageQLength = sumOfQLength/MAXCLOCK;     //This is the average QLength PER UNIT TIME (including times where there was no one in the QUEUE)
        averageWaitTime = sumWaitTime/(alreadyServicedQueue.lengthOfQueue()); //THIS IS THE AVERAGE WAIT TIME FOR PEOPLE WHO INTERACTED (but may not have finished) WITH A TELLER

        System.out.println("Average queue length: "+ averageQLength);
        System.out.println("Maximum queue length was "+ maxQlength +" and occurred at time "+ timeOfMaxQLength +".");
        System.out.println("Average wait time: "+ averageWaitTime);
        System.out.println("Maximum wait time: "+ maxWaitTime);
    }
}