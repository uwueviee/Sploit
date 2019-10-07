package gq.skyenet.spacestocc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

/**
 * Manages the Discord RPC connection
 *
 * @author Skye Viau (PretzelCA) {@literal <skye.viau@gmail.com>}
 */

class Discord {

    /**
     * DiscordRPC Instance Object
     */

    static DiscordRPC discordLib = DiscordRPC.INSTANCE;

    /**
     * Discord Application ID
     */

    private static String applicationId = "630462473765650469";

    /**
     * Starts the Discord connection
     * @see #changePresence
     */

    static void startDiscord() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        discordLib.Discord_Initialize(applicationId, handlers, false, null);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                discordLib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }

    /**
     * Changes the Discord rich presence
     *
     * @param largeImage Large image key as found on Discord
     * @param largeImageText Large image detail
     * @param smallImage Small image key as found on Discord
     * @param smallImageText Small image detail
     * @param state User's current status
     * @param details User's current action
     * @see #startDiscord
     */

    static void changePresence(String largeImage, String largeImageText, String smallImage, String smallImageText, String state, String details) {
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.details = details;
        presence.state = state;
        presence.smallImageKey = smallImage;
        presence.smallImageText = smallImageText;
        presence.largeImageKey = largeImage;
        presence.largeImageText = largeImageText;

        discordLib.Discord_UpdatePresence(presence);
    }

    /**
     * Shuts down the Discord connection (Not to be confused with {@link Util#shutdown})
     * @see #startDiscord
     */

    static void stopDiscord() {
        discordLib.Discord_Shutdown();
    }
}
