package net.shadowfacts.forgelin

import net.minecraftforge.fml.common.FMLModContainer
import java.lang.reflect.Field
import java.lang.reflect.Method
import io.github.chaosunity.forgelin.KotlinAdapter as KtContinuousAdapter

class KotlinAdapter : KtContinuousAdapter() {
    override fun getNewInstance(
        container: FMLModContainer,
        objectClass: Class<*>,
        classLoader: ClassLoader,
        factoryMarkedAnnotation: Method?
    ): Any {
        LOGGER.debug("Redirecting getNewInstance method from ShadowFact's Forgelin")
        return super.getNewInstance(container, objectClass, classLoader, factoryMarkedAnnotation)
    }

    override fun setProxy(target: Field, proxyTarget: Class<*>, proxy: Any) {
        LOGGER.debug("Redirecting setProxy method from ShadowFact's Forgelin")
        super.setProxy(target, proxyTarget, proxy)
    }
}
