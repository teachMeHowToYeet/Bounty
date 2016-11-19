package me.blazeit.com;

import java.util.HashMap;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BountyEvents
  implements Listener
{
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e)
  {
    if (Bounty.getInstance().getConfig().getBoolean("show-bounty-status-in-chat")) {
      if (Bounty.bounties.containsKey(e.getPlayer().getName())) {
        e.setFormat(e.getFormat().replace("[BOUNTY]", "?"));
      } else {
        e.setFormat(e.getFormat().replace("[BOUNTY]", ""));
      }
    }
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent e)
  {
    if (Bounty.bounties.containsKey(e.getPlayer().getName()))
    {
      Bukkit.broadcastMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + e.getPlayer().getName() + "'s bounty will be claimable when they login again.");
      Bounty.getInstance().addBountyToFile(e.getPlayer(), (Integer)Bounty.bounties.get(e.getPlayer().getName()));
      Bounty.bounties.remove(e.getPlayer().getName());
      Bounty.getInstance().saveBountyFile();
    }
  }
  
  @EventHandler
  public void onKick(PlayerKickEvent e)
  {
    if (Bounty.bounties.containsKey(e.getPlayer().getName()))
    {
      Bukkit.broadcastMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + e.getPlayer().getName() + "'s bounty will be claimable when they login again.");
      Bounty.getInstance().addBountyToFile(e.getPlayer(), (Integer)Bounty.bounties.get(e.getPlayer().getName()));
      Bounty.bounties.remove(e.getPlayer().getName());
      Bounty.getInstance().saveBountyFile();
    }
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    if (Bounty.getInstance().bfile.contains(e.getPlayer().getUniqueId().toString()))
    {
      UUID id = UUID.fromString(e.getPlayer().getUniqueId().toString());
      Player player = Bukkit.getPlayer(id);
      int price = Bounty.getInstance().bfile.getInt(e.getPlayer().getUniqueId().toString());
      Bounty.bounties.put(player.getName(), Integer.valueOf(price));
      Bukkit.broadcastMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + e.getPlayer().getName() + " logged in and has a claimable bounty!");
      Bounty.getInstance().bfile.set(e.getPlayer().getUniqueId().toString(), null);
      Bounty.getInstance().saveBountyFile();
    }
  }
  
  @EventHandler
  public void onKill(PlayerDeathEvent e)
  {
    if (!(e.getEntity().getKiller() instanceof Player)) {
      return;
    }
    if (!Bounty.bounties.containsKey(e.getEntity().getName())) {
      return;
    }
    Player p = e.getEntity();
    Player killer = e.getEntity().getKiller();
    if (Bounty.bounties.containsKey(p.getName()))
    {
      int price = ((Integer)Bounty.bounties.get(p.getName())).intValue();
      p.playEffect(EntityEffect.WITCH_MAGIC);
      Bounty.econ.depositPlayer(killer, price);
      killer.sendMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + "You got " + ChatColor.GREEN + ChatColor.UNDERLINE + "$" + price + ChatColor.YELLOW + " for claiming the bounty on " + p.getName() + "!");
      Bukkit.broadcastMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + "The bounty on " + ChatColor.UNDERLINE + p.getName() + ChatColor.YELLOW + " has been claimed by " + killer.getName() + ".");
      Bounty.bounties.remove(p.getName());
    }
  }
}
