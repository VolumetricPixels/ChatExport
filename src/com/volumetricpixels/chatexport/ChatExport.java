package com.volumetricpixels.chatexport;

import java.io.File;

import com.volumetricpixels.chatexport.listeners.*;
import com.volumetricpixels.chatexport.commands.*;

import org.spout.api.plugin.CommonPlugin;

public class ChatExport extends CommonPlugin {
    
    public static boolean activated;
    
	@Override
	public void onDisable() {
		//TODO close connections
		
	    // Display unloaded message
		getLogger().info(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " disabled");
	}

	@Override
	public void onEnable() {
	    // Create the directory and files as needed and load config
	    new File(this.getDataFolder().toString()).mkdir();
	        //TODO config...
	    
	    // Load servers
	    activated = true;
	        //TODO actually load stuff based on config
	    
	    // Set up listeners
	    this.getEngine().getEventManager().registerEvents(new ChatListener(), this);
	    
	    //TODO connect with servers that require a permanent connection
	    
	    // Set up commands
	    this.getEngine().getRootCommand().addSubCommand(this.getEngine(), "chatexport").setHelp("activates and deactivates chatexport").setExecutor(new ChatExportCommand(this));
	    
	    // Display loaded message
		getLogger().info(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " enabled");
	}

}
