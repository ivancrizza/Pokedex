object Configs {
    const val applicationId = "com.ivan.crizza.pokedex"
    const val buildToolVersion = "29.0.3"
    const val compileSdkVersion = 29
    const val minSdkVersion = 19
    const val targetSdkVersion = 29
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val versionCode = calculateVersionCode()
    val versionName = calculateVersionName()
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    private fun calculateVersionCode(): Int = versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100

    private fun calculateVersionName(): String = "${versionMajor}.${versionMinor}.${versionPatch}"
}