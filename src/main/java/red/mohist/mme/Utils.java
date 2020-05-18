package red.mohist.mme;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

/**
 * @author Mgazul
 * @date 2019/12/28 3:16
 */
public class Utils {

    public static String replacepapi(OfflinePlayer player, String x) {
        return PlaceholderAPI.setPlaceholders(player, x.replace("&", "§"));
    }
}
