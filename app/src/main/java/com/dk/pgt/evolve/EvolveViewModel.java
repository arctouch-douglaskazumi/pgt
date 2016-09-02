package com.dk.pgt.evolve;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.dk.pgt.BR;
import com.dk.pgt.PGTApp;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class EvolveViewModel extends BaseObservable {
    private EvolveContract.Presenter mPresenter;
    private String mCurrentCp;

    public EvolveViewModel(EvolveContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
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
}
