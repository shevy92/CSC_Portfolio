package prog12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class MyGoogle implements Google {

	//MyGoogle Private Variables
	//a Map from the URL for a page to its unique integer ID,
    private Map <String,Integer> myGoogleURLs = new TreeMap <String,Integer>();	
	//the number of references to each page,
    List<Integer> numRefs = new ArrayList<Integer>();
	//the URL of each page.
    List<String> URLs = new ArrayList<String>();
    
    //Map from a word to its ID
    private Map <String,Integer> wordIds = new HashMap <String,Integer>();
    //Sort List of page IDs for each word
    List<List<Integer>> wordPageIds = new ArrayList<List<Integer>>();
	
	@Override
	public void gather(Browser browser, List<String> startingURLs) {
		
		int count = 0;
		Queue <String> urlQueue = new LinkedList <String>();
		
		for (String startingURL: startingURLs) {
			addPage(startingURL);
			urlQueue.add(startingURL);
		}
		
		  while (!urlQueue.isEmpty() && count++ < 100) {
		  	String url = urlQueue.poll();
			System.out.println(url);
			browser.loadPage(url);
			
			List<String> gottenWords = browser.getWords();
			List<String> gottenURLs = browser.getURLs();
			Set <String>  seenSites = new HashSet<String>();
			for (String linkedURL: gottenURLs) {
				
				if (!seenSites.contains(linkedURL)) {
				    if (!myGoogleURLs.containsKey(linkedURL)){
					    addPage(linkedURL);
					    urlQueue.add(linkedURL);
				    }
				    
				    int pageID = myGoogleURLs.get(linkedURL);				
				    numRefs.set(pageID, numRefs.get(pageID)+1);	
				    seenSites.add(linkedURL);
				}
				
			}
			
			int URLId = myGoogleURLs.get(url);
			for (String seenWords: gottenWords) {
				
				if (!wordIds.containsKey(seenWords)){
					addWord(seenWords);
				}
				
				int wordID = wordIds.get(seenWords);
				List <Integer> pageIDs = wordPageIds.get(wordID);
				
				if (pageIDs.size() == 0 || pageIDs.get(pageIDs.size()-1) != URLId) {
					pageIDs.add(URLId);	
				}
			}
			
		  }

	}

	@Override
	public String[] search(List<String> keyWords, int numResults) {
		
		Iterator<Integer>[] pageIDiterators = (Iterator<Integer>[]) new Iterator[keyWords.size()];
		int[] currentPageIDs;
		PriorityQueue<PageID> bestPageIDs;
		
		//Write a loop to initialize the entries of pageIDiterators
		
		/*Implement the loop of search.  While updateSmallest is successful,
          check if the entries of currentPageIDs are all equal.  If so, you
          have a found a match, so save it in the queue. **/
		while (updateSmallest(currentPageIDs, pageIDiterators) == true) {
			boolean ifAllEqual = allEqual(currentPageIDs);
			if (ifAllEqual == true) {
				bestPageIDs.add();
			}
		}
		
		//Create an array of String which will hold the results.
		String[] resultsArray;
		
		/*Unload the priority queue into the string.  But polling the queue
		    gives you the pages in reverse order from least significant to
		    most significant.  What should you do? **/

		//Also, the queue is giving you PageID objects.  How do you get the URLs?
		
		return null;
	}
	
    private boolean updateSmallest (int[] currentPageIDs, Iterator<Integer>[] pageIDiterators) {
    /*Look through currentPageIDs for all the smallest elements.  For
      each smallest element currentPageIDs[i], load the next element
      from pageIDiterators[i].  If pageIDiterators[i] does not have a
      next element, return false.  Otherwise, return true.
      **/
    	for (){
    		
    	}
    	
    	return true;
    }
    
    private boolean allEqual (int[] array) {
    //Check if all elements in an array are equal.
    	int i = 0;
    	while (array[i] <= array.length) {
    		if (array[i] == array[i+1]) {
    			i++;
    		} else {
    			return false;
    		}    		
    	}
    	return true;
    }
	
	private void addPage(String url) {
		
		int pageId = myGoogleURLs.size();
	//Update Map
		myGoogleURLs.put(url, pageId);
	//Update numRefs
		numRefs.add(0);
	//Update URLs
		URLs.add(url);	
	}
	
	private void addWord(String word) {
		
		int wordId = wordIds.size();
		List<Integer> listWordPageIds = new ArrayList<Integer>();
	//Update Word Map
		wordIds.put(word, wordId);
	//Update wordPageId List
		wordPageIds.add(wordId, listWordPageIds);
		
	}


}
