package xyz.szymon3ek.bettertpa.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import xyz.szymon3ek.bettertpa.events.tpRequest;

import java.util.HashMap;
import java.util.UUID;

public class tpaccept implements CommandExecutor {

    private boolean check(HashMap<Player, Player> request, Player target, Player player) {

        return request.containsKey(target) && request.get(target) == player;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length != 1) {
            sender.sendMessage("§7» §cUzycie: §4/tpaccept <gracz>");
            return true;
        }


        Player p = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        HashMap<Player, Player> request = tpRequest.getRequestMap();



        if(sender == target) {
            sender.sendMessage("§7» §cNie mozesz zaakceptowac prosby o teleportacje od siebie samego!");
            p.playSound(p.getLocation(), "minecraft:entity.villager.no", 1, 1);
            return true;
        }
        else {
            if (request.containsKey(target)) {

                if(check(request, target, p)) {
                    p.playSound(p.getLocation(), "minecraft:entity.villager.yes", 1, 1);
                    p.sendMessage("§7» §aZaakceptowales prosbe o teleportacje od §2" + target.getName() + "§a!");
                    target.sendMessage("§7» §a§2" + p.getName() + " §a zaakceptowal twoja prosbe o teleportacje!");
                    target.teleport(p.getLocation());
                    request.remove(target, p);
                    return true;
                } else {
                    p.sendMessage("§7» §cNie ma takiej prosby o teleportacje!");
                    p.playSound(p.getLocation(), "minecraft:entity.villager.no", 1, 1);
                    return true;
                }



            } else {
                p.sendMessage("§7» §cNie ma takiej prosby o teleportacje!");
                p.playSound(p.getLocation(), "minecraft:entity.villager.no", 1, 1);
                return true;
            }

        }
    }
}