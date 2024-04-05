# Crypty
![badge](https://img.shields.io/github/v/release/VenteriaTech/Crypty)
[![badge](https://jitpack.io/v/VenteriaTech/Crypty.svg)](https://jitpack.io/#VenteriaTech/Crypty)
![badge](https://img.shields.io/github/downloads/VenteriaTech/Crypty/total)
![badge](https://img.shields.io/github/last-commit/VenteriaTech/Crypty)
![badge](https://img.shields.io/badge/platform-spigot%20%7C%20bungeecord-lightgrey)
[![badge](https://img.shields.io/discord/896466173166747650?label=discord)](https://discord.gg/wKGf7EDvRv)
[![badge](https://img.shields.io/github/license/VenteriaTech/Crypty)](https://github.com/VenteriaTech/Crypty/blob/master/LICENSE.txt)

**[JavaDoc 1.0](https://venteriatech.github.io/Crypty/1.0/)**

Do you want new economy part, on your sever? <br>
You found what you're looking for! <br>

## Table of contents

* [Getting started](#getting-started)
* [License](#license)

## Getting started

Make sure you reloaded maven or gradle in your project.


### Add Crypty to your project

[![badge](https://jitpack.io/v/VenteriaTech/Crypty.svg)](https://jitpack.io/#VenteriaTech/Crypty)

You need to add this dependency into your plugin, then look at under the dependencies example

<details>
    <summary>Maven</summary>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.VenteriaTech</groupId>
        <artifactId>Crypty</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
</details>

<details>
    <summary>Gradle</summary>

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.VenteriaTech:Crypty:VERSION'
}
```
</details>

## License
Crypty is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/VenteriaTech/Crypty/blob/master/LICENSE.txt) for more information.