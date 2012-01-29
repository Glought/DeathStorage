package com.thoughtsofglought.deathstorage;

//Java imports
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

//Bukkit imports
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thoughtsofglought.deathstorage.Listeners.DeathStorageDchestListener;
import com.thoughtsofglought.deathstorage.Listeners.DeathStorageEntityListener;
import com.thoughtsofglought.deathstorage.Listeners.DeathStoragePlayerListener;

public class DeathStorage extends JavaPlugin {
	
	private File versionFile = new File(this.getDataFolder() + File.separator + "VERSION");
	File configFile;
	FileConfiguration config;
	
	Logger log = Logger.getLogger("Minecraft");
	
	
	
	public void onEnable(){
	    
		configFile = new File(getDataFolder(), "Config.yml");
		
		try{
			firstRun();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		config = new YamlConfiguration();
		loadYamls();
		
		log.info("DeathStorage has Been Enabled");
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(playerListener, this);
		pm.registerEvents(EntityListener, this);
		pm.registerEvents(DchestListener, this);
	}
	
	public void onDisable(){
		saveYamls();
		log.info("DeathStorage has Been Disabled");
	}
	
	private void firstRun() throws Exception{
		if(!configFile.exists()){
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
	}
	private void copy(InputStream in, File file){
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len=in.read(buf)) >0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveYamls() {
		try {
			config.save(configFile);		
			} catch (IOException e){
				e.printStackTrace();
			}
	}
	
	public void loadYamls(){
		try {
			config.load(configFile);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void writeVersion() {
		try {
			versionFile.createNewFile();
			BufferedWriter vout = new BufferedWriter(new FileWriter(versionFile));
			vout.write(this.getDescription().getVersion());
			vout.close();
		} catch (IOException ex){
			ex.printStackTrace();
		} catch (SecurityException ex){
			ex.printStackTrace();
		}
	}
	
	public String readVersion(){
		byte[] buffer = new byte[(int) versionFile.length()];
		BufferedInputStream f = null;
		try {
			
			 f = new BufferedInputStream(new FileInputStream(versionFile));
			 f.read(buffer);
		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (f != null) try { f.close();} catch  (IOException ignored){}
		}
	     return new String(buffer);
	}
		
	public void updateFromLevel (int updateLevel){
		//If we say we are updating from -1, no special code needs to be run, we just need to update the VERSION file.
		if(updateLevel == -1){
			writeVersion();
			return;
		}
		if(updateLevel == 1){
		 // for later use.	
		}
		if(updateLevel == 2){
		// For later use.	
		}
		
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
