package io.github.estivensh4.kotlinmultiplatformwizard.project

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

class KmpProjectTemplateProvider : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> {
        return listOf(KmpWizardTemplate().projectTemplate)
    }
}