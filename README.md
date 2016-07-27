## About
Orbs.io is yet another .io game like agar.io or slither.io or diep.io

## Technologies used
* Java Spring - Application framework
* Gradle - Build tool
* PostgreSQL - Aatabase 
* Spook - Behaviour driven test framework which uses Groovy language
* Webpack - Frontend packaging tool, which uses Node.js
* React.js - Frontend framework

## Project dependencies
They are all handled in build.gradle. 
So, you can just run 'gradle bootRun', and then all the dependencies are going to be installed before the actual server boot
If you don't want to boot server, and just build all the assets, just run 'gradle buildAssets'

You can view dependencies in:  
Backend: build.gradle - under '// app dependencies' line  
Frontend: package.json - under "dependencies":" line

## How to run
```bash
# Clone the last version from github 
git clone https://github.com/SigmaOne/orbs.io.git
cd orbs.io

# Run gradle task to install all the dependencies and boot up the server
gradle bootRun
```

## Contribution rules
1. **Make a lot of short commits with descriptive messages**
  
  Bad git log:
  ```bash
  Start building this big ass game
  Add half a big ass game
  Finish this big ass game
  ```
  
  Good git log:
  ```bash
  Add interface to class Mage 
  Add logic to method makeFireball() in Mage.java and tests for it
  Add logic to method die() in Mage.java and tests for it
  Refactor Mage
  Add integration test for Game
  # ...
  ```
2. **Our methodology for working with git is git flow**

  Long story short: 
  * master - release branch 
  * develop - contains fully shippable version, from develop every developer starts his feature branch
  * feature/feature_name - Every developer works in his feature branch, and then, when feature is ready and version is shippable, he merges it with develop. We use git flow tool to limit ourself by this methodology rules.  
  
  How to use git flow:
  ```bash
  git clone https://github.com/SigmaOne/orbs.io.git
  git flow init
  git flow feature start user_statistic
  
  # write up some code
  git add --all
  git commit --verbose
  
  # optionally, you can publish your branch - push it to repository
  # git flow feature publish user_statistic
  
  # write up some code again
  git add --all
  git commit --verbose
  
  git pull
  git flow feature finish user_statistic
  git push
  ```
3. **Commit message format**
  ```bash
  Add registration logic # Main message 

  Changelog:  # Mandatory and very detailed changelog! For every changed line of code!
    # Log entries may be structured - typical
    * Add tests to Foo  
    * Rename class Foo to Bar
    * Refactor doSomething in Foo
    # Or less structured(like feature description, but they should be in separate commit ideally)
    * Class Foo now handles all the database connection
    * Class Foo now can registrate new users
  ```
  Between main message and changelog you can inser description if needed 
4. **Think before you commit something**

  Don't commit trash to repository, add trash files to .gitignore

5. **Java code style guide**  
  [here](https://google.github.io/styleguide/javaguide.html)
6. **Javascript code style guide**  
  [here](https://github.com/airbnb/javascript)
