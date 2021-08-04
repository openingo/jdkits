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

package org.openingo.java.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * NamedThreadLocalKit
 *
 * @author Qicz
 * @since 2021/7/27 16:18
 */
public final class NamedThreadLocalKit {

	private static Logger log = LoggerFactory.getLogger(NamedThreadLocalKit.class);

	private NamedThreadLocalKit() {

	}

	private final static String DEFAULT_THREAD_LOCAL = "default-thread-local";
	private final static Map<String, NamedThreadLocal<Object>> MAPPING = new ConcurrentHashMap<>();

	static {
		MAPPING.put(DEFAULT_THREAD_LOCAL, new NamedThreadLocal<>(DEFAULT_THREAD_LOCAL));
	}

	public static <T> void put(T data) {
		if (!(data instanceof String)) {
			put(DEFAULT_THREAD_LOCAL);
			return;
		}
		put(DEFAULT_THREAD_LOCAL, data);
	}

	public static <T> T get() {
		return get(DEFAULT_THREAD_LOCAL);
	}

	public static <T> T getRemove() {
		return getRemove(DEFAULT_THREAD_LOCAL);
	}

	public static <T> void put(String name, T data) {
		log.info("put data with name {} data {}", name, data);
		MAPPING.put(name, new NamedThreadLocal<Object>(name) {
			{
				set(data);
			}
		});
	}

	public static <T> T get(String name) {
		if (!MAPPING.containsKey(name)) {
			log.info("name {} is not exist", name);
			return null;
		}
		final Object data = MAPPING.get(name).get();
		log.info("get data {} with name {}", data, name);
		return (T) data;
	}

	public static <T> T getRemove(String name) {
		if (!MAPPING.containsKey(name)) {
			log.info("name {} is not exist", name);
			return null;
		}
		final Object data = MAPPING.get(name).getRemove();
		log.info("getRemove data {} with name {}", data, name);
		return (T) data;
	}
}
