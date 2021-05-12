package org.bytedeco.javacpp.tools;

import org.apache.maven.plugin.MojoExecutionException;

public class ParseMojo extends BuildMojo {
    public void execute() throws MojoExecutionException {
        this.generate = false;
        super.execute();
    }
}
