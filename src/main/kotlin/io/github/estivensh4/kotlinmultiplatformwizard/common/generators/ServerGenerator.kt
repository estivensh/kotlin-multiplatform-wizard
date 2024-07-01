package io.github.estivensh4.kotlinmultiplatformwizard.common.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import io.github.estivensh4.kotlinmultiplatformwizard.common.utils.TemplateGroup

class ServerGenerator(params: KmpModuleModel, private val isProject: Boolean) : PlatformGenerator(params) {
    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return if (isProject) {
            listOf(
                GeneratorTemplateFile(
                    "${params.serverName}/src/main/kotlin/$packageName/${params.serverName}/Application.kt",
                    ftManager.getCodeTemplate(TemplateGroup.SERVER_APP_MAIN)
                ),
                GeneratorTemplateFile(
                    "${params.serverName}/src/main/resources/logback.xml",
                    ftManager.getCodeTemplate(TemplateGroup.SERVER_LOGBACK)
                ),
                GeneratorTemplateFile(
                    "${params.serverName}/build.gradle.kts",
                    ftManager.getCodeTemplate(TemplateGroup.SERVER_BUILD_GRADLE_KTS)
                )
            )
        } else emptyList()
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return if (!isProject) {
            listOf(
                GeneratorTemplateFile(
                    "src/jvmMain/kotlin/$packageName/${params.moduleName}/Platform.jvm.kt",
                    ftManager.getCodeTemplate(TemplateGroup.JVM_PLATFORM)
                )
            )
        } else emptyList()
    }
}