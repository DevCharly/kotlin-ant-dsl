import com.devcharly.kotlin.ant.*
import java.io.File

fun main(args: Array<String>) {
	// make basedirs used below
	File("_files_").mkdirs()
	File("_fileset_").mkdirs()
	File("_zip_").mkdirs()

	demoEcho()
	demoProperty()
	demoFiles()
	demoFileset()
	demoZip()
	demoTar()
	demoJar()
}

fun demoEcho() {
	Ant {
		echo("Hello World")
		echo {
			+"aa"
			+"bb"
			+"cc"
		}
		echo(level = EchoLevel.ERROR) {
			+"""
				111
				22
				3
			"""
		}
	}
}

fun demoProperty() {
	Ant {
		property("place", "World")
		echo("Hello ${p("place")}")
	}
}

fun demoFiles() {
	Ant(basedir = "_files_") {
		touch("file.txt")
		echo("content2\n", file = "file2.txt", append = true)
		copy("file.txt", todir = "dir", overwrite = true)
		copy("file2.txt", tofile = "dir/file2.txt")
		delete("file.txt")
	}
}

fun demoFileset() {
	Ant(basedir = "_fileset_", logLevel = LogLevel.VERBOSE) {
		mkdir("dir1")
		mkdir("dir2")
		touch("dir1/file1.java")
		touch("dir2/file2.java")
		touch("dir2/fileTest.java")

		copy(todir = "dir") {
			fileset("dir1")
			fileset("dir2") {
				include(name = "**/*.java")
				exclude(name = "**/*Test*")
			}
		}
	}
}

fun demoZip() {
	Ant(basedir = "_zip_") {
		echo("content1", file = "dir/file1.txt")
		echo("content2", file = "dir/file2.txt")

		zip("out1.zip", basedir = "dir")
		zip("out2.zip", basedir = "dir", includes = "file1.txt")
		zip("out3.zip") {
			fileset(dir = "dir", includes = "file2.txt")
		}
		zip("out4.zip") {
			zipfileset(dir = "dir", prefix = "pre-dir")
		}
	}
}

fun demoTar() {
	Ant(basedir = "_zip_", logLevel = LogLevel.VERBOSE) {
		tar("out1.tar") {
			tarfileset(dir = "dir", username = "user1", uid = 123, filemode = "600")
		}

		bzip2(src = "out1.tar", destfile = "out1.tar.bz2")
		gzip(src = "out1.tar", destfile = "out1.tar.gz")

		bunzip2(src = "out1.tar.bz2", dest = "out1b.tar")
		gunzip(src = "out1.tar.gz", dest = "out1g.tar")
	}
}

fun demoJar() {
	Ant(basedir = "_zip_") {
		jar(destfile = "out1.jar", basedir = "dir") {
			manifest {
				attribute("Main-Class", "com.myapp.Main")
				attribute("Class-Path", "common.jar")
			}

			service("javax.script.ScriptEngineFactory", "org.acme.PinkyLanguage")
			service("javax.script.ScriptEngineFactory", "org.acme.BrainLanguage")
		}
	}
}
