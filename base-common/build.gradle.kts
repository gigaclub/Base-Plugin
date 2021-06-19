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
    api("org.apache.xmlrpc:xmlrpc-client:3.1.3")
    api("net.gigaclub:baseapi:14.0.1.0.1")
}