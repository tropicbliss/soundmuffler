package com.tropicbliss.soundmuffler.events.handler;

import com.tropicbliss.soundmuffler.block.ModBlocks;
import com.tropicbliss.soundmuffler.events.SoundPlayingEvents;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class SoundEventHandler {

  private static final MinecraftClient client = MinecraftClient.getInstance();

  public static void init() {
    SoundPlayingEvents.SOUND_VOLUME_EVENT.register(SoundEventHandler::getSoundVolume);
  }

  private static void getSoundVolume(SoundPlayingEvents.SoundInfo soundInfo) {
    if ((client.world) == null) {
      return;
    }
    double minY = soundInfo.getY() - 7;
    double maxY = soundInfo.getY() + 7;
    double minX = soundInfo.getX() - 7;
    double maxX = soundInfo.getX() + 7;
    double minZ = soundInfo.getZ() - 7;
    double maxZ = soundInfo.getZ() + 7;
    for (double y = minY; y <= maxY; y++) {
      for (double x = minX; x <= maxX; x++) {
        for (double z = minZ; z <= maxZ; z++) {
          Block scannedBlock = client.world.getBlockState(new BlockPos(x, y, z)).getBlock();
          if (scannedBlock == ModBlocks.SOUND_MUFFLER) {
            soundInfo.setVolume(0.05F);
            return;
          }
        }
      }
    }
  }
}
