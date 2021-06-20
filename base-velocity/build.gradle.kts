import org.apache.tools.ant.filters.ReplaceTokens

val GITHUB_PACKAGES_USERID: String by project
val GITHUB_PACKAGES_IMPORT_TOKEN: String by project

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
        credentials {
            username = GITHUB_PACKAGES_USERID
            password = GITHUB_PACKAGES_IMPORT_TOKEN
        }
    }}

dependencies {
    implementation(project(":base-common"))
    compileOnly("com.velocitypowered:velocity-api:1.1.8")
    annotationProcessor("com.velocitypowered:velocity-api:1.1.8")
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
