package itaka.intellij.plugin.json.navigator.base

import com.google.common.base.Preconditions
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import org.jetbrains.annotations.NotNull
import java.io.File
import java.net.URISyntaxException

abstract class BaseFixtureTestCase : LightCodeInsightFixtureTestCase() {
    @NotNull
    override fun getTestDataPath(): String {
        try {
            return tryToGetAbsolutePath()
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }

    }

    @NotNull
    @Throws(URISyntaxException::class)
    private fun tryToGetAbsolutePath(): String {
        val url = ClassLoader.getSystemClassLoader().getResource("testFiles/")
        Preconditions.checkNotNull(url)
        return File(url!!.toURI()).getAbsolutePath()
    }
}