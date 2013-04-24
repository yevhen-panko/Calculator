package com.teamdev.calculator.impl.context;

import com.teamdev.calculator.operator.BinaryOperator;
import com.teamdev.calculator.function.Function;
import com.teamdev.calculator.impl.*;
import com.teamdev.calculator.statemachine.StateMachineContext;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.NoSuchElementException;

import static com.teamdev.calculator.impl.EvaluationState.*;

/*
    This class contains all operations with expression.
    There are next fields:
        state - state of stateMachine.
        expressionReader - util object, which working with expression and return position of caret in expression, current char and substring.
        contextStack - stack of EvaluationStack objects.
 */
public class EvaluationContext implements StateMachineContext<
        EvaluationState, BigDecimal> {

    private EvaluationState state;
    private final MathExpressionReader expressionReader;
    final private Deque<EvaluationStack> contextStack = new ArrayDeque<>();

    public EvaluationContext(String expression) {
        expressionReader = new MathExpressionReader(expression);
        contextStack.push(new EvaluationStack(null));
    }

    public MathExpressionReader getExpressionReader() {
        return expressionReader;
    }

    @Override
    public EvaluationState getState() {
        return state;
    }

    @Override
    public void setState(EvaluationState state) {
        this.state = state;
    }

    public void pushOperand(BigDecimal operand) {
        contextStack.peek().getOperandStack().push(operand);
    }

    public Deque<Integer> getBracketStack(){
        return contextStack.peek().getBracketStack();
    }

    /*
        This method pushing binary operator to operatorStack.
        If there are few elements in bracketStack and count of operators is more than last element in bracketStack,
        then remember last operator from operatorStack.
        After, compared operators priority.
        If priority of new binary operator is less than priority of last operator,
        then last operator pop from stack and new operator push in stack.
        If operatorStack is empty, new operator push in stack.
     */
    public void pushBinaryOperator(BinaryOperator binaryOperator) {
        BinaryOperator topOperator = null;
        do {
            if (!contextStack.peek().getBracketStack().isEmpty()){
                if (contextStack.peek().getOperatorStack().size() > contextStack.peek().getBracketStack().peek()){
                    topOperator = contextStack.peek().getOperatorStack().peek();
                }
            }
            else topOperator = contextStack.peek().getOperatorStack().peek();
            if (topOperator != null) {
                if (binaryOperator.compareTo(topOperator) < 1 ) {
                    executeBinaryOperator(topOperator);
                    contextStack.peek().getOperatorStack().pop();
                    break;
                } else {
                    break;
                }
            }

        } while (false);

        contextStack.peek().getOperatorStack().push(binaryOperator);
    }
    /*
        This method is util. It calls from pushBinaryOperator and pushClosingBracket methods.
        It pop two last operands from operandStack and make binary operation from argument.
        Next, It push result in operandStack.
    */
    private void executeBinaryOperator(BinaryOperator topOperator) {
        if (contextStack.peek().getOperandStack().size() > 1){
            final BigDecimal rightOperand = contextStack.peek().getOperandStack().pop();
            final BigDecimal leftOperand = contextStack.peek().getOperandStack().pop();
            final BigDecimal result = topOperator.evaluate(leftOperand, rightOperand);
            pushOperand(result);
        }
        else throw new NoSuchElementException("Invalid operand count in brackets.");
    }

    public void popBinaryOperators() {
        while (!contextStack.peek().getOperatorStack().isEmpty()) {
            executeBinaryOperator(contextStack.peek().getOperatorStack().pop());
        }
    }

    public void pushFunction(Function function){
        contextStack.push(new EvaluationStack(function));
    }
    /*
        This method create array of operands from operandStack
        and call function method evaluate.
        Then result push in operandStack.
     */
    public void popFunction() throws NoSuchElementException {
        BigDecimal[] arrayForFunction = new BigDecimal[contextStack.peek().getOperandStack().size()];
        int i = 0;
        if (contextStack.size() > 1) {
            final Function function = contextStack.peek().getFunction();
            while (!contextStack.peek().getOperandStack().isEmpty()){
                arrayForFunction[i] = contextStack.peek().getOperandStack().pop();
                i++;
            }
            contextStack.pop();
            pushOperand(function.evaluate(arrayForFunction));
        }
    }

    public void pushOpeningBracket() {
        contextStack.peek().getBracketStack().push(contextStack.peek().getOperatorStack().size());
    }
    /*
        This method pop operators from OperatorStack to size of last element in bracketStack.
        It call method executeBinaryOperator.
        After it check bracket stack is empty.
        If true, it calls method popFunction.
     */
    public void pushClosingBracket() throws EvaluationException, NoSuchElementException {
        if (!contextStack.peek().getBracketStack().isEmpty()){
            while (contextStack.peek().getOperatorStack().size() > contextStack.peek().getBracketStack().peek()){
                executeBinaryOperator(contextStack.peek().getOperatorStack().pop());
            }
            contextStack.peek().getBracketStack().pop();
            if (contextStack.peek().getBracketStack().isEmpty()){
                popFunction();
            }
        }
        else {
            throw new EvaluationException("ERROR! Invalid expression format", 0, EnumSet.of(OPENING_BRACKET));
        }
    }

    public void poshComa() throws EvaluationException {
        if (contextStack.size() > 1) {
            final int operatorStackSize = contextStack.peek().getBracketStack().peek();
            while (contextStack.peek().getOperatorStack().size() > operatorStackSize) {
                executeBinaryOperator(contextStack.peek().getOperatorStack().pop());
            }
        } else {
            throw new EvaluationException("ERROR! Invalid expression format", 0, EnumSet.of(FUNCTION, OPENING_BRACKET));
        }
    }

    @Override
    public BigDecimal getResult() {
        if (!contextStack.peek().getOperandStack().isEmpty()){
            return contextStack.peek().getOperandStack().pop();
        }
        else throw new NoSuchElementException("Invalid expression format. Nothing to calculate.");
    }
}
