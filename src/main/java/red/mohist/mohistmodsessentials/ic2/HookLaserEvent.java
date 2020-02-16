package red.mohist.mohistmodsessentials.ic2;

import ic2.api.event.LaserEvent;
import java.util.ArrayList;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import red.mohist.api.event.BukkitHookForgeEvent;
import red.mohist.mohistmodsessentials.Main;

/**
 * @author Mgazul
 * @date 2019/12/24 5:07
 */
public class HookLaserEvent implements Listener {

    @EventHandler
    public void onic2LaserEvent(BukkitHookForgeEvent event) {
        if (event.getEvent() instanceof LaserEvent) {
            if ((((LaserEvent) event.getEvent()).owner instanceof EntityPlayer && !((LaserEvent) event.getEvent()).owner.getBukkitEntity().hasPermission("mme.ic2.laserall"))
                    || Main.plugin.getConfig().getBoolean("ic2.canceled.LaserEvent-all", false)) {
                event.getEvent().setCanceled(true);
            }
        }
        if (!Main.plugin.getConfig().getBoolean("ic2.canceled.LaserEvent-all", true)) {
            if (event.getEvent() instanceof LaserEvent.LaserExplodesEvent) {
                if (((LaserEvent.LaserExplodesEvent) event.getEvent()).owner instanceof EntityPlayer) {
                    if (!((LaserEvent.LaserExplodesEvent) event.getEvent()).owner.getBukkitEntity().hasPermission("mme.ic2.laserexplodes")
                            || Main.plugin.getConfig().getBoolean("ic2.canceled.LaserEvent-Explodes", false)) {
                        event.getEvent().setCanceled(true);
                        return;
                    }
                    Location location = new Location(((LaserEvent.LaserExplodesEvent) event.getEvent()).getWorld().getWorld(), ((LaserEvent.LaserExplodesEvent) event.getEvent()).lasershot.field_70165_t, ((LaserEvent.LaserExplodesEvent) event.getEvent()).lasershot.field_70163_u, ((LaserEvent.LaserExplodesEvent) event.getEvent()).lasershot.field_70161_v);
                    EntityExplodeEvent entityExplodeEvent = new EntityExplodeEvent(new EntityTNTPrimed(((LaserEvent.LaserExplodesEvent) event.getEvent()).getWorld()).getBukkitEntity(), location, new ArrayList(), ((LaserEvent.LaserExplodesEvent) event.getEvent()).explosionPower);
                    Bukkit.getPluginManager().callEvent(entityExplodeEvent);
                    event.getEvent().setCanceled(entityExplodeEvent.isCancelled());
                }
            }
            if (event.getEvent() instanceof LaserEvent.LaserHitsBlockEvent) {
                if (((LaserEvent.LaserHitsBlockEvent) event.getEvent()).owner instanceof EntityPlayer) {
                    if (!((LaserEvent.LaserHitsBlockEvent) event.getEvent()).owner.getBukkitEntity().hasPermission("mme.ic2.laserhitsblock")
                            || Main.plugin.getConfig().getBoolean("ic2.canceled.LaserEvent-HitsBlock", false)) {
                        event.getEvent().setCanceled(true);
                        return;
                    }
                    Block block = ((LaserEvent.LaserHitsBlockEvent) event.getEvent()).getWorld().getWorld().getBlockAt(((LaserEvent.LaserHitsBlockEvent) event.getEvent()).pos.func_177958_n(), ((LaserEvent.LaserHitsBlockEvent) event.getEvent()).pos.func_177956_o(), ((LaserEvent.LaserHitsBlockEvent) event.getEvent()).pos.func_177952_p());
                    BlockBreakEvent blockBreakEvent = new BlockBreakEvent(block, (Player) ((LaserEvent.LaserHitsBlockEvent) event.getEvent()).owner.getBukkitEntity());
                    Bukkit.getPluginManager().callEvent(blockBreakEvent);
                    event.getEvent().setCanceled(blockBreakEvent.isCancelled());
                }
            }
            if (event.getEvent() instanceof LaserEvent.LaserHitsEntityEvent) {
                if (((LaserEvent.LaserHitsEntityEvent) event.getEvent()).owner instanceof EntityPlayer) {
                    if (!((LaserEvent.LaserHitsEntityEvent) event.getEvent()).owner.getBukkitEntity().hasPermission("mme.ic2.laserhitsentity")
                            || Main.plugin.getConfig().getBoolean("ic2.canceled.LaserEvent-HitsEntity", false)) {
                        event.getEvent().setCanceled(true);
                        return;
                    }
                    PlayerInteractEntityEvent playerInteractEntityEvent = new PlayerInteractEntityEvent((Player) ((LaserEvent.LaserHitsEntityEvent) event.getEvent()).owner.getBukkitEntity(), ((LaserEvent.LaserHitsEntityEvent) event.getEvent()).hitEntity.getBukkitEntity());
                    Bukkit.getPluginManager().callEvent(playerInteractEntityEvent);
                    event.getEvent().setCanceled(playerInteractEntityEvent.isCancelled());
                }
            }
            if (event.getEvent() instanceof LaserEvent.LaserShootEvent) {
                if ((((LaserEvent.LaserShootEvent) event.getEvent()).owner instanceof EntityPlayer && !((LaserEvent.LaserShootEvent) event.getEvent()).owner.getBukkitEntity().hasPermission("mme.ic2.lasershoot"))
                        || Main.plugin.getConfig().getBoolean("ic2.canceled.LaserEvent-Shoot", false)) {
                    event.getEvent().setCanceled(true);
                }
            }
        }
    }
}
