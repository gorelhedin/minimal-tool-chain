package piglatin;

import java.io.Console;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatin {

	// Strings which are Holy Words, these will never be translated
	// due to their magnificence
	public static String[] HolyWords = {"Amir", "Shahbaz", "Rui", "Patric", "Celine Dion", "WASP", "semla"};


    public static void main(String[] args) {
    	//reading from console
        System.out.println("Input a sentance or word to be piggified:");
        
        Scanner sc = new Scanner(System.in);
        // convert to string
        String sentence = sc.nextLine();

        //sentence = replacePunctuationMarksBy(sentence, "");
        
        String piggified_sentence = pigiffySentence(sentence);
        
        System.out.println("The piggified version of your input is: " + piggified_sentence);

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
            	System.out.println("last character: "+ chPunch  );
            	// remove the last char - expected punctuation at end of word
            	word = removeLastChar(word);
            	System.out.println("word without end char: "+ word );
            	// if still the punctuation exist means the world is crap 
            	if (isContainSpecialChar(word)){
            		// So we are not piggyfiying the word
            		 piggified_word = word + chPunch;
            	}else {
                    // TODO we need to check if this is a bad world or not
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
    
    public static String pigiffyDigit(int number) {

    	String word;

    	switch (number) {

    		case 0:  word = "zero";
                     break;
            case 1:  word = "one";
                     break;
            case 2:  word = "two";
                     break;
            case 3:  word = "three";
                     break;
            case 4:  word = "four";
                     break;
            case 5:  word = "five";
                     break;
            case 6:  word = "six";
                     break;
            case 7:  word = "seven";
                     break;
            case 8:  word = "eight";
                     break;
            case 9:  word = "nine";
                     break;
            default: word = "";
                     break;
        }

    	String pigiffied_word = pigiffy(word);

    	return pigiffied_word;

    }

}
