package itaka.intellij.plugin.json.navigator

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiReferenceBase


class FilePathReference(element: PsiElement, private val filePath: String) : PsiReferenceBase<PsiElement>(element) {

    override fun resolve(): PsiElement? {
        val baseDir = element.containingFile.virtualFile.parent
        val file = baseDir.findFileByRelativePath(filePath)
                ?: findFileByProjectPath()
                ?: return null

        return getPsiFile(file)
    }

    private fun getPsiFile(file: VirtualFile): PsiFile? {
        return PsiManager.getInstance(element.project).findFile(file)
    }

    private fun findFileByProjectPath(): VirtualFile? {
        return element.project.baseDir.findFileByRelativePath(filePath)
    }

    override fun getVariants(): Array<Any> {
        return arrayOf()
    }
}