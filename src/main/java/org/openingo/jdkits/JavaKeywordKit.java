/*
 * MIT License
 *
 * Copyright (c) 2020 OpeningO Co.,Ltd.
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

package org.openingo.jdkits;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Java关键字工具 JavaKeywordKit
 *
 * @author Qicz
 */
public final class JavaKeywordKit {

    private String[] keywordArray = {
            "abstract",
            "assert",
            "boolean",
            "break",
            "byte",
            "case",
            "catch",
            "char",
            "class",
            "const",
            "continue",
            "default",
            "do",
            "double",
            "else",
            "enum",
            "extends",
            "final",
            "finally",
            "float",
            "for",
            "goto",
            "if",
            "implements",
            "import",
            "instanceof",
            "int",
            "interface",
            "long",
            "native",
            "new",
            "package",
            "private",
            "protected",
            "public",
            "return",
            "strictfp",
            "short",
            "static",
            "super",
            "switch",
            "synchronized",
            "this",
            "throw",
            "throws",
            "transient",
            "try",
            "void",
            "volatile",
            "while"
    };

    private Set<String> set;

    public static final JavaKeywordKit javaKeywordKit = createSharedInstance();

    private static JavaKeywordKit createSharedInstance() {
        JavaKeywordKit jk = new JavaKeywordKit();
        jk.set = Collections.unmodifiableSet(jk.set);	// 共享对象不让修改
        return jk;
    }

    private JavaKeywordKit() {
        set = new HashSet<String>();
        for (String keyword : keywordArray) {
            set.add(keyword);
        }
    }

    public JavaKeywordKit addKeyword(String keyword) {
        if (StrKit.notBlank(keyword)) {
            set.add(keyword);
        }
        return this;
    }

    public JavaKeywordKit removeKeyword(String keyword) {
        set.remove(keyword);
        return this;
    }

    public boolean contains(String str) {
        return set.contains(str);
    }
}
