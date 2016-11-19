package me.blazeit.com;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Bounty
  extends JavaPlugin
{
  public static Economy econ = null;
  public static Bounty instance;
  public static HashMap<String, Integer> bounties = new HashMap();
  public File f = new File(getDataFolder(), "bounty.yml");
  public YamlConfiguration bfile = YamlConfiguration.loadConfiguration(this.f);
  
  public void onEnable()
  {
    setupEconomy();
    generateFiles();
    instance = this;
    getCommand("bounty").setExecutor(new BountyCommands());
    getServer().getPluginManager().registerEvents(new BountyEvents(), this);
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (this.bfile.contains(p.getUniqueId().toString()))
      {
        UUID id = UUID.fromString(p.getUniqueId().toString());
        Player player = Bukkit.getPlayer(id);
        int price = getInstance().bfile.getInt(p.getUniqueId().toString());
        bounties.put(player.getName(), Integer.valueOf(price));
        getInstance().bfile.set(p.getUniqueId().toString(), null);
        saveBountyFile();
      }
    }
  }
  
  public void onDisable()
  {
    saveFiles();
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (bounties.containsKey(p.getName()))
      {
        this.bfile.set(p.getUniqueId().toString(), bounties.get(p.getName()));
        saveBountyFile();
      }
    }
  }
  
  public void saveFiles()
  {
    saveConfig();
    try
    {
      this.bfile.save(this.f);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private void generateFiles()
  {
    File config = new File(getDataFolder(), "config.yml");
    if (!config.exists())
    {
      saveDefaultConfig();
      getLogger().info("config.yml created.");
    }
    if (!this.f.exists()) {
      try
      {
        this.f.createNewFile();
        getLogger().info("bounty.yml created.");
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static Bounty getInstance()
  {
    return instance;
  }
  
  public void saveBountyFile()
  {
    try
    {
      this.bfile.save(this.f);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void addBountyToFile(Player p, Integer price)
  {
    this.bfile.set(p.getUniqueId().toString(), price);
    saveBountyFile();
  }
  
  private boolean setupEconomy()
  {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
      return false;
    }
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
      return false;
    }
    econ = (Economy)rsp.getProvider();
    return econ != null;
  }
}
