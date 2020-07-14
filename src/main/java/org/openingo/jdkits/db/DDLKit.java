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

package org.openingo.jdkits.db;

import org.openingo.jdkits.validate.ValidateKit;

import java.util.*;

/**
 * DDLKit
 * TODO indexes
 *
 *  @author Qicz
 */
public final class DDLKit {

    private DDLKit(){}

    public static final String BIGINT = "bigint";
    public static final String VARCHAR = "varchar";
    public static final String TEXT = "text";
    public static final String TINYTEXT = "tinytext";
    public static final String INT = "int";
    public static final String TINYINT = "tinyint";
    public static final String CHARSET_UTF8MB4 = "utf8mb4";
    public static final String CHARSET_UTF8 = "utf8";

    public static class Table{
        // 表名
        private String tableName = null;
        // 表注释
        private String comment = null;
        // 表的所有列
        HashMap<String, Column> columnsMap = new LinkedHashMap<String, Column>();
        // 表的所有 primary key
        private List<Column> primaryKeys = new ArrayList<Column>();
        // 表的所有 unique Key
        private List<Column> uniqueKeys = new ArrayList<Column>();
        // 是否自动创建Id
        private Boolean generateId = true;
        // pretty
        private Boolean pretty = true;
        // charset
        private String charset = CHARSET_UTF8MB4;

        private Table(String tableName, String comment){
            this.tableName = tableName;
            if (!comment.startsWith("`")) {
                comment = "'" + comment;
            }
            if (!comment.endsWith("`")) {
                comment += "'";
            }
            this.comment = comment;
        }

        /**
         * 添加一列到表中
         * @param column ， 列
         * @return 返回添加列之后的Table
         */
        public Table addColumn(Column column){
            if (ValidateKit.isNull(column)) {
                return this;
            }
            if (!this.columnsMap.containsKey(column.columnName)) {
                this.columnsMap.put(column.columnName, column);
            }
            if (column.primaryKey) {
                this.primaryKeys.add(column);
            }
            if (column.uniqueKey) {
                this.uniqueKeys.add(column);
            }
            return this;
        }

        /**
         * 添加Columns
         * @param columns
         * @return 返回添加列之后的Table
         */
        public Table addColumns(List<Column> columns){
            if (ValidateKit.isNull(columns)) {
                return this;
            }

            for (Column column : columns) {
                this.addColumn(column);
            }
            return this;
        }

        /**
         * 是否自动生成Id
         * @param generateId
         */
        public void setGenerateId(Boolean generateId) {
            this.generateId = generateId;
        }

        /**
         * DDL Pretty
         * @param pretty
         */
        public void setPretty(Boolean pretty) {
            this.pretty = pretty;
        }

        /**
         * 设置编码
         * @param charset
         */
        public void setCharset(String charset) {
            this.charset = charset;
        }

        /**
         * 生成DDL
         * @return ddl
         */
        public String tableDDL(){
            StringBuilder sqlBuilder = new StringBuilder();
            String pretty = this.pretty ? "\n" :"";
            //start ddl
            sqlBuilder.append("CREATE TABLE `").append(this.tableName).append("` (").append(pretty);
            sqlBuilder.append(this.generateId ? "`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '索引',"+pretty : "");
            // all columns
            Iterator<String> keyIterator = this.columnsMap.keySet().iterator();
            int size = this.columnsMap.size();
            int idx = 0;
            while (keyIterator.hasNext()) {
                Object key = keyIterator.next();
                sqlBuilder.append(this.columnsMap.get(key).columnDDL());
                if (idx != (size - 1)) {
                    sqlBuilder.append(",").append(pretty);
                }
                idx++;
            }
            // all primary keys
            sqlBuilder.append(this.generateId ? ","+pretty+"PRIMARY KEY (`id`" : "");
            for (Column column : this.primaryKeys) {
                sqlBuilder.append(",").append(column.columnName);
            }
            sqlBuilder.append(this.generateId ? ")" : "");
            // all unique keys
            for (Column column : this.uniqueKeys) {
                sqlBuilder.append(","+pretty+"UNIQUE KEY").append(column.columnName).append("(").append(column.columnName).append(")");
            }
            //end ddl
            sqlBuilder.append(pretty).append(") ENGINE=InnoDB CHARSET=").append(charset).append(" COMMENT=").append(this.comment).append(";");
            return sqlBuilder.toString();
        }
    }

