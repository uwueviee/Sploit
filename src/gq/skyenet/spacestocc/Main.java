package gq.skyenet.spacestocc;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main runnable class
 *
 * @author Skye Viau (PretzelCA) {@literal <skye.viau@gmail.com>}
 */

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        Util.clearScreen();

        System.out.println("Starting SpaceStocc");
        System.out.println("======================");

        System.out.println("Getting API status");
        if (!Networking.getAPIStatus().equals("OK")) {
            Util.errorScreen("API STATUS", "STARTING", Networking.getAPIStatus());
        }

        System.out.println("Getting current news");
        if (Networking.getNews().equals("Unavailable")) {
            Util.errorScreen("NEWS API", "STARTING", null);
        }

    }
}
