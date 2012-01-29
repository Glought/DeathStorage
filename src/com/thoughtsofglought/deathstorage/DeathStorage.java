package com.thoughtsofglought.deathstorage;

//Java imports
import java.util.logging.Logger;

//Bukkit imports
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.thoughtsofglought.deathstorage.Listeners.DeathStorageDchestListener;
import com.thoughtsofglought.deathstorage.Listeners.DeathStorageEntityListener;
import com.thoughtsofglought.deathstorage.Listeners.DeathStoragePlayerListener;

public class DeathStorage extends JavaPlugin {
	protected static Configuration CONFIG;
	
	Logger log = Logger.getLogger("Minecraft");
	
	
	
	public void onEnable(){
	    CONFIG = getConfiguration();
	    CONFIG.setProperty("ChestProtection.UseLwc?", true);
		CONFIG.setProperty("ChestProtection.UseLockette?", false);
		
		
		
		log.info("DeathStorage has Been Enabled");
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DEATH, EntityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.CUSTOM_EVENT, DchestListener, Event.Priority.Normal, this);
	}
	
	public void onDisable(){
		CONFIG.save();
		log.info("DeathStorage has Been Disabled");
	}
	
	private final DeathStoragePlayerListener playerListener = new DeathStoragePlayerListener(this);
	private final DeathStorageEntityListener EntityListener = new DeathStorageEntityListener(this);
    private final DeathStorageDchestListener DchestListener = new DeathStorageDchestListener(this); 
   
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){ 
    	
    	Player player = null;
    	if (sender instanceof Player){
    		player = (Player) sender;
    	}
    	
    	if(cmd.getName().equalsIgnoreCase("deathstorage")){
    		
    		return true;
    	}
    	return false;
    }
    
}
