package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.EvaluationCommand;
import com.teamdev.calculator.impl.command.FinishEvaluationCommand;

public class EndOfExpressionParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        if (reader.endOfExpression()) {
            return new FinishEvaluationCommand();
        }
        return null;
    }
}
