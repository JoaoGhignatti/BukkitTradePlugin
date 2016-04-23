package ghignatti.joao.plugin.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IgnoredPlayers {

    private String player;
    private String target;
    private long time;

    public IgnoredPlayers(Player player, Player target) {

        this.player = player.getName();
        this.target = target.getName();
        this.time = System.currentTimeMillis();
    }

    //Player
    public Player getPlayer() {
        return Bukkit.getPlayer(player);
    }

    //Target
    public Player getTarget() {
        return Bukkit.getPlayer(target);
    }

    //Time
    public long getTime() {
        return time;
    }
}
