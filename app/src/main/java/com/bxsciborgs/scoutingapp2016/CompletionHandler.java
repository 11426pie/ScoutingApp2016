package com.bxsciborgs.scoutingapp2016;

/**
 * Created by Nik on 3/6/2016.
 */
public interface CompletionHandler {
    public void handle(Object obj);

    /*usage:
    Handle can be whatever method used for completion handling;
    when creating a completion block, use handle method

    as so:
    something.setCompletionHandler(new CompletionHandler() {
        @Override
        public void handle(<parameter>) {
            ...
        }
    });

    In another class:
    void setCompletionHandler(CompletionHandler h) {
         savedHandler = h;
    }

    Call completion method:
    savedHandler.handle(<parameter>);
    */
}
