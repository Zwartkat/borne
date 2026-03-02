
plugins {
    id("java")
    application
}

group = "fr.zwartkat"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(files("../../MG2D.jar"))
    implementation(files("./libs/snakeyaml.jar"))
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")

}

tasks.test {
    useJUnitPlatform()
}

application {
    // classe contenant le main
    mainClass.set("fr.zwartkat.Main")
}

// Fat JAR pour inclure les dépendances
tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "fr.zwartkat.Main"
    }

    // inclure toutes les dépendances runtime
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}