package co.com.cope.domain.exceptions

sealed class DynamicModuleInstallationException(
    open val featureName: String,
    override val cause: Throwable? = null
) :
    Exception("Error installing feature ${featureName}") {
    class InstallationFailed(
        override val featureName: String,
        override val cause: Throwable? = null
    ) :
        DynamicModuleInstallationException(featureName, cause)

    class NetworkError(
        override val featureName: String,
        override val cause: Throwable? = null
    ) :
        DynamicModuleInstallationException(featureName, cause)
}
