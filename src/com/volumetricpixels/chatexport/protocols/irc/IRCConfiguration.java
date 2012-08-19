package com.volumetricpixels.chatexport.protocols.irc;

import java.util.List;

import com.volumetricpixels.chatexport.protocols.ProtocolConfiguration;

/**
 * Set of configuration options for IRC
 */
public class IRCConfiguration extends ProtocolConfiguration {
    
    final String server;
    final int port;
    final String nick;
    final String password;
    final List<String> channels;

    public IRCConfiguration(String server, int port, String nick, String password, List<String> channels) {
        this.server = server;
        this.port = port;
        this.nick = nick;
        this.password = password;
        this.channels = channels;
    }
    
}
