package io.github.estivensh4.kotlinmultiplatformwizard.common.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import io.github.estivensh4.kotlinmultiplatformwizard.common.utils.TemplateGroup

class AndroidGenerator(
    params: KmpModuleModel,
    private val isProject: Boolean
) : PlatformGenerator(params) {

    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return if (isProject) {
            listOf(
                GeneratorTemplateFile(
                    "${params.composeName}/src/androidMain/kotlin/$packageName/${params.composeName}/MainActivity.kt",
                    ftManager.getCodeTemplate(TemplateGroup.ANDROID_MAINACTIVITY)
                ),
                GeneratorTemplateFile(
                    "${params.composeName}/src/androidMain/AndroidManifest.xml",
                    ftManager.getCodeTemplate(TemplateGroup.ANDROID_MANIFEST)
                ),
                GeneratorTemplateFile(
                    "${params.composeName}/src/androidMain/res/values/strings.xml",
                    ftManager.getCodeTemplate(TemplateGroup.ANDROID_VALUES_XML)
                ),
            )
        } else emptyList()
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {

        val relativePath = if (isProject) {
            "${params.sharedName}/src/androidMain/kotlin/$packageName/${params.sharedName}/Platform.android.kt"
        } else "src/androidMain/kotlin/$packageName/${params.moduleName}/Platform.android.kt"

        return listOf(
            GeneratorTemplateFile(
                relativePath,
                ftManager.getCodeTemplate(TemplateGroup.DEFAULT_PLATFORM)
            )
        )
    }
}
