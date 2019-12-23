package red.mohsit.mohistmodsessentials;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import red.mohist.api.ServerAPI;
import red.mohsit.mohistmodsessentials.ic2.ExplosionEvent;
import red.mohsit.mohistmodsessentials.ic2.LaserEvent;
import red.mohsit.mohistmodsessentials.pixelmon.eco.VaultEcoHookPixelmon;

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
            if (Bukkit.getPluginManager().getPlugin("Vault") != null && getConfig().getBoolean("pixelmon.hookvault", false)){
                this.setuppixelmonEconomy();
            }
            Bukkit.getLogger().info("Successful hook pixelmon mod!");
        }
        if (ServerAPI.hasMod("ic2")){
            if(getConfig().getBoolean("ic2.canceledExplosion", true)) {
                Bukkit.getPluginManager().registerEvents(new ExplosionEvent(), this);
            }
            if(getConfig().getBoolean("ic2.canceledLaserEvent", false)) {
                Bukkit.getPluginManager().registerEvents(new LaserEvent(), this);
            }
        }
    }

    private void setuppixelmonEconomy() {
        Vault vault = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
        Bukkit.getServicesManager().register(Economy.class, new VaultEcoHookPixelmon(), vault, ServicePriority.Highest);
    }
}
