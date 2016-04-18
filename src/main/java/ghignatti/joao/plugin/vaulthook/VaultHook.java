package ghignatti.joao.plugin.vaulthook;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Server;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHook {
    private static VaultHook ourInstance = new VaultHook();

    public static VaultHook getInstance() {
        return ourInstance;
    }

    private VaultHook() {
    }

    private static Economy economy = null;

    private boolean setupEconomy(Server server) {
        RegisteredServiceProvider<Economy> economyProvider = server.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public void startHook(Server server) {
        setupEconomy(server);
    }
}
