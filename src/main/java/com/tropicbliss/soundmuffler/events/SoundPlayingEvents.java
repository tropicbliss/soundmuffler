// Adapted from https://github.com/MorningSage/ExtremeSoundMuffler-Fabric/blob/master/src/main/java/morningsage/extremesoundmuffler/events/SoundPlayingEvents.java by tropicbliss on 22/12/2021

package com.tropicbliss.soundmuffler.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface SoundPlayingEvents {

  Event<SoundPlayingEvents> SOUND_VOLUME_EVENT = EventFactory.createArrayBacked(
      SoundPlayingEvents.class,
      callbacks -> (soundInfo) -> {
        for (SoundPlayingEvents callback : callbacks) {
          callback.onSoundVolume(soundInfo);
        }
      });

  void onSoundVolume(SoundInfo soundInfo);

  class SoundInfo {

    private float volume;
    private final double x;
    private final double y;
    private final double z;

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }

    public double getZ() {
      return z;
    }

    public SoundInfo(float volume, double x, double y, double z) {
      this.volume = volume;
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public float getVolume() {
      return volume;
    }

    public void setVolume(float volume) {
      this.volume = volume;
    }
  }
}
