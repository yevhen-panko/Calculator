package com.teamdev.calculator.operator;

import java.math.BigDecimal;

public class MultiplyBinaryOperator extends AbstractBinaryOperator {

    public MultiplyBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.multiply(rightOperand).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }

}
