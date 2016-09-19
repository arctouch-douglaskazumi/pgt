package com.dk.pgt.data.PokeApi;

import com.google.gson.Gson;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class PokeApi implements PokemonApi {
    public static final String BASE_URL = "http://pokeapi.co/api/v2/";

    PokeApiRetrofit mApi;

    public PokeApi(PokeApiRetrofit retrofit) {
        this.mApi = retrofit;
    }


    public class MockApi extends PokeApiResponse<Pokemon>{

    }

    @Override
    public Observable<PokeApiResponse<Pokemon>> getPokemons(Integer limit, Integer offset) {
//region Mock

        Gson gson = new Gson();

        PokeApiResponse parsedObjetct = gson.fromJson("{" +
                "  \"count\": 811," +
                "  \"previous\": null," +
                "  \"results\": [" +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/1/\"," +
                "      \"name\": \"bulbasaur\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/2/\"," +
                "      \"name\": \"ivysaur\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/3/\"," +
                "      \"name\": \"venusaur\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/4/\"," +
                "      \"name\": \"charmander\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/5/\"," +
                "      \"name\": \"charmeleon\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/6/\"," +
                "      \"name\": \"charizard\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/7/\"," +
                "      \"name\": \"squirtle\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/8/\"," +
                "      \"name\": \"wartortle\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/9/\"," +
                "      \"name\": \"blastoise\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/10/\"," +
                "      \"name\": \"caterpie\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/11/\"," +
                "      \"name\": \"metapod\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/12/\"," +
                "      \"name\": \"butterfree\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/13/\"," +
                "      \"name\": \"weedle\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/14/\"," +
                "      \"name\": \"kakuna\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/15/\"," +
                "      \"name\": \"beedrill\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/16/\"," +
                "      \"name\": \"pidgey\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/17/\"," +
                "      \"name\": \"pidgeotto\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/18/\"," +
                "      \"name\": \"pidgeot\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/19/\"," +
                "      \"name\": \"rattata\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/20/\"," +
                "      \"name\": \"raticate\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/21/\"," +
                "      \"name\": \"spearow\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/22/\"," +
                "      \"name\": \"fearow\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/23/\"," +
                "      \"name\": \"ekans\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/24/\"," +
                "      \"name\": \"arbok\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/25/\"," +
                "      \"name\": \"pikachu\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/26/\"," +
                "      \"name\": \"raichu\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/27/\"," +
                "      \"name\": \"sandshrew\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/28/\"," +
                "      \"name\": \"sandslash\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/29/\"," +
                "      \"name\": \"nidoran-f\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/30/\"," +
                "      \"name\": \"nidorina\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/31/\"," +
                "      \"name\": \"nidoqueen\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/32/\"," +
                "      \"name\": \"nidoran-m\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/33/\"," +
                "      \"name\": \"nidorino\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/34/\"," +
                "      \"name\": \"nidoking\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/35/\"," +
                "      \"name\": \"clefairy\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/36/\"," +
                "      \"name\": \"clefable\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/37/\"," +
                "      \"name\": \"vulpix\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/38/\"," +
                "      \"name\": \"ninetales\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/39/\"," +
                "      \"name\": \"jigglypuff\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/40/\"," +
                "      \"name\": \"wigglytuff\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/41/\"," +
                "      \"name\": \"zubat\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/42/\"," +
                "      \"name\": \"golbat\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/43/\"," +
                "      \"name\": \"oddish\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/44/\"," +
                "      \"name\": \"gloom\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/45/\"," +
                "      \"name\": \"vileplume\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/46/\"," +
                "      \"name\": \"paras\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/47/\"," +
                "      \"name\": \"parasect\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/48/\"," +
                "      \"name\": \"venonat\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/49/\"," +
                "      \"name\": \"venomoth\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/50/\"," +
                "      \"name\": \"diglett\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/51/\"," +
                "      \"name\": \"dugtrio\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/52/\"," +
                "      \"name\": \"meowth\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/53/\"," +
                "      \"name\": \"persian\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/54/\"," +
                "      \"name\": \"psyduck\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/55/\"," +
                "      \"name\": \"golduck\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/56/\"," +
                "      \"name\": \"mankey\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/57/\"," +
                "      \"name\": \"primeape\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/58/\"," +
                "      \"name\": \"growlithe\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/59/\"," +
                "      \"name\": \"arcanine\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/60/\"," +
                "      \"name\": \"poliwag\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/61/\"," +
                "      \"name\": \"poliwhirl\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/62/\"," +
                "      \"name\": \"poliwrath\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/63/\"," +
                "      \"name\": \"abra\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/64/\"," +
                "      \"name\": \"kadabra\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/65/\"," +
                "      \"name\": \"alakazam\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/66/\"," +
                "      \"name\": \"machop\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/67/\"," +
                "      \"name\": \"machoke\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/68/\"," +
                "      \"name\": \"machamp\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/69/\"," +
                "      \"name\": \"bellsprout\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/70/\"," +
                "      \"name\": \"weepinbell\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/71/\"," +
                "      \"name\": \"victreebel\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/72/\"," +
                "      \"name\": \"tentacool\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/73/\"," +
                "      \"name\": \"tentacruel\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/74/\"," +
                "      \"name\": \"geodude\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/75/\"," +
                "      \"name\": \"graveler\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/76/\"," +
                "      \"name\": \"golem\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/77/\"," +
                "      \"name\": \"ponyta\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/78/\"," +
                "      \"name\": \"rapidash\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/79/\"," +
                "      \"name\": \"slowpoke\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/80/\"," +
                "      \"name\": \"slowbro\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/81/\"," +
                "      \"name\": \"magnemite\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/82/\"," +
                "      \"name\": \"magneton\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/83/\"," +
                "      \"name\": \"farfetchd\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/84/\"," +
                "      \"name\": \"doduo\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/85/\"," +
                "      \"name\": \"dodrio\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/86/\"," +
                "      \"name\": \"seel\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/87/\"," +
                "      \"name\": \"dewgong\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/88/\"," +
                "      \"name\": \"grimer\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/89/\"," +
                "      \"name\": \"muk\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/90/\"," +
                "      \"name\": \"shellder\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/91/\"," +
                "      \"name\": \"cloyster\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/92/\"," +
                "      \"name\": \"gastly\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/93/\"," +
                "      \"name\": \"haunter\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/94/\"," +
                "      \"name\": \"gengar\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/95/\"," +
                "      \"name\": \"onix\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/96/\"," +
                "      \"name\": \"drowzee\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/97/\"," +
                "      \"name\": \"hypno\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/98/\"," +
                "      \"name\": \"krabby\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/99/\"," +
                "      \"name\": \"kingler\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/100/\"," +
                "      \"name\": \"voltorb\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/101/\"," +
                "      \"name\": \"electrode\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/102/\"," +
                "      \"name\": \"exeggcute\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/103/\"," +
                "      \"name\": \"exeggutor\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/104/\"," +
                "      \"name\": \"cubone\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/105/\"," +
                "      \"name\": \"marowak\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/106/\"," +
                "      \"name\": \"hitmonlee\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/107/\"," +
                "      \"name\": \"hitmonchan\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/108/\"," +
                "      \"name\": \"lickitung\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/109/\"," +
                "      \"name\": \"koffing\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/110/\"," +
                "      \"name\": \"weezing\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/111/\"," +
                "      \"name\": \"rhyhorn\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/112/\"," +
                "      \"name\": \"rhydon\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/113/\"," +
                "      \"name\": \"chansey\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/114/\"," +
                "      \"name\": \"tangela\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/115/\"," +
                "      \"name\": \"kangaskhan\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/116/\"," +
                "      \"name\": \"horsea\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/117/\"," +
                "      \"name\": \"seadra\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/118/\"," +
                "      \"name\": \"goldeen\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/119/\"," +
                "      \"name\": \"seaking\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/120/\"," +
                "      \"name\": \"staryu\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/121/\"," +
                "      \"name\": \"starmie\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/122/\"," +
                "      \"name\": \"mr-mime\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/123/\"," +
                "      \"name\": \"scyther\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/124/\"," +
                "      \"name\": \"jynx\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/125/\"," +
                "      \"name\": \"electabuzz\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/126/\"," +
                "      \"name\": \"magmar\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/127/\"," +
                "      \"name\": \"pinsir\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/128/\"," +
                "      \"name\": \"tauros\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/129/\"," +
                "      \"name\": \"magikarp\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/130/\"," +
                "      \"name\": \"gyarados\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/131/\"," +
                "      \"name\": \"lapras\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/132/\"," +
                "      \"name\": \"ditto\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/133/\"," +
                "      \"name\": \"eevee\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/134/\"," +
                "      \"name\": \"vaporeon\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/135/\"," +
                "      \"name\": \"jolteon\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/136/\"," +
                "      \"name\": \"flareon\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/137/\"," +
                "      \"name\": \"porygon\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/138/\"," +
                "      \"name\": \"omanyte\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/139/\"," +
                "      \"name\": \"omastar\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/140/\"," +
                "      \"name\": \"kabuto\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/141/\"," +
                "      \"name\": \"kabutops\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/142/\"," +
                "      \"name\": \"aerodactyl\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/143/\"," +
                "      \"name\": \"snorlax\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/144/\"," +
                "      \"name\": \"articuno\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/145/\"," +
                "      \"name\": \"zapdos\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/146/\"," +
                "      \"name\": \"moltres\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/147/\"," +
                "      \"name\": \"dratini\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/148/\"," +
                "      \"name\": \"dragonair\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/149/\"," +
                "      \"name\": \"dragonite\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/150/\"," +
                "      \"name\": \"mewtwo\"" +
                "    }," +
                "    {" +
                "      \"url\": \"http://pokeapi.co/api/v2/pokemon/151/\"," +
                "      \"name\": \"mew\"" +
                "    }" +
                "  ]," +
                "  \"next\": \"http://pokeapi.co/api/v2/pokemon/?limit=151&offset=151\"" +
                "}", MockApi.class);

        return Observable.just(parsedObjetct);

//endregion

//        return mApi.getPokemons(limit, offset)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
    }

    public interface PokeApiRetrofit {
        @GET("pokemon/")
        Observable<PokeApiResponse<Pokemon>> getPokemons(@Query("limit") Integer limit, @Query
                ("offset") Integer offset);
    }
}
