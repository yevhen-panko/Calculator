import com.teamdev.calculator.function.SqrtFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TestSqrtFunction {
    private SqrtFunction sqrtFunction = null;

    @Before
    public void initializeSqrtFunction(){
        sqrtFunction = new SqrtFunction(1, 1);
    }

    @After
    public void deInitializeSqrtFunction(){
        sqrtFunction = null;
    }

    @Test (expected = NoSuchElementException.class)
    public void testSqrtFunctionWithoutArguments() throws NoSuchElementException {
        sqrtFunction.evaluate();
    }

    @Test
    public void testSqrtFunctionWithOneArgument() throws NoSuchElementException {
        assertEquals("Invalid result of sqrt function with one argument.", new BigDecimal(10).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                sqrtFunction.evaluate(new BigDecimal(100)));
    }

    @Test (expected = NoSuchElementException.class)
    public void testSqrtFunctionWithMoreArgument() throws NoSuchElementException {
        sqrtFunction.evaluate(new BigDecimal(100), new BigDecimal(-200));
    }
}
