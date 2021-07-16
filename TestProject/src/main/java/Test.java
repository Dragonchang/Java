import cglib.LearnImpl;
import cglib.LogMethodInterceptor;
import interexteninter.implement;
import jdkproxy.ProxyFactory;
import jdkproxy.Task;
import jdkproxy.TaskImpl;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test {
    public static void testProxy() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Task task = (Task) ProxyFactory.newInstance(new TaskImpl());
        task.setData("Test");
        System.out.println("============");
        System.out.println(task.getCalData(5));


        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "com/sun/cglibproxy");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LearnImpl.class);
        enhancer.setCallback(new LogMethodInterceptor());
        LearnImpl realLearn = (LearnImpl) enhancer.create();
        realLearn.learnChinese();




    }

    void testSocket() {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("10.64.7.151"), 8987);
        } catch (SocketException e) {
            System.out.println("11111");
        } catch (UnknownHostException e) {
            System.out.println("222222");
        }catch (Exception e) {
            System.out.println("3333333");
        }
        System.out.println("chenggong----");
    }

    public static void main(String[] args) {
        //测试动态代理
        //testProxy();
        //testSocket();
        implement i = new implement();
        i.funA();
        i.funB();
    }
}
