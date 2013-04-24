package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.ComaCommand;
import com.teamdev.calculator.impl.command.EvaluationCommand;

public class ComaParser implements MathExpressionParser{

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {
        if (reader.endOfExpression()) {
            return null;
        }

        if (reader.getCurrentChar() == ',') {
            reader.incReadPosition();
            return new ComaCommand();
        }

        return null;
    }
}
