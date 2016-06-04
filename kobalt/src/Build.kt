import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.*

val project = project {
	name = "kobalt-ant"
	group = "com.devcharly.kobalt"
	artifactId = name
	version = "0.1"

	dependencies {
		compile("com.beust:kobalt-plugin-api:")
		compile("org.apache.ant:ant:1.9.7")
	}

	assemble {
		mavenJars {
		}
	}

/*
	bintray {
		publish = true
	}
*/
}
