package com.kanoa.LightningRod;

import java.util.HashMap;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Rods implements Listener{
	private LightningRod plugin;
	int rodID;
	HashMap<Player, Long> lasttime =  new HashMap<Player, Long>();

	public Rods (LightningRod plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		rodID = plugin.getConfig().getInt("rodID");
		if(Material.getMaterial(rodID) == null){
			plugin.getLogger().warning("[LightningRod] Warning: the rodID " + rodID + " is invaild!!!");
			plugin.getLogger().warning("[LightningRod] Defaulting to a Blaze Rod (369)");
			rodID = 369;
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){

		if(event.getPlayer().getItemInHand().getType() == Material.getMaterial(rodID)){
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
				String name = event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName();
				ItemMeta used_rod = event.getPlayer().getItemInHand().getItemMeta();
				if(name == null){
					name = "null";
				}
				if(!lasttime.containsKey(event.getPlayer())){
					lasttime.put(event.getPlayer(), 0l);
				}
				if(used_rod.hasLore()){
					//start of "health rod" code
					if(used_rod.getLore().contains("health")){

						if(event.getPlayer().hasPermission("lr.use.healthrod") && plugin.getConfig().getBoolean("enabled.health_rod")){

							int rod_cost = plugin.getConfig().getInt("cost.health_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;
							int currhealth = event.getPlayer().getHealth();

							if(currhealth != 20){
								if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){

									int newhealth = currhealth + plugin.getConfig().getInt("effect.health_rod");
									if(newhealth > 20){
										newhealth = 20;
									}

									//rod cost code
									ItemStack inHand = event.getPlayer().getItemInHand();
									if(inHand.getAmount() == 1){
										event.getPlayer().setItemInHand(null);
									}
									else{
										inHand.setAmount(inHand.getAmount() - rod_cost);
										event.getPlayer().setItemInHand(inHand);
									}
									//end of rod cost code
									event.getPlayer().getWorld().strikeLightningEffect(event.getPlayer().getLocation());
									event.getPlayer().setHealth(newhealth);
									lasttime.put(event.getPlayer(), System.currentTimeMillis());
								}
								else{
									event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
								}
							}
						}
					}
					//end of "health rod" code

					//start of "companion rod" code
					if(used_rod.getLore().contains("companion")){

						if(event.getPlayer().hasPermission("lr.use.companionrod") && plugin.getConfig().getBoolean("enabled.companion_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.companion_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								for(int x = 0; x < plugin.getConfig().getInt("effect.companion_rod"); x++){
									Wolf wolf = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Wolf.class);
									wolf.setTamed(true);
									wolf.setOwner(event.getPlayer());
								}

								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "companion rod" code

					//start of "lightning rod" code
					if(used_rod.getLore().contains("lightning")){

						if(event.getPlayer().hasPermission("lr.use.lightningrod") && plugin.getConfig().getBoolean("enabled.lightning_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.lightning_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								event.getPlayer().getWorld().strikeLightning(event.getPlayer().getTargetBlock(null, plugin.getConfig().getInt("effect.lightning_rod")).getLocation());
								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "lightning rod" code

					//start of "hunger rod" code
					if(used_rod.getLore().contains("hunger")){

						if(event.getPlayer().hasPermission("lr.use.hungerrod")  && plugin.getConfig().getBoolean("enabled.hunger_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.hunger_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;
							int currhunger = event.getPlayer().getFoodLevel();

							if(currhunger != 20){
								if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){

									int newhunger = currhunger + plugin.getConfig().getInt("effect.hunger_rod");
									if(newhunger > 20){
										newhunger = 20;
									}

									//rod cost code
									ItemStack inHand = event.getPlayer().getItemInHand();
									if(inHand.getAmount() == 1){
										event.getPlayer().setItemInHand(null);
									}
									else{
										inHand.setAmount(inHand.getAmount() - rod_cost);
										event.getPlayer().setItemInHand(inHand);
									}
									//end of rod cost code
									event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 50);
									event.getPlayer().setFoodLevel(newhunger);
									lasttime.put(event.getPlayer(), System.currentTimeMillis());
								}
								else{
									event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
								}
							}
						}
					}
					//end of "hunger rod" code

					//start of "creeper rod" code
					if(used_rod.getLore().contains("creeper")){

						if(event.getPlayer().hasPermission("lr.use.creeperrod") && plugin.getConfig().getBoolean("enabled.creeper_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.creeper_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								for(int x = 0; x < plugin.getConfig().getInt("effect.creeper_rod"); x++){
									event.getPlayer().getWorld().spawn(event.getPlayer().getTargetBlock(null, 200).getLocation(), Creeper.class);
								}

								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "creeper rod" code

					//start of "explosion rod" code
					if(used_rod.getLore().contains("explosion")){

						if(event.getPlayer().hasPermission("lr.use.explosionrod") && plugin.getConfig().getBoolean("enabled.explosion_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.explosion_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								event.getPlayer().getWorld().createExplosion(event.getPlayer().getTargetBlock(null, plugin.getConfig().getInt("effect.explosion_rod")).getLocation(), 3.5f);
								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "explosion rod" code

					//start of "ender rod" code
					if(used_rod.getLore().contains("ender")){

						if(event.getPlayer().hasPermission("lr.use.enderrod")  && plugin.getConfig().getBoolean("enabled.ender_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.ender_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								event.getPlayer().launchProjectile(EnderPearl.class);
								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "ender rod" code

					//start of "parkour rod" code
					if(used_rod.getLore().contains("parkour")){

						if(event.getPlayer().hasPermission("lr.use.parkourrod")  && plugin.getConfig().getBoolean("enabled.parkour_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.parkour_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								int time = plugin.getConfig().getInt("effect.parkour_rod") * 20;
								PotionEffect jump = new PotionEffect(PotionEffectType.JUMP, time, 1);
								PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, time, 2);
								event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
								event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
								event.getPlayer().addPotionEffect(jump);
								event.getPlayer().addPotionEffect(speed);
								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "ender rod" code

					//start of "fire rod" code
					if(used_rod.getLore().contains("fire")){

						if(event.getPlayer().hasPermission("lr.use.firerod")  && plugin.getConfig().getBoolean("enabled.fire_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.fire_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								event.getPlayer().launchProjectile(SmallFireball.class);
								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "fire rod" code

					//start of "arrow rod" code
					if(used_rod.getLore().contains("arrow")){

						if(event.getPlayer().hasPermission("lr.use.arrowrod")  && plugin.getConfig().getBoolean("enabled.arrow_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.arrow_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;

							long ticks;
							int ticksRaw = 0;

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								final Player player = event.getPlayer();
								for(int x = 0; x < plugin.getConfig().getInt("effect.arrow_rod"); x++){
									ticksRaw = x * 3;
									ticks = Long.valueOf(ticksRaw);

									plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
										public void run() {
											player.launchProjectile(Arrow.class);
										}
									}, ticks);
								}
								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "arrow rod" code

					//start of "weather rod" code
					if(used_rod.getLore().contains("weather")){

						if(event.getPlayer().hasPermission("lr.use.weatherrod")  && plugin.getConfig().getBoolean("enabled.weather_rod")){
							int rod_cost = plugin.getConfig().getInt("cost.weather_rod");
							int regaintime = plugin.getConfig().getInt("regen_time");
							int rt = regaintime * 1000;
							World world = event.getPlayer().getWorld();

							if(System.currentTimeMillis() - lasttime.get(event.getPlayer()) > rt){
								if(world.hasStorm()){
									world.setStorm(false);
									world.setThundering(false);
									event.getPlayer().sendMessage("You just stoped a storm!!!");
								}
								else{
									world.setStorm(true);
									world.setThundering(true);
									event.getPlayer().sendMessage("You just started a storm!!!");
								}

								//rod cost code
								ItemStack inHand = event.getPlayer().getItemInHand();
								if(inHand.getAmount() == 1){
									event.getPlayer().setItemInHand(null);
								}
								else{
									inHand.setAmount(inHand.getAmount() - rod_cost);
									event.getPlayer().setItemInHand(inHand);
								}
								//end of rod cost code
								lasttime.put(event.getPlayer(), System.currentTimeMillis());
							}
							else{
								event.getPlayer().sendMessage("Slow down! Magic takes " + regaintime + " secounds to regain power!");
							}
						}
					}
					//end of "weather rod" code
				}
			}
		}
	}	
}
