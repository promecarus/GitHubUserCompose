plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.promecarus.githubusercompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.promecarus.githubusercompose"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("Boolean", "DEBUG", "true")
        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        buildConfigField("String", "GITHUB_API_KEY", "\"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\"")
        buildConfigField("String", "DB_NAME", "\"favorite_db\"")
        buildConfigField("String", "PREF_HISTORY", "\"history\"")
        buildConfigField("String", "PREF_SETTING", "\"setting\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // default
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // koin
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.5.0")

    // logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // material
    implementation("androidx.compose.material:material-icons-extended")

    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.5")

    // retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // room
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.room:room-runtime:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")
    testImplementation("androidx.room:room-testing:2.6.0")

    // splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
}
