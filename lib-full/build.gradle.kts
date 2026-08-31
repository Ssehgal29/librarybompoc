plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}

android {
    namespace = "com.sign3.libfull"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    // lib-full aggregates all individual libraries and re-exports them.
    api(project(":lib-1"))
    api(project(":lib-2"))
    api(project(":lib-3"))
    api(project(":lib-4"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = project.property("PUBLISH_GROUP") as String
            artifactId = "lib-full"
            version = project.property("PUBLISH_VERSION") as String
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
