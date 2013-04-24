package com.teamdev.calculator.impl.command;

import com.teamdev.calculator.impl.EvaluationException;
import com.teamdev.calculator.impl.context.EvaluationContext;

public interface EvaluationCommand {
    void evaluate(EvaluationContext context) throws EvaluationException;
}
