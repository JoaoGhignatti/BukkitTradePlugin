package ghignatti.joao.plugin.action;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryCloseAction {

    private ArrayList<ItemStack> senderArray = new ArrayList<ItemStack>();
    private ArrayList<ItemStack> targetArray = new ArrayList<ItemStack>();

    public InventoryCloseAction(Player p, Player t) {

        searchTopInv(p, t);
        placeBackItens(p, senderArray);
        placeBackItens(t, targetArray);
    }

    private void searchTopInv(Player p, Player t) {

        for(int i=0; i<36; i++) {

            //p
            if(i%9 < 4 && i != 29 && i != 30) {

                ItemStack item = p.getOpenInventory().getTopInventory().getItem(i);

                if(item != null) {

                    if(senderArray.isEmpty()) {

                        senderArray.add(item);

                    } else {

                        int index = searchArray(item, senderArray);

                        if(index != -1) {

                            int amount = senderArray.get(index).getAmount();

                            ItemStack itemStack = new ItemStack(item.getType(), (amount + item.getAmount()));

                            senderArray.remove(index);
                            senderArray.add(itemStack);

                        } else {

                            senderArray.add(item);
                        }
                    }
                }
            //t
            } else if(i%9 > 4 && i != 32 && i != 33) {

                ItemStack item = t.getOpenInventory().getTopInventory().getItem(i);

                if(item != null) {

                    if(targetArray.isEmpty()) {

                        targetArray.add(item);

                    } else {

                        int index = searchArray(item, targetArray);

                        if(index != -1) {

                            int amount = targetArray.get(index).getAmount();

                            ItemStack itemStack = new ItemStack(item.getType(), (amount + item.getAmount()));

                            targetArray.remove(index);
                            targetArray.add(itemStack);

                        } else {

                            targetArray.add(item);
                        }
                    }
                }
            }
        }
    }

    private int searchArray(ItemStack item, ArrayList<ItemStack> arrayList) {

        for(int i=0; i<arrayList.size(); i++) {

            if(item.getType().equals(arrayList.get(i).getType())) {

                return i;
            }
        }

        return -1;
    }

    private void placeBackItens(Player aux, ArrayList<ItemStack> arrayList) {

        for(ItemStack item : arrayList) {

            aux.getInventory().addItem(item);
        }
    }
}
