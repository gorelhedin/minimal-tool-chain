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
public class TestPigiffyDigitParametrized{

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, "erozay" }, { 1, "oneway" },
                { 6, "ixsay" }, { 7, "evensay" },
                { 8, "eightway" }, { 9, "inenay" }
           });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ int fInput;

    @Parameter(1)
    public /* NOT private */ String fExpected;

    @Test
    public void testDigitPigiffying() {
        /*assertEquals(fExpected, Fibonacci.compute(fInput));*/
        assertEquals( fExpected, PigNumberClass.pigiffyDigit(fInput) );
    }
	
}
