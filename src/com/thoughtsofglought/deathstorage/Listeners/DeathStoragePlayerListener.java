package com.thoughtsofglought.deathstorage.Listeners;


//Bukkit imports
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.thoughtsofglought.deathstorage.DeathStorage;

public class DeathStoragePlayerListener extends PlayerListener {
	
	public static DeathStorage plugin; public DeathStoragePlayerListener(DeathStorage instance){
		plugin = instance;
		
		
	}
	
	public void onPlayerMoveEvent(PlayerMoveEvent event){
		
		
	}
	
	
	

}
