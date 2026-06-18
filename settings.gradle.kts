// ─────────────────────────────────────────────────────────────────────
//  Ejected Multiverse — settings.gradle.kts
//  Foojay resolver (auto-download JDK) + plugin/dep management
// ─────────────────────────────────────────────────────────────────────

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "ejectedmultiverse"