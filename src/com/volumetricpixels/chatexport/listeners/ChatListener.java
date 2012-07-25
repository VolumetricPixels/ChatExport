package com.volumetricpixels.chatexport.listeners;

import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.player.Player;

import com.volumetricpixels.chatexport.ChatExport;
import com.volumetricpixels.chatexport.ChatExporter;

public class ChatListener implements Listener {
    
    // If a player enters a chat message forward to other servers
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        if (ChatExport.activated) {
            // Call Network and Protocol Handler
            // TODO potentially remove unneeded messages
            String name = p.getDisplayName();
            ChatExporter.newMessage(name, message);
        }
    }
    
}