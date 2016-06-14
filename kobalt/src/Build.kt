import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.*

val project = project {
	name = "kobalt-ant"
	group = "com.devcharly.kobalt"
	artifactId = name
	version = "0.1"

	dependencies {
		provided("com.beust:kobalt-plugin-api:")
		compile("org.apache.ant:ant:1.9.7")
	}

	assemble {
		// Kobalt plugin (includes ant.jar)
		mavenJars {
			jar {
				fatJar = true

				exclude("**/kotlin-stdlib-*.jar")
				exclude("**/kotlin-runtime-*.jar")
			}
		}

		// Kotlin AntBuilder only
		jar {
			name = "kotlin-antbuilder-$version.jar"

			exclude("**/com/devcharly/kobalt/**")
			exclude("**/kobalt-plugin.xml")
		}
	}


/*
	bintray {
		publish = true
	}
*/
}
