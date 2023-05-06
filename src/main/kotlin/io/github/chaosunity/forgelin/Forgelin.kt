package io.github.chaosunity.forgelin

import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(
    modid = Forgelin.MOD_ID,
    name = Forgelin.MOD_NAME,
    version = Forgelin.MOD_VERSION,
    modLanguageAdapter = Forgelin.ADAPTER
)
object Forgelin {
    /**
     *  Testing feature, use this at your risk.
     */
    const val ADAPTER = "io.github.chaosunity.forgelin.KotlinAdapter"

    const val MOD_ID = "forgelin_continuous"
    const val MOD_NAME = "Forgelin Continuous"
    const val MOD_VERSION = "@version@"

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        Loader.instance().modList.forEach {
            ForgelinAutomaticEventSubscriber.subscribeAutomatic(it, event.asmData, FMLCommonHandler.instance().side)
        }
    }
}