package com.teamdev.calculator.function;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MaxFunction extends AbstractFunction{

    public MaxFunction(int minCount, int maxCount) {
        super(minCount, maxCount);
    }

    @Override
    public BigDecimal evaluate(BigDecimal... arguments) throws NoSuchElementException {
        if (checkArgumentCount(arguments)){
            Arrays.sort(arguments);
            return arguments[arguments.length-1].setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        }
        else throw new NoSuchElementException("Invalid number of arguments in function max().\n" +
                "You can use function only with more than two arguments.");
    }
}
