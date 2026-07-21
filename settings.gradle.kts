rootProject.name = "BurnedConfig-Forge"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.minecraftforge.net/") { name = "MinecraftForge" }
        mavenCentral()
    }
}

// core:  saf Java, tüm config mantığı (BurnedConfig'in orijinal fabric
//        deposundan aynen taşındı, hiç değiştirilmedi)
// forge: ince Forge entrypoint'i (@Mod), core'u shadow ile gömer
include("core")
include("forge")
