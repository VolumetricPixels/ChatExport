package com.volumetricpixels.chatexport.protocols.irc;

import java.util.List;

import com.volumetricpixels.chatexport.protocols.ProtocolConfiguration;

/**
 * Set of configuration options for IRC
 */
public class IRCConfiguration extends ProtocolConfiguration {
    
    final List<IRCServer> servers;

    public IRCConfiguration(List<IRCServer> servers) {
        this.servers = servers;
    }
    
}
