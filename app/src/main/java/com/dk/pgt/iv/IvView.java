package com.dk.pgt.iv;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.dk.pgt.PGTApp;
import com.dk.pgt.data.PgoivApi.PgoivApiResponse;
import com.dk.pgt.data.PgoivApi.SummaryData;
import com.dk.pgt.databinding.IvCalcBinding;
import com.dk.pgt.floater.FloaterViewModel;
import com.dk.pgt.pokemonSearch.PokemonSearchContract;
import com.dk.pgt.pokemonSearch.PokemonSearchView;

import java.util.Locale;

import javax.inject.Inject;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by douglaskazumi on 8/31/16.
 */

public class IvView extends PokemonSearchView implements IvContract.View {
    @Inject
    IvContract.Presenter presenter;
    private IvCalcBinding binding;

    public IvView(Context context, FloaterViewModel parentViewModel) {
        super(context, parentViewModel);
    }

    @Override
    public void showResult(PgoivApiResponse response) {
        StringBuilder builder = new StringBuilder();
        SummaryData data = response.getData();
        builder.append(
                String.format(Locale.US,
                        "Perfection: %.2f - %.2f",
                        data.getMinPerfection(),
                        data.getMaxPerfection()));
        builder.append(System.lineSeparator());
        builder.append(
                String.format(Locale.US,
                        "Evolution CP: %d - %d",
                        data.getMinEstimatedEvolutionCp(),
                        data.getMaxEstimatedEvolutionCp()));

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
        binding = IvCalcBinding.inflate(inflater, this, true);
        binding.setViewModel(new IvViewModel(presenter));
    }

    @Override
    protected void setupInjection() {
        DaggerIvComponent.builder()
                .dataComponent(PGTApp.getInstance().getDataComponent())
                .ivModule(new IvModule(this))
                .build()
                .inject(this);
    }
}
