package example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;


import java.io.File;

public class Application {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        //создаем контекст

        Context context = tomcat.addContext("/",
                new File(".").getAbsolutePath());

        String servletName = "ExampleServlet";
        //привязываем к контексту сервлет
        Tomcat.addServlet(context, servletName, new ExampleServlet());
        //связываем в контекст URL и сервлет
        context.addServletMappingDecoded("/main", servletName);
        tomcat.start();
        tomcat.getServer().await();
    }

}
