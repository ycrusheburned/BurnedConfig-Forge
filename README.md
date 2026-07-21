# BurnedConfig-Forge

[BurnedConfig](https://github.com/ycrusheburned/BurnedConfig) kütüphanesinin **Forge 1.21.11** portu.

`core` modülü orijinal Fabric deposundan hiç değiştirilmeden taşındı;
tüm config mantığı (annotation API + builder API) aynen korunuyor.
`forge` modülü, Fabric tarafındaki `BurnedConfigFabric` ile birebir aynı
işi yapan ince bir `@Mod` giriş noktasıdır — sadece config dizinini
`FMLPaths.CONFIGDIR` üzerinden `core`'a bildirir.

## Kurulum / kullanım

Kullanım şekli orijinal repo ile birebir aynıdır, bkz. [BurnedConfig README](https://github.com/ycrusheburned/BurnedConfig#kullanım-annotation-api).

## Derleme

```bash
./gradlew build
```

Jar `forge/build/libs/` altında oluşur. GitHub Actions her push'ta
otomatik derleyip `BurnedConfig-Forge-jar` adıyla artifact üretir.

## Lisans

[MIT](LICENSE)
