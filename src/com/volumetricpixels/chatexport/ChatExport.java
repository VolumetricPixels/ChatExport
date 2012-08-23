package com.volumetricpixels.chatexport;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.spout.api.Spout;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.scheduler.TaskPriority;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.api.util.config.yaml.YamlConfiguration;

import com.volumetricpixels.chatexport.protocols.Protocol;
import com.volumetricpixels.chatexport.protocols.irc.IRCConfiguration;
import com.volumetricpixels.chatexport.protocols.irc.IRCProtocol;
import com.volumetricpixels.chatexport.protocols.irc.IRCServer;

/**
 * Main class for ChatExport
 * Allows admins to export chat to various protocols
 */
public class ChatExport extends CommonPlugin {
    
    public static boolean activated;
    public static final List<Protocol> enabledProtocols = new ArrayList<Protocol>();
    
    private YamlConfiguration conf;
    private File configFile;
    private Logger log;
    
    @Override
    public void onEnable() {
        // Create the directory and files as needed and load config
        configFile = new File(getDataFolder(), "config.yml");
        conf = new YamlConfiguration(configFile);
        log = getLogger();
        
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
        
        // Load IRC
        if (conf.getNode("Protocols.IRC.Use").getBoolean(false)) {
            log.info("Enabling IRC!");
            List<IRCServer> servers = new ArrayList<IRCServer>();
            Map<String, ConfigurationNode> servahs = conf.getNode("Protocols.IRC.Servers").getChildren();
            
            for (String s : servahs.keySet()) {
                String hostname = null;
                List<String> channels = null;
                int port = 6667;
                String password = null;
                String nsPass = null;
                String botNick = null;
                for (ConfigurationNode n : conf.getNode("Protocols.IRC.Servers." + s).getChildren().values()) {
                    String path = n.getPath().toLowerCase();
                    if (path.endsWith("hostname")) {
                        hostname = n.getString("irc.esper.net");
                    } else if (path.endsWith("channels")) {
                        channels = n.getStringList(Arrays.<String>asList("#volumetricpixels", "#spout"));
                    } else if (path.endsWith("port")) {
                        port = n.getInt(6667);
                    } else if (path.endsWith("password")) {
                        password = n.getString("");
                    } else if (path.endsWith("nick-pass")) {
                        nsPass = n.getString("");
                    } else if (path.endsWith("bot-nick")) {
                        botNick = n.getString("ChatBot");
                    }
                }
                servers.add(new IRCServer(hostname, port, password, nsPass, botNick, channels));
            }
            
            enabledProtocols.add(new IRCProtocol(new IRCConfiguration(servers)));
            log.info("IRC Enabled!");
        }
        
        // Set up listeners
        this.getEngine().getEventManager().registerEvents(new ChatListener(), this);
        
        // Set up commands
        this.getEngine().getRootCommand().addSubCommand(this.getEngine(), "chatexport").setExecutor(new ChatExportCommandExecutor());
        
        Spout.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {

            @Override
            public void run() {
                for (Protocol p : enabledProtocols) {
                    p.enable();
                    log.info("Enabled " + p.getName() + "!");
                }
            }
            
        }, 1000, TaskPriority.HIGHEST);
    }
    
    @Override
    public void onDisable() {
        activated = false;
        for (Protocol p : enabledProtocols) {
            p.disable();
        }
        enabledProtocols.clear();
    }
    
}
