import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 */
class GitGocdVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        /*
         *  TODO
         *   1 - create the extension
         *   2 - create the tasks required
         *   3 - link everything into the build process
         */

        project.extensions.create("GitGocdVersion", GitGocdVersionPluginExtension, project)

    }
}
