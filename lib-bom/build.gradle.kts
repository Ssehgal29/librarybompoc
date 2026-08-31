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

// Publish the BOM as POM-only. JitPack rewrites group ids inside POMs but not inside
// Gradle module metadata, so shipping the .module file would leave constraints pointing
// at the un-rewritten group and consumers would resolve no versions from the BOM.
tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

// JitPack rewrites the BOM's own coordinates to com.github.<user>.<repo> but leaves the
// <dependencyManagement> section of the POM untouched, so the constraints must be written
// with the final JitPack group up front.
val bomConstraintGroup = "$publishGroup.librarybompoc"

dependencies {
    constraints {
        api("$bomConstraintGroup:lib-1:$publishVersion")
        api("$bomConstraintGroup:lib-2:$publishVersion")
        api("$bomConstraintGroup:lib-3:$publishVersion")
        api("$bomConstraintGroup:lib-4:$publishVersion")
        api("$bomConstraintGroup:lib-full:$publishVersion")
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
