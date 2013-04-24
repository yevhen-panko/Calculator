package com.teamdev.calculator.function;

import java.math.BigDecimal;

abstract public class AbstractFunction implements Function {
    private final int minCount;
    private final int maxCount;

    public AbstractFunction(int minCount, int maxCount) {
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    public Boolean checkArgumentCount(BigDecimal... arguments){
        return arguments.length >= minCount && arguments.length <= maxCount;
    }
}
