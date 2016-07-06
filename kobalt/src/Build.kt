import com.beust.kobalt.*
import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.bintray

val dsl = project {
	name = "kotlin-ant-dsl"
	group = "com.devcharly"
	artifactId = name
	version = "0.3"

	dependencies {
		compile("org.apache.ant:ant:1.9.7")
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
		compile("org.apache.ant:ant:1.9.7")
		compile("org.ow2.asm:asm:5.1")
	}

	// this is necessary for the application directive
	assemble { jar {  } }

	application {
		mainClass = "com.devcharly.kotlin.ant.generator.MainKt"
	}
}

val examples = project(dsl) {
	name = "examples"
	directory = "examples"
	sourceDirectories { path("src") }
}
