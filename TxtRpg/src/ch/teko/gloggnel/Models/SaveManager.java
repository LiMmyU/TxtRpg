package ch.teko.gloggnel.Models;

import java.io.*;

public class SaveManager {

    private static final String SAVE_FILE = "player.sav";

    public static void savePlayer(Player player) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(player);
            System.out.println("Player saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadPlayer() {
        Player player = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            player = (Player) ois.readObject();
            System.out.println("Player loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved game data found. A new player will be created.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return player;
    }
}

