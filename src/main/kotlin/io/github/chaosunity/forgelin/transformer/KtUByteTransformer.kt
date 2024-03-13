package io.github.chaosunity.forgelin.transformer

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode

object KtUByteTransformer: Transformer {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun getTarget(): String =
        UByteArray::class.qualifiedName!!
    
    override fun transform(originalClass: ByteArray): ByteArray {
        val cr = ClassReader(originalClass)
        val classNode = ClassNode()
        
        cr.accept(classNode, 0)
        
        val cw = ClassWriter(ClassWriter.COMPUTE_MAXS or ClassWriter.COMPUTE_FRAMES)
        
        classNode.accept(cw)
        
        val mw = cw.visitMethod(Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC or Opcodes.ACC_FINAL, "get-impl", "([BI)B", null, null)
        
        mw.visitCode()
        mw.visitVarInsn(Opcodes.ALOAD, 0)
        mw.visitVarInsn(Opcodes.ILOAD, 1)
        mw.visitMethodInsn(Opcodes.INVOKESTATIC, getTragetInternalName(), "get", "([BI)B", false)
        mw.visitInsn(Opcodes.IRETURN)
        mw.visitMaxs(-1, -1)
        mw.visitEnd()
        
        return cw.toByteArray()
    }
}