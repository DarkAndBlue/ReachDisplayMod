package de.darkandblue.reachdisplaymod.mixin;

import de.darkandblue.reachdisplaymod.ReachDisplayMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(InGameHud.class)
public class InGameHudMixin {
  @Inject(method = "render", at = @At("HEAD"))
  public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
    MinecraftClient minecraftInstance = MinecraftClient.getInstance();
    int scaledHeight = minecraftInstance.getWindow().getScaledHeight();
    
    minecraftInstance.textRenderer.draw(
      matrices,
      "Reach: " + ReachDisplayMod.reach,
      2, scaledHeight - 30, Color.white.getRGB()
    );
  }
}