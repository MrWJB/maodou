package com.jbw.maodou.function;

import java.util.List;

/**
 * 函数式接口
 * @param <T>
 */
@FunctionalInterface
public interface IFuntionSum<T extends Number> {
    T sum(List<T> numbers);
}
