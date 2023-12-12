package com.natamus.fastercrouching.forge.mixin;

import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = KeyboardInput.class, priority = 1001)
public class KeyboardInputMixin extends Input {
    @Inject(method = "tick(Z)V", at = @At(value = "TAIL"))
    public void tick(boolean pIsMovingSlowly, CallbackInfo ci) {
        if (pIsMovingSlowly) {
            this.leftImpulse *= 10.0F;
            this.forwardImpulse *= 10.0F;
        }
    }
}
