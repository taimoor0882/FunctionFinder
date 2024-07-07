plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.loginscreenactivity"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.loginscreenactivity"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.17")
    implementation ("com.google.android.material:material:1.6.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

}