package ghignatti.joao.plugin.event.inventory;

import ghignatti.joao.plugin.verifiers.ClickActionVerifier;
import ghignatti.joao.plugin.verifiers.PickUpVerifier;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryCLick(InventoryClickEvent event) {

        if(event.getInventory().getType() == InventoryType.CHEST) {

            if (PickUpVerifier.getInstance().cancelClick(event)) {

                event.setCancelled(true);
            } else {

                ClickActionVerifier.getInstance().verifyAction(event);
            }
        }
    }
}
