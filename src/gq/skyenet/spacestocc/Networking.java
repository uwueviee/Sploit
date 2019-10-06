package gq.skyenet.spacestocc;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Networking utilities
 *
 * @author Skye Viau (PretzelCA) {@literal <skye.viau@gmail.com>}
 */

class Networking {

    /**
     * Attempts to get information from a URL object
     *
     * @param url URL object to connect to
     * @return URL response
     * @throws Throwable Throws exception if URL can't be accessed.
     */

    static String getURL(URL url) throws Throwable {
        HttpURLConnection urlConnection = null;

        StringBuilder content = new StringBuilder();

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
        } catch (Throwable e) {
            content.append(e);
            return content.toString();
        }

        int status = urlConnection.getResponseCode();


        if (status == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
        } else {
            content.append(urlConnection.getResponseCode());
        }

        urlConnection.disconnect();

        return content.toString();
    }

    /**
     * Gets current API status
     *
     * @return API status
     * @throws Throwable Throws exception if URL can't be accessed.
     */

    static String getAPIStatus() throws Throwable {
        URL apiStatusURL = new URL("http://localhost:3000/apiStatus");
        return getURL(apiStatusURL);
    }

    /**
     * Gets current news from an API
     *
     * @return Current news in an JSONArray
     * @throws Throwable Throws exception if URL can't be accessed.
     */

    static JSONArray getNews() throws Throwable {
        URL currentNewsURL = new URL("http://localhost:3000/currentNews");
        JSONTokener tokener = new JSONTokener(getURL(currentNewsURL));
        return new JSONArray(tokener);
    }

    /**
     * Gets current stock prices from an API
     *
     * @return Current stock prices in a JSONObject
     * @throws Throwable Throws exception if URL can't be accessed.
     */

    static JSONObject getStocks() throws Throwable {
        URL stocksURL = new URL("http://localhost:3000/stocks");
        JSONTokener tokener = new JSONTokener(getURL(stocksURL));
        return new JSONObject(tokener);
    }
}
