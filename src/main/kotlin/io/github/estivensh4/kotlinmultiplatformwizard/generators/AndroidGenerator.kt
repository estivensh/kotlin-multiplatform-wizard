package io.github.estivensh4.kotlinmultiplatformwizard.generators

//import com.android.tools.idea.welcome.install.AndroidSdk
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import com.intellij.ide.starters.local.StarterModuleBuilder
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderTemplateGroup
import io.github.estivensh4.kotlinmultiplatformwizard.utils.GeneratorTemplateFile

class AndroidGenerator(params: BuilderParams, private val projectName: String) : PlatformGenerator(params) {

    override fun setup() {
        ///AndroidSdk(true)
    }

    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        val sanitizedPackageName = StarterModuleBuilder.sanitizePackage(projectName)

        return listOf(
            GeneratorTemplateFile(
                "${params.composeName}/src/androidMain/kotlin/$packageName/${sanitizedPackageName}/MainActivity.kt",
                ftManager.getCodeTemplate(BuilderTemplateGroup.ANDROID_MAINACTIVITY)
            ),
            GeneratorTemplateFile(
                "${params.composeName}/src/androidMain/AndroidManifest.xml",
                ftManager.getCodeTemplate(BuilderTemplateGroup.ANDROID_MANIFEST)
            ),
            GeneratorTemplateFile(
                "${params.composeName}/src/androidMain/res/values/strings.xml",
                ftManager.getCodeTemplate(BuilderTemplateGroup.ANDROID_VALUES_XML)
            ),
        )
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return listOf(
            GeneratorTemplateFile(
                "${params.sharedName}/src/androidMain/kotlin/$packageName/${params.sharedName}/Platform.android.kt",
                ftManager.getCodeTemplate(BuilderTemplateGroup.DEFAULT_PLATFORM)
            )
        )
    }
}
