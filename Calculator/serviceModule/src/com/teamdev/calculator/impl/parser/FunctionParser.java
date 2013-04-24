package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.function.Function;
import com.teamdev.calculator.function.FunctionFactory;
import com.teamdev.calculator.impl.MathExpressionReader;
import com.teamdev.calculator.impl.command.EvaluationCommand;
import com.teamdev.calculator.impl.command.FunctionCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionParser implements  MathExpressionParser{

    private final FunctionFactory functionFactory = new FunctionFactory();

    @Override
    public EvaluationCommand parse(MathExpressionReader reader) {
        final Pattern pattern = Pattern.compile("[a-z,A-Z]");
        int currentPosition = reader.getReadPosition();
        StringBuilder functionName = new StringBuilder();
        for (int i=currentPosition; i < reader.getExpression().length(); i++){
            Matcher matcher = pattern.matcher(reader.getSubString(i));
            if (matcher.matches()){
                functionName = functionName.append(reader.getSubString(i));
                currentPosition = i+1;
            }
            else break;
        }
        final Function function = functionFactory.createFunction(functionName.toString());

        if (function != null){
            reader.setReadPosition(currentPosition);
            return new FunctionCommand(function);
        }
        else return null;
    }
}
