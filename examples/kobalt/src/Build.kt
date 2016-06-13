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
	}

	antTask("property") {
		property("place", "World")
		echo("Hello ${p("place")}")
	}

	antTask("files", basedir = "_files_") {
		touch("file.txt")
		copy("file.txt", todir = "dir", overwrite = true)
		copy("file.txt", tofile = "dir/file2.txt")
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
