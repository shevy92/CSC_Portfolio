/*******************************************************************************
 * Calculator.h
 * 
 * CSC210 - Fall 2012
 * Department of Computer Science
 * University of Miami
 * 
 * Author: Andreas Seekircher (aseek@cs.miami.edu)
 * Based on InfixToValue.java from a CSC220 assignment from Victor Milenkovic.
 *
 * 
 **/

#ifndef CALCULATOR_H
#define CALCULATOR_H

#include <stack>
#include <cstring>
#include <cstdlib>
#include <cstdio>
#include <cmath>


template<class T> class Stack : public std::stack<T>
{
  public:
    T pop()
    {
      if(this->empty())
        return dummy;
      const T tmp = this->top();
      ((std::stack<T>*)this)->pop();
      return tmp;
    }
    T& top()
    {
      if(this->empty())
        return dummy;
      return ((std::stack<T>*)this)->top();
    }
    const T& top() const
    {
      if(this->empty())
        return dummy;
      return ((std::stack<T>*)this)->top();
    }
    void clear()
    {
      while(!this->empty())
        pop();
    }
  private:
    T dummy;
};


template<typename T>
class Calculator
{
  public:

    T evaluate(const char* expr)
    {
      error = false;
      input = expr;
      index = 0;
      operatorStack.clear();
      numberStack.clear();

      char prevOp = 'e';
      while(!error && !atEndOfInput())
        if(isNumber())
        {
          numberStack.push(getNumber());
          prevOp = 'n';
        }
        else
        {
          char op = getOperator();
          if (!error && op == '-' && prevOp != 'n' && prevOp != ')')
            processOperator('u');
          else if(!error)
            processOperator(op);
          prevOp = op;
        }
            

      while(!error && !operatorStack.empty())
        evaluateOperator();

      if(error)
        return 0;
      return numberStack.pop();
    }
        
  private:
    bool error;
    
    static const char OPERATORS[];
    static const int PRECEDENCE[];
  
    const char* input;
    unsigned int index;
    
    Stack<char> operatorStack;
    Stack<T> numberStack;


    bool atEndOfInput()
    {
      unsigned int l = strlen(input);
      while(index < l
            && (input[index]==' ' || input[index]=='\t' || input[index]=='\n'))
        index++;
      return index == l;
    }

    bool isNumber()
    {
      return (input[index] >= '0' && input[index] <= '9');
    }
    
    T getNumber()
    {
      double d = atof(input + index);  
      while(index < strlen(input)
            && ((input[index] >= '0' && input[index] <= '9') 
                || input[index] == '.'))
        index++;
      return d;
    }
    
    char getOperator()
    {
      char op = input[index++];
      if((error = (strchr(OPERATORS, op) == NULL)))
        printf("Operator %c not recognized.\r\n", op);
      return op;
    }

    void processOperator(char op)
    {
      if(op == '(')
        operatorStack.push(op);
      else if(op == ')')
      {
        while(!operatorStack.empty() && operatorStack.top() != '(' && !error)
          evaluateOperator();
        if(operatorStack.empty())
          printf("Opening parenthesis missing!\n\r");
        operatorStack.pop();
      }
      else if(op == 'u')
        operatorStack.push('u');
      else
      {
        while(!error && !operatorStack.empty()
              && precedence(op) <= precedence(operatorStack.top()))
          evaluateOperator();
        operatorStack.push(op);
      }
    }
    
    int precedence(char op)
    {
      return PRECEDENCE[strchr(OPERATORS, op) - OPERATORS];
    }

    void evaluateOperator()
    {
      char op = operatorStack.pop();
      if(op != 'u')
      {
        T b = numberStack.pop();
        T a = numberStack.pop();
        numberStack.push(evaluateOperator(a, op, b));
      }
      else
        numberStack.push(-numberStack.pop());
    }
    
    double evaluateOperator(T a, char op, T b)
    {
      switch(op)
      {
        case '+':
          return a + b;
        case '-':
          return a - b;
        case '*':
          return a * b;
        case '/':
          return a / b;
        case '^':
          return pow(a, b);
      }
      if(op == '(')
        printf("Closing parenthesis missing!\n\r");
      else
        printf("Unknown operator %c\n\r", op);
      error = true;
      return 0;
    }

};

template<class T>
const char Calculator<T>::OPERATORS[] = "()+-*/u^";
template<class T> 
const int Calculator<T>::PRECEDENCE[] = { -1, -1, 1, 1, 2, 2, 3, 4 };

#endif
