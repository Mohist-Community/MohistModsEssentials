package red.mohist.mohistmodsessentials.pixelmon.nyphysical;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import red.mohist.mohistmodsessentials.Main;

/**
 * @author Mgazul
 * @date 2019/12/28 3:18
 */
public class NyPhysicalPlayerData {

    private int np;
    private int max;
    private File f;
    private FileConfiguration data;
    private String name;

    public NyPhysicalPlayerData(String name) {
        this.name = name;
        this.f = new File(Main.plugin.getDataFolder() + "/NyPhysical/Data/", name + ".yml");
        this.data = YamlConfiguration.loadConfiguration(this.f);
        if (!this.f.exists()) {
            try {
                this.f.createNewFile();
                this.data.set("NP", Main.plugin.getConfig().getInt(NyPhysical.key + "default"));
                this.data.set("Max", Main.plugin.getConfig().getInt(NyPhysical.key + "default"));
                this.data.save(this.f);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.np = this.data.getInt("NP");
        this.max = this.data.getInt("Max");
        this.check();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.name);
    }

    public int getNP() {
        return this.np;
    }

    public int getMax() {
        return this.max;
    }

    public void addNP(int amount) {
        this.np += amount;
        this.np = (Math.min(this.np, this.max));
    }

    public void deleteNP(int amount) {
        this.np -= amount;
        this.np = (Math.max(this.np, 0));
    }

    public void check() {
        if (this.np > this.max) {
            this.np = this.max;
        }
    }

    public void save() {
        try {
            this.data.set("NP", this.np);
            this.data.set("Max", this.max);
            this.data.save(this.f);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
