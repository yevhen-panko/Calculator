package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.operator.BinaryOperator;
import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.EvaluateBinaryOperatorCommand;
import com.teamdev.calculator.impl.command.EvaluationCommand;
import com.teamdev.calculator.operator.BinaryOperatorFactory;

public class BinaryOperatorParser implements MathExpressionParser {

    private final BinaryOperatorFactory factory =
            new BinaryOperatorFactory();

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {

        if (reader.endOfExpression()) {
            return null;
        }

        final char representation = reader.getCurrentChar();

        final BinaryOperator binaryOperator = factory.createBinaryOperator(
                representation);

        if (binaryOperator != null) {
            reader.incReadPosition();
            return new EvaluateBinaryOperatorCommand(binaryOperator);
        }

        return null;
    }
}
