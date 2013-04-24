import com.teamdev.calculator.impl.Calculator;
import com.teamdev.calculator.impl.EvaluationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class TestCalculator {
    private Calculator calculator = null;

    @Before
    public void initializeTestData(){
        calculator = new Calculator();
    }

    @After
    public void deInitializeTestData(){
        calculator = null;
    }

    @Test
    public void testMultiply() throws EvaluationException {
        assertEquals("Invalid result of multiply 100*200.", new BigDecimal(20000).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("100*200"));
    }

    @Test
    public void testAddition() throws EvaluationException {
        assertEquals("Invalid result of addition 100+200.", new BigDecimal(300).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("100+200"));
    }

    @Test
    public void testPower() throws EvaluationException {
        assertEquals("Invalid result of power 10^2.", new BigDecimal(100).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("10^2"));
    }

    @Test
    public void testSubtract() throws EvaluationException {
        assertEquals("Invalid result of subtract 200-100.", new BigDecimal(100).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("200-100"));
    }

    @Test
    public void testDivision() throws EvaluationException {
        assertEquals("Invalid result of division 200/100.", new BigDecimal(2).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("200/100"));
    }

    @Test
    public void testMaxFunction() throws EvaluationException {
        assertEquals("Invalid result of max(100,300,200).", new BigDecimal(300).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("max(100,300,200)"));
    }

    @Test
    public void testMinFunction() throws EvaluationException {
        assertEquals("Invalid result of min(100,300,200).", new BigDecimal(100).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("min(100,300,200)"));
    }

    @Test
    public void testSumFunction() throws EvaluationException {
        assertEquals("Invalid result of sum(100,300,200).", new BigDecimal(600).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("sum(100,300,200)"));
    }

    @Test
    public void testAbsFunction() throws EvaluationException {
        assertEquals("Invalid result of abs(-100).", new BigDecimal(100).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("abs(-100)"));
    }

    @Test
    public void testSqrtFunction() throws EvaluationException {
        assertEquals("Invalid result of sqrt(100).", new BigDecimal(10).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("sqrt(100)"));
    }

    @Test
    public void testCalculatorWithHardExpressionWithoutFunctions() throws EvaluationException {
        assertEquals("Invalid result of expression: (35+25)*5-((25-10)/3)^3", new BigDecimal(175).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("(35+25)*5-((25-10)/3)^3"));
    }

    @Test
    public void testCalculatorWithHardExpressionWithFunctionsOne() throws EvaluationException {
        assertEquals("Invalid result of expression: sum(max(100+5^4,300,200), sqrt(pi()*10), abs(-300), (25+35)*100-900/3)",
                new BigDecimal(6730.6049912164).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("sum(max(100+5^4,300,200), sqrt(pi()*10), abs(-300), (25+35)*100-900/3)"));
    }

    @Test
    public void testCalculatorWithHardExpressionWithFunctionsTwo() throws EvaluationException {
        assertEquals("Invalid result of expression: sum(max(100+5^4,300,200), sqrt(pi()*10), abs(-300), (25+35)*100-900/3)",
                new BigDecimal(7730.6049912164).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros(),
                calculator.evaluate("(sum(max(100+5^4,300,200), sqrt(pi()*10), abs(-300), (25+35)*100-900/3)+1000)"));
    }

    @Test (expected = EvaluationException.class)
    public void testMissedNumber() throws Exception {
        calculator.evaluate("100+");
    }

    @Test (expected = EvaluationException.class)
    public void testMissedOpenBracket() throws EvaluationException {
        calculator.evaluate("100+100)");
    }

    @Test (expected = EvaluationException.class)
    public void testMissedCloseBracket() throws EvaluationException {
        calculator.evaluate("(100+100");
    }

    @Test (expected = EvaluationException.class)
    public void testMissedBinaryOperator() throws EvaluationException {
        calculator.evaluate("100(25+35)");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDivisionByZero() throws EvaluationException{
        calculator.evaluate("1000/0");
    }

    @Test (expected = NoSuchElementException.class)
    public void testInvalidNumberOfArgumentsInMaxFunction() throws EvaluationException{
        calculator.evaluate("max(100)");
    }

    @Test (expected = NoSuchElementException.class)
    public void testInvalidNumberOfArgumentsInMinFunction() throws EvaluationException{
        calculator.evaluate("min(100)");
    }

    @Test (expected = NoSuchElementException.class)
    public void testInvalidNumberOfArgumentsInSumFunction() throws EvaluationException{
        calculator.evaluate("sum(100)");
    }

    @Test (expected = NoSuchElementException.class)
    public void testInvalidNumberOfArgumentsInAbsFunction() throws EvaluationException{
        calculator.evaluate("abs(100, 200)");
    }

    @Test (expected = NoSuchElementException.class)
     public void testInvalidNumberOfArgumentsInPiFunction() throws EvaluationException{
        calculator.evaluate("pi(100)");
    }

    @Test (expected = NoSuchElementException.class)
    public void testInvalidNumberOfArgumentsInSqrtFunction() throws EvaluationException{
        calculator.evaluate("sqrt(100, 200)");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeArgumentInSqrtFunction() throws EvaluationException{
        calculator.evaluate("sqrt(-100)");
    }
}
