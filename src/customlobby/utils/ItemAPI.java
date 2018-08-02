package customlobby.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemAPI {

    public static ItemStack createItem(Material material, String displayname, byte bite, Integer amount) {
        ItemStack item = new ItemStack(material,  amount, bite);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createSkull(String skullOwner, String displayname, Integer amount) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setOwner(skullOwner);
        return skull;
    }

}
