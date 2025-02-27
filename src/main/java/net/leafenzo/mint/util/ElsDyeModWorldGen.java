package net.leafenzo.mint.util;

import com.mojang.serialization.Codec;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.datageneration.ElsDyeModPlacedFeatures;
import net.leafenzo.mint.world.gen.HugeWaxcapMushroomDecorator;
import net.leafenzo.mint.world.gen.MushroomBlockDirectionDecorator;
import net.leafenzo.mint.world.gen.WintergreenFoliagePlacer;
import net.leafenzo.mint.world.gen.WintergreenTrunkPlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ElsDyeModWorldGen {
        //  public static final FoliagePlacerType<HugeWaxcapMushroomFoliagePlacer> HUGE_WAXCAP_MUSHROOM_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("huge_waxcap_mushroom_foliage_placer", HugeWaxcapMushroomFoliagePlacer.CODEC);
    public static final TreeDecoratorType<HugeWaxcapMushroomDecorator> HUGE_WAXCAP_MUSHROOM_DECORATOR = registerTreeDecorator("huge_waxcap_mushroom_decorator", HugeWaxcapMushroomDecorator.CODEC);
    public static final TreeDecoratorType<MushroomBlockDirectionDecorator> MUSHROOM_BLOCK_DIRECTION_DECORATOR = registerTreeDecorator("mushroom_block_direction_decorator", MushroomBlockDirectionDecorator.CODEC);
        //  public static final FoliagePlacerType<HugeWaxcapMushroomFoliagePlacer> HUGE_WAXCAP_MUSHROOM_FOLIAGE_PLACER = registerFoliagePlacer("huge_waxcap_mushroom_foliage_placer", HugeWaxcapMushroomFoliagePlacer.CODEC);
    public static final TrunkPlacerType<WintergreenTrunkPlacer> WINTERGREEN_TRUNK_PLACER = registerTrunkPlacer("wintergreen_trunk_placer", WintergreenTrunkPlacer.CODEC);
    public static final FoliagePlacerType<WintergreenFoliagePlacer> WINTERGREEN_FOLIAGE_PLACER = registerFoliagePlacer("wintergreen_foliage_placer", WintergreenFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacer(String id, Codec<P> codec) {
        return (FoliagePlacerType) Registry.register(Registries.FOLIAGE_PLACER_TYPE, new Identifier(ElsDyeMod.MOD_ID, id), new FoliagePlacerType(codec));
    }
    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String id, Codec<P> codec) {
        return (TrunkPlacerType)Registry.register(Registries.TRUNK_PLACER_TYPE,  new Identifier(ElsDyeMod.MOD_ID, id), new TrunkPlacerType(codec));
    }
    private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecorator(String id, Codec<P> codec) {
        return (TreeDecoratorType)Registry.register(Registries.TREE_DECORATOR_TYPE,  new Identifier(ElsDyeMod.MOD_ID, id), new TreeDecoratorType(codec));
    }

    public static void registerWorldGen() {
        ElsDyeModInit.LOGGER.debug("Registering mod world gen for " + ElsDyeMod.MOD_ID);
        ElsDyeModPlacedFeatures.registerModifications(); // feels a bit wrong calling this here. It should work fine it just feels smelly to me.
    }
}