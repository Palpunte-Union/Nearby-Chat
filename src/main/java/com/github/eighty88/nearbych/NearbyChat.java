package com.github.eighty88.nearbych;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NearbyChat extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> Bukkit.getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onChat(AsyncPlayerChatEvent e) {
                String msg = e.getMessage();
                for(Entity entity: e.getPlayer().getWorld().getNearbyEntities(e.getPlayer().getLocation(), 16, 16, 16)) {
                    if(entity instanceof Player) {
                        entity.sendMessage("<" + e.getPlayer().getName() + "> " + msg);
                    }
                }
                e.setCancelled(true);
            }
        }, this), 20L);
    }
}