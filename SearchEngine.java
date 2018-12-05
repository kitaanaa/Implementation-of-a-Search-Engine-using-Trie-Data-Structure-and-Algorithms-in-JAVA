package cs600_test1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.io.IOException;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SearchEngine {
	/*
	 * Let us declare the variables
	  */
	/* 
	 * The splitting is used to split the words or string to proper word excluding regular expressions or any other symbols in it
	 */
	private final String splitting = "[[ ]*|[,]*|[)]*|[(]*|[\"]*|[;]*|[-]*|[:]*|[']*|[’]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+";
	/*
	 * I have loaded a file consisting of all the stop words, I down loaded this file 
	 * and instead of creating a new class for stop words and working on it 
	 * I thought it would be even more efficient to create a stop word file and work with it  
	 */
	private final String stopwordsFile = "stopwords.txt";
	/*
	 * A Trie is initialized using the ArrayList collection present in Java 
	 * Although the trie data structure was implemented using hash map as the underlying data structure
	 */
	private Trie<ArrayList<Integer>> trie;
	/*
	 * A web pages array is used to store all the Urls of the web pages in a web site 
	 */
	private String [] webPagesArray;
	
	// Below is the Search Engine constructor 
	public SearchEngine(String websiteName) {
		// The Trie Data Structure is used for mapping words to references (i.e.) mapping words to the urls (w,L) format
		this.trie = new Trie<ArrayList<Integer>>();
		/*
		 *A Hash set is used to store the text information in any file and it does not contain any duplicates    
		 */
		HashSet<String> stopWords = parseToSave(stopwordsFile);
		/*
		 * The web site file which the user mentioned stores the urls of the Web-pages that it contains
		 * I used xml-sitemaps website to create the xml-sitemaps for that particular website (i.e.) to get all the hyper links in that web site
		 */
		/*
		 * Now parsetosave function will be executed after the below statement
		 */
		/*
		 *The results of the parseTosave will be assigned to the temporary HashSet string variable 
		 */
		HashSet<String> temp = parseToSave(websiteName);
		/*
		 * Convert the HashSet to String array and assign them to the web pages array
		 * That is there are many url or links in the web site file all the links are now stored in a string array 
		 */
		
		this.webPagesArray = temp.toArray(new String[0]);
		
		temp = null;
		String text;
		String word;
		String[] words;
		/*
		 *Now I have declared Iterator in java which is used to get the string one by one  from the pages array that is to get each url one by one
		 */
		Iterator<String> iter = null;
		/*
		 *  The Page Index is used to keep track of the index of the urls in a webpages array
		 */
		for (int pageIndex = 0; pageIndex < this.webPagesArray.length; ++pageIndex) {
			try {
				text = webCrawler(this.webPagesArray[pageIndex]);
				text = text.toLowerCase();
				words = text.split(splitting);
				/*
				 * the words are split which is stored in words array 
				 * the words array is then converted to the list and stored as hashset and assigned to temp 
				 */
				temp = new HashSet<String>(Arrays.asList(words));
				/*
				 * The stop words are removed using removeall() function 
				 */
				temp.removeAll(stopWords); // remove all stop words from the page
				/*
				 *Using the iterator the index terms are obtained one by one  
				 */
				iter = temp.iterator();
				while(iter.hasNext()) {
					word = (String) iter.next();
					/*
					 *When we get an index term call the search function in the trie if the trie is empty insert into the trie
					 *  else search
					 */
					ArrayList<Integer> array = this.trie.search(word);
					if (array == null) {
						// insert a new word referencing the current page
						this.trie.insert(word, new ArrayList<Integer>(Arrays.asList(pageIndex)));
					} else {
						array.add(pageIndex);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		JOptionPane.showMessageDialog(null,"The trie has " + this.trie.size + " entries.");
	}
	
	/*
	 * The parseTosave is used to parse and read through a file and put the lines in a hashset so that there will not be any duplicate entries
	 */
	
	/*
	 *The process here is to read the web site file and store all the string or text in the hash and 
	 *if no such file exists simply throw a message to the user
	 */
	private HashSet<String> parseToSave(String filename) {
		HashSet<String> hash = new HashSet<String>();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				hash.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"no such file");
			
			
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null,"sorrry");
		}
		return hash;
	}
	
	
	/*
	 *WebCrawler is used to read the url and get the text and information stored in the url
	 *This is done using Jsoup which will connect to the internet and read the url and its information
	 *the information is stored as text
	 *the text consists of all the words or index terms in that particular url of the webpage of a website 
	 */
	private String webCrawler(String url) throws Exception {	
		Document document = Jsoup.connect(url).get();
		String text = document.body().text();
		return text;
	}
	/*the index term is passed and is searched and the ranking is done here that is the first occurrence in all the urls */
	public String[] search (String[] indexTerm) {
		int[] votes = new int[this.webPagesArray.length];
		ArrayList<Integer> temp = null;
		for (int i = 0; i < indexTerm.length; ++i) {
			temp = this.trie.search(indexTerm[i].toLowerCase());
			if (temp != null) {
				for (int k = 0; k < temp.size(); k++) {
					votes[temp.get(k)]++;
				}
			} else {
				JOptionPane.showMessageDialog( null, "The word <" + indexTerm[i] + "> is not in any file!" );
				
				return null;
			}
		}
		
		/*answers stores the indexes of the webPages*/ 
		ArrayList<String> webPages = new ArrayList<String>();
		for (int p = 0; p < votes.length; ++p) {
			if (votes[p] == indexTerm.length) {
//				System.out.println(p);
				webPages.add(this.webPagesArray[p]);
			}
		}

		return webPages.toArray(new String[0]);
	}
}
