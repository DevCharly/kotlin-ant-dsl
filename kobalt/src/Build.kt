import com.beust.kobalt.*
import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.bintray

object Versions {
	val ant = "1.10.1"
	val asm = "6.0"
}

val dsl = project {
	name = "kotlin-ant-dsl"
	group = "com.devcharly"
	artifactId = name
	version = "0.6"

	dependencies {
		compile("org.apache.ant:ant:${Versions.ant}")
	}

	assemble {
		mavenJars {
			jar {
			}
		}
	}

	bintray {
		publish = true
	}
}

val generator = project {
	name = "generator"
	directory = "generator"
	version = "1.0" // run task does not work without version because of a bug in Kobalt

	dependencies {
		compile("org.apache.ant:ant:${Versions.ant}")
		compile("org.ow2.asm:asm:${Versions.asm}")
	}

	// this is necessary for the application directive
	assemble { jar {  } }

	application {
		mainClass = "com.devcharly.kotlin.ant.generator.MainKt"
	}
}

val converter = project {
	name = "converter"
	directory = "converter"
}

val examples = project(dsl) {
	name = "examples"
	directory = "examples"
	sourceDirectories { path("src") }
}
