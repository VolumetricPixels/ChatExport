package com.volumetricpixels.chatexport.commands;

import org.spout.api.command.CommandExecutor;
import org.spout.api.command.Command;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.exception.CommandException;

import com.volumetricpixels.chatexport.ChatExport;

public class ChatExportCommand implements CommandExecutor {
    
    private ChatExport plugin;
    
    public ChatExportCommand (ChatExport instance){
        plugin = instance;
     }

    @Override
    public boolean processCommand(CommandSource arg0, Command arg1,
            CommandContext arg2) throws CommandException {
        // TODO Auto-generated method stub
        return false;
    }
}
