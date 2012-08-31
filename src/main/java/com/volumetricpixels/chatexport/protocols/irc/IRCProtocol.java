package com.volumetricpixels.chatexport.protocols.irc;

import java.util.HashMap;
import java.util.Map;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import org.spout.api.Server;
import org.spout.api.Spout;

import com.volumetricpixels.chatexport.ChatExport;
import com.volumetricpixels.chatexport.protocols.Protocol;
import com.volumetricpixels.chatexport.protocols.ProtocolType;

/**
 * Represents the IRC protocol using PircBotX
 */
public class IRCProtocol extends Protocol {
    
    private Map<String, PircBotX> serverBots = new HashMap<String, PircBotX>();
    private IRCConfiguration conf;

    public IRCProtocol(IRCConfiguration config) {
        super(ProtocolType.IRC, config);
        this.conf = config;
    }
    
    @Override
    public void enable() {
        for (IRCServer s : conf.servers) {
            PircBotX bot = new PircBotX();
            bot.getListenerManager().addListener(new IRCListener());
            serverBots.put(s.hostname + String.valueOf(s.port), bot);
        }
    }

    @Override
    public void disable() {
        for (PircBotX bot : serverBots.values()) {
            bot.disconnect();
        }
    }

    @Override
    public void exportMessage(String sender, String message) {
        for (PircBotX bot : serverBots.values()) {
            for (Channel c : bot.getChannels()) {
                bot.sendMessage(c, sender + ": " + message);
            }
        }
    }
    
    private class IRCListener extends ListenerAdapter<PircBotX> {
        
        @Override
        public void onMessage(MessageEvent<PircBotX> e) {
            ((Server) Spout.getEngine()).broadcastMessage("[" + e.getChannel().getName() + "]" + e.getUser().getNick() + ": " + e.getMessage());
            for (Protocol p : ChatExport.enabledProtocols) {
                if (p.getType() != ProtocolType.IRC) {
                    p.exportMessage("[IRC] [" + e.getChannel().getName() + "] " + e.getUser().getNick(), e.getMessage());
                }
            }
        }
        
    }

    @Override
    public String getName() {
        return "IRC";
    }
    
}
