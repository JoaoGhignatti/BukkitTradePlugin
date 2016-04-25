package ghignatti.joao.plugin.action;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryCloseAction {

    private ArrayList<ItemStack> esquerdaArray = new ArrayList<ItemStack>();
    private ArrayList<ItemStack> direitaArray = new ArrayList<ItemStack>();

    public InventoryCloseAction(Player esquerda, Player direita) {

        searchTopInv(esquerda, direita);
        placeBackItens(esquerda, esquerdaArray);
        placeBackItens(direita, direitaArray);
    }

    private void searchTopInv(Player esquerda, Player direita) {

        for(int i=0; i<36; i++) {

            //Esquerda
            if(i%9 < 4 && i != 29 && i != 30) {

                ItemStack item = esquerda.getOpenInventory().getTopInventory().getItem(i);

                if(item != null) {

                    if(esquerdaArray.isEmpty()) {

                        esquerdaArray.add(item);

                    } else {

                        int index = searchArray(item, esquerdaArray);

                        if(index != -1) {

                            int amount = esquerdaArray.get(index).getAmount();

                            ItemStack itemStack = new ItemStack(item.getType(), (amount + item.getAmount()));

                            esquerdaArray.remove(index);
                            esquerdaArray.add(itemStack);

                        } else {

                            esquerdaArray.add(item);
                        }
                    }
                }
            //Direita
            } else if(i%9 > 4 && i != 32 && i != 33) {

                ItemStack item = direita.getOpenInventory().getTopInventory().getItem(i);

                if(item != null) {

                    if(direitaArray.isEmpty()) {

                        direitaArray.add(item);

                    } else {

                        int index = searchArray(item, direitaArray);

                        if(index != -1) {

                            int amount = direitaArray.get(index).getAmount();

                            ItemStack itemStack = new ItemStack(item.getType(), (amount + item.getAmount()));

                            direitaArray.remove(index);
                            direitaArray.add(itemStack);

                        } else {

                            direitaArray.add(item);
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