    public static class Column{
        // 列名
        private String columnName = null;
        // 列类型
        private String columnType = null;
        // 列大小
        private int columnSize = 10;
        // 默认值
        private StringBuilder defaultValue = new StringBuilder();
        // 列注释
        private String comment = null;
        // 是否primary key
        private boolean primaryKey = false;
        // 是否unique key
        private boolean uniqueKey = false;
        // 是否有符号
        private boolean unsigned = true;

        /**
         * 初始化Column
         * @param columnName 列名
         * @param columnType 列类型
         * @param comment 列注释
         * @param columnSize 列大小
         * @param defaultValue 默认值
         */
        public Column(String columnName,
                      String columnType,
                      String comment,
                      int columnSize,
                      String defaultValue) {
            this(columnName,
                columnType,
                true,
                comment,
                columnSize,
                defaultValue,
                false,
                false);
        }

        /**
         * 初始化Column
         * @param columnName 列名
         * @param columnType 列类型
         * @param unsigned 是否有符号
         * @param comment 列注释
         * @param columnSize 列大小
         * @param defaultValue 默认值
         * @param primaryKey 是否为primary key
         * @param uniqueKey 是否为unique key
         */
        public Column(String columnName,
                      String columnType,
                      boolean unsigned,
                      String comment,
                      int columnSize,
                      Object defaultValue,
                      boolean primaryKey,
                      boolean uniqueKey){
            if (!columnName.startsWith("`")) {
                columnName = "`" + columnName;
            }
            if (!columnName.endsWith("`")) {
                columnName += "`";
            }
            this.columnName = columnName;
            this.columnType = columnType;

            if (ValidateKit.isNull(defaultValue)) {
                this.defaultValue.append("DEFAULT NULL");
            }else{
                String value = defaultValue.toString();
                value = ("".equals(value)||"''".equals(value))?"''":"'"+value+"'";
                this.defaultValue.append("NOT NULL DEFAULT ").append(value);
            }

            if (!comment.startsWith("'")) {
                comment = "'" + comment;
            }
            if (!comment.endsWith("'")) {
                comment += "'";
            }
            this.unsigned = unsigned;
            this.comment = comment;
            this.columnSize = columnSize;
            this.primaryKey = primaryKey;
            this.uniqueKey = uniqueKey;
        }

        /**
         * column DDL
         */
        public String columnDDL(){
            return new StringBuilder()
                .append(this.columnName).append(" ")
                .append(this.columnType).append("(").append(this.columnSize).append(") ")
                .append(this.isNumType() ? "unsigned " : "")
                .append(this.defaultValue)
                .append(" COMMENT ").append(this.comment).toString();
        }

        /**
         * 判断当前类型是否为数字类型 int，tinyint，bigint
         * @return true是false否
         */
        private boolean isNumType() {
            return (INT.equals(this.columnType)
                || TINYINT.equals(this.columnType)
                || BIGINT.equals(this.columnType)) && this.unsigned;
        }
    }

    /**
     * 创建一个table，没有任何列
     * @param tableName
     * @param comment
     * @return
     */
    public static Table createTable(String tableName, String comment){
        return (new Table(tableName, comment));
    }

    /**
     * 创建一个table
     * @param tableName
     * @param comment
     * @param columns
     * @return ddl
     */
    public static String createTable(String tableName, String comment, List<Column> columns){
        return (new Table(tableName, comment).addColumns(columns)).tableDDL();
    }

