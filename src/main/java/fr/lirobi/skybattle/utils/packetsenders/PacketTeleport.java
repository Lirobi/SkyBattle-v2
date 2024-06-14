package fr.lirobi.skybattle.utils.packetsenders;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;


public class PacketTeleport {

    public void teleport(Player player, Location location) {
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(
                ((CraftPlayer) player).getHandle().getId(),
                (int) (location.getX() * 32),
                (int) (location.getY() * 32),
                (int) (location.getZ() * 32),
                (byte) ((int) (location.getYaw() * 256 / 360)),
                (byte) ((int) (location.getPitch() * 256 / 360)),
                true
        );
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
