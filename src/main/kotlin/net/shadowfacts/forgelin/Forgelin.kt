package net.shadowfacts.forgelin

import io.github.chaosunity.forgelin.ForgelinAutomaticEventSubscriber
import net.minecraftforge.fml.common.*
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.versioning.ArtifactVersion

@Mod(
    modid = Forgelin.MOD_ID,
    name = Forgelin.MOD_NAME,
    version = Forgelin.MOD_VERSION,
    modLanguageAdapter = Forgelin.ADAPTER
)
object Forgelin {
    const val ADAPTER = "net.shadowfacts.forgelin.KotlinAdapter"
    const val MOD_ID = "forgelin"
    const val MOD_NAME = "Shadowfacts' Forgelin (Bundled)"
    const val MOD_VERSION = "1.8.4"

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        Loader.instance().modList.forEach {
            ForgelinAutomaticEventSubscriber.subscribeAutomatic(it, event.asmData, FMLCommonHandler.instance().side)
        }
    }
}