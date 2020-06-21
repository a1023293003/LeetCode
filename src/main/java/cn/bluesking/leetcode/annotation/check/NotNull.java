package cn.bluesking.leetcode.annotation.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示不能为空。
 *
 * @author 随心
 * @date 2020/6/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
}
