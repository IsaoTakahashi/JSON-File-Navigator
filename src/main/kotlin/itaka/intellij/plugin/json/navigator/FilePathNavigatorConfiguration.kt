package itaka.intellij.plugin.json.navigator

import com.intellij.openapi.options.BaseConfigurable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.options.SearchableConfigurable
import org.jetbrains.annotations.Nls

import javax.swing.*

class FilePathNavigatorConfiguration : BaseConfigurable(), SearchableConfigurable {
    override fun getId(): String {
        return "FilePathNavigatorConfiguration"
    }

    @Nls
    override fun getDisplayName(): String {
        return "FilePathNavigator"
    }

    override fun createComponent(): JComponent? {
        return null
    }

    override fun isModified(): Boolean {
        return false
    }

    @Throws(ConfigurationException::class)
    override fun apply() {

    }
}
