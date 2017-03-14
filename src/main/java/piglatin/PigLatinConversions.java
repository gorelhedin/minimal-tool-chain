package piglatin;

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
import java.io.FileReader;


import piglatin.PigLatin;


public class PigLatinConversions {


	// Strings which are Holy Words, these will never be translated
	// due to their magnificence
	public static String[] HolyWords = {"Amir", "Shahbaz", "Rui", "Patric", "Celine Dion", "WASP", "semla", "WASP"};
    
    // The words contained in the Bad Words file must not be seen by innocent eyes.
	public static String badWordsFilename = "./src/BADWORDS.txt";
	public static ArrayList<String> badWords = readFromFile();


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
            	// remove the last char - expected punctuation at end of word
            	word = removeLastChar(word);
            	
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

            //constrauct the sentence word by word
            piggified_sentence = piggified_sentence + piggified_word + " ";
        }
    	
    	return piggified_sentence;
    }
    
    
    public static String pigiffy(String word) {
        String vowels = "aeiouAEIOU";
		String piggified = word;
		
		if (isBadWord(word)){
			//TODO Replace with random signs !!!!
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
        boolean b = m.find();

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
                return true;
            }
        }

        return false;
    }

        
    //This function read the file and return list of word on that file 
    public static ArrayList<String> readFromFile (){
    	
    	ArrayList<String> wordsList = new ArrayList<String>();
    	
    	try {

            BufferedReader reader=new BufferedReader(new FileReader(badWordsFilename));
            String line=null;
                while((line=reader.readLine())!=null){
                	wordsList.add(line);
                }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return wordsList;

    }

    	
}
