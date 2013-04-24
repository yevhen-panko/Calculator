package com.teamdev.calculator.operator;

import java.math.BigDecimal;

public class PlusBinaryOperator extends AbstractBinaryOperator {

    public PlusBinaryOperator(Priority priority) {
        super(priority);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand) {
        return leftOperand.add(rightOperand).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }

}
