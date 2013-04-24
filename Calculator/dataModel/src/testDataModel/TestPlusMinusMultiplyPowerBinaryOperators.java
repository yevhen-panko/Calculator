import com.teamdev.calculator.operator.*;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teamdev.calculator.operator.AbstractBinaryOperator.*;
import static org.junit.Assert.assertEquals;

public class TestPlusMinusMultiplyPowerBinaryOperators {

    @Test
    public void testMultiply(){
        assertEquals("Invalid result of multiply 100*200.", new BigDecimal(20000).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                new MultiplyBinaryOperator(Priority.MEDIUM).evaluate(new BigDecimal(100), new BigDecimal(200)));
    }

    @Test
    public void testAddition(){
        assertEquals("Invalid result of addition 100+200.", new BigDecimal(300).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                new PlusBinaryOperator(Priority.LOW).evaluate(new BigDecimal(100), new BigDecimal(200)));
    }

    @Test
    public void testPower(){
        assertEquals("Invalid result of power 10^2.", new BigDecimal(100).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                new PowerBinaryOperator(Priority.HIGH).evaluate(new BigDecimal(10), new BigDecimal(2)));
    }

    @Test
    public void testSubtract(){
        assertEquals("Invalid result of subtract 200-100.", new BigDecimal(100).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                new MinusBinaryOperator(Priority.LOW).evaluate(new BigDecimal(200), new BigDecimal(100)));
    }
}
