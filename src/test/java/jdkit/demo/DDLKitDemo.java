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

package jdkit.demo;

import org.openingo.jdkits.DDLKit;

import java.util.ArrayList;
import java.util.List;

/**
 * DDLKit Demo
 *
 * @author Qicz
 */
public class DDLKitDemo {



    public static void main(String[] args) {
        List<DDLKit.Column> columns = new ArrayList<>();

        DDLKit.Column name = DDLKit.createColumn("name", DDLKit.VARCHAR, "名字", 20, "");
        columns.add(name);
        DDLKit.Column age = DDLKit.createSignedTinyIntColumn("age", "年龄", 4, 1);
        columns.add(age);
        DDLKit.Column addr = DDLKit.createColumn("addr", DDLKit.VARCHAR, "地址", 4, "");
        columns.add(addr);

        DDLKit.Table table = DDLKit.createTable("user", "用户");
        table.setCharset(DDLKit.CHARSET_UTF8);
        table.addColumns(columns);
        String ddl = table.tableDDL();

        System.out.println(table.tableDDL());

    }
}
