import com.teamdev.calculator.function.AbsFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TestAbsFunction {
    private AbsFunction absFunction = null;

    @Before
    public void initializeAbsFunction(){
        absFunction = new AbsFunction(1, 1);
    }

    @After
    public void deInitializeAbsFunction(){
        absFunction = null;
    }

    @Test (expected = NoSuchElementException.class)
    public void testAbsFunctionWithoutArguments() throws NoSuchElementException {
        absFunction.evaluate();
    }

    @Test
    public void testAbsFunctionWithOneArgument() throws NoSuchElementException {
        assertEquals("Invalid result of abs function with one argument.", new BigDecimal(100).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                absFunction.evaluate(new BigDecimal(-100)));
    }

    @Test (expected = NoSuchElementException.class)
    public void testAbsFunctionWithMoreArgument() throws NoSuchElementException {
        absFunction.evaluate(new BigDecimal(100), new BigDecimal(-200));
    }
}
