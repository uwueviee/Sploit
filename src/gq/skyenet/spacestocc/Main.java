package gq.skyenet.spacestocc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Console;
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

    /**
     * Stocks JSONObject
     */

    static JSONObject stocks;

    /**
     * News JSONObject
     */

    static JSONArray news;

    /**
     * The user object in JSONObject form
     */

    static JSONObject userObject;

    /**
     * Main method for SpaceStocc
     * @param args Input arguments
     * @throws Throwable Any errors that occur while running
     */

    public static void main(String[] args) throws Throwable {
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
        news = Networking.getNews();
        if (news.equals("Unavailable")) {
            Util.errorScreen("NEWS API", "STARTING", "News API is currently unavailable");
        }

        System.out.println("Getting current stock prices");
        stocks = Networking.getStocks();
        if (stocks.equals("Unavailable")) {
            Util.errorScreen("STOCKS API", "STARTING", "Stocks API is currently unavailable");
        }

        for (;;) {
            Util.clearScreen();

            // Profile selection screen
            System.out.println("SpaceStocc");
            System.out.println("=============");
            System.out.println(" ");

            System.out.println("1 - Log into existing profile");
            System.out.println("2 - Create new profile");
            System.out.println(" ");
            System.out.println("3 - Exit");

            Scanner input = new Scanner(System.in);

            System.out.print("> ");
            String mainMenuChoice = input.nextLine();

            if (mainMenuChoice.equals("1")) {
                logIn();
            } else if (mainMenuChoice.equals("2")) {
                createNewUser();
            } else if (mainMenuChoice.equals("3")) {
                Util.shutdown(0);
            }
        }
    }

    /**
     * Shows login screen
     */

    private static void logIn() {
        Console cnsl = System.console();

        Util.clearScreen();

        System.out.println("SpaceStocc");
        System.out.println("=============");
        System.out.println(" ");

        System.out.println("Welcome back investor!");
        System.out.println("Please enter a username and password for your account.");
        System.out.println(" ");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter username > ");
        String name = input.nextLine();

        // Check for null console

        String pass;

        if (cnsl == null) {
            System.out.print("Enter password > ");
            pass = input.nextLine();
        } else {
            char[] passInput = cnsl.readPassword("Enter password > ");
            pass = new String(passInput);
        }

        if (User.loadUser(name, pass) != null) {
            userObject = User.loadUser(name, pass);
            mainMenu();
        } else {
            logIn();
        }
    }

    /**
     * Shows create new user screen
     */

    private static void createNewUser() {
        Console cnsl = System.console();

        Util.clearScreen();

        System.out.println("SpaceStocc");
        System.out.println("=============");
        System.out.println(" ");

        System.out.println("Welcome investor to the SSX! Here you'll be able to trade stocks from all sorts of planets! You'll also be able to trade other commodities like currency and resources!");
        System.out.println("To get started, please enter a username and password for your account.");
        System.out.println(" ");

        Scanner input = new Scanner(System.in);

        System.out.print("Enter username > ");
        String name = input.nextLine();

        // Check for null console

        String pass;

        if (cnsl == null) {
            System.out.print("Enter password > ");
            pass = input.nextLine();
        } else {
            char[] passInput = cnsl.readPassword("Enter password > ");
            pass = new String(passInput);
        }

        User.createUser(name, pass);

        System.out.println("SpaceStocc");
        System.out.println("=============");
        System.out.println(" ");

        System.out.println("Success! Press enter to go to your console.");
        input.nextLine();

        mainMenu();
    }

    /**
     * Shows the main menu
     */

    private static void mainMenu() {
        Scanner input = new Scanner(System.in);

        Util.clearScreen();

        // Home screen
        System.out.println("SpaceStocc");
        System.out.println("=============");
        System.out.println(" ");

        System.out.println("Most recent news article: ");
        System.out.println(news.getJSONObject(0).get("title"));
        System.out.println(LocalDateTime.parse(news.getJSONObject(0).get("time").toString()).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.CANADA).withZone(ZoneId.of("GMT"))));
        System.out.println("=============");
        System.out.println(news.getJSONObject(0).get("content"));
        System.out.println(" ");

        System.out.println("Watchlist:");
        System.out.println("=============");

        for (;;) {
            String yeet = input.nextLine();
            if(yeet.equals("pp")) {
                Util.shutdown(0);
                break;
            }
        }
    }
}
