package com.volumetricpixels.chatexport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.spout.api.plugin.CommonPlugin;
import org.spout.api.util.config.yaml.YamlConfiguration;

import com.volumetricpixels.chatexport.commands.ChatExportCommand;
import com.volumetricpixels.chatexport.protocols.Protocol;
import com.volumetricpixels.chatexport.protocols.irc.IRCConfiguration;
import com.volumetricpixels.chatexport.protocols.irc.IRCProtocol;

public class ChatExport extends CommonPlugin {
    
    public static boolean activated;
    public static final List<Protocol> enabledProtocols = new ArrayList<Protocol>();
    
    private YamlConfiguration conf;
    private File configFile;
    
    @Override
    public void onDisable() {
        for (Protocol p : enabledProtocols) {
            p.disable();
        }
    }
    
    @Override
    public void onEnable() {
        // Create the directory and files as needed and load config
        configFile = new File(getDataFolder(), "config.yml");
        conf = new YamlConfiguration(configFile);
        
        if (!getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {}
        }
        conf.setWritesDefaults(true);
        
        // Load servers
        activated = true;
        if (conf.getNode("Protocols.IRC.Use").getBoolean(false)) {
            enabledProtocols.add(new IRCProtocol(new IRCConfiguration(conf.getNode("Protocols.IRC.Server.Hostname").getString("irc.esper.net"),
                conf.getNode("Protocols.IRC.Server.Port").getInt(6667), conf.getNode("Protocols.IRC.Bot.Nickname").getString("ChatExportBot"),
                conf.getNode("Protocols.IRC.Bot.Channels").getStringList(new ArrayList<String>()))));
        }
        
        // Set up listeners
        this.getEngine().getEventManager().registerEvents(new ChatListener(), this);
        
        // Set up commands
        this.getEngine().getRootCommand().addSubCommand(this.getEngine(), "chatexport").setExecutor(new ChatExportCommand());
    }
    
}
