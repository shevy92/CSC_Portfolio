CXX=g++
CFLAGS= -Wall -ggdb
LFLAGS= 
BIN=floattest

CPPFILES=main.cpp

default: $(CPPFILES)
	$(CXX) $(CFLAGS) $(CPPFILES) Prompt.cpp $(LFLAGS) -lncurses -DUSENCURSES -o $(BIN)

noncurses: $(CPPFILES)
	$(CXX) $(CFLAGS) $(CPPFILES) $(LFLAGS) -o $(BIN)





