import com.beust.kobalt.*
import com.devcharly.kobalt.plugin.ant.*
import com.devcharly.kotlin.ant.*

val plugins = plugins(
	file("../kobaltBuild/classes")
)

val project = project {
	name = "project"

	antTask("echo") {
		echo("Hello World")
		echo {
			+"aa"
			+"bb"
			+"cc"
		}
		echo(level = LogLevel.ERR) {
			+"""
				111
				22
				3
			"""
		}
	}

	antTask("property") {
		property("place", "World")
		echo("Hello ${p("place")}")
	}

	antTask("files", basedir = "_files_") {
		touch("file.txt")
		echo("content2\n", file = "file2.txt", append = true)
		copy("file.txt", todir = "dir", overwrite = true)
		copy("file2.txt", tofile = "dir/file2.txt")
		delete("file.txt")
	}

	antTask("fileset", basedir = "_fileset_") {
		mkdir("dir1")
		mkdir("dir2")
		touch("dir1/file1.java")
		touch("dir2/file2.java")
		touch("dir2/fileTest.java")

		copy(todir = "dir") {
			fileset("dir1")
			fileset("dir2") {
				include(name="**/*.java")
				exclude(name="**/*Test*")
			}
		}
	}

	antTask("zip", basedir = "_zip_") {
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

	antTask("tar", basedir = "_zip_", logLevel = LogLevel.VERBOSE) {
		tar("out1.tar") {
			tarfileset(dir = "dir", username = "user1", uid = 123, filemode = "600")
		}
	}

	antTask("jar", basedir = "_zip_") {
		jar("out1.jar", basedir = "dir") {
			manifest {
				attribute("Main-Class", "com.myapp.Main")
				attribute("Class-Path", "common.jar")
			}

			service("javax.script.ScriptEngineFactory",
					"org.acme.PinkyLanguage",
					"org.acme.BrainLanguage")
		}
	}
}
