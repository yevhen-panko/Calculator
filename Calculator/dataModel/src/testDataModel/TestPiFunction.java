import com.teamdev.calculator.function.PiFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TestPiFunction {
    private PiFunction piFunction = null;

    @Before
    public void initializePiFunction(){
        piFunction = new PiFunction(0, 0);
    }

    @After
    public void deInitializePiFunction(){
        piFunction = null;
    }

    @Test
    public void testPiFunctionWithoutArguments() throws NoSuchElementException {
        assertEquals("Invalid result of pi function without arguments.", new BigDecimal(3.1415926536).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                piFunction.evaluate());
    }

    @Test (expected = NoSuchElementException.class)
    public void testPiFunctionWithMoreArguments() throws NoSuchElementException {
        piFunction.evaluate(new BigDecimal(100));
    }
}
