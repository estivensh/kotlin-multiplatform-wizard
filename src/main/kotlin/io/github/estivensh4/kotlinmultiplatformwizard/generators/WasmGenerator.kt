package io.github.estivensh4.kotlinmultiplatformwizard.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.starters.local.GeneratorAsset
import com.intellij.ide.starters.local.GeneratorTemplateFile
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderTemplateGroup
import io.github.estivensh4.kotlinmultiplatformwizard.utils.GeneratorTemplateFile

class WasmGenerator(params: BuilderParams, val appName: String) : PlatformGenerator(params) {
    override fun generateProject(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> {
        return listOf(
            GeneratorTemplateFile(
                "${params.composeName}/src/wasmJsMain/resources/styles.css",
            ) {
                """
                html, body {
                    width: 100%;
                    height: 100%;
                    margin: 0;
                    padding: 0;
                    overflow: hidden;
                }
                """.trimIndent()
            },
            GeneratorTemplateFile(
                "${params.composeName}/src/wasmJsMain/resources/index.html",
            ) {
                """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>${appName}</title>
                        <link type="text/css" rel="stylesheet" href="styles.css">
                        <script type="application/javascript" src="composeApp.js"></script>
                    </head>
                    <body>
                    </body>
                    </html>
                """.trimIndent()
            },
            GeneratorTemplateFile(
                "${params.composeName}/src/wasmJsMain/kotlin/$packageName/${params.composeName}/main.kt",
                ftManager.getCodeTemplate(BuilderTemplateGroup.COMPOSE_WASM_JS_MAIN)
            )
        )
    }

    override fun addToCommon(ftManager: FileTemplateManager, packageName: String): List<GeneratorAsset> = listOf(
        GeneratorTemplateFile(
            "${params.sharedName}/src/wasmJsMain/kotlin/$packageName/${params.sharedName}/Platform.wasmJs.kt",
            ftManager.getCodeTemplate(BuilderTemplateGroup.WASM_JS_MAIN)
        )
    )
}