package cc.i9mc.k8sgameack;

import cc.i9mc.gameutils.utils.JedisUtil;
import cc.i9mc.k8sgameack.data.ServerData;
import cc.i9mc.k8sgameack.data.ServerType;
import cc.i9mc.k8sgameack.listeners.NameListener;
import cc.i9mc.k8sgameack.listeners.ServerListener;
import cc.i9mc.k8sgameack.utils.IPUtil;
import cc.i9mc.k8sgameack.utils.JSONUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JinVan on 2020/7/22.
 */
public class K8SGameACK extends JavaPlugin {
    @Getter
    private static K8SGameACK instance;
    private Timer timer;
    @Getter
    private ServerData serverData;
    @Getter
    private final HashMap<String, Object> expand = new HashMap<>();
    private final long startTime = System.currentTimeMillis();
    private long forceBOOMTime = 0L;

    @Override
    public void onEnable() {
        instance = this;
        timer = new Timer();

        serverData = new ServerData();
        serverData.setServerType(ServerType.STARTUP);
        serverData.setIp(IPUtil.getLocalIp());

        getServer().getConsoleSender().sendMessage("辣鸡GameACK开始加载!~~");
        getServer().getPluginManager().registerEvents(new ServerListener(), this);
        getServer().getPluginManager().registerEvents(new NameListener(), this);

        File file = new File("/data/serverACK.log");

        timer.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                if (serverData.getGameType() == null) {
                    return;
                }

                switch (serverData.getServerType()) {
                    case STARTUP:
                        if (Math.abs(System.currentTimeMillis() - startTime) > 300000L) {
                            if(file.exists()){
                                file.delete();
                                serverData.setServerType(ServerType.END);
                            }
                        }
                        break;
                    case RUNNING:
                    case END:
                        if (serverData.getPlayers() == 0) {
                            if(forceBOOMTime == 0){
                                forceBOOMTime = System.currentTimeMillis();
                            } else if(Math.abs(System.currentTimeMillis() - forceBOOMTime) > 300000L){
                                if(file.exists()){
                                    file.delete();
                                    serverData.setServerType(ServerType.END);
                                }
                            }
                        }
                        break;
                }

                JedisUtil.publish("GameServerACK", JSONUtil.getDynamicString(serverData, expand));

                if(serverData.getName() != null && serverData.getServerType() == ServerType.WAITING && !file.exists()){
                    file.createNewFile();
                }
            }
        }, 1000L, 1000L);
    }

    @Override
    public void onDisable() {
        timer.cancel();
    }
}
