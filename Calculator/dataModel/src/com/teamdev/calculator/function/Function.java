package com.teamdev.calculator.function;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public interface Function {
    BigDecimal evaluate(BigDecimal... arguments) throws NoSuchElementException;
}
