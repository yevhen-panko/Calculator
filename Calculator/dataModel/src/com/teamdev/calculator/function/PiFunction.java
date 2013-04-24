package com.teamdev.calculator.function;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class PiFunction extends AbstractFunction{

    public PiFunction(int minCount, int maxCount) {
        super(minCount, maxCount);
    }

    @Override
    public BigDecimal evaluate(BigDecimal... arguments) throws NoSuchElementException {
        if (checkArgumentCount(arguments)){
            return BigDecimal.valueOf(Math.PI).setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        }
        else throw new NoSuchElementException("Invalid number of arguments in function pi().\n" +
                "You can use function only without arguments.");
    }
}
