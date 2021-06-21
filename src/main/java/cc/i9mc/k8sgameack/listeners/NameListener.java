package cc.i9mc.k8sgameack.listeners;

import cc.i9mc.gameutils.event.bukkit.BukkitSendNameEvent;
import cc.i9mc.k8sgameack.K8SGameACK;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by JinVan on 2020/7/23.
 */
public class NameListener implements Listener {
    @EventHandler
    public void onName(BukkitSendNameEvent event){
        K8SGameACK.getInstance().getServerData().setName(event.getName());
    }
}
