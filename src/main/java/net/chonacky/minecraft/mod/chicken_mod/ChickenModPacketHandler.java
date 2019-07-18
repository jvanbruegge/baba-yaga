package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public final class ChickenModPacketHandler {
	
	private static int channelid = 0;
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel HANDLER = NetworkRegistry.newSimpleChannel(
		    new ResourceLocation(ChickenMod.MODID, "main"),
		    () -> PROTOCOL_VERSION,
		    PROTOCOL_VERSION::equals,
		    PROTOCOL_VERSION::equals
		);	

public static void register() {
	HANDLER.registerMessage(channelid++, LaserSpawnPacket.class, LaserSpawnPacket::encode, LaserSpawnPacket::decode, LaserSpawnPacket.Handler::handle);
}
	
	
	
}
