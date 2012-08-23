package com.volumetricpixels.chatexport;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandExecutor;
import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.exception.CommandException;

/**
 * Executes commands in ChatExport
 */
public class ChatExportCommandExecutor implements CommandExecutor {
    
    @Override
    public void processCommand(CommandSource source, Command cmd, CommandContext args) throws CommandException {
        if (cmd.getPreferredName().equalsIgnoreCase("chatexport")) {
            if (source.hasPermission("chatexport.toggle")) {
                ChatExport.activated = !ChatExport.activated;
                if (ChatExport.activated) {
                    source.sendMessage(ChatStyle.GRAY, "Chat exporting deactivated!");
                } else if (!ChatExport.activated) {
                    source.sendMessage(ChatStyle.GRAY, "Chat exporting reactivated!");
                }
            } else {
                source.sendMessage(ChatStyle.RED, "You don't have permission to do that!");
            }
        }
    }
    
}
