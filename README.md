# jdkits
Java Develop Common Kits

![maven](https://img.shields.io/maven-central/v/org.openingo.kits/jdkits.svg)

```xml
<dependency>
  <groupId>org.openingo.kits</groupId>
  <artifactId>jdkits</artifactId>
  <version>new_version</version>
</dependency>
```

### DDLKit使用

> 默认使用InnoDB

##### 创建一个VARCHAR Column

```java
DDLKit.createColumn("name", DDLKit.VARCHAR, "名字", 20, "");
```

也可以这样

```java
DDLKit.createVarcharColumn("name", "名字", 20, "");
```

##### 创建一个INT Column

```java
DDLKit.createIntColumn("age", "年龄", 4, 1);
```

##### 创建有符号的 INT Column

```java
DDLKit.createSignedIntColumn("age", "年龄", 4, 1);
```

##### 修改DDL charset

```java
DDLKit.Table table = DDLKit.createTable("user", "用户");
table.setCharset(DDLKit.CHARSET_UTF8);
table.addColumns(columns);
String ddl = table.tableDDL();
```

> - DDLKit.CHARSET_UTF8 -> utf8
>
> - DDLKit.CHARSET_UTF8MB4 -> utf8mb4

##### 示例

```java
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
```

```sql
CREATE TABLE `user` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '索引',
`name` varchar(20) DEFAULT NULL COMMENT '名字',
`age` tinyint(4) NOT NULL DEFAULT '1' COMMENT '年龄',
`addr` varchar(4) DEFAULT NULL COMMENT '地址',
PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='用户';
```

