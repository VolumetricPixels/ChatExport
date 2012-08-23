package com.volumetricpixels.chatexport.protocols;

/**
 * Represents a Protocol in ChatExport
 */
public abstract class Protocol {
    
    private ProtocolType type;
    private ProtocolConfiguration config;
    
    public Protocol(ProtocolType type, ProtocolConfiguration config) {
        this.type = type;
        this.config = config;
    }
    
    public ProtocolType getType() {
        return type;
    }
    
    public ProtocolConfiguration getConfig() {
        return config;
    }
    
    public abstract void enable();

    public abstract void disable();
    
    public abstract void exportMessage(String sender, String message);

    public abstract String getName();
    
}
