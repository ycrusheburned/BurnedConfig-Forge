plugins {
    id("net.minecraftforge.gradle") version "6.0.+"
    id("com.gradleup.shadow") version "8.3.5"
}

java {
    // Forge 1.21.11 (ve dolayısıyla eventbus/fmlcore/fmlloader vb.) Java 21
    // gerektiriyor.
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

minecraft {
    mappings("official", "1.21.11")
}

val shade: Configuration by configurations.creating

dependencies {
    minecraft("net.minecraftforge:forge:1.21.11-61.1.0")
    shade(project(":core"))
    implementation(project(":core"))
    compileOnly("com.google.code.gson:gson:2.11.0")
}

base {
    archivesName.set("${rootProject.property("archives_base_name")}-forge")
}

tasks.shadowJar {
    // YALNIZCA 'shade' configuration'ındaki (yani core) sınıflar gömülür.
    // Varsayılan davranış tüm runtime classpath'i (Minecraft/Forge dahil)
    // taradığından jar'ı yüz megabaytlara şişiriyordu; bu satır o hatayı
    // giderir.
    configurations = listOf(shade)
    archiveClassifier.set("")
    archiveVersion.set("${project.version}-1.21.11")
}

// ForgeGradle varsayılan olarak "jar" task'ını hedefleyen bir reobfJar
// oluşturur; bu yüzden plain jar'ı devre dışı bırakmıyoruz, sadece
// dağıtım artifact'i olarak shadowJar'ı (core gömülü) kullanıyoruz.

// ForgeGradle'ın normalde reobfJar (plain jar task'ı hedefleyen) yerine,
// asıl dağıtacağımız shadowJar çıktısını reobfuscate ediyoruz.
reobf {
    create("shadowJar")
}

tasks.build {
    dependsOn("reobfShadowJar")
}
