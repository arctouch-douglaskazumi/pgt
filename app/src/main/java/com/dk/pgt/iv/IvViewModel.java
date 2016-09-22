package com.dk.pgt.iv;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.dk.pgt.BR;
import com.dk.pgt.PGTApp;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class IvViewModel extends BaseObservable {
    private IvContract.Presenter mPresenter;
    private String currentCp;
    private String currentHp;
    private String requiredDust;

    public IvViewModel(IvContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Bindable
    public String getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(String currentHp) {
        this.currentHp = currentHp;
        notifyPropertyChanged(BR.currentHp);
    }

    @Bindable
    public String getRequiredDust() {
        return requiredDust;
    }

    public void setRequiredDust(String requiredDust) {
        this.requiredDust = requiredDust;
        notifyPropertyChanged(BR.requiredDust);
    }

    @Bindable
    public String getCurrentCp() {
        return currentCp;
    }

    public void setCurrentCp(String currentCp) {
        this.currentCp = currentCp;
        notifyPropertyChanged(BR.currentCp);
    }

    public void calculateIvs() {
        try {
            int currentCp = Integer.parseInt(this.currentCp);
            int currentHp = Integer.parseInt(this.currentHp);
            //TODO: Make the trainer level a pref
            int trainerLevel = 22;
            mPresenter.calculateIv(currentCp, currentHp, requiredDust, trainerLevel, false);
        } catch (NumberFormatException ex) {
            Log.e(PGTApp.PGT, ex.getMessage());
        }
    }
}
