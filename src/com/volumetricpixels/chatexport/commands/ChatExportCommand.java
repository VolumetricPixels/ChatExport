package com.volumetricpixels.chatexport.commands;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandExecutor;
import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.exception.CommandException;

import com.volumetricpixels.chatexport.ChatExport;

public class ChatExportCommand implements CommandExecutor {
    
    @Override
    public boolean processCommand(CommandSource source, Command cmd, CommandContext args) throws CommandException {
        if (cmd.getPreferredName().compareToIgnoreCase("chatexport") == 0) {
            if (source.hasPermission("chatexport.toggle")) {
                if (ChatExport.activated == true) {
                    ChatExport.activated = false;
                    source.sendMessage(ChatStyle.GRAY, "Chat exporting deactivated!");
                } else if (ChatExport.activated == false) {
                    ChatExport.activated = true;
                    source.sendMessage(ChatStyle.GRAY, "Chat exporting reactivated!");
                }
            } else {
                source.sendMessage(ChatStyle.RED, "You don't have permission to do that!");
            }
            return true;
        }
        return false;
    }
    
}
