package com.example.examplemod;

import com.example.examplemod.mc_02_myblock.MyBlock;
import com.example.examplemod.mc_03_myitem.ItemOnigiri;
import com.example.examplemod.mc_03_myitem.MyItem;
import com.example.examplemod.mc_04_rainbowblock.BlockRainbow;
import com.example.examplemod.mc_05_soundblock.BlockSound;
import com.example.examplemod.mc_06_woodcut.BlockBreakEventHandler;
import com.example.examplemod.mc_07_redstone.BlockRedstoneClock;
import com.example.examplemod.mc_07_redstone.BlockRedstoneInput;
import com.example.examplemod.mc_08_snowball_fight.EntityMySnowball;
import com.example.examplemod.mc_08_snowball_fight.ItemMySnowball;
import com.example.examplemod.mc_09_footprints_sand.BlockFootprintsSand;
import com.example.examplemod.mc_10_biome.BiomeIceberg;
import com.example.examplemod.mc_10_biome.BiomeMyBiome;
import com.example.examplemod.mc_10_biome.MyWorldGenerator;
import com.example.examplemod.mc_11_explosive_arrow.EntityExplosiveArrow;
import com.example.examplemod.mc_11_explosive_arrow.ItemExplosiveArrow;
import com.example.examplemod.mc_11_explosive_arrow.RenderExplosiveArrow;
import com.example.examplemod.mc_12_bull_fighting.EntityBull;
import com.example.examplemod.mc_12_bull_fighting.RenderBull;
import com.example.examplemod.mc_13_tobisuke.EntityTobisuke;
import com.example.examplemod.mc_13_tobisuke.RenderTobisuke;
import com.example.examplemod.mc_launching.BlockLaunchingInput;
import com.example.examplemod.mc_missile.EntityMissile;
import com.example.examplemod.mc_missile.ItemMissile;
import com.example.examplemod.mc_missile.RenderMissile;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";

    //MC-02 : Myblock
    public static Block blockMyBlock = new MyBlock();
    //MC-04 : rainbow
    public static Block blockRainbow = new BlockRainbow();


    public static Block blockSound = new BlockSound();
    public static SoundEvent[] soundEvents = {
            new SoundEvent(new ResourceLocation(MODID, "sound1")),
            new SoundEvent(new ResourceLocation(MODID, "sound2")),
            new SoundEvent(new ResourceLocation(MODID, "sound3"))
};


    //MC-03 : MyItem
    public static Item itemMySword = new MyItem();
    public static Item itemOnigiri = new ItemOnigiri();

    //MC-07 :Redstone
    public static Block blockRedstoneInput = new BlockRedstoneInput();
    public static Block blockRedstoneClock = new BlockRedstoneClock();

    public static Item itemMySnowball = new ItemMySnowball();

    public static Block blockFootprintsSand = new BlockFootprintsSand();

    public static BiomeManager.BiomeEntry myBiomeEntry = new BiomeManager.BiomeEntry(new BiomeMyBiome(), 30);

    public static BiomeManager.BiomeEntry icebergBiomeEntry = new BiomeManager.BiomeEntry(new BiomeIceberg(), 30);

    public static Item itemExplosiveArrow = new ItemExplosiveArrow();

    //original
    public static Item itemMissile = new ItemMissile();

    public static Block blockLaunchingInput = new BlockLaunchingInput();
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        boolean isClient = event.getSide().isClient();
        registerBlock(blockMyBlock, isClient);
        registerBlock(blockRainbow, isClient);

        registerMyItem(isClient);
        registerSoundBlock(isClient);
        registerRedstone(isClient);

        registerSnowballFight(isClient);
        registerSnowballFightRenderer();

        registerFootprintsSand(isClient);

        registerBiome();

        registerExplosiveArrow(isClient);

        registerExplosiveArrowRenderer();

        registerBull();

        registerBullRenderer();

//        registerTobisuke();

        //original
        registerMissile(isClient);

        registerLaunching(isClient);

//        registerMissile();

//        registerLaunching();
    }

    //MC-06 : woodcut
    private void registerWoodCut(){
        MinecraftForge.EVENT_BUS.register(new BlockBreakEventHandler());
    }
        @EventHandler
        public void init(FMLInitializationEvent event){
            registerWoodCut();
        }

    public void registerBlock (Block block, boolean isClient) {
        ItemBlock itemBlockInput = new ItemBlock(block);

        GameRegistry.register(block);
        GameRegistry.register(itemBlockInput, block.getRegistryName());

        if (isClient){
            ModelResourceLocation modelName = new ModelResourceLocation(block.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemBlockInput, 0, modelName);
        }

    }

    public void registerMyItem(boolean isClient) {
        GameRegistry.register(itemMySword);

        if (isClient) {
            ModelResourceLocation modelName = new ModelResourceLocation(itemMySword.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemMySword, 0, modelName);
        }
        GameRegistry.register(itemOnigiri);

        if (isClient) {
            ModelResourceLocation modelName = new ModelResourceLocation(itemOnigiri.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemOnigiri, 0, modelName);
        }
    }

    public void registerRecipe() {
        GameRegistry.addRecipe(new ItemStack(Blocks.DIAMOND_BLOCK),
                 "AAA",
                "AAA",
                "AAA",
                'A', new ItemStack(Blocks.DIRT));

        NBTTagCompound creeperId = new NBTTagCompound();
        creeperId.setString("id", "Creeper");
        ItemStack creeperSpawnEgg = new ItemStack(Items.SPAWN_EGG);
        creeperSpawnEgg.setTagInfo("EntityTag", creeperId );
        GameRegistry.addRecipe(creeperSpawnEgg,
                " A ", "CBC", "CBC",
                'A', new ItemStack(Items.SKULL, 1,4),
                'B', new ItemStack(Blocks.TNT),
                'C', new ItemStack(Items.GUNPOWDER));

    }

    private void registerSoundBlock(boolean isClient){
        ItemBlock itemBlock = new ItemBlock(blockSound);

        GameRegistry.register(blockSound);
        GameRegistry.register(itemBlock, blockSound.getRegistryName());
        for (int i = 0; i < soundEvents.length; i++) {
            GameRegistry.register(soundEvents[i], soundEvents[i].getSoundName());
        }

        if (isClient){
            ModelResourceLocation modelName = new ModelResourceLocation(blockSound.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemBlock, 0, modelName);
        }
    }
    private void registerRedstone(boolean isClient){
        ItemBlock itemBlockInput = new ItemBlock(blockRedstoneInput);

        GameRegistry.register(blockRedstoneInput);
        GameRegistry.register(itemBlockInput, blockRedstoneInput.getRegistryName());

        if(isClient){
            ModelResourceLocation modelName = new ModelResourceLocation(blockRedstoneInput.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemBlockInput, 0, modelName);
        }

        ItemBlock itemBlockClock = new ItemBlock(blockRedstoneClock);

        GameRegistry.register(blockRedstoneClock);
        GameRegistry.register(itemBlockClock, blockRedstoneClock.getRegistryName());

        if(isClient){
            ModelResourceLocation modelName = new ModelResourceLocation(blockRedstoneClock.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemBlockClock, 0, modelName);
        }
    }

    private void registerSnowballFight(boolean isClient){
        GameRegistry.register(itemMySnowball);

        if(isClient){
            ModelResourceLocation modelName = new ModelResourceLocation(Items.SNOWBALL.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemMySnowball, 0, modelName);
        }
        EntityRegistry.registerModEntity(EntityMySnowball.class, "my_snowball", EntityMySnowball.Entity_ID, this, 10, 10, true);
    }

    private void registerSnowballFightRenderer(){
        RenderingRegistry.registerEntityRenderingHandler(EntityMySnowball.class, new IRenderFactory<EntityMySnowball>() {
            @Override
            public Render<? super EntityMySnowball> createRenderFor(RenderManager manager) {
                return new RenderSnowball<EntityMySnowball>(manager, Items.SNOWBALL, Minecraft.getMinecraft().getRenderItem());
            }
        });
    }

    private void registerFootprintsSand(boolean isClient){
        ItemBlock itemBlock = new ItemBlock(blockFootprintsSand);

        GameRegistry.register(blockFootprintsSand);
        GameRegistry.register(itemBlock, blockFootprintsSand.getRegistryName());

        if(isClient){
            ModelResourceLocation modelName = new ModelResourceLocation(blockFootprintsSand.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemBlock,0, modelName);
        }

    }

    private void registerBiome(){
        GameRegistry.registerWorldGenerator(new MyWorldGenerator(blockMyBlock, 1000), 1);

        BiomeManager.oceanBiomes.clear();
        BiomeProvider.allowedBiomes.clear();

        Biome.registerBiome(40, "mybiome", myBiomeEntry.biome);
        BiomeManager.addSpawnBiome(myBiomeEntry.biome);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, myBiomeEntry);
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, myBiomeEntry);
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, myBiomeEntry);
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, myBiomeEntry);

        Biome.registerBiome(41, "iceberg", icebergBiomeEntry.biome);
        BiomeManager.addSpawnBiome(icebergBiomeEntry.biome);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, icebergBiomeEntry);
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, icebergBiomeEntry);
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, icebergBiomeEntry);
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, icebergBiomeEntry);
    }

    private void registerExplosiveArrow(boolean isClient){
        GameRegistry.register(itemExplosiveArrow);
        EntityRegistry.registerModEntity(EntityExplosiveArrow.class,
                "explosive_arrow", EntityExplosiveArrow.ENTITY_ID, this, 10, 10, true);

        if(isClient){
            ModelResourceLocation modelName =
                    new ModelResourceLocation(itemExplosiveArrow.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemExplosiveArrow, 0, modelName);
        }
    }

    private void registerExplosiveArrowRenderer(){

        RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveArrow.class,
                new IRenderFactory<EntityExplosiveArrow>() {
                    @Override
                    public Render<? super EntityExplosiveArrow> createRenderFor(RenderManager manager) {
                        return new RenderExplosiveArrow(manager);
                    }
                });
    }

    private void registerBull(){
        EntityRegistry.registerModEntity(EntityBull.class, "bull", EntityBull.ENTITY_ID, this, 10, 10, true, 0xFFFF00, 0xFF0000);
    }

    private void registerBullRenderer(){
        RenderingRegistry.registerEntityRenderingHandler(EntityBull.class, RenderBull::new);
    }

//    private void registerTobisuke() {
//        EntityRegistry.registerModEntity(
//                EntityTobisuke.class, "tobisuke",
//                EntityTobisuke.EntityID,
//                this, 10, 1, true,
//                0xFF0000, 0x00FF00);
//        EntityRegistry.addSpawn(
//                EntityTobisuke.class,
//                20, 15, 30,
//                EnumCreatureType.CREATURE,
//                Biomes.PLAINS);
//        RenderingRegistry.registerEntityRenderingHandler(EntityTobisuke.class, RenderTobisuke::new);
//    }

    private void registerMissile(boolean isClient) {
        GameRegistry.register(itemMissile);

        if (isClient) {
            ModelResourceLocation modelName = new ModelResourceLocation(itemMissile.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemMissile, 0, modelName);
        }
        EntityRegistry.registerModEntity(EntityMissile.class, "missile", EntityMissile.ENTITY_ID, this, 100, 1, true);
        RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, RenderMissile::new);
    }

    private void registerLaunching(boolean isClient){
        ItemBlock itemBlockInput = new ItemBlock(blockLaunchingInput);

        GameRegistry.register(blockLaunchingInput);
        GameRegistry.register(itemBlockInput, blockLaunchingInput.getRegistryName());

        if(isClient){
            ModelResourceLocation modelName = new ModelResourceLocation(blockLaunchingInput.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(itemBlockInput, 0, modelName);
        }
    }


}

