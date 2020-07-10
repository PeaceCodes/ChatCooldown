package me.peacetuba.chatcooldown.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.peacetuba.chatcooldown.ChatCooldown;
import me.peacetuba.chatcooldown.cc;

public class setCooldown implements CommandExecutor{

	private ChatCooldown main;
	
	public setCooldown(ChatCooldown main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players are allowed to perform this command!");
			return false;
		}
		
		Player p = (Player) sender;
		
		if(args.length != 1) {
			p.sendMessage(cc.chat("&cUsage: /chatcooldown <seconds/off>"));
			return false;
		}
		
		try {
			int cooldown = Integer.parseInt(args[0]);
			if(cooldown == main.cooldown) {
				p.sendMessage(cc.chat("&cCooldown is already set to: &f" + cooldown + "&c seconds!"));
				return false;
			}
			
			if(cooldown < 0) {
				p.sendMessage(cc.chat("&cPlease dont enter a negative number!"));
				return false;
			} else if(cooldown == 0) {
				p.sendMessage(cc.chat("&cPlease use /chatcooldown off!"));
				return false;
			}
			main.cooldown = cooldown;
			main.isOff = false;
			main.getCooldownManager().getOnCooldown().clear();
			p.sendMessage(cc.chat("&aSet cooldown to &f" + cooldown + "&a seconds!"));
			return false;
		} catch(Exception e) {
			if(!args[0].equalsIgnoreCase("off")) {
				p.sendMessage(cc.chat("&cUsage: /chatcooldown <seconds/off>"));
				return false;
			}
			main.isOff = true;
			main.getCooldownManager().getOnCooldown().clear();
			p.sendMessage(cc.chat("&aTurned off Chat Cooldown!"));
			return false;
		}
	}

}
