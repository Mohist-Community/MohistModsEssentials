package red.mohist.mohistmodsessentials.pixelmon.placeholderhook;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

/**
 * @author Mgazul
 * @date 2019/12/28 3:14
 */
public class NyPhysicalPAPI extends PlaceholderHook
{
    private static String hook_name = "mme";

    public String onPlaceholderRequest(Player player, String s) {
        return "0";
    }

    public static void hook() {
        PlaceholderAPI.registerPlaceholderHook(hook_name, new NyPhysicalPAPI());
    }

    public static void unhook() {
        PlaceholderAPI.unregisterPlaceholderHook(hook_name);
    }
}
