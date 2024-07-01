package io.github.estivensh4.kotlinmultiplatformwizard.common.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import io.github.estivensh4.kotlinmultiplatformwizard.common.utils.TemplateGroup

class DesktopGenerator(params: KmpModuleModel, private val isProject: Boolean) : PlatformGenerator(params) {
    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return if (isProject) {
            listOf(
                GeneratorTemplateFile(
                    "${params.composeName}/src/desktopMain/kotlin/$packageName/${params.composeName}/main.kt",
                    ftManager.getCodeTemplate(TemplateGroup.DESKTOP_MAIN)
                )
            )
        } else emptyList()
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        val relativePath = if (isProject) {
            "${params.sharedName}/src/jvmMain/kotlin/$packageName/${params.sharedName}/Platform.jvm.kt"
        } else {
            "src/jvmMain/kotlin/$packageName/${params.moduleName}/Platform.jvm.kt"
        }
        return listOf(
            GeneratorTemplateFile(
                relativePath,
                ftManager.getCodeTemplate(TemplateGroup.JVM_PLATFORM)
            )
        )
    }
}