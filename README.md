# NavyMcPlugin
Navy the plugin that enchance navigation between lobbys and world on your Minecraft server

# Commands
```/navy help``` — *help menu*

```/navy list``` — *list of worlds defined in config.yml*

```/navy <visible_world>``` — *teleport world and coordinates defined in ```Overrides``` and ```Spawn```*


# Configuration
## Structure:
config.md
```yml
Overrides:
 <world_name>: <visible_name>
 ...

Spawn:
 <world_name>:
  x: <x_spawn>
  y: <y_spawn>
  z: <z_spawn>
  yaw: <player_yaw>
  pitch: <player_pitch>
 ...

```

## Explanation:
 * Overrides — How the world would be accessed via command
 * <world_name> — actual world name _(like in hierarchy)_
 * <visible_name> — Name expected and used by player (ex. Lobby)
 
 * Spawn * — Where the player would appear
 * <x_spawn> — X spawn location 
 * <y_spawn> — Y spawn location
 * <z_spawn> — Z spawn location

## Complete code example:
config.yml
```yml
Overrides:
 world: lobby # registering ./world as lobby
 minigames_lobby_world: minigames # So on
Spawn:
 world:
  x: 0
  y: 2
  z: 0
  yaw: 0
  pitch: 0
 minigames_lobby_world:
  x: 5
  y: 10
  z: 5
  yaw: 90
  pitch: 20
```

### Hope it helps.
