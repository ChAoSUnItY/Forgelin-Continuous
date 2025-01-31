package io.github.chaosunity.forgelin

import net.minecraftforge.fml.common.FMLModContainer
import net.minecraftforge.fml.common.ILanguageAdapter
import net.minecraftforge.fml.common.ModContainer
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.LogManager
import java.lang.reflect.Field
import java.lang.reflect.Method

open class KotlinAdapter : ILanguageAdapter {
    protected val LOGGER = LogManager.getLogger("KotlinAdapter")

    override fun getNewInstance(
        container: FMLModContainer,
        objectClass: Class<*>,
        classLoader: ClassLoader,
        factoryMarkedAnnotation: Method?
    ): Any {
        LOGGER.debug("FML has asked for ${objectClass.simpleName} to be constructed")
        try {
            // Ensure the class is initialized so kotlin objectInstance can be
            // retrieved safely
            Class.forName(objectClass.name, true, classLoader)
        } catch (e: AssertionError) {
            LOGGER.error("FML has failed to construct ${objectClass.simpleName}")
            throw e
        }
        return objectClass.kotlin.objectInstance ?: objectClass.getDeclaredConstructor().newInstance()
    }

    override fun supportsStatics(): Boolean =
        false

    override fun setProxy(target: Field, proxyTarget: Class<*>, proxy: Any) {
        LOGGER.debug("Setting proxy: ${target.declaringClass.simpleName}.${target.name} -> $proxy")

        // objectInstance is not null if it's a Kotlin object, so set the value on the object
        // if it is null, set the value on the static field
        target.set(proxyTarget.kotlin.objectInstance, proxy)
    }

    override fun setInternalProxies(mod: ModContainer?, side: Side?, loader: ClassLoader?) {
        // Nothing to do with this
    }
}