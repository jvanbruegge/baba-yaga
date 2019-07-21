/**
 * 
 */
package net.chonacky.minecraft.mod.chicken_mod;

import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author philip
 *
 */
public class LaserSpawnPacket {
	
	private final double xPos,yPos,zPos;
	private final Vec3d velocity;
	private final int pitch,yaw, shooterId, entityId;
	private final UUID uuid;
	
	
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
	
	public static class Handler {
		public static void handle(final LaserSpawnPacket msg, Supplier<NetworkEvent.Context> ctx) {
			DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
				ctx.get().enqueueWork(() -> {
					if (msg.getClass() == LaserSpawnPacket.class) {
						ClientWork.FireLaser(msg);
						ctx.get().setPacketHandled(true);
					}
				});
			});
			
		}
	}

	/**
	 * @return the xPos
	 */
	public double getxPos() {
		return xPos;
	}


	/**
	 * @return the yPos
	 */
	public double getyPos() {
		return yPos;
	}


	/**
	 * @return the zPos
	 */
	public double getzPos() {
		return zPos;
	}


	/**
	 * @return the velocity
	 */
	public Vec3d getVelocity() {
		return velocity;
	}


	/**
	 * @return the pitch
	 */
	public int getPitch() {
		return pitch;
	}


	/**
	 * @return the yaw
	 */
	public int getYaw() {
		return yaw;
	}


	/**
	 * @return the shooterId
	 */
	public int getShooterId() {
		return shooterId;
	}


	/**
	 * @return the entityId
	 */
	public int getEntityId() {
		return entityId;
	}


	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	
}
