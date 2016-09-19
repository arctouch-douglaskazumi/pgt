package com.dk.pgt.evolve;

import com.dk.pgt.BaseTest;
import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.data.PoGoApi.PoGoApi;
import com.dk.pgt.data.PokeApi.PokeApi;
import com.dk.pgt.data.PokeApi.PokeApiResponse;
import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.data.PokeApi.PokemonApi;
import com.google.gson.Gson;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by douglaskazumi on 9/15/16.
 */

public class EvolvePresenterTest extends BaseTest {

    @Mock
    EvolveView view;
    @Mock
    PoGoApi poGoApi;
    @Mock
    PokemonApi pokeApi;

    @InjectMocks
    EvolvePresenter presenter;

    @Test
    public void testCalculateEvolutionsWithoutPokemonSelected() {
        //given
        when(pokeApi.getPokemons(151, null)).thenReturn(mockPokemonApiGetPokemons());
        when(poGoApi.getEvolutions(1,12)).thenReturn(mockPoGoApiGetEvolutions());
        presenter.loadPokemons();
        presenter.filterSuggestions("bulba");

        //when
        presenter.calculateEvolutions(12);

        //then
        verify(view, atLeastOnce()).presentError(any());
    }

    @Test
    public void testCalculateEvolutions() {
        //given
        when(pokeApi.getPokemons(151, null)).thenReturn(mockPokemonApiGetPokemons());
        when(poGoApi.getEvolutions(1,12)).thenReturn(mockPoGoApiGetEvolutions());
        presenter.loadPokemons();
        presenter.filterSuggestions("bulba");
        presenter.getSelectedPokemon(0);

        //when
        presenter.calculateEvolutions(12);

        //then
        verify(view, atLeastOnce()).showEvolutions(any());
    }

    private Observable<PokeApiResponse<Pokemon>> mockPokemonApiGetPokemons() {
        Gson gson = new Gson();

        return Observable.just(gson.fromJson("{" +
                "  \"count\": 811," +
                "  \"previous\": null," +
                "  \"results\": [" +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/1/\"," +
                "      \"name\": \"bulbasaur\"" +
                "    }" +
                "  ]," +
                "  \"next\": \"http://pokeapi.co/api/v2/pokemon/?limit=151&offset=151\"" +
                "}", PokeApi.MockApi.class));
    }

    private Observable<List<Evolution>> mockPoGoApiGetEvolutions() {
        List<Evolution> evolutions = new ArrayList<>();

        evolutions.add(new Evolution(
                19,
                Arrays.asList(19, 19),
                Arrays.asList(1.0, 1.0),
                Arrays.asList(
                        "Ivysaur",
                        "/images/pokemon/2.png",
                        "/pokemon/2-ivysaur"),
                Arrays.asList(18, 23)
        ));

        evolutions.add(new Evolution(
                32,
                Arrays.asList(32, 32),
                Arrays.asList(1.0, 1.0),
                Arrays.asList(
                        "Venusaur",
                        "/images/pokemon/3.png",
                        "/pokemon/3-venusaur"),
                Arrays.asList(31, 36)
        ));

        return Observable.just(evolutions);
    }

    @Test
    public void TestPokemonNumber() {
        Gson gson = new Gson();
        Pokemon parsedObjetct = gson.fromJson(
                "    {" +
                        "      \"url\": \"http://pokeapi.co/api/v2/pokemon/5000/\"," +
                        "      \"name\": \"bulbasaur\"" +
                        "    }", Pokemon.class);

        assertEquals(parsedObjetct.getNumber(), 5000);
    }
}
