package cn.bluesking.leetcode.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 工具类。
 *
 * @author 随心
 * @date 2020/6/21
 */
public final class Utilities {

    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 判断目标（Class、Method 或其他 AnnotatedElement 的子类）是否具有某个特定的注解。
     *
     * @param annotationClass [<code>{@code Class<T>}</code>] 待判断的注解类 Class
     * @param <T> 注解类型
     * @return [<code>{@code Predicate<AnnotatedElement>}</code>] 返回一个断言对象，如果目标具备指定注解，断言返回 true，
     * 否则返回 false。
     */
    public static <T extends Annotation> Predicate<AnnotatedElement> withAnnotation(Class<T> annotationClass) {
        return annotatedElement -> Optional.ofNullable(annotatedElement)
                .map(element -> element.getDeclaredAnnotation(annotationClass))
                .filter(Objects::nonNull)
                .isPresent();
    }

    public static <T, R> Function<T, R> of(Function<T, R> element) {
        return element;
    }

}
