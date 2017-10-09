package itaka.intellij.plugin.json.navigator

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil


@State(
        name = "FilePathNavigatorState",
        storages = arrayOf(Storage("FilePathNavigatorState.xml"))
)
class FilePathNavigatorState : PersistentStateComponent<FilePathNavigatorState> {
    var searchFilePaths: String? = null

    override fun loadState(state: FilePathNavigatorState?) {
        if (state != null) {
            XmlSerializerUtil.copyBean(state, this)
        }
    }

    override fun getState(): FilePathNavigatorState? {
        return this
    }


    companion object {
        fun getInstance(): FilePathNavigatorState {
            return ServiceManager.getService(FilePathNavigatorState::class.java)
        }
    }
}