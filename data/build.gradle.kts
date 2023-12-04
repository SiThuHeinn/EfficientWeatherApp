plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")

}

android {
    namespace = "com.sithuheinn.mm.data"
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
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":designSystems"))

    implementation(libs.androidx.ktx)
    implementation(libs.material)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.android.test.junit)
    androidTestImplementation(libs.android.test.espresso.core)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.logging.interceptor)

    implementation(libs.threetenbp)

    implementation(libs.moshi)
    kapt(libs.moshi.codegen)

    // Room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
}