import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
}

group = "todo"
version = "1.0-SNAPSHOT"

kotlin {
    configAsKobwebApplication(includeServer = true)
    
    jvm {
        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "11"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk.core)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
            }
        }
    }
}