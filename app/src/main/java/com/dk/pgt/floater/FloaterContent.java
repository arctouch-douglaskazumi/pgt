package com.dk.pgt.floater;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by douglaskazumi on 9/6/16.
 */
public abstract class FloaterContent extends LinearLayout implements FloaterContract.View {
    protected Context context;
    private FloaterViewModel floaterViewModel;

    public FloaterContent(Context context, FloaterViewModel floaterViewModel) {
        super(context);
        this.context = context;
        this.floaterViewModel = floaterViewModel;
        this.setOrientation(VERTICAL);

        setupInjection();
        setupBinding();
    }

    @Override
    public void presentError(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setIndicatorVisible(boolean isVisible) {
        floaterViewModel.setLoadingVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    protected abstract void setupInjection();

    protected abstract void setupBinding();
}
