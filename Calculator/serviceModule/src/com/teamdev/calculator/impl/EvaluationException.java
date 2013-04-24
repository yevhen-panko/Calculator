package com.teamdev.calculator.impl;

import java.util.Iterator;
import java.util.Set;

public class EvaluationException extends Exception {

    private int errorPosition;
    private Set<EvaluationState> possibleState;
    private String possibleStateString = "";

    public EvaluationException(String message, int errorPosition, Set<EvaluationState> possibleState) {
        super(message);
        this.errorPosition = errorPosition;
        this.possibleState = possibleState;
    }

    public int getErrorPosition() {
        return errorPosition + 1;
    }

    public String getPossibleStateString(){
        possibleState.remove(EvaluationState.WHITE_SPACE);
        Iterator iterator = possibleState.iterator();
        int count = 1;
        while (iterator.hasNext()){
            if (possibleState.size() == 1){
                possibleStateString = iterator.next() + ".";
            }
            else {
                if (count < possibleState.size()){
                    possibleStateString = iterator.next() + ", " + possibleStateString;
                }
                else {
                    possibleStateString = possibleStateString + " or " + iterator.next() + ".";
                }
            }
            count++;
        }
        return possibleStateString;
    }

    public String toString(){
        return getMessage() + " in position: " + getErrorPosition() + ".";
    }
}
