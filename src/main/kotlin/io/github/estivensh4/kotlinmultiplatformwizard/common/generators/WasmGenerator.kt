package io.github.estivensh4.kotlinmultiplatformwizard.common.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import io.github.estivensh4.kotlinmultiplatformwizard.common.utils.TemplateGroup

class WasmGenerator(params: KmpModuleModel, private val isProject: Boolean) : PlatformGenerator(params) {
    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return if (isProject) {
            listOf(
                GeneratorTemplateFile(
                    "${params.composeName}/src/wasmJsMain/resources/styles.css",
                    ftManager.getCodeTemplate(TemplateGroup.WASMJS_STYLES_CSS)
                ),
                GeneratorTemplateFile(
                    "${params.composeName}/src/wasmJsMain/resources/index.html",
                    ftManager.getCodeTemplate(TemplateGroup.WASMJS_INDEX_HTML)
                ),
                GeneratorTemplateFile(
                    "${params.composeName}/src/wasmJsMain/kotlin/$packageName/${params.composeName}/main.kt",
                    ftManager.getCodeTemplate(TemplateGroup.COMPOSE_WASM_JS_MAIN)
                )
            )
        } else emptyList()
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        val relativePath = if (isProject) {
            "${params.sharedName}/src/wasmJsMain/kotlin/$packageName/${params.sharedName}/Platform.wasmJs.kt"
        } else "src/wasmJsMain/kotlin/$packageName/${params.moduleName}/Platform.wasmJs.kt"
        return listOf(
            GeneratorTemplateFile(
                relativePath,
                ftManager.getCodeTemplate(TemplateGroup.WASM_JS_MAIN)
            )
        )
    }
}