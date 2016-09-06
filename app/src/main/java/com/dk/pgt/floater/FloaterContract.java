package com.dk.pgt.floater;

/**
 * Created by douglaskazumi on 9/6/16.
 */

public interface FloaterContract {
    interface Presenter {

    }

    interface View {
        void presentError(String errorMessage);

        void setIndicatorVisible(boolean isVisible);
    }
}
