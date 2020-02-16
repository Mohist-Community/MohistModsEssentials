package red.mohist.mohistmodsessentials.pixelmon.nyphysical;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import red.mohist.mohistmodsessentials.Main;
import red.mohist.mohistmodsessentials.pixelmon.placeholderhook.NyPhysicalPAPI;

/**
 * @author Mgazul
 * @date 2019/12/28 3:23
 */
public class NyPhysical{

    public static HashMap<String, NyPhysicalPlayerData> pds = new HashMap<>();
    public static List<String> players = new ArrayList<>();
    public static int time = 0;
    public static boolean can;
    public Main plugin;
    public static String prefix;
    public static String key = "pixelmon.nyphysical.";

    public NyPhysical(Main main){
        main = plugin;
        prefix = plugin.getConfig().getString(key + "message.prefix").replace("&", "§");
        pds.clear();
        for (final Player p : Bukkit.getOnlinePlayers()) {
            pds.put(p.getName(), new NyPhysicalPlayerData(p.getName()));
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            this.checkTime();
            if (time < plugin.getConfig().getInt(key + "add-time")) {
                ++time;
            }
            else {
                time = 0;
                for (NyPhysicalPlayerData pd : pds.values()) {
                    pd.addNP(1);
                }
            }
        }, 20L, 20L);
        NyPhysicalPAPI.hook();
        Bukkit.getPluginManager().registerEvents(new NyPhysicalListener(), plugin);
        plugin.getCommand("np").setExecutor(new NyPhysicalCommand());
    }

    public void checkTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat day = new SimpleDateFormat("yyyy/MM/dd ");
        try {
            Date now = new Date(System.currentTimeMillis());
            String nowData = day.format(now);
            String[] oneTime = plugin.getConfig().getString(key + "time.one").split("-");
            String[] twoTime = plugin.getConfig().getString(key + "time.two").split("-");
            Date date = sdf.parse(nowData + oneTime[0]);
            Date date2 = sdf.parse(nowData + oneTime[1]);
            Date date3 = sdf.parse(nowData + twoTime[0]);
            Date date4 = sdf.parse(nowData + twoTime[1]);
            if (this.betweenOn(now, date, date2) || this.betweenOn(now, date3, date4)) {
                can = true;
            }
            else {
                can = false;
                players.clear();
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean betweenOn(Date value, Date startTime, Date endDate) {
        if (value == null || startTime == null || endDate == null) {
            return false;
        }
        long start = startTime.getTime();
        long end = endDate.getTime();
        return value.getTime() >= start && value.getTime() < end;
    }
}
