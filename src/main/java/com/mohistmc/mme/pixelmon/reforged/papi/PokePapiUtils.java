package com.mohistmc.mme.pixelmon.reforged.papi;

import com.mohistmc.util.NumberUtils;
import com.pixelmonmod.pixelmon.Pixelmon;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * @author Mgazul
 * @date 2020/5/19 1:20
 */
public class PokePapiUtils {

    public static String rep(Player p, String s){
        UUID player = p.getUniqueId();
        String[] strings = s.split("_");
        if(NumberUtils.isInteger(strings[2])) {
            int size = Integer.parseInt(strings[2]);
            if (size >=0 && size <= 6) {
                if (("isegg_" + size).equals(s)) {
                    if (Pixelmon.storageManager.getParty(player).get(size) != null) {
                        return String.valueOf(Pixelmon.storageManager.getParty(player).get(size).isEgg());
                    }
                }
                if (("special_" + size).equals(s)) {
                    if (Pixelmon.storageManager.getParty(player).get(size) != null) {
                        return Pixelmon.storageManager.getParty(player).get(size).getSpecies().getLocalizedName();
                    }
                }
                if (("nickname_" + size).equals(s)) {
                    if (Pixelmon.storageManager.getParty(player).get(size) != null) {
                        return Pixelmon.storageManager.getParty(player).get(size).getDisplayName();
                    }
                }
            }
        }
        return "none";
    }
}
