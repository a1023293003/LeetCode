package cn.bluesking.leetcode;

import cn.bluesking.leetcode.annotation.Problem;
import cn.bluesking.leetcode.annotation.Solution;
import cn.bluesking.leetcode.annotation.check.NotNull;
import cn.bluesking.leetcode.util.Utilities;
import lombok.Getter;
import lombok.NonNull;

import java.awt.image.ImageProducer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 题目的上下文对象。
 *
 * @author 随心
 * @date 2020/6/21
 */
@Getter
class ProblemContext {

    private static final String URL_PREFIX = "https://github.com/a1023293003/LeetCode/tree/master/src/main/java/";
    private static final String URL_SEPARATOR = "/";
    private static final String URL_SUFFIX = ".java";

    private Class<?> solutionClass;

    private String sourceCodeUrl;

    private Problem problem;
    private List<Solution> solutions;

    private Solution bestExecuteTimeSolution;
    private Solution bestMemoryConsumptionSolution;

    public ProblemContext(@NonNull Class<?> solutionClass) {
        this.solutionClass = solutionClass;
        this.sourceCodeUrl = URL_PREFIX + solutionClass.getName()
                .replace(UpdateReadMe.PACKAGE_SEPARATOR, URL_SEPARATOR) + URL_SUFFIX;
        this.problem = solutionClass.getDeclaredAnnotation(Problem.class);
        this.solutions = Optional.ofNullable(solutionClass)
                .map(Class::getDeclaredMethods)
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .filter(Utilities.withAnnotation(Solution.class))
                .map(method -> method.getDeclaredAnnotation(Solution.class))
                .collect(Collectors.toList());
        this.bestExecuteTimeSolution = solutions.stream()
                .sorted(Comparator.comparingDouble(Solution::executionTimeBeatRate).reversed())
                .findFirst()
                .orElse(null);
        this.bestMemoryConsumptionSolution = solutions.stream()
                .sorted(Comparator.comparingDouble(Solution::memoryConsumptionBeatRate).reversed())
                .findFirst()
                .orElse(null);
    }

}
