package de.darkandblue.reachdisplaymod.mixin;

import de.darkandblue.reachdisplaymod.ReachDisplayMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
  
  @Inject(method = "attackEntity", at = @At("HEAD"))
  public void attackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
    MinecraftClient minecraftInstance = MinecraftClient.getInstance();
    HitResult crosshairTarget = minecraftInstance.gameRenderer.getClient().crosshairTarget;

    if (crosshairTarget != null) {
      Vec3d hitVector = crosshairTarget.getPos();
      Vec3d eyeVector = player.getEyePos();
      
      ReachDisplayMod.reach = eyeVector.distanceTo(hitVector);
    }
  }
}