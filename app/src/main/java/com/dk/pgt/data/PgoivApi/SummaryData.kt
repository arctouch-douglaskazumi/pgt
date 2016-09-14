package com.dk.pgt.data.PgoivApi

import com.google.gson.annotations.SerializedName

/**
 * Created by douglaskazumi on 9/12/16.
 */
data class SummaryData(
        @SerializedName("maxPrec") var maxPerfection: Float,
        @SerializedName("minPrec") var minPerfection: Float,
        @SerializedName("maxCP") var maxCpLevelCap: Int,
        @SerializedName("minCP") var minCpLevelCap: Int,
        @SerializedName("maxCPAfterEvolve") var maxEstimatedEvolutionCp: Int,
        @SerializedName("minCPAfterEvolve") var minEstimatedEvolutionCp: Int,
        @SerializedName("lowestLevel") var lowestLevel: Float,
        @SerializedName("isTrainerLevelIncluded") var trainerLevelIncluded: Boolean,
        @SerializedName("stardustRequired") var stardustRequiredToCap: Int,
        @SerializedName("maxCPEvo") var maxEvolutionCp: Int,
        @SerializedName("minCPEvo") var minEvolutionCp: Int,
        var cpCap: Int
)