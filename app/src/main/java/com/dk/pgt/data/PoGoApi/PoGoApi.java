package com.dk.pgt.data.PoGoApi;

import java.util.List;

import rx.Observable;

public interface PoGoApi {
    Observable<List<Evolution>> getEvolutions(int pokemonId, int currentCp);
}
