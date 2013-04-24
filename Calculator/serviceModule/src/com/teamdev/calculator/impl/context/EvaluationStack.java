package com.teamdev.calculator.impl.context;

import com.teamdev.calculator.function.Function;
import com.teamdev.calculator.operator.BinaryOperator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
/*
    This class contains all Deque for EvaluationContext.
    OperandStack - there are all operands of expression.
    OperatorStack - there are all binary operators. + - * / ^
    FunctionContextStack - there are all function contexts, with information about function,
    operand count and bracket count to function start.
 */
public class EvaluationStack {
    final private Deque<BigDecimal> operandStack = new ArrayDeque<>();
    final private Deque<BinaryOperator> operatorStack = new ArrayDeque<>();
    final private Deque<Integer> bracketStack = new ArrayDeque<>();

    final private Function function;

    public EvaluationStack(Function function){
        this.function = function;
    }

    public Deque<BigDecimal> getOperandStack() {
        return operandStack;
    }

    public Deque<BinaryOperator> getOperatorStack() {
        return operatorStack;
    }

    public Deque<Integer> getBracketStack() {
        return bracketStack;
    }

    public Function getFunction() {
        return function;
    }
}
