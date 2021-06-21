package cc.i9mc.k8sgameack.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by JinVan on 2020/7/22.
 */
public class ACKGameStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}