import com.beust.kobalt.*
import com.devcharly.kobalt.plugin.ant.*

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
		echo("Hello "+p("place"))
	}
}
