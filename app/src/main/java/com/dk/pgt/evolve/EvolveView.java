package com.dk.pgt.evolve;

import android.content.Context;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.dk.pgt.PGTApp;
import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.databinding.EvolveBinding;
import com.dk.pgt.floater.FloaterContent;
import com.dk.pgt.floater.FloaterViewModel;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by douglaskazumi on 8/31/16.
 */

public class EvolveView extends FloaterContent implements EvolveContract.View {

    private static final String SUGGESTION_FIELD_NAME = "POKEMON_NAME";
    @Inject
    EvolveContract.Presenter presenter;
    private EvolveBinding bindingObject;
    private EvolveViewModel evolveViewModel;
    private SimpleCursorAdapter suggestionAdapter;

    public EvolveView(Context context, FloaterViewModel parentViewModel) {
        super(context, parentViewModel);

        setupSuggestions();

        presenter.loadPokemons();
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
                        pokemon -> c.addRow(new Object[]{pokemon.getNumber(), pokemon.getName()}),
                        throwable -> Log.e(PGTApp.PGT, throwable.getMessage()),
                        () -> suggestionAdapter.changeCursor(c));
    }

    @Override
    public void showSelectedPokemon(Pokemon selectedPokemon) {
        bindingObject.pokemonName.setQuery(selectedPokemon.getName(), false);
    }

    @Override
    protected void setupBinding() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (LAYOUT_INFLATER_SERVICE);
        bindingObject = EvolveBinding.inflate(inflater, this, true);
        evolveViewModel = new EvolveViewModel(presenter);
        bindingObject.setViewModel(evolveViewModel);
    }

    @Override
    protected void setupInjection() {
        DaggerEvolveComponent.builder()
                .dataComponent(PGTApp.getInstance().getDataComponent())
                .evolveModule(new EvolveModule(this))
                .build()
                .inject(this);
    }

    private void setupSuggestions() {
        final String[] from = new String[]{SUGGESTION_FIELD_NAME};
        final int[] to = new int[]{android.R.id.text1};
        suggestionAdapter = new SimpleCursorAdapter(
                this.context,
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        bindingObject.pokemonName.setSuggestionsAdapter(suggestionAdapter);
        bindingObject.pokemonName.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                presenter.getSelectedPokemon(position);
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                presenter.getSelectedPokemon(position);
                return false;
            }
        });

        bindingObject.pokemonName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.filterSuggestions(newText);
                return false;
            }
        });
    }
}
