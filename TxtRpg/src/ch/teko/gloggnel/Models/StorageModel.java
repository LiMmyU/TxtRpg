package ch.teko.gloggnel.Models;

import java.io.*;

public class StorageModel {

    // Saves Character to a File
    public static void saveCharacter(PlayerModel character) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ch/teko/gloggnel/character.dat"))) {
            out.writeObject(character);
            System.out.println("Charakter wurde erfolgreich gespeichert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads Character from File
    public static PlayerModel loadCharacter() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("ch/teko/gloggnel/character.dat"))) {
            PlayerModel character = (PlayerModel) in.readObject();
            System.out.println("Charakter wurde erfolgreich geladen.");
            return character;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
