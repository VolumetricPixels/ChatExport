package com.volumetricpixels.chatexport;

import org.spout.api.plugin.CommonPlugin;

public class ChatExport extends CommonPlugin {

	@Override
	public void onDisable() {
		//TODO
		
		getLogger().info(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " disabled");
	}

	@Override
	public void onEnable() {
		//TODO 
		
		getLogger().info(this.getDescription().getName() + " v" + this.getDescription().getVersion() + " enabled");
	}

}
