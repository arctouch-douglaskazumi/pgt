package com.dk.pgt.pokemonSearch;

import android.content.Context;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.dk.pgt.PGTApp;
import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.floater.FloaterContent;
import com.dk.pgt.floater.FloaterViewModel;

import java.util.List;

import rx.Observable;

/**
 * Created by douglaskazumi on 9/14/16.
 */

public abstract class PokemonSearchView
        extends FloaterContent
        implements PokemonSearchContract.View {

    private static final String SUGGESTION_FIELD_NAME = "POKEMON_NAME";
    private SearchView searchView;
    private PokemonSearchContract.Presenter presenter;
    private SimpleCursorAdapter suggestionAdapter;

    public PokemonSearchView(Context context, FloaterViewModel floaterViewModel) {
        super(context, floaterViewModel);
        presenter = setupPresenter();
        presenter.loadPokemons();

        searchView = setupSearchView();

        setupSuggestions();
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
        searchView.setQuery(selectedPokemon.getName(), false);
    }

    protected abstract PokemonSearchContract.Presenter setupPresenter();

    protected abstract SearchView setupSearchView();

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
        searchView.setSuggestionsAdapter(suggestionAdapter);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
