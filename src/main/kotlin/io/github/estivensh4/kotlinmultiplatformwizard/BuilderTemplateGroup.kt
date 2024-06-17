package io.github.estivensh4.kotlinmultiplatformwizard

import com.intellij.icons.AllIcons
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory

internal class BuilderTemplateGroup : FileTemplateGroupDescriptorFactory {
    override fun getFileTemplatesDescriptor(): FileTemplateGroupDescriptor {
        val root = FileTemplateGroupDescriptor("COMPOSE", AllIcons.Nodes.Module)

        with(root) {
            addTemplate(COMPOSE_PROJECT_GRADLE)
            addTemplate(ANDROID_BUILD)
            addTemplate(ANDROID_MAINACTIVITY)
            addTemplate(ANDROID_MANIFEST)
            addTemplate(COMMON_APP)
            addTemplate(COMMON_BUILD)
            addTemplate(COMMON_PLATFORM)
            addTemplate(DEFAULT_PLATFORM)
            addTemplate(DESKTOP_BUILD)
            addTemplate(DESKTOP_MAIN)
            addTemplate(IDEA_GRADLE)
            addTemplate(IOS_IOSAPP)
            addTemplate(IOS_PLATFORM)
            addTemplate(IOS_PLATFORM3)
            addTemplate(IOS_PROJECT)
            addTemplate(JS_BUILD)
            addTemplate(WASM_JS_MAIN)
            addTemplate(PROJECT_GRADLE)
            addTemplate(PROJECT_SETTINGS)
            addTemplate(COMMON_ANDROID_MANIFEST)
            addTemplate(PROJECT_TOML)
        }

        return root
    }

    companion object {
        const val COMPOSE_PROJECT_GRADLE = "project_build.gradle.kts"
        const val ANDROID_BUILD = "android_build.gradle.kts"
        const val ANDROID_MAINACTIVITY = "android_mainactivity.kt"
        const val ANDROID_MANIFEST = "android_manifest.xml"
        const val COMMON_APP = "common_app.kt"
        const val COMMON_BUILD = "common_build.gradle.kts"
        const val COMMON_PLATFORM = "common_platform.kt"
        const val DEFAULT_PLATFORM = "default_platform.kt"
        const val JVM_PLATFORM = "jvm_platform.kt"
        const val DESKTOP_BUILD = "desktop_build.gradle.kts"
        const val DESKTOP_MAIN = "desktop_main.kt"
        const val IDEA_GRADLE = "idea_gradle.xml"
        const val IOS_IOSAPP = "ios_iosapp.swift"
        const val IOS_PLATFORM = "ios_platform.kt"
        const val IOS_PLATFORM3 = "ios_platform3.kt"
        const val IOS_PROJECT = "ios_project.pbxproj"
        const val JS_BUILD = "js_build.gradle.kts"
        const val WASM_JS_MAIN = "wasm_js_main.kt"
        const val COMPOSE_WASM_JS_MAIN = "compose_wasm_js_main.kt"
        const val PROJECT_GRADLE = "project_gradle.properties"
        const val PROJECT_SETTINGS = "project_settings.gradle.kts"
        const val COMMON_ANDROID_MANIFEST = "common_android_manifest.xml"
        const val PROJECT_TOML = "libs.versions.toml"
        const val GRADLE_WRAPPER_PROPERTIES = "gradle_wrapper.properties"
        const val COMPOSE_GRADLE_KTS = "compose.gradle.kts"
        const val ANDROID_VALUES_XML = "values.xml"
        const val COMMON_COMPOSE_RESOURCES_MULTIPLATFORM_XML = "compose_multiplatform.xml"
        const val COMPOSE_IOS_MAIN = "compose_ios_main.kt"
        const val SERVER_APP_MAIN = "server_app.kt"
        const val SERVER_LOGBACK = "logback.xml"
        const val SERVER_BUILD_GRADLE_KTS = "server_build.gradle.kts"
        const val SHARED_CONSTANTS = "shared_constants.kt"
        const val SHARED_GREETING = "shared_greeting.kt"
        const val IOS_APP_CONFIGURATION = "config.xcconfig"
    }
}