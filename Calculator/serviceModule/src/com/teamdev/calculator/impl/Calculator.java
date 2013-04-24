package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.context.EvaluationContext;
import com.teamdev.calculator.statemachine.AbstractStateMachine;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This is sample implementation, do not use for production code!!!!!
 */
public class Calculator extends AbstractStateMachine<
        BigDecimal,
        EvaluationContext,
        EvaluationState,
        EvaluationMatrix,
        EvaluationException,
        EvaluationService> {

    private final EvaluationMatrix matrix = new EvaluationMatrix();
    private final EvaluationService evaluationService = new EvaluationService();

    @Override
    protected EvaluationMatrix getTransitionMatrix() {
        return matrix;
    }

    @Override
    protected EvaluationService getStateRecognizer() {
        return evaluationService;
    }

    @Override
    protected void deadlock(EvaluationContext context) throws EvaluationException {
        final int position = context.getExpressionReader().getReadPosition();
        Set<EvaluationState> possibleState = matrix.getPossibleStates(context.getState());
        throw new EvaluationException("ERROR! Invalid expression format", position, possibleState);
    }

    public BigDecimal evaluate(String mathExpression) throws EvaluationException, NoSuchElementException {
        return run(new EvaluationContext(mathExpression));
    }
}
