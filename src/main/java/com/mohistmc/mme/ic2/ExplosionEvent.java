package com.mohistmc.mme.ic2;

import com.mohistmc.api.event.BukkitHookForgeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Mgazul
 * @date 2019/12/24 4:57
 */
public class ExplosionEvent implements Listener {

    @EventHandler
    public void onic2explosion(BukkitHookForgeEvent event){
        if(event.getEvent() instanceof ic2.api.event.ExplosionEvent) {
            ic2.api.event.ExplosionEvent explosionEvent = (ic2.api.event.ExplosionEvent)event.getEvent();
            explosionEvent.setCanceled(true);
        }
    }
}
