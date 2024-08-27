import ch.teko.gloggnel.Models.PlayerModel;
import ch.teko.gloggnel.Models.StorageModel;
import ch.teko.gloggnel.Views.MainView;


public class Main {
    public static void main(String[] args) {
        StorageModel storage = new StorageModel();
        PlayerModel playerInit = new PlayerModel();
        storage.saveCharacter(playerInit);
        PlayerModel player = storage.loadCharacter();
        MainView view = new MainView(player, storage);
    }

}