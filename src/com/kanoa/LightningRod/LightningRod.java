package com.kanoa.LightningRod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class LightningRod extends JavaPlugin implements Listener {
	public final Logger logger = Logger.getLogger("Minecraft");
	addRecipes addRecipes;
	Rods rods;
	int rodID;

	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		addRecipes = new addRecipes(this);
		rods = new Rods(this);
		logger.info("[LightningRod] Prepare for EPICNESS!");
		addRecipes.add();
		rodID = this.getConfig().getInt("rodID");
		if(Material.getMaterial(rodID) == null){
			this.getLogger().warning("[LightningRod] Warning: the rodID " + rodID + " is invaild!!!");
			this.getLogger().warning("[LightningRod] Defaulting to a Blaze Rod (369)");
			rodID = 369;
		}
		if(this.getConfig().getBoolean("metrics")){
			try {
				Metrics metrics = new Metrics(this);
				metrics.start();
			} catch (IOException e) {
				this.getLogger().warning("Metrics could not be enabled for LightningRod D:");
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("lr")){
			//Player type /lr
			if(args.length < 1){
				sender.sendMessage("§5/lr help");
				sender.sendMessage("§5/lr reload");
				sender.sendMessage("§5/lr spawnrod [rod ID]");
			}
			else if(args.length == 1){
				//Player type /lr reload
				if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("lr.reload")){
						sender.sendMessage("Reloading config.yml file...");
						this.getLogger().info("Reloading config.yml file...");
						this.reloadConfig();
						sender.sendMessage("config.yml has been reloaded!");
						this.getLogger().info("config.yml has been reloaded!");
						return true;
					}
					else{
						sender.sendMessage("§4You don't have permissions!");
						return false;
					}
				}
				else if(args[0].equalsIgnoreCase("spawnrod")){
					if (!(sender instanceof Player)){
						sender.sendMessage("You must be a player to execute that command!");
						return true;
					}
					if(sender.hasPermission("lr.spawnrod")){
						sender.sendMessage("§6Commend usage: /lr spawnrod [rod ID]");
						sender.sendMessage("§6Note: [rod ID] MUST be in all lowercase!");
						return true;
					}
					else{
						sender.sendMessage("§4You don't have permissions!");
						return false;
					}
				}
				//Player type /lr help
				else if(args[0].equalsIgnoreCase("help")){
					if(sender.hasPermission("lr.help")){
						sender.sendMessage("§6Please check out LightningRod's Bukkit Dev page for help at:");
						sender.sendMessage("§6http://dev.bukkit.org/server-mods/kanoas-lightning-rods/");
						return true;
					}
					else{
						sender.sendMessage("§4You don't have permissions!");
						return false;
					}
				}
			}
			else if(args.length == 2){
				if(args[0].equalsIgnoreCase("spawnrod")){
					if (!(sender instanceof Player)){
						sender.sendMessage("You must be a player to execute that command!");
						return true;
					}
					Player player = (Player) sender;
					if(sender.hasPermission("lr.spawnrod")){
						//Give the player a set number of Rods
						ItemStack spawnRod = new ItemStack(rodID, this.getConfig().getInt("spawn_number"));
						ItemMeta spawnRod_meta = spawnRod.getItemMeta();
						spawnRod_meta.setDisplayName(args[1] + " Rod");
						ArrayList<String> spawnRod_lore = new ArrayList<String> ( );
						spawnRod_lore.add(args[1]);
						spawnRod_meta.setLore(spawnRod_lore);
						spawnRod.setItemMeta(spawnRod_meta);
						player.getInventory().addItem(spawnRod);
						sender.sendMessage("§6Note: You can spawn a rod with any ID, but only Rods with an actual Rod ID will work");
						//Rods have been given!
						return true;
					}
					else{
						sender.sendMessage("§4You don't have permission!");
						return false;
					}
				}
			}
			return false;
		}
		//Player type /lr [arg0] [arg1] +
		else{
			sender.sendMessage("§6Too many arguments, please use no more then 1!");
			sender.sendMessage("§6Type /lr help");
			return false;
		}
	}
}