package prog03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import prog02.DirectoryEntry;
import prog02.GUI;
import prog02.PhoneDirectory;
import prog02.SortedPD;
import prog02.UserInterface;

public class Jumble extends SortedPD {

	public static void main(String[] args) throws IOException {
		PhoneDirectory pd = new SortedPD();
		UserInterface ui = new GUI();
		String fn = "words.txt";
		processCommands(fn, ui, pd);
	}

	private static void processCommands(String fn, UserInterface ui, PhoneDirectory pd) throws IOException {
		addJumbles(fn, pd);
		
		String jumbledWord, name, number;
			 
		while (true) {
		jumbledWord = ui.getInfo("Enter jumbled word: ");
		if (jumbledWord != null) {
			name = alphabetizeWord (jumbledWord);
			number = pd.lookupEntry(name);
			ui.sendMessage("''" + jumbledWord + "'' unscrambled is ''" + number + "''");
	    } else {
			ui.sendMessage("No word has been entered");
			return;
		}
		}
	}

	private static String alphabetizeWord(String jumbledWord) {
		char[] sorted = new char[100];
		String abcWord;
		
		for (int i = 0; i < jumbledWord.length(); i++) {
			char c = jumbledWord.charAt(i);
			sorted[i] = c;
			int count = i;
			for (int i1 = count; i1 > 0 && c < sorted[i1-1] ; i1--) {
				sorted[i1] = sorted[i1-1];
				sorted[i1-1] = c;
			}
		}
		abcWord = new String(sorted, 0, jumbledWord.length());		
		return abcWord;
	}
		
	private static void addJumbles (String fn, PhoneDirectory pd) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File(fn));
		while (sc.hasNextLine()){
	        String name = sc.nextLine();
			
			String abcWord = alphabetizeWord(name);
			
			pd.addOrChangeEntry(abcWord, name);
		} 
	
	}	
	
	
}