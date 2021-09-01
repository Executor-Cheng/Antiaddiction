package cc.silenter.antiaddictionbyqq;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AntiaddictionByQQ extends JavaPlugin {
    public static Plugin instance;
    public static Logger log;
    public static JsonObject recv;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance=this;
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        log = this.getLogger();
        if(!getDataFolder().exists())
        {
            //noinspection ResultOfMethodCallIgnored
            getDataFolder().mkdirs();
            saveDefaultConfig();
        }
        for (Player i: Bukkit.getOnlinePlayers()){
            Storage.time.put(i.getUniqueId().toString(),instance.getConfig().getInt("settings.time"));
            Storage.isChecked.put(i.getUniqueId().toString(),false);
            Storage.isChecking.put(i.getUniqueId().toString(),false);
        }
        Tasks task = new Tasks();
        task.runTaskTimer(this,0,20);
        if (instance.getConfig().getInt("settings.use_holiday")==2){
            recv = GetHttpRequest.sendGet("http://timor.tech/api/holiday/year","");
            if (recv==null){this.getConfig().set("use_holiday",0);log.info("Fail to load json, reset use_holiday to 0");}
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
