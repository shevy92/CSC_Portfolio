package prog03;

public class ConstantFib extends PowerFib {

	//Calls Math.pow instead of the private pow
	protected double pow (double x, int n) {
		return Math.pow (x,n);
	}	
	//Test its running time
	public double o (int n) {
        return Math.log(n);
    }
}
