package com.volumetricpixels.chatexport;

import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerChatEvent;

import com.volumetricpixels.chatexport.protocols.Protocol;

/**
 * Listen and export Spout chat
 */
public class ChatListener implements Listener {
    
    // If a player enters a chat message forward to other servers
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        if (ChatExport.activated) {
            // Call all protocol exporters
            for (Protocol p : ChatExport.enabledProtocols) {
                p.exportMessage(e.getPlayer().getDisplayName(), e.getMessage().asString());
            }
        }
    }
    
}