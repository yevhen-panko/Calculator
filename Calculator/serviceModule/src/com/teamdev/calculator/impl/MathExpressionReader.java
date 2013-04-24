package com.teamdev.calculator.impl;

public class MathExpressionReader {

    private final String expression;
    private int readPosition = 0;

    public MathExpressionReader(String expression) {

        if (expression == null) {
            throw new NullPointerException("Null expression passed.");
        }

        if (expression.trim().length() == 0) {
            throw new IllegalArgumentException("Empty expression parsed.");
        }

        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public int getReadPosition() {
        return readPosition;
    }

    public char getCurrentChar() {
        return expression.charAt(readPosition);
    }

    public void incReadPosition() {
        readPosition++;
    }

    public void setReadPosition(int readPosition) {
        this.readPosition = readPosition;
    }

    public boolean endOfExpression() {
        return readPosition >= expression.length();
    }

    public String getSubString(int readPosition) {
        if (readPosition < expression.length()){
            return expression.substring(readPosition, readPosition+1);
        }
        else return "";
    }

}
