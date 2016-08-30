package com.dk.pgt.data.PokeApi;

import rx.Observable;

public interface PokemonApi {
    Observable<PokeApiResponse<Pokemon>> getPokemons(Integer limit, Integer offset);
}
