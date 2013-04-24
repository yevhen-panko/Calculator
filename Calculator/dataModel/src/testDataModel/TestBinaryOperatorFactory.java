import com.teamdev.calculator.operator.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.teamdev.calculator.operator.AbstractBinaryOperator.Priority;
import static org.junit.Assert.*;

public class TestBinaryOperatorFactory {
    private BinaryOperatorFactory factory = null;

    @Before
    public void initializeBinaryOperatorFactory(){
        factory = new BinaryOperatorFactory();
    }

    @After
    public void deInitializeBinaryOperatorFactory(){
        factory = null;
    }

    @Test
    public void testCreatePlusBinaryOperator(){
        assertEquals("Invalid create plus binary operator object by name: '+'.", PlusBinaryOperator.class,
                factory.createBinaryOperator('+').getClass());
    }

    @Test
    public void testCreateMinusBinaryOperator(){
        assertEquals("Invalid create plus binary operator object by name: '-'.", MinusBinaryOperator.class,
                factory.createBinaryOperator('-').getClass());
    }

    @Test
    public void testCreateMultiplyBinaryOperator(){
        assertEquals("Invalid create plus binary operator object by name: '*'.", MultiplyBinaryOperator.class,
                factory.createBinaryOperator('*').getClass());
    }

    @Test
    public void testCreateDivideBinaryOperator(){
        assertEquals("Invalid create plus binary operator object by name: '/'.", DivideBinaryOperator.class,
                factory.createBinaryOperator('/').getClass());
    }

    @Test
    public void testCreatePowerBinaryOperator(){
        assertEquals("Invalid create plus binary operator object by name: '^'.", PowerBinaryOperator.class,
                factory.createBinaryOperator('^').getClass());
    }

    @Test
    public void testCreateUnknownBinaryOperator(){
        assertNull("Invalid create unknown binary operator object by name: '$'.", factory.createBinaryOperator('$'));
    }
}
