package net.aerotux.aerolauncher.item;

import net.aerotux.aerolauncher.AeroLauncher;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RocketLauncher {

    AeroLauncher instance = AeroLauncher.getInstance();
    public static String ROCKET_LAUNCHER_NAME = ChatColor.GREEN + "Rocket Launcher";


    public void giveLauncher(Player player) {

        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ROCKET_LAUNCHER_NAME);
        meta.setLore(Arrays.asList(ChatColor.GRAY + "This launches a fireball!"));
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GREEN + "You have received the " + ROCKET_LAUNCHER_NAME);

    }

}
