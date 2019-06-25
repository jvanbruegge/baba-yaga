package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.util.math.Vec3d;

public class MCMathHelper {

	public static final float radFactor = (float) (2*Math.PI/360);

public static Vec3d moveVector3xz(Vec3d origin, float heading, double distance) {
	origin.add(-Math.sin(heading)*distance,0.0D,Math.cos(heading)*distance);
	return origin;
	
}

}
