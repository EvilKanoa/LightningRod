package com.kanoa.LightningRod;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class addRecipes {
	private LightningRod plugin;
	
	public addRecipes(LightningRod plugin){
		this.plugin = plugin;
	}
	
	public void add(){
		int rodID;
		Material rod;
		rodID = plugin.getConfig().getInt("rodID");
		if(Material.getMaterial(rodID) == null){
			plugin.logger.warning("[LightningRod] Warning: the rodID " + rodID + " is invaild!!!");
			plugin.logger.warning("[LightningRod] Defaulting to a Blaze Rod (369)");
			rodID = 369;
		}
		rod = Material.getMaterial(rodID);
		
		//Setup a variable for the config
		FileConfiguration config = plugin.getConfig();
		//Get Craft_Number and set it to a variable
		int craft_number = plugin.getConfig().getInt("craft_number");
    	//Setup the ItemStack variables 
    	ItemStack health_rod = new ItemStack(rodID, craft_number);
    	ItemStack companion_rod = new ItemStack(rodID, craft_number);
    	ItemStack lightning_rod = new ItemStack(rodID, craft_number);
    	ItemStack hunger_rod = new ItemStack(rodID, craft_number);
    	ItemStack creeper_rod = new ItemStack(rodID, craft_number);
    	ItemStack explosion_rod = new ItemStack(rodID, craft_number);
    	ItemStack ender_rod = new ItemStack(rodID, craft_number);
    	ItemStack parkour_rod = new ItemStack(rodID, craft_number);
    	ItemStack fire_rod = new ItemStack(rodID, craft_number);
    	ItemStack arrow_rod = new ItemStack(rodID, craft_number);
    	ItemStack weather_rod = new ItemStack(rodID, craft_number);
    	//ItemStack variables have been setup
    	
    	//Setup the ItemMeta variables from the ItemStack variables
    	ItemMeta health_meta = health_rod.getItemMeta();
    	ItemMeta companion_meta = companion_rod.getItemMeta();
    	ItemMeta lightning_meta = lightning_rod.getItemMeta();
    	ItemMeta hunger_meta = hunger_rod.getItemMeta();
    	ItemMeta creeper_meta = creeper_rod.getItemMeta();
    	ItemMeta explosion_meta = explosion_rod.getItemMeta();
    	ItemMeta ender_meta = ender_rod.getItemMeta();
    	ItemMeta parkour_meta = parkour_rod.getItemMeta();
    	ItemMeta fire_meta = fire_rod.getItemMeta();
    	ItemMeta arrow_meta = arrow_rod.getItemMeta();
    	ItemMeta weather_meta = weather_rod.getItemMeta();
    	//ItemMeta variables setup
    	
    	//Set the display names of the Lightning Rods
    	health_meta.setDisplayName(config.getString("name.health_rod"));
    	companion_meta.setDisplayName(config.getString("name.companion_rod"));
    	lightning_meta.setDisplayName(config.getString("name.lightning_rod"));
    	hunger_meta.setDisplayName(config.getString("name.hunger_rod"));
    	creeper_meta.setDisplayName(config.getString("name.creeper_rod"));
    	explosion_meta.setDisplayName(config.getString("name.explosion_rod"));
    	ender_meta.setDisplayName(config.getString("name.ender_rod"));
    	parkour_meta.setDisplayName(config.getString("name.parkour_rod"));
    	fire_meta.setDisplayName(config.getString("name.fire_rod"));
    	arrow_meta.setDisplayName(config.getString("name.arrow_rod"));
    	weather_meta.setDisplayName(config.getString("name.weather_rod"));
    	//Display names set
    	
    	//Set up the arrays for lore
    	ArrayList<String> health_lore = new ArrayList<String>();
    	ArrayList<String> companion_lore = new ArrayList<String>();
    	ArrayList<String> lightning_lore = new ArrayList<String>();
    	ArrayList<String> hunger_lore = new ArrayList<String>();
    	ArrayList<String> creeper_lore = new ArrayList<String>();
    	ArrayList<String> explosion_lore = new ArrayList<String>();
    	ArrayList<String> ender_lore = new ArrayList<String>();
    	ArrayList<String> parkour_lore = new ArrayList<String>();
    	ArrayList<String> fire_lore = new ArrayList<String>();
    	ArrayList<String> arrow_lore = new ArrayList<String>();
    	ArrayList<String> weather_lore = new ArrayList<String>();
    	//The arrays have been setup
    	
    	//Add the IDs to the lore arrays
    	health_lore.add("health");
    	companion_lore.add("companion");
    	lightning_lore.add("lightning");
    	hunger_lore.add("hunger");
    	creeper_lore.add("creeper");
    	explosion_lore.add("explosion");
    	ender_lore.add("ender");
    	parkour_lore.add("parkour");
    	fire_lore.add("fire");
    	arrow_lore.add("arrow");
    	weather_lore.add("weather");
    	//IDs have been added to the lore arrays
    	
    	//Attache the arrays to the ItemMeta
    	health_meta.setLore(health_lore);
    	companion_meta.setLore(companion_lore);
    	lightning_meta.setLore(lightning_lore);
    	hunger_meta.setLore(hunger_lore);
    	creeper_meta.setLore(creeper_lore);
    	explosion_meta.setLore(explosion_lore);
    	ender_meta.setLore(ender_lore);
    	parkour_meta.setLore(parkour_lore);
    	fire_meta.setLore(fire_lore);
    	arrow_meta.setLore(arrow_lore);
    	weather_meta.setLore(weather_lore);
    	//The arrays have been attached
    	
    	//Set the ItemMeta variables to there ItemStack variables
    	health_rod.setItemMeta(health_meta);
    	companion_rod.setItemMeta(companion_meta);
    	lightning_rod.setItemMeta(lightning_meta);
    	hunger_rod.setItemMeta(hunger_meta);
    	creeper_rod.setItemMeta(creeper_meta);
    	explosion_rod.setItemMeta(explosion_meta);
    	ender_rod.setItemMeta(ender_meta);
    	parkour_rod.setItemMeta(parkour_meta);
    	fire_rod.setItemMeta(fire_meta);
    	arrow_rod.setItemMeta(arrow_meta);
    	weather_rod.setItemMeta(weather_meta);
    	//ItemStack variables are completed 
    	
    	//Setup the ShapedRecipe so we can make the rods craftable
    	ShapedRecipe health_recipe = new ShapedRecipe(health_rod);
    	ShapedRecipe companion_recipe = new ShapedRecipe(companion_rod);
    	ShapedRecipe lightning_recipe = new ShapedRecipe(lightning_rod);
    	ShapedRecipe hunger_recipe = new ShapedRecipe(hunger_rod);
    	ShapedRecipe creeper_recipe = new ShapedRecipe(creeper_rod);
    	ShapedRecipe explosion_recipe = new ShapedRecipe(explosion_rod);
    	ShapedRecipe ender_recipe = new ShapedRecipe(ender_rod);
    	ShapedRecipe parkour_recipe = new ShapedRecipe(parkour_rod);
    	ShapedRecipe fire_recipe = new ShapedRecipe(fire_rod);
    	ShapedRecipe arrow_recipe = new ShapedRecipe(arrow_rod);
    	ShapedRecipe weather_recipe = new ShapedRecipe(weather_rod);
    	//The ShapedRecipes have been loaded
    	
    	//Setting the other ingredients and shape for health_recipe
    	health_recipe.shape("MBG");
    	health_recipe.setIngredient('M', Material.MELON);
    	health_recipe.setIngredient('G', Material.GOLD_NUGGET);
    	//Setting the other ingredients and shape for companion_recipe
    	companion_recipe.shape(" W ", "WBW", " W ");
    	companion_recipe.setIngredient('W', Material.BONE);
    	//Setting the other ingredients and shape for lightning_recipe
    	lightning_recipe.shape(" I ", " B ", " I ");
    	lightning_recipe.setIngredient('I', Material.IRON_INGOT);
    	//Setting the other ingredients and shape for hunger_recipe
    	hunger_recipe.shape(" P ", "FBF", " P ");
    	hunger_recipe.setIngredient('P', Material.COOKED_BEEF);
    	hunger_recipe.setIngredient('F', Material.BREAD);
    	//Setting the other ingredients and shape for creeper_recipe
    	creeper_recipe.shape(" G ", "GBG", " G ");
    	creeper_recipe.setIngredient('G', Material.SULPHUR);
    	//Setting the other ingredients and shape for explosion_recipe
    	explosion_recipe.shape(" B ", " T ");
    	explosion_recipe.setIngredient('T', Material.TNT);
    	//Setting the other ingredients and shape for ender_recipe
    	ender_recipe.shape("EBE");
    	ender_recipe.setIngredient('E', Material.ENDER_PEARL);
    	//Setting the other ingredients and shape for parkour_recipe
    	parkour_recipe.shape("SBU");
    	parkour_recipe.setIngredient('S', Material.SLIME_BALL);
    	parkour_recipe.setIngredient('U', Material.SUGAR);
    	//Setting the other ingredients and shape for fire_recipe
    	fire_recipe.shape("CBG");
    	fire_recipe.setIngredient('C', Material.COAL);
    	fire_recipe.setIngredient('G', Material.SULPHUR);
    	//Setting the other ingredients and shape for arrow_recipe
    	arrow_recipe.shape("AAA", "ABA", "AAA");
    	arrow_recipe.setIngredient('A', Material.ARROW);
    	//Setting the other ingredients and shape for arrow_recipe
    	weather_recipe.shape(" I ", "SBS", " I ");
    	weather_recipe.setIngredient('S', Material.SNOW_BALL);
    	weather_recipe.setIngredient('I', Material.IRON_INGOT);
    	
    	
    	//Setting the blaze rod as ingredient 'B'
    	health_recipe.setIngredient('B', rod);
    	companion_recipe.setIngredient('B', rod);
    	lightning_recipe.setIngredient('B', rod);
    	hunger_recipe.setIngredient('B', rod);
    	creeper_recipe.setIngredient('B', rod);
    	explosion_recipe.setIngredient('B', rod);
    	ender_recipe.setIngredient('B', rod);
    	parkour_recipe.setIngredient('B', rod);
    	fire_recipe.setIngredient('B', rod);
    	arrow_recipe.setIngredient('B', rod);
    	weather_recipe.setIngredient('B', rod);
    	//Blaze rod as been set as ingredient 'B'
    	
    	//Loading the full recipes into memory
    	plugin.getServer().addRecipe(health_recipe);
    	plugin.getServer().addRecipe(companion_recipe);
    	plugin.getServer().addRecipe(lightning_recipe);
    	plugin.getServer().addRecipe(hunger_recipe);
    	plugin.getServer().addRecipe(creeper_recipe);
    	plugin.getServer().addRecipe(explosion_recipe);
    	plugin.getServer().addRecipe(ender_recipe);
    	plugin.getServer().addRecipe(parkour_recipe);
    	plugin.getServer().addRecipe(fire_recipe);
    	plugin.getServer().addRecipe(arrow_recipe);
    	plugin.getServer().addRecipe(weather_recipe);
    	//Done loading recipes
    	
    	//Tell the console that all recipes have been loaded
    	plugin.getLogger().info("[LightningRod] All recipes have been loaded");
    }
}
