/*******************************************************************************
 * Prompt.h
 * 
 * CSC210 - Fall 2012
 * Department of Computer Science
 * University of Miami
 * 
 * Author: Andreas Seekircher (aseek@cs.miami.edu)
 *
 * This class provides a command line input with a history.
 * 
 **/

#ifndef PROMPT_H
#define PROMPT_H

#include <string>
#include <vector>

class Prompt
{
  public:
  
    Prompt(const char* promptText);
    ~Prompt();
    
    void getInput(char* buffer, int maxSize);
    
  private:
    std::string promptText;
    std::vector<std::string> history;
};

#endif
