package net.leafenzo.mint.mixin.compat.botania;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.leafenzo.mint.util.ElsDyeModUtil;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.botania.common.block.flower.generating.SpectrolusBlockEntity;

@Pseudo
@Mixin(SpectrolusBlockEntity.class)
public class SpectrolusBlockEntityMixin {

    @ModifyExpressionValue(
            method = "*",
            at = @At(value = "INVOKE", target = "net/minecraft/util/DyeColor.values ()[Lnet/minecraft/util/DyeColor;")
    )
    private DyeColor[] truncateDyeColors(DyeColor[] original) {
        // Use only vanilla dye colors, assuming that it expects nothing else to be there
        return ElsDyeModUtil.VANILLA_DYE_COLORS;
    }
}
