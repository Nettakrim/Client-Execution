# Client Execution

required on both server and client (although it shouldnt crash anything if only client/only server has it)

adds a new command `/executeclient` for running client only commands from the server

the syntax is `/executeclient <targets> <command>`

the `/` is automatically included, eg `/executeclient @r soup:random` runs `/soup:random` from [Souper Secret Settings](https://modrinth.com/mod/souper-secret-settings) (as long as the client has it installed), and `/executeclient @p /calc` would run `//calc` from [WorldEdit](https://modrinth.com/plugin/worldedit)

it runs the command as if the client typed it themselves, so it can also run server side commands, but only if the player running the command has the permission level needed for that command. (the command itself has the same permission level as /execute)

you can also use `/commandlock` and `/commandunlock` to stop players running specific commands. For example, `/commandlock @a me` will stop anyone running `/me` (however, it would also block a `/message`, since it just checks if the entered command starts with any of the locks). command locks are reset on relog

### Notes for the plugin version:
the permission node is `client_execution.executeclient`, and it only works with specifically mentioned players and @a - so `/executeclient Steve soup:random` works, but `/executeclient @p soup:random` or `/execute as Steve run executeclient @s soup:random` do not! this is an important difference!

the permission nodes for command locking are `client_execution.commandlock` and `client_execution.commandunlock`