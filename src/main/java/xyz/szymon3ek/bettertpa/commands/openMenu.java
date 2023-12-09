package xyz.szymon3ek.bettertpa.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class openMenu implements CommandExecutor {

    static private List<Player> graczeOnline = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        graczeOnline.removeAll(graczeOnline);
        Player p = (Player) sender;
        graczeOnline.addAll(Bukkit.getOnlinePlayers());
        p.playSound(p.getLocation(), "minecraft:block.conduit.activate", 1, 1);

        pageEQ(1, p);





        return true;
    }

    public static int minpage(int page) {
        if(page == 1) {
            return 0;
        } else {
            return (35 * (page - 1)) + 1;
        }
    }

    public static void pageEQ(int page, Player p) {

        Inventory menu = Bukkit.createInventory(null, 9 * 5, "§cbetterTPA");


        int test = 0;

        for(int i = minpage(page); i < graczeOnline.size(); i++) {


            if(!p.getName().equals(graczeOnline.get(i).getName())) {

                ItemStack gracz = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta graczMeta = (SkullMeta) gracz.getItemMeta();

                graczMeta.setDisplayName("§7» §6" + graczeOnline.get(i).getName());
                List<String> lore = new ArrayList<>();
                lore.add("§7↳ §eKliknij aby wyslac zaproszenie do gracza");
                lore.add("§7↳ §7Gracz jest w swiecie §e" + graczeOnline.get(i).getWorld().getName());

                graczMeta.setLore(lore);

                graczMeta.setOwningPlayer(graczeOnline.get(i));
                gracz.setItemMeta(graczMeta);
                menu.setItem(test, gracz);

            } else {
                ItemStack gracz = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta graczMeta = (SkullMeta) gracz.getItemMeta();

                graczMeta.setDisplayName("§7» §6" + graczeOnline.get(i).getName());
                List<String> lore = new ArrayList<>();
                lore.add("§7↳ §cNie mozesz sie do siebie teleportowac!");
                lore.add("§7↳ §7Jestes w swiecie §e" + graczeOnline.get(i).getWorld().getName());

                graczMeta.setLore(lore);

                graczMeta.setOwningPlayer(graczeOnline.get(i));
                gracz.setItemMeta(graczMeta);
                menu.setItem(test, gracz);

            }

            if(test >= 36) {
                break;
            }


            test = test + 1;

        }

        ItemStack close = new ItemStack(Material.BARRIER); ItemMeta closeMeta = close.getItemMeta(); closeMeta.setDisplayName("§4Zamknij"); close.setItemMeta(closeMeta);
        ItemStack prevpage = new ItemStack(Material.RED_WOOL); ItemMeta prevMeta = prevpage.getItemMeta(); prevMeta.setDisplayName("§cWczesniejsza strona"); prevpage.setItemMeta(prevMeta);
        ItemStack nextpage = new ItemStack(Material.LIME_WOOL); ItemMeta nextMeta = nextpage.getItemMeta(); nextMeta.setDisplayName("§aNastepna strona"); nextpage.setItemMeta(nextMeta);
        ItemStack actualpage = new ItemStack(Material.YELLOW_WOOL); ItemMeta actualMeta = prevpage.getItemMeta(); actualMeta.setDisplayName("§eAktualna strona §6§l" + page); actualpage.setItemMeta(actualMeta);
        ItemStack glasspage = new ItemStack(Material.BLACK_STAINED_GLASS_PANE); ItemMeta glassMeta = glasspage.getItemMeta(); glassMeta.setDisplayName("§8(╯°□°）╯︵ ┻━┻"); glasspage.setItemMeta(glassMeta);

        menu.setItem(36, glasspage);
        menu.setItem(37, glasspage);
        menu.setItem(38, glasspage);
        menu.setItem(39, glasspage);
        menu.setItem(40, glasspage);


        menu.setItem(42, prevpage);
        menu.setItem(43, nextpage);
        menu.setItem(44, close);
        menu.setItem(41, actualpage);


        p.openInventory(menu);
    }




}
