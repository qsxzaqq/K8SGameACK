package cc.i9mc.k8sgameack.utils;

import cc.i9mc.k8sgameack.data.ServerData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class JSONUtil {
    public static final Gson GSON = new Gson();

    public static String getDynamicString(ServerData serverData, HashMap<String, Object> expand) {
        HashMap<String, Object> object = new LinkedHashMap<>();
        object.put("serverType", serverData.getServerType().toString());
        object.put("gameType", serverData.getGameType());
        if(serverData.getName() != null){
            object.put("name", serverData.getName());
            object.put("players", serverData.getPlayers());
            object.put("maxPlayers", serverData.getMaxPlayers());
        }
        object.put("ip", serverData.getIp());
        object.put("expand", expand);

        return GSON.toJson(object);
    }
}
