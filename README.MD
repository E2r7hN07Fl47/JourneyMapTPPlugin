# JMTP (JourneyMap TelePort)

Plugin for Minecraft Spigot which allows teleporting through the dimensions (especially for using with JourneyMap)

## Commands

### /wtp
`/wtp {dim} {x} {y} {z}` - teleports you to provided dimension and coordinates

*dim*, *x*, *y*, *z* - must be **signed int**


### /wtp_version
`/wtp_version` - prints version of plugin


### /wtp_dims_list
`/wtp_dims_list` - prints all avaliable dimensions


## Tested server cores

- ❌ Craftbukkit version git-Bukkit-1.8.1-R4-17-g04a14f7-b1493jnks (MC: 1.0.0) (1.0.0-R1-SNAPSHOT)
- ✅ CraftBukkit version git-Bukkit-1.1-R4-23-gb2d2dba (MC: 1.1) (Implementing API version 1.1-R5-SNAPSHOT)
- ✅ CraftBukkit version git-Bukkit-jenkins-CraftBukkit-183 (MC: 1.2.5) (1.2.5-R5.0-MCPC-SNAPSHOT)
- ✅ CraftBukkit version git-Bukkit-1.2.5-R5.0-1-g4d45a19-b2266jnks (MC: 1.2.5) (1.2.5-R5.1-SNAPSHOT)
- ✅ CraftBukkit version git-Spigot-959 (MC: 1.5.2) (1.5.2-R1.1-SNAPSHOT)
- ❌ CraftBukkit version git-MCPC-Plus-jenkins-MCPC-Plus-507 (MC: 1.5.2) (1.5.2-R0.2-SNAPSHOT)
- ❌ Cauldron-MCPC-Plus version git-Cauldron-MCPC-Plus-1.6.4-1.965.01.205 (MC: 1.6.4) (1.6.4-R2.1-SNAPSHOT)
- ❌ MCPC+ version git-MCPC-Plus-jenkins-MCPC-Plus-164-251 (MC: 1.6.4) (1.6.4-R2.1-SNAPSHOT)
- ✅ CraftBukkit version git-Spigot-1.7.9-R0.2-208-ge0f2e95 (MC: 1.7.10) (1.7.10-R0.1-SNAPSHOT)
- ✅ CraftBukkit version git-Bukkit-1.7.9-R0.2-20-g0b2ed13-b3108jnks (MC: 1.7.10) (1.7.10-R0.1-SNAPSHOT)
- ✅ Cauldron version 1.7.10-1614.58 (MC: 1.7.10) (1.7.10-R0.1-SNAPSHOT)
- ✅ KCauldron version 1.7.10-1492.152 (MC: 1.7.10) (1.7.10-R0.1-SNAPSHOT)
- ✅ CatServer version CatServer-dae70adc (MC: 1.12.2) (1.12.2-R0.1-SpigotAPI)
- ✅ CraftBukkit version git-Spigot-79a30d7-f4830a1 (MC: 1.12.2) (1.12.2-R0.1-SNAPSHOT)
- ✅ Paper version git-Paper-1620 (MC: 1.12.2) (1.12.2-R0.1-SNAPSHOT)
- ✅ Cardboard version git-7ae8ebd (MC: 1.18.2) (1.18.2-R0.1-SNAPSHOT) on **Fabric**
- ✅ Cardboard version git-6841632 (MC: 1.18.2) (1.18.2-R0.1-SNAPSHOT) on **Fabric**
- ❌ Cardboard version git-7ae8ebd (MC: 1.18.2) (1.18.2-R0.1-SNAPSHOT) on **Quilt**
- ❌ Cardboard version git-6841632 (MC: 1.18.2) (1.18.2-R0.1-SNAPSHOT) on **Quilt**
- ✅ CraftBukkit version 3608-Spigot-6198b5a-b5aa0be (MC: 1.19.2) (1.19.2-R0.1-SNAPSHOT)
- ✅ Paper version git-Paper-333 (Git: eec64a4) (MC: 1.19.3) (1.19.3-R0.1-SNAPSHOT)


### Why not working

- 1.0.0 has issues with logger
- MCPC+ (and Cauldron before 1.7.x) have issues with plugin remapping
- Cardboard on Quilt has problems with Fabric version detection (could be fixed by Cardboard devs in future)
