package io.github.estivensh4.kotlinmultiplatformwizard.common.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import io.github.estivensh4.kotlinmultiplatformwizard.common.utils.TemplateGroup

class CommonGenerator(
    private val params: KmpModuleModel,
) {
    fun generate(
        list: MutableList<GeneratorAsset>,
        ftManager: FileTemplateManager,
        packageName: String,
    ) = list.apply {
        operator fun GeneratorAsset.unaryPlus() = add(this)

        val generatorList: List<PlatformGenerator> = listOfNotNull(
            if (params.hasAndroid) AndroidGenerator(params, true) else null,
            if (params.hasDesktop) DesktopGenerator(params, true) else null,
            if (params.hasWeb) WasmGenerator(params, true) else null,
            if (params.hasIOS) IOSGenerator(params, true) else null,
            if (params.hasServer) ServerGenerator(params, true) else null
        )

        //Project
        +GeneratorTemplateFile(
            "build.gradle.kts",
            ftManager.getCodeTemplate(TemplateGroup.COMPOSE_PROJECT_GRADLE)
        )

        +GeneratorTemplateFile(
            "settings.gradle.kts",
            ftManager.getCodeTemplate(TemplateGroup.PROJECT_SETTINGS)
        )

        +GeneratorTemplateFile(
            "gradle.properties",
            ftManager.getCodeTemplate(TemplateGroup.PROJECT_GRADLE)
        )

        +GeneratorTemplateFile(
            "gradle/wrapper/gradle-wrapper.properties",
            ftManager.getCodeTemplate(TemplateGroup.GRADLE_WRAPPER_PROPERTIES)
        )

        +GeneratorTemplateFile(
            "gradle/libs.versions.toml",
            ftManager.getCodeTemplate(TemplateGroup.PROJECT_TOML)
        )

        //Common
        +GeneratorTemplateFile(
            "${params.composeName}/src/commonMain/kotlin/$packageName/${params.composeName}/App.kt",
            ftManager.getCodeTemplate(TemplateGroup.COMMON_APP)
        )

        +GeneratorTemplateFile(
            "${params.sharedName}/src/commonMain/kotlin/$packageName/${params.sharedName}/Platform.kt",
            ftManager.getCodeTemplate(TemplateGroup.COMMON_PLATFORM)
        )

        if (params.hasServer) {
            +GeneratorTemplateFile(
                "${params.sharedName}/src/commonMain/kotlin/$packageName/${params.sharedName}/Constants.kt",
                ftManager.getCodeTemplate(TemplateGroup.SHARED_CONSTANTS)
            )
        }

        +GeneratorTemplateFile(
            "${params.sharedName}/src/commonMain/kotlin/$packageName/${params.sharedName}/Greeting.kt",
            ftManager.getCodeTemplate(TemplateGroup.SHARED_GREETING)
        )

        +GeneratorTemplateFile(
            "${params.composeName}/src/commonMain/composeResources/drawable/compose-multiplatform.xml",
            ftManager.getCodeTemplate(TemplateGroup.COMMON_COMPOSE_RESOURCES_MULTIPLATFORM_XML)
        )

        +GeneratorTemplateFile(
            "${params.sharedName}/build.gradle.kts",
            ftManager.getCodeTemplate(TemplateGroup.COMMON_BUILD)
        )

        +GeneratorTemplateFile(
            "${params.composeName}/build.gradle.kts",
            ftManager.getCodeTemplate(TemplateGroup.COMPOSE_GRADLE_KTS)
        )

        addAll(generatorList.flatMap { it.commonFiles(ftManager, packageName) })
        addAll(generatorList.flatMap { it.generate(ftManager, packageName) })
    }
}