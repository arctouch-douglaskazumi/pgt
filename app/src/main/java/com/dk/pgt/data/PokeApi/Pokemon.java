package com.dk.pgt.data.PokeApi;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by douglaskazumi on 8/20/16.
 */
public class Pokemon {
    private final String URL_NUMBER_PATTERN = "/\\d+/$";
    private String name;

    @SerializedName("url")
    private String infoApiUrl;

    public String getName() {
        return name;
    }

    public String getInfoApiUrl() {
        return infoApiUrl;
    }

    public int getNumber() {
        Matcher matcher = Pattern.compile(URL_NUMBER_PATTERN).matcher(infoApiUrl);
        if (matcher.find()) {
            String urlId = infoApiUrl
                    .substring(matcher.start(), matcher.end())
                    .replace("/", "");
            return Integer.parseInt(urlId);
        }
        return 0;
    }
}
