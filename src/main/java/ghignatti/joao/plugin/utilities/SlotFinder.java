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

    private int searchSlot(Player p, String player, ItemStack item, boolean unproject) {

        if(player.equals("sender")) {

            for(int i=0; i<36; i++) {

                if(i%9 > 4 && i != 32) {

                    if(p.getOpenInventory().getTopInventory() != null) {

                        if (p.getOpenInventory().getTopInventory().getItem(i) == null) {

                            return i;

                        } else if(unproject) {

                            if(p.getOpenInventory().getTopInventory().getItem(i).getType() == item.getType()) {

                                return i;
                            }
                        }
                    }
                }
            }
        } else if(player.equals("target")) {

            for(int i=0; i<36; i++) {

                if(i%9 < 4 && i!= 30) {

                    if(p.getOpenInventory().getTopInventory() != null) {

                        if (p.getOpenInventory().getTopInventory().getItem(i) == null) {

                            return i;

                        } else if(unproject) {

                            if(p.getOpenInventory().getTopInventory().getItem(i).getType() == item.getType()) {

                                return i;
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    public int getSearchSlot(Player p, String player, ItemStack item, boolean unproject) {
        return searchSlot(p, player, item, unproject);
    }
}
