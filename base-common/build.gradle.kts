plugins {
    `java-library`
}

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/gigaclub/baseapi")
        metadataSources {
            mavenPom()
            artifact()
        }
    }
}

dependencies {
    api("net.gigaclub:baseapi:14.0.1.0.1")
}