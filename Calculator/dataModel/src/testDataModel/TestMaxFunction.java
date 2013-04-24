import com.teamdev.calculator.function.MaxFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TestMaxFunction {
    private MaxFunction maxFunction = null;

    @Before
    public void initializeMaxFunction(){
        maxFunction = new MaxFunction(2, Integer.MAX_VALUE);
    }

    @After
    public void deInitializeMaxFunction(){
        maxFunction = null;
    }

    @Test (expected = NoSuchElementException.class)
    public void testMaxFunctionWithoutArguments() throws NoSuchElementException {
        maxFunction.evaluate();
    }

    @Test (expected = NoSuchElementException.class)
    public void testMaxFunctionWithOneArgument() throws NoSuchElementException {
        maxFunction.evaluate(new BigDecimal(100));
    }

    @Test
    public void testMaxFunctionWithMoreArgument() throws NoSuchElementException {
        assertEquals("Invalid result of max function with more than 2 arguments.", new BigDecimal(1000).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                maxFunction.evaluate(new BigDecimal(100), new BigDecimal(1000), new BigDecimal(-300)));
    }


}
