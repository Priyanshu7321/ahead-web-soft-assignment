import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
}

// Load keystore properties
val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.example.assignmentprojxml"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.assignmentprojxml"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // BuildConfig fields for API configuration - FIXED SYNTAX
        buildConfigField("String", "API_BASE_URL", "\"${findProperty("API_BASE_URL") ?: "https://demo.socialnetworking.solutions/sesapi/"}\"")
        buildConfigField("String", "API_REST_API", "\"${findProperty("API_REST_API") ?: "Sesapi"}\"")
        buildConfigField("int", "API_PLATFORM", "${findProperty("API_PLATFORM") ?: "1"}")
        buildConfigField("String", "API_AUTH_TOKEN", "\"${findProperty("API_AUTH_TOKEN") ?: "B179086bb56c32731633335762"}\"")
    }

    signingConfigs {
        create("release") {
            if (keystorePropertiesFile.exists()) {
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
                storeFile = file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            // Debug configuration
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    buildFeatures {
        viewBinding = true
        buildConfig = true  // Re-enable BuildConfig - PROPERLY CONFIGURED
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.drawerlayout)
    
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    
    // Lifecycle components
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    
    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)
    
    // Dagger
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
    
    // Image loading
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    
    // CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}