/**
 * 
 */
package net.chonacky.minecraft.mod.chicken_mod;

import java.util.UUID;

import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.ICustomPacket;

/**
 * @author philip
 *
 */
public class LaserSpawnPacket  {
	
	private double xPos,yPos,zPos;
	private Vec3d velocity;
	private int pitch,yaw, shooterId, entityId, type;
	private UUID uuid;
	
	
	public LaserSpawnPacket ( double xPos, double yPos, double zPos, int pitch, int yaw, Vec3d velocity, int shooterId, int entityId, UUID uuid, int type) {
		this.xPos=xPos;
		this.yPos=yPos;
		this.zPos=zPos;
		this.pitch=pitch;
		this.yaw=yaw;
		this.velocity=velocity;
		this.shooterId=shooterId;
		this.entityId=entityId;
		this.uuid=uuid;
		this.type= type;	
	}
	
	
	public LaserSpawnPacket (EntityLaser entity, int shooterId) {
			this(entity.posX,entity.posY,entity.posZ,
					(int)entity.getPitchYaw().x,    (int)entity.getPitchYaw().y,
					entity.getMotion(),
					shooterId,
					entity.getEntityId(),entity.getUniqueID(),entity.getType().hashCode());
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
		buf.writeInt(msg.type);
	}

	public static LaserSpawnPacket decode(PacketBuffer buf) {
		return new LaserSpawnPacket(buf.readDouble(),buf.readDouble(),buf.readDouble(),
				buf.readInt(),buf.readInt(),new Vec3d (buf.readInt(),buf.readInt(),buf.readInt()),
				buf.readInt(),buf.readInt(),buf.readUniqueId(),buf.readInt());
	}
	
	
	
	
}
