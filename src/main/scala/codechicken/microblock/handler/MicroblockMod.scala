package codechicken.microblock.handler

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent
import codechicken.microblock.MicroMaterialRegistry
import codechicken.microblock.DefaultContent
import codechicken.multipart.Tags
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import codechicken.microblock.ConfigContent
import codechicken.microblock.DefaultContent
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent
import scala.collection.JavaConversions._

@Mod(
  modid = "ForgeMicroblock",
  name = "Forge Microblocks",
  acceptedMinecraftVersions = "[1.7.10]",
  dependencies = "required-after:ForgeMultipart",
  version = Tags.VERSION,
  modLanguage = "scala"
)
object MicroblockMod {
  @EventHandler
  def preInit(event: FMLPreInitializationEvent) {
    MicroblockProxy.preInit(event.getModLog)
    ConfigContent.parse(event.getModConfigurationDirectory)
  }

  @EventHandler
  def init(event: FMLInitializationEvent) {
    MicroblockProxy.init()
    DefaultContent.load()
    ConfigContent.load()
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent) {
    MicroMaterialRegistry.setupIDMap()
    MicroblockProxy.postInit()
  }

  @EventHandler
  def beforeServerStart(event: FMLServerAboutToStartEvent) {
    MicroMaterialRegistry.setupIDMap()
  }

  @EventHandler
  def handleIMC(event: IMCEvent) {
    ConfigContent.handleIMC(event.getMessages)
  }
}
