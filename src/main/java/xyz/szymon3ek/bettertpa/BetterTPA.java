package xyz.szymon3ek.bettertpa;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.szymon3ek.bettertpa.commands.tpaccept;
import xyz.szymon3ek.bettertpa.commands.tpdeny;
import xyz.szymon3ek.bettertpa.events.clickMenu;
import xyz.szymon3ek.bettertpa.commands.openMenu;

public final class BetterTPA extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("BetterTPA zostal wlaczony!");
        getCommand("tpa").setExecutor(new openMenu());
        getServer().getPluginManager().registerEvents(new clickMenu(), this);
        getCommand("tpdeny").setExecutor(new tpdeny());
        getCommand("tpaccept").setExecutor(new tpaccept());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
