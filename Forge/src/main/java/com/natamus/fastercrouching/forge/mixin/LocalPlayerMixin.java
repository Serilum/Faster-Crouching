package com.natamus.fastercrouching.forge.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = LocalPlayer.class, priority = 1001)
public class LocalPlayerMixin {
	@ModifyVariable(method = "aiStep", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/Mth;clamp(FFF)F"))
	private float LocalPlayer_f(float f) {
		return (0.3F + EnchantmentHelper.getSneakingSpeedBonus((LocalPlayer)(Object)this)) * 10.0F;
	}
}
