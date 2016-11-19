package me.blazeit.com;

import java.util.HashMap;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class BountyCommands
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("bounty")) {
      if ((sender instanceof Player))
      {
        Player p = (Player)sender;
        if (args.length == 0)
        {
          p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
          p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
          p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
          p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
          if (p.hasPermission("bounty.admin"))
          {
            p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
            p.sendMessage(ChatColor.GOLD + " /bounty reload " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
          }
        }
        else if (args.length == 1)
        {
          if (args[0].equalsIgnoreCase("list"))
          {
            p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " ACTIVE BOUNTIES " + ChatColor.YELLOW + "****");
            if (!Bounty.bounties.isEmpty()) {
              for (String b : Bounty.bounties.keySet()) {
                p.sendMessage(ChatColor.RED + b + ChatColor.GRAY + " - " + ChatColor.GREEN + ChatColor.ITALIC + "$" + Bounty.bounties.get(b));
              }
            } else {
              p.sendMessage(ChatColor.RED + "There are no active bounties.");
            }
          }
          else if (args[0].equalsIgnoreCase("set"))
          {
            p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bounty set (name) (price)");
          }
          else if (args[0].equalsIgnoreCase("add"))
          {
            p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bounty add (name) (price)");
          }
          else if (args[0].equalsIgnoreCase("remove"))
          {
            if (p.isOp())
            {
              p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bounty remove (name)");
            }
            else
            {
              p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
              p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
              p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
              p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
              if (p.hasPermission("bounty.admin"))
              {
                p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
                p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
              }
            }
          }
          else if (args[0].equalsIgnoreCase("reload"))
          {
            if (p.hasPermission("bounty.admin"))
            {
              Bounty.getInstance().reloadConfig();
              p.sendMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.GREEN + ChatColor.BOLD + "Config reloaded!");
            }
            else
            {
              p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
              p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
              p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
              p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
              if (p.hasPermission("bounty.admin"))
              {
                p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
                p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
              }
            }
          }
          else
          {
            p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
            p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
            p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
            p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
            if (p.hasPermission("bounty.admin"))
            {
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
            }
          }
        }
        else if (args.length == 1)
        {
          if (args[0].equalsIgnoreCase("set"))
          {
            p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bounty set (name) (price)");
          }
          else if (args[0].equalsIgnoreCase("set"))
          {
            p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bounty add (name) (price)");
          }
          else
          {
            p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
            p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
            p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
            p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
            if (p.hasPermission("bounty.admin"))
            {
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
            }
          }
        }
        else if (args.length == 2)
        {
          if (args[0].equalsIgnoreCase("set"))
          {
            p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bounty set (name) (price)");
          }
          else if (args[0].equalsIgnoreCase("set"))
          {
            p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/bounty add (name) (price)");
          }
          else if (args[0].equalsIgnoreCase("remove"))
          {
            if (p.hasPermission("bounty.admin"))
            {
              Player rem = Bukkit.getPlayer(args[1]);
              if (rem != null)
              {
                if (Bounty.bounties.containsKey(rem.getName()))
                {
                  Bounty.bounties.remove(rem.getName());
                  Bukkit.broadcastMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.RED + "The bounty on " + ChatColor.UNDERLINE + rem.getName() + ChatColor.RED + " was removed by " + p.getName() + ".");
                }
                else
                {
                  p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "Player does not have a bounty.");
                }
              }
              else {
                p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "Player not found.");
              }
            }
            else
            {
              p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
              p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
              p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
              p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
              if (p.hasPermission("bounty.admin"))
              {
                p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
                p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
              }
            }
          }
          else
          {
            p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
            p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
            p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
            p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
            if (p.hasPermission("bounty.admin"))
            {
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
            }
          }
        }
        else if (args.length == 3)
        {
          if (args[0].equalsIgnoreCase("set"))
          {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null)
            {
              if (target != p)
              {
                if (!Bounty.bounties.containsKey(target.getName())) {
                  try
                  {
                    int price = Integer.parseInt(args[2]);
                    int lowest = Bounty.getInstance().getConfig().getInt("minimum-bounty-price");
                    if (price >= lowest)
                    {
                      if (Bounty.econ.getBalance(p) >= price)
                      {
                        Bounty.bounties.put(target.getName(), Integer.valueOf(price));
                        Bounty.econ.withdrawPlayer(p, price);
                        Bukkit.broadcastMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + "A bounty of " + ChatColor.GREEN + "$" + price + ChatColor.YELLOW + " has been placed on " + ChatColor.GOLD + ChatColor.UNDERLINE + target.getName() + ChatColor.YELLOW + "!");
                        p.sendMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + "You were charged " + ChatColor.UNDERLINE + "$" + price + ChatColor.YELLOW + " for placing a bounty.");
                      }
                      else
                      {
                        p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You do not have enough money to place this bounty.");
                      }
                    }
                    else {
                      p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "The bounty can not be lower than " + ChatColor.GREEN + "$" + lowest + ChatColor.GOLD + "!");
                    }
                  }
                  catch (NumberFormatException ex)
                  {
                    p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "Invalid bounty price.");
                  }
                } else {
                  p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "There is already a bounty on this player, to add money to it use /bounty add.");
                }
              }
              else {
                p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can not set a bounty on yourself!");
              }
            }
            else {
              p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "Player not found.");
            }
          }
          else if (args[0].equalsIgnoreCase("add"))
          {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null)
            {
              if (Bounty.bounties.containsKey(target.getName()))
              {
                if (target != p) {
                  try
                  {
                    int add = Integer.parseInt(args[2]);
                    int old = ((Integer)Bounty.bounties.get(target.getName())).intValue();
                    if (Bounty.econ.getBalance(p) >= add)
                    {
                      Bounty.bounties.put(target.getName(), Integer.valueOf(old + add));
                      Bounty.econ.withdrawPlayer(p, add);
                      Bukkit.broadcastMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.GREEN + "$" + add + ChatColor.YELLOW + " has been added to the bounty on " + ChatColor.GOLD + ChatColor.UNDERLINE + target.getName() + ChatColor.YELLOW + "!");
                      p.sendMessage(ChatColor.GOLD + "BOUNTY: " + ChatColor.YELLOW + "You were charged " + ChatColor.UNDERLINE + "$" + add + ChatColor.YELLOW + " for adding to a bounty.");
                    }
                    else
                    {
                      p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You do not have enough money to add to this bounty.");
                    }
                  }
                  catch (NumberFormatException e)
                  {
                    p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "Invalid bounty price.");
                  }
                } else {
                  p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can't add to your own bounty!");
                }
              }
              else {
                p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "This player does not have a bounty. To set a bounty, use /bounty set.");
              }
            }
            else {
              p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "Player not found.");
            }
          }
          else
          {
            p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
            p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
            p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
            p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
            if (p.hasPermission("bounty.admin"))
            {
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
              p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
            }
          }
        }
        else
        {
          p.sendMessage(ChatColor.YELLOW + "****" + ChatColor.GOLD + ChatColor.BOLD + " BOUNTY HELP " + ChatColor.YELLOW + "****");
          p.sendMessage(ChatColor.GOLD + " /bounty list " + ChatColor.GRAY + ChatColor.ITALIC + "(List all active bounties)");
          p.sendMessage(ChatColor.GOLD + " /bounty set (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Set a bounty on another player)");
          p.sendMessage(ChatColor.GOLD + " /bounty add (name) (price) " + ChatColor.GRAY + ChatColor.ITALIC + "(Add to an existing bounty)");
          if (p.hasPermission("bounty.admin"))
          {
            p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Remove a player's bounty)");
            p.sendMessage(ChatColor.GOLD + " /bounty remove (name) " + ChatColor.GRAY + ChatColor.ITALIC + "(ADMIN: Reload config file)");
          }
        }
      }
      else
      {
        sender.sendMessage("This command can not be used from console!");
      }
    }
    return false;
  }
}
