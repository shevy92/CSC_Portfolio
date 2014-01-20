/*******************************************************************************
 * floattest
 * 
 * CSC210 - Fall 2012
 * Department of Computer Science
 * University of Miami
 * 
 * Author: Andreas Seekircher (aseek@cs.miami.edu)
 * 
 * Simple tool for showing surprising effects caused by rounding error in
 * floating point arithmetic.
 * 
 * 
 * There are different methods that can be called in the main function at the
 * end of the file.
 * 
 * runIteration():
 *   Iterates two MAPs and prints out all bits of the current x values in each
 *   iteration.
 * userInput():
 *   Allows the user to type in an expression and prints out the result.
 * testRoundingModes():
 *   Contains some tests that set different rounding modes.
 * 
 **/

#include <cstdio>
#include <fenv.h>

#ifdef USENCURSES
  #include <curses.h>
  #define printf printw
  #include "Prompt.h"
#endif

#include "Calculator.h"

/*******************************************************************************
 * Functions for binary printing of variables:
 **/

// print one bit 
void printBit(void *p, int bit)
{
  printf("%c", *((char*)p + bit / 8) & (1 << (bit % 8)) ? '1' : '0');
}

// print n bits starting from the "froms"th bit relative to address p
void printBits(void *p, int from, int n)
{
  for(int i=from; i<from+n; i++)
    printBit(p, i);
}

// print n bits in a reversed order
void printBitsReverse(void *p, int from, int n)
{
  for(int i=from+n-1; i>=from; i--)
    printBit(p, i);
}

// print unknown types just as binary data 
template<typename T> void printBinary(T &x)
{
  printBits(&x, 0, sizeof(T)*8);
}

// IEEE 754-2008 single-precision binary floating-point format: binary32
void printBinary(float &x)
{
  long exponent = (*((long*)((char*)&x+2))>>7) & ((1<<8)-1);
  printf(" M ");  printBitsReverse(&x,  0, 23); // mantissa
  printf(" E ");  printBitsReverse(&x, 23,  8); // exponent
  printf(" (%ld) ", exponent - 127);
  printf(" S ");  printBitsReverse(&x, 31,  1); // sign
}

// IEEE 754-2008 double-precision binary floating-point format: binary64
void printBinary(double &x)
{
  long exponent = (*((long*)((char*)&x+6))>>4) & ((1<<11)-1);
  printf(" M ");  printBitsReverse(&x,  0, 52); // mantissa
  printf(" E ");  printBitsReverse(&x, 52, 11); // exponent
  printf(" (%ld) ", exponent - 1023);
  printf(" S ");  printBitsReverse(&x, 63,  1); // sign
}

/*******************************************************************************
 * MAPs and the iteration function:
 **/

typedef double Value;  // change the used variable type here


// print t and one number
void output(int t, Value x)
{
  printf("t=%d\n ", t);  
  printBinary(x);
  printf("\n ");  
}
// print t and two numbers
void output(int t, Value x1, Value x2)
{
  printf("t=%d\n ", t);  
  printBinary(x1);
  printf("\n ");  
  printBinary(x2);
  printf("\n");
}


// -- MAPs
Value logisticMAP(Value a, Value x)
{
  return a*x*(1.0-x);
}
Value logisticMAP3(Value a, Value x)
{
  return a*x-a*x*x;
}
Value tentMAP(Value a, Value x)
{
  if(x <= 0.5)
    return a*x;
  return a*(1.0-x) ;
}


// -- iterate MAPs
void runIteration()
{
  //parameter
  Value a = 2.9;

  //initial condition
  Value x1 = 0.2;
  //Value x2 = 0.2;

  //iteration
  for(int t=0; t<=5; t++)  
  {/*
    //print numbers
    output(t, x1, x2);
    //update x values
    x1 = logisticMAP(a, x1);
    x2 = logisticMAP3(a, x2);    
    */
    output(t, x1);
    x1 = tentMAP(a, x1);    
  }
}


/*******************************************************************************
 * Some test methods and the main function
 **/

void testRoundingModes()
{
  /*
  FE_TONEAREST
    Round to nearest.
  FE_UPWARD
    Round toward +inf
  FE_DOWNWARD
    Round toward -inf
  FE_TOWARDZERO
    Round toward zero. 
  */
  int mode[4] = {FE_TONEAREST, FE_UPWARD, FE_DOWNWARD, FE_TOWARDZERO};
  const char* modeStr[4] = {"FE_TONEAREST", "FE_UPWARD",
                            "FE_DOWNWARD", "FE_TOWARDZERO"};


  for(int m=0; m<4; m++) 
  {
    fesetround(mode[m]);  
    printf("%s:\n", modeStr[m]);

    printf("0.1 =");
    double x = 0.1;
    printBinary(x);
    printf("\n");
    
    printf("devisions by 2\n");
    double test = 3;
    for(int i=0; i<1024+48; i++)
      test *= 0.5;
    for(int i=0; i<5; i++)
    {
      test *= 0.5;
      printBinary(test);
      printf("\n");
    }
  }
}


void userInput()
{
  #ifdef USENCURSES
    Prompt prompt("> ");
  #endif
  Calculator<double> calc;
  bool stop = false;
  char input[256];

  while(!stop)
  {
    #ifdef USENCURSES
      prompt.getInput(input, 256);
    #else
      printf("> ");
      fgets(input, 255, stdin);
      input[strlen(input)-1] = 0;
    #endif
  
    //process input
    if(strlen(input) == 0)
        stop = true;
    else
    {  
      double result = calc.evaluate(input);
      printf("ans = %f\n", result);
      printBinary(result);
      printf("\n");
    }
  }
}

int main()
{
  
  //runIteration();
  //testRoundingModes();
  userInput();
      
  return 0;
}






