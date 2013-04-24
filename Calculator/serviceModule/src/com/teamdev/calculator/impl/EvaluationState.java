package com.teamdev.calculator.impl;

public enum EvaluationState {
    START,
    NUMBER,
    BINARY_OPERATOR,
    FUNCTION,
    OPENING_BRACKET,
    COMA,
    WHITE_SPACE,
    CLOSING_BRACKET,
    FINISH
}
