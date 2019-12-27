package red.mohsit.mohistmodsessentials.pixelmon.nyphysical;

import com.pixelmonmod.pixelmon.api.events.BattleStartedEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import red.mohist.api.event.BukkitHookForgeEvent;
import red.mohsit.mohistmodsessentials.Main;

/**
 * @author Mgazul
 * @date 2019/12/28 3:32
 */
public class NyPhysicalListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals(Main.plugin.getConfig().getString("inventory.title").replace("&", "§"))) {
            e.setCancelled(true);
            if (e.getSlot() == 22) {
                if (NyPhysical.can) {
                    if (NyPhysical.pds.containsKey(e.getWhoClicked().getName())) {
                        if (NyPhysical.players.contains(e.getWhoClicked().getName())) {
                            e.getWhoClicked().sendMessage(NyPhysical.prefix + Main.plugin.getConfig().getString("message.get").replace("&", "§"));
                            return;
                        }
                        NyPhysical.players.add(e.getWhoClicked().getName());
                        NyPhysical.pds.get(e.getWhoClicked().getName()).addNP(NyPhysical.pds.get(e.getWhoClicked().getName()).getMax());
                        Bukkit.getServer().broadcastMessage(NyPhysical.prefix + Main.plugin.getConfig().getString("message.broadcast").replace("%player%", e.getWhoClicked().getName()));
                        e.getWhoClicked().sendMessage(NyPhysical.prefix + Main.plugin.getConfig().getString("message.success").replace("&", "§"));
                    }
                    else {
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().sendMessage(NyPhysical.prefix + "§c出现异常, 请退出服务器再次进入!");
                    }
                }
                else {
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage(NyPhysical.prefix + Main.plugin.getConfig().getString("message.usf-time").replace("&", "§"));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        NyPhysical.pds.put(e.getPlayer().getName(), new NyPhysicalPlayerData(e.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (NyPhysical.pds.containsKey(e.getPlayer().getName())) {
            NyPhysical.pds.get(e.getPlayer().getName()).save();
            NyPhysical.pds.remove(e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onForgeEvent(BukkitHookForgeEvent e) {
        if (e.getEvent() instanceof BattleStartedEvent) {
            BattleStartedEvent be = (BattleStartedEvent)e.getEvent();
            if (be.bc.getPlayers().size() == 1.0) {
                String name = be.bc.getPlayers().get(0).player.getDisplayNameString();
                Player p = Bukkit.getPlayer(name);
                if (Main.plugin.getConfig().getStringList("worlds").contains(p.getWorld().getName())) {
                    return;
                }
                if (NyPhysical.pds.containsKey(name)) {
                    int np = NyPhysical.pds.get(name).getNP();
                    if (np > 0) {
                        NyPhysical.pds.get(name).deleteNP(1);
                    }
                    else {
                        be.setResult(Event.Result.DENY);
                        be.setCanceled(true);
                        p.sendMessage(NyPhysical.prefix + Main.plugin.getConfig().getString("message.lack-phy").replace("&", "§"));
                    }
                }
                else {
                    be.setResult(Event.Result.DENY);
                    be.setCanceled(true);
                    p.sendMessage(NyPhysical.prefix + Main.plugin.getConfig().getString("message.lack-phy").replace("&", "§"));
                }
            }
        }
    }
}
