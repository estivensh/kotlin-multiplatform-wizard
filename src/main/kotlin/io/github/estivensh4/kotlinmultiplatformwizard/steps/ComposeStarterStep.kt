package io.github.estivensh4.kotlinmultiplatformwizard.steps

import com.intellij.ide.starters.local.StarterContextProvider
import com.intellij.ide.starters.local.wizard.StarterInitialStep
import com.intellij.ui.dsl.builder.Panel
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderParams
import io.github.estivensh4.kotlinmultiplatformwizard.BuilderWizardBuilder
import io.github.estivensh4.kotlinmultiplatformwizard.utils.NetworkVersions
import org.jetbrains.skiko.OS
import org.jetbrains.skiko.hostOs

class ComposeStarterStep(
    private val builder: BuilderWizardBuilder,
    private val params: BuilderParams = builder.params,
    contextProvider: StarterContextProvider,
) : StarterInitialStep(contextProvider) {
    override fun addFieldsAfter(layout: Panel) {
        layout.group {
            addCheckboxItem("Include Android", params.hasAndroid) { params.hasAndroid = it }

            if (hostOs == OS.MacOS) {
                addCheckboxItem("Include iOS", params.hasiOS) { params.hasiOS = it }
            }

            addCheckboxItem("Include Desktop", params.hasDesktop) { params.hasDesktop = it }
            addCheckboxItem("Include Web", params.hasWeb) { params.hasWeb = it }
            addCheckboxItem("Include Server", params.hasServer) { params.hasServer = it }

            addDivider()

            addCheckboxItem("Use Material 3", params.compose.useMaterial3) { params.compose.useMaterial3 = it }

            addDivider()

            collapsibleGroup("Libraries") {
                librariesWithDocumentation(
                    label = "Use ktor",
                    documentationUrl = "https://ktor.io/docs/welcome.html",
                    isChecked = params.library.useKtor,
                    selectedChangeListener = { params.library.useKtor = it }
                )

                addCheckboxItem(
                    "Use Ktor for HTTP client apps",
                    params.library.useKtor
                ) { params.library.useKtor = it }
            }

            addDivider()

            addCheckboxItem(
                "Get latest library versions from remote source?",
                params.remoteVersions
            ) { params.remoteVersions = it }

            row { text("The source is from this plugin's GitHub Repo.") }

            row { browserLink("GitHub Repo", NetworkVersions.githubRepoUrl) }
        }
    }

    private fun Panel.addDivider() {
        //separator()
    }

    private fun Panel.addCheckboxItem(
        label: String,
        isChecked: Boolean,
        selectedChangeListener: (Boolean) -> Unit,
    ) {
        row {
            checkBox(label).applyToComponent {
                isSelected = isChecked
                addActionListener { selectedChangeListener(isSelected) }
            }
        }
    }

    private fun Panel.librariesWithDocumentation(
        label: String,
        documentationUrl: String,
        isChecked: Boolean,
        selectedChangeListener: (Boolean) -> Unit,
    ) {
        row {
            checkBox(label).applyToComponent {
                isSelected = isChecked
                addActionListener { selectedChangeListener(isSelected) }
            }
            browserLink("Documentation", documentationUrl)
        }
    }
}