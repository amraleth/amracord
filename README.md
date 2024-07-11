# AmraCord

[![wakatime](https://wakatime.com/badge/gitlab/Amraleth/amracord.svg)](https://wakatime.com/badge/gitlab/Amraleth/amracord)

AmraCord is a very simple collection of classes for Discord Bot development
using [JDA](https://github.com/discord-jda/JDA). A lot of trivial tasks have been simplified for faster and easier bot
development.

## Setup

1. Add the repository

```kotlin
maven {
    url = uri("https://repo.amraleth.xyz/repository/amracord/")
}
```

2Add the dependency

```kotlin
implementation("xyz.amraleth:AmraCord:VERSION")
```

## Features

### Module system

In AmraCord all functionality is decided by so-called modules. Each module has a main class which is annotated with
the ``@Module`` annotation. It holds various meta-data required by the module loader. Furthermore, each module is
required to implement the ``CustomModule`` interface, which provides the ``initModule()`` method. It is the entrypoint
method for each module. For example:

```java
import module.services.noloy.nolcord.CustomModule;
import module.services.noloy.nolcord.Module;


@Module(moduleId = "example", moduleName = "Example Module", version = "1.0", description = "An example module for previewing the module system")
public class ExampleModule implements CustomModule {

    // start method of the module
    @Override
    public void initModule() {

    }
}
```

Modules are not registered by default, therefore the ``ModuleRegistry`` is required:

```java
import exception.services.noloy.nolcord.NoModuleException;
import registry.services.noloy.nolcord.ModuleRegistry;

public class BotClass {
    private final ModuleRegistry moduleRegistry;

    public BotClass() {
        // initialise module registry
        this.moduleRegistry = new ModuleRegistry();
    }

    public void launchBot() throws NoModuleException {
        // add all modules
        this.moduleRegistry.addModule(new ExampleModule());
        
        /*
        Bot setup
         */

        // init the modules; should be called after the bot finished starting
        this.moduleRegistry.initModules();
    }
}

```

## Event system

In addition to the module system there is also an event system. It is primarily designed for use in combination with
the ``ModuleRegistry``. The first step is creating a listener class:

```java
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ExampleListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
    }
}
```

As with the module system, listeners aren't registered automatically.The ``EventRegistry#addEvent`` method requires two
parameters, the Module class and the listener class. Let's add the listener in the
modules ``initModule`` method:

```java
// start method of each module
@Override
public void initModule() {
    EventRegistry.addEvent(this, new ExampleListener());
}
```

The last step for configuring the event system is registering the listeners:

```java
public void launchBot() throws NoModuleException {
    this.moduleRegistry.addModule(new ExampleModule());

           /*
          Bot setup
          */

    this.moduleRegistry.initModules();

    EventRegistry.registerListeners(this.jda);
}
```

## Config system

The third big system is the config system, which provides an easy way for creating config ``json`` files. The first step
is creating the config class and the corresponding json file:

```java
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class BotConfig {
    @NotNull
    String mainGuildId;
}
```

config.json:

```json
{
  "mainGuildId": "1252345223323"
}
```

With the help of the ``ConfigProvider`` the ``config.json`` can be parsed to the ``BotConfig``:

```java
private final ModuleRegistry moduleRegistry;
private final ConfigProvider configProvider;

private final BotConfig botConfig;

public BotClass() {
    this.configProvider = new ConfigProvider();
    this.botConfig = this.configProvider.loadConfig("config.json", BotConfig.class);
    this.moduleRegistry = new ModuleRegistry();
}
```