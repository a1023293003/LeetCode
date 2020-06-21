package cn.bluesking.leetcode;

import cn.bluesking.leetcode.annotation.Problem;
import cn.bluesking.leetcode.annotation.Solution;
import cn.bluesking.leetcode.util.Utilities;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

/**
 * 通过读取项目中的类信息来更新 README.md 文件。
 *
 * @author 随心
 * @date 2020/6/20
 */
public class UpdateReadMe {

    public static final String PACKAGE_SEPARATOR = ".";
    public static final String PACKAGE_PATH_SEPARATOR = "/";

    private static void addClass(Set<Class<?>> classSet, String classQualifiedName) {
        try {
            classSet.add(Class.forName(classQualifiedName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // do nothing...
        }
    }

    private static void addClasses(Set<Class<?>> classSet, String packageName) throws URISyntaxException, IOException {
        String packagePath = packageName.replace(PACKAGE_SEPARATOR, PACKAGE_PATH_SEPARATOR);
        Enumeration<URL> urls = UpdateReadMe.class.getClassLoader().getResources(packagePath);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            String protocol = url.getProtocol();
            if ("file".equalsIgnoreCase(protocol)) {
                Optional.ofNullable(new File(url.toURI().getPath()))
                        .map(File::listFiles)
                        .map(Arrays::stream)
                        .orElseGet(Stream::empty)
                        .filter((file -> file.isDirectory() || (file.isFile() && file.getName().endsWith(".class"))))
                        .forEach(file -> {
                            if (file.isFile()) {
                                String className = removeFileSuffix(file);
                                addClass(classSet, packageName + PACKAGE_SEPARATOR + className);
                            } else {
                                addClass(classSet, packageName + PACKAGE_SEPARATOR + file.getName());
                            }
                        });
            }

        }
    }

    private static String removeFileSuffix(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf('.'));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String solutionPackageName = UpdateReadMe.class.getPackage().getName() + ".solution";
        Set<Class<?>> classSet = new HashSet<>();
        addClasses(classSet, solutionPackageName);
        classSet.stream()
                .filter(Utilities.withAnnotation(Problem.class))
                .map(ProblemContext::new)
                .sorted(Comparator.comparing(Utilities.of(ProblemContext::getProblem).andThen(Problem::id)))
                .forEach(problemContext -> {
                    Problem problem = problemContext.getProblem();
                    Optional<Solution> solutionOptional = Optional.ofNullable(problemContext.getBestExecuteTimeSolution());
                    System.out.printf("%d|[%s](%s)|[%s](%s)|%.2f|%.2f|%s%n",
                            problem.id(),
                            problem.title(), problem.url(),
                            "JAVA", problemContext.getSourceCodeUrl(),
                            solutionOptional.map(Solution::executionTimeBeatRate).orElse(-1.0),
                            solutionOptional.map(Solution::memoryConsumptionBeatRate).orElse(-1.0),
                            solutionOptional.map(Solution::submitDate).orElse(""));
                });
    }

}
