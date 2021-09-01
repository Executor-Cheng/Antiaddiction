package cc.silenter.antiaddictionbyqq;

import com.google.gson.JsonObject;
import me.dreamvoid.miraimc.api.MiraiMC;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import static cc.silenter.antiaddictionbyqq.AntiaddictionByQQ.instance;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Storage.time.put(event.getPlayer().getUniqueId().toString(),instance.getConfig().getInt("settings.time"));
        Storage.isChecked.put(event.getPlayer().getUniqueId().toString(),false);
        Storage.isChecking.put(event.getPlayer().getUniqueId().toString(),false);
    }

}