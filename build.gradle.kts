import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.net.URI

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "com.alwaysafter.minecraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://maven.elmakers.com/repository/")
    maven("https://repo.kryptonmc.org/releases")

    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("fr.mrmicky:fastboard:2.0.0")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")

    val lombok = "org.projectlombok:lombok:1.18.24"
    compileOnly(lombok)
    annotationProcessor(lombok)
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("")
    archiveFileName.set("${project.name}-${version}.jar")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

bukkit {
    name = "Paintball-Minecraft"
    prefix = "Paintball"
    apiVersion = "1.8"
    version = "${project.version}"
    main = "com.alwaysafter.minecraft.paintball.PaintballPlugin"
}
