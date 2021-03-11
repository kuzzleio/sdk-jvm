import org.gradle.jvm.tasks.Jar
import java.time.LocalDateTime
import com.jfrog.bintray.gradle.BintrayPlugin
import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.publish.maven.MavenPom
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    `maven-publish`
    kotlin("jvm") version "1.3.61"
    id("org.jetbrains.dokka") version "0.10.0"
    id("com.jfrog.bintray") version "1.8.5"
}

val artifactName = "sdk-jvm"
val artifactGroup = "io.kuzzle"
val artifactVersion = "1.2.0"

val pomUrl = "https://github.com/kuzzleio/sdk-jvm"
val pomScmUrl = "https://github.com/kuzzleio/sdk-jvm"
val pomIssueUrl = "https://github.com/kuzzleio/sdk-jvm/issues"
val pomDesc = "https://github.com/kuzzleio/sdk-jvm"

val githubRepo = "kuzzleio/sdk-jvm"
val githubReadme = "README.md"

val pomLicenseName = "MIT"
val pomLicenseUrl = "https://opensource.org/licenses/mit-license.php"
val pomLicenseDist = "repo"

val pomDeveloperId = "kuzzleio"
val pomDeveloperName = "kuzzle"

publishing {
    publications {
        create<MavenPublication>("kuzzle-sdk-jvm-fat") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            from(components["java"])
            artifact(tasks["fatJar"])

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
        create<MavenPublication>("kuzzle-sdk-jvm-thin") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            from(components["java"])

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true

    setPublications("kuzzle-sdk-jvm-fat", "kuzzle-sdk-jvm-thin")

    pkg.apply {
        repo = "maven"
        name = artifactName
        userOrg = "kuzzle"
        vcsUrl = pomScmUrl
        description = "Kuzzle JVM SDK"
        setLabels("kuzzle", "java", "kotlin", "scala", "jvm", "sdk")
        setLicenses("MIT")
        desc = description
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl

        version.apply {
            name = artifactVersion
            desc = pomDesc
            // released = LocalDateTime.now().toString()
            vcsTag = artifactVersion
        }
    }
}

group = "io.kuzzle.sdk"
version = "1.2.0"
val ktorVersion = "1.5.2"

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
    testImplementation("io.ktor:ktor-client-mock:1.3.2")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-json-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-js:1.3.2")
    testImplementation("io.ktor:ktor-client-mock-native:1.3.2")

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
    archiveClassifier.set("without-dependencies")
}

tasks {
  register("fatJar", Jar::class.java) {
    archiveClassifier.set("")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
      attributes("Main-Class" to application.mainClassName)
    }
    from(configurations.runtimeClasspath.get()
        .onEach { println("Add from dependencies: ${it.name}") }
        .map { if (it.isDirectory) it else zipTree(it) })
    val sourcesMain = sourceSets.main.get()
    sourcesMain.allSource.forEach { println("Add from sources: ${it.name}") }
    from(sourcesMain.output)
  }
}
