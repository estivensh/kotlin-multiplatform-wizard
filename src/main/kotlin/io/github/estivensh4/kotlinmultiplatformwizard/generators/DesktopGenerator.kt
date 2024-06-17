package io.github.estivensh4.kotlinmultiplatformwizard.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorEmptyDirectory
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderTemplateGroup
import io.github.estivensh4.kotlinmultiplatformwizard.utils.GeneratorTemplateFile

class DesktopGenerator(params: BuilderParams) : PlatformGenerator(params) {
    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return listOf(
            GeneratorTemplateFile(
                "${params.composeName}/src/desktopMain/kotlin/$packageName/${params.composeName}/main.kt",
                ftManager.getCodeTemplate(BuilderTemplateGroup.DESKTOP_MAIN)
            )
        )
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return listOf(
            GeneratorTemplateFile(
                "${params.sharedName}/src/jvmMain/kotlin/$packageName/${params.sharedName}/Platform.jvm.kt",
                ftManager.getCodeTemplate(BuilderTemplateGroup.JVM_PLATFORM)
            )
        )
    }
}