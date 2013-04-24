package com.teamdev.calculator.statemachine;

abstract public class AbstractStateMachine<
        Result,
        Context extends StateMachineContext<State, Result>,
        State extends Enum,
        Matrix extends TransitionMatrix<State>,
        TransitionError extends Exception,
        Recognizer extends StateRecognizer<State, TransitionError, Context>> {

    final protected Result run(Context context) throws TransitionError {

        final Matrix matrix = getTransitionMatrix();
        context.setState(matrix.getStartState());

        while (!matrix.isFinishState(context.getState())) {
            if (!moveForward(context)) {
                deadlock(context);
                return null;
            }
        }

        return context.getResult();
    }

    private boolean moveForward(Context context) throws TransitionError {
        final Matrix matrix = getTransitionMatrix();
        final Recognizer recognizer = getStateRecognizer();
        final State currentState = context.getState();
        for (State possibleState : matrix.getPossibleStates(currentState)) {
            if (recognizer.accept(possibleState, context)) {
                context.setState(possibleState);
                return true;
            }
        }
        return false;
    }

    abstract protected Matrix getTransitionMatrix();

    abstract protected Recognizer getStateRecognizer();

    /**
     * This method is invoked if no possible state is defined.
     * Some kind of error should be raised by this method.
     * Result of execution is undefined in this case.
     *
     * @param context state machine execution context
     */
    abstract protected void deadlock(Context context) throws TransitionError;
}
