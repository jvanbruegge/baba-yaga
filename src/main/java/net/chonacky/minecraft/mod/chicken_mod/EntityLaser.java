package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityLaser extends AbstractArrowEntity {

	
	public EntityLaser(EntityType<? extends AbstractArrowEntity> type, World p_i48546_2_) {
		super(type, p_i48546_2_);
	}

//	public EntityLaser(EntityType<? extends AbstractArrowEntity> type, World p_i48546_2_) {
//		super(type, p_i48546_2_);	
//	}

//	public EntityLaser(EntityType<? extends AbstractArrowEntity> p_i48548_1_, LivingEntity p_i48548_2_,
//			World p_i48548_3_) {
//		super(p_i48548_1_, p_i48548_2_, p_i48548_3_);
//	}

//	public EntityLaser(EntityType<? extends AbstractArrowEntity> p_i48547_1_, double p_i48547_2_, double p_i48547_4_,
//			double p_i48547_6_, World p_i48547_8_) {
//		super(p_i48547_1_, p_i48547_2_, p_i48547_4_, p_i48547_6_, p_i48547_8_);
//	}

	@Override
	protected ItemStack getArrowStack() {
		// TODO Auto-generated method stub
		return null;
	}


}
