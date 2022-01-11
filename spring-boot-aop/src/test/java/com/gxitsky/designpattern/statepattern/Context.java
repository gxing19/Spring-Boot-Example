package com.gxitsky.designpattern.statepattern;

public class Context {

    private IState state;

    public Context() {

    }

    public Context(IState state) {
        this.state = state;
    }

    void handle() {
        state.handle(this);
    }

    public IState getState() {
        return state;
    }

    public Context setState(IState state) {
        this.state = state;
        return this;
    }
}
