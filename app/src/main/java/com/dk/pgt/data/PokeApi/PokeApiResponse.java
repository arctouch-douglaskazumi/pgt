package com.dk.pgt.data.PokeApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class PokeApiResponse<T> {
    @SerializedName("count")
    private int totalCount;

    @SerializedName("previous")
    private String previousResultsUrl;

    private List<T> results;

    @SerializedName("next")
    private String nextResultsUrl;

    public int getTotalCount() {
        return totalCount;
    }

    public String getPreviousResultsUrl() {
        return previousResultsUrl;
    }

    public List<T> getResults() {
        return results;
    }

    public String getNextResultsUrl() {
        return nextResultsUrl;
    }
}
