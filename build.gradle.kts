plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
}

group = "net.gigaclub"
version = "1.16.5.1.0.0"

subprojects {
    apply(plugin = "java")

    group = project.group
    version = project.version

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        mavenCentral()
    }
}
