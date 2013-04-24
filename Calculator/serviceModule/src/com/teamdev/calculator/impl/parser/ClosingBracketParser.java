package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.ClosingBracketCommand;
import com.teamdev.calculator.impl.command.EvaluationCommand;

public class ClosingBracketParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        if (reader.endOfExpression()) {
            return null;
        }

        if (reader.getCurrentChar() == ')') {
            reader.incReadPosition();
            return new ClosingBracketCommand();
        }

        return null;
    }
}
