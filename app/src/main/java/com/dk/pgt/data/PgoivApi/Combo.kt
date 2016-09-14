package com.dk.pgt.data.PgoivApi

import com.google.gson.annotations.SerializedName

/**
 * Created by douglaskazumi on 9/12/16.
 */
data class Combo(
        @SerializedName("atkIV") var attackIV: Int,
        @SerializedName("defIV") var defenseIV: Int,
        @SerializedName("staIV") var staminaIV: Int,
        @SerializedName("level") var level: Float,
        @SerializedName("cpMult") var cpMultiplier: Float,
        @SerializedName("perfectPrecision") var perfection: Float,
        @SerializedName("CP") var cp: Int
)
