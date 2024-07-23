package net.aerotux.aerolauncher;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.aerotux.aerolauncher.command.MainCommand;
import net.aerotux.aerolauncher.event.InteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class AeroLauncher extends JavaPlugin {

    @Getter private static AeroLauncher instance;

    private PaperCommandManager commandManager;

    public static AeroLauncher getInstance() {
        return instance;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {

        instance = this;

        getLogger().info("Enabled!");
        getLogger().info("Please join the Discord for support!");

        registerCommands();
        registerEvents();

    }

    private void registerCommands() {

        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new MainCommand());

    }

    private void registerEvents() {

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new InteractEvent(), this);

    }

}
