plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sign3.demo.lib1"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.sign3.demo.lib1"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            optimization {
                enable = true
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Firebase-style: import the BOM, then declare modules without versions.
    implementation(platform("com.github.Ssehgal29.librarybompoc:lib-bom:1.0.2"))
    implementation("com.github.Ssehgal29.librarybompoc:lib-1")

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
