package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.EvaluationCommand;

public interface MathExpressionParser {
    EvaluationCommand parse(MathExpressionReader reader);
}
