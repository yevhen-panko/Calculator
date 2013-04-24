package com.teamdev.calculator.impl.command;

import com.teamdev.calculator.operator.BinaryOperator;
import com.teamdev.calculator.impl.context.EvaluationContext;

public class EvaluateBinaryOperatorCommand implements EvaluationCommand {

    private final BinaryOperator operator;

    public EvaluateBinaryOperatorCommand(BinaryOperator operator) {

        if (operator == null) {
            throw new NullPointerException("Null operator passed.");
        }

        this.operator = operator;
    }

    @Override
    public void evaluate(EvaluationContext context) {
        context.pushBinaryOperator(operator);
    }
}
