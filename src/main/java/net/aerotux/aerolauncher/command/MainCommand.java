package net.aerotux.aerolauncher.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.aerotux.aerolauncher.AeroLauncher;
import net.aerotux.aerolauncher.item.RocketLauncher;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("aerolauncher|al")
@CommandPermission("aerol.admin")
public class MainCommand extends BaseCommand {

    RocketLauncher rocketLauncher = new RocketLauncher();

    @Default
    public void main(Player player) {

        player.sendMessage(ChatColor.RED + "Use: /aerolauncher [argument]");

    }

    @Subcommand("reload")
    @CommandPermission("aerol.admin")
    public void reload(Player player) {
        player.sendMessage(ChatColor.GREEN + "You have reloaded AeroLauncher by Aerotux config!");
        AeroLauncher.getInstance().reloadConfig();
    }

    @Subcommand("get-launcher")
    @CommandPermission("aerol.admin")
    public void getLauncher(Player player) {

        rocketLauncher.giveLauncher(player);

    }

}
