package io.github.estivensh4.kotlinmultiplatformwizard.module

import com.intellij.ui.JBColor
import com.intellij.util.ui.JBUI
import java.awt.*
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class KmpModuleConfigurationPanel : JPanel() {

    private val defaultPackage = "com.example"
    private val packageNameLabel: JLabel = JLabel("Package Name:")
    private val packageNameField: JTextField = JTextField(15)

    private val moduleNameLabel: JLabel = JLabel("Module Name:")
    val moduleNameField: JTextField = JTextField(15)
    private val moduleNameErrorLabel: JLabel = JLabel()

    private val includeAndroidCheckBox: JCheckBox = JCheckBox("Include Android")
    private val includeIosCheckBox: JCheckBox = JCheckBox("Include iOS")
    private val includeJvmCheckBox: JCheckBox = JCheckBox("Include JVM")
    private val includeWebCheckBox: JCheckBox = JCheckBox("Include Web")

    init {

        includeAndroidCheckBox.isSelected = true
        includeIosCheckBox.isSelected = true

        moduleNameField.text = "kmpsharedmodule"
        packageNameField.text = "${defaultPackage}.kmpsharedmodule"

        moduleNameField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                validateModuleName()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                validateModuleName()
            }

            override fun changedUpdate(e: DocumentEvent?) {
                validateModuleName()
            }
        })

        layout = GridBagLayout()
        val gbc = GridBagConstraints().apply {
            insets = JBUI.insets(5, 0)
            fill = GridBagConstraints.HORIZONTAL
            anchor = GridBagConstraints.NORTH
        }

        // Module Name
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.gridwidth = 1
        add(moduleNameLabel, gbc)
        gbc.gridx = 1
        gbc.gridy = 0
        gbc.gridwidth = 2
        add(moduleNameField, gbc)
        gbc.gridx = 1
        gbc.gridy = 1
        gbc.gridwidth = 2
        add(moduleNameErrorLabel, gbc)

        // Package Name
        gbc.gridx = 0
        gbc.gridy = 2
        gbc.gridwidth = 1
        add(packageNameLabel, gbc)
        gbc.gridx = 1
        gbc.gridy = 2
        gbc.gridwidth = 2
        add(packageNameField, gbc)

        // Include Platforms
        val platformPanel = JPanel(GridLayout(1, 4))
        platformPanel.add(includeAndroidCheckBox)
        platformPanel.add(includeIosCheckBox)
        platformPanel.add(includeWebCheckBox)
        platformPanel.add(includeJvmCheckBox)

        gbc.gridx = 0
        gbc.gridy = 4
        gbc.gridwidth = 3
        add(platformPanel, gbc)

        gbc.gridx = 0
        gbc.gridy = 5
        gbc.weighty = 1.0
        add(Box.createVerticalGlue(), gbc)

        moduleNameErrorLabel.isVisible = false
    }

    private fun validateModuleName() {
        val moduleName = moduleNameField.text.trim()
        if (moduleName.isEmpty()) {
            moduleNameField.background = JBColor.PINK
            moduleNameErrorLabel.text = "Please a enter a valid module name"
            moduleNameErrorLabel.isVisible = true
        } else {
            moduleNameField.background = JBColor.DARK_GRAY
            moduleNameErrorLabel.isVisible = false
        }

        updatePackageName()
    }

    private fun updatePackageName() {
        val moduleName = moduleNameField.text.trim()
        val packageName = "${defaultPackage}.${moduleName.lowercase()}"
        packageNameField.text = packageName
    }

    fun getPackageName(): String = packageNameField.text.trim()
    fun getModuleName(): String = moduleNameField.text.trim()
    fun isIncludeAndroid(): Boolean = includeAndroidCheckBox.isSelected
    fun isIncludeIos(): Boolean = includeIosCheckBox.isSelected
    fun isIncludeWeb(): Boolean = includeWebCheckBox.isSelected
    fun isIncludeDesktop(): Boolean = includeJvmCheckBox.isSelected
    fun isIncludeServer(): Boolean = includeJvmCheckBox.isSelected
}