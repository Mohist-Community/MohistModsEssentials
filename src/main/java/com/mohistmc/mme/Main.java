package com.mohistmc.mme;

import com.mohistmc.api.ServerAPI;
import com.mohistmc.mme.pixelmon.reforged.papi.PokePapi;
import com.pixelmonmod.pixelmon.Pixelmon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.mohistmc.mme.ic2.ExplosionEvent;
import com.mohistmc.mme.ic2.HookLaserEvent;

public class Main extends JavaPlugin {

    public static Main plugin;
    public Pixelmon pixelmon;

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        saveDefaultConfig();
        reloadConfig();
        if (ServerAPI.hasMod("pixelmon")) {
            this.pixelmon = Pixelmon.instance;
            if (ServerAPI.hasPlugin("Vault") && getConfig().getBoolean("pixelmon.hookvault", false)) {
                if (ServerAPI.hasPlugin("PlaceholderAPI")) {
                    new PokePapi().register();
                }
            }
            if (ServerAPI.hasPlugin("PlaceholderAPI")) {
                new PokePapi().register();
            }
            Bukkit.getLogger().info("Successful hook pixelmon mod!");
        }
        if (ServerAPI.hasMod("ic2")) {
            if (getConfig().getBoolean("ic2.canceled.Explosion", false)) {
                ServerAPI.registerBukkitEvents(new ExplosionEvent(), this);
            }
            if (getConfig().getBoolean("ic2.canceled.LaserEvent", false)) {
                ServerAPI.registerBukkitEvents(new HookLaserEvent(), this);
            }
        }
    }

    public void onDisable() {
        if (ServerAPI.hasPlugin("PlaceholderAPI") ) {
            new PokePapi().unregister();
        }
    }
}
