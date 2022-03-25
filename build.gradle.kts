import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Date
import org.gradle.api.publish.maven.MavenPom
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

plugins {
    application
    `java-library`
    `maven-publish`
    signing
    jacoco
    kotlin("jvm") version "1.3.61"
}

val artifactName = "sdk-jvm"
val artifactGroup = "io.kuzzle"
val artifactVersion = "1.2.3"

val pomUrl = "https://github.com/kuzzleio/sdk-jvm"
val pomScmUrl = "https://github.com/kuzzleio/sdk-jvm"
val pomScmConnection = "git@github.com/kuzzleio/sdk-jvm"
val pomIssueUrl = "https://github.com/kuzzleio/sdk-jvm/issues"
val pomDesc = "https://github.com/kuzzleio/sdk-jvm"

val githubRepo = "kuzzleio/sdk-jvm"
val githubReadme = "README.md"

val pomLicenseName = "MIT"
val pomLicenseUrl = "https://opensource.org/licenses/mit-license.php"
val pomLicenseDist = "repo"

val pomDeveloperId = "kuzzleio"
val pomDeveloperName = "kuzzle"

group = "io.kuzzle.sdk"
version = "1.2.4"
val ktorVersion = "1.5.2"

repositories {
    mavenCentral()
}

configurations {
    register("cucumberRuntime") {
        extendsFrom(testImplementation.get())
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("com.google.code.gson:gson:2.9.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.mockk:mockk:1.8.13")
    testImplementation("io.ktor:ktor-client-mock:1.3.1")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-json-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-js:1.3.1")
    testImplementation("io.ktor:ktor-client-mock-native:1.3.1")
    testImplementation("org.mock-server:mockserver-netty:5.3.0")
    testImplementation("io.cucumber:cucumber-java8:7.0.0")
    testImplementation("io.cucumber:cucumber-junit:7.0.0")
}

java {
    withSourcesJar()
}

application {
    mainClassName = "io.kuzzle.sdk.protocol"
}

tasks.withType<Jar> {
    archiveFileName.set("${artifactName}-${artifactVersion}-without-dependencies.jar")
}

tasks {
    register<Jar>("fatJar") {
        archiveFileName.set("${artifactName}-${artifactVersion}.jar")
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

tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven {
            credentials {
                username = "Kuzzle"
                password = System.getenv("OSSRH_PASSWORD")
            }
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        }
    }
    publications {
        create<MavenPublication>("kuzzle-sdk-jvm-thin") {
            groupId = artifactGroup
            artifactId = artifactName
            version = "${artifactVersion}-without-dependencies"
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

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
                        appendNode("connection", pomScmConnection)
                    }
                }
            }
        }
        create<MavenPublication>("kuzzle-sdk-jvm-fat") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            artifact(tasks["fatJar"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

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
                        appendNode("connection", pomScmConnection)
                    }
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(System.getenv("MAVEN_CENTRAL_GPG"), System.getenv("MAVEN_CENTRAL_GPG_PASSWORD"))
    sign(publishing.publications["kuzzle-sdk-jvm-thin"])
    sign(publishing.publications["kuzzle-sdk-jvm-fat"])
}