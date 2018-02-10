/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 90606
 Source Host           : localhost:5432
 Source Catalog        : event
 Source Schema         : ec

 Target Server Type    : PostgreSQL
 Target Server Version : 90606
 File Encoding         : 65001

 Date: 09/01/2018 15:25:06
*/


-- ----------------------------
-- Sequence structure for event_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "ec"."event_log_id_seq";
CREATE SEQUENCE "ec"."event_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for trigger_detail_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "ec"."trigger_detail_id_seq";
CREATE SEQUENCE "ec"."trigger_detail_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for trigger_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "ec"."trigger_id_seq";
CREATE SEQUENCE "ec"."trigger_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for trigger_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "ec"."trigger_log_id_seq";
CREATE SEQUENCE "ec"."trigger_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for dict_code
-- ----------------------------
DROP TABLE IF EXISTS "ec"."dict_code";
CREATE TABLE "ec"."dict_code" (
  "item_type" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "enable_flag" int2 NOT NULL DEFAULT 1,
  "description" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "item_code" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "parent_code" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "item_name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sort" int2 DEFAULT NULL,
  "item_value" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "ec"."dict_code"."item_type" IS '类型编码，关联数据字典主表type_code';
COMMENT ON COLUMN "ec"."dict_code"."enable_flag" IS '是否启用：0-禁用，1-启用（默认启用）';
COMMENT ON COLUMN "ec"."dict_code"."description" IS '描述';
COMMENT ON COLUMN "ec"."dict_code"."item_code" IS '字表项目编码，与parent_code 构成唯一键';
COMMENT ON COLUMN "ec"."dict_code"."parent_code" IS '父编码，配合本表的item_code';
COMMENT ON COLUMN "ec"."dict_code"."item_name" IS '项目名称';
COMMENT ON COLUMN "ec"."dict_code"."sort" IS '排序码';
COMMENT ON COLUMN "ec"."dict_code"."item_value" IS '项目 值';
COMMENT ON TABLE "ec"."dict_code" IS '字典字表';

-- ----------------------------
-- Records of dict_code
-- ----------------------------
INSERT INTO "ec"."dict_code" VALUES ('03', 1, '梯控对应设备类型编码', 'FAC_CALLING', NULL, NULL, NULL, '2016');
INSERT INTO "ec"."dict_code" VALUES ('04', 1, '人脸抓拍事件', 'EVENT_TYPE_FACE_CAP', '39999', '人脸抓拍', NULL, NULL);
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '信息发布组件MQ话题', 'MSG/EVENT/INFOSCREEN', NULL, '信息发布组件', NULL, '69999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '门禁组件MQ话题', 'MSG/EVENT/DOORACS', NULL, '门禁组件', NULL, '39999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '可视对讲组件MQ话题', 'MSG/EVENT/VIDEOINTERCOM', NULL, '可视对讲组件', NULL, '49999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '梯控组件话题MQ', 'MSG/EVENT/LIFTCONTROL', NULL, '梯控组件', NULL, '59999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '视频组件MQ话题', 'MSG/EVENT/CAMERA', NULL, '视频组件', NULL, '29999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '巡更组件MQ话题', 'MSG/EVENT/PATROLDEVICE', NULL, '巡更组件', NULL, '79999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '广播组件MQ话题', 'MSG/EVENT/BROADCAST', NULL, '广播组件', NULL, '89999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '事件组件MQ话题', 'MSG/EVENT/TRIGGERRESULT', NULL, '事件组件', NULL, '99999');
INSERT INTO "ec"."dict_code" VALUES ('01', 1, '停车场组件MQ话题', 'MSG/EVENT/PARKING', '', '停车场组件', NULL, '19999');
INSERT INTO "ec"."dict_code" VALUES ('02', 1, '联动方式与中文对应，页面展示需要', 'FAC_CALLING', NULL, '联动呼梯', NULL, 'MSG/EVENT/LIFTCONTROL');
INSERT INTO "ec"."dict_code" VALUES ('04', 1, '开门事件', 'EVENT_TYPE_OPEN_DOOR', '39999', '开门', NULL, NULL);

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS "ec"."dict_type";
CREATE TABLE "ec"."dict_type" (
  "type_code" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "type_name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "enable_flag" int2 NOT NULL DEFAULT 1,
  "description" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "ec"."dict_type"."type_code" IS '类型编码';
COMMENT ON COLUMN "ec"."dict_type"."type_name" IS '类型名称';
COMMENT ON COLUMN "ec"."dict_type"."enable_flag" IS '是否启用：0-禁用，1-启用（默认启用）';
COMMENT ON COLUMN "ec"."dict_type"."description" IS '描述';
COMMENT ON TABLE "ec"."dict_type" IS '字典主表';

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO "ec"."dict_type" VALUES ('01', 'component_topic', 1, '业务组件话题发送对应列表');
INSERT INTO "ec"."dict_type" VALUES ('02', 'triggerType_topic', 1, '联动方式，中文，topic对应列表');
INSERT INTO "ec"."dict_type" VALUES ('03', 'triggerType_device', 1, '联动方式，设备对应列表');
INSERT INTO "ec"."dict_type" VALUES ('04', 'component_eventType', 1, '组件与事件类型对应列表');

-- ----------------------------
-- Table structure for event_log
-- ----------------------------
DROP TABLE IF EXISTS "ec"."event_log";
CREATE TABLE "ec"."event_log" (
  "id" int8 NOT NULL DEFAULT nextval('ec.event_log_id_seq'::regclass),
  "event_log_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "event_type" int4 NOT NULL DEFAULT NULL,
  "start_time" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "end_time" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "content" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "creator" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "device_code" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "resource_code" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "device_name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "resource_name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "status" int2 DEFAULT NULL,
  "extend" json DEFAULT NULL,
  "court_uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_time" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "create_time" timestamp(6) DEFAULT NULL,
  "create_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "ec"."event_log"."id" IS 'ID,自增';
COMMENT ON COLUMN "ec"."event_log"."event_log_id" IS '事件日志唯一编号，可以为UUID';
COMMENT ON COLUMN "ec"."event_log"."event_type" IS '事件类型';
COMMENT ON COLUMN "ec"."event_log"."start_time" IS '事件开始时间';
COMMENT ON COLUMN "ec"."event_log"."end_time" IS '事件结束时间';
COMMENT ON COLUMN "ec"."event_log"."content" IS '事件日志内容';
COMMENT ON COLUMN "ec"."event_log"."creator" IS '事件日志记录者';
COMMENT ON COLUMN "ec"."event_log"."device_code" IS '发生事件的设备编号';
COMMENT ON COLUMN "ec"."event_log"."resource_code" IS '发生事件的资源点编号';
COMMENT ON COLUMN "ec"."event_log"."device_name" IS '设备名称（冗余）';
COMMENT ON COLUMN "ec"."event_log"."resource_name" IS '资源点名称（冗余）';
COMMENT ON COLUMN "ec"."event_log"."status" IS '事件状态，1-开始，2-脉冲，3-结束';
COMMENT ON COLUMN "ec"."event_log"."extend" IS '业务的拓展字段';
COMMENT ON COLUMN "ec"."event_log"."court_uuid" IS '小区UUID';
COMMENT ON COLUMN "ec"."event_log"."update_time" IS '更新时间';
COMMENT ON COLUMN "ec"."event_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "ec"."event_log"."create_user" IS '创建人';
COMMENT ON COLUMN "ec"."event_log"."update_user" IS '修订人';
COMMENT ON COLUMN "ec"."event_log"."uuid" IS '表记录';
COMMENT ON TABLE "ec"."event_log" IS '事件日志表';

-- ----------------------------
-- Table structure for trigger
-- ----------------------------
DROP TABLE IF EXISTS "ec"."trigger";
CREATE TABLE "ec"."trigger" (
  "id" int8 NOT NULL DEFAULT nextval('ec.trigger_id_seq'::regclass),
  "event_type" int4 DEFAULT NULL,
  "event_source_code" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "is_sequential" bool DEFAULT NULL,
  "creator" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "court_uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_time" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "create_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(6) DEFAULT NULL
)
;
COMMENT ON COLUMN "ec"."trigger"."id" IS 'ID，自增';
COMMENT ON COLUMN "ec"."trigger"."event_type" IS '事件类型';
COMMENT ON COLUMN "ec"."trigger"."event_source_code" IS '事件源编号';
COMMENT ON COLUMN "ec"."trigger"."is_sequential" IS '时序性，true : 是, false : 否';
COMMENT ON COLUMN "ec"."trigger"."creator" IS '创建者编号';
COMMENT ON COLUMN "ec"."trigger"."uuid" IS '表记录';
COMMENT ON COLUMN "ec"."trigger"."court_uuid" IS '小区UUID';
COMMENT ON COLUMN "ec"."trigger"."update_time" IS '更新时间';
COMMENT ON COLUMN "ec"."trigger"."create_user" IS '创建人';
COMMENT ON COLUMN "ec"."trigger"."update_user" IS '修订人';
COMMENT ON COLUMN "ec"."trigger"."create_time" IS '创建时间';
COMMENT ON TABLE "ec"."trigger" IS '联动配置表';

-- ----------------------------
-- Table structure for trigger_detail
-- ----------------------------
DROP TABLE IF EXISTS "ec"."trigger_detail";
CREATE TABLE "ec"."trigger_detail" (
  "id" int8 NOT NULL DEFAULT nextval('ec.trigger_detail_id_seq'::regclass),
  "trigger_id" int8 NOT NULL DEFAULT NULL,
  "trigger_type" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "trigger_params" jsonb DEFAULT NULL,
  "to" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "order" int2 DEFAULT 1,
  "uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "court_uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(6) DEFAULT NULL,
  "update_time" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "create_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "ec"."trigger_detail"."id" IS 'ID, 自增';
COMMENT ON COLUMN "ec"."trigger_detail"."trigger_id" IS '联动规则ID';
COMMENT ON COLUMN "ec"."trigger_detail"."trigger_type" IS '联动方法';
COMMENT ON COLUMN "ec"."trigger_detail"."trigger_params" IS '联动参数';
COMMENT ON COLUMN "ec"."trigger_detail"."to" IS '下一个联动方的联动规则接收队列';
COMMENT ON COLUMN "ec"."trigger_detail"."order" IS '联动顺序';
COMMENT ON COLUMN "ec"."trigger_detail"."uuid" IS '表记录';
COMMENT ON COLUMN "ec"."trigger_detail"."court_uuid" IS '小区UUID';
COMMENT ON COLUMN "ec"."trigger_detail"."create_time" IS '创建时间';
COMMENT ON COLUMN "ec"."trigger_detail"."update_time" IS '更新时间';
COMMENT ON COLUMN "ec"."trigger_detail"."create_user" IS '创建人';
COMMENT ON COLUMN "ec"."trigger_detail"."update_user" IS '修订人';
COMMENT ON TABLE "ec"."trigger_detail" IS '联动明细表';

-- ----------------------------
-- Table structure for trigger_log
-- ----------------------------
DROP TABLE IF EXISTS "ec"."trigger_log";
CREATE TABLE "ec"."trigger_log" (
  "id" int8 NOT NULL DEFAULT nextval('ec.trigger_log_id_seq'::regclass),
  "trigger_log_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "trigger_type" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "trigger_result" jsonb DEFAULT NULL,
  "order" int2 DEFAULT NULL,
  "trigger_time" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "creator" varchar COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "retry_count" int4 DEFAULT 0,
  "event_log_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "court_uuid" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(6) DEFAULT NULL,
  "update_time" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "create_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_user" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "ec"."trigger_log"."id" IS 'ID,自增';
COMMENT ON COLUMN "ec"."trigger_log"."trigger_log_id" IS '联动日志编号,UUID';
COMMENT ON COLUMN "ec"."trigger_log"."trigger_type" IS '联动方式';
COMMENT ON COLUMN "ec"."trigger_log"."trigger_result" IS '联动结果';
COMMENT ON COLUMN "ec"."trigger_log"."order" IS ' 联动顺序，从1开始';
COMMENT ON COLUMN "ec"."trigger_log"."trigger_time" IS '联动时间';
COMMENT ON COLUMN "ec"."trigger_log"."creator" IS '联动方';
COMMENT ON COLUMN "ec"."trigger_log"."retry_count" IS '重试次数';
COMMENT ON COLUMN "ec"."trigger_log"."event_log_id" IS '关联事件日志编号';
COMMENT ON COLUMN "ec"."trigger_log"."uuid" IS '表记录';
COMMENT ON COLUMN "ec"."trigger_log"."court_uuid" IS '小区UUID';
COMMENT ON COLUMN "ec"."trigger_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "ec"."trigger_log"."update_time" IS '更新时间';
COMMENT ON COLUMN "ec"."trigger_log"."create_user" IS '创建人';
COMMENT ON COLUMN "ec"."trigger_log"."update_user" IS '修订人';
COMMENT ON TABLE "ec"."trigger_log" IS '联动日志表';

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "ec"."event_log_id_seq"
OWNED BY "ec"."event_log"."id";
SELECT setval('"ec"."event_log_id_seq"', 12487, true);
ALTER SEQUENCE "ec"."trigger_detail_id_seq"
OWNED BY "ec"."trigger_detail"."id";
SELECT setval('"ec"."trigger_detail_id_seq"', 23, true);
ALTER SEQUENCE "ec"."trigger_id_seq"
OWNED BY "ec"."trigger"."id";
SELECT setval('"ec"."trigger_id_seq"', 17, true);
ALTER SEQUENCE "ec"."trigger_log_id_seq"
OWNED BY "ec"."trigger_log"."id";
SELECT setval('"ec"."trigger_log_id_seq"', 67, true);

-- ----------------------------
-- Indexes structure for table event_log
-- ----------------------------
CREATE INDEX "event_log_event_log_id_idx" ON "ec"."event_log" USING btree (
  "event_log_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
COMMENT ON INDEX "ec"."event_log_event_log_id_idx" IS '事件日志唯一标示索引';
CREATE INDEX "event_log_event_type_idx" ON "ec"."event_log" USING btree (
  "event_type" "pg_catalog"."int4_ops" ASC NULLS LAST
);
COMMENT ON INDEX "ec"."event_log_event_type_idx" IS '事件类型索引';
CREATE INDEX "event_log_start_time_idx" ON "ec"."event_log" USING btree (
  "start_time" "pg_catalog"."timestamp_ops" ASC NULLS LAST
);
COMMENT ON INDEX "ec"."event_log_start_time_idx" IS '事件开始时间索引';

-- ----------------------------
-- Primary Key structure for table event_log
-- ----------------------------
ALTER TABLE "ec"."event_log" ADD CONSTRAINT "event_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table trigger
-- ----------------------------
ALTER TABLE "ec"."trigger" ADD CONSTRAINT "trigger_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table trigger_detail
-- ----------------------------
ALTER TABLE "ec"."trigger_detail" ADD CONSTRAINT "trigger_detail_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table trigger_log
-- ----------------------------
CREATE INDEX "trigger_log_event_log_id_idx" ON "ec"."trigger_log" USING btree (
  "event_log_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
COMMENT ON INDEX "ec"."trigger_log_event_log_id_idx" IS '事件日志编号索引';

-- ----------------------------
-- Primary Key structure for table trigger_log
-- ----------------------------
ALTER TABLE "ec"."trigger_log" ADD CONSTRAINT "trigger_log_pkey" PRIMARY KEY ("id");
