package com.natamus.fastercrouching.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = LocalPlayer.class, priority = 1001)
public class LocalPlayerMixin {
	@ModifyVariable(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isMovingSlowly()Z"))
	private float LocalPlayer_f(float f) {
		return (float) ((0.3F + ((LocalPlayer)(Object)this).getAttributeValue(Attributes.SNEAKING_SPEED)) * 10.0F);
	}
}
