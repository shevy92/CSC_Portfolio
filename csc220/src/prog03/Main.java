package prog03;

import prog02.GUI;
import prog02.UserInterface;

public class Main {
	
	public static void main (String[] args) {
    	doExperiments();
    }
    
    private static double previousConstant = 0.0;
    private static UserInterface ui = new GUI();
    
    /** Use this variable to store the result of each call to fib. */
    public static double fibn;

    /** Determine the time in milliseconds it takes to calculate the
        n'th Fibonacci number averaged over ncalls calls.
	@param fib an object that implements the Fib interface
	@param n the index of the Fibonacci number to calculate
	@param ncalls the number of calls to average over
	@return the average time per call
    */
    public static double time (Fib fib, int n, long ncalls) {
	// Get the current time in milliseconds.  This is a static
	// method in the System class.  What type should you use to
	// store the current time?
    	long startTime = System.currentTimeMillis();
	// Calculate the n'th Fibonacci number ncalls times.  Each
	// time store the restul in fibn.
    	for (long i = 0; i < ncalls; i++) 
    		fibn = fib.fib(n);	
    	
	// Get the current time in milliseconds.
    	long endTime = System.currentTimeMillis();
	// Using ncalls and the starting and ending times, calculate
	// the average time per call and return it.  Make sure to use
	// double precision arithmetic for the calculation.
    	return (double) (endTime - startTime) / (double)ncalls;
    }

    /** Determine the time in milliseconds it takes to to calculate
	the n'th Fibonacci number ACCURATE TO THREE SIGNIFICANT FIGURES.
	@param fib an object that implements the Fib interface
	@param n the index of the Fibonacci number to calculate
	@return the time it it takes to compute the n'th Fibonacci number
    */
    public static double time (Fib fib, int n) {
	// Since the clock is only accurate to the millisecond, we
	// need to use a value of ncalls such that the total time is a
	// second.  First we need to figure that value of ncalls.
        long ncalls = 1;
	// Starting with ncalls=1, calculate the total time, which is
	// ncalls times the average time.  Use the method
	// time(fib,n,ncalls) method to get the average time.  Keep
	// multiplying ncalls times 10 until the total time is more
	// than a second.
        double time;
        for (time = time(fib, n, ncalls); (double)ncalls * time <= 1000; time = time(fib, n, ncalls)) 
        	ncalls *= 10;
        
	// Return the average time for that value of ncalls.  As a
	// test, print out ncalls times this average time to make sure
	// it is more than a second.
	return time;
    }

    public static long nn;
    //Write a Method doExperiments in Main. 
    public static void doExperiments(Fib fib) {
    //Repeatedly prompt the user for n using GUI.java and stop
    //when the user enters a blank.

    	do { 
        	String stringN = ui.getInfo("Please enter a value for n : ");
        	if (stringN == null )
        		return;
        	int n = -1;
        	
        	try {
        	    n = Integer.parseInt(stringN);
        	} 
        	catch(Exception e) {
        		ui.sendMessage(n + " is not an integer. This will not work.");
        		continue;
        	}
        	if (n < 0) {
            	ui.sendMessage(n + " is negative. This number will not work.");
                continue;
        	}
        	if (previousConstant != 0.0) {
        		double estimatedTime = previousConstant * fib.o(n);
        		ui.sendMessage("Estimated time for input " + n + " is " + estimatedTime + " Milliseconds");
        		if (estimatedTime > 3600000) {
        			ui.sendMessage("Estimated time is over 1 hour. I will ask you if you really want to run it.");
        			String choices[] = {"yes", "no"};
        			int selection = ui.getCommand(choices);
        			if (selection == 1) 
        				continue;
        			
        		}
        	}
    //For each n, it should figure out a good value of ncalls, get a good
    //time for n, and figure out the new constant.
        
        		double fn = fib.fib(n);
                double newTime = time(fib, n);
                previousConstant = newTime / fib.o(n);
                ui.sendMessage("fib ''" + n + "'' = ''" + fn + "'' in " + newTime + " milliseconds" );
        	} while(true); 
    	
    }

    //If this is not the first n, it should use the constant from the
    //previous n to estimate the time for the current n.  It should display
    //the value of n, fib(n), estimated time, actual time, and new constant.
    	 public static void doExperiments() {
    	        String choices[] = {"ExponentialFib", "LinearFib", "LogFib", "ConstantFib"};
    	        int choice = ui.getCommand(choices);
    	        Fib fib = new ExponentialFib();
    	        switch(choice) {
    	        case 0: 
    	            doExperiments(((Fib) (new ExponentialFib())));
    	            break;

    	        case 1: 
    	            doExperiments(((Fib) (new LinearFib())));
    	            break;

    	        case 2: 
    	            doExperiments(((Fib) (new LogFib())));
    	            break;

    	        case 3:
    	            doExperiments(((Fib) (new ConstantFib())));
    	            break;
    	        }
    	    }
    
}
