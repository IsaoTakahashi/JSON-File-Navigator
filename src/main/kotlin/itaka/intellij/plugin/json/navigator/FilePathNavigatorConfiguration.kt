package itaka.intellij.plugin.json.navigator

import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.ui.layout.CCFlags
import com.intellij.ui.layout.panel
import org.jetbrains.annotations.Nls
import javax.swing.JComponent
import javax.swing.JTextField


class FilePathNavigatorConfiguration : SearchableConfigurable {

    private val state = FilePathNavigatorState.getInstance()
    private val pathField = JTextField("")

    override fun createComponent(): JComponent? {
        if (state.searchFilePaths != null) {
            pathField.text = state.searchFilePaths
        }

        return panel {
            noteRow("Please input additional search path with comma")
            row {
                pathField(CCFlags.grow)
            }
        }
    }


    override fun getId(): String {
        return "JsonFilePathNavigatorConfiguration"
    }

    @Nls
    override fun getDisplayName(): String {
        return "JsonFilePathNavigator"
    }

    override fun isModified(): Boolean {
        return !state.searchFilePaths.equals(pathField.text)
    }

    @Throws(ConfigurationException::class)
    override fun apply() {
        state.searchFilePaths = pathField.text
    }

    override fun reset() {
        pathField.text = state.searchFilePaths
    }
}
