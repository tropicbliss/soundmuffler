// Adapted from https://github.com/MorningSage/ExtremeSoundMuffler-Fabric/blob/master/src/main/java/morningsage/extremesoundmuffler/mixin/SoundSystemMixin.java by tropicbliss on 22/12/2021

package com.tropicbliss.soundmuffler.mixin;

import com.tropicbliss.soundmuffler.events.SoundPlayingEvents;
import com.tropicbliss.soundmuffler.events.SoundPlayingEvents.SoundInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundSystem.class)
@Environment(EnvType.CLIENT)
public abstract class SoundSystemMixin {

  @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/SoundInstance;getVolume()F"), method = "getAdjustedVolume")
  private float getVolume(SoundInstance soundInstance) {
    SoundPlayingEvents.SoundInfo soundInfo = new SoundInfo(soundInstance.getVolume(),
        soundInstance.getX(), soundInstance.getY(), soundInstance.getZ());
    SoundPlayingEvents.SOUND_VOLUME_EVENT.invoker().onSoundVolume(soundInfo);
    return soundInfo.getVolume();
  }
}
