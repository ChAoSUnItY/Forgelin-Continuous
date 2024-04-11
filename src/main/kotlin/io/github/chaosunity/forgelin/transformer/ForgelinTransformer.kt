package io.github.chaosunity.forgelin.transformer

import com.google.common.collect.Multimap
import com.google.common.collect.MultimapBuilder
import net.minecraft.launchwrapper.IClassTransformer
import org.apache.logging.log4j.LogManager
import kotlin.reflect.KClass

@Suppress("unused")
class ForgelinTransformer : IClassTransformer {
    companion object {
        private val LOGGER = LogManager.getLogger("Forgelin-Continuous Transformer")
    }

    @Suppress("UnstableApiUsage")
    private val transformers: Multimap<String, (ByteArray) -> ByteArray> =
        MultimapBuilder.hashKeys(30).arrayListValues(1).build()

    init {
        LOGGER.info("Begin register to-be-transformed classes")
        // addTransformation(KtUByteTransformer::class)
        LOGGER.info("Skipping registration...")
        LOGGER.info("Registration for transformation is completed")
    }

    override fun transform(p0: String, p1: String, p2: ByteArray?): ByteArray? {
        val transformations = transformers.get(p1)
        
        // LOGGER.info("Transforming class (`${p1}` => obf: `${p0}`)...")
        
        var transformedBytes = p2 ?: return null
        
        for (transformation in transformations) {
            LOGGER.info("Transforming...")
            
            transformedBytes = transformation(transformedBytes)
        }
        
        return transformedBytes
    }

    private fun addTransformation(clazz: KClass<out Transformer>) {
        val classQualifiedName = clazz.qualifiedName
        
        LOGGER.info("Adding class $classQualifiedName to the transformation queue")
        val objectInstance = clazz.objectInstance
            ?: throw IllegalStateException("Internal transformation error: unable to get transformer `$classQualifiedName` instance")
        
        transformers.put(objectInstance.getTarget(), objectInstance::transform)
    }

    private fun addTransformation(name: String, transformation: (ByteArray) -> ByteArray) {
        LOGGER.info("Adding class $name to the transformation queue")
        transformers.put(name, transformation)
    }
}