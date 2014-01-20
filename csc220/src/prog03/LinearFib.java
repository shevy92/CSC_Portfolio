package prog03;

public class LinearFib implements Fib {

	@Override
	public double fib(int n) {
		double a;
		double b;
	//Implement LinearFib which computes fib(n) in O(n) time.
	//Set a=0 and b=1.	
		a = 0;
		b = 1;
	//Make a=1 and b=2, a=2 and b=3, etc.
		int index;
		for (index = 0; index < n; index++) {
			double nextNumber = b;
			b += a;
			a = nextNumber;
		}
		return a;
	}

	@Override
	public double o(int n) {
	//Return Value
		return (double)n;
	}

}
