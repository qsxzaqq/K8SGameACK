package cc.i9mc.k8sgameack.utils;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by JinVan on 2020/7/22.
 */
public class IPUtil {

    @SneakyThrows
    public static String getLocalIp() {
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = e.nextElement();
            Enumeration<InetAddress> ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = ee.nextElement();
                if(i.getHostAddress().split("\\.").length == 4) {
                    return i.getHostAddress() + ":" +  Bukkit.getPort();
                }
            }
        }
        return null;
    }
}
