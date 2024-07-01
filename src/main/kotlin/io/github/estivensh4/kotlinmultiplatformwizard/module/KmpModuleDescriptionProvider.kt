package io.github.estivensh4.kotlinmultiplatformwizard.module

import com.android.tools.idea.npw.model.ProjectSyncInvoker
import com.android.tools.idea.npw.module.ModuleDescriptionProvider
import com.android.tools.idea.npw.module.ModuleGalleryEntry
import com.android.tools.idea.wizard.model.SkippableWizardStep
import com.google.common.collect.ImmutableList
import com.intellij.openapi.project.Project
import com.intellij.ui.IconManager
import io.github.estivensh4.kotlinmultiplatformwizard.common.models.KmpModuleModel
import javax.swing.Icon


class KmpModuleDescriptionProvider : ModuleDescriptionProvider {

    override fun getDescriptions(project: Project): MutableCollection<out ModuleGalleryEntry> {
        return ImmutableList.of(FeatureModuleEntry())
    }

    @Suppress("UnstableApiUsage")
    class FeatureModuleEntry : ModuleGalleryEntry {
        override val description: String
            get() = ""
        override val icon: Icon
            get() = IconManager.getInstance()
                .loadRasterizedIcon("META-INF/pluginIcon.svg", this::class.java.classLoader, this.hashCode(), 0)
        override val name: String
            get() = "KMP Shared Module"

        override fun createStep(
            project: Project,
            moduleParent: String,
            projectSyncInvoker: ProjectSyncInvoker
        ): SkippableWizardStep<*> {
            val kmpModuleModel = KmpModuleModel()
            return KmpConfigureModuleStep(project, kmpModuleModel)
        }
    }
}