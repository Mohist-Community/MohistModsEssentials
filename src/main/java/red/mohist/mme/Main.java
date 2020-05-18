package red.mohist.mme;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import red.mohist.api.ServerAPI;
import red.mohist.mme.ic2.ExplosionEvent;
import red.mohist.mme.ic2.HookLaserEvent;
import red.mohist.mme.pixelmon.reforged.eco.VaultEcoHookPixelmon;
import red.mohist.mme.pixelmon.reforged.papi.PokePapi;

public class Main extends JavaPlugin {

    public static Main plugin;
    public Pixelmon pixelmon;

    @Override
    public void onEnable(){
        plugin = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        saveDefaultConfig();
        reloadConfig();
        if (ServerAPI.hasMod("pixelmon")) {
            this.pixelmon = Pixelmon.instance;
            if (ServerAPI.hasPlugin("Vault") && getConfig().getBoolean("pixelmon.hookvault", false)){
                this.setuppixelmonEconomy();
            }
            if (ServerAPI.hasPlugin("PlaceholderAPI") ) {
                PokePapi.hook();
            }
            Bukkit.getLogger().info("Successful hook pixelmon mod!");
        }
        if (ServerAPI.hasMod("ic2")){
            if(getConfig().getBoolean("ic2.canceled.Explosion", false)) {
                ServerAPI.registerBukkitEvents(new ExplosionEvent(), this);
            }
            if(getConfig().getBoolean("ic2.canceled.LaserEvent", false)) {
                ServerAPI.registerBukkitEvents(new HookLaserEvent(), this);
            }
        }
    }

    private void setuppixelmonEconomy() {
        Vault vault = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
        Bukkit.getServicesManager().register(Economy.class, new VaultEcoHookPixelmon(), vault, ServicePriority.Highest);
    }

    public void onDisable() {
    }
}
