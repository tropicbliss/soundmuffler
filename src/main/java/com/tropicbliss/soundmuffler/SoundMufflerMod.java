package com.tropicbliss.soundmuffler;

import com.tropicbliss.soundmuffler.block.ModBlocks;
import com.tropicbliss.soundmuffler.events.handler.SoundEventHandler;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoundMufflerMod implements ModInitializer {

  public static final String MOD_ID = "soundmuffler";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    ModBlocks.registerBlocks();
    SoundEventHandler.init();
    LOGGER.info("Hello Fabric world!");
  }
}
