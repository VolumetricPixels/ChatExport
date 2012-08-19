package com.volumetricpixels.chatexport.protocols.irc;

import java.util.List;

import com.volumetricpixels.chatexport.protocols.ProtocolConfiguration;

public class IRCConfiguration extends ProtocolConfiguration {
    
    public final String server;
    public final int port;
    public final String nick;
    public final List<String> channels;

    public IRCConfiguration(String server, int port, String nick, List<String> channels) {
        this.server = server;
        this.port = port;
        this.nick = nick;
        this.channels = channels;
    }
    
}
