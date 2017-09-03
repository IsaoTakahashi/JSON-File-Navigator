package itaka.intellij.plugin.json.navigator

import com.intellij.json.psi.impl.JsonFileImpl
import itaka.intellij.plugin.json.navigator.base.BaseFixtureTestCase

class FilePathReferenceTest : BaseFixtureTestCase() {

    fun testResolve() {
        myFixture.configureByFiles("test.json", "expected.json")

        val jsonStringLiteral = myFixture.file.findElementAt(30)?.parent!!
        val target = FilePathReference(jsonStringLiteral, "expected.json")

        val actual = (target.resolve() as JsonFileImpl).virtualFile.path
        assertEquals("/src/expected.json", actual)
    }
}