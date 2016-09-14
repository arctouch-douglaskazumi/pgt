package com.dk.pgt.floater;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;

import com.dk.pgt.BR;
import com.dk.pgt.databinding.FloaterBinding;
import com.dk.pgt.evolve.EvolveView;
import com.dk.pgt.iv.IvView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;
import static android.view.WindowManager.LayoutParams.TYPE_PHONE;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class FloaterViewModel extends BaseObservable {
    private int mFormVisibility;
    private int mLoadingVisibility = View.GONE;
    private Context context;
    private FloaterBinding binding;
    private View currentView;

    public FloaterViewModel(Context context, FloaterBinding binding) {
        this.context = context;
        this.binding = binding;
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

    public void changeView() {
        binding.content.removeViews(1, binding.content.getChildCount() - 1);

        if (currentView != null) {
            if (currentView.getClass() == EvolveView.class) {
                currentView = new IvView(context, this);
            } else {
                currentView = new EvolveView(context, this);
            }
        }
        else {
            currentView = new EvolveView(context, this);
        }

        binding.content.addView(
                currentView,
                new WindowManager.LayoutParams(
                        MATCH_PARENT,
                        MATCH_PARENT,
                        TYPE_PHONE,
                        SOFT_INPUT_STATE_VISIBLE,
                        PixelFormat.TRANSLUCENT));
    }
}
