import org.gradle.jvm.tasks.Jar
import com.jfrog.bintray.gradle.BintrayPlugin
import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    application
    `maven-publish`
    kotlin("jvm") version "1.3.61"
    id("org.jetbrains.dokka") version "0.10.0"
    id("com.jfrog.bintray") version "1.8.0"
}

group = "io.kuzzle.sdk"
version = "1.0.0"

val ktorVersion = "1.3.2"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("com.google.code.gson:gson:2.8.5")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.mockk:mockk:1.8.13")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-json-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-js:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-native:$ktorVersion")

}

// Configure existing Dokka task to output HTML to typical Javadoc directory
tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}

// Create dokka Jar task from dokka task output
val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    classifier = "javadoc"
    // dependsOn(tasks.dokka) not needed; dependency automatically inferred by from(tasks.dokka)
    from(tasks.dokka)
}

// Create sources Jar from main kotlin sources
val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    classifier = "sources"
    from(sourceSets["main"].allSource)
}

application {
    mainClassName = "io.kuzzle.sdk.protocol"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
    from(configurations.compileClasspath.get().map { if (it.isDirectory()) it else zipTree(it) })
}

publishing {
    publications {
        create<MavenPublication>("MyPublication") {
            from(components["java"])
            artifact(sourcesJar)
            artifact(dokkaJar)
            groupId("io.kuzzle")
            artifactId("kuzzle-sdk-jvm")
            version(version)
            pom.withXml {
            def root = asNode()
            root.appendNode("description", "Kuzzle JVM SDK")
            root.appendNode("name", "kuzzle-sdk-java")
            root.appendNode("url", "https://github.com/kuzzleio/sdk-jvm")
            root.children().last() + pomConfig
            }
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/repository")
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    setPublications("MyPublication")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "kuzzle-sdk-jvm"
        userOrg = "kuzzle"
        websiteUrl = "https://kuzzle.io"
        vcsUrl = "https://github.com/kuzzleio/sdk-jvm.git"
        setLabels("kotlin")
        desc = "Kuzzle JVM SDK"
        publicDownloadNumbers = true
        publish = true
    })

}

tasks {
    withType(GradleBuild::class.java) {
        dependsOn(shadowJar)
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType(Test::class.java) {
        testLogging.showStandardStreams = true
    }
    withType<GenerateMavenPom> {
        destination = file("$buildDir/libs/${shadowJar.archiveName}.pom")
    }
}