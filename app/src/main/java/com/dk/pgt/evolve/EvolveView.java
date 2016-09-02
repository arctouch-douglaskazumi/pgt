package com.dk.pgt.evolve;

import android.content.Context;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.dk.pgt.PGTApp;
import com.dk.pgt.R;
import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.databinding.EvolveBinding;
import com.dk.pgt.floater.FloaterViewModel;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by douglaskazumi on 8/31/16.
 */

public class EvolveView extends LinearLayout implements EvolveContract.View {

    private static final String SUGGESTION_FIELD_NAME = "POKEMON_NAME";
    @Inject
    EvolveContract.Presenter mPresenter;
    private Context context;
    private EvolveBinding mBinding;
    private FloaterViewModel parentViewModel;
    private EvolveViewModel mViewModel;
    private SimpleCursorAdapter mPokemonAdapter;

    public EvolveView(Context context, FloaterViewModel parentViewModel) {
        super(context);
        this.context = context;
        this.parentViewModel = parentViewModel;
        View.inflate(context, R.layout.evolve, this);

        setupInjection();
        setupBindings();
        setupSuggestions();

        mPresenter.loadPokemons();
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

        Toast.makeText(context, builder.toString().trim(), Toast.LENGTH_LONG)
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
        mBinding.pokemonName.setQuery(selectedPokemon.getName(), false);
    }

    @Override
    public void presentError(String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setIndicatorVisible(boolean isVisible) {
        parentViewModel.setLoadingVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void setupInjection() {
        DaggerEvolveComponent.builder()
                .dataComponent(PGTApp.getInstance().getDataComponent())
                .evolveModule(new EvolveModule(this))
                .build()
                .inject(this);
    }

    private void setupBindings() {
        mBinding = EvolveBinding.bind(this.getChildAt(0));
        mViewModel = new EvolveViewModel(mPresenter);
        mBinding.setViewModel(mViewModel);
    }

    private void setupSuggestions() {
        final String[] from = new String[]{SUGGESTION_FIELD_NAME};
        final int[] to = new int[]{android.R.id.text1};
        mPokemonAdapter = new SimpleCursorAdapter(
                this.context,
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mBinding.pokemonName.setSuggestionsAdapter(mPokemonAdapter);
        mBinding.pokemonName.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
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

        mBinding.pokemonName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
}
