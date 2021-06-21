package cc.i9mc.k8sgameack.data;

import lombok.Data;
import org.bukkit.Bukkit;

/**
 * Created by JinVan on 2020/7/22.
 */
@Data
public class ServerData {
    private String gameType;
    private String name;
    private String ip;
    private ServerType serverType;
    private int maxPlayers = -1;

    public int getPlayers(){
        return Bukkit.getOnlinePlayers().size();
    }
}
