package tests;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import piglatin.*;

@RunWith(Parameterized.class)
public class TestPigiffyDigitToWordsParametrized{

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, "zero" },
                { 219, "two hundred nineteen" },
                { 1316, "one thousand three hundred sixteen" },
                { 123456789, "one hundred twenty three million four hundred fifty six thousand seven hundred eighty nine" }
           });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ int fInput;

    @Parameter(1)
    public /* NOT private */ String fExpected;

    @Test
    public void testDigitToWords() {
        assertEquals( fExpected, PigNumberClass.convertPosIntToWords(fInput) );
    }
	
}
