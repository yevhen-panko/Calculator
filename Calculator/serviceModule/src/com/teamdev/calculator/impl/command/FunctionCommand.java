package com.teamdev.calculator.impl.command;

import com.teamdev.calculator.function.Function;
import com.teamdev.calculator.impl.context.EvaluationContext;

public class FunctionCommand implements EvaluationCommand{

    private final Function function;

    public FunctionCommand(Function function) {

        if (function == null) {
            throw new NullPointerException("Null function passed.");
        }

        this.function = function;
    }

    @Override
    public void evaluate(EvaluationContext context) {
        context.pushFunction(function);
    }
}
