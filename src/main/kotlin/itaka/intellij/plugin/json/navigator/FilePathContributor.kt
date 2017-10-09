package itaka.intellij.plugin.json.navigator

import com.intellij.json.psi.JsonStringLiteral
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

class FilePathContributor : PsiReferenceContributor() {
    val state = FilePathNavigatorState.getInstance()

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(JsonStringLiteral::class.java),
                object : PsiReferenceProvider() {
                    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
                        return getFileReferences(element, state.getPaths()).toTypedArray()
                    }
                })
    }

}

val filePathPattern = Regex("[A-Za-z0-9\\.\\-_/]+\\.[A-z0-9]{1,10}")

fun getFileReferences(element: PsiElement, searchPaths: List<String>): List<FilePathReference> {
    val jsonStringLiteral = element as JsonStringLiteral

    return filePathPattern.findAll(jsonStringLiteral.value)
            .map { FilePathReference(element, it.value, searchPaths) }
            .toList()
}

