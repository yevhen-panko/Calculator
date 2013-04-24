import com.teamdev.calculator.function.SumFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TestSumFunction {
    private SumFunction sumFunction = null;

    @Before
    public void initializeSumFunction(){
        sumFunction = new SumFunction(2, Integer.MAX_VALUE);
    }

    @After
    public void deInitializeSumFunction(){
        sumFunction = null;
    }

    @Test (expected = NoSuchElementException.class)
    public void testSumFunctionWithoutArguments() throws NoSuchElementException {
        sumFunction.evaluate();
    }

    @Test (expected = NoSuchElementException.class)
    public void testSumFunctionWithOneArgument() throws NoSuchElementException {
        sumFunction.evaluate(new BigDecimal(100));
    }

    @Test
    public void testSumFunctionWithMoreArgument() throws NoSuchElementException {
        assertEquals("Invalid result of sum function with more than 2 arguments.", new BigDecimal(1000).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                sumFunction.evaluate(new BigDecimal(100), new BigDecimal(700), new BigDecimal(200)));
    }
}
