# 校园快递管理系统 - 后端服务

本项目为校园快递管理系统的后端服务部分，基于 Java 21 和 Spring Boot 3 搭建，并使用 MyBatis-Plus 简化数据操作。

## 核心技术栈

- **语言**：Java 21
- **框架**：Spring Boot 3.2.5
- **持久层框架**：MyBatis-Plus 3.5.5
- **数据库**：MySQL 8.0

## 已实现功能模块

后端已严格按照项目文档 `docs/05_前后端接口清单与字段约定.md` 中的规范完成以下核心模块：

1. **登录与用户模块 (`AuthController` / `UserService`)**
   - 包含简单的 Token 模拟鉴权登录
   - 支持当前用户信息查询
   - 支持系统全量用户查询

2. **收件模块 (`ReceiveRecordController` / `ReceiveRecordService`)**
   - 快递入库，自动生成 6 位取件码
   - 收件记录列表查询，普通用户个人收件查询
   - 出库核验（核验单号与取件码后状态变更为 `已出库`）

3. **发件模块 (`SendRequestController` / `SendRequestService`)**
   - 发件申请录入
   - 发件列表查询、个人发件记录查询
   - 管理员分配快递员任务
   - 快递员接收分配任务、确认揽件

4. **异常模块 (`ExceptionRecordController` / `ExceptionRecordService`)**
   - 异常单上报
   - 异常记录列表查询
   - 异常处理（记录结果和处理时间）

5. **大盘统计 (`DashboardController`)**
   - 获取各项核心业务的待处理数量概览供首页使用

## 数据库说明

- 在本目录下提供了 `schema.sql`，其中包含了全部的表结构定义和初始化测试账号数据。
- 默认连接的数据库为本地 3306 端口 `campus_express`，密码默认为 `123`（如果不同可以在 `src/main/resources/application.yml` 中修改）。

## 环境说明与启动方式

> **兼容性说明：** 为适配部分用户环境下较新的 Java 版本（如 JDK 26 可能不支持当前版本的 Lombok 插件），本项目已从源码中移除了 `@Data` 等 Lombok 注解，并采用了原生的 Getter/Setter 方法，这保证了代码在所有环境中的稳定编译和运行。

您可以使用本地的 IDE 工具（如 IDEA）直接运行 `CampusExpressApplication.java` 即可启动服务（默认运行在 8080 端口）。

如果本地没有全局配置 Maven 环境变量，可以使用本项目下提供的局部 Maven 工具包进行启动或测试：
```bash
./apache-maven-3.9.6/bin/mvn spring-boot:run
```
