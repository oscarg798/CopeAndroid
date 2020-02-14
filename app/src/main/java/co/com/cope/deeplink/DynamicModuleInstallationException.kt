package co.com.cope.deeplink

sealed class DynamicModuleInstallationException(
    open val featureName: FeatureName,
    override val cause: Throwable? = null
) :
    Exception("Error installing feature ${FeatureName}") {
    class InstallationFailed(
        override val featureName: FeatureName,
        override val cause: Throwable? = null
    ) :
        DynamicModuleInstallationException(featureName, cause)

    class NetworkError(
        override val featureName: FeatureName,
        override val cause: Throwable? = null
    ) :
        DynamicModuleInstallationException(featureName, cause)
}
