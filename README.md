### 总体概括

1. 设计数据库模型：根据系统需求，设计数据库模型，包括宠物表、用户表、领养申请表等。使用MySQL创建相应的表结构，并定义它们之间的关系。
2. 搭建后端环境：使用Spring Boot创建一个后端项目。配置数据库连接，引入相关依赖，如Spring Data JPA用于数据库操作。
3. 实现后端接口：在Spring Boot项目中编写控制器（Controller）和服务（Service）类，处理前端请求并与数据库进行交互。例如，实现用户注册、登录、宠物信息查询、领养申请提交等功能的接口。
4. 搭建前端环境：使用Vue CLI创建一个前端项目。安装Vue 3及相关依赖，如Vue Router用于路由管理和导航，Axios用于与后端接口进行数据交互。
5. 设计前端界面：根据系统需求，设计并实现用户界面，包括宠物列表、详情页面、用户注册登录等。使用Vue 3的组件化开发方式，拆分页面为多个可复用的组件，提高代码的可维护性和重用性。
6. 实现前后端交互：在前端项目中使用Axios发送HTTP请求，调用后端接口获取数据或提交表单数据。根据需要，处理后端返回的数据，并将其展示在前端界面上。
7. 进行系统集成和测试：将前端和后端项目进行集成，确保它们可以相互通信和配合工作。进行系统测试，验证各个功能的正确性和稳定性。
8. 部署和发布：将前端项目打包生成静态文件，将后端项目打包成可执行的JAR文件。将静态文件部署到Web服务器，如Nginx或Apache，将后端项目部署到Java应用服务器，如Tomcat或Jetty。

****

#### 数据库表：

```mysql
-- 创建用户表
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建宠物信息表
CREATE TABLE pets (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  species VARCHAR(50) NOT NULL,
  age INT NOT NULL,
  description TEXT,
  available BOOLEAN DEFAULT true,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建领养记录表
CREATE TABLE adoption_records (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  pet_id INT NOT NULL,
  adoption_date DATE NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
);

```

上述代码创建了三个表：`users`（用户表）、`pets`（宠物信息表）和`adoption_records`（领养记录表）。每个表都有一个自增的主键 `id` 字段，用于唯一标识每个记录。

`users` 表包含了用户的基本信息，包括用户名 (`username`)、密码 (`password`)、电子邮件 (`email`) 和创建时间 (`created_at`)。

`pets` 表包含了宠物的信息，包括宠物名字 (`name`)、种类 (`species`)、年龄 (`age`)、描述 (`description`)、是否可领养 (`available`)、创建时间 (`created_at`) 和更新时间 (`updated_at`)。

`adoption` 表用于记录用户领养宠物的信息，包括用户ID (`user_id`)、宠物ID (`pet_id`) 和领养日期 (`adoption_date`)。同时，`adoption` 表中的 `user_id` 和 `pet_id` 字段分别与 `users` 表和 `pets` 表的主键建立了外键关联。

#### 测试数据

```mysql
-- 插入用户表测试数据
INSERT INTO users (username, password, email) VALUES
  ('user1', 'password1', 'user1@example.com'),
  ('user2', 'password2', 'user2@example.com'),
  ('user3', 'password3', 'user3@example.com');

-- 插入宠物信息表测试数据
INSERT INTO pets (name, species, age, description) VALUES
  ('Fluffy', 'Cat', 2, 'A cute and playful cat'),
  ('Buddy', 'Dog', 3, 'A friendly and loyal dog'),
  ('Coco', 'Rabbit', 1, 'A fluffy and adorable rabbit');

-- 插入领养记录表测试数据
INSERT INTO adoption_records (user_id, pet_id, adoption_date) VALUES
  (1, 1, '2023-12-01'),
  (2, 2, '2023-11-15'),
  (3, 3, '2023-12-20');

```

```mysql
CREATE TABLE adopt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone INT(11) NOT NULL,
    petType VARCHAR(10) NOT NULL,
    reason TEXT NOT NULL
);
```

在这个表中，我们使用了以下字段：

- `id`：作为主键和自动递增的唯一标识符。
- `name`：姓名，使用`VARCHAR(50)`类型存储，最大长度为50。
- `age`：年龄，使用`INT`类型存储。
- `address`：地址，使用`VARCHAR(100)`类型存储，最大长度为100。
- `phone`：联系电话，使用`INT(11)`类型存储，最大长度为20。
- `petType`：宠物类型，使用`VARCHAR(10)`类型存储，最大长度为10。
- `reason`：申请理由，使用`TEXT`类型存储，可以存储较长的文本。

```mysql
CREATE TABLE adoption (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pet_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (pet_id) REFERENCES pet(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
