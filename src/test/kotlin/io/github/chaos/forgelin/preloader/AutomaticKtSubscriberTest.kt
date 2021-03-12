package io.github.chaos.forgelin.preloader

import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(
    modid = AutomaticKtSubscriberTest.MOD_ID,
    modLanguageAdapter = "io.github.chaos.forgelin.KotlinAdapter"
)
object AutomaticKtSubscriberTest {
    const val MOD_ID = "ktsubtest"

    @Mod.EventBusSubscriber(modid = MOD_ID)
    object EventSubscriber {
        @SubscribeEvent
        fun onRightClickBlock(event: PlayerInteractEvent.RightClickBlock) {
            println("Automatic KT subscriber: Right click ${event.pos}")
        }

        @JvmStatic
        @SubscribeEvent
        fun onRightClickItem(event: PlayerInteractEvent.RightClickItem) {
            println("Right click item")
        }
    }
}