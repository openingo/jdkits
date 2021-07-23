/*
 * MIT License
 *
 * Copyright (c) 2021 OpeningO Co.,Ltd.
 *
 *    https://openingo.org
 *    contactus(at)openingo.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.openingo.jdkits.lang;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * EnumKit
 *
 * @author Qicz
 * @since 2021/7/23 15:56
 */
public final class EnumKit {

	private EnumKit() {}

	/**
	 * 根据条件查找一个enum
	 * @param enumClass
	 * @param predicate
	 * @param defaultValue
	 * @param <E>
	 * @return 根据predicate匹配的enum或defaultValue
	 */
	public static <E> E find(Class<E> enumClass,
							 Predicate<? super E> predicate,
							 E defaultValue) {
		return Stream.of(enumClass.getEnumConstants()).filter(predicate).findFirst().orElse(defaultValue);
	}

	/**
	 * 根据条件查找一个enum
	 * @param enumClass
	 * @param predicate
	 * @param <E>
	 * @return 根据predicate匹配的enum或null
	 */
	public static <E> E find(Class<E> enumClass,
							 Predicate<? super E> predicate) {
		return find(enumClass, predicate, null);
	}
}
