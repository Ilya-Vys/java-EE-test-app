package example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;


import java.io.File;

public class Application {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        //создаем контекст

        StandardContext context = (StandardContext) tomcat.addWebapp("/",
                new File("web/").getAbsolutePath());

        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                new File("target/classes").getAbsolutePath(), "/"));
        context.setResources(resources);
        tomcat.start();
        tomcat.getServer().await();
    }

}
