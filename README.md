# Implementation-of-a-Search-Engine-using-Trie-Data-Structure-and-Algorithms-in-JAVA
Implemented a simplified Web Search Engine for the pages of a small Web site. A web crawler was implemented to gather information about these pages and store them in a dictionary known as inverted index. Inverted Index was implemented with Trie Data Structure for storing the index terms. When a single keyword or multiple keywords query is given, the search engine gives the pages containing all the given keywords. The occurrence list of each keyword was retrieved using the Trie. In addition to that ranking of pages has been implemented based on relevancy
#  ABOUT
•	I have implemented the simplified Search Engine described in Section 23.5.4 for the pages of a small Web site
•	I have used all the words in the pages of the site as index terms, excluding stop words such as articles, prepositions, and pronouns for which I have used a file to store all the stop words and work on it
•	I have used Tries Data Structure to implement the Inverted Index/ Inverted File
•	I have implemented the tries using the HashMap but there are many other methods like arrays and linked list to implement tries but for our problem hashmap will work more efficiently therefore I have used HashMap
•	I have used Jsoup to work with HTML and to read the web pages in from the internet using Jsoup jar mentioned by our professor (Make sure to install the Jsoup jar before running the program from the below site)	https://jsoup.org/download
# SETUP
•	Download the input files from the input file folder I have attached make sure you store them in the same folder you save your classes (i.e.) your eclipse workspace
Like the below screenshot
•	Run the program and import the Jsoup jar before running the program
# APPROACH
•	The input file is a folder containing website files which has the webpages in it I have used four websites
•	Get the name of the Web site from the user from which the search engine will search for the keywords
•	The name of the web site is passed into the search engine class 
•	After this the parseTosave  function is used to parse and read through the website file and put the lines in a hashset so that there will not be any duplicate entries. The process here is to read the web site file and store all the string or text in the hash and if no such file exists simply throw a message to the user saying no file exists
•	The stopwords file is also passed into this parseTosave function and the stop words file is read and stored in the hash later all the stopwords are removed from the web pages using removeAll() function
•	Now all the text of the website stored in the hashset are returned and it is then converted to string array and are stored in a webpages array that will have all the URLs in the website file like below,
WebpagesArray
•	The next part is I insert the content into the trie this is done with a for loop,
•	The Page Index is used to keep track of the index of the URLs in a webpages array
•	I have read each URL from the webpages array and then passed them into the web crawler to read them 
•	WebCrawler is used to read the URL and get the text and information stored in the URL
•	This is done using Jsoup which will connect to the internet and read the URL and its information & the information is stored as text also that the text consists of all the words or index terms in that particular URL of the webpage of a website
•	Now the results of the web crawler are stored as a string 
•	And the string is split into individual words and stored in words string array
•	And this is converted to a list and with the help of the Iterator in java we can get each word one by one from the structure
•	Now this word is passed into the trie data structure
•	It checks if the trie is empty if so it inserts all the words into the tries 
•	Later after the structure is prepared the user is asked to enter the word to search and it is searched in the web page if it there it is returned if not a message is thrown saying no word is there 
•	When finished, enter or press esc
# TRIES INSERT
•	Two parameters are passed into the insert function one is the string other is the value (i.e.) page index
•	This is the implementation of the inverted file or inverted index storing key value pairs (w, L) where w is the word and L is a collection of references to pages containing the word w 
•	The elements in the dictionary are occurrence list and key(words) in the dictionary are index terms 
•	Each letter is checked if it is present in the trie if not a new node is created, and the character is put into it when it comes to an end the leaf node will have the page index as a value
•	Each time a new node is added the size of the trie grows and increases
# TRIES SEARCH
•	When a word is searched and if the characters of the word are present it returns the url which has the word (i.e.) value or page index else returns null
•	Page index gets returned if exists else null
# RANKING
•	My idea of ranking was to return the web page where the word first occurs (i.e.) when a word is given it returns the page that has the word high number of times and the first occurrence of the it compared to other web pages

