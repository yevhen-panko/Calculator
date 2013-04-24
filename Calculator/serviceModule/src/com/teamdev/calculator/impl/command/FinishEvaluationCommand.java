package com.teamdev.calculator.impl.command;

import com.teamdev.calculator.impl.context.EvaluationContext;
import com.teamdev.calculator.impl.EvaluationException;

import java.util.EnumSet;

import static com.teamdev.calculator.impl.EvaluationState.CLOSING_BRACKET;

public class FinishEvaluationCommand implements EvaluationCommand {

    @Override
    public void evaluate(EvaluationContext context) throws EvaluationException {
        if (!context.getBracketStack().isEmpty()){
            throw new EvaluationException("ERROR! Invalid expression format", context.getExpressionReader().getReadPosition(), EnumSet.of(CLOSING_BRACKET));
        }
        else{
            context.popBinaryOperators();
        }
    }
}
