package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.EvaluationCommand;
import com.teamdev.calculator.impl.command.OpeningBracketCommand;

public class OpeningBracketParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        if (reader.endOfExpression()) {
            return null;
        }

        if (reader.getCurrentChar() == '(') {
            reader.incReadPosition();
            return new OpeningBracketCommand();
        }

        return null;
    }
}
