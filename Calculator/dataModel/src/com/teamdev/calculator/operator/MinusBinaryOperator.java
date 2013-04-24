package com.teamdev.calculator.operator;

import java.math.BigDecimal;

public class MinusBinaryOperator extends AbstractBinaryOperator {

    public MinusBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.subtract(rightOperand).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }

}
