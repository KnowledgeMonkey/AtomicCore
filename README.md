# AtomicCore

**AtomicCore** is a versatile Minecraft plugin designed to enhance your server with various commands and features. Built for Spigot 1.20.4, this plugin offers a wide range of functionalities, including custom gamemodes, enchantments, scoreboards, and more!

## Features

- **Custom Gamemode Commands**: Easily switch between gamemodes for yourself or other players.
- **Enchantments**: Add special enchantments to items with custom commands.
- **Scoreboards**: Display a custom scoreboard with server info when players join.
- **Discord Integration**: Provide your server's Discord link via commands and entity interactions.

## Commands

### `/gamemode`

- **Aliases**: `/gm`
- **Description**: Change your or another player's gamemode.
- **Usage**: `/gamemode <survival|creative|adventure|spectator> [player]`
- **Permissions**:
    - `gamemode`: Allows changing own gamemode.
    - `gamemode.other`: Allows changing another player's gamemode.

### `/enchantwithpig`

- **Description**: Enchant your held item with a special "Pig Shooter I" enchantment.
- **Usage**: `/enchantwithpig`
- **Permissions**: `enchantwithpig`

### `/discord`

- **Description**: Provides a clickable link to the server's Discord.
- **Usage**: `/discord`
- **Permissions**: `discord`

## Event Listeners

### Player Join

- **Description**: Displays a custom scoreboard to the player when they join the server.

### Entity Interaction

- **Description**: Sends a clickable Discord link to the player when they right-click an entity named "&9Discord".

## Installation

1. **Download the plugin**: Download the latest release from the [releases page](https://github.com/KnowledgeMonkey/AtomicCore/releases).
2. **Install the plugin**: Place the `AtomicCore-1.0.jar` file into your server's `plugins` directory.
3. **Start the server**: Restart or start your Minecraft server to load the plugin.

## Configuration

Currently, there are no external configuration files needed. All features are ready to use out of the box.

## Example Usage

### Gamemode Command

To change your own gamemode to creative, use:
```
/gamemode creative
```

To change another player's gamemode to spectator, use:
```
/gamemode spectator <player>
```

### Enchant With Pig Command

Hold the item you want to enchant and use:
```
/enchantwithpig
```

### Discord Command

Simply type:
```
/discord
```
to receive a clickable link to the server's Discord.


## Contact

For any questions or support, please join our [Discord server](https://discord.gg/84RkRb5r7H) or create an issue on GitHub.
