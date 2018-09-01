package customlobby.essential;


import net.minecraft.server.v1_8_R3.EntityEnderDragon;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Location;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class BossBar {

    public static void newBar(Player player, String name){
        Location loc = player.getLocation();
        WorldServer world = ((CraftWorld) player.getLocation().getWorld()).getHandle();

        EntityEnderDragon dragon = new EntityEnderDragon(world);
        dragon.setLocation(loc.getX() - 30, loc.getY() - 100, loc.getZ(), 0, 0);
        dragon.setCustomName(name);
        dragon.setInvisible(true);

        Packet packet = new PacketPlayOutSpawnEntityLiving(dragon);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }



}
