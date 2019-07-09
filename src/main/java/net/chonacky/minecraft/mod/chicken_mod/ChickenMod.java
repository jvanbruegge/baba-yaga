package net.chonacky.minecraft.mod.chicken_mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
/**
 * 
 * @author philip
 *
 */
@Mod(value = ChickenMod.MODID)
public class ChickenMod 
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "chicken_mod";
	public static final ItemGroup ITEMTAB = ItemGroup.MISC;

    public ChickenMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
	
    private void setup(final FMLCommonSetupEvent event)
    {	//  preinit code  
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    	RenderingRegistry.registerEntityRenderingHandler(EntityProtoChicken.class,RenderProtoChicken :: new);
    	RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class,RenderLaser :: new);
    }
    
    private void enqueueIMC(final InterModEnqueueEvent event)
    {	// dispatch IMC to another mod 
    }

    private void processIMC(final InterModProcessEvent event)
    { // receive and process InterModComms from other mod
    }
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }

    //Mod Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(modid=ChickenMod.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
    @ObjectHolder(ChickenMod.MODID)
    public static class RegistryEvents {
    	
    	//Object Holders
    	public static final Block test_block=null;
    	public static final Item test_item=null;
		public static final Item uncharged_laser_cannon=null;
		public static final Item laser_cannon=null;
    	public static final EntityType<EntityProtoChicken> protochicken=null;
    	public static final EntityType<EntityLaser> cannon_laser=null;

				
    	//Register Blocks
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
    			blockRegistryEvent.getRegistry().registerAll(
    					new Block(Block.Properties.create(Material.IRON)).setRegistryName(MODID,"test_block")); 
        }
        
        
        //Register Items and BlockItems
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        	itemRegistryEvent.getRegistry().registerAll(
        			(Item)new BlockItem(test_block,new Item.Properties().group(ITEMTAB))
        				.setRegistryName(test_block.getRegistryName()),
        			new Item(new Item.Properties().group(ITEMTAB))
        				.setRegistryName(MODID,"test_item"),
        			new Item (new Item.Properties().group(ITEMTAB))
        				.setRegistryName(MODID,"uncharged_laser_cannon"),
        			new ItemLaserCannon(new Item.Properties().group(ITEMTAB).maxDamage(ItemLaserCannon.maxDamage))
        				.setRegistryName(MODID, "laser_cannon")
        			);
        }
        
    	//Register Entities
    	@SubscribeEvent
    	public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> entityRegistryEvent) {	
    		entityRegistryEvent.getRegistry().registerAll(		    	
    		    EntityType.Builder.create(EntityProtoChicken::new,EntityClassification.MISC).size(2.5F, 5.0F)
    		    	.setShouldReceiveVelocityUpdates(true)
    		    	.setTrackingRange(24)
    		    	.setUpdateInterval(60)
    		    	.build("protochicken")
    		    	.setRegistryName(MODID, "protochicken"),
		    	EntityType.Builder.<EntityLaser>create(EntityLaser::new,EntityClassification.MISC).size(0.5F,1)
		    		.setShouldReceiveVelocityUpdates(true)
		    		.setTrackingRange(24)
		    		.setUpdateInterval(60)
		    		.build("cannon_laser")
		    		.setRegistryName(MODID,"cannon_laser")
    			);	
    	}
        
    }  //RegistryEvents class
    
    

}
    
    