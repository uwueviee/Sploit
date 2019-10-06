package gq.skyenet.spacestocc;

/**
 * Misc utilities
 */

class Util {

    /**
     * Flushes the screen
     */

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Shows an error screen, otherInformation can be set to null
     * @param errorType
     * @param errorLocation
     * @param otherInformation
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

        System.exit(1);
    }
}
