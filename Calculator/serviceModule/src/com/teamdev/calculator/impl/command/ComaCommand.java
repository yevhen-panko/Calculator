package com.teamdev.calculator.impl.command;

import com.teamdev.calculator.impl.EvaluationException;
import com.teamdev.calculator.impl.context.EvaluationContext;

public class ComaCommand implements EvaluationCommand{

    @Override
    public void evaluate(EvaluationContext context) throws EvaluationException {
        context.poshComa();
    }
}
