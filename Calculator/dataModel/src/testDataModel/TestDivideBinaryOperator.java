import com.teamdev.calculator.operator.DivideBinaryOperator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teamdev.calculator.operator.AbstractBinaryOperator.*;
import static org.junit.Assert.assertEquals;

public class TestDivideBinaryOperator {
    private DivideBinaryOperator divideBinaryOperator = null;

    @Before
    public void initializeDivideBinaryOperator(){
        divideBinaryOperator = new DivideBinaryOperator(Priority.LOW);
    }

    @After
    public void deInitializeDivideBinaryOperator(){
        divideBinaryOperator = null;
    }

    @Test
    public void testDivide(){
        assertEquals("Invalid result of divide 200/100.", new BigDecimal(2).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                divideBinaryOperator.evaluate(new BigDecimal(200), new BigDecimal(100)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDivisionByZero(){
        divideBinaryOperator.evaluate(new BigDecimal(200), new BigDecimal(0));
    }
}
