package ghignatti.joao.plugin.utilities;

import org.bukkit.entity.Player;

public class InTrading {

    public InTrading(Player player, Player target, boolean status) {

        setPlayer(player);
        setTarget(target);
        setStatus(status);
    }

    private Player player;
    private Player target;

    private boolean status;

    //Player
    private void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    //Target
    private void setTarget(Player target) {
        this.target = target;
    }

    public Player getTarget() {
        return target;
    }

    //Status
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
