package io.github.estivensh4.kotlinmultiplatformwizard.module

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorEmptyDirectory
import com.intellij.ide.starters.local.GeneratorTemplateFile
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.*
import io.github.estivensh4.kotlinmultiplatformwizard.common.utils.Utils
import io.github.estivensh4.kotlinmultiplatformwizard.module.generators.ModuleCommonGenerator

class KmpModuleRecipe {
    fun executeRecipe(project: Project, model: KmpModuleModel, moduleDir: VirtualFile) {
        val ftManager = FileTemplateManager.getInstance(project)
        val templateData = mapOf(
            "PACKAGE_NAME" to model.packageName,
            "SHARED_NAME" to model.moduleName,
            "MODULE_NAME" to model.moduleName,
            "HAS_ANDROID" to model.hasAndroid,
            "HAS_IOS" to model.hasIOS,
            "HAS_WEB" to model.hasWeb,
            "HAS_DESKTOP" to model.hasDesktop,
            "BUILD_VERSION_SDK_INT" to "\${Build.VERSION.SDK_INT}",
            "JVM_JAVA_VERSION" to "\${System.getProperty(\"java.version\")}",
            model.hasAndroid(),
            model.hasDesktop(),
            model.hasIOS(),
            model.hasWeb(),
            model.hasServer(),
        )

        val generatorAssets = mutableListOf<GeneratorAsset>()
        val moduleCommonList = ModuleCommonGenerator(model).generate(generatorAssets, ftManager, model.packageName)
        generatorAssets.addAll(moduleCommonList)
        generatorAssets.forEach { asset ->
            when (asset) {
                is GeneratorEmptyDirectory -> createEmptyDirectory(moduleDir, asset.relativePath)
                is GeneratorTemplateFile -> Utils.generateFileFromTemplate(
                    templateName = "${asset.template.name}.${asset.template.extension}",
                    dataModel = templateData,
                    outputDir = moduleDir,
                    outputFilePath = asset.relativePath
                )

                else -> println("Module Generator: Nothing")
            }
        }
    }

    private fun createEmptyDirectory(parent: VirtualFile, path: String) {
        VfsUtil.createDirectoryIfMissing(parent, path)
    }
}