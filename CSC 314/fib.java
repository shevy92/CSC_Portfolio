import java.util.Scanner;
//=============================================================================
public class fib {
//-----------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);
    private static final int MAX_NUMBERS = 10;
    private static final int ZERO = 0;
    private static final int ONE = 1;

//-----------------------------------------------------------------------------
    public static void main(String[] args) {

        boolean finished;
        boolean fibonacci;
        boolean prime;
        int numberOfNumbers;
        int index;
        int[] numbers = new int[MAX_NUMBERS];
        
        System.out.println();
        System.out.print("Please enter numbers (0 to stop) : ");
        numberOfNumbers = ZERO;
        finished = false;
        
        do {
          numbers[numberOfNumbers] = keyboard.nextInt();
          if (numbers[numberOfNumbers] == ZERO) {
              finished= true;
          } else if (numbers[numberOfNumbers] >= ZERO &&
numbers[numberOfNumbers] <= numbers[numberOfNumbers]) {
              numberOfNumbers++;
          } else {
              System.out.println(
              "We ignore negatives, please use positive numbers...");
          }
        } while (!finished && numberOfNumbers < MAX_NUMBERS);
		
		System.out.println();

//---- for loop to test Fibonacci or prime
		for (index = ZERO; index < numberOfNumbers; index++) { 
		  int currentNum = numbers[index];
		  fibonacci = computeFibonacci(currentNum);
		  prime = computePrime(currentNum);
		  
		  if (fibonacci == true && prime == true){
		    System.out.println(currentNum + " is Fibonacci and is prime");
		  } else if (fibonacci == false && prime == false) {
		    System.out.println(currentNum + 
		      " is not Fibonacci and is not prime");
		  } else if (fibonacci == true && prime == false) {
	        System.out.println(currentNum + " is Fibonacci and is not prime");
	      } else if (fibonacci == false && prime == true) {
	        System.out.println(currentNum + " is not Fibonacci and is prime");
	      }
	    }
		System.out.println();

    }
//-----------------------------------------------------------------------------
    static boolean computePrime(int number) {
      boolean isPrime = false;
      int i = (int) Math.ceil(Math.sqrt(number));
      while (i > ONE) {
              if ((number != i) && (number % i == ZERO)) {
                      isPrime = false;
                      break;
              } else if (!isPrime)
                      isPrime = true;
              --i;
      }
      return isPrime;
    }
//-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------
    static boolean computeFibonacci(int value) {
        
      boolean isFibonacci = false;
      long current = ONE;
      long previous = ZERO;
      long next;
      
      while (previous <= current) {
          next = current + previous;
          if (value == current) {
              isFibonacci = true;
              break;
          } else if (next > value) {
              isFibonacci = false;
              break;
          } else {
              previous = current;
              current = next;
          }   
      
      }
      
      return isFibonacci;
    
    }
//-----------------------------------------------------------------------------

}
//=============================================================================
