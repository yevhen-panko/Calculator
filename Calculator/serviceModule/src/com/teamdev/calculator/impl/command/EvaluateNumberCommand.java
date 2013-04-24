package com.teamdev.calculator.impl.command;

import com.teamdev.calculator.impl.context.EvaluationContext;

import java.math.BigDecimal;

public class EvaluateNumberCommand implements EvaluationCommand {

    private final BigDecimal number;

    public EvaluateNumberCommand(BigDecimal number) {
        this.number = number;
    }

    @Override
    public void evaluate(EvaluationContext context) {
        context.pushOperand(number);
    }
}
