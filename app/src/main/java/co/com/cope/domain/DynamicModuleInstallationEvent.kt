package co.com.cope.domain

sealed class DynamicModuleInstallationEvent {
    object ConfirmationRequired : DynamicModuleInstallationEvent()
    object Installed : DynamicModuleInstallationEvent()
    object Cancelled : DynamicModuleInstallationEvent()
    class Downloading(val progress: Double) : DynamicModuleInstallationEvent()
    object Error : DynamicModuleInstallationEvent()
}
