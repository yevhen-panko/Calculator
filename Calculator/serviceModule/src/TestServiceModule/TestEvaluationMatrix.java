import com.teamdev.calculator.impl.EvaluationMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumSet;

import static com.teamdev.calculator.impl.EvaluationState.*;
import static com.teamdev.calculator.impl.EvaluationState.FINISH;
import static org.junit.Assert.assertEquals;

public class TestEvaluationMatrix {
    private EvaluationMatrix evaluationMatrix = null;

    @Before
    public void initializeTestData(){
        evaluationMatrix = new EvaluationMatrix();
    }

    @After
    public void deInitializeTestData(){
        evaluationMatrix = null;
    }

    @Test
    public void testGetPossibleStates(){
        assertEquals("Invalid result of getPossibleStates method with argument NUMBER.",
                EnumSet.of(BINARY_OPERATOR, CLOSING_BRACKET, COMA, WHITE_SPACE, FINISH),
                evaluationMatrix.getPossibleStates(NUMBER));
    }
}
