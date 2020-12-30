package com.mohistmc.mme.pixelmon.reforged.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mgazul
 * @date 2019/12/28 3:14
 */
public class PokePapi extends PlaceholderExpansion
{
    private static String hook_name = "pokepapi";
    int fulldex;

    public String onPlaceholderRequest(Player p, String s) {
        PokePapiUtils.rep(p, s);
        return "none";
    }

    @Override
    public @NotNull String getIdentifier() {
        return hook_name;
    }

    @Override
    public @NotNull String getAuthor() {
        return "Mohist-Community";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.1";
    }
}
