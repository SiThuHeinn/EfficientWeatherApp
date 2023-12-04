plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.sithuheinn.mm.designsystems"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.stdlib.jdk)

    implementation(libs.androidx.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.activity.compose)
    implementation(libs.bundles.compose)
    implementation(libs.compose.material3)
    implementation(libs.lifecycle.viewModel.compose)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.bundles.android.tests)
    debugImplementation(libs.bundles.debug.test)
}