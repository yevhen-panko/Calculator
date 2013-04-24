import com.teamdev.calculator.function.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFunctionFactory {
    private FunctionFactory factory = null;

    @Before
    public void initializeFactory(){
        factory = new FunctionFactory();
    }

    @After
    public void deInitializeFactory(){
        factory = null;
    }

    @Test
    public void testCreateMaxFunction(){
        assertEquals("Invalid create max function object by name: 'max'.", MaxFunction.class, factory.createFunction("max").getClass());
    }

    @Test
    public void testCreateMinFunction(){
        assertEquals("Invalid create min function object by name: 'min'.", MinFunction.class, factory.createFunction("min").getClass());
    }

    @Test
    public void testCreateAbsFunction(){
        assertEquals("Invalid create abs function object by name: 'abs'.", AbsFunction.class, factory.createFunction("abs").getClass());
    }

    @Test
    public void testCreateSumFunction(){
        assertEquals("Invalid create sum function object by name: 'sum'.", SumFunction.class, factory.createFunction("sum").getClass());
    }

    @Test
    public void testCreatePiFunction(){
        assertEquals("Invalid create pi function object by name: 'pi'.", PiFunction.class, factory.createFunction("pi").getClass());
    }

    @Test
    public void testCreateSqrtFunction(){
        assertEquals("Invalid create sqrt function object by name: 'sqrt'.", SqrtFunction.class, factory.createFunction("sqrt").getClass());
    }

    @Test
    public void testCreateUnknownFunction(){
        assertNull("Invalid create function object if function name not found.", factory.createFunction("cos"));
    }
}
