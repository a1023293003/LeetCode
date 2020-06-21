package cn.bluesking.leetcode.annotation;

import cn.bluesking.leetcode.annotation.check.NotNull;
import cn.bluesking.leetcode.annotation.check.Required;
import cn.bluesking.leetcode.annotation.check.Unique;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 题目注解，提供题目序号、题目名称、题目链接等信息。
 *
 * @author 随心
 * @date 2020/6/20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Problem {

    /**
     * @return [int] 题目序号
     */
    @Unique
    @Required
    int id() default -1;

    /**
     * @return [String] 题目标题
     */
    @NotNull
    String title();

    /**
     * @return [String] 题目链接
     */
    @NotNull
    String url();

}
