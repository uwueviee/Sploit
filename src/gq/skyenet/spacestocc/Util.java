package gq.skyenet.spacestocc;

/**
 * Misc utilities
 *
 * @author Skye Viau (PretzelCA) {@literal <skye.viau@gmail.com>}
 */

class Util {

    /**
     * Flushes the screen using an escape code (Will only work on POSIX systems)
     */

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Shows an error screen
     *
     * @param errorType Type of error
     * @param errorLocation Location of error
     * @param otherInformation Any other information (Can be set to null)
     */

    static void errorScreen(String errorType, String errorLocation, String otherInformation) {
        Util.clearScreen();

        System.out.println("SpaceStocc");
        System.out.println("=============");
        System.out.println(" ");
        System.out.println("An error has occurred in the " + errorType + " method, error location is at " + errorLocation);
        System.out.println(" ");

        if (otherInformation != null) {
            System.out.println("Other information: " + otherInformation);
        }

        shutdown(1);
    }

    /**
     * Safely shuts down SpaceStocc (Not to be confused with {@link Discord#stopDiscord})
     *
     * @param shutdownCode Error code to report
     */

    static void shutdown(int shutdownCode) {
        Discord.discordLib.Discord_Shutdown();
        System.exit(shutdownCode);
    }
}
