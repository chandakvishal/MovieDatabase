package com.test.omdb.project;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * author vcchanda on 2019-07-25.
 */
public class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        System.out.println("resultString = " + resultString);
        return resultString.length() > 0
               ? resultString.substring(0, resultString.length() - 1)
               : resultString;
    }

    public static URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        return new URI(oldUri.getScheme(), oldUri.getAuthority(), oldUri.getPath(), newQuery, oldUri.getFragment());
    }
}
