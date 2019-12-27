package red.mohsit.mohistmodsessentials.pixelmon.nyphysical;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import red.mohsit.mohistmodsessentials.Main;

/**
 * @author Mgazul
 * @date 2019/12/28 3:54
 */
public class NyPhysicalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                Inventory inv = Bukkit.createInventory(null, 45, Main.plugin.getConfig().getString(NyPhysical.key + "inventory.title").replace("&", "§"));
                int[] slots = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
                ItemStack fg = new ItemStack(Material.STAINED_GLASS_PANE, 1);
                ItemMeta fgim = fg.getItemMeta();
                fgim.setDisplayName("§f");
                fg.setItemMeta(fgim);
                for (int i : slots) {
                    inv.setItem(i, fg);
                }
                ItemStack tl = new ItemStack(Material.DRAGONS_BREATH);
                ItemMeta im = tl.getItemMeta();
                List<String> lores = new ArrayList<>();
                for (String j : Main.plugin.getConfig().getStringList(NyPhysical.key + "lore")) {
                    lores.add(j.replace("&", "§"));
                }
                im.setLore(lores);
                im.setDisplayName(Main.plugin.getConfig().getString(NyPhysical.key + "inventory.item-name").replace("&", "§"));
                tl.setItemMeta(im);
                inv.setItem(22, tl);
                ((Player)sender).openInventory(inv);
            }
            else if (args[0].equalsIgnoreCase("add") && sender.hasPermission("mme.nyphysical.admin")) {
                if (args.length <= 2) {
                    sender.sendMessage(NyPhysical.prefix + "§c请输入玩家名及数量");
                    return false;
                }
                Player player = Bukkit.getPlayer(args[1]);
                if (player != null && player.isOnline() && NyPhysical.pds.containsKey(args[1])) {
                    NyPhysical.pds.get(args[1]).addNP(Integer.parseInt(args[2]));
                }
            }
        }
        return false;
    }
}
