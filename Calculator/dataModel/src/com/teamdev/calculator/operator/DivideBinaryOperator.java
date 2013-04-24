package com.teamdev.calculator.operator;

import java.math.BigDecimal;

public class DivideBinaryOperator extends AbstractBinaryOperator {

    public DivideBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {

        if (BigDecimal.ZERO.compareTo(rightOperand) == 0) {
            throw new IllegalArgumentException("Division by zero.");
        }
        return new BigDecimal(leftOperand.doubleValue() / rightOperand.doubleValue()).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }
}
