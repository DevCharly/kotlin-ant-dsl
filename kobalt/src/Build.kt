import com.beust.kobalt.*
import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.bintray

object Versions {
	val ant = "1.9.7"
	val asm = "5.1"
}

val dsl = project {
	name = "kotlin-ant-dsl"
	group = "com.devcharly"
	artifactId = name
	version = "0.5"

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
