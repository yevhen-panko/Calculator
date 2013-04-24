package com.teamdev.calculator.impl;

import com.teamdev.calculator.statemachine.TransitionMatrix;

import java.util.EnumSet;
import java.util.Set;

import static com.teamdev.calculator.impl.EvaluationState.*;

/**
 * This is sample implementation, do not use for production code!!!!!
 */
public class EvaluationMatrix implements
        TransitionMatrix<EvaluationState> {

    @Override
    public EvaluationState getStartState() {
        return EvaluationState.START;
    }

    @Override
    public boolean isFinishState(EvaluationState evaluationState) {
        return FINISH == evaluationState;
    }

    @Override
    public Set<EvaluationState> getPossibleStates(EvaluationState currentState) {

        switch (currentState) {
            case START:
                return EnumSet.of(NUMBER, OPENING_BRACKET, FUNCTION);
            case NUMBER:
                return EnumSet.of(BINARY_OPERATOR, CLOSING_BRACKET, COMA, WHITE_SPACE, FINISH);
            case BINARY_OPERATOR:
                return EnumSet.of(NUMBER, OPENING_BRACKET, FUNCTION, WHITE_SPACE);
            case FUNCTION:
                return  EnumSet.of(OPENING_BRACKET, WHITE_SPACE);
            case OPENING_BRACKET:
                return EnumSet.of(NUMBER, OPENING_BRACKET, FUNCTION, CLOSING_BRACKET, WHITE_SPACE);
            case COMA:
                return EnumSet.of(NUMBER, OPENING_BRACKET, FUNCTION, WHITE_SPACE);
            case WHITE_SPACE:
                return EnumSet.of(NUMBER, FUNCTION, OPENING_BRACKET, CLOSING_BRACKET, COMA, BINARY_OPERATOR, WHITE_SPACE);
            case CLOSING_BRACKET:
                return EnumSet.of(CLOSING_BRACKET, BINARY_OPERATOR, COMA, WHITE_SPACE, FINISH);
        }

        return EnumSet.noneOf(EvaluationState.class);
    }
}
