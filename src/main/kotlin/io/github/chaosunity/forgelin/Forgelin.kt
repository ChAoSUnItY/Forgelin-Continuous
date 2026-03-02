package io.github.chaosunity.forgelin

import net.minecraftforge.fml.common.*
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager

@Mod(
    modid = Forgelin.MOD_ID,
    name = Forgelin.MOD_NAME,
    version = Forgelin.MOD_VERSION,
    modLanguageAdapter = Forgelin.ADAPTER
)
object Forgelin {
    val LOGGER = LogManager.getLogger("Forgelin-Continuous")

    const val MOD_ID = "forgelin_continuous"
    const val MOD_NAME = "Forgelin Continuous"
    const val MOD_VERSION = "@version@"
    const val ADAPTER = "io.github.chaosunity.forgelin.KotlinAdapter"

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        Loader.instance().modList.forEach {
            ForgelinAutomaticEventSubscriber.subscribeAutomatic(it, event.asmData, FMLCommonHandler.instance().side)
        }
    }
}
