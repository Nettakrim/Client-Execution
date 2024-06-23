# Client Execution

required on both server and client

adds a new command `/executeclient` for running client only commands from the server

the syntax is `/executeclient <targets> <command>`

the `/` is automatically included, eg `/executeclient @r soup:random` runs `/soup:random` from [Souper Secret Settings](https://modrinth.com/mod/souper-secret-settings) as long as the client has it installed (and `/executeclient @p /calc` would run `//calc` from [WorldEdit](https://modrinth.com/plugin/worldedit))

it runs the command as if the client typed it themselves, so it can also run server side commands, but only if the player running the command has the permission level needed for that command