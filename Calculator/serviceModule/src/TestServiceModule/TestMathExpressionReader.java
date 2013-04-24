import com.teamdev.calculator.impl.MathExpressionReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMathExpressionReader {
    private MathExpressionReader reader = null;

    @Before
    public void initializeTestData(){
        reader = new MathExpressionReader("35+25");
    }

    @After
    public void deInitializeTestData(){
        reader = null;
    }

    @Test (expected = NullPointerException.class)
    public void testMathExpressionReaderWithNullExpression(){
        reader = new MathExpressionReader(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMathExpressionReaderWithEmptyExpression(){
        reader = new MathExpressionReader("   ");
    }

    @Test
    public void testGetCurrentChar(){
        assertEquals("Invalid result of method getCurrentChar.", '3', reader.getCurrentChar());
    }

    @Test
    public void testGetSubString(){
        assertEquals("Invalid result of method getSubString.", "3", reader.getSubString(0));
    }
}
