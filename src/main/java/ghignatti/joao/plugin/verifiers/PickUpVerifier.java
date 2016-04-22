package ghignatti.joao.plugin.verifiers;

import ghignatti.joao.plugin.action.InvSetter;
import ghignatti.joao.plugin.array.ArrayInTrading;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PickUpVerifier {
    private static PickUpVerifier ourInstance = new PickUpVerifier();

    public static PickUpVerifier getInstance() {
        return ourInstance;
    }

    private PickUpVerifier() {
    }

    public boolean cancelClick(InventoryClickEvent event) {

        ArrayInTrading arrayInTrading = ArrayInTrading.getInstance();

        Player aux = (Player) event.getWhoClicked();

        Player player = arrayInTrading.tradingHash.get(aux).getPlayer();
        Player target = arrayInTrading.tradingHash.get(aux).getTarget();

        if(player == null || target == null) {
            return false;
        }

        Inventory inv = event.getInventory();
        int slot = event.getRawSlot();

        if(arrayInTrading.tradingHash.get(player).isStatus()) {

            if(aux.equals(player)) {

                if(slot < inv.getSize()) {

                    if(event.getRawSlot() == 29) {

                        ItemStack emeraldBlock = new InvSetter().nameItem(new ItemStack(Material.EMERALD_BLOCK), player.getName() + " escolha.");
                        target.getOpenInventory().getTopInventory().setItem(30, emeraldBlock);

                        return true;

                    } else if(event.getRawSlot() == 30) {

                        ItemStack redstoneBlock = new InvSetter().nameItem(new ItemStack(Material.REDSTONE_BLOCK), player.getName() + " escolha.");
                        target.getOpenInventory().getTopInventory().setItem(30, redstoneBlock);

                        return true;

                    } else if(event.getRawSlot() % 9 > 3 && event.getRawSlot() != 29 && event.getRawSlot() != 30) {

                        return true;
                    }
                }

            } else if(aux.equals(target)) {

                if(slot < inv.getSize()) {

                    if(event.getRawSlot() == 33) {

                        ItemStack emeraldBlock = new InvSetter().nameItem(new ItemStack(Material.EMERALD_BLOCK), target.getName() + " escolha.");
                        player.getOpenInventory().getTopInventory().setItem(32, emeraldBlock);

                        return true;

                    } else if(event.getRawSlot() == 32) {

                        ItemStack redstoneBlock = new InvSetter().nameItem(new ItemStack(Material.REDSTONE_BLOCK), target.getName() + " escolha.");
                        player.getOpenInventory().getTopInventory().setItem(32, redstoneBlock);

                        return true;

                    } else if(event.getRawSlot() % 9 < 5 && event.getRawSlot() != 32 && event.getRawSlot() != 33) {

                        return true;
                    }
                }
            }
        }

        return false;
    }
}
