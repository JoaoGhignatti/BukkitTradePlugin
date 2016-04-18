package ghignatti.joao.plugin;

import ghignatti.joao.plugin.commands.TacceptCommand;
import ghignatti.joao.plugin.commands.TdeclineCommand;
import ghignatti.joao.plugin.commands.TradeCommand;
import ghignatti.joao.plugin.event.inventory.InventoryClick;
import ghignatti.joao.plugin.vaulthook.VaultHook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        VaultHook.getInstance().startHook(Bukkit.getServer());

        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        getCommand("trade").setExecutor(new TradeCommand());
        getCommand("taccept").setExecutor(new TacceptCommand());
        getCommand("tdecline").setExecutor(new TdeclineCommand());
    }
}
