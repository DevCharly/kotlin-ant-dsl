# [Ant] plug-in for [Kobalt] build system

Supports definition of per-project Kobalt tasks (similar to Ant targets)
and execution of [Ant tasks].

```kotlin
val project = project {
    antTask("hello") {
       echo("Hello World")
    }
}
```

The primary goal is support migration of Ant builds to Kobalt.
You can use your custom Ant tasks in Kobalt. Or use built-it Ant tasks.

**This is project is currently work-in-progress.**

## Supported Ant tasks

  * [echo](http://ant.apache.org/manual/Tasks/echo.html) (partial)
  * [property](http://ant.apache.org/manual/Tasks/property.html) (partial)

[Kobalt]: http://beust.com/kobalt
[Ant]: http://ant.apache.org/
[Ant tasks]: http://ant.apache.org/manual/tasksoverview.html