package xyz.tomszir.urpg.___old.managers.player;

public class CustomPlayerExtension {

    private CustomPlayer player;
    private PlayerFile file;

    public CustomPlayerExtension(CustomPlayer player) {
        this.player = player;
        this.file = player.getFile();
    }

    public CustomPlayer getPlayer() {
        return player;
    }

    public PlayerFile getPlayerFile() {
        return file;
    }
}
