package io.github.estivensh4.kotlinmultiplatformwizard.module.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.generators.*
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import io.github.estivensh4.kotlinmultiplatformwizard.common.utils.TemplateGroup

class ModuleCommonGenerator(
    private val params: KmpModuleModel,
) {
    fun generate(
        list: MutableList<GeneratorAsset>,
        ftManager: FileTemplateManager,
        packageName: String,
    ) = list.apply {
        operator fun GeneratorAsset.unaryPlus() = add(this)

        val generatorList: List<PlatformGenerator> = listOfNotNull(
            if (params.hasAndroid) AndroidGenerator(params, false) else null,
            if (params.hasDesktop) DesktopGenerator(params, false) else null,
            if (params.hasWeb) WasmGenerator(params, false) else null,
            if (params.hasIOS) IOSGenerator(params, false) else null,
            if (params.hasServer) ServerGenerator(params, false) else null
        )

        //Common
        +GeneratorTemplateFile(
            "src/commonMain/kotlin/$packageName/${params.moduleName}/Platform.kt",
            ftManager.getCodeTemplate(TemplateGroup.COMMON_PLATFORM)
        )

        +GeneratorTemplateFile(
            "build.gradle.kts",
            ftManager.getCodeTemplate(TemplateGroup.COMMON_BUILD)
        )

        addAll(generatorList.flatMap { it.commonFiles(ftManager, packageName) })
        addAll(generatorList.flatMap { it.generate(ftManager, packageName) })
    }
}