package com.teamdev.calculator.function;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinFunction extends AbstractFunction{

    public MinFunction(int minCount, int maxCount) {
        super(minCount, maxCount);
    }

    @Override
    public BigDecimal evaluate(BigDecimal... arguments) throws NoSuchElementException {
        if (checkArgumentCount(arguments)){
            Arrays.sort(arguments);
            return arguments[0].setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        }
        else throw new NoSuchElementException("Invalid number of arguments in function min().\n" +
                "You can use function only with more than two arguments.");
    }
}
