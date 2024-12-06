package com.natamus.fastercrouching.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

	@Inject(method = "getAttributeValue(Lnet/minecraft/core/Holder;)D", at = @At(value = "HEAD"), cancellable = true)
	public void getAttributeValue(Holder<Attribute> attributeHolder, CallbackInfoReturnable<Double> cir) {
		if (attributeHolder.equals(Attributes.SNEAKING_SPEED)) {
			cir.setReturnValue((double) ((0.3F + ((LivingEntity)(Object)this).getAttributes().getValue(Attributes.SNEAKING_SPEED)) * 10.0F));
		}
	}
}
