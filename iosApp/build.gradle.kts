plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":composeApp"))
        }

        iosMain.dependencies {
        }
    }
}


