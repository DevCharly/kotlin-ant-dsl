# [Ant] plug-in for [Kobalt] build system

[![Build Status](https://travis-ci.org/DevCharly/kobalt-ant.svg?branch=master)](https://travis-ci.org/DevCharly/kobalt-ant)

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

  * [copy](http://ant.apache.org/manual/Tasks/copy.html) (partial)
  * [delete](http://ant.apache.org/manual/Tasks/delete.html) (partial)
  * [echo](http://ant.apache.org/manual/Tasks/echo.html)
  * [jar](http://ant.apache.org/manual/Tasks/jar.html) (partial)
  * [mkdir](http://ant.apache.org/manual/Tasks/mkdir.html)
  * [property](http://ant.apache.org/manual/Tasks/property.html) (partial)
  * [touch](http://ant.apache.org/manual/Tasks/touch.html) (partial)
  * [zip](http://ant.apache.org/manual/Tasks/zip.html) (partial)

[Kobalt]: http://beust.com/kobalt
[Ant]: http://ant.apache.org/
[Ant tasks]: http://ant.apache.org/manual/tasksoverview.html