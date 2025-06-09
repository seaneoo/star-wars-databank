plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.kapt)
}

android {
    namespace = "edu.regis.soconnor005.starwarsdatabank"
    compileSdk = 35

    defaultConfig {
        applicationId = "edu.regis.soconnor005.starwarsdatabank"
        minSdk = 31
        targetSdk = 35
        versionCode = 3
        versionName = "2025.06.15"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.room.runtime)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
}
