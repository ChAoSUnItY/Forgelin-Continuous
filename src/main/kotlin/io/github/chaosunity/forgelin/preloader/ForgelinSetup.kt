package io.github.chaosunity.forgelin.preloader

import net.minecraftforge.fml.relauncher.IFMLCallHook

@Suppress("unused")
class ForgelinSetup: IFMLCallHook {
    override fun injectData(data: MutableMap<String, Any>?) {
        val loader = data!!["classLoader"]!! as ClassLoader
        
        // Explicit class load for KotlinAdapter
        try {
            loader.loadClass("kotlin.text.Charsets")
            loader.loadClass("io.github.chaosunity.forgelin.KotlinAdapter")
        } catch (e: ClassNotFoundException) {
            // this should never happen
            throw RuntimeException("Couldn't find Forgelin langague adapter, this shouldn't be happening", e)
        }
    }
    
    override fun call(): Void? {
        return null
    }
}