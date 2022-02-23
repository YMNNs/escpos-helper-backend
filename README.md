# ESC/POS 打印小助手

轻松通过浏览器连接ESC/POS打印机，需要搭配[前端程序](https://github.com/ymnns/escpos-helper-front)使用

## 为什么需要后端程序？

要想向打印机中写入数据，就需要建立TCP Socket连接，而浏览器并没有这种API。

## 功能

当前版本支持以下的功能，未被勾选的功能将在后续版本实现

- [x] 随时随地打印文本（包括汉字）
- [x] 调整字符大小和对齐方式
- [x] 连接打印机（暂时只支持以太网）
- [x] 查阅历史记录
- [x] 通过链接快速设置
- [x] 测试设置有效性
- [x] 将设置导出为**印有二维码的纸条**:sparkles:
- [ ] 调整文本列表的顺序
- [ ] 标签模板
- [ ] 支持USB、串口和并口
- [ ] 打印二维码和图片

## 后端技术栈

+ SpringBoot
+ ESC/POS（内置工具类，封装了[手册](https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=2)中的大部分指令）

## 快速入门

### 编译

```shell
$ mvn clean install -Dmaven.test.skip=true
```

### 运行

找到打包好的jar

```shell
$ java -jar escpos-helper-backend-0.0.1-SNAPSHOT.jar
```

### 部署前端

请参阅[前端程序](https://github.com/ymnns/escpos-helper-front)

### 注意事项

您可以在`src/main/resources/application.properties`填写服务器端口号，默认设置8080端口。