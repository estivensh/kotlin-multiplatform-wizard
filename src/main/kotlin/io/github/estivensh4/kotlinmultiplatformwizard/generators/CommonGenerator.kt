package io.github.estivensh4.kotlinmultiplatformwizard.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import com.intellij.ide.starters.local.StarterContext
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderTemplateGroup

internal const val SHARED_NAME = "SHARED_NAME"
internal const val PACKAGE_NAME = "PACKAGE_NAME"
internal const val COMPOSE_NAME = "COMPOSE_NAME"
internal const val SERVER_NAME = "SERVER_NAME"

class CommonGenerator(
    private val params: BuilderParams,
    private val starterContext: StarterContext,
) {
    fun generate(
        list: MutableList<GeneratorAsset>,
        ftManager: FileTemplateManager,
        packageName: String,
    ) = list.apply {
        operator fun GeneratorAsset.unaryPlus() = add(this)

        val generatorList: List<PlatformGenerator> = listOfNotNull(
            if (params.hasAndroid) AndroidGenerator(params, starterContext.artifact) else null,
            if (params.hasDesktop) DesktopGenerator(params) else null,
            if (params.hasWeb) WasmGenerator(params, starterContext.artifact) else null,
            if (params.hasiOS) IOSGenerator(params) else null,
            if (params.hasServer) ServerGenerator(params) else null
        )

        //Project
        +GeneratorTemplateFile(
            "build.gradle.kts",
            ftManager.getCodeTemplate(BuilderTemplateGroup.COMPOSE_PROJECT_GRADLE)
        )

        +GeneratorTemplateFile(
            "settings.gradle.kts",
            ftManager.getCodeTemplate(BuilderTemplateGroup.PROJECT_SETTINGS)
        )

        +GeneratorTemplateFile(
            "gradle.properties",
            ftManager.getCodeTemplate(BuilderTemplateGroup.PROJECT_GRADLE)
        )

        +GeneratorTemplateFile(
            "gradle/wrapper/gradle-wrapper.properties",
            ftManager.getCodeTemplate(BuilderTemplateGroup.GRADLE_WRAPPER_PROPERTIES)
        )

        +GeneratorTemplateFile(
            "gradle/libs.versions.toml",
            ftManager.getCodeTemplate(BuilderTemplateGroup.PROJECT_TOML)
        )

        //Common
        +GeneratorTemplateFile(
            "${params.composeName}/src/commonMain/kotlin/$packageName/${params.composeName}/App.kt",
            ftManager.getCodeTemplate(BuilderTemplateGroup.COMMON_APP)
        )

        +GeneratorTemplateFile(
            "${params.sharedName}/src/commonMain/kotlin/$packageName/${params.sharedName}/Platform.kt",
            ftManager.getCodeTemplate(BuilderTemplateGroup.COMMON_PLATFORM)
        )

        +GeneratorTemplateFile(
            "${params.sharedName}/src/commonMain/kotlin/$packageName/${params.sharedName}/Constants.kt",
            ftManager.getCodeTemplate(BuilderTemplateGroup.SHARED_CONSTANTS)
        )

        +GeneratorTemplateFile(
            "${params.sharedName}/src/commonMain/kotlin/$packageName/${params.sharedName}/Greeting.kt",
            ftManager.getCodeTemplate(BuilderTemplateGroup.SHARED_GREETING)
        )

        +GeneratorTemplateFile(
            "${params.composeName}/src/commonMain/composeResources/drawable/compose-multiplatform.xml",
            ftManager.getCodeTemplate(BuilderTemplateGroup.COMMON_COMPOSE_RESOURCES_MULTIPLATFORM_XML)
        )

        +GeneratorTemplateFile(
            "${params.sharedName}/build.gradle.kts",
            ftManager.getCodeTemplate(BuilderTemplateGroup.COMMON_BUILD)
        )

        +GeneratorTemplateFile(
            "${params.composeName}/build.gradle.kts",
            ftManager.getCodeTemplate(BuilderTemplateGroup.COMPOSE_GRADLE_KTS)
        )

        addAll(generatorList.flatMap { it.commonFiles(ftManager, packageName) })
        addAll(generatorList.flatMap { it.generate(ftManager, packageName) })
        generatorList.forEach { it.setup() }
    }
}