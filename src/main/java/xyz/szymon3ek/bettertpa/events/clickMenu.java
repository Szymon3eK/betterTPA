package xyz.szymon3ek.bettertpa.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.szymon3ek.bettertpa.events.tpRequest;
import xyz.szymon3ek.bettertpa.commands.openMenu;

public class clickMenu implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Inventory clickedInventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if (clickedInventory != null && event.getView().getTitle().equals("§cbetterTPA")) {
            event.setCancelled(true);

            String name = clickedInventory.getItem(event.getSlot()).getItemMeta().getDisplayName().replace("§7» §6", "");
            Player target = Bukkit.getPlayer(name);

            if(clickedInventory.getItem(event.getSlot()) != null && clickedInventory.getItem(event.getSlot()).getType() == Material.BLACK_STAINED_GLASS_PANE) {
                player.closeInventory();
                player.updateInventory();
            }

            if(player == target) {
                player.closeInventory();
                player.updateInventory();
                player.sendMessage("§7» §cNie mozesz sie do siebie teleportowac!");
                player.getWorld().playSound(player.getLocation(), "minecraft:entity.villager.no", 1, 1);
            } else {
                if(clickedInventory.getItem(event.getSlot()) != null && clickedInventory.getItem(event.getSlot()).getType() == Material.PLAYER_HEAD) {
                    tpRequest.teleportRequest(player, target);
                    player.playSound(player.getLocation(), "minecraft:block.note_block.bell", 1, 1);
                    player.closeInventory();
                    player.updateInventory();
                }
            }

            if(clickedInventory.getItem(event.getSlot()) != null && clickedInventory.getItem(event.getSlot()).getType() == Material.RED_WOOL) {
                ItemStack ktorastrona = clickedInventory.getItem(41);
                String strona = ktorastrona.getItemMeta().getDisplayName().replace("§eAktualna strona §6§l", "");

                if(strona.equals("1")) {
                    player.sendMessage("§7» §cNie mozesz przejsc na ta strone!");
                } else {
                    player.closeInventory();
                    player.updateInventory();
                    player.playSound(player.getLocation(), "minecraft:block.ender_chest.open", 1, 1);
                    openMenu.pageEQ(Integer.parseInt(strona) - 1, player);
                }

            }


            // wyjscie
            if(clickedInventory.getItem(event.getSlot()) != null && clickedInventory.getItem(event.getSlot()).getType() == Material.BARRIER) {
                player.closeInventory();
                player.updateInventory();
            }

            if(clickedInventory.getItem(event.getSlot()) != null && clickedInventory.getItem(event.getSlot()).getType() == Material.LIME_WOOL) {

                ItemStack czyaktywny = clickedInventory.getItem(35);

                if(czyaktywny == null) {
                    player.sendMessage("§7» §cNie mozesz przejsc na ta strone!");
                    return;
                } else {
                    player.closeInventory();
                    player.updateInventory();
                    player.playSound(player.getLocation(), "minecraft:block.ender_chest.open", 1, 1);
                    ItemStack ktorastrona = clickedInventory.getItem(41);
                    String strona = ktorastrona.getItemMeta().getDisplayName().replace("§eAktualna strona §6§l", "");
                    openMenu.pageEQ(Integer.parseInt(strona) + 1, player);
                }



            }
        }
    }
}
