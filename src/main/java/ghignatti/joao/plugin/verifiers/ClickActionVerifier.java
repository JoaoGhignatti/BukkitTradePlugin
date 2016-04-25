package ghignatti.joao.plugin.verifiers;

import ghignatti.joao.plugin.action.*;
import ghignatti.joao.plugin.array.ArrayInTrading;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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

        if(arrayInTrading.tradingHash.containsKey(aux)) {

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

                    if(arrayInTrading.tradingHash.get(p).isSenderGreenBlock() ||
                            arrayInTrading.tradingHash.get(p).isTargetGreenBlock()) {

                        resetButtons(p, t);
                    }

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

    private void resetButtons(Player p, Player t) {

        ArrayInTrading arrayInTrading = ArrayInTrading.getInstance();

        arrayInTrading.tradingHash.get(p).setSenderGreenBlock(false);
        arrayInTrading.tradingHash.get(p).setTargetGreenBlock(false);

        arrayInTrading.tradingHash.get(t).setSenderGreenBlock(false);
        arrayInTrading.tradingHash.get(t).setTargetGreenBlock(false);

        ItemStack stone1 = new InvSetter().nameItem(new ItemStack(Material.STONE), ChatColor.BLUE + p.getName() + " escolha.");
        t.getOpenInventory().getTopInventory().setItem(30, stone1);
        t.updateInventory();

        ItemStack stone2 = new InvSetter().nameItem(new ItemStack(Material.STONE), ChatColor.BLUE + t.getName() + " escolha.");
        p.getOpenInventory().getTopInventory().setItem(32, stone2);
        p.updateInventory();
    }
}