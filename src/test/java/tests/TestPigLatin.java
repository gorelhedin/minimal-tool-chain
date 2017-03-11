package tests;
import static org.junit.Assert.*;
import org.junit.Test;
import piglatin.*;
//import Num2Words.*;

public class TestPigLatin {

	@Test
	public void pigiffyReturnsNonEmptyString() {
		//  This test simply checks if the PigLatin is returning
		// a non empty string when we ask it to piggify something.
		PigLatin pigLatin = new PigLatin();

		String word = "string";
		String pigWord = pigLatin.pigiffy( word );

		if ( pigWord.length() < 1 ){

			fail("The returned String is empty.");

		}

	}

	@Test
	public void checkWordsBeginWithVowel() {
		//  This test checks if words that begins with vowel are translated correctly.
		PigLatin pigLatin = new PigLatin();

		String[] inputWords = {"a", "iphone", "."};
		String[] pigWords = {"away", "iphoneway", "."};

		for (int i=0;i<inputWords.length;i++) {
			if(!(pigWords[i].equals(pigLatin.pigiffy(inputWords[i])))) {
				System.out.println(pigWords[i]);
				System.out.println(pigLatin.pigiffy(inputWords[i]));
				fail("Test with words beginning with vowels failed.");
			}
		}
	}

	@Test
	public void checkWordsBeginConsonant() {
		//  This test checks if words that begins with consonants are translated correctly.
		PigLatin pigLatin = new PigLatin();

		String[] inputWords = {"cat", "phone", "."};
		String[] pigWords = {"atcay", "onephay", "."};

		for (int i=0;i<inputWords.length;i++) {
			if(!(pigWords[i].equals(pigLatin.pigiffy(inputWords[i])))) {
				//System.out.println(pigWords[i]);
				//System.out.println(pigLatin.pigiffy(inputWords[i]));
				fail("Test with words beginning with consonants failed.");
			}
		}
	}

	@Test
	public void checkNumberTranslation() {
		//  This test checks if some numbers are correctly translated
		PigNumberClass pigNum = new PigNumberClass();

		int[] inputNumbers = {0, 1, 7, 8, 9};
		String[] pigWords = {"erozay", "oneway", "evensay", "eightway", "inenay"};

		for (int i=0;i<inputNumbers.length;i++) {
			if(!(pigWords[i].equals(pigNum.pigiffyDigit(inputNumbers[i])))) {
				System.out.println(pigWords[i]);
				System.out.println(pigNum.pigiffyDigit(inputNumbers[i]));
				fail("Test of number translation failed.");
			}
		}
	}

	@Test
	public void checkHolyWords() {
		// This test checks if some holy words are detected, and if some non-holy
		// words are marked as such
		PigLatin pigLatin = new PigLatin();

		int[] inputNumbers = {0, 1, 7, 8, 9};
		String[] HolyWords = pigLatin.HolyWords;
		String[] unholyWords = {"pokemon", "chair", "brexit"};

		for (int i=0;i<HolyWords.length;i++) {
			if(!(pigLatin.isHolyWord(HolyWords[i]))) {
				System.out.println(HolyWords[i]);
				System.out.println("Was classified as being a non holy word.");
				fail("Test of Holy Words check failed.");
			}
		}

		for (int i=0;i<unholyWords.length;i++) {
			if(pigLatin.isHolyWord(unholyWords[i])) {
				System.out.println(unholyWords[i]);
				System.out.println("Was classified as being a holy word.");
				fail("Test of Holy Words check failed.");
			}
		}
	}

	@Test
	public void replacePunctuationMarksTest() {
		// 
		String inputString = "Sometimes commas, can be as slow as full stop. Is someone excited for the weekend? Yes!";	
		String replacerString = "";
		String correctString = "Sometimes commas can be as slow as full stop Is someone excited for the weekend Yes";

		PigLatin pigLatin = new PigLatin();
		String outputString = pigLatin.replacePunctuationMarksBy( inputString, replacerString );

		if(!(outputString.equals(correctString))) {
			System.out.println(outputString);
			System.out.println(correctString);
			fail("Test for punctuation mark replacement failed.");
		}

		/*replacerString = " ";
		correctString = "Sometimes commas  can be as slow as full stop  Is someone excited for the weekend  Yes ";		
		outputString = pigLatin.replacePunctuationMarksBy( inputString, replacerString );

		if(!(outputString.equals(correctString))) {
			System.out.println(outputString);
			System.out.println(correctString);
			fail("Test for punctuation mark replacement failed.");
		}*/

	}
 
	
	@Test
	public void checkLatCharRemove() {
		// This test checks if the last character is been removed from word
		
		String inputString = "This";
		String expectedResult = "Thi";
	
		PigLatin pigLatin = new PigLatin();
		String outputString = pigLatin.removeLastChar(inputString);
		
		if (!(outputString.equals(expectedResult))){
			fail("Messed up on removing");
		}
			
	}
	


	@Test
	public void isContainSpecialCharTest() {
		// This test checks if we aer not wrongly detect special character
		
		String inputString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ0123456789";
		PigLatin pigLatin = new PigLatin();		
		if (pigLatin.isContainSpecialChar(inputString)){
			fail("Messed up on dtecting a special character");
		}
			
	}


	@Test
	public void isContainSpecialCharTest2() {
		// This test checks if we are not missing a special character
		
		String inputString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ0123456789.";
		PigLatin pigLatin = new PigLatin();		
		if (!(pigLatin.isContainSpecialChar(inputString))){
			fail("Messed up on dtecting a special character");
		}
			
	}
	
	@Test
	public void isBadWordTest() {
		// This test checks if we detect bad word
		
		String inputString = "Fuck";
		PigLatin pigLatin = new PigLatin();		
		if (!(pigLatin.isBadWord(inputString))){
			fail("we can not detect a bad word");
		}
	}
	
	@Test
	public void TestreplacePosIntInSentenceWithWords() {
		// This test checks if we detect bad word
		
		String inputString = "Test number 123 45.6 -2 -2.5 0.0045. That's it!";
		String correctString = 	"Test number one hundred twenty three 45.6 -2 -2.5 0.0045. That's it!";
		assertEquals(correctString,PigNumberClass.replacePosIntInSentenceWithWords(inputString)); 
	}
	
}
