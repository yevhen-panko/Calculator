package com.teamdev.calculator.function;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class SqrtFunction extends AbstractFunction {

    public SqrtFunction(int minCount, int maxCount) {
        super(minCount, maxCount);
    }

    @Override
    public BigDecimal evaluate(BigDecimal... arguments) throws NoSuchElementException, IllegalArgumentException {
        if (checkArgumentCount(arguments)){
            if (arguments[0].compareTo(BigDecimal.ZERO)> -1){
                return BigDecimal.valueOf(Math.pow(arguments[0].doubleValue(), 0.5)).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
            } else throw new IllegalArgumentException("Invalid argument in SQRT function. Argument is negative.");
        }
        else throw new NoSuchElementException("Invalid number of arguments in function sqrt().\n" +
                "You can use function only with one argument.");

    }
}
