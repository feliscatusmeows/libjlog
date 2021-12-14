# libjlog
An easy-to-use logging library for humans

## Installing
In order to install this library, you have to compile it first. After you compiled the library, a jarfile should pop up in the ./build/libs folder. That jarfile has all the stuff you need

## Building
1. Make sure that `java -version` yields java 16 or above.
2. Clone the repository.
3. Build the project
   - On linux: `./gradlew build`
   - On windows: `gradlew.bat build`
4. Find compiled jarfile in ./build/libs

## Usage
To make a new logger, either use `Logger.getDefault(String);` or `new DefaultLogger();`. The prefix will be assumed on the class calling the constructor.

### Examples

```java
Logger logger = Logger.getDefault("Main");
logger.info("Hello world\nI support newlines, and I am proud of that");
logger.warning("You should probably not do that");
logger.error("Something terribly just happened.")
logger.debug("Breakpoint 1");
```

It is not recommended to make multiple instances of the same logger. While it will not cause any issues, it is generally a bad practise to do so.

## Support
If you find any bugs or have feature suggestions, feel free to open an issue.