package com.dk.pgt.evolve;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.dk.pgt.PGTApp;
import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.databinding.EvolveBinding;
import com.dk.pgt.floater.FloaterViewModel;
import com.dk.pgt.pokemonSearch.PokemonSearchContract;
import com.dk.pgt.pokemonSearch.PokemonSearchView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by douglaskazumi on 8/31/16.
 */

public class EvolveView extends PokemonSearchView implements EvolveContract.View {
    @Inject
    EvolveContract.Presenter presenter;
    private EvolveBinding binding;

    public EvolveView(Context context, FloaterViewModel parentViewModel) {
        super(context, parentViewModel);
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
    protected PokemonSearchContract.Presenter setupPresenter() {
        return presenter;
    }

    @Override
    protected SearchView setupSearchView() {
        return binding.pokemonName;
    }

    @Override
    protected void setupBinding() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (LAYOUT_INFLATER_SERVICE);
        binding = EvolveBinding.inflate(inflater, this, true);
        binding.setViewModel(new EvolveViewModel(presenter));
    }

    @Override
    protected void setupInjection() {
        DaggerEvolveComponent.builder()
                .dataComponent(PGTApp.getInstance().getDataComponent())
                .evolveModule(new EvolveModule(this))
                .build()
                .inject(this);
    }
}
