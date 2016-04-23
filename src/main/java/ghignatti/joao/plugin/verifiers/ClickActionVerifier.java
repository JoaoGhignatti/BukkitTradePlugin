package ghignatti.joao.plugin.verifiers;

import ghignatti.joao.plugin.action.*;
import ghignatti.joao.plugin.array.ArrayInTrading;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickActionVerifier {
    private static ClickActionVerifier ourInstance = new ClickActionVerifier();

    public static ClickActionVerifier getInstance() {
        return ourInstance;
    }

    private ClickActionVerifier() {
    }

    public void verifyAction(InventoryClickEvent event) {

        if(!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player aux = (Player) event.getWhoClicked();

        ArrayInTrading arrayInTrading = ArrayInTrading.getInstance();

        Player p = arrayInTrading.tradingHash.get(aux).getPlayer();
        Player t = arrayInTrading.tradingHash.get(aux).getTarget();

        if(p == null || t == null) {
            return;
        }

        if(!(arrayInTrading.tradingHash.get(p).isStatus())) {
            return;
        }

        int slot = event.getRawSlot();
        Inventory inv = event.getInventory();
        InventoryAction inventoryAction = event.getAction();

        if(aux.equals(p) || aux.equals(t)) {

            if(event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
                return;
            }

            if(slot < inv.getSize()) {

                if(inventoryAction == InventoryAction.SWAP_WITH_CURSOR ||
                        inventoryAction == InventoryAction.HOTBAR_SWAP) {

                    ItemSwap.getInstance().doSwap(event, p, t);

                } else if(inventoryAction == InventoryAction.PLACE_ALL ||
                        inventoryAction == InventoryAction.PLACE_ONE ||
                        inventoryAction == InventoryAction.PLACE_SOME ||
                        inventoryAction == InventoryAction.MOVE_TO_OTHER_INVENTORY) {

                    ItemProjector.getInstance().doProjection(event, p, t);

                } else if(inventoryAction == InventoryAction.PICKUP_ALL ||
                        inventoryAction == InventoryAction.PICKUP_HALF ||
                        inventoryAction == InventoryAction.PICKUP_ONE ||
                        inventoryAction == InventoryAction.PICKUP_SOME) {

                    ItemUnprojector.getInstance().doUnprojection(event, p, t);
                }
            }
        }
    }
}

























/*
                if(inventoryAction == InventoryAction.SWAP_WITH_CURSOR |
                        inventoryAction == InventoryAction.HOTBAR_SWAP) {

                    ItemSwap.getInstance().doSwap(event, p, t);

                } else if(inventoryAction == InventoryAction.PLACE_ALL ||
                        inventoryAction == InventoryAction.PLACE_ONE ||
                        inventoryAction == InventoryAction.PLACE_SOME ||
                        inventoryAction == InventoryAction.MOVE_TO_OTHER_INVENTORY) {

                    ItemProjector.getInstance().doProjection(event, p, t);

                } else if(inventoryAction == InventoryAction.PICKUP_ALL ||
                        inventoryAction == InventoryAction.PICKUP_HALF ||
                        inventoryAction == InventoryAction.PICKUP_ONE ||
                        inventoryAction == InventoryAction.PICKUP_SOME) {

                    ItemUnprojector.getInstance().doUnprojection(event, p, t);
                }
            } else {

                if(clickType == ClickType.SHIFT_LEFT || clickType == ClickType.SHIFT_RIGHT) {

                    ItemStack itemStack = event.getCurrentItem();


                }
            }
        }
    }
}*/
