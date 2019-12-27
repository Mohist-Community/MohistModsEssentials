package red.mohsit.mohistmodsessentials.pixelmon.placeholderhook;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;
import red.mohsit.mohistmodsessentials.Main;
import red.mohsit.mohistmodsessentials.pixelmon.nyphysical.NyPhysical;

/**
 * @author Mgazul
 * @date 2019/12/28 3:14
 */
public class NyPhysicalPAPI extends PlaceholderHook
{
    private static String hook_name = "mme";

    public String onPlaceholderRequest(Player player, String s) {
        if (NyPhysical.pds.containsKey(player.getName())) {
            if (s.equals("max")) {
                return NyPhysical.pds.get(player.getName()).getMax() + "";
            }
            if (s.equals("np")) {
                return NyPhysical.pds.get(player.getName()).getNP() + "";
            }
            if (s.equals("time")) {
                return Main.plugin.getConfig().getInt("add-time") - NyPhysical.time + "";
            }
            if (s.equals("st")) {
                return NyPhysical.time + "";
            }
            if (s.equals("status")) {
                int max = NyPhysical.pds.get(player.getName()).getMax();
                int np = NyPhysical.pds.get(player.getName()).getNP();
                if (np >= max) {
                    return "已满值";
                }
                return "恢复中";
            }
        }
        return "0";
    }

    public static void hook() {
        PlaceholderAPI.registerPlaceholderHook(hook_name, new NyPhysicalPAPI());
    }

    public static void unhook() {
        PlaceholderAPI.unregisterPlaceholderHook(hook_name);
    }
}
