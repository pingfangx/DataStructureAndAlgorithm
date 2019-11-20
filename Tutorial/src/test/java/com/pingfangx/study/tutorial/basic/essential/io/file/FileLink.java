import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 如果要在本目录编译运行，注意删除包，并指定编码
 * <pre>
 *     javac -encoding utf-8 FileLink.java
 *     java FileLink <link> <target>
 * </pre>
 *
 * @author pingfangx
 * @date 2019/10/30
 */
public class FileLink {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("长度需要为 2");
            return;
        }
        createSymbolLink(args[0], args[1]);
    }

    private static void createSymbolLink(String link, String target) {
        try {
            System.out.printf("创建链接%n%s%n->%n%s%n", link, target);
            Files.createSymbolicLink(Paths.get(link), Paths.get(target));
        } catch (IOException | UnsupportedOperationException x) {
            System.err.println(x);
        }
    }
}
