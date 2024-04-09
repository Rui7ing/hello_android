plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("androidx.room") version "2.6.1" apply false
    kotlin("kapt")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("/Users/ruiqing.piao/Documents/KeyStore/debug_sign.keystore")
            storePassword = "debug123456"
            keyPassword = "debug123456"
            keyAlias = "debug_sign.keystore"
        }
        create("release") {
            storeFile = file("/Users/ruiqing.piao/Documents/KeyStore/release_sign.keystore")
            storePassword = "release123456"
            keyAlias = "release_sign.keystore"
            keyPassword = "release123456"
        }
    }
    namespace = "com.thoughtworks.androidtrain"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.thoughtworks.androidtrain"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)


    implementation(libs.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.converter.gson)
    kapt(libs.androidx.room.compiler)
    kapt(libs.androidx.room.ktx)
    implementation(libs.rxjava)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.symbol.processing.api)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.gson)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.coil)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}