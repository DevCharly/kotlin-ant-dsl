import com.beust.kobalt.*
import com.devcharly.kobalt.plugin.ant.antTask
import com.devcharly.kobalt.plugin.ant.echo

val plugins = plugins(
	file("../kobaltBuild/classes")
)

val project = project {
	antTask("echo") {
		echo("Hello World")
	}
}
