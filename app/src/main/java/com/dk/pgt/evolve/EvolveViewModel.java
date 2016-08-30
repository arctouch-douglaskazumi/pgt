package com.dk.pgt.evolve;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.dk.pgt.BR;
import com.dk.pgt.PGTApp;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class EvolveViewModel extends BaseObservable {
    private EvolveContract.Presenter mPresenter;
    private String mCurrentCp;
    private int mFormVisibility;

    public EvolveViewModel(EvolveContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Bindable
    public int getFormVisibility() {
        return mFormVisibility;
    }

    public void setFormVisibility(int formVisibility) {
        this.mFormVisibility = formVisibility;
        notifyPropertyChanged(BR.formVisibility);
    }

    @Bindable
    public String getCurrentCp() {
        return mCurrentCp;
    }

    public void setCurrentCp(String currentCp) {
        this.mCurrentCp = currentCp;
        notifyPropertyChanged(BR.currentCp);
    }

    public void calculateEvolutions() {
        try {
            int currentCp = Integer.parseInt(mCurrentCp);
            mPresenter.calculateEvolutions(currentCp);
        } catch (NumberFormatException ex) {
            Log.e(PGTApp.PGT, ex.getMessage());
        }
    }

    public void controlFormVisbility(WindowManager.LayoutParams params) {
        boolean isShown = mFormVisibility == View.GONE;
        setFormVisibility(isShown ? View.VISIBLE : View.GONE);
        params.flags = isShown
                ? WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.FLAG_DIM_BEHIND
                : WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    }
}
