package com.teamdev.calculator.statemachine;

public interface StateRecognizer<
        State extends Enum,
        TransitionError extends Exception,
        Context> {

    boolean accept(State possibleState, Context context) throws TransitionError;
}
