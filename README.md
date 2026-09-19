# WeBlog

前后端分离的个人博客系统，基于 **Spring Boot + Vue 3** 构建。支持文章发布、分类标签、归档、全文检索、文件上传与后台管理等能力。

> 仓库：[AGkite/We-Bolg](https://github.com/AGkite/We-Bolg)

---

## 功能特性

### 前台

- 文章列表与详情（Markdown 渲染、代码高亮）
- 分类 / 标签浏览与筛选
- 文章归档
- Lucene 站内全文检索（中文分词、关键词高亮）
- 博客基础信息展示与访问统计

### 后台

- 账号登录（JWT）
- 文章发布 / 编辑 / 删除
- 分类、标签管理
- 博客设置
- 仪表盘数据概览
- 文件上传（MinIO 对象存储）

---

## 技术栈

| 层级 | 技术 |
|------|------|
| 后端 | Java 8、Spring Boot 2.6.3、Spring Security、JWT |
| 持久化 | MyBatis-Plus、MySQL、HikariCP |
| 文档 | Knife4j（OpenAPI） |
| 搜索 | Apache Lucene 8 + SmartChineseAnalyzer |
| 存储 | MinIO |
| 前端 | Vue 3、Vite 4、Vue Router、Pinia |
| UI | Element Plus、Tailwind CSS、ECharts |
| 编辑器 | md-editor-v3 |

---

## 项目结构

```text
We-Bolg/
├── README.md
├── 系统架构/                      # 架构示意图
├── weblog-springboot/             # 后端（Maven 多模块）
│   ├── weblog-web/                # 启动入口 + 前台 API
│   ├── weblog-module-admin/       # 后台管理 API
│   ├── weblog-module-common/      # 公共实体、Mapper、工具
│   ├── weblog-module-jwt/         # JWT 认证
│   └── weblog-module-search/      # Lucene 全文检索
└── weblog-vue3/                   # 前端（Vue 3）
    ├── public/
    └── src/
        ├── api/                   # admin / frontend 接口封装
        ├── components/
        ├── composables/
        ├── layouts/               # 前台 / 后台布局
        ├── pages/                 # 页面
        ├── router/
        └── stores/
```

---

## 环境要求

- JDK 8+
- Maven 3.6+
- Node.js 16+（建议 18 LTS）
- MySQL 8.0+
- MinIO（用于图片 / 文件上传，可选但后台上传功能需要）

---

## 快速开始

### 1. 克隆仓库

```bash
git clone https://github.com/AGkite/We-Bolg.git
cd We-Bolg
```

### 2. 初始化数据库

1. 创建数据库（示例）：

```sql
CREATE DATABASE weblog DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

2. 导入初始化脚本：

```bash
mysql -u root -p weblog < weblog-springboot/weblog-web/src/main/resources/db/init.sql
```

脚本会创建业务表并写入示例数据与初始用户（见 `t_user`）。

### 3. 配置后端

编辑开发环境配置：

`weblog-springboot/weblog-web/src/main/resources/application-dev.yaml`

按本地环境修改：

- MySQL 连接地址、用户名、密码
- MinIO `endpoint` / `accessKey` / `secretKey` / `bucketName`
- Lucene 索引目录 `lucene.indexDir`（需使用本机可写路径）

JWT 相关配置在：

`weblog-springboot/weblog-web/src/main/resources/application.yaml`

> 生产环境请务必修改数据库密码、JWT `secret`、MinIO 密钥等敏感配置。

### 4. 启动后端

```bash
cd weblog-springboot
mvn clean package -DskipTests
cd weblog-web
mvn spring-boot:run
```

默认开发端口：**8080**

API 文档（Knife4j）启动后一般可通过：

- 前台文档：`http://localhost:8080/doc.html`
- 后台文档：以项目实际 Knife4j 分组为准

### 5. 启动前端

```bash
cd weblog-vue3
npm install
npm run dev
```

Vite 已将 `/api` 代理到 `http://localhost:8080`，可直接本地联调。

### 6. 访问地址

| 入口 | 地址 |
|------|------|
| 前台首页 | 前端开发服务器地址（Vite 默认多为 `http://localhost:5173`） |
| 后台登录 | `/admin/login` |
| 后端接口 | `http://localhost:8080` |

初始管理员用户名见数据库种子数据（`admin`）。密码为 BCrypt 加密存储于 `init.sql`，如无法登录请自行在库中重置或按项目约定密码登录。

---

## 常用脚本

### 后端

```bash
# 编译打包
cd weblog-springboot
mvn clean package -DskipTests

# 生产启动示例（需先配置 application-prod.yaml）
java -jar weblog-web/target/weblog-web-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

也可使用模块内脚本（如存在）：

`weblog-springboot/weblog-web/src/main/resources/shell/`

### 前端

```bash
cd weblog-vue3
npm run dev      # 开发
npm run build    # 构建
npm run preview  # 预览构建产物
```

---

## 模块说明

| 模块 | 说明 |
|------|------|
| `weblog-web` | Spring Boot 启动模块，对外提供前台接口与应用入口 |
| `weblog-module-admin` | 后台文章、分类、标签、用户、仪表盘、文件等管理能力 |
| `weblog-module-common` | DO / Mapper、统一返回、异常处理、公共配置 |
| `weblog-module-jwt` | 登录认证、Token 签发与校验过滤器 |
| `weblog-module-search` | Lucene 索引构建与检索 |

前端按业务拆分：

- `src/pages/frontend`：访客侧页面
- `src/pages/admin`：管理后台页面
- `src/api`：与后端接口对应的请求封装

---

## 配置要点

### 开发 / 生产 Profile

- 激活方式：`application.yaml` 中 `spring.profiles.active`
- 开发：`application-dev.yaml`
- 生产：`application-prod.yaml`

### 文件上传

后台上传依赖 MinIO。请先启动 MinIO 并创建对应 Bucket，再与配置文件保持一致。

### 全文检索

文章发布 / 更新 / 删除时会维护 Lucene 索引。请保证 `lucene.indexDir` 目录存在且进程可写。

---

## 架构示意

项目根目录 `系统架构/` 中包含系统架构相关图片，可结合源码理解模块划分与调用关系。

---

## 许可证

本仓库未单独声明开源许可证。使用、修改与分发请遵循原作者约定，并自行评估第三方依赖的许可要求。

---

## 致谢

项目基于前后端分离博客实践搭建，后端采用多模块 Spring Boot 架构，前端采用 Vue 3 生态。感谢相关开源社区提供的框架与组件支持。
