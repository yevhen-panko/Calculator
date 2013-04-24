package com.teamdev.calculator.function;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class SumFunction extends AbstractFunction{

    public SumFunction(int minCount, int maxCount) {
        super(minCount, maxCount);
    }

    @Override
    public BigDecimal evaluate(BigDecimal... arguments) throws NoSuchElementException {
        BigDecimal result = new BigDecimal(0);
        if (checkArgumentCount(arguments)){
            for (BigDecimal argument : arguments) {
                result = result.add(argument);
            }
            return result.setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        }
        else throw new NoSuchElementException("Invalid number of arguments in function sum().\n" +
                "You can use function only with more than two arguments.");
    }
}
