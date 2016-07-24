# About
Orbs.io is yet another .io game like agar.io or slither.io or diep.io

# Requirements
**Backend**:
* Java 8
* Gradle
* Others are downloaded by gradle

**Frontend**:   
I cannot make a script for automated downloads. So, you have to install them manually
* Node.js
* Webpack
* Babel
* react.js
* rest.js

# Contribution guide
```bash
# ---- Clone the last version from github ---- #

git clone https://github.com/SigmaOne/orbs.io.git
cd orbs.io

# ---- Install all the dependencies ---- #

# Backend:
gradle build

# Frontend:
# There you can install them manually, or just install npm(node package manager) 
# and install them all using him
npm install babel webpack react rest

# ---- Compile assets ---- #

cd ./src/main/resources/static
webpack

# ---- Run server ---- #

cd ../../../..
gradle bootRun
```
