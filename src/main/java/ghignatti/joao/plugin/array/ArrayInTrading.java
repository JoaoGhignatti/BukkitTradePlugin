package ghignatti.joao.plugin.array;

import ghignatti.joao.plugin.utilities.InTrading;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ArrayInTrading {
    private static ArrayInTrading ourInstance = new ArrayInTrading();

    public static ArrayInTrading getInstance() {
        return ourInstance;
    }

    private ArrayInTrading() {
    }

    public HashMap<Player, InTrading> tradingHash = new HashMap<Player, InTrading>();
}
