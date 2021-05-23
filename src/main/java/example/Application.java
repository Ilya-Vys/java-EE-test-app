package example;

import example.utils.ConnectionManager;
import example.utils.FileUtils;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.log4j.Logger;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws LifecycleException {

        try {
            ConnectionManager manager = ConnectionManager.fromResourceProperties("/db.properties");
            try (Connection connection = manager.getConnection()) {
                FileUtils.executeSqlFile(connection, "db-init.sql");
            }
        } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

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
