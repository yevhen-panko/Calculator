package com.teamdev.calculator.function;

public class FunctionFactory {

    public Function createFunction(String functionName){
        switch (functionName.toLowerCase()){
            case "max": return new MaxFunction(2, Integer.MAX_VALUE);
            case "min": return new MinFunction(2, Integer.MAX_VALUE);
            case "sum": return new SumFunction(2, Integer.MAX_VALUE);
            case "sqrt": return new SqrtFunction(1, 1);
            case "abs": return new AbsFunction(1, 1);
            case "pi" : return new PiFunction(0, 0);
            default: return null;
        }
    }
}
