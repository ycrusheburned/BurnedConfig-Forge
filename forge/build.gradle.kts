plugins {
    id("net.minecraftforge.gradle") version "6.0.+"
    id("com.gradleup.shadow") version "8.3.5"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

minecraft {
    mappings("official", "1.20.1")
}

dependencies {
    minecraft("net.minecraftforge:forge:1.20.1-47.4.0")
    implementation(project(":core"))
    compileOnly("com.google.code.gson:gson:2.11.0")
}

base {
    archivesName.set("${rootProject.property("archives_base_name")}-forge")
}

tasks.shadowJar {
    archiveClassifier.set("")
    archiveVersion.set("${project.version}-1.20.1")
    dependencies {
        // core dışında hiçbir şey gömülmez: Gson ve Forge zaten runtime'da
        // mevcuttur, tekrar paketlenmeleri jar boyutunu şişirir.
        exclude(dependency("com.google.code.gson:gson"))
    }
}

// Varsayılan (shade edilmemiş, core'u içermeyen) jar task'ına ihtiyacımız
// yok; tek dağıtım artifact'i shadowJar'dır.
tasks.jar {
    enabled = false
}

// ForgeGradle'ın normalde reobfJar (plain jar task'ı hedefleyen) yerine,
// asıl dağıtacağımız shadowJar çıktısını reobfuscate ediyoruz.
reobf {
    create("shadowJar")
}

tasks.build {
    dependsOn("reobfShadowJar")
}
