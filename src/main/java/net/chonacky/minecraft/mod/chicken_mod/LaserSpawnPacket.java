/**
 * 
 */
package net.chonacky.minecraft.mod.chicken_mod;

import java.util.UUID;

import com.google.common.base.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author philip
 *
 */
public class LaserSpawnPacket {
	
	private double xPos,yPos,zPos;
	private Vec3d velocity;
	private int pitch,yaw, shooterId, entityId;
	private UUID uuid;
	
	
	public LaserSpawnPacket ( double xPos, double yPos, double zPos, int pitch, int yaw,
			Vec3d velocity, int shooterId, int entityId, UUID uuid) {
		this.xPos=xPos;
		this.yPos=yPos;
		this.zPos=zPos;
		this.pitch=pitch;
		this.yaw=yaw;
		this.velocity=velocity;
		this.shooterId=shooterId;
		this.entityId=entityId;
		this.uuid=uuid;	
	}
	
	
	public LaserSpawnPacket (EntityLaser entity, Entity shooter) {
			this(
					entity.posX,entity.posY,entity.posZ,
					(int)entity.getPitchYaw().x, (int)entity.getPitchYaw().y, entity.getMotion(),
					shooter.getEntityId(), entity.getEntityId(),entity.getUniqueID()
					);
		}
	


	public static void encode(LaserSpawnPacket msg, PacketBuffer buf) {
		buf.writeDouble(msg.xPos);
		buf.writeDouble(msg.yPos);
		buf.writeDouble(msg.zPos);
		buf.writeInt(msg.pitch);
		buf.writeInt(msg.yaw);
		buf.writeInt((int) msg.velocity.x);
		buf.writeInt((int) msg.velocity.y);
		buf.writeInt((int) msg.velocity.z);
		buf.writeInt(msg.shooterId);
		buf.writeInt(msg.entityId);
		buf.writeUniqueId(msg.uuid);
	}

	public static LaserSpawnPacket decode(PacketBuffer buf) {
		return new LaserSpawnPacket(buf.readDouble(),buf.readDouble(),buf.readDouble(),
				buf.readInt(),buf.readInt(),new Vec3d (buf.readInt(),buf.readInt(),buf.readInt()),
				buf.readInt(),buf.readInt(),buf.readUniqueId());
	}
	
	
	public static void handle(LaserSpawnPacket msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if (msg.getClass() == LaserSpawnPacket.class) {
				World world = Minecraft.getInstance().world;				

					AbstractArrowEntity entity = new EntityLaser(world,msg.xPos,msg.yPos,msg.zPos);
					Entity shooter = world.getEntityByID(msg.shooterId);
					if (shooter != null) {
						((AbstractArrowEntity)entity).setShooter(shooter);
					}
				
				ctx.get().setPacketHandled(true);
			}
		});
	}
	
}