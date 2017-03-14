package piglatin;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import piglatin.PigLatin;

public class PigNumberClass {

	
    
    public static String pigiffyDigit(int number) {

    	String word;

        // @Shahbaz, here I call a method. From Rui
        // pigNum.oink();

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

    	String pigiffied_word = PigLatin.pigiffy(word);

    	return pigiffied_word;

    }
    
	  private static final String[] tensNames = {
	    "",
	    " ten",
	    " twenty",
	    " thirty",
	    " forty",
	    " fifty",
	    " sixty",
	    " seventy",
	    " eighty",
	    " ninety"
	  };

	  private static final String[] numNames = {
	    "",
	    " one",
	    " two",
	    " three",
	    " four",
	    " five",
	    " six",
	    " seven",
	    " eight",
	    " nine",
	    " ten",
	    " eleven",
	    " twelve",
	    " thirteen",
	    " fourteen",
	    " fifteen",
	    " sixteen",
	    " seventeen",
	    " eighteen",
	    " nineteen"
	  };

	  //private EnglishNumberToWords() {}

	  private static String convertLessThanOneThousand(int number) {
	    String soFar;

	    if (number % 100 < 20){
	      soFar = numNames[number % 100];
	      number /= 100;
	    }
	    else {
	      soFar = numNames[number % 10];
	      number /= 10;

	      soFar = tensNames[number % 10] + soFar;
	      number /= 10;
	    }
	    if (number == 0) return soFar;
	    return numNames[number] + " hundred" + soFar;
	  }


	  public static String convertPosIntToWords(long number) {
	    // 0 to 999 999 999 999
	    if (number == 0) { return "zero"; }

	    String snumber = Long.toString(number);

	    // pad with "0"
	    String mask = "000000000000";
	    DecimalFormat df = new DecimalFormat(mask);
	    snumber = df.format(number);

	    // XXXnnnnnnnnn
	    int billions = Integer.parseInt(snumber.substring(0,3));
	    // nnnXXXnnnnnn
	    int millions  = Integer.parseInt(snumber.substring(3,6));
	    // nnnnnnXXXnnn
	    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
	    // nnnnnnnnnXXX
	    int thousands = Integer.parseInt(snumber.substring(9,12));

	    String tradBillions;
	    switch (billions) {
	    case 0:
	      tradBillions = "";
	      break;
	    case 1 :
	      tradBillions = convertLessThanOneThousand(billions)
	      + " billion ";
	      break;
	    default :
	      tradBillions = convertLessThanOneThousand(billions)
	      + " billion ";
	    }
	    String result =  tradBillions;

	    String tradMillions;
	    switch (millions) {
	    case 0:
	      tradMillions = "";
	      break;
	    case 1 :
	      tradMillions = convertLessThanOneThousand(millions)
	         + " million ";
	      break;
	    default :
	      tradMillions = convertLessThanOneThousand(millions)
	         + " million ";
	    }
	    result =  result + tradMillions;

	    String tradHundredThousands;
	    switch (hundredThousands) {
	    case 0:
	      tradHundredThousands = "";
	      break;
	    case 1 :
	      tradHundredThousands = "one thousand ";
	      break;
	    default :
	      tradHundredThousands = convertLessThanOneThousand(hundredThousands)
	         + " thousand ";
	    }
	    result =  result + tradHundredThousands;

	    String tradThousand;
	    tradThousand = convertLessThanOneThousand(thousands);
	    result =  result + tradThousand;

	    // remove extra spaces!
	    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	  }

	  private static boolean isInteger(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s);
	 
	         // s is a valid integer
	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         // s is not an integer
	      }
	 
	      return isValidInteger;
	   }
	  
	  public static String replacePosIntInSentenceWithWords(String sentence){
		  StringTokenizer t = new StringTokenizer(sentence);
		  String outputSentence = "";
		  String word, outputWord;
		  int num;
		  while(t.hasMoreTokens()){
	            word = t.nextToken();                        
	            if (isInteger(word)) {
	            	num = Integer.parseInt(word);
	            	if (num >= 0){
	            		outputWord = convertPosIntToWords(num);
	            	}
	            	else{
	            		outputWord = word;
	            	}
	            	 
	            }
	            else{
	            	outputWord = word;
	            }
	            if(t.hasMoreTokens()){
	            	outputSentence = outputSentence + outputWord + " ";
	            }
	            else{
	            	outputSentence = outputSentence + outputWord;
	            }
	            
	        }
		  return outputSentence;
	  }

}
