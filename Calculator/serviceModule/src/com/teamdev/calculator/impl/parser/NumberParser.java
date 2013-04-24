package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.EvaluateNumberCommand;
import com.teamdev.calculator.impl.command.EvaluationCommand;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;

public class NumberParser implements MathExpressionParser {

    private static final DecimalFormat NUMBER_FORMAT =
            new DecimalFormat("0.0");

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        NUMBER_FORMAT.setDecimalFormatSymbols(decimalFormatSymbols);

        final ParsePosition position =
                new ParsePosition(reader.getReadPosition());
        final Number number =
                NUMBER_FORMAT.parse(reader.getExpression(), position);

        if (position.getErrorIndex() < 0) {

            final int readPosition = position.getIndex();
            reader.setReadPosition(readPosition);

            return new EvaluateNumberCommand(
                    new BigDecimal(number.doubleValue()));
        }

        return null;
    }
}
