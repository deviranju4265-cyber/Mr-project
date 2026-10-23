// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.google.devtools.ksp) apply false
  alias(libs.plugins.secrets) apply false
  alias(libs.plugins.google.services) apply false
}

// Automatically ensure debug.keystore exists from debug.keystore.base64 for CI/GitHub builds
val keystoreFile = file("${rootDir}/debug.keystore")
val base64File = file("${rootDir}/debug.keystore.base64")
if (!keystoreFile.exists() && base64File.exists()) {
  try {
    val decoded = java.util.Base64.getMimeDecoder().decode(base64File.readText().trim())
    keystoreFile.writeBytes(decoded)
  } catch (e: Exception) {
    logger.warn("Could not decode debug.keystore.base64: ${e.message}")
  }
}
