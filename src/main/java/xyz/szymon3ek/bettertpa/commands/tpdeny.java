package xyz.szymon3ek.bettertpa.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.szymon3ek.bettertpa.events.tpRequest;

import java.util.HashMap;
import java.util.UUID;

public class tpdeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            sender.sendMessage("§7» §cUzycie: §4/tpdeny <gracz>");
            return true;
        }


        Player p = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);


        if (sender == target) {
            sender.sendMessage("§7» §cNie mozesz anulowac prosby o teleportacje od siebie samego!");
            p.playSound(p.getLocation(), "minecraft:entity.villager.no", 1, 1);
            return true;
        }

        try {
            HashMap<Player, Player> request = tpRequest.getRequestMap();

/*            if (request.get(target.getUniqueId())) {
                sender.sendMessage("§7» §aAnulowales prosbe od §2" + target.getName() + "§a!");
                request.remove(target.getUniqueId());
                target.sendMessage("§7» §cGracz §4" + sender.getName() + " §cAnulowal twoja prosbe o teleportacje!");
                return true;
            }*/
        } catch (NullPointerException e) {
            sender.sendMessage("§7» §cGracz §4" + args[0] + " §cnie wyslal do ciebie prosby o teleportacje!");
            return true;
        }

        return true;

    }

}
