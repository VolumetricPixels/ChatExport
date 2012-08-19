package com.volumetricpixels.chatexport.protocols.irc;

import java.io.IOException;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.spout.api.Spout;

import com.volumetricpixels.chatexport.protocols.Protocol;
import com.volumetricpixels.chatexport.protocols.ProtocolType;

public class IRCProtocol extends Protocol {
    
    private PircBotX bot;
    private IRCConfiguration conf;

    public IRCProtocol(IRCConfiguration config) {
        super(ProtocolType.IRC, config);
        this.conf = config;
    }
    
    @Override
    public void enable() {
        this.bot = new PircBotX();
        bot.setName(conf.nick);
        bot.getListenerManager().addListener(new IRCListener());
        try {
            bot.connect(conf.server, conf.port);
            for (String s : conf.channels) {
                bot.joinChannel(s);
            }
        } catch (NickAlreadyInUseException e) {
            Spout.getLogger().severe("[ChatExport] IRC nick already in use!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disable() {
        if (bot != null) {
            bot.disconnect();
        }
    }

    @Override
    public void exportMessage(String sender, String message) {
        for (Channel c : bot.getChannels()) {
            bot.sendMessage(c, sender + ": " + message);
        }
    }
    
    private class IRCListener extends ListenerAdapter<PircBotX> {
        
        @Override
        public void onMessage(MessageEvent<PircBotX> e) {
            Spout.getEngine().broadcastMessage("[" + e.getChannel().getName() + "]" + e.getUser().getNick() + ": " + e.getMessage());
        }
        
    }
    
}
