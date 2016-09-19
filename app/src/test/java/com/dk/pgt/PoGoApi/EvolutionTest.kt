package com.dk.pgt.PoGoApi

import com.dk.pgt.BaseTest
import com.dk.pgt.data.PoGoApi.Evolution
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Created by douglaskazumi on 9/16/16.
 */
class EvolutionTest : BaseTest() {
    val evolutions: List<Evolution> = createStubEvolutions()

    @Test
    fun testEvolutionName() {
        assertEquals(evolutions.get(0).name, "Ivysaur")
    }

    @Test
    fun testEvolutionImage() {
        assertEquals(evolutions.get(0).image, "/images/pokemon/2.png")
    }

    @Test
    fun testEvolutionPage() {
        assertEquals(evolutions.get(0).page, "/pokemon/2-ivysaur")
    }

    @Test
    fun testEvolutionCp() {
        assertEquals(evolutions.get(0).cpEstimate, 19)
        assertEquals(evolutions.get(0).minCp, 19)
        assertEquals(evolutions.get(0).maxCp, 19)
    }

    @Test
    fun testEvolutionLevels() {
        assertEquals(evolutions.get(0).levels.get(0),1.0, 0.0)
        assertEquals(evolutions.get(0).levels.get(1),1.0, 0.0)
    }

    @Test
    fun testEvolutionSpread() {
        assertEquals(evolutions.get(0).spread.get(0),18)
        assertEquals(evolutions.get(0).spread.get(1),23)
    }

    fun createStubEvolutions(): List<Evolution> {
        val gson = Gson()

        val json = "[" +
                "  {" +
                "    \"estimate\": 19," +
                "    \"range\": [" +
                "      19," +
                "      19" +
                "    ]," +
                "    \"levels\": [" +
                "      1," +
                "      1" +
                "    ]," +
                "    \"target\": [" +
                "      \"Ivysaur\"," +
                "      \"/images/pokemon/2.png\"," +
                "      \"/pokemon/2-ivysaur\"" +
                "    ]," +
                "    \"spread\": [" +
                "      18," +
                "      23" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"estimate\": 32," +
                "    \"range\": [" +
                "      32," +
                "      32" +
                "    ]," +
                "    \"levels\": [" +
                "      1," +
                "      1" +
                "    ]," +
                "    \"target\": [" +
                "      \"Venusaur\"," +
                "      \"/images/pokemon/3.png\"," +
                "      \"/pokemon/3-venusaur\"" +
                "    ]," +
                "    \"spread\": [" +
                "      31," +
                "      36" +
                "    ]" +
                "  }" +
                "]"

        return gson.fromJson(json, Evolutions::class.java)
    }

    class Evolutions : ArrayList<Evolution>() {

    }
}