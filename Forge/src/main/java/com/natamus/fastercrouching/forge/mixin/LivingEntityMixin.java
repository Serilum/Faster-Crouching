package com.natamus.fastercrouching.forge.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 1001)
public class LivingEntityMixin {
	@Shadow
	private float speed;

	@Inject(method = "setSpeed", at = @At(value = "HEAD"), cancellable = true)
	public void setSpeed(float pSpeed, CallbackInfo ci) {
		LivingEntity livingEntity = (LivingEntity)(Object)this;
		if (livingEntity instanceof Player) {
			if (livingEntity.isCrouching()) {
				this.speed = pSpeed * 10.0F;
				ci.cancel();
			}
		}
	}
}
