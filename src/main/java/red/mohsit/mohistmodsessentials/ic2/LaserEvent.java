package red.mohsit.mohistmodsessentials.ic2;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import red.mohist.api.event.BukkitHookForgeEvent;

/**
 * @author Mgazul
 * @date 2019/12/24 5:07
 */
public class LaserEvent implements Listener {

    @EventHandler
    public void onic2LaserEvent(BukkitHookForgeEvent event){
        if(event.getEvent() instanceof ic2.api.event.LaserEvent) {
            ic2.api.event.LaserEvent explosionEvent = (ic2.api.event.LaserEvent)event.getEvent();
            explosionEvent.setCanceled(true);
        }
    }
}
