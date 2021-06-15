import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
    id("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
    maven {
        name = "velocitypowered-repo"
        url = uri("https://repo.velocitypowered.com/releases/")
    }
    maven {
        name = "minecraft-libraries"
        url = uri("https://libraries.minecraft.net/")
    }
    maven {
        name = "spongepowered-repo"
        url = uri("https://repo.spongepowered.org/maven")
    }
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/gigaclub/baseapi")
        metadataSources {
            mavenPom()
            artifact()
        }
    }}

dependencies {
    implementation(project(":base-common"))
    compileOnly("com.velocitypowered:velocity-api:1.1.0")
    annotationProcessor("com.velocitypowered:velocity-api:1.1.0")
}

tasks {
    processResources {
        from(sourceSets["main"].resources) {
            val tokens = mapOf("version" to version)
            filter(ReplaceTokens::class, mapOf("tokens" to tokens))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
