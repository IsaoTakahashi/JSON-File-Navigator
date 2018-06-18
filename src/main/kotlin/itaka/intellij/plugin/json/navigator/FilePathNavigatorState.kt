package itaka.intellij.plugin.json.navigator

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil


@State(
        name = "JsonFilePathNavigatorState",
        storages = arrayOf(Storage("JsonFilePathNavigatorState.xml"))
)
class FilePathNavigatorState : PersistentStateComponent<FilePathNavigatorState> {
    var searchFilePaths: String? = null

    override fun loadState(state: FilePathNavigatorState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun getState(): FilePathNavigatorState? {
        return this
    }

    fun getPaths(): List<String> {
        return searchFilePaths.orEmpty()
                .split(",")
                .map { it.trim() }
    }


    companion object {
        fun getInstance(): FilePathNavigatorState {
            return ServiceManager.getService(FilePathNavigatorState::class.java)
        }
    }
}