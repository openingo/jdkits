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

package org.openingo.java.lang;

/**
 * StringBuilderX
 *
 * @author Qicz
 */
@SuppressWarnings("all")
public class StringBuilderX {

    private StringBuilder stringBuilder;

    /**
     * Constructs a string builder with no characters in it and an
     * initial capacity of 16 characters.
     */
    public StringBuilderX() {
        this(16);
    }

    /**
     * Constructs a string builder with no characters in it and an
     * initial capacity specified by the {@code capacity} argument.
     *
     * @param      capacity  the initial capacity.
     * @throws     NegativeArraySizeException  if the {@code capacity}
     *               argument is less than {@code 0}.
     */
    public StringBuilderX(int capacity) {
        stringBuilder = new StringBuilder(capacity);
    }

    /**
     * Constructs a string builder initialized to the contents of the
     * specified string. The initial capacity of the string builder is
     * {@code 16} plus the length of the string argument.
     *
     * @param   str   the initial contents of the buffer.
     */
    public StringBuilderX(String str) {
        this(str.length() + 16);
        this.stringBuilder.append(str);
    }

    /**
     * Constructs a string builder that contains the same characters
     * as the specified {@code CharSequence}. The initial capacity of
     * the string builder is {@code 16} plus the length of the
     * {@code CharSequence} argument.
     *
     * @param      seq   the sequence to copy.
     */
    public StringBuilderX(CharSequence seq) {
        this(seq.length() + 16);
        this.stringBuilder.append(seq);
    }

    public StringBuilderX append(Object obj) {
        return append(String.valueOf(obj));
    }

    public StringBuilderX append(String str) {
        return this.append(true, str);
    }

    /**
     * Appends the specified {@code StringBuffer} to this sequence.
     * <p>
     * The characters of the {@code StringBuffer} argument are appended,
     * in order, to this sequence, increasing the
     * length of this sequence by the length of the argument.
     * If {@code sb} is {@code null}, then the four characters
     * {@code "null"} are appended to this sequence.
     * <p>
     * Let <i>n</i> be the length of this character sequence just prior to
     * execution of the {@code append} method. Then the character at index
     * <i>k</i> in the new character sequence is equal to the character at
     * index <i>k</i> in the old character sequence, if <i>k</i> is less than
     * <i>n</i>; otherwise, it is equal to the character at index <i>k-n</i>
     * in the argument {@code sb}.
     *
     * @param   sb   the {@code StringBuffer} to append.
     * @return  a reference to this object.
     */
    public StringBuilderX append(StringBuffer sb) {
        return this.append(true, sb);
    }

    public StringBuilderX append(CharSequence s) {
        return this.append(true, s);
    }

    /**
     * @throws     IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX append(CharSequence s, int start, int end) {
        return this.append(true, s, start, end);
    }

    public StringBuilderX append(char[] str) {
        return this.append(true, str);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX append(char[] str, int offset, int len) {
        return this.append(true, str, offset, len);
    }

    public StringBuilderX append(boolean b) {
        return this.append(true, b);
    }

    public StringBuilderX append(char c) {
        return this.append(true, c);
    }

    public StringBuilderX append(int i) {
        return this.append(true, i);
    }

    public StringBuilderX append(long lng) {
        return this.append(true, lng);
    }

    public StringBuilderX append(float f) {
        return this.append(true, f);
    }

    public StringBuilderX append(double d) {
        return this.append(true, d);
    }

    /**
     * @since 1.5
     */
    public StringBuilderX appendCodePoint(int codePoint) {
        return this.appendCodePoint(true, codePoint);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX delete(int start, int end) {
        return this.delete(true, start, end);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX deleteCharAt(int index) {
        return this.deleteCharAt(true, index);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX replace(int start, int end, String str) {
        return this.replace(true, start, end, str);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int index, char[] str, int offset,
                                int len)
    {
        return this.insert(true, index, str, offset, len);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, Object obj) {
        return this.insert(true, offset, obj);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, String str) {
        return this.insert(true, offset, str);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, char[] str) {
        return this.insert(true, offset, str);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int dstOffset, CharSequence s) {
        return this.insert(true, dstOffset, s);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int dstOffset, CharSequence s,
                                int start, int end)
    {
        return this.insert(true, dstOffset, s, start, end);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, boolean b) {
        return this.insert(true, offset, b);
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, char c) {
        return this.insert(true, offset, c);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, int i) {
        return this.insert(true, offset, i);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, long l) {
        return this.insert(true, offset, l);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, float f) {
        return this.insert(true, offset, f);
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(int offset, double d) {
        return this.insert(true, offset, d);
    }

    public int indexOf(String str) {
        return this.stringBuilder.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return this.stringBuilder.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return this.stringBuilder.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return this.stringBuilder.lastIndexOf(str, fromIndex);
    }

    public StringBuilderX reverse() {
        this.stringBuilder.reverse();
        return this;
    }

    @Override
    public String toString() {
        // Create a copy, don't share the array
        return this.stringBuilder.toString();
    }

    // conditional

    public StringBuilderX append(boolean bool, Object obj) {
        if (!bool) {
            return this;
        }
        return append(String.valueOf(obj));
    }

    public StringBuilderX append(boolean bool, String str) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(str);
        return this;
    }

    /**
     * Appends the specified {@code StringBuffer} to this sequence.
     * <p>
     * The characters of the {@code StringBuffer} argument are appended,
     * in order, to this sequence, increasing the
     * length of this sequence by the length of the argument.
     * If {@code sb} is {@code null}, then the four characters
     * {@code "null"} are appended to this sequence.
     * <p>
     * Let <i>n</i> be the length of this character sequence just prior to
     * execution of the {@code append} method. Then the character at index
     * <i>k</i> in the new character sequence is equal to the character at
     * index <i>k</i> in the old character sequence, if <i>k</i> is less than
     * <i>n</i>; otherwise, it is equal to the character at index <i>k-n</i>
     * in the argument {@code sb}.
     *
     * @param   sb   the {@code StringBuffer} to append.
     * @return  a reference to this object.
     */
    public StringBuilderX append(boolean bool, StringBuffer sb) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(sb);
        return this;
    }

    public StringBuilderX append(boolean bool, CharSequence s) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(s);
        return this;
    }

    /**
     * @throws     IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX append(boolean bool, CharSequence s, int start, int end) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(s, start, end);
        return this;
    }

    public StringBuilderX append(boolean bool, char[] str) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX append(boolean bool, char[] str, int offset, int len) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(str, offset, len);
        return this;
    }

    public StringBuilderX append(boolean bool, boolean b) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(b);
        return this;
    }

    public StringBuilderX append(boolean bool, char c) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(c);
        return this;
    }

    public StringBuilderX append(boolean bool, int i) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(i);
        return this;
    }

    public StringBuilderX append(boolean bool, long lng) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(lng);
        return this;
    }

    public StringBuilderX append(boolean bool, float f) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(f);
        return this;
    }

    public StringBuilderX append(boolean bool, double d) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.append(d);
        return this;
    }

    /**
     * @since 1.5
     */
    public StringBuilderX appendCodePoint(boolean bool, int codePoint) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.appendCodePoint(codePoint);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX delete(boolean bool, int start, int end) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.delete(start, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX deleteCharAt(boolean bool, int index) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.deleteCharAt(index);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX replace(boolean bool,
                                  int start, int end, String str) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.replace(start, end, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool,
                                 int index, char[] str, int offset,
                                 int len)
    {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(index, str, offset, len);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, Object obj) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, obj);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, String str) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, str);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, char[] str) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, str);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int dstOffset, CharSequence s) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(dstOffset, s);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool,
                                 int dstOffset, CharSequence s,
                                 int start, int end) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, boolean b) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, b);
        return this;
    }

    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, char c) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, c);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, int i) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, i);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, long l) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, l);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, float f) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, f);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    public StringBuilderX insert(boolean bool, int offset, double d) {
        if (!bool) {
            return this;
        }
        this.stringBuilder.insert(offset, d);
        return this;
    }
}
