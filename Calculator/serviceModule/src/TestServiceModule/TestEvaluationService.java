import com.teamdev.calculator.impl.EvaluationException;
import com.teamdev.calculator.impl.EvaluationService;
import com.teamdev.calculator.impl.EvaluationState;
import com.teamdev.calculator.impl.context.EvaluationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.teamdev.calculator.impl.EvaluationState.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestEvaluationService {
    private EvaluationService evaluationService = null;
    private EvaluationContext evaluationContext = null;
    private EvaluationState possibleState = null;

    @Before
    public void initializeTestData(){
        evaluationService = new EvaluationService();
        evaluationContext = new EvaluationContext("35");
    }

    @After
    public void deInitializeTestData(){
        evaluationService = null;
        evaluationContext = null;
        possibleState = null;
    }

    @Test
    public void testAcceptWithRightPossibleState() throws EvaluationException {
        possibleState = NUMBER;
        assertTrue("Invalid result of method accept with right possible state.", evaluationService.accept(possibleState, evaluationContext));
    }

    @Test
    public void testAcceptWithFalsePossibleState() throws EvaluationException {
        possibleState = BINARY_OPERATOR;
        assertFalse("Invalid result of method accept with false possible state", evaluationService.accept(possibleState, evaluationContext));
    }

    @Test (expected = IllegalStateException.class)
    public void testAcceptWithUnknownPossibleState() throws EvaluationException {
        possibleState = null;
        evaluationService.accept(possibleState, evaluationContext);
    }
}
