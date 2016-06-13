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
}
