package prog05;

import java.util.Stack;
import prog02.UserInterface;
import prog02.GUI;

public class InfixToValue {
    static final String OPERATORS = "()+-*/u^";
    static final int[] PRECEDENCE = { -1, -1, 1, 1, 2, 2, 3, 4 };
    String input = null;
    int index = 0;
    Stack<Character> operatorStack = new Stack<Character>();
    Stack<Double> numberStack = new Stack<Double>();

    boolean atEndOfInput () {
	while (index < input.length() &&
	       Character.isWhitespace(input.charAt(index)))
	    index++;
	return index == input.length();
    }

    boolean isNumber () {
	return Character.isDigit(input.charAt(index));
    }

    double getNumber () {
	int index2 = index;
	while (index2 < input.length() &&
	       (Character.isDigit(input.charAt(index2)) ||
		input.charAt(index2) == '.'))
	    index2++;
	double d = 0;
	try {
	    d = Double.parseDouble(input.substring(index, index2));
	} catch (Exception e) {
	    System.out.println(e);
	}
	index = index2;
	return d;
    }

    char getOperator () {
	char op = input.charAt(index++);
	if (OPERATORS.indexOf(op) == -1)
	    System.out.println("Operator " + op + " not recognized.");
	return op;
    }

double evaluate (String expr) {
	input = expr;
	index = 0;
	
	while (!operatorStack.empty()) operatorStack.pop();
	while (!numberStack.empty()) numberStack.pop();

	boolean ifUnaryMinus = true;
	
	while (!atEndOfInput())
	    if (isNumber()) {
		    numberStack.push(getNumber());
	        ifUnaryMinus = false;
	    } else {
	    	char op = getOperator();
	    	if (op == '-' && ifUnaryMinus == true){
	    		op = 'u';
	    		operatorStack.push(op);
	    	}else{
	    		processOperator(op);
	    	}
	    	ifUnaryMinus = true;
	    	if (op == ')'){
	    		ifUnaryMinus = false;
	    	}
	    }


        while (!operatorStack.empty()) {
            evaluateOperator();
        }
       
        double value = numberStack.peek();
        return value;
}


    public static void main (String[] args) {
      UserInterface ui = new GUI();
	InfixToValue converter = new InfixToValue();

	while (true) {
          String line = ui.getInfo("Enter arithmetic expression or cancel.");
          if (line == null)
            return;

          try {
            double result = converter.evaluate(line);
            ui.sendMessage(line + " = " + result);
          } catch (Exception e) {
            System.out.println(e);
          }
	}
    }
  protected int precedence (char op) {
	  for (int index = 0; index < OPERATORS.length(); index++) {
  		if (op == (OPERATORS.charAt(index))) {
  			return PRECEDENCE[index];
  		}
  	}
  	return 0;
  }
  protected double evaluateOperator (double a, char op, double b) {
	  double result = 0;
	  switch (op) {
      case '+':
        result = a + b;
        break;
      case '-':
        result = a - b;
        break;
      case '/':
        result = a / b;
        break;
      case '*':
        result = a * b;
        break;
      case '^':
    	result = Math.pow(a, b);
    	break;
    }
    return result;
  }
  protected void evaluateOperator () {
	  	  
	  char op = operatorStack.pop();
	  if (op == 'u') {
		  double a = (numberStack.pop() * -1);
		  numberStack.push(a);
	  } else {
	      double a = numberStack.pop();
	      double b = numberStack.pop();
	  
	      double result = evaluateOperator(b, op, a);
	      numberStack.push(result);
	  }
  }
  protected void processOperator (char op) {
	  if (op == '('){
  		operatorStack.push(op);
  	}else if (op == ')'){
  		while (operatorStack.peek() != '('){
  			evaluateOperator();
  		}
  		operatorStack.pop();
  	}else{
  		if (!operatorStack.empty()){
      		int operatorNum1 = precedence(op);
      		int operatorNum2 = precedence(operatorStack.peek());
      		
      		while (!operatorStack.empty() && operatorNum2 >= operatorNum1){
      			evaluateOperator();
      			if (!operatorStack.empty()){
      				operatorNum2 = precedence(operatorStack.peek());
      			}
      			
      		}
      	}
  		operatorStack.push(op);
  	}
  	
  }


}