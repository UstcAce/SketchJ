package files;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Stream;

/**
 * Path表示的是一个目录名序列，其后还可以跟着一个文件名。
 * 注：文件系统的分隔符（类Unix文件系统是 / (正斜杠)，Windows是 \ (反斜杠)）
 * https://www.jianshu.com/p/2110abaee0ba
 */
public class PathBasics {

    // 路径中的第一个部件可以是根部件，例如 / 或 C:\ 。以根部件开始的是绝对路径；
    // 否则就是相对路径
    @Test
    public void test01() {
        // 绝对路径
        Path absolute = Paths.get("D:\\Javacodes\\SketchJ\\workspace\\src\\main\\java\\files\\PathBasics.java");
        // 相对路径
        Path relative = Paths.get("files\\PathBasics.java");

        System.out.println("absolute: " + absolute.toString());
        System.out.println("relative: " + relative.toString());

        // 返回该路径的最后一个部件，或者在该路径没有任何部件时，返回null
        System.out.println("File Last Name: " + absolute.getFileName().toString());

        // 返回父路径，或者在该路径没有父路径时，返回null。
        System.out.println("Parent File Name: " + absolute.getParent().toString());

        // 返回该路径的根部件，或者在该路径没有任何根部件时，返回null
        System.out.println("Absolute Root File Name:" + absolute.getRoot().toString());
        System.out.println("Relative Root File Name:" + relative.getRoot());
    }

    /**
     * 以下方法适用于处理中等长度的文本文件，如果要处理的文件长度比较大，或者是二进制文件，
     * 那么还是应该使用流或者杜读入器 / 写出器。
     */
    // 读文件
    @Test
    public void test02() throws IOException {
        Path path = Paths.get("D:\\Javacodes\\SketchJ\\workspace\\src\\main\\java\\files\\PathBasics.java");
        byte[] bytes = Files.readAllBytes(path);


        // 获取Java支持的全部字符集
        SortedMap<String,Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet())
        {
            // 输出字符集的别名
//            System.out.println(alias);
        }

        // 将文件当做字符串读入，可添加如下代码：
        Charset charset = Charset.forName("UTF-8");
        String content = new String(bytes, charset);

        System.out.println(content);
    }

    // 将文件当做行序列读入。
    @Test
    public void test03() throws IOException {
        Path path = Paths.get("D:\\Javacodes\\SketchJ\\workspace\\src\\main\\java\\files\\PathBasics.java");
        Charset charset = Charset.forName("UTF-8");
        List<String> lines = Files.readAllLines(path, charset);
        lines.forEach(System.out::println);
    }

    // 写文件
    @Test
    public void test04() throws IOException {
        // Files.write(path, content.getBytes(charset));写出一个字符串到文件中。
        Path path = Paths.get("D:\\Javacodes\\SketchJ\\workspace\\src\\main\\resources\\path_demo.txt");
        Charset charset = Charset.forName("UTF-8");

        Files.write(path, path.toString().getBytes(charset));

        // Files.write(path, content.getBytes(charset), StandardOpenOption.APPEND);向指定文件中追加内容：
        Files.write(path, "\n".getBytes(charset), StandardOpenOption.APPEND);
        Files.write(path, path.toString().getBytes(charset), StandardOpenOption.APPEND);

        // static Path write(Path path, Iterable<? extends CharSequence> contents, OpenOption options)
        // 将一个行的集合写出到文件中
        List<String> list = new ArrayList<>();
        list.add(path.toString() + ":1");
        list.add(path.toString() + ":2");
        list.add(path.toString() + ":3");

        Files.write(path, list);
        Files.write(path, list, StandardOpenOption.APPEND);
    }

    // static Path copy(Path from, Path to, CopyOption... options)
    // 将文件从一个位置复制到另一个位置，并返回to。
    @Test
    public void test05() throws IOException {
        Path path = Paths.get("D:\\Javacodes\\SketchJ\\workspace\\src\\main\\resources\\path_demo.txt");

        String copyPathStr = path.getParent().toString() + File.separator + "path_demo_copy.txt";

        Path pathCopy = Paths.get(copyPathStr);

        Path newPath = Files.copy(path, pathCopy);
        System.out.println("New Path:" + newPath.toString());
    }

    // 深度优先遍历目录树
    @Test
    public void test06() throws IOException {
        Path path = Paths.get("D:\\Javacodes\\SketchJ\\workspace\\src\\main\\java");

        Stream<Path> oneDepth = Files.walk(path, 1);

        oneDepth.forEach(System.out::println);

        Stream<Path> oneDepth2 = Files.walk(path, 1)
                .filter(path2 -> !path2.equals(path))
//                .map(Path::getFileName);
                .map(path::relativize);
        /**
         * basics
         * commons
         * concurrent
         * files
         */

        oneDepth2.forEach(System.out::println);
    }

    @Test
    public void test07() {
        Path path = Paths.get("D:\\Javacodes\\SketchJ\\workspace\\src\\main\\java");
        Path newPath = path.resolve("\\foo");
        System.out.println("newPath:" + newPath);

        Path newPath2 = path.resolve(path);
        System.out.println("newPath2:" + newPath2);
    }
}
