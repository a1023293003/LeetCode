package cn.bluesking.leetcode.annotation;

import cn.bluesking.leetcode.annotation.check.Required;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解决方案注解，提供题解运行时间、内存消耗和排名
 *
 * @author 随心
 * @date 2020/6/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Solution {

    /**
     * @return [double] 执行耗时，单位：毫秒。
     */
    @Required
    double executeTime();

    /**
     * @return [double] 内存消耗，单位：毫秒。
     */
    @Required
    double memoryConsumption();

    /**
     * @return [double] 执行耗时击败用户比例，单位：百分比。
     */
    @Required
    double executionTimeBeatRate();

    /**
     * @return [double] 内存消耗击败用户比例，单位：百分比。
     */
    @Required
    double memoryConsumptionBeatRate();

}
