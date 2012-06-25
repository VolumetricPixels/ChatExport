package com.volumetricpixels.chatexport;

import java.io.File;

import com.volumetricpixels.chatexport.listeners.*;
import com.volumetricpixels.chatexport.commands.*;

import org.spout.api.plugin.CommonPlugin;

public class ChatExport extends CommonPlugin {

	@Override
	public void onDisable() {
		//TODO close connections
		
		getLogger().info(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " disabled");
	}

	@Override
	public void onEnable() {
	    // Create the directory and files as needed
	    new File(this.getDataFolder().toString()).mkdir();
	    
	    // Set up listeners
	    this.getEngine().getEventManager().registerEvents(new ChatListener(), this);
	    
	    // Set up commands
	    this.getEngine().getRootCommand().addSubCommand(this.getEngine(), "chatexport").setHelp("activates and deactivates chatexport").setExecutor(new ChatExportCommand(this));
		
		getLogger().info(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " enabled");
	}

}
