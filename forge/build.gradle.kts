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
        exclude(dependency("com.google.code.gson:gson"))
    }
    // core burada gömülüyor; forge kendi Gson'unu runtime'da zaten sağlıyor.
    relocate("dev.burned.config.internal", "dev.burned.config.internal")
}

tasks.jar {
    finalizedBy("reobfJar")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
