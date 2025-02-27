package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.effect.ElsDyeModEffects;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.leafenzo.mint.util.ElsDyeModUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;
import org.apache.http.annotation.Experimental;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static net.leafenzo.mint.util.ElsDyeModUtil.toSentanceCase;

public class ElsDyeModEnglishLangProvider extends FabricLanguageProvider {
    Set<String> usedTranslationKeys = new HashSet<String>(); // For duplicate handling

    public ElsDyeModEnglishLangProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator, "en_us");
    }
    private void generateTranslation(TranslationBuilder translationBuilder, String key, String translation) {
        if(usedTranslationKeys.contains(key)) {
            //("Duplicate translation key " + key + "ignored"); //TODO, figure out how the heck do we print to debug from datagen?
            return;
        }
        translationBuilder.add(key, translation);
        usedTranslationKeys.add(key);
    }
    private void generateTranslation(TranslationBuilder translationBuilder, Block block, String translation) {
        generateTranslation(translationBuilder, block.getTranslationKey(), translation);
    }
    private void generateTranslation(TranslationBuilder translationBuilder, Item item, String translation) {
        generateTranslation(translationBuilder, item.getTranslationKey(), translation);
    }
    private void generatePotionTranslation(TranslationBuilder translationBuilder,  String subKey, String effectName) {
        String potionKey = "item.minecraft.potion.effect." + subKey;
        generateTranslation(translationBuilder, potionKey, "Potion of " + effectName);

        String splashPotionKey = "item.minecraft.splash_potion.effect." + subKey;
        generateTranslation(translationBuilder, splashPotionKey, "Splash Potion of " + effectName);

        String lingeringPotionKey = "item.minecraft.lingering_potion.effect." + subKey;
        generateTranslation(translationBuilder, lingeringPotionKey, "Lingering Potion of " + effectName);

        String arrowKey = "item.minecraft.tipped_arrow.effect." + subKey;
        generateTranslation(translationBuilder, arrowKey, "Arrow of " + effectName);
    }

    private void generateShieldVariantTranslations(TranslationBuilder translationBuilder) {
        for(DyeColor color : ElsDyeModDyeColor.VALUES) {
            String key = "item.minecraft.shield." + color.getName();
            generateTranslation(translationBuilder, key, ElsDyeModUtil.toSentanceCase(color.getName() + "_shield"));
        }
    }

    private void generateBannerPatternColorTranslations(TranslationBuilder translationBuilder) {
        for(DyeColor color : ElsDyeModDyeColor.VALUES) {
            for(Identifier id : Registries.BANNER_PATTERN.getIds()) {
                String key = "block.minecraft.banner." + id.getPath();
                String baseKey = "block.minecraft.banner." + id.getPath() + ".black";
                generateTranslation(translationBuilder, key + "." + color.getName(), ElsDyeModUtil.toSentanceCase(color.getName()) + " " + Language.getInstance().get(baseKey).replaceAll("Black ", ""));
//                generateTranslation(translationBuilder, key + "." + color.getName(), ModUtil.toSentanceCase(color.getName()) + " " + ModUtil.toSentanceCase(key.replaceAll("block\\.minecraft\\.banner\\.", "")));
            }
        }
    }

    private void generateWoodsetTranslations(TranslationBuilder translationBuilder) {
        for (WoodSet woodSet : ElsDyeModBlocks.WOODSETS) {
            String key = woodSet.getChestBoatEntityType().getTranslationKey();
            String baseKey = "entity.minecraft.chest_boat";
            generateTranslation(translationBuilder, key, Language.getInstance().get(baseKey));
//            generateTranslation(translationBuilder, key, ModUtil.toSentanceCase(key.replaceAll("entity\\." + Super.MOD_ID + "\\.", "")));
        }
    }

    // UNTESTED
    @Experimental
    private void generateColoredBlockAndItemTranslationsForMod(TranslationBuilder translationBuilder, String modId) {
        for (Identifier id : ElsDyeModUtil.allBlockIdsInNamespace(modId)) {
            for (DyeColor color : ElsDyeModDyeColor.VALUES) {
                if (id.getPath().contains(color.getName())) {
                    String key = "block." + modId + "." + id.getPath();
                    usedTranslationKeys.add(key);
                    translationBuilder.add(key, toSentanceCase(color.getName() + id.getPath().replace(color.getName(), ""))); //move the color name to the beginning
                }
            }
        }
    }

    /**
     * Must be run before our automatic translation building to avoid wall banner nonsense
     * @param translationBuilder
     * @param BannerBlocks
     */
    private void generateBannerTranslations(TranslationBuilder translationBuilder, ArrayList<Block> BannerBlocks) {
        for(Block block : BannerBlocks) {
            generateTranslation(translationBuilder, block.getTranslationKey(), toSentanceCase(Registries.BLOCK.getId(block).getPath()));
        }
    }
    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        //Manual
        generateTranslation(translationBuilder, ElsDyeModEffects.MINT_CHILL.getTranslationKey(), "Mint Chill");
        generatePotionTranslation(translationBuilder, "mint_chill", "Mint Chill");
        generatePotionTranslation(translationBuilder, "long_mint_chill", "Mint Chill");
        generatePotionTranslation(translationBuilder, "strong_mint_chill", "Mint Chill");

        generateTranslation(translationBuilder, ElsDyeModEffects.THORNS.getTranslationKey(), "Thorns");
        generatePotionTranslation(translationBuilder, "thorns", "Thorns");
        generatePotionTranslation(translationBuilder, "long_thorns", "Thorns");
        generatePotionTranslation(translationBuilder, "strong_thorns", "Thorns");

        generateTranslation(translationBuilder, ElsDyeModBlocks.MINT_SPRIG_BLOCK, "Pile of Mint");

        generateTranslation(translationBuilder, ElsDyeModBlocks.WAXCAP_GILLS, "Waxcap Gill Block");
        generateTranslation(translationBuilder, ElsDyeModBlocks.WAXCAP_GILL_SLAB, "Waxcap Gills");

        generateTranslation(translationBuilder, ElsDyeModBlocks.AMBER_BLOCK, "Block of Amber");
        generateTranslation(translationBuilder, ElsDyeModBlocks.CINNABAR_BLOCK, "Block of Cinnabar");

        generateTranslation(translationBuilder, "subtitles.entity.beetle.ambient", "Beetle Clicks");
        generateTranslation(translationBuilder, "subtitles.entity.beetle.death", "Beetle Dies");
        generateTranslation(translationBuilder, "subtitles.entity.beetle.hurt", "Beetle Hurts");

        generateWoodsetTranslations(translationBuilder);

        generateBannerTranslations(translationBuilder, ElsDyeModBlocks.BANNER_BLOCKS); // Necessary (albeit hacky) so we don't get Wall Banner nonsense
        generateBannerPatternColorTranslations(translationBuilder);
        generateShieldVariantTranslations(translationBuilder);

        //Automatic
        for(Identifier id : ElsDyeModUtil.allBlockIdsInNamespace(ElsDyeMod.MOD_ID)) {
            String key = Registries.BLOCK.get(id).getTranslationKey();
            if(usedTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
            usedTranslationKeys.add(key);
            translationBuilder.add(key, toSentanceCase(id.getPath()));
        }
        for(Identifier id : ElsDyeModUtil.allItemIdsInNamespace(ElsDyeMod.MOD_ID)) {
            String key = Registries.ITEM.get(id).getTranslationKey();
            if(usedTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
            usedTranslationKeys.add(key);
            translationBuilder.add(key, toSentanceCase(id.getPath()));
        }
        for(Identifier id : ElsDyeModUtil.allItemGroupIdsInNamespace(ElsDyeMod.MOD_ID)) {
            String key = Registries.ITEM_GROUP.get(id).getDisplayName().getString();
            if(usedTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
            usedTranslationKeys.add(key);
            translationBuilder.add(key, toSentanceCase(id.getPath()));
        }
        for(Identifier id : ElsDyeModUtil.allStatusEffectIdsInNamespace(ElsDyeMod.MOD_ID)) {
            String key = Registries.STATUS_EFFECT.get(id).getTranslationKey();
            if(usedTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
            usedTranslationKeys.add(key);
            translationBuilder.add(key, toSentanceCase(id.getPath()));
        }

        // This is a mess, just do this part manually for now.
        // Effects, Potions, and Tipped arrows
//        for(Identifier id : ModUtil.allPotionIdsInNamespace(Super.MOD_ID)) {
//            String key = Registries.POTION.get(id).finishTranslationKey("item.minecraft.potion.effect."); // what does this even do
//            if(usedTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
//            usedTranslationKeys.add(key);
//
//            String effectName = Registries.POTION.get(id).getEffects().get(0).getEffectType().getName().toString());
//
//            // Potion
//            translationBuilder.add(key, "Potion of " + effectName);
//
//            // Tipped Arrow
//            String arrowKey = "item.minecraft.tipped_arrow.effect.";
//            arrowKey = arrowKey.concat(Pattern.compile("(?<=potion\\.)[^.]+").matcher(key).toString());
//            translationBuilder.add(arrowKey, "Arrow of " + toSentanceCase(effectName));
//        }
    }
}