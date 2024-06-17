package io.github.estivensh4.kotlinmultiplatformwizard.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorEmptyDirectory
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderTemplateGroup
import io.github.estivensh4.kotlinmultiplatformwizard.utils.GeneratorTemplateFile

class ServerGenerator(params: BuilderParams) : PlatformGenerator(params) {
    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return listOf(
            GeneratorTemplateFile(
                "${params.serverName}/src/main/kotlin/$packageName/${params.serverName}/Application.kt",
                ftManager.getCodeTemplate(BuilderTemplateGroup.SERVER_APP_MAIN)
            ),
            GeneratorTemplateFile(
                "${params.serverName}/src/main/resources/logback.xml",
                ftManager.getCodeTemplate(BuilderTemplateGroup.SERVER_LOGBACK)
            ),
            GeneratorTemplateFile(
                "${params.serverName}/build.gradle.kts",
                ftManager.getCodeTemplate(BuilderTemplateGroup.SERVER_BUILD_GRADLE_KTS)
            )
        )
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return listOf()
    }
}