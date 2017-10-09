package itaka.intellij.plugin.json.navigator

import itaka.intellij.plugin.json.navigator.base.BaseFixtureTestCase

class FilePathContributorTest : BaseFixtureTestCase() {
    fun testGetFileReferences() {
        myFixture.configureByFiles("test.json")

        val jsonStringLiteral = myFixture.file.findElementAt(30)?.parent!!
        val fileReferences = getFileReferences(jsonStringLiteral, emptyList())

        assertEquals(2, fileReferences.size)
    }
}