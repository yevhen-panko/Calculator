package com.teamdev.calculator.operator;

import java.math.BigDecimal;

public interface BinaryOperator extends Comparable<BinaryOperator> {
    BigDecimal evaluate(BigDecimal leftOperand, BigDecimal rightOperand);
}
