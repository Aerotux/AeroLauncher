package net.aerotux.aerolauncher.event;

import net.aerotux.aerolauncher.AeroLauncher;
import net.aerotux.aerolauncher.item.RocketLauncher;
import org.bukkit.*;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

public class InteractEvent implements Listener {

    private AeroLauncher instance = AeroLauncher.getInstance();
    private RocketLauncher rocketLauncher = new RocketLauncher();
    private HashMap<Sheep, Location> sheepLocations = new HashMap<>();
    private HashMap<Fireball, Location> fireballLocations = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND)
            return;

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
            return;

        if (event.getItem() == null || event.getItem().getItemMeta() == null || event.getItem().getItemMeta().getDisplayName() == null)
            return;

        if (!event.getItem().getItemMeta().getDisplayName().equals(RocketLauncher.ROCKET_LAUNCHER_NAME))
            return;

        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);

        Sheep sheep = (Sheep) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SHEEP);
        sheep.setColor(DyeColor.YELLOW);
        sheepLocations.put(sheep, sheep.getLocation());
        sheep.setVelocity(sheep.getLocation().getDirection().multiply(6));
        sheep.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Rocket Sheep");
        sheep.setCustomNameVisible(true);
        sheep.setInvulnerable(true);

        instance.getServer().getScheduler().runTaskTimer(instance, () -> {
            sheepLocations.forEach((currentSheep, location) -> {
                Location sheepLocation = currentSheep.getLocation();
                Location particleLocation = sheepLocation.clone().add(0, 0.5, 0);
                currentSheep.getWorld().spawnParticle(Particle.NOTE, particleLocation, 0, 1, 1, 1, 1);
            });
        }, 0L, 1L);

        /*Fireball fireball = (Fireball) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.FIREBALL);
        fireball.setDirection(player.getEyeLocation().getDirection());
        fireballLocations.put(fireball, fireball.getLocation());
        fireball.setYield(50);
        fireball.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "Fireball");
        fireball.setCustomNameVisible(true);*/

        instance.getServer().getScheduler().runTaskTimer(instance, () -> {
            fireballLocations.forEach((currentFireball, location) -> {
                Location fireballLocation = currentFireball.getLocation();
                Location particleLocation = fireballLocation.clone().add(0, 0.5, 0);
                currentFireball.getWorld().spawnParticle(Particle.NOTE, particleLocation, 0, 1, 1, 1, 1);
            });
        }, 0L, 1L);

    }

    @EventHandler
    public void onMap(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Sheep && sheepLocations.containsKey((Sheep) entity)) {
            sheepLocations.remove((Sheep) entity);
        }
        if (entity instanceof Fireball && fireballLocations.containsKey((Fireball) entity)) {
            fireballLocations.remove((Fireball) entity);
        }
    }
}