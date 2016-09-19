package com.dk.pgt.data.PokeApi


import com.dk.pgt.BaseTest
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Created by douglaskazumi on 9/16/16.
 */
class PokemonTest : BaseTest() {
    @Test
    fun testPokemonNumber() {
        val pokemon = createStubPokemon()

        assertEquals(pokemon.number, 1)
    }

    @Test
    fun testPokemonNumber2Digits() {
        val number = 12;
        val pokemon = createStubPokemon(number = number)

        assertEquals(pokemon.number, number)
        //For coverage purposes only :D
        assertEquals(pokemon.infoApiUrl,"http://pokeapi.co/api/v2/pokemon/$number/")
    }

    @Test
    fun testPokemonNumber3Digits() {
        val pokemon = createStubPokemon(number = 151)

        assertEquals(pokemon.number, 151)
    }

    @Test
    fun testPokemonNumberWithInvalidUrl() {
        val pokemon = createStubPokemon(number = "")

        assertEquals(pokemon.number, 0)
    }

    fun createStubPokemon(name: String = "bulbasaur", number: Int = 1): Pokemon {
        return createStubPokemon(name, number.toString())
    }

    fun createStubPokemon(name: String = "bulbasaur", number: String): Pokemon {
        val gson = Gson()
        val baseJson = "{" +
                "\"url\": \"http://pokeapi.co/api/v2/pokemon/$number/\", " +
                "\"name\": \"$name\" }"
        return gson.fromJson(baseJson, Pokemon::class.java)
    }
}