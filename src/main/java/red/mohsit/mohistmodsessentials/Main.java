package red.mohsit.mohistmodsessentials;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import red.mohist.api.ServerAPI;
import red.mohsit.mohistmodsessentials.ic2.ExplosionEvent;
import red.mohsit.mohistmodsessentials.ic2.HookLaserEvent;
import red.mohsit.mohistmodsessentials.pixelmon.eco.VaultEcoHookPixelmon;
import red.mohsit.mohistmodsessentials.pixelmon.nyphysical.NyPhysical;
import red.mohsit.mohistmodsessentials.pixelmon.nyphysical.NyPhysicalPlayerData;
import red.mohsit.mohistmodsessentials.pixelmon.placeholderhook.NyPhysicalPAPI;

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
            if (getConfig().getBoolean(NyPhysical.key + "enable", false)) {
                new NyPhysical(this);
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
        if (getConfig().getBoolean(NyPhysical.key + "enable", false)) {
            for (NyPhysicalPlayerData pd : NyPhysical.pds.values()) {
                pd.save();
            }
            NyPhysicalPAPI.unhook();
        }
    }
}
