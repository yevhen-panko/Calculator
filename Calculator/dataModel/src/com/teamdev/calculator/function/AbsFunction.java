package com.teamdev.calculator.function;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class AbsFunction extends AbstractFunction{

    public AbsFunction(int minCount, int maxCount) {
        super(minCount, maxCount);
    }

    @Override
    public BigDecimal evaluate(BigDecimal... arguments) throws NoSuchElementException {
        if (checkArgumentCount(arguments)){
            return arguments[0].abs().setScale(10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        }
        else throw new NoSuchElementException("Invalid number of arguments in function abs().\n" +
                "You can use function only with one argument.");
    }
}
