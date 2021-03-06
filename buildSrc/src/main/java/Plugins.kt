object Plugins {
    object Gradle {
        private const val version = "4.0.1"
        const val plugin = "com.android.tools.build:gradle:$version"
    }

    object Kotlin {
        private const val version = Dependencies.Kotlin.version
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Hilt {
        private const val version = Dependencies.Dagger.version
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Navigation {
        private const val version = "2.3.0"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    }

    object Mockk {
        private const val version = "1.12.4"
        const val mockk = "io.mockk:mockk:{version}"
    }
    object MockkAgent {
        private const val version = "1.12.4"
        const val mockka = "io.mockk:mockk-agent-jvm:{version}"
    }
    object MockkCommon {
        private const val version = "1.12.4"
        const val mockkc = "io.mockk:mockk-common:{version}"
    }
    object Junit {
        private const val version = "4.12"
        const val junit = "junit:junit:$version"
    }

    object Ext {
        private const val version = "1.1.1"
        const val ext = "androidx.test.ext:junit:$version"
    }

    object Espresso {
        private const val version = "3.2.0"
        const val espresso = "androidx.test.espresso:espresso-core:$version"
    }

}