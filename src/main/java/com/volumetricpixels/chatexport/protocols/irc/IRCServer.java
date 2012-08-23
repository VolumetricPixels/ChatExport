package com.volumetricpixels.chatexport.protocols.irc;

import java.util.List;

public class IRCServer {
    
    final String hostname;
    final int port;
    final String password;
    final String nsPass;
    final String botNick;
    final List<String> channels;
    
    public IRCServer(String hostname, int port, String password, String nsPass, String botNick, List<String> channels) {
        this.hostname = hostname;
        this.port = port;
        this.password = password;
        this.nsPass = nsPass;
        this.botNick = botNick;
        this.channels = channels;
    }
    
}
