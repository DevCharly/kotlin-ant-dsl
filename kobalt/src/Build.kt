import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.bintray

val dsl = project {
	name = "kotlin-ant-dsl"
	group = "com.devcharly"
	artifactId = name
	version = "0.2"

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
    name = "kotlin-ant-dsl-generator"
    directory = "generator"

    dependencies {
        compile("org.apache.ant:ant:1.9.7")
        compile("org.ow2.asm:asm:5.1")
    }
}

val examples = project(dsl) {
	name = "kotlin-ant-dsl-examples"
	directory = "examples"
	sourceDirectories { path("src") }
}
