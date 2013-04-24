package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.EvaluationCommand;
import com.teamdev.calculator.impl.command.WhiteSpaceCommand;

public class WhiteSpaceParser implements MathExpressionParser{

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {
        if (reader.endOfExpression()) {
            return null;
        }

        if (reader.getCurrentChar() == ' '
                || reader.getCurrentChar() == '\n'
                || reader.getCurrentChar() == '\f'
                || reader.getCurrentChar() == '\r'
                || reader.getCurrentChar() == '\t') {
            reader.incReadPosition();
            return new WhiteSpaceCommand();
        }

        return null;
    }
}
