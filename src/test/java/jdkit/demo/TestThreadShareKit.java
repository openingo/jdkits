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

package jdkit.demo;

import org.junit.Test;
import org.openingo.java.util.concurrent.ExecutorsX;
import org.openingo.jdkits.lang.ThreadShareKit;

import java.util.concurrent.ExecutorService;

/**
 * v
 *
 * @author Qicz
 * @since 2021/8/4 11:24
 */
public class TestThreadShareKit {

	@Test
	public void test() {
		ThreadShareKit.put("a","111");
		ThreadShareKit.put("222");
		ThreadShareKit.put("22211");
		System.out.println((String) ThreadShareKit.getRemove("a"));
		System.out.println((String) ThreadShareKit.getRemove());

		final ExecutorService executorService = ExecutorsX.newFixedThreadPool(3, 100);
		for (int i = 0; i < 100; i++) {
			executorService.submit(() -> {

				ThreadShareKit.put("default");
				ThreadShareKit.put("name", "named value");

				System.out.println((String)ThreadShareKit.getRemove());
				System.out.println((String)ThreadShareKit.getRemove("name"));
			});
		}

		System.out.println((String)ThreadShareKit.get());
	}
}
