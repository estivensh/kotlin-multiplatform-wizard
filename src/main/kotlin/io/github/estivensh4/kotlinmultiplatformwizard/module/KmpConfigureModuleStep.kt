package io.github.estivensh4.kotlinmultiplatformwizard.module

import com.android.tools.idea.wizard.model.SkippableWizardStep
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import java.io.IOException
import javax.swing.JComponent

class KmpConfigureModuleStep(
    private val project: Project,
    private val model: KmpModuleModel
) : SkippableWizardStep<KmpModuleModel>(model, "KMM Module Configuration") {

    private lateinit var panel: KmpModuleConfigurationPanel

    override fun getComponent(): JComponent {
        if (!this::panel.isInitialized) {
            panel = KmpModuleConfigurationPanel()
        }
        return panel
    }

    override fun onProceeding() {
        super.onProceeding()
        model.packageName = panel.getPackageName().substringBeforeLast(".")
        model.moduleName = panel.getModuleName()
        model.hasAndroid = panel.isIncludeAndroid()
        model.hasIOS = panel.isIncludeIos()
        model.hasWeb = panel.isIncludeWeb()
        model.hasDesktop = panel.isIncludeDesktop()
        model.hasServer = panel.isIncludeServer()
    }

    override fun onWizardFinished() {
        super.onWizardFinished()
        createKmmModule(project, model)
    }

    private fun createKmmModule(project: Project, model: KmpModuleModel) {
        WriteCommandAction.runWriteCommandAction(project) {
            try {
                val baseDir = project.baseDir
                val moduleDir = createDirectory(baseDir, model.moduleName)
                KmpModuleRecipe().executeRecipe(project, model, moduleDir)
                addModuleToSettingsGradle(project, model.moduleName)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun addModuleToSettingsGradle(project: Project, moduleName: String) {
        val settingsFile = project.baseDir.findFileByRelativePath("settings.gradle.kts")
        if (settingsFile != null) {
            WriteCommandAction.runWriteCommandAction(project) {
                try {
                    val document = FileDocumentManager.getInstance().getDocument(settingsFile)
                    if (document != null) {
                        val newModuleEntry = "include(\":$moduleName\")"
                        if (!document.text.contains(newModuleEntry)) {
                            document.insertString(document.textLength, "\n$newModuleEntry")
                        }
                        FileDocumentManager.getInstance().saveDocument(document)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else {
            println("Error: settings.gradle.kts file not found.")
        }
    }


    private fun createDirectory(parent: VirtualFile, name: String): VirtualFile {
        return parent.createChildDirectory(null, name)
    }
}