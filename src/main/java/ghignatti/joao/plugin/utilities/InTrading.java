package ghignatti.joao.plugin.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InTrading {

    public InTrading(Player player, Player target, boolean status) {

        setPlayer(player);
        setTarget(target);
        setStatus(status);
    }

    private String player;
    private String target;

    private boolean status;

    //Player
    private void setPlayer(Player player) {
        this.player = player.getName();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(player);
    }

    //Target
    private void setTarget(Player target) {
        this.target = target.getName();
    }

    public Player getTarget() {
        return Bukkit.getPlayer(target);
    }

    //Status
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
