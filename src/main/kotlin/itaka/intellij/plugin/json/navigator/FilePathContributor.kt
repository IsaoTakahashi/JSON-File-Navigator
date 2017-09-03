package itaka.intellij.plugin.json.navigator

import com.intellij.json.psi.JsonStringLiteral
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

class FilePathContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(JsonStringLiteral::class.java),
                object : PsiReferenceProvider() {
                    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
                        return getFileReferences(element).toTypedArray()
                    }
                })
    }

}

fun getFileReferences(element: PsiElement): List<FilePathReference> {
    val jsonStringLiteral = element as JsonStringLiteral
    val filePathPattern = Regex("[A-Za-z0-9\\.\\-_/]+\\.[A-z0-9]{1,10}")

    return filePathPattern.findAll(jsonStringLiteral.value)
            .map { FilePathReference(element, it.value) }
            .toList()
}

