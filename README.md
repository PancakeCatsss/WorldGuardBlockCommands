# WorldGuardBlockCommands
Simple plugin to run commands in a certain worldguard region when a player walks over a certain block

Config
```
# unique name of worldguard region
# this plugin only works within that region
Worldguard-Region: "specialevent"

# commands to run when the player walks over certain blocks
# these commands are run as console, you can reference the %player%
Blocks:
  EMERALD_BLOCK:
    - "heal %player%"
    - "broadcast Yay %player% ran over emerald block"
  DIAMOND_BLOCK:
    - "feed %player%"
    - "msg %player% You are getting close, now go find an emerald block"
```
    
Command:
```/worldguardblockcommands reload```

Permission:
```worldguardblockcommands.command.reload```
