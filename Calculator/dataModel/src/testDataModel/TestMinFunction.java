import com.teamdev.calculator.function.MinFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TestMinFunction {
    private MinFunction minFunction = null;

    @Before
    public void initializeMinFunction(){
        minFunction = new MinFunction(2, Integer.MAX_VALUE);
    }

    @After
    public void deInitializeMinFunction(){
        minFunction = null;
    }

    @Test (expected = NoSuchElementException.class)
    public void testMinFunctionWithoutArguments() throws NoSuchElementException {
        minFunction.evaluate();
    }

    @Test (expected = NoSuchElementException.class)
    public void testMinFunctionWithOneArgument() throws NoSuchElementException {
        minFunction.evaluate(new BigDecimal(100));
    }

    @Test
    public void testMinFunctionWithMoreArgument() throws NoSuchElementException {
        assertEquals("Invalid result of min function with more than 2 arguments.", new BigDecimal(-300).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                minFunction.evaluate(new BigDecimal(100), new BigDecimal(1000), new BigDecimal(-300)));
    }
}
