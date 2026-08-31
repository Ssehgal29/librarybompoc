plugins {
    `java-platform`
    `maven-publish`
}

// BOM (Bill of Materials), Firebase-style: no code, only version constraints.
// Consumers import it with platform(...) and then declare lib modules without versions:
//
//   implementation(platform("com.github.Ssehgal29.librarybompoc:lib-bom:<version>"))
//   implementation("com.github.Ssehgal29.librarybompoc:lib-1")
//   implementation("com.github.Ssehgal29.librarybompoc:lib-3")

val publishGroup = project.property("PUBLISH_GROUP") as String
val publishVersion = project.property("PUBLISH_VERSION") as String

dependencies {
    constraints {
        api("$publishGroup:lib-1:$publishVersion")
        api("$publishGroup:lib-2:$publishVersion")
        api("$publishGroup:lib-3:$publishVersion")
        api("$publishGroup:lib-4:$publishVersion")
        api("$publishGroup:lib-full:$publishVersion")
    }
}

publishing {
    publications {
        register<MavenPublication>("bom") {
            groupId = publishGroup
            artifactId = "lib-bom"
            version = publishVersion
            from(components["javaPlatform"])
        }
    }
}
