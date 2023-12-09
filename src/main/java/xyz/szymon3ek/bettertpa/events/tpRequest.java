package xyz.szymon3ek.bettertpa.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;


public class tpRequest {
    static HashMap<Player, Player> request = new HashMap<Player, Player>();
    public static void teleportRequest(Player player, Player target) {

        if(request.containsKey(player) && request.get(player) == target) {
            player.sendMessage("§7» §cJuz wyslales prosbe o teleportacje do §4" + request.get(player).getName() + "§c!");
            player.playSound(player.getLocation(), "minecraft:entity.villager.no", 1, 1);
            return;
        } else {
            target.getWorld().playSound(target.getLocation(), "minecraft:item.goat_horn.sound.0", 1, 1);
            target.sendMessage("§7» §6" + player.getName() + " §7chce sie do ciebie teleportowac!");
            target.sendMessage("§7» §7Wpisz §e/tpaccept " + player.getName() + " §7lub §e/tpdeny " + player.getName() + "§7!");
            player.sendMessage("§7» §aWyslales prosbe o teleportacje do §2" + target.getName() + "§a!");

            request.put(player, target);
        }

    }

    public static HashMap<Player, Player> getRequestMap() {
        return request;
    }

}
