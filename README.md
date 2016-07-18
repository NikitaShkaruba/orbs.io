# About
Orbs.io is yet another .io game like agar.io or slither.io or diep.io

# Requirements
* Java 8
* Gradle

# Installation
```bash
# ---- First of all install required packages ---- #
# Arch
sudo pacman -Suy java-runtime-common java-environment-common git gradle
# Ubuntu
sudo add-apt-repository ppa:cwchien/gradle
sudo apt-get update
sudo apt-get install default-jre default-jdk git git gradle 
# Windows
# Todo: add installation notes

# ---- Second of all clone dem git repo ---- #
git clone https://github.com/SigmaOne/orbs.io.git
cd orbs.io

# ---- And then just run application with one simple command ---- #
# build.gradle contains everything you need
gradle bootRun
```
