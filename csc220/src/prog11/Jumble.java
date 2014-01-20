package prog11;

import java.io.File;
import java.util.Scanner;
import prog02.UserInterface;
import prog02.ConsoleUI;
import prog02.GUI;
import prog02.PhoneDirectory;
import prog02.ArrayBasedPD;
import prog02.SortedPD;
import java.util.TreeMap;

public class Jumble {
  public static boolean realWord (String word) {
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (c < 'a' || c > 'z')
        return false;
    }
    return true;
  }

  static char[] sorted = new char[100];

  public static String sort (String word) {
    if (sorted.length < word.length())
      sorted = new char[word.length()];

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      int j = i;
      while (j > 0 && c < sorted[j-1]) {
        sorted[j] = sorted[j-1];
        j--;
      }
      sorted[j] = c;
    }

    return new String(sorted, 0, word.length());
  }


  public static void main (String[] args) {
    UserInterface ui = new GUI();
    PhoneDirectory pd = new SortedPD();
    OpenHashTable<String, String> map = new OpenHashTable<String,String>();

    Scanner in = null;
    do {
      try {
        in = new Scanner(new File(ui.getInfo("Enter word file.")));
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("Try again.");
      }
    } while (in == null);
	    
    int n = 0;
    while (in.hasNextLine()) {
      String word = in.nextLine();
      if (realWord(word)) {
        String sorted = sort(word);
        pd.addOrChangeEntry(sorted, word);
        if (n % 10000 == 0)
        	System.out.println(word);
        n++;
      }
    }

    while (true) {
      String jumble = ui.getInfo("Enter jumble.");
      if (jumble == null)
        return;
      String sorted = sort(jumble);
      String word = pd.lookupEntry(sorted);
      if (word == null)
        ui.sendMessage("No match for " + jumble);
      else
        ui.sendMessage(jumble + " unjumbled is " + word);
    }
  }
}

        
    

