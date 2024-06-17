package io.github.estivensh4.kotlinmultiplatformwizard.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams

abstract class PlatformGenerator(protected val params: BuilderParams) {

    open fun setup() {}

    fun generate(
        ftManager: FileTemplateManager,
        packageName: String,
    ) = generateProject(ftManager, packageName)

    protected abstract fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset>

    fun commonFiles(
        ftManager: FileTemplateManager,
        packageName: String,
    ) = addToCommon(ftManager, packageName)

    protected abstract fun addToCommon(
        ftManager: FileTemplateManager,
        packageName: String,
    ): List<GeneratorAsset>
}