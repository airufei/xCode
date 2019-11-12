

-- ----------------------------
-- 生成方案
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_menu`;
CREATE TABLE `t_admin_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单地址',
  `isbutton` bigint(20) DEFAULT '0' COMMENT '是否button按钮 0不是 1是',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '删除标记 1删除 0正常',
  `remark` text COMMENT '备注',
  `fid` bigint(20) DEFAULT '-1' COMMENT '父级菜单ID',
  `level` int(11) DEFAULT '1' COMMENT '菜单等级',
  `isadmin` int(11) DEFAULT '0' COMMENT '菜单所属系统 后端菜单 0是 1前端菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- 生成方案
-- ----------------------------

-- ----------------------------
-- Table structure for `t_admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(角色id)',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '删除标记 1删除 0正常',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_admin_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(角色id)',
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色名称',
  `menuId` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '删除标记 1删除 0正常',
  `remark` text COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_admin_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user_role`;
CREATE TABLE `t_admin_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(角色id)',
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色名称',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '删除标记 1删除 0正常',
  `remark` text COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of t_admin_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_base_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_base_dict`;
CREATE TABLE `t_base_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dictKey` varchar(200) DEFAULT NULL COMMENT '值',
  `dictValue` varchar(200) DEFAULT NULL COMMENT '名称',
  `fId` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) DEFAULT '0' COMMENT '删除标记 1删除 0正常',
  `remark` text COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `fvalue` varchar(50) DEFAULT NULL COMMENT '父级字典值',
  `keyWords` text COMMENT '关键词',
  `type` varchar(50) DEFAULT NULL COMMENT '字典类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of t_base_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `t_base_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_base_log`;
CREATE TABLE `t_base_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` longtext COMMENT '日志内容',
  `logtype` varchar(200) DEFAULT NULL COMMENT '日志类型',
  `remark` text COMMENT '备注',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(2) unsigned DEFAULT '0' COMMENT '删除标记',
  `rqurl` text COMMENT '请求全路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of t_base_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_base_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user`;
CREATE TABLE `t_base_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `qq` varchar(12) DEFAULT NULL,
  `wechart` varchar(35) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  `remark` varchar(200) DEFAULT NULL,
  `type` int(1) unsigned zerofill DEFAULT '0' COMMENT '用户类型: 0是互联网用户，1是管理员用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='sss';

-- ----------------------------
-- Records of t_base_user
-- ----------------------------
INSERT INTO `t_base_user` VALUES ('1', 'admin', 'e99a18c428cb38d5f260853678922e03', '18', '199199688@qq.com', '', null, null, '', '2017-03-13 16:07:38', '2017-11-08 16:09:38', '0', '', '1');

-- ----------------------------
-- 生成方案
-- ----------------------------
DROP TABLE IF EXISTS `t_code_scheme`;
CREATE TABLE `t_code_scheme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `category` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '分类',
  `package_name` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '生成模块名',
  `sub_module_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '生成子模块名',
  `function_name` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '生成功能名',
  `function_name_simple` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '生成功能名（简写）',
  `function_author` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '生成功能作者',
  `table_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '表名',
  `table_id` bigint(20) DEFAULT NULL COMMENT '生成表编号',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `flag` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `module_page_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '页面模块',
  `sub_page_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '子模块',
  PRIMARY KEY (`id`),
  KEY `gen_scheme_del_flag` (`flag`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='生成方案';


-- ----------------------------
-- Records of t_code_scheme
-- ----------------------------



-- ----------------------------
-- Table structure for `t_code_table`
-- ----------------------------
DROP TABLE IF EXISTS `t_code_table`;
CREATE TABLE `t_code_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `comments` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `class_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '实体类名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `flag` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `gen_table_name` (`name`),
  KEY `gen_table_del_flag` (`flag`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务表';

-- ----------------------------
-- 业务表字段
-- ----------------------------

-- ----------------------------
-- Table structure for `t_code_table_column`
-- ----------------------------
DROP TABLE IF EXISTS `t_code_table_column`;
CREATE TABLE `t_code_table_column` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '归属表编号',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `comments` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `jdbc_type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '列的数据类型的字节长度',
  `java_type` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否主键',
  `is_null` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否可为空',
  `is_insert` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否为插入字段',
  `is_edit` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否编辑字段',
  `is_list` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否列表字段',
  `is_query` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否查询字段',
  `query_type` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）',
  `show_type` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）',
  `dict_type` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '字典类型',
  `settings` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '其它设置（扩展字段JSON）',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `flag` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  `table_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '表名称',
  `is_editPage` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '编辑字段',
  `isInsertRequiredField` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '插入必须字段 1 非必须0',
  `isUpdateRequiredField` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '插入必须字段 1 非必须0',
  `is_sort` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否排序 1排序，0不排序',
  PRIMARY KEY (`id`),
  KEY `gen_table_column_table_id` (`table_id`),
  KEY `gen_table_column_name` (`name`),
  KEY `gen_table_column_sort` (`sort`),
  KEY `gen_table_column_del_flag` (`flag`)
) ENGINE=InnoDB AUTO_INCREMENT=796 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务表字段';

-- ----------------------------
-- Records of t_code_table_column
-- ----------------------------

-- ----------------------------
-- 代码模板表
-- ----------------------------
DROP TABLE IF EXISTS `t_code_template`;
CREATE TABLE `t_code_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `category` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '分类',
  `file_path` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '生成文件路径',
  `file_name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '生成文件名',
  `content` text COLLATE utf8_bin COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `flag` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `gen_template_del_falg` (`flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='代码模板表';

-- ----------------------------
-- Records of t_code_template
-- ----------------------------
