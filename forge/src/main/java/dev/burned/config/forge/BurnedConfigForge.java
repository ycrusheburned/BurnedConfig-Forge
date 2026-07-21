package dev.burned.config.forge;

import dev.burned.config.ConfigManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BurnedConfig'in Forge giriş noktası.
 * <p>
 * Bu sınıfın TEK görevi, platform-bağımsız {@code core} modülüne
 * Minecraft'ın gerçek config klasörünü bildirmektir. Bütün config mantığı
 * core modülünde yaşar; burada tekrar yazılmış hiçbir kod yoktur.
 * <p>
 * Fabric sürümündeki BurnedConfigFabric ile birebir aynı davranışı,
 * Forge'un FMLPaths API'si üzerinden sağlar. Hiçbir net.minecraft.*
 * sınıfına dokunmaz.
 */
@Mod(BurnedConfigForge.MOD_ID)
public final class BurnedConfigForge {

    public static final String MOD_ID = "burnedconfig";

    private static final Logger LOGGER = LoggerFactory.getLogger("BurnedConfig");

    public BurnedConfigForge() {
        ConfigManager.setBaseDirectory(FMLPaths.CONFIGDIR.get());
        LOGGER.info("BurnedConfig hazır ({} config dizini)", FMLPaths.CONFIGDIR.get());
    }
}
