package com.dk.pgt.evolve;

import android.app.Service;
import android.content.Intent;
import android.database.MatrixCursor;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.IBinder;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.dk.pgt.PGTApp;
import com.dk.pgt.R;
import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.databinding.EvolveBinding;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class PoGoToolsService extends Service implements EvolveContract.View {

    private static final String SUGGESTION_FIELD_NAME = "POKEMON_NAME";
    private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams
                    .FLAG_DIM_BEHIND,
            PixelFormat.TRANSLUCENT);

    @BindView(R.id.floater_icon)
    ImageView mFloaterIcon;
    @BindView(R.id.pokemonName)
    SearchView mPokemonName;
    @BindView(R.id.progressIndicator)
    LinearLayout mProgressIndicator;
    @Inject
    EvolveContract.Presenter mPresenter;
    private WindowManager mWindowManager;
    private View mFloater;
    private Point mScreenSize;
    private EvolveViewModel mViewModel;
    private SimpleCursorAdapter mPokemonAdapter;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setupInjection();

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mScreenSize = new Point();
        mWindowManager.getDefaultDisplay().getSize(mScreenSize);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mFloater = layoutInflater.inflate(R.layout.evolve, null);
        setupBindings();

        mParams.gravity = Gravity.TOP | Gravity.LEFT;
        mParams.x = 0;
        mParams.y = 100;
        mParams.dimAmount = 0.5f;

        mWindowManager.addView(mFloater, mParams);

        setupMotionHandler();

        mPresenter.loadPokemons();
    }

    private void setupInjection() {
        DaggerEvolveComponent.builder()
                .dataComponent(((PGTApp) getApplication()).getDataComponent())
                .evolveModule(new EvolveModule(this))
                .build()
                .inject(this);
    }

    private void setupBindings() {
        ButterKnife.bind(this, mFloater);

        EvolveBinding binding = EvolveBinding.bind(mFloater);
        mViewModel = new EvolveViewModel(mPresenter);
        binding.setViewModel(mViewModel);

        setupSuggestions();
    }

    private void setupSuggestions() {
        final String[] from = new String[]{SUGGESTION_FIELD_NAME};
        final int[] to = new int[]{android.R.id.text1};
        mPokemonAdapter = new SimpleCursorAdapter(
                PoGoToolsService.this,
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mPokemonName.setSuggestionsAdapter(mPokemonAdapter);
        mPokemonName.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                mPresenter.getSelectedPokemon(position);
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                mPresenter.getSelectedPokemon(position);
                return false;
            }
        });

        mPokemonName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.filterSuggestions(newText);
                return false;
            }
        });
    }

    @Override
    public void showEvolutions(List<Evolution> evolutions) {
        StringBuilder builder = new StringBuilder();

        Observable.from(evolutions)
                .map(evo -> String.format(Locale.US,
                        "%s: %d%s",
                        evo.getName(),
                        evo.getCpEstimate(),
                        System.lineSeparator()))
                .subscribe(builder::append);

        Toast.makeText(PoGoToolsService.this, builder.toString().trim(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void updateSuggestions(List<Pokemon> pokemons) {
        final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID,
                SUGGESTION_FIELD_NAME});

        Observable.from(pokemons)
                .subscribe(
                        pokemon ->
                                c.addRow(new Object[]{pokemon.getNumber(), pokemon.getName()})
                        ,
                        throwable -> Log.e(PGTApp.PGT, throwable.getMessage()),
                        () -> mPokemonAdapter.changeCursor(c));
    }

    @Override
    public void showSelectedPokemon(Pokemon selectedPokemon) {
        mPokemonName.setQuery(selectedPokemon.getName(), false);
    }

    @Override
    public void presentError(String errorMessage) {
        Toast.makeText(PoGoToolsService.this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setIndicatorVisible(boolean isVisible) {
        mProgressIndicator.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloater != null) mWindowManager.removeView(mFloater);
    }

    private void setupMotionHandler() {
        mFloaterIcon.setOnTouchListener(new View.OnTouchListener() {
            private static final double MOTION_THRESHOLD = 2000;
            private static final double BOTTOM_SCREEN_PERCENTAGE_TO_DESTROY = 0.9;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            private float totalMotion;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = mParams.x;
                        initialY = mParams.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d(PGTApp.PGT, "TotalMotion: " + totalMotion);
                        if (totalMotion < MOTION_THRESHOLD) {
                            mViewModel.controlFormVisbility(mParams);
                            mWindowManager.updateViewLayout(mFloater, mParams);
                        }
                        totalMotion = 0;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        totalMotion += event.getRawX() + event.getRawY();
                        if (event.getRawY() > mScreenSize.y * BOTTOM_SCREEN_PERCENTAGE_TO_DESTROY) {
                            stopSelf();
                        }
                        mParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                        mParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mFloater, mParams);
                        return true;
                }
                return false;
            }
        });
    }
}
