package gq.skyenet.spacestocc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Networking utilities
 */

public class Networking {

    /**
     * Attempts to get information from a URL object
     *
     * @param url
     * @return URL response
     * @throws IOException
     */

    static String getURl(URL url) throws IOException {
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
     * @throws IOException
     */

    static String getAPIStatus() throws IOException {
        URL apiStatusURL = new URL("http://localhost:3000/apiStatus");
        return getURl(apiStatusURL);
    }

    /**
     * Gets current news from API
     *
     * @return Current News
     * @throws IOException
     */

    static String getNews() throws IOException {
        URL currentNewsURL = new URL("http://localhost:3000/currentNews");
        return getURl(currentNewsURL);
    }
}
