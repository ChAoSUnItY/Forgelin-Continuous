package io.github.chaosunity.forgelin.preloader

import com.ibm.icu.impl.PluralRulesLoader.loader
import net.minecraftforge.common.ForgeVersion
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import org.apache.logging.log4j.LogManager

@IFMLLoadingPlugin.Name("Forgelin-Continuous")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
class ForgelinPlugin: IFMLLoadingPlugin {
    override fun getASMTransformerClass(): Array<String> {
        return arrayOf()
    }

    override fun getModContainerClass(): String? {
        return null
    }

    override fun getSetupClass(): String {
        return "io.github.chaosunity.forgelin.preloader.ForgelinSetup"
    }

    override fun injectData(data: MutableMap<String, Any>?) {}

    override fun getAccessTransformerClass(): String {
        return "io.github.chaosunity.forgelin.transformer.ForgelinTransformer"
    }
}