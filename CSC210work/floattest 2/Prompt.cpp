/*******************************************************************************
 * Prompt.cpp
 * 
 * CSC210 - Fall 2012
 * Department of Computer Science
 * University of Miami
 * 
 * Author: Andreas Seekircher (aseek@cs.miami.edu)
 *
 **/

#include "Prompt.h"

#include <cstdio>
#include <curses.h>
#include <term.h>
#include <cstring>

Prompt::Prompt(const char* promptText)
{
  this->promptText = promptText;
  initscr();
  cbreak();
  noecho();
  scrollok(stdscr, TRUE);
  keypad(stdscr, TRUE);
}

Prompt::~Prompt()
{
  echo();
  nocbreak();
  endwin();
}

void Prompt::getInput(char* buffer, int bufferSize)
{
  int row, col;
  getyx(stdscr,row,col);
  mvprintw(row, col, promptText.c_str());

  int cursorPos = 0;
  int inputLength = 0, prevInputLength = 0;
  int key=0;
  buffer[0] = 0;
  history.push_back(buffer);
  unsigned int historyPos = history.size()-1;
  
  while(key != '\n')
  {
    //get key
    key = getch();
  
    //process key
    if(key == KEY_LEFT)
    {
      if(cursorPos > 0)
        cursorPos--;
    }
    else if(key == KEY_RIGHT)
    {
      if(cursorPos < inputLength)
        cursorPos++;
    }
    else if(key == KEY_HOME)
    {
      cursorPos = 0;
    }
    else if(key == KEY_END)
    {
      cursorPos = inputLength;
    }
    else if(key == KEY_UP)
    {
      if(historyPos > 0)
      {
        if(historyPos == history.size()-1)
          history[historyPos] = std::string(buffer);
        historyPos--;
        strcpy(buffer, history[historyPos].c_str());
        inputLength = strlen(buffer);
        cursorPos = inputLength;
      }
    }
    else if(key == KEY_DOWN)
    {
      if(historyPos < history.size()-1)
      {
        historyPos++;
        strcpy(buffer, history[historyPos].c_str());
        inputLength = strlen(buffer);
        cursorPos = inputLength;
      }
    }
    else if(key == KEY_BACKSPACE)
    {
      if(cursorPos > 0)
      {
        for(int i=cursorPos; i<=inputLength; i++)
          buffer[i-1] = buffer[i];
        inputLength--;
        cursorPos--;
      }
    }
    else if(key == KEY_DC)
    {
      if(cursorPos < inputLength)
      {
        for(int i=cursorPos+1; i<=inputLength; i++)
          buffer[i-1] = buffer[i];
        inputLength--;
      }
    }
    else if(key == '\t')
    {  
      // TODO tab completion :)
    }
    else if(key != '\n' && inputLength<bufferSize-1)
    {
      for(int i=inputLength; i>=cursorPos; i--)
        buffer[i+1] = buffer[i];
      inputLength++;
      buffer[cursorPos++] = key;
    }    
    

    //update input line on screen
    int l = promptText.length() + inputLength + 10;
    char format[l];
    sprintf(format, "%%s%%s%%%ds", std::max(0,prevInputLength-inputLength));
    mvprintw(row, col, format, promptText.c_str(), buffer, "");
    if(key != '\n')
      move(row, col + promptText.length() + cursorPos);
    refresh();
    prevInputLength = inputLength;
  }
  
  //history update
  if(history.size()>=2 && strcmp(buffer, history[history.size()-2].c_str())==0)
    history.pop_back();
  history[history.size()-1] = std::string(buffer);
  addstr("\n");
  refresh();
}
