package ghignatti.joao.plugin.utilities;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SlotFinder {
    private static SlotFinder ourInstance = new SlotFinder();

    public static SlotFinder getInstance() {
        return ourInstance;
    }

    private SlotFinder() {
    }

    private int searchSlot(Player p, String player, ItemStack item) {

        if(player.equals("sender")) {

            for(int i=0; i<36; i++) {

                if(i%9 > 4 && i != 32) {

                    if(p.getOpenInventory().getTopInventory() != null) {

                        if (p.getOpenInventory().getTopInventory().getItem(i) == item) {

                            return i;
                        }
                    }
                }
            }
        } else if(player.equals("target")) {

            for(int i=0; i<36; i++) {

                if(i%9 < 4 && i!= 30) {

                    if(p.getOpenInventory().getTopInventory() != null) {

                        if (p.getOpenInventory().getTopInventory().getItem(i) == item) {

                            return i;
                        }
                    }
                }
            }
        }

        return -1;
    }

    public int getSearchSlot(Player p, String player, ItemStack item) {
        return searchSlot(p, player, item);
    }
}
