package xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author wangzi
 * @date 18/12/26 下午4:21.
 */
public class xmlUtil {

    static SAXReader reader = new SAXReader();

    public static void main(String[] args) throws Exception {
//        parseXml();
        Document document = reader.read(new File("/Users/ziwang/Desktop/test.xml"));
//        fun01(document);
        Element root = document.getRootElement();
        readDocument(root, 0);
    }

    public static void readDocument(Element element, int level) {
//        Document doc = null;
        try {
            headTab(element, level);
            List<Element> list = element.elements();
            //子节点不为空,继续递归
            if (list != null && list.size() != 0) {
                System.out.println();
                for (Element child : list) {
                    readDocument(child, level + 1);
                }
                tailTab(element, level);
            }
            else {
                //如果子节点为空,说明是叶子结点,否则是循环循环结束过来的普通结点,普通结点不需要输出内容
                printText(element, level + 1);
                tailTab(element, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 打印叶子结点的信息
     *
     * @param element 叶子结点
     * @param level   层级,0最高,用来决定tab的个数
     */
    private static void printText(Element element, int level) {
        if (null == element.getText() || element.getText().trim() == "" || element.getText().trim().length() == 0) {
            return;
        }
        System.out.print(element.getText().trim());
    }

    /**
     * 写element的开头标签
     *
     * @param element xml元素
     * @param level   层级,0最高,用来决定tab的个数
     */
    private static void headTab(Element element, int level) {
        for (int i = 0; i < level * 2; i++) {
            System.out.print("  ");
        }
        System.out.print("<" + element.getName());
        //未知属性名情况下
        List<Attribute> attributeList = element.attributes();
        for (Attribute attr : attributeList) {
            System.out.print(" " + attr.getName() + "=" + attr.getValue());
        }
        System.out.print(">");
    }

    /**
     * 写element的闭合标签
     *
     * @param element xml元素
     * @param level   层级,0最高,用来决定tab的个数
     */
    private static void tailTab(Element element, int level) {
        for (int i = 0; i < level * 2; i++) {
            System.out.print("  ");
        }
        System.out.print("</" + element.getName());
        System.out.println(">");
    }


    public static String parseXml() {
        String xmlResult = null;

        try {
            Document document = reader.read(new File("/Users/ziwang/Desktop/test.xml"));

            Element root = document.getRootElement();
            Element element = root.element("红楼梦");
            Attribute attr = element.attribute("id");
            element.remove(attr);
            element.addAttribute("作者", "wang");
            Element e = element.addElement("朝代");
            e.addText("清朝");
            element.addCDATA("红楼梦是中国四大名著之一");

            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(new File("/Users/ziwang/Desktop/test2.xml")), format);
            xmlWriter.write(document);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return xmlResult;
    }

    public static void fun01(Document document) throws TransformerException {

    }
}
