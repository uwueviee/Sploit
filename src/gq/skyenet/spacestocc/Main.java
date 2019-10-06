package gq.skyenet.spacestocc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

/**
 * Main runnable class
 *
 * @author Skye Viau (PretzelCA) {@literal <skye.viau@gmail.com>}
 */

public class Main {
    public static void main(String[] args) throws Throwable {
        Scanner input = new Scanner(System.in);

        Discord.startDiscord();
        Discord.changePresence("mainIcon", "SpaceStocc", "", "", "Doing Nothing", "In Main Menu");

        Util.clearScreen();

        // Start SpaceStocc and get all information required from the online API

        System.out.println("Starting SpaceStocc");
        System.out.println("======================");

        System.out.println("Getting API status");
        if (!Networking.getAPIStatus().equals("OK")) {
            Util.errorScreen("API STATUS", "STARTING", "API Status reported " + Networking.getAPIStatus());
        }

        System.out.println("Getting current news");
        JSONArray news = Networking.getNews();
        if (news.equals("Unavailable")) {
            Util.errorScreen("NEWS API", "STARTING", "News API is currently unavailable");
        }

        System.out.println("Getting current stock prices");
        JSONObject stocks = Networking.getStocks();
        if (stocks.equals("Unavailable")) {
            Util.errorScreen("STOCKS API", "STARTING", "Stocks API is currently unavailable");
        }

        Util.clearScreen();

        // Home screen
        System.out.println("SpaceStocc");
        System.out.println("=============");
        System.out.println(" ");

        System.out.println("Most recent news article: ");
        System.out.println(news.getJSONObject(0).get("title"));
        System.out.println(LocalDateTime.parse(news.getJSONObject(0).get("time").toString()).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.CANADA).withZone(ZoneId.of("GMT"))));
        System.out.println("=====");
        System.out.println(news.getJSONObject(0).get("content"));

        for (;;) {
            String yeet = input.nextLine();
            if(yeet.equals("pp")) {
                Util.shutdown(0);
                break;
            }
        }
    }
}
