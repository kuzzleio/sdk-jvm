import org.gradle.jvm.tasks.Jar
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    `java-library`
    `maven-publish`
    signing
    jacoco
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jetbrains.dokka") version "1.7.10"
}

val artifactName = "sdk-jvm"
val artifactGroup = "io.kuzzle"
val artifactVersion = "1.3.2"

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
version = "1.3.2"
val ktorVersion = "1.6.8"

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
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-json-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-js:1.3.1")
    testImplementation("io.ktor:ktor-client-mock-native:1.3.1")
    testImplementation("org.mock-server:mockserver-netty:5.3.0")
    testImplementation("io.cucumber:cucumber-java8:7.3.3")
    testImplementation("io.cucumber:cucumber-junit:7.3.3")
}

application {
    mainClass.set("io.kuzzle.sdk.protocol")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Test> {
    this.testLogging {
        this.showStandardStreams = true
    }
}

val javadocJar = tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
}

tasks.named<Jar>("jar") {
    archiveClassifier.set("without-dependencies")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveClassifier.set("")
        archiveBaseName.set(artifactName)
        manifest {
            attributes(mapOf("Main-Class" to application.mainClass.get()))
        }
    }
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
        create<MavenPublication>("kuzzle-sdk-jvm") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            artifact(tasks["jar"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["shadowJar"])
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
    sign(publishing.publications["kuzzle-sdk-jvm"])
}