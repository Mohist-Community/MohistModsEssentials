package red.mohist.mme.pixelmon.reforged.papi;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

/**
 * @author Mgazul
 * @date 2019/12/28 3:14
 */
public class PokePapi extends PlaceholderHook
{
    private static String hook_name = "pokepapi";
    int fulldex;

    public String onPlaceholderRequest(Player p, String s) {
        PokePapiUtils.rep(p, s);
        return "none";
    }

    public static void hook() {
        PlaceholderAPI.registerPlaceholderHook(hook_name, new PokePapi());
    }

    public static void unhook() {
        PlaceholderAPI.unregisterPlaceholderHook(hook_name);
    }
}
