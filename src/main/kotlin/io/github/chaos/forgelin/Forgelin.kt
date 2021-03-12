package io.github.chaos.forgelin

import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(
    modid = Forgelin.MOD_ID,
    name = Forgelin.MOD_NAME,
    version = Forgelin.MOD_VERSION,
    modLanguageAdapter = "io.github.chaos.forgelin.KotlinAdapter"
)
object Forgelin {
    const val MOD_ID = "forgelin_continuous"
    const val MOD_NAME = "Forgelin Continuous"
    const val MOD_VERSION = "1.4.30.1"

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        Loader.instance().modList.forEach {
            ForgelinAutomaticEventSubscriber.subscribeAutomatic(it, event.asmData, FMLCommonHandler.instance().side)
        }
    }
}