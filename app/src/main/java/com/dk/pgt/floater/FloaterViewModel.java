package com.dk.pgt.floater;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.view.WindowManager;

import com.dk.pgt.BR;

import static android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class FloaterViewModel extends BaseObservable {
    private int mFormVisibility;
    private int mLoadingVisibility = View.GONE;

    @Bindable
    public int getFormVisibility() {
        return mFormVisibility;
    }

    public void setFormVisibility(int formVisibility) {
        this.mFormVisibility = formVisibility;
        notifyPropertyChanged(BR.formVisibility);
    }

    @Bindable
    public int getLoadingVisibility() {
        return mLoadingVisibility;
    }

    public void setLoadingVisibility(int loadingVisibility) {
        this.mLoadingVisibility = loadingVisibility;
        notifyPropertyChanged(BR.loadingVisibility);
    }

    public void controlFormVisbility(WindowManager.LayoutParams params) {
        boolean isShown = mFormVisibility == View.GONE;
        setFormVisibility(isShown ? View.VISIBLE : View.GONE);
        params.flags = isShown
                ? SOFT_INPUT_STATE_VISIBLE | FLAG_DIM_BEHIND
                : FLAG_NOT_FOCUSABLE;
    }
}
