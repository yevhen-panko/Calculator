package com.teamdev.calculator.impl.command;

import com.teamdev.calculator.impl.context.EvaluationContext;

public class OpeningBracketCommand implements EvaluationCommand {

    @Override
    public void evaluate(EvaluationContext context) {
        context.pushOpeningBracket();
    }
}
