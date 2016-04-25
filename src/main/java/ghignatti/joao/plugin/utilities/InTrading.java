package ghignatti.joao.plugin.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InTrading {

    public InTrading(Player player, Player target) {

        setPlayer(player);
        setTarget(target);
        setStatus(true);
        setSenderGreenBlock(false);
        setTargetGreenBlock(false);
    }

    private String player;
    private String target;

    private boolean status;
    private boolean senderGreenBlock;
    private boolean targetGreenBlock;

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

    //Sender Green Block
    public void setSenderGreenBlock(boolean greenBlock) {
        this.senderGreenBlock = greenBlock;
    }

    public boolean isSenderGreenBlock() {
        return senderGreenBlock;
    }

    //Target Green Block
    public void setTargetGreenBlock(boolean greenBlock) {
        this.targetGreenBlock = greenBlock;
    }

    public boolean isTargetGreenBlock() {
        return targetGreenBlock;
    }
}
