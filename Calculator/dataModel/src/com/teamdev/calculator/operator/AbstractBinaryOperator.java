package com.teamdev.calculator.operator;

abstract public class AbstractBinaryOperator implements BinaryOperator {

    public static enum Priority {
        LOW, // +, -
        MEDIUM, // *, /
        HIGH // ^
    }

    private final Priority priority;

    protected AbstractBinaryOperator(Priority priority) {

        if (priority == null) {
            throw new NullPointerException("Null priority passed.");
        }

        this.priority = priority;
    }

    @Override
    public int compareTo(BinaryOperator o) {
        return priority.compareTo(((AbstractBinaryOperator) o).priority);
    }
}
