package prog12;

import java.util.List;

public interface Browser {
	/**Determines whether or not loading a page with the given URL was successful or not*/
    boolean loadPage (String url);
    /**Create a list of the words on the webpage*/
    List<String> getWords ();
    /**Create a list of the URL's on the webpage*/
    List<String> getURLs ();
}

