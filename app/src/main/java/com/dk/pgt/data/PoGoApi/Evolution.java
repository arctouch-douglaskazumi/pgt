package com.dk.pgt.data.PoGoApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by douglaskazumi on 8/18/16.
 */
public class Evolution {
    @SerializedName("estimate")
    private int cpEstimate;

    @SerializedName("range")
    private List<Integer> cpRange;

    private List<Double> levels;

    @SerializedName("target")
    private List<String> info;

    private List<Integer> spread;

    public Evolution() {
    }

    public Evolution(int cpEstimate, List<Integer> cpRange, List<Double> levels, List<String>
            info, List<Integer> spread) {
        this.cpEstimate = cpEstimate;
        this.cpRange = cpRange;
        this.levels = levels;
        this.info = info;
        this.spread = spread;
    }

    public int getCpEstimate() {
        return cpEstimate;
    }

    public int getMinCp() {
        return cpRange.get(0);
    }

    public int getMaxCp() {
        return cpRange.get(1);
    }

    public String getName() {
        return info.get(0);
    }

    public String getImage() {
        return info.get(1);
    }

    public String getPage() {
        return info.get(2);
    }

    public List<Double> getLevels() {
        return levels;
    }

    public List<Integer> getSpread() {
        return spread;
    }
}