    /**
     * 创建一个Column
     * @param columnName 列名
     * @param columnType 列类型
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     * @param primaryKey 是否为primary key
     * @param uniqueKey 是否为unique key
     */
    public static Column createColumn(String columnName, String columnType, String comment,
                                      int columnSize, Object defaultValue,
                                      boolean primaryKey, boolean uniqueKey){
        return (new Column(columnName, columnType, true, comment, columnSize, defaultValue, primaryKey, uniqueKey));
    }

    /**
     * 创建一个Unsigned Column
     * @param columnName 列名
     * @param columnType 列类型
     * @param unsigned 是否有符号
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     * @param primaryKey 是否为primary key
     * @param uniqueKey 是否为unique key
     */
    public static Column createColumn(String columnName, String columnType, boolean unsigned, String comment,
                                      int columnSize, Object defaultValue,
                                      boolean primaryKey, boolean uniqueKey){
        return (new Column(columnName, columnType, unsigned, comment, columnSize, defaultValue, primaryKey, uniqueKey));
    }

    /**
     * 创建一个Column
     * @param columnName 列名
     * @param columnType 列类型
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createColumn(String columnName, String columnType, String comment,
                  int columnSize, Object defaultValue) {
        return createColumn(columnName, columnType, comment, columnSize, defaultValue, false, false);
    }

    /**
     * 生成Primary Key
     * @param columnName 列名
     * @param columnType 列类型
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createPrimaryKey(String columnName, String columnType, String comment, int columnSize, Object defaultValue) {
        return createColumn(columnName, columnType, comment, columnSize, defaultValue, true, false);
    }

    /**
     * 生成 unique Key
     * @param columnName 列名
     * @param columnType 列类型
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createUniqueKey(String columnName, String columnType, String comment, int columnSize, Object defaultValue) {
        return createColumn(columnName, columnType, comment, columnSize, defaultValue, false, true);
    }

    /**
     * 创建一个Varchar Column
     * @param columnName 列名
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createVarcharColumn(String columnName, String comment,
                                             int columnSize, Object defaultValue) {
        return createColumn(columnName, VARCHAR, comment, columnSize, defaultValue, false, false);
    }

    /**
     * 创建一个Text Column
     * @param columnName 列名
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createTextColumn(String columnName, String comment,
                                             int columnSize, Object defaultValue) {
        return createColumn(columnName, TEXT, comment, columnSize, defaultValue, false, false);
    }

    /**
     * 创建一个TinyText Column
     * @param columnName 列名
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createTinyTextColumn(String columnName, String comment,
                                          int columnSize, Object defaultValue) {
        return createColumn(columnName, TINYTEXT, comment, columnSize, defaultValue, false, false);
    }

    /**
     * 创建一个Int Column
     * @param columnName 列名
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createIntColumn(String columnName, String comment,
                                              int columnSize, Object defaultValue) {
        return createColumn(columnName, INT, comment, columnSize, defaultValue, false, false);
    }

    /**
     * 创建一个 Signed Int Column
     * @param columnName 列名
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createSignedIntColumn(String columnName, String comment,
                                         int columnSize, Object defaultValue) {
        return createColumn(columnName, INT, false, comment, columnSize, defaultValue, false, false);
    }

    /**
     * 创建一个TinyInt Column
     * @param columnName 列名
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createTinyIntColumn(String columnName, String comment,
                                         int columnSize, Object defaultValue) {
        return createColumn(columnName, TINYINT, comment, columnSize, defaultValue, false, false);
    }

    /**
     * 创建一个 Signed TinyInt Column
     * @param columnName 列名
     * @param comment 列注释
     * @param columnSize 列大小
     * @param defaultValue 默认值
     */
    public static Column createSignedTinyIntColumn(String columnName, String comment,
                                                 int columnSize, Object defaultValue) {
        return createColumn(columnName, TINYINT, false, comment, columnSize, defaultValue, false, false);
    }
}
