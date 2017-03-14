package piglatin;

import piglatin.PigClass;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PigLatin {

    // @Shahbaz, here I declare it. From Rui
    // public static PigNumberClass pigNum;

	// Strings which are Holy Words, these will never be translated
	// due to their magnificence
	public static String[] HolyWords = {"Amir", "Shahbaz", "Rui", "Patric", "Celine Dion", "WASP", "semla", "WASP"};
    
    // These words must never be seen by innocent eyes
    //public static String[] badWords = {"fuck", "shit", "javla"};
	
	public static InputStream badWordIn = PigLatin.class.getResourceAsStream("BADWORDS.txt");
	public static ArrayList<String> badWords;

	public static void init() {
    	//Load a bad words from File 
    	badWords = new ArrayList<String>();
    	badWords = readFromFile(badWordIn);
	}
	
	
	public static void main(String[] args) {    	
    	
		//initialization
		init();
		
		// reading from console
        System.out.println("Plesae type a sentence or word to be piggified:");
       
        Scanner sc = new Scanner(System.in);
        // convert to string
        String sentence = sc.nextLine();
        String newSentence = PigNumberClass.replacePosIntInSentenceWithWords(sentence);
        String piggified_sentence = pigiffySentence(newSentence);
        
        System.out.println("Yes, The piggified version of your input is:" + piggified_sentence);

    }

    public static String replacePunctuationMarksBy(String sentence, String replacer) {

    	String[] punctuationMarks = {",", ".", "?", "!"};

    	String correctedString = sentence;

    	for (int i=0;i<punctuationMarks.length;i++) {
    		
    		correctedString = correctedString.replace(punctuationMarks[i], replacer);

		}

		return correctedString;

    }

    
    public static String pigiffySentence(String sentence) {

    	
    	//TODO rewrite it using split 
        //StringTokenizer t = new StringTokenizer(sentence, " \t\n\r\f,.:;?![]'");
        StringTokenizer t = new StringTokenizer(sentence);

        String word ="";
        String piggified_sentence = "";
        String chPunch = null; // holder for punctuation char
        String piggified_word = "";
        while(t.hasMoreTokens())
        {
            word = t.nextToken();  
        
            // if contain special character
            if (isContainSpecialChar(word)) {
            	//Split a word to punctuation and new word
            	chPunch = word.substring(word.length() - 1);
            	//System.out.println("last character: "+ chPunch  );
            	// remove the last char - expected punctuation at end of word
            	word = removeLastChar(word);
            	//System.out.println("word without end char: "+ word );
            	
            	// if still the punctuation exist means the world is crap 
            	if (isContainSpecialChar(word)){
            		// So we are not piggyfiying the word
            		 piggified_word = word + chPunch;
            	}else {
                       piggified_word = pigiffy(word) + chPunch;
            		}
            }else{
            	piggified_word = pigiffy(word);
            };

            // for debug to see how the sentence is splitted 
    		//System.out.print("("+ word + ") ");
            //constrauct the sentence world by world
            piggified_sentence = piggified_sentence + piggified_word + " ";
        }
    	
    	return piggified_sentence;
    }
    
    
    public static String pigiffy(String word) {
        String vowels = "aeiouAEIOU";
		String piggified = word;
		
		if (isBadWord(word)){
			//TODO Replace with random signe !!!!
			piggified = "BIIIIP";
			return piggified;
		}

		if (vowels.contains(""+word.charAt(0))) {
			piggified = word+"way";
			return piggified;
		}
		else {
			for (int i = 1; i < word.length(); i++) {
		    	if (vowels.contains(""+word.charAt(i))) {
		        	String prefix = word.substring(0, i);
		        	String suffix = word.substring(i);
		        	piggified = suffix + prefix + "ay";
		        	break;
		    	}
			}
		}
    	return piggified;
    }

    public static boolean isHolyWord(String word){

    	for (int i=0;i<HolyWords.length;i++) {
    		boolean isHolyWord = HolyWords[i].equalsIgnoreCase( word );
			if(isHolyWord) {
				return true;
			}
		}

    	return false;

    }
    
    
    // This will check if the string contain any special character
    public static boolean isContainSpecialChar(String s) {
        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            System.exit(0);;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
       // boolean b = m.matches();
        boolean b = m.find();
        //For testing 
        //if (b == true)
           //System.out.println("There is a special character in my string ");
       // else
            //System.out.println("There is no special char.");
        return b;
    }

    // This will remove last char from string
    public static String removeLastChar (String str) {
        if (str != null && str.length() > 0) {
          str = str.substring(0, str.length()-1);
        }
        return str;
    }
    
    
    
    
    		

        public static boolean isBadWord (String st){

            for (int i=0;i<badWords.size();i++) {

                if ( st.toLowerCase().contains(badWords.get(i).toLowerCase())){
                    // We don't want any traces of bad words :(
                	System.out.println(" in method isBadWord - bad word is detected");
                    return true;
                }
            }

            return false;
        }

        
        //This function read the file and return list of word on that file 
        public static ArrayList<String> readFromFile (InputStream in){
        	
        	ArrayList<String> wordsList = new ArrayList<String>();
        	
        	try {
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                String line=null;
                    while((line=reader.readLine())!=null){
                        //System.out.println(line);
                    	wordsList.add(line);
                    }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			return wordsList;

        }

    	
}
