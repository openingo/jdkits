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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ThreadShareKit
 *
 * @author Qicz
 * @since 2021/7/27 16:18
 */
public final class ThreadShareKit {

	private static Logger log = LoggerFactory.getLogger(ThreadShareKit.class);

	private ThreadShareKit() {

	}

	private final static String DEFAULT_SHARE = "default-thread-share";
	private final static Map<ThreadShare<Object>, Object> MAPPING = new ConcurrentHashMap<>();

	private static ThreadShare<Object> getShareByName(String name) {
		return new ThreadShare<>(name);
	}

	public static <T> void put(T data) {
		put(DEFAULT_SHARE, data);
	}

	public static <T> T get() {
		return get(DEFAULT_SHARE);
	}

	public static <T> T getRemove() {
		return getRemove(DEFAULT_SHARE);
	}

	public static <T> void put(String name, T data) {
		log.info("put data with name {} data {}", name, data);
		MAPPING.put(getShareByName(name), data);
	}

	public static <T> T get(String name) {
		final ThreadShare<Object> share = getShareByName(name);
		if (!MAPPING.containsKey(share)) {
			log.info("name {} is not exist", name);
			return null;
		}
		final Object data = MAPPING.get(share);
		log.info("get data {} with name {}", data, name);
		return (T) data;
	}

	public static <T> T getRemove(String name) {
		final ThreadShare<Object> share = getShareByName(name);
		if (!MAPPING.containsKey(share)) {
			log.info("name {} is not exist", name);
			return null;
		}
		final Object data =  MAPPING.remove(share);
		log.info("getRemove data {} with name {}", data, name);
		return (T) data;
	}
}
