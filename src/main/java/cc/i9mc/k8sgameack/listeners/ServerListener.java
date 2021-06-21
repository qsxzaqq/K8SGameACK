package cc.i9mc.k8sgameack.listeners;

import cc.i9mc.k8sgameack.K8SGameACK;
import cc.i9mc.k8sgameack.data.ServerType;
import cc.i9mc.k8sgameack.events.ACKGameLoadingEvent;
import cc.i9mc.k8sgameack.events.ACKGameEndEvent;
import cc.i9mc.k8sgameack.events.ACKGameStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by JinVan on 2020/7/22.
 */
public class ServerListener implements Listener {

    @EventHandler
    public void onGameLoading(ACKGameLoadingEvent event){
        K8SGameACK.getInstance().getServerData().setServerType(ServerType.WAITING);
        K8SGameACK.getInstance().getServerData().setMaxPlayers(event.getMaxPlayers());
    }

    @EventHandler
    public void onGameStart(ACKGameStartEvent event){
        K8SGameACK.getInstance().getServerData().setServerType(ServerType.RUNNING);
    }

    @EventHandler
    public void onGameEnd(ACKGameEndEvent event){
        K8SGameACK.getInstance().getServerData().setServerType(ServerType.END);
    }
}
