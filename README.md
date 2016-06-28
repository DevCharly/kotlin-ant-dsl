# Kotlin Ant DSL

[![Build Status](https://travis-ci.org/DevCharly/kotlin-ant-dsl.svg?branch=master)](https://travis-ci.org/DevCharly/kotlin-ant-dsl)

**This is project is work-in-progress.**

Kotlin Ant DSL allows using [Ant] tasks from Kotlin.

```kotlin
Ant {
    echo("Hello World")
    mkdir("dir1")
    copy(todir = "dir1") {
        fileset(dir = "dir2")
            include(name = "**/*.txt")
        }
    }
}
```


Define a function to use a custom Ant task:

```kotlin
fun Ant.myanttask(attr1: String, attr2: String) {
    MyAntTask().execute("myanttask") { task ->
        task.setAttr1(attr1)
        task.setAttr2(attr2)
    }
}
```

Execute custom Ant Task:

```kotlin
Ant {
    myanttask("value1", "value2")
}
```


## Supported Ant features

### Tasks

  * [bunzip2](http://ant.apache.org/manual/Tasks/unpack.html)
  * [bzip2](http://ant.apache.org/manual/Tasks/pack.html)
  * [copy](http://ant.apache.org/manual/Tasks/copy.html)
  * [delete](http://ant.apache.org/manual/Tasks/delete.html)
  * [echo](http://ant.apache.org/manual/Tasks/echo.html)
  * [gunzip](http://ant.apache.org/manual/Tasks/unpack.html)
  * [gzip](http://ant.apache.org/manual/Tasks/pack.html)
  * [jar](http://ant.apache.org/manual/Tasks/jar.html)
  * [mkdir](http://ant.apache.org/manual/Tasks/mkdir.html)
  * [property](http://ant.apache.org/manual/Tasks/property.html)
  * [tar](http://ant.apache.org/manual/Tasks/tar.html)
  * [touch](http://ant.apache.org/manual/Tasks/touch.html)
  * [zip](http://ant.apache.org/manual/Tasks/zip.html)

### Types

  * [DirSet](http://ant.apache.org/manual/Types/dirset.html)
  * [FileSet](http://ant.apache.org/manual/Types/fileset.html)
  * [PatternSet](http://ant.apache.org/manual/Types/patternset.html)
  * [TarFileSet](http://ant.apache.org/manual/Types/tarfileset.html)
  * [ZipFileSet](http://ant.apache.org/manual/Types/zipfileset.html)


## Who uses Kotlin Ant DSL?

  * [Kobalt build system Ant plug-in](https://github.com/DevCharly/kobalt-ant)


[Ant]: http://ant.apache.org/
[Ant tasks]: http://ant.apache.org/manual/tasksoverview.html