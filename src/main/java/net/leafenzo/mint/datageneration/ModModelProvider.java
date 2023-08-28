package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.stream.IntStream;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void registerUpDefaultOrientable(BlockStateModelGenerator blockStateModelGenerator, Block block, TexturedModel.Factory modelFactory) {
        Identifier identifier = modelFactory.upload(block, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).coordinate(this.createUpDefaultRotationStates()));
        //blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateModelGenerator.createNorthDefaultRotationStates(), identifier));
    }
    private static BlockStateVariantMap createUpDefaultRotationStates() {
        return BlockStateVariantMap.create(Properties.FACING).register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180)).register(Direction.UP, BlockStateVariant.create()).register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90)).register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180)).register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270)).register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90));
    }
    private void registerWithModelId(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier ModelId) {
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, ModelId));
    }

//    public final void registerBed(BlockStateModelGenerator blockStateModelGenerator, Block bed, Block particleSource) {
//        blockStateModelGenerator.blockStateCollector.accept(Models.TEMPLATE_BED.upload(ModelIds.getItemModelId(bed.asItem()), TextureMap.particle(particleSource), blockStateModelGenerator.modelCollector));
//    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // MINT - Special
        blockStateModelGenerator.registerCrop(ModBlocks.MINT_CROP, MintCropBlock.AGE, IntStream.rangeClosed(0, MintCropBlock.MAX_AGE).toArray());
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_SPRIG_BLOCK);

        BlockStateModelGenerator.BlockTexturePool mintBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_BRICKS);
        mintBricksTexturePool.slab(ModBlocks.MINT_BRICK_SLAB);
        mintBricksTexturePool.stairs(ModBlocks.MINT_BRICK_STAIRS);
        //mintBricksTexturePool.wall(ModBlocks.MINT_BRICK_WALL);

        // PEACH - Special
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PEACH_LOG);


        //Main
//  FLOWER BLOCKS    //  FLOWER_POT_BLOCKS
        for(Block block : ModBlocks.SMALL_FLOWERS) {
            if(ModBlocks.FLOWER_POT_FROM_FLOWER.get(block) != null) {
                blockStateModelGenerator.registerFlowerPotPlant(block, ModBlocks.FLOWER_POT_FROM_FLOWER.get(block), BlockStateModelGenerator.TintType.NOT_TINTED);
            }
            else {
                blockStateModelGenerator.registerTintableCross(block, BlockStateModelGenerator.TintType.NOT_TINTED);
            }
        }

//  WOOL_BLOCKS     //  CARPET_BLOCKS
        for(Block wool : ModBlocks.WOOL_BLOCKS) {
            Block carpet = ModBlocks.WOOL_CARPET_FROM_WOOL.get(wool);
            if(carpet != null) { blockStateModelGenerator.registerWoolAndCarpet(wool, carpet);  }
            else { blockStateModelGenerator.registerCubeAllModelTexturePool(wool); }
        }

//  TERRACOTTA_BLOCKS
        for (Block block : ModBlocks.TERRACOTTA_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }

//  CONCRETE_BLOCKS
        for (Block block : ModBlocks.CONCRETE_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }

//  CONCRETE_POWDER_BLOCKS
        for (Block block : ModBlocks.CONCRETE_POWDER_BLOCKS) {
            blockStateModelGenerator.registerRandomHorizontalRotations(TexturedModel.CUBE_ALL, block);
        }

//  GLAZED_TERRACOTTA_BLOCKS
        for (Block block : ModBlocks.GLAZED_TERRACOTTA_BLOCKS) {
            blockStateModelGenerator.registerSouthDefaultHorizontalFacing(TexturedModel.TEMPLATE_GLAZED_TERRACOTTA, block);
            //registerUpDefaultOrientable(blockStateModelGenerator, block, TexturedModel.CUBE_ALL);
        }

//  STAINED_GLASS_BLOCKS  //  STAINED_GLASS_PANE_BLOCKS
        for(Block glass : ModBlocks.STAINED_GLASS_BLOCKS) {
            Block pane = ModBlocks.STAINED_GLASS_PANE_FROM_STAINED_GLASS.get(glass);
            if(pane != null) { blockStateModelGenerator.registerGlassPane(glass, pane);  }
            else { blockStateModelGenerator.registerCubeAllModelTexturePool(glass); }
        }

//  SHULKER_BOX_BLOCKS
        for (Block block : ModBlocks.SHULKER_BOX_BLOCKS) {
            blockStateModelGenerator.registerShulkerBox(block);
        }

//  BED_BLOCKS
        for (Block block : ModBlocks.BED_BLOCKS) {
            blockStateModelGenerator.registerBuiltin(Registries.BLOCK.getId(block), Blocks.OAK_PLANKS)
                    .includeWithItem(Models.TEMPLATE_BED, block);
        }

//  CANDLE_BLOCKS     //  CANDLE_CAKE_BLOCKS
        for(Block candle : ModBlocks.CANDLE_BLOCKS) {
            Block cake = ModBlocks.CANDLE_CAKE_FROM_CANDLE.get(candle);
            if(cake != null) { blockStateModelGenerator.registerCandle(candle, cake); }
            else { throw new RuntimeException(Registries.BLOCK.getId(candle).toString() + "does not have a candle cake"); }
        }

//  BANNER_BLOCKS     // WALL_BANNER_BLOCKS
        for(Block banner : ModBlocks.BANNER_BLOCKS) {
            Block wallBanner = ModBlocks.WALL_BANNER_FROM_BANNER.get(banner);
            if(wallBanner != null) {
                blockStateModelGenerator.registerBuiltin(Registries.BLOCK.getId(banner), Blocks.OAK_PLANKS)
                        .includeWithItem(banner)
                        .includeWithoutItem(wallBanner);
            }
            else { throw new RuntimeException(Registries.BLOCK.getId(banner).toString() + "does not have a wall banner"); }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(ModItems.MINT_SPRIG, Models.GENERATED); // this is a duplicate... somehow???????
        itemModelGenerator.register(ModItems.MINT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_TEA, Models.GENERATED);

        itemModelGenerator.register(ModItems.PEACH_PIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_COBBLER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRUIT_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBROSIA, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_ANEMONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORAL_ANEMONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_BRANCH, Models.GENERATED);

//  DYES
        for(Item item : ModItems.DYE_ITEMS) {
            itemModelGenerator.register(item, Models.GENERATED);
        }

//  BANNERS
        for(Block block : ModBlocks.BANNER_BLOCKS) {
            itemModelGenerator.register(block.asItem(), Models.TEMPLATE_BANNER);
        }

        //itemModelGenerator.register(ModItems.MINT_BED, Models.TEMPLATE_BED);
    }
}