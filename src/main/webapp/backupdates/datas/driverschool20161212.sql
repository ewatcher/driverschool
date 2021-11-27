-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: sys
-- ------------------------------------------------------
-- Server version	5.7.9-enterprise-commercial-advanced-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `host_summary`
--

DROP TABLE IF EXISTS `host_summary`;
/*!50001 DROP VIEW IF EXISTS `host_summary`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `host_summary` AS SELECT 
 1 AS `host`,
 1 AS `statements`,
 1 AS `statement_latency`,
 1 AS `statement_avg_latency`,
 1 AS `table_scans`,
 1 AS `file_ios`,
 1 AS `file_io_latency`,
 1 AS `current_connections`,
 1 AS `total_connections`,
 1 AS `unique_users`,
 1 AS `current_memory`,
 1 AS `total_memory_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `host_summary_by_file_io`
--

DROP TABLE IF EXISTS `host_summary_by_file_io`;
/*!50001 DROP VIEW IF EXISTS `host_summary_by_file_io`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `host_summary_by_file_io` AS SELECT 
 1 AS `host`,
 1 AS `ios`,
 1 AS `io_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `host_summary_by_file_io_type`
--

DROP TABLE IF EXISTS `host_summary_by_file_io_type`;
/*!50001 DROP VIEW IF EXISTS `host_summary_by_file_io_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `host_summary_by_file_io_type` AS SELECT 
 1 AS `host`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `host_summary_by_stages`
--

DROP TABLE IF EXISTS `host_summary_by_stages`;
/*!50001 DROP VIEW IF EXISTS `host_summary_by_stages`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `host_summary_by_stages` AS SELECT 
 1 AS `host`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `host_summary_by_statement_latency`
--

DROP TABLE IF EXISTS `host_summary_by_statement_latency`;
/*!50001 DROP VIEW IF EXISTS `host_summary_by_statement_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `host_summary_by_statement_latency` AS SELECT 
 1 AS `host`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `host_summary_by_statement_type`
--

DROP TABLE IF EXISTS `host_summary_by_statement_type`;
/*!50001 DROP VIEW IF EXISTS `host_summary_by_statement_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `host_summary_by_statement_type` AS SELECT 
 1 AS `host`,
 1 AS `statement`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `innodb_buffer_stats_by_schema`
--

DROP TABLE IF EXISTS `innodb_buffer_stats_by_schema`;
/*!50001 DROP VIEW IF EXISTS `innodb_buffer_stats_by_schema`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `innodb_buffer_stats_by_schema` AS SELECT 
 1 AS `object_schema`,
 1 AS `allocated`,
 1 AS `data`,
 1 AS `pages`,
 1 AS `pages_hashed`,
 1 AS `pages_old`,
 1 AS `rows_cached`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `innodb_buffer_stats_by_table`
--

DROP TABLE IF EXISTS `innodb_buffer_stats_by_table`;
/*!50001 DROP VIEW IF EXISTS `innodb_buffer_stats_by_table`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `innodb_buffer_stats_by_table` AS SELECT 
 1 AS `object_schema`,
 1 AS `object_name`,
 1 AS `allocated`,
 1 AS `data`,
 1 AS `pages`,
 1 AS `pages_hashed`,
 1 AS `pages_old`,
 1 AS `rows_cached`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `innodb_lock_waits`
--

DROP TABLE IF EXISTS `innodb_lock_waits`;
/*!50001 DROP VIEW IF EXISTS `innodb_lock_waits`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `innodb_lock_waits` AS SELECT 
 1 AS `wait_started`,
 1 AS `wait_age`,
 1 AS `wait_age_secs`,
 1 AS `locked_table`,
 1 AS `locked_index`,
 1 AS `locked_type`,
 1 AS `waiting_trx_id`,
 1 AS `waiting_trx_started`,
 1 AS `waiting_trx_age`,
 1 AS `waiting_trx_rows_locked`,
 1 AS `waiting_trx_rows_modified`,
 1 AS `waiting_pid`,
 1 AS `waiting_query`,
 1 AS `waiting_lock_id`,
 1 AS `waiting_lock_mode`,
 1 AS `blocking_trx_id`,
 1 AS `blocking_pid`,
 1 AS `blocking_query`,
 1 AS `blocking_lock_id`,
 1 AS `blocking_lock_mode`,
 1 AS `blocking_trx_started`,
 1 AS `blocking_trx_age`,
 1 AS `blocking_trx_rows_locked`,
 1 AS `blocking_trx_rows_modified`,
 1 AS `sql_kill_blocking_query`,
 1 AS `sql_kill_blocking_connection`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `io_by_thread_by_latency`
--

DROP TABLE IF EXISTS `io_by_thread_by_latency`;
/*!50001 DROP VIEW IF EXISTS `io_by_thread_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `io_by_thread_by_latency` AS SELECT 
 1 AS `user`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`,
 1 AS `thread_id`,
 1 AS `processlist_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `io_global_by_file_by_bytes`
--

DROP TABLE IF EXISTS `io_global_by_file_by_bytes`;
/*!50001 DROP VIEW IF EXISTS `io_global_by_file_by_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `io_global_by_file_by_bytes` AS SELECT 
 1 AS `file`,
 1 AS `count_read`,
 1 AS `total_read`,
 1 AS `avg_read`,
 1 AS `count_write`,
 1 AS `total_written`,
 1 AS `avg_write`,
 1 AS `total`,
 1 AS `write_pct`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `io_global_by_file_by_latency`
--

DROP TABLE IF EXISTS `io_global_by_file_by_latency`;
/*!50001 DROP VIEW IF EXISTS `io_global_by_file_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `io_global_by_file_by_latency` AS SELECT 
 1 AS `file`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `count_read`,
 1 AS `read_latency`,
 1 AS `count_write`,
 1 AS `write_latency`,
 1 AS `count_misc`,
 1 AS `misc_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `io_global_by_wait_by_bytes`
--

DROP TABLE IF EXISTS `io_global_by_wait_by_bytes`;
/*!50001 DROP VIEW IF EXISTS `io_global_by_wait_by_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `io_global_by_wait_by_bytes` AS SELECT 
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`,
 1 AS `count_read`,
 1 AS `total_read`,
 1 AS `avg_read`,
 1 AS `count_write`,
 1 AS `total_written`,
 1 AS `avg_written`,
 1 AS `total_requested`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `io_global_by_wait_by_latency`
--

DROP TABLE IF EXISTS `io_global_by_wait_by_latency`;
/*!50001 DROP VIEW IF EXISTS `io_global_by_wait_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `io_global_by_wait_by_latency` AS SELECT 
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`,
 1 AS `read_latency`,
 1 AS `write_latency`,
 1 AS `misc_latency`,
 1 AS `count_read`,
 1 AS `total_read`,
 1 AS `avg_read`,
 1 AS `count_write`,
 1 AS `total_written`,
 1 AS `avg_written`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `latest_file_io`
--

DROP TABLE IF EXISTS `latest_file_io`;
/*!50001 DROP VIEW IF EXISTS `latest_file_io`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `latest_file_io` AS SELECT 
 1 AS `thread`,
 1 AS `file`,
 1 AS `latency`,
 1 AS `operation`,
 1 AS `requested`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `memory_by_host_by_current_bytes`
--

DROP TABLE IF EXISTS `memory_by_host_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `memory_by_host_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `memory_by_host_by_current_bytes` AS SELECT 
 1 AS `host`,
 1 AS `current_count_used`,
 1 AS `current_allocated`,
 1 AS `current_avg_alloc`,
 1 AS `current_max_alloc`,
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `memory_by_thread_by_current_bytes`
--

DROP TABLE IF EXISTS `memory_by_thread_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `memory_by_thread_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `memory_by_thread_by_current_bytes` AS SELECT 
 1 AS `thread_id`,
 1 AS `user`,
 1 AS `current_count_used`,
 1 AS `current_allocated`,
 1 AS `current_avg_alloc`,
 1 AS `current_max_alloc`,
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `memory_by_user_by_current_bytes`
--

DROP TABLE IF EXISTS `memory_by_user_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `memory_by_user_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `memory_by_user_by_current_bytes` AS SELECT 
 1 AS `user`,
 1 AS `current_count_used`,
 1 AS `current_allocated`,
 1 AS `current_avg_alloc`,
 1 AS `current_max_alloc`,
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `memory_global_by_current_bytes`
--

DROP TABLE IF EXISTS `memory_global_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `memory_global_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `memory_global_by_current_bytes` AS SELECT 
 1 AS `event_name`,
 1 AS `current_count`,
 1 AS `current_alloc`,
 1 AS `current_avg_alloc`,
 1 AS `high_count`,
 1 AS `high_alloc`,
 1 AS `high_avg_alloc`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `memory_global_total`
--

DROP TABLE IF EXISTS `memory_global_total`;
/*!50001 DROP VIEW IF EXISTS `memory_global_total`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `memory_global_total` AS SELECT 
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `metrics`
--

DROP TABLE IF EXISTS `metrics`;
/*!50001 DROP VIEW IF EXISTS `metrics`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `metrics` AS SELECT 
 1 AS `Variable_name`,
 1 AS `Variable_value`,
 1 AS `Type`,
 1 AS `Enabled`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `processlist`
--

DROP TABLE IF EXISTS `processlist`;
/*!50001 DROP VIEW IF EXISTS `processlist`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `processlist` AS SELECT 
 1 AS `thd_id`,
 1 AS `conn_id`,
 1 AS `user`,
 1 AS `db`,
 1 AS `command`,
 1 AS `state`,
 1 AS `time`,
 1 AS `current_statement`,
 1 AS `statement_latency`,
 1 AS `progress`,
 1 AS `lock_latency`,
 1 AS `rows_examined`,
 1 AS `rows_sent`,
 1 AS `rows_affected`,
 1 AS `tmp_tables`,
 1 AS `tmp_disk_tables`,
 1 AS `full_scan`,
 1 AS `last_statement`,
 1 AS `last_statement_latency`,
 1 AS `current_memory`,
 1 AS `last_wait`,
 1 AS `last_wait_latency`,
 1 AS `source`,
 1 AS `trx_latency`,
 1 AS `trx_state`,
 1 AS `trx_autocommit`,
 1 AS `pid`,
 1 AS `program_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `ps_check_lost_instrumentation`
--

DROP TABLE IF EXISTS `ps_check_lost_instrumentation`;
/*!50001 DROP VIEW IF EXISTS `ps_check_lost_instrumentation`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ps_check_lost_instrumentation` AS SELECT 
 1 AS `variable_name`,
 1 AS `variable_value`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_auto_increment_columns`
--

DROP TABLE IF EXISTS `schema_auto_increment_columns`;
/*!50001 DROP VIEW IF EXISTS `schema_auto_increment_columns`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_auto_increment_columns` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `column_name`,
 1 AS `data_type`,
 1 AS `column_type`,
 1 AS `is_signed`,
 1 AS `is_unsigned`,
 1 AS `max_value`,
 1 AS `auto_increment`,
 1 AS `auto_increment_ratio`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_index_statistics`
--

DROP TABLE IF EXISTS `schema_index_statistics`;
/*!50001 DROP VIEW IF EXISTS `schema_index_statistics`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_index_statistics` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `index_name`,
 1 AS `rows_selected`,
 1 AS `select_latency`,
 1 AS `rows_inserted`,
 1 AS `insert_latency`,
 1 AS `rows_updated`,
 1 AS `update_latency`,
 1 AS `rows_deleted`,
 1 AS `delete_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_object_overview`
--

DROP TABLE IF EXISTS `schema_object_overview`;
/*!50001 DROP VIEW IF EXISTS `schema_object_overview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_object_overview` AS SELECT 
 1 AS `db`,
 1 AS `object_type`,
 1 AS `count`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_redundant_indexes`
--

DROP TABLE IF EXISTS `schema_redundant_indexes`;
/*!50001 DROP VIEW IF EXISTS `schema_redundant_indexes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_redundant_indexes` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `redundant_index_name`,
 1 AS `redundant_index_columns`,
 1 AS `redundant_index_non_unique`,
 1 AS `dominant_index_name`,
 1 AS `dominant_index_columns`,
 1 AS `dominant_index_non_unique`,
 1 AS `subpart_exists`,
 1 AS `sql_drop_index`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_table_lock_waits`
--

DROP TABLE IF EXISTS `schema_table_lock_waits`;
/*!50001 DROP VIEW IF EXISTS `schema_table_lock_waits`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_table_lock_waits` AS SELECT 
 1 AS `object_schema`,
 1 AS `object_name`,
 1 AS `waiting_thread_id`,
 1 AS `waiting_pid`,
 1 AS `waiting_account`,
 1 AS `waiting_lock_type`,
 1 AS `waiting_lock_duration`,
 1 AS `waiting_query`,
 1 AS `waiting_query_secs`,
 1 AS `waiting_query_rows_affected`,
 1 AS `waiting_query_rows_examined`,
 1 AS `blocking_thread_id`,
 1 AS `blocking_pid`,
 1 AS `blocking_account`,
 1 AS `blocking_lock_type`,
 1 AS `blocking_lock_duration`,
 1 AS `sql_kill_blocking_query`,
 1 AS `sql_kill_blocking_connection`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_table_statistics`
--

DROP TABLE IF EXISTS `schema_table_statistics`;
/*!50001 DROP VIEW IF EXISTS `schema_table_statistics`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_table_statistics` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `total_latency`,
 1 AS `rows_fetched`,
 1 AS `fetch_latency`,
 1 AS `rows_inserted`,
 1 AS `insert_latency`,
 1 AS `rows_updated`,
 1 AS `update_latency`,
 1 AS `rows_deleted`,
 1 AS `delete_latency`,
 1 AS `io_read_requests`,
 1 AS `io_read`,
 1 AS `io_read_latency`,
 1 AS `io_write_requests`,
 1 AS `io_write`,
 1 AS `io_write_latency`,
 1 AS `io_misc_requests`,
 1 AS `io_misc_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_table_statistics_with_buffer`
--

DROP TABLE IF EXISTS `schema_table_statistics_with_buffer`;
/*!50001 DROP VIEW IF EXISTS `schema_table_statistics_with_buffer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_table_statistics_with_buffer` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `rows_fetched`,
 1 AS `fetch_latency`,
 1 AS `rows_inserted`,
 1 AS `insert_latency`,
 1 AS `rows_updated`,
 1 AS `update_latency`,
 1 AS `rows_deleted`,
 1 AS `delete_latency`,
 1 AS `io_read_requests`,
 1 AS `io_read`,
 1 AS `io_read_latency`,
 1 AS `io_write_requests`,
 1 AS `io_write`,
 1 AS `io_write_latency`,
 1 AS `io_misc_requests`,
 1 AS `io_misc_latency`,
 1 AS `innodb_buffer_allocated`,
 1 AS `innodb_buffer_data`,
 1 AS `innodb_buffer_free`,
 1 AS `innodb_buffer_pages`,
 1 AS `innodb_buffer_pages_hashed`,
 1 AS `innodb_buffer_pages_old`,
 1 AS `innodb_buffer_rows_cached`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_tables_with_full_table_scans`
--

DROP TABLE IF EXISTS `schema_tables_with_full_table_scans`;
/*!50001 DROP VIEW IF EXISTS `schema_tables_with_full_table_scans`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_tables_with_full_table_scans` AS SELECT 
 1 AS `object_schema`,
 1 AS `object_name`,
 1 AS `rows_full_scanned`,
 1 AS `latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `schema_unused_indexes`
--

DROP TABLE IF EXISTS `schema_unused_indexes`;
/*!50001 DROP VIEW IF EXISTS `schema_unused_indexes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `schema_unused_indexes` AS SELECT 
 1 AS `object_schema`,
 1 AS `object_name`,
 1 AS `index_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `session`
--

DROP TABLE IF EXISTS `session`;
/*!50001 DROP VIEW IF EXISTS `session`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `session` AS SELECT 
 1 AS `thd_id`,
 1 AS `conn_id`,
 1 AS `user`,
 1 AS `db`,
 1 AS `command`,
 1 AS `state`,
 1 AS `time`,
 1 AS `current_statement`,
 1 AS `statement_latency`,
 1 AS `progress`,
 1 AS `lock_latency`,
 1 AS `rows_examined`,
 1 AS `rows_sent`,
 1 AS `rows_affected`,
 1 AS `tmp_tables`,
 1 AS `tmp_disk_tables`,
 1 AS `full_scan`,
 1 AS `last_statement`,
 1 AS `last_statement_latency`,
 1 AS `current_memory`,
 1 AS `last_wait`,
 1 AS `last_wait_latency`,
 1 AS `source`,
 1 AS `trx_latency`,
 1 AS `trx_state`,
 1 AS `trx_autocommit`,
 1 AS `pid`,
 1 AS `program_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `session_ssl_status`
--

DROP TABLE IF EXISTS `session_ssl_status`;
/*!50001 DROP VIEW IF EXISTS `session_ssl_status`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `session_ssl_status` AS SELECT 
 1 AS `thread_id`,
 1 AS `ssl_version`,
 1 AS `ssl_cipher`,
 1 AS `ssl_sessions_reused`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `statement_analysis`
--

DROP TABLE IF EXISTS `statement_analysis`;
/*!50001 DROP VIEW IF EXISTS `statement_analysis`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `statement_analysis` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `full_scan`,
 1 AS `exec_count`,
 1 AS `err_count`,
 1 AS `warn_count`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `avg_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_sent_avg`,
 1 AS `rows_examined`,
 1 AS `rows_examined_avg`,
 1 AS `rows_affected`,
 1 AS `rows_affected_avg`,
 1 AS `tmp_tables`,
 1 AS `tmp_disk_tables`,
 1 AS `rows_sorted`,
 1 AS `sort_merge_passes`,
 1 AS `digest`,
 1 AS `first_seen`,
 1 AS `last_seen`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `statements_with_errors_or_warnings`
--

DROP TABLE IF EXISTS `statements_with_errors_or_warnings`;
/*!50001 DROP VIEW IF EXISTS `statements_with_errors_or_warnings`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `statements_with_errors_or_warnings` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `errors`,
 1 AS `error_pct`,
 1 AS `warnings`,
 1 AS `warning_pct`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `statements_with_full_table_scans`
--

DROP TABLE IF EXISTS `statements_with_full_table_scans`;
/*!50001 DROP VIEW IF EXISTS `statements_with_full_table_scans`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `statements_with_full_table_scans` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `total_latency`,
 1 AS `no_index_used_count`,
 1 AS `no_good_index_used_count`,
 1 AS `no_index_used_pct`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_sent_avg`,
 1 AS `rows_examined_avg`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `statements_with_runtimes_in_95th_percentile`
--

DROP TABLE IF EXISTS `statements_with_runtimes_in_95th_percentile`;
/*!50001 DROP VIEW IF EXISTS `statements_with_runtimes_in_95th_percentile`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `statements_with_runtimes_in_95th_percentile` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `full_scan`,
 1 AS `exec_count`,
 1 AS `err_count`,
 1 AS `warn_count`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `avg_latency`,
 1 AS `rows_sent`,
 1 AS `rows_sent_avg`,
 1 AS `rows_examined`,
 1 AS `rows_examined_avg`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `statements_with_sorting`
--

DROP TABLE IF EXISTS `statements_with_sorting`;
/*!50001 DROP VIEW IF EXISTS `statements_with_sorting`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `statements_with_sorting` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `total_latency`,
 1 AS `sort_merge_passes`,
 1 AS `avg_sort_merges`,
 1 AS `sorts_using_scans`,
 1 AS `sort_using_range`,
 1 AS `rows_sorted`,
 1 AS `avg_rows_sorted`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `statements_with_temp_tables`
--

DROP TABLE IF EXISTS `statements_with_temp_tables`;
/*!50001 DROP VIEW IF EXISTS `statements_with_temp_tables`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `statements_with_temp_tables` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `total_latency`,
 1 AS `memory_tmp_tables`,
 1 AS `disk_tmp_tables`,
 1 AS `avg_tmp_tables_per_query`,
 1 AS `tmp_tables_to_disk_pct`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `variable` varchar(128) NOT NULL,
  `value` varchar(128) DEFAULT NULL,
  `set_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `set_by` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`variable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES ('diagnostics.allow_i_s_tables','OFF','2016-03-14 06:13:51',NULL),('diagnostics.include_raw','OFF','2016-03-14 06:13:51',NULL),('ps_thread_trx_info.max_length','65535','2016-03-14 06:13:51',NULL),('statement_performance_analyzer.limit','100','2016-03-14 06:13:51',NULL),('statement_performance_analyzer.view',NULL,'2016-03-14 06:13:51',NULL),('statement_truncate_len','64','2016-03-14 06:13:51',NULL);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_summary`
--

DROP TABLE IF EXISTS `user_summary`;
/*!50001 DROP VIEW IF EXISTS `user_summary`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_summary` AS SELECT 
 1 AS `user`,
 1 AS `statements`,
 1 AS `statement_latency`,
 1 AS `statement_avg_latency`,
 1 AS `table_scans`,
 1 AS `file_ios`,
 1 AS `file_io_latency`,
 1 AS `current_connections`,
 1 AS `total_connections`,
 1 AS `unique_hosts`,
 1 AS `current_memory`,
 1 AS `total_memory_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `user_summary_by_file_io`
--

DROP TABLE IF EXISTS `user_summary_by_file_io`;
/*!50001 DROP VIEW IF EXISTS `user_summary_by_file_io`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_summary_by_file_io` AS SELECT 
 1 AS `user`,
 1 AS `ios`,
 1 AS `io_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `user_summary_by_file_io_type`
--

DROP TABLE IF EXISTS `user_summary_by_file_io_type`;
/*!50001 DROP VIEW IF EXISTS `user_summary_by_file_io_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_summary_by_file_io_type` AS SELECT 
 1 AS `user`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `user_summary_by_stages`
--

DROP TABLE IF EXISTS `user_summary_by_stages`;
/*!50001 DROP VIEW IF EXISTS `user_summary_by_stages`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_summary_by_stages` AS SELECT 
 1 AS `user`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `user_summary_by_statement_latency`
--

DROP TABLE IF EXISTS `user_summary_by_statement_latency`;
/*!50001 DROP VIEW IF EXISTS `user_summary_by_statement_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_summary_by_statement_latency` AS SELECT 
 1 AS `user`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `user_summary_by_statement_type`
--

DROP TABLE IF EXISTS `user_summary_by_statement_type`;
/*!50001 DROP VIEW IF EXISTS `user_summary_by_statement_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_summary_by_statement_type` AS SELECT 
 1 AS `user`,
 1 AS `statement`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `version`
--

DROP TABLE IF EXISTS `version`;
/*!50001 DROP VIEW IF EXISTS `version`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `version` AS SELECT 
 1 AS `sys_version`,
 1 AS `mysql_version`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `wait_classes_global_by_avg_latency`
--

DROP TABLE IF EXISTS `wait_classes_global_by_avg_latency`;
/*!50001 DROP VIEW IF EXISTS `wait_classes_global_by_avg_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `wait_classes_global_by_avg_latency` AS SELECT 
 1 AS `event_class`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `wait_classes_global_by_latency`
--

DROP TABLE IF EXISTS `wait_classes_global_by_latency`;
/*!50001 DROP VIEW IF EXISTS `wait_classes_global_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `wait_classes_global_by_latency` AS SELECT 
 1 AS `event_class`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `waits_by_host_by_latency`
--

DROP TABLE IF EXISTS `waits_by_host_by_latency`;
/*!50001 DROP VIEW IF EXISTS `waits_by_host_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `waits_by_host_by_latency` AS SELECT 
 1 AS `host`,
 1 AS `event`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `waits_by_user_by_latency`
--

DROP TABLE IF EXISTS `waits_by_user_by_latency`;
/*!50001 DROP VIEW IF EXISTS `waits_by_user_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `waits_by_user_by_latency` AS SELECT 
 1 AS `user`,
 1 AS `event`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `waits_global_by_latency`
--

DROP TABLE IF EXISTS `waits_global_by_latency`;
/*!50001 DROP VIEW IF EXISTS `waits_global_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `waits_global_by_latency` AS SELECT 
 1 AS `events`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$host_summary`
--

DROP TABLE IF EXISTS `x$host_summary`;
/*!50001 DROP VIEW IF EXISTS `x$host_summary`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$host_summary` AS SELECT 
 1 AS `host`,
 1 AS `statements`,
 1 AS `statement_latency`,
 1 AS `statement_avg_latency`,
 1 AS `table_scans`,
 1 AS `file_ios`,
 1 AS `file_io_latency`,
 1 AS `current_connections`,
 1 AS `total_connections`,
 1 AS `unique_users`,
 1 AS `current_memory`,
 1 AS `total_memory_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$host_summary_by_file_io`
--

DROP TABLE IF EXISTS `x$host_summary_by_file_io`;
/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_file_io`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$host_summary_by_file_io` AS SELECT 
 1 AS `host`,
 1 AS `ios`,
 1 AS `io_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$host_summary_by_file_io_type`
--

DROP TABLE IF EXISTS `x$host_summary_by_file_io_type`;
/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_file_io_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$host_summary_by_file_io_type` AS SELECT 
 1 AS `host`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$host_summary_by_stages`
--

DROP TABLE IF EXISTS `x$host_summary_by_stages`;
/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_stages`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$host_summary_by_stages` AS SELECT 
 1 AS `host`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$host_summary_by_statement_latency`
--

DROP TABLE IF EXISTS `x$host_summary_by_statement_latency`;
/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_statement_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$host_summary_by_statement_latency` AS SELECT 
 1 AS `host`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$host_summary_by_statement_type`
--

DROP TABLE IF EXISTS `x$host_summary_by_statement_type`;
/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_statement_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$host_summary_by_statement_type` AS SELECT 
 1 AS `host`,
 1 AS `statement`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$innodb_buffer_stats_by_schema`
--

DROP TABLE IF EXISTS `x$innodb_buffer_stats_by_schema`;
/*!50001 DROP VIEW IF EXISTS `x$innodb_buffer_stats_by_schema`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$innodb_buffer_stats_by_schema` AS SELECT 
 1 AS `object_schema`,
 1 AS `allocated`,
 1 AS `data`,
 1 AS `pages`,
 1 AS `pages_hashed`,
 1 AS `pages_old`,
 1 AS `rows_cached`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$innodb_buffer_stats_by_table`
--

DROP TABLE IF EXISTS `x$innodb_buffer_stats_by_table`;
/*!50001 DROP VIEW IF EXISTS `x$innodb_buffer_stats_by_table`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$innodb_buffer_stats_by_table` AS SELECT 
 1 AS `object_schema`,
 1 AS `object_name`,
 1 AS `allocated`,
 1 AS `data`,
 1 AS `pages`,
 1 AS `pages_hashed`,
 1 AS `pages_old`,
 1 AS `rows_cached`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$innodb_lock_waits`
--

DROP TABLE IF EXISTS `x$innodb_lock_waits`;
/*!50001 DROP VIEW IF EXISTS `x$innodb_lock_waits`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$innodb_lock_waits` AS SELECT 
 1 AS `wait_started`,
 1 AS `wait_age`,
 1 AS `wait_age_secs`,
 1 AS `locked_table`,
 1 AS `locked_index`,
 1 AS `locked_type`,
 1 AS `waiting_trx_id`,
 1 AS `waiting_trx_started`,
 1 AS `waiting_trx_age`,
 1 AS `waiting_trx_rows_locked`,
 1 AS `waiting_trx_rows_modified`,
 1 AS `waiting_pid`,
 1 AS `waiting_query`,
 1 AS `waiting_lock_id`,
 1 AS `waiting_lock_mode`,
 1 AS `blocking_trx_id`,
 1 AS `blocking_pid`,
 1 AS `blocking_query`,
 1 AS `blocking_lock_id`,
 1 AS `blocking_lock_mode`,
 1 AS `blocking_trx_started`,
 1 AS `blocking_trx_age`,
 1 AS `blocking_trx_rows_locked`,
 1 AS `blocking_trx_rows_modified`,
 1 AS `sql_kill_blocking_query`,
 1 AS `sql_kill_blocking_connection`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$io_by_thread_by_latency`
--

DROP TABLE IF EXISTS `x$io_by_thread_by_latency`;
/*!50001 DROP VIEW IF EXISTS `x$io_by_thread_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$io_by_thread_by_latency` AS SELECT 
 1 AS `user`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`,
 1 AS `thread_id`,
 1 AS `processlist_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$io_global_by_file_by_bytes`
--

DROP TABLE IF EXISTS `x$io_global_by_file_by_bytes`;
/*!50001 DROP VIEW IF EXISTS `x$io_global_by_file_by_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$io_global_by_file_by_bytes` AS SELECT 
 1 AS `file`,
 1 AS `count_read`,
 1 AS `total_read`,
 1 AS `avg_read`,
 1 AS `count_write`,
 1 AS `total_written`,
 1 AS `avg_write`,
 1 AS `total`,
 1 AS `write_pct`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$io_global_by_file_by_latency`
--

DROP TABLE IF EXISTS `x$io_global_by_file_by_latency`;
/*!50001 DROP VIEW IF EXISTS `x$io_global_by_file_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$io_global_by_file_by_latency` AS SELECT 
 1 AS `file`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `count_read`,
 1 AS `read_latency`,
 1 AS `count_write`,
 1 AS `write_latency`,
 1 AS `count_misc`,
 1 AS `misc_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$io_global_by_wait_by_bytes`
--

DROP TABLE IF EXISTS `x$io_global_by_wait_by_bytes`;
/*!50001 DROP VIEW IF EXISTS `x$io_global_by_wait_by_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$io_global_by_wait_by_bytes` AS SELECT 
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`,
 1 AS `count_read`,
 1 AS `total_read`,
 1 AS `avg_read`,
 1 AS `count_write`,
 1 AS `total_written`,
 1 AS `avg_written`,
 1 AS `total_requested`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$io_global_by_wait_by_latency`
--

DROP TABLE IF EXISTS `x$io_global_by_wait_by_latency`;
/*!50001 DROP VIEW IF EXISTS `x$io_global_by_wait_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$io_global_by_wait_by_latency` AS SELECT 
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`,
 1 AS `read_latency`,
 1 AS `write_latency`,
 1 AS `misc_latency`,
 1 AS `count_read`,
 1 AS `total_read`,
 1 AS `avg_read`,
 1 AS `count_write`,
 1 AS `total_written`,
 1 AS `avg_written`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$latest_file_io`
--

DROP TABLE IF EXISTS `x$latest_file_io`;
/*!50001 DROP VIEW IF EXISTS `x$latest_file_io`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$latest_file_io` AS SELECT 
 1 AS `thread`,
 1 AS `file`,
 1 AS `latency`,
 1 AS `operation`,
 1 AS `requested`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$memory_by_host_by_current_bytes`
--

DROP TABLE IF EXISTS `x$memory_by_host_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `x$memory_by_host_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$memory_by_host_by_current_bytes` AS SELECT 
 1 AS `host`,
 1 AS `current_count_used`,
 1 AS `current_allocated`,
 1 AS `current_avg_alloc`,
 1 AS `current_max_alloc`,
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$memory_by_thread_by_current_bytes`
--

DROP TABLE IF EXISTS `x$memory_by_thread_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `x$memory_by_thread_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$memory_by_thread_by_current_bytes` AS SELECT 
 1 AS `thread_id`,
 1 AS `user`,
 1 AS `current_count_used`,
 1 AS `current_allocated`,
 1 AS `current_avg_alloc`,
 1 AS `current_max_alloc`,
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$memory_by_user_by_current_bytes`
--

DROP TABLE IF EXISTS `x$memory_by_user_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `x$memory_by_user_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$memory_by_user_by_current_bytes` AS SELECT 
 1 AS `user`,
 1 AS `current_count_used`,
 1 AS `current_allocated`,
 1 AS `current_avg_alloc`,
 1 AS `current_max_alloc`,
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$memory_global_by_current_bytes`
--

DROP TABLE IF EXISTS `x$memory_global_by_current_bytes`;
/*!50001 DROP VIEW IF EXISTS `x$memory_global_by_current_bytes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$memory_global_by_current_bytes` AS SELECT 
 1 AS `event_name`,
 1 AS `current_count`,
 1 AS `current_alloc`,
 1 AS `current_avg_alloc`,
 1 AS `high_count`,
 1 AS `high_alloc`,
 1 AS `high_avg_alloc`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$memory_global_total`
--

DROP TABLE IF EXISTS `x$memory_global_total`;
/*!50001 DROP VIEW IF EXISTS `x$memory_global_total`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$memory_global_total` AS SELECT 
 1 AS `total_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$processlist`
--

DROP TABLE IF EXISTS `x$processlist`;
/*!50001 DROP VIEW IF EXISTS `x$processlist`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$processlist` AS SELECT 
 1 AS `thd_id`,
 1 AS `conn_id`,
 1 AS `user`,
 1 AS `db`,
 1 AS `command`,
 1 AS `state`,
 1 AS `time`,
 1 AS `current_statement`,
 1 AS `statement_latency`,
 1 AS `progress`,
 1 AS `lock_latency`,
 1 AS `rows_examined`,
 1 AS `rows_sent`,
 1 AS `rows_affected`,
 1 AS `tmp_tables`,
 1 AS `tmp_disk_tables`,
 1 AS `full_scan`,
 1 AS `last_statement`,
 1 AS `last_statement_latency`,
 1 AS `current_memory`,
 1 AS `last_wait`,
 1 AS `last_wait_latency`,
 1 AS `source`,
 1 AS `trx_latency`,
 1 AS `trx_state`,
 1 AS `trx_autocommit`,
 1 AS `pid`,
 1 AS `program_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$ps_digest_95th_percentile_by_avg_us`
--

DROP TABLE IF EXISTS `x$ps_digest_95th_percentile_by_avg_us`;
/*!50001 DROP VIEW IF EXISTS `x$ps_digest_95th_percentile_by_avg_us`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$ps_digest_95th_percentile_by_avg_us` AS SELECT 
 1 AS `avg_us`,
 1 AS `percentile`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$ps_digest_avg_latency_distribution`
--

DROP TABLE IF EXISTS `x$ps_digest_avg_latency_distribution`;
/*!50001 DROP VIEW IF EXISTS `x$ps_digest_avg_latency_distribution`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$ps_digest_avg_latency_distribution` AS SELECT 
 1 AS `cnt`,
 1 AS `avg_us`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$ps_schema_table_statistics_io`
--

DROP TABLE IF EXISTS `x$ps_schema_table_statistics_io`;
/*!50001 DROP VIEW IF EXISTS `x$ps_schema_table_statistics_io`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$ps_schema_table_statistics_io` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `count_read`,
 1 AS `sum_number_of_bytes_read`,
 1 AS `sum_timer_read`,
 1 AS `count_write`,
 1 AS `sum_number_of_bytes_write`,
 1 AS `sum_timer_write`,
 1 AS `count_misc`,
 1 AS `sum_timer_misc`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$schema_flattened_keys`
--

DROP TABLE IF EXISTS `x$schema_flattened_keys`;
/*!50001 DROP VIEW IF EXISTS `x$schema_flattened_keys`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$schema_flattened_keys` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `index_name`,
 1 AS `non_unique`,
 1 AS `subpart_exists`,
 1 AS `index_columns`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$schema_index_statistics`
--

DROP TABLE IF EXISTS `x$schema_index_statistics`;
/*!50001 DROP VIEW IF EXISTS `x$schema_index_statistics`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$schema_index_statistics` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `index_name`,
 1 AS `rows_selected`,
 1 AS `select_latency`,
 1 AS `rows_inserted`,
 1 AS `insert_latency`,
 1 AS `rows_updated`,
 1 AS `update_latency`,
 1 AS `rows_deleted`,
 1 AS `delete_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$schema_table_lock_waits`
--

DROP TABLE IF EXISTS `x$schema_table_lock_waits`;
/*!50001 DROP VIEW IF EXISTS `x$schema_table_lock_waits`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$schema_table_lock_waits` AS SELECT 
 1 AS `object_schema`,
 1 AS `object_name`,
 1 AS `waiting_thread_id`,
 1 AS `waiting_pid`,
 1 AS `waiting_account`,
 1 AS `waiting_lock_type`,
 1 AS `waiting_lock_duration`,
 1 AS `waiting_query`,
 1 AS `waiting_query_secs`,
 1 AS `waiting_query_rows_affected`,
 1 AS `waiting_query_rows_examined`,
 1 AS `blocking_thread_id`,
 1 AS `blocking_pid`,
 1 AS `blocking_account`,
 1 AS `blocking_lock_type`,
 1 AS `blocking_lock_duration`,
 1 AS `sql_kill_blocking_query`,
 1 AS `sql_kill_blocking_connection`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$schema_table_statistics`
--

DROP TABLE IF EXISTS `x$schema_table_statistics`;
/*!50001 DROP VIEW IF EXISTS `x$schema_table_statistics`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$schema_table_statistics` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `total_latency`,
 1 AS `rows_fetched`,
 1 AS `fetch_latency`,
 1 AS `rows_inserted`,
 1 AS `insert_latency`,
 1 AS `rows_updated`,
 1 AS `update_latency`,
 1 AS `rows_deleted`,
 1 AS `delete_latency`,
 1 AS `io_read_requests`,
 1 AS `io_read`,
 1 AS `io_read_latency`,
 1 AS `io_write_requests`,
 1 AS `io_write`,
 1 AS `io_write_latency`,
 1 AS `io_misc_requests`,
 1 AS `io_misc_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$schema_table_statistics_with_buffer`
--

DROP TABLE IF EXISTS `x$schema_table_statistics_with_buffer`;
/*!50001 DROP VIEW IF EXISTS `x$schema_table_statistics_with_buffer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$schema_table_statistics_with_buffer` AS SELECT 
 1 AS `table_schema`,
 1 AS `table_name`,
 1 AS `rows_fetched`,
 1 AS `fetch_latency`,
 1 AS `rows_inserted`,
 1 AS `insert_latency`,
 1 AS `rows_updated`,
 1 AS `update_latency`,
 1 AS `rows_deleted`,
 1 AS `delete_latency`,
 1 AS `io_read_requests`,
 1 AS `io_read`,
 1 AS `io_read_latency`,
 1 AS `io_write_requests`,
 1 AS `io_write`,
 1 AS `io_write_latency`,
 1 AS `io_misc_requests`,
 1 AS `io_misc_latency`,
 1 AS `innodb_buffer_allocated`,
 1 AS `innodb_buffer_data`,
 1 AS `innodb_buffer_free`,
 1 AS `innodb_buffer_pages`,
 1 AS `innodb_buffer_pages_hashed`,
 1 AS `innodb_buffer_pages_old`,
 1 AS `innodb_buffer_rows_cached`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$schema_tables_with_full_table_scans`
--

DROP TABLE IF EXISTS `x$schema_tables_with_full_table_scans`;
/*!50001 DROP VIEW IF EXISTS `x$schema_tables_with_full_table_scans`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$schema_tables_with_full_table_scans` AS SELECT 
 1 AS `object_schema`,
 1 AS `object_name`,
 1 AS `rows_full_scanned`,
 1 AS `latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$session`
--

DROP TABLE IF EXISTS `x$session`;
/*!50001 DROP VIEW IF EXISTS `x$session`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$session` AS SELECT 
 1 AS `thd_id`,
 1 AS `conn_id`,
 1 AS `user`,
 1 AS `db`,
 1 AS `command`,
 1 AS `state`,
 1 AS `time`,
 1 AS `current_statement`,
 1 AS `statement_latency`,
 1 AS `progress`,
 1 AS `lock_latency`,
 1 AS `rows_examined`,
 1 AS `rows_sent`,
 1 AS `rows_affected`,
 1 AS `tmp_tables`,
 1 AS `tmp_disk_tables`,
 1 AS `full_scan`,
 1 AS `last_statement`,
 1 AS `last_statement_latency`,
 1 AS `current_memory`,
 1 AS `last_wait`,
 1 AS `last_wait_latency`,
 1 AS `source`,
 1 AS `trx_latency`,
 1 AS `trx_state`,
 1 AS `trx_autocommit`,
 1 AS `pid`,
 1 AS `program_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$statement_analysis`
--

DROP TABLE IF EXISTS `x$statement_analysis`;
/*!50001 DROP VIEW IF EXISTS `x$statement_analysis`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$statement_analysis` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `full_scan`,
 1 AS `exec_count`,
 1 AS `err_count`,
 1 AS `warn_count`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `avg_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_sent_avg`,
 1 AS `rows_examined`,
 1 AS `rows_examined_avg`,
 1 AS `rows_affected`,
 1 AS `rows_affected_avg`,
 1 AS `tmp_tables`,
 1 AS `tmp_disk_tables`,
 1 AS `rows_sorted`,
 1 AS `sort_merge_passes`,
 1 AS `digest`,
 1 AS `first_seen`,
 1 AS `last_seen`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$statements_with_errors_or_warnings`
--

DROP TABLE IF EXISTS `x$statements_with_errors_or_warnings`;
/*!50001 DROP VIEW IF EXISTS `x$statements_with_errors_or_warnings`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$statements_with_errors_or_warnings` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `errors`,
 1 AS `error_pct`,
 1 AS `warnings`,
 1 AS `warning_pct`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$statements_with_full_table_scans`
--

DROP TABLE IF EXISTS `x$statements_with_full_table_scans`;
/*!50001 DROP VIEW IF EXISTS `x$statements_with_full_table_scans`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$statements_with_full_table_scans` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `total_latency`,
 1 AS `no_index_used_count`,
 1 AS `no_good_index_used_count`,
 1 AS `no_index_used_pct`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_sent_avg`,
 1 AS `rows_examined_avg`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$statements_with_runtimes_in_95th_percentile`
--

DROP TABLE IF EXISTS `x$statements_with_runtimes_in_95th_percentile`;
/*!50001 DROP VIEW IF EXISTS `x$statements_with_runtimes_in_95th_percentile`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$statements_with_runtimes_in_95th_percentile` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `full_scan`,
 1 AS `exec_count`,
 1 AS `err_count`,
 1 AS `warn_count`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `avg_latency`,
 1 AS `rows_sent`,
 1 AS `rows_sent_avg`,
 1 AS `rows_examined`,
 1 AS `rows_examined_avg`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$statements_with_sorting`
--

DROP TABLE IF EXISTS `x$statements_with_sorting`;
/*!50001 DROP VIEW IF EXISTS `x$statements_with_sorting`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$statements_with_sorting` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `total_latency`,
 1 AS `sort_merge_passes`,
 1 AS `avg_sort_merges`,
 1 AS `sorts_using_scans`,
 1 AS `sort_using_range`,
 1 AS `rows_sorted`,
 1 AS `avg_rows_sorted`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$statements_with_temp_tables`
--

DROP TABLE IF EXISTS `x$statements_with_temp_tables`;
/*!50001 DROP VIEW IF EXISTS `x$statements_with_temp_tables`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$statements_with_temp_tables` AS SELECT 
 1 AS `query`,
 1 AS `db`,
 1 AS `exec_count`,
 1 AS `total_latency`,
 1 AS `memory_tmp_tables`,
 1 AS `disk_tmp_tables`,
 1 AS `avg_tmp_tables_per_query`,
 1 AS `tmp_tables_to_disk_pct`,
 1 AS `first_seen`,
 1 AS `last_seen`,
 1 AS `digest`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$user_summary`
--

DROP TABLE IF EXISTS `x$user_summary`;
/*!50001 DROP VIEW IF EXISTS `x$user_summary`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$user_summary` AS SELECT 
 1 AS `user`,
 1 AS `statements`,
 1 AS `statement_latency`,
 1 AS `statement_avg_latency`,
 1 AS `table_scans`,
 1 AS `file_ios`,
 1 AS `file_io_latency`,
 1 AS `current_connections`,
 1 AS `total_connections`,
 1 AS `unique_hosts`,
 1 AS `current_memory`,
 1 AS `total_memory_allocated`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$user_summary_by_file_io`
--

DROP TABLE IF EXISTS `x$user_summary_by_file_io`;
/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_file_io`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$user_summary_by_file_io` AS SELECT 
 1 AS `user`,
 1 AS `ios`,
 1 AS `io_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$user_summary_by_file_io_type`
--

DROP TABLE IF EXISTS `x$user_summary_by_file_io_type`;
/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_file_io_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$user_summary_by_file_io_type` AS SELECT 
 1 AS `user`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$user_summary_by_stages`
--

DROP TABLE IF EXISTS `x$user_summary_by_stages`;
/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_stages`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$user_summary_by_stages` AS SELECT 
 1 AS `user`,
 1 AS `event_name`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$user_summary_by_statement_latency`
--

DROP TABLE IF EXISTS `x$user_summary_by_statement_latency`;
/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_statement_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$user_summary_by_statement_latency` AS SELECT 
 1 AS `user`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$user_summary_by_statement_type`
--

DROP TABLE IF EXISTS `x$user_summary_by_statement_type`;
/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_statement_type`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$user_summary_by_statement_type` AS SELECT 
 1 AS `user`,
 1 AS `statement`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `max_latency`,
 1 AS `lock_latency`,
 1 AS `rows_sent`,
 1 AS `rows_examined`,
 1 AS `rows_affected`,
 1 AS `full_scans`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$wait_classes_global_by_avg_latency`
--

DROP TABLE IF EXISTS `x$wait_classes_global_by_avg_latency`;
/*!50001 DROP VIEW IF EXISTS `x$wait_classes_global_by_avg_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$wait_classes_global_by_avg_latency` AS SELECT 
 1 AS `event_class`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$wait_classes_global_by_latency`
--

DROP TABLE IF EXISTS `x$wait_classes_global_by_latency`;
/*!50001 DROP VIEW IF EXISTS `x$wait_classes_global_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$wait_classes_global_by_latency` AS SELECT 
 1 AS `event_class`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `min_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$waits_by_host_by_latency`
--

DROP TABLE IF EXISTS `x$waits_by_host_by_latency`;
/*!50001 DROP VIEW IF EXISTS `x$waits_by_host_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$waits_by_host_by_latency` AS SELECT 
 1 AS `host`,
 1 AS `event`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$waits_by_user_by_latency`
--

DROP TABLE IF EXISTS `x$waits_by_user_by_latency`;
/*!50001 DROP VIEW IF EXISTS `x$waits_by_user_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$waits_by_user_by_latency` AS SELECT 
 1 AS `user`,
 1 AS `event`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `x$waits_global_by_latency`
--

DROP TABLE IF EXISTS `x$waits_global_by_latency`;
/*!50001 DROP VIEW IF EXISTS `x$waits_global_by_latency`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `x$waits_global_by_latency` AS SELECT 
 1 AS `events`,
 1 AS `total`,
 1 AS `total_latency`,
 1 AS `avg_latency`,
 1 AS `max_latency`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `host_summary`
--

/*!50001 DROP VIEW IF EXISTS `host_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `host_summary` AS select if(isnull(`performance_schema`.`accounts`.`HOST`),'background',`performance_schema`.`accounts`.`HOST`) AS `host`,sum(`stmt`.`total`) AS `statements`,`sys`.`format_time`(sum(`stmt`.`total_latency`)) AS `statement_latency`,`sys`.`format_time`((sum(`stmt`.`total_latency`) / sum(`stmt`.`total`))) AS `statement_avg_latency`,sum(`stmt`.`full_scans`) AS `table_scans`,sum(`io`.`ios`) AS `file_ios`,`sys`.`format_time`(sum(`io`.`io_latency`)) AS `file_io_latency`,sum(`performance_schema`.`accounts`.`CURRENT_CONNECTIONS`) AS `current_connections`,sum(`performance_schema`.`accounts`.`TOTAL_CONNECTIONS`) AS `total_connections`,count(distinct `performance_schema`.`accounts`.`USER`) AS `unique_users`,`sys`.`format_bytes`(sum(`mem`.`current_allocated`)) AS `current_memory`,`sys`.`format_bytes`(sum(`mem`.`total_allocated`)) AS `total_memory_allocated` from (((`performance_schema`.`accounts` join `sys`.`x$host_summary_by_statement_latency` `stmt` on((`performance_schema`.`accounts`.`HOST` = `stmt`.`host`))) join `sys`.`x$host_summary_by_file_io` `io` on((`performance_schema`.`accounts`.`HOST` = `io`.`host`))) join `sys`.`x$memory_by_host_by_current_bytes` `mem` on((`performance_schema`.`accounts`.`HOST` = `mem`.`host`))) group by if(isnull(`performance_schema`.`accounts`.`HOST`),'background',`performance_schema`.`accounts`.`HOST`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `host_summary_by_file_io`
--

/*!50001 DROP VIEW IF EXISTS `host_summary_by_file_io`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `host_summary_by_file_io` AS select if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) AS `host`,sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR`) AS `ios`,`sys`.`format_time`(sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`)) AS `io_latency` from `performance_schema`.`events_waits_summary_by_host_by_event_name` where (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') group by if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) order by sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `host_summary_by_file_io_type`
--

/*!50001 DROP VIEW IF EXISTS `host_summary_by_file_io_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `host_summary_by_file_io_type` AS select if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) AS `host`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency` from `performance_schema`.`events_waits_summary_by_host_by_event_name` where ((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` like 'wait/io/file%') and (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `host_summary_by_stages`
--

/*!50001 DROP VIEW IF EXISTS `host_summary_by_stages`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `host_summary_by_stages` AS select if(isnull(`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`) AS `host`,`performance_schema`.`events_stages_summary_by_host_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_stages_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_stages_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_stages_summary_by_host_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency` from `performance_schema`.`events_stages_summary_by_host_by_event_name` where (`performance_schema`.`events_stages_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_stages_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `host_summary_by_statement_latency`
--

/*!50001 DROP VIEW IF EXISTS `host_summary_by_statement_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `host_summary_by_statement_latency` AS select if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`) AS `host`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`COUNT_STAR`) AS `total`,`sys`.`format_time`(sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`)) AS `total_latency`,`sys`.`format_time`(sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`MAX_TIMER_WAIT`)) AS `max_latency`,`sys`.`format_time`(sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_LOCK_TIME`)) AS `lock_latency`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_SENT`) AS `rows_sent`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_EXAMINED`) AS `rows_examined`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_AFFECTED`) AS `rows_affected`,(sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_INDEX_USED`) + sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_GOOD_INDEX_USED`)) AS `full_scans` from `performance_schema`.`events_statements_summary_by_host_by_event_name` group by if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`) order by sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `host_summary_by_statement_type`
--

/*!50001 DROP VIEW IF EXISTS `host_summary_by_statement_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `host_summary_by_statement_type` AS select if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`) AS `host`,substring_index(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`EVENT_NAME`,'/',-(1)) AS `statement`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_LOCK_TIME`) AS `lock_latency`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_SENT` AS `rows_sent`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_EXAMINED` AS `rows_examined`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_AFFECTED` AS `rows_affected`,(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_INDEX_USED` + `performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_GOOD_INDEX_USED`) AS `full_scans` from `performance_schema`.`events_statements_summary_by_host_by_event_name` where (`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `innodb_buffer_stats_by_schema`
--

/*!50001 DROP VIEW IF EXISTS `innodb_buffer_stats_by_schema`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `innodb_buffer_stats_by_schema` AS select if((locate('.',`ibp`.`TABLE_NAME`) = 0),'InnoDB System',replace(substring_index(`ibp`.`TABLE_NAME`,'.',1),'`','')) AS `object_schema`,`sys`.`format_bytes`(sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`))) AS `allocated`,`sys`.`format_bytes`(sum(`ibp`.`DATA_SIZE`)) AS `data`,count(`ibp`.`PAGE_NUMBER`) AS `pages`,count(if((`ibp`.`IS_HASHED` = 'YES'),1,0)) AS `pages_hashed`,count(if((`ibp`.`IS_OLD` = 'YES'),1,0)) AS `pages_old`,round((sum(`ibp`.`NUMBER_RECORDS`) / count(distinct `ibp`.`INDEX_NAME`)),0) AS `rows_cached` from `information_schema`.`innodb_buffer_page` `ibp` where (`ibp`.`TABLE_NAME` is not null) group by `object_schema` order by sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`)) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `innodb_buffer_stats_by_table`
--

/*!50001 DROP VIEW IF EXISTS `innodb_buffer_stats_by_table`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `innodb_buffer_stats_by_table` AS select if((locate('.',`ibp`.`TABLE_NAME`) = 0),'InnoDB System',replace(substring_index(`ibp`.`TABLE_NAME`,'.',1),'`','')) AS `object_schema`,replace(substring_index(`ibp`.`TABLE_NAME`,'.',-(1)),'`','') AS `object_name`,`sys`.`format_bytes`(sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`))) AS `allocated`,`sys`.`format_bytes`(sum(`ibp`.`DATA_SIZE`)) AS `data`,count(`ibp`.`PAGE_NUMBER`) AS `pages`,count(if((`ibp`.`IS_HASHED` = 'YES'),1,0)) AS `pages_hashed`,count(if((`ibp`.`IS_OLD` = 'YES'),1,0)) AS `pages_old`,round((sum(`ibp`.`NUMBER_RECORDS`) / count(distinct `ibp`.`INDEX_NAME`)),0) AS `rows_cached` from `information_schema`.`innodb_buffer_page` `ibp` where (`ibp`.`TABLE_NAME` is not null) group by `object_schema`,`object_name` order by sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`)) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `innodb_lock_waits`
--

/*!50001 DROP VIEW IF EXISTS `innodb_lock_waits`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `innodb_lock_waits` AS select `r`.`trx_wait_started` AS `wait_started`,timediff(now(),`r`.`trx_wait_started`) AS `wait_age`,timestampdiff(SECOND,`r`.`trx_wait_started`,now()) AS `wait_age_secs`,`rl`.`lock_table` AS `locked_table`,`rl`.`lock_index` AS `locked_index`,`rl`.`lock_type` AS `locked_type`,`r`.`trx_id` AS `waiting_trx_id`,`r`.`trx_started` AS `waiting_trx_started`,timediff(now(),`r`.`trx_started`) AS `waiting_trx_age`,`r`.`trx_rows_locked` AS `waiting_trx_rows_locked`,`r`.`trx_rows_modified` AS `waiting_trx_rows_modified`,`r`.`trx_mysql_thread_id` AS `waiting_pid`,`sys`.`format_statement`(`r`.`trx_query`) AS `waiting_query`,`rl`.`lock_id` AS `waiting_lock_id`,`rl`.`lock_mode` AS `waiting_lock_mode`,`b`.`trx_id` AS `blocking_trx_id`,`b`.`trx_mysql_thread_id` AS `blocking_pid`,`sys`.`format_statement`(`b`.`trx_query`) AS `blocking_query`,`bl`.`lock_id` AS `blocking_lock_id`,`bl`.`lock_mode` AS `blocking_lock_mode`,`b`.`trx_started` AS `blocking_trx_started`,timediff(now(),`b`.`trx_started`) AS `blocking_trx_age`,`b`.`trx_rows_locked` AS `blocking_trx_rows_locked`,`b`.`trx_rows_modified` AS `blocking_trx_rows_modified`,concat('KILL QUERY ',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_query`,concat('KILL ',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_connection` from ((((`information_schema`.`innodb_lock_waits` `w` join `information_schema`.`innodb_trx` `b` on((`b`.`trx_id` = `w`.`blocking_trx_id`))) join `information_schema`.`innodb_trx` `r` on((`r`.`trx_id` = `w`.`requesting_trx_id`))) join `information_schema`.`innodb_locks` `bl` on((`bl`.`lock_id` = `w`.`blocking_lock_id`))) join `information_schema`.`innodb_locks` `rl` on((`rl`.`lock_id` = `w`.`requested_lock_id`))) order by `r`.`trx_wait_started` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `io_by_thread_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `io_by_thread_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `io_by_thread_by_latency` AS select if(isnull(`performance_schema`.`threads`.`PROCESSLIST_ID`),substring_index(`performance_schema`.`threads`.`NAME`,'/',-(1)),concat(`performance_schema`.`threads`.`PROCESSLIST_USER`,'@',`performance_schema`.`threads`.`PROCESSLIST_HOST`)) AS `user`,sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`COUNT_STAR`) AS `total`,`sys`.`format_time`(sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT`)) AS `total_latency`,`sys`.`format_time`(min(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`MIN_TIMER_WAIT`)) AS `min_latency`,`sys`.`format_time`(avg(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`AVG_TIMER_WAIT`)) AS `avg_latency`,`sys`.`format_time`(max(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`MAX_TIMER_WAIT`)) AS `max_latency`,`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID` AS `thread_id`,`performance_schema`.`threads`.`PROCESSLIST_ID` AS `processlist_id` from (`performance_schema`.`events_waits_summary_by_thread_by_event_name` left join `performance_schema`.`threads` on((`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID` = `performance_schema`.`threads`.`THREAD_ID`))) where ((`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and (`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT` > 0)) group by `performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID`,`performance_schema`.`threads`.`PROCESSLIST_ID`,`user` order by sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `io_global_by_file_by_bytes`
--

/*!50001 DROP VIEW IF EXISTS `io_global_by_file_by_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `io_global_by_file_by_bytes` AS select `sys`.`format_path`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`) AS `file`,`performance_schema`.`file_summary_by_instance`.`COUNT_READ` AS `count_read`,`sys`.`format_bytes`(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ`) AS `total_read`,`sys`.`format_bytes`(ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` / nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_READ`,0)),0)) AS `avg_read`,`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE` AS `count_write`,`sys`.`format_bytes`(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`) AS `total_written`,`sys`.`format_bytes`(ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE` / nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`,0)),0.00)) AS `avg_write`,`sys`.`format_bytes`((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` + `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`)) AS `total`,ifnull(round((100 - ((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` / nullif((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` + `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`),0)) * 100)),2),0.00) AS `write_pct` from `performance_schema`.`file_summary_by_instance` order by (`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` + `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `io_global_by_file_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `io_global_by_file_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `io_global_by_file_by_latency` AS select `sys`.`format_path`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`) AS `file`,`performance_schema`.`file_summary_by_instance`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WAIT`) AS `total_latency`,`performance_schema`.`file_summary_by_instance`.`COUNT_READ` AS `count_read`,`sys`.`format_time`(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_READ`) AS `read_latency`,`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE` AS `count_write`,`sys`.`format_time`(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WRITE`) AS `write_latency`,`performance_schema`.`file_summary_by_instance`.`COUNT_MISC` AS `count_misc`,`sys`.`format_time`(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_MISC`) AS `misc_latency` from `performance_schema`.`file_summary_by_instance` order by `performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `io_global_by_wait_by_bytes`
--

/*!50001 DROP VIEW IF EXISTS `io_global_by_wait_by_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `io_global_by_wait_by_bytes` AS select substring_index(`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME`,'/',-(2)) AS `event_name`,`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`MIN_TIMER_WAIT`) AS `min_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,`performance_schema`.`file_summary_by_event_name`.`COUNT_READ` AS `count_read`,`sys`.`format_bytes`(`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`) AS `total_read`,`sys`.`format_bytes`(ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_READ`,0)),0)) AS `avg_read`,`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE` AS `count_write`,`sys`.`format_bytes`(`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE`) AS `total_written`,`sys`.`format_bytes`(ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE`,0)),0)) AS `avg_written`,`sys`.`format_bytes`((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` + `performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`)) AS `total_requested` from `performance_schema`.`file_summary_by_event_name` where ((`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and (`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` > 0)) order by (`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` + `performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `io_global_by_wait_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `io_global_by_wait_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `io_global_by_wait_by_latency` AS select substring_index(`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME`,'/',-(2)) AS `event_name`,`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_READ`) AS `read_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WRITE`) AS `write_latency`,`sys`.`format_time`(`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_MISC`) AS `misc_latency`,`performance_schema`.`file_summary_by_event_name`.`COUNT_READ` AS `count_read`,`sys`.`format_bytes`(`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`) AS `total_read`,`sys`.`format_bytes`(ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_READ`,0)),0)) AS `avg_read`,`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE` AS `count_write`,`sys`.`format_bytes`(`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE`) AS `total_written`,`sys`.`format_bytes`(ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE`,0)),0)) AS `avg_written` from `performance_schema`.`file_summary_by_event_name` where ((`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and (`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` > 0)) order by `performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `latest_file_io`
--

/*!50001 DROP VIEW IF EXISTS `latest_file_io`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `latest_file_io` AS select if(isnull(`information_schema`.`processlist`.`ID`),concat(substring_index(`performance_schema`.`threads`.`NAME`,'/',-(1)),':',`performance_schema`.`events_waits_history_long`.`THREAD_ID`),concat(`information_schema`.`processlist`.`USER`,'@',`information_schema`.`processlist`.`HOST`,':',`information_schema`.`processlist`.`ID`)) AS `thread`,`sys`.`format_path`(`performance_schema`.`events_waits_history_long`.`OBJECT_NAME`) AS `file`,`sys`.`format_time`(`performance_schema`.`events_waits_history_long`.`TIMER_WAIT`) AS `latency`,`performance_schema`.`events_waits_history_long`.`OPERATION` AS `operation`,`sys`.`format_bytes`(`performance_schema`.`events_waits_history_long`.`NUMBER_OF_BYTES`) AS `requested` from ((`performance_schema`.`events_waits_history_long` join `performance_schema`.`threads` on((`performance_schema`.`events_waits_history_long`.`THREAD_ID` = `performance_schema`.`threads`.`THREAD_ID`))) left join `information_schema`.`processlist` on((`performance_schema`.`threads`.`PROCESSLIST_ID` = `information_schema`.`processlist`.`ID`))) where ((`performance_schema`.`events_waits_history_long`.`OBJECT_NAME` is not null) and (`performance_schema`.`events_waits_history_long`.`EVENT_NAME` like 'wait/io/file/%')) order by `performance_schema`.`events_waits_history_long`.`TIMER_START` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `memory_by_host_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `memory_by_host_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `memory_by_host_by_current_bytes` AS select if(isnull(`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`) AS `host`,sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_COUNT_USED`) AS `current_count_used`,`sys`.`format_bytes`(sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_allocated`,`sys`.`format_bytes`(ifnull((sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) / nullif(sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_COUNT_USED`),0)),0)) AS `current_avg_alloc`,`sys`.`format_bytes`(max(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_max_alloc`,`sys`.`format_bytes`(sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`SUM_NUMBER_OF_BYTES_ALLOC`)) AS `total_allocated` from `performance_schema`.`memory_summary_by_host_by_event_name` group by if(isnull(`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`) order by sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `memory_by_thread_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `memory_by_thread_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `memory_by_thread_by_current_bytes` AS select `mt`.`THREAD_ID` AS `thread_id`,if((`t`.`NAME` = 'thread/sql/one_connection'),concat(`t`.`PROCESSLIST_USER`,'@',`t`.`PROCESSLIST_HOST`),replace(`t`.`NAME`,'thread/','')) AS `user`,sum(`mt`.`CURRENT_COUNT_USED`) AS `current_count_used`,`sys`.`format_bytes`(sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_allocated`,`sys`.`format_bytes`(ifnull((sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) / nullif(sum(`mt`.`CURRENT_COUNT_USED`),0)),0)) AS `current_avg_alloc`,`sys`.`format_bytes`(max(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_max_alloc`,`sys`.`format_bytes`(sum(`mt`.`SUM_NUMBER_OF_BYTES_ALLOC`)) AS `total_allocated` from (`performance_schema`.`memory_summary_by_thread_by_event_name` `mt` join `performance_schema`.`threads` `t` on((`mt`.`THREAD_ID` = `t`.`THREAD_ID`))) group by `mt`.`THREAD_ID`,if((`t`.`NAME` = 'thread/sql/one_connection'),concat(`t`.`PROCESSLIST_USER`,'@',`t`.`PROCESSLIST_HOST`),replace(`t`.`NAME`,'thread/','')) order by sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `memory_by_user_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `memory_by_user_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `memory_by_user_by_current_bytes` AS select if(isnull(`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`) AS `user`,sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_COUNT_USED`) AS `current_count_used`,`sys`.`format_bytes`(sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_allocated`,`sys`.`format_bytes`(ifnull((sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) / nullif(sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_COUNT_USED`),0)),0)) AS `current_avg_alloc`,`sys`.`format_bytes`(max(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `current_max_alloc`,`sys`.`format_bytes`(sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`SUM_NUMBER_OF_BYTES_ALLOC`)) AS `total_allocated` from `performance_schema`.`memory_summary_by_user_by_event_name` group by if(isnull(`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`) order by sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `memory_global_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `memory_global_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `memory_global_by_current_bytes` AS select `performance_schema`.`memory_summary_global_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_COUNT_USED` AS `current_count`,`sys`.`format_bytes`(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `current_alloc`,`sys`.`format_bytes`(ifnull((`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` / nullif(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_COUNT_USED`,0)),0)) AS `current_avg_alloc`,`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_COUNT_USED` AS `high_count`,`sys`.`format_bytes`(`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_NUMBER_OF_BYTES_USED`) AS `high_alloc`,`sys`.`format_bytes`(ifnull((`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_NUMBER_OF_BYTES_USED` / nullif(`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_COUNT_USED`,0)),0)) AS `high_avg_alloc` from `performance_schema`.`memory_summary_global_by_event_name` where (`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` > 0) order by `performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `memory_global_total`
--

/*!50001 DROP VIEW IF EXISTS `memory_global_total`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `memory_global_total` AS select `sys`.`format_bytes`(sum(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`)) AS `total_allocated` from `performance_schema`.`memory_summary_global_by_event_name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `metrics`
--

/*!50001 DROP VIEW IF EXISTS `metrics`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `metrics` AS (select lower(`performance_schema`.`global_status`.`VARIABLE_NAME`) AS `Variable_name`,`performance_schema`.`global_status`.`VARIABLE_VALUE` AS `Variable_value`,'Global Status' AS `Type`,'YES' AS `Enabled` from `performance_schema`.`global_status`) union all (select `information_schema`.`innodb_metrics`.`NAME` AS `Variable_name`,`information_schema`.`innodb_metrics`.`COUNT` AS `Variable_value`,concat('InnoDB Metrics - ',`information_schema`.`innodb_metrics`.`SUBSYSTEM`) AS `Type`,if((`information_schema`.`innodb_metrics`.`STATUS` = 'enabled'),'YES','NO') AS `Enabled` from `information_schema`.`innodb_metrics` where (`information_schema`.`innodb_metrics`.`NAME` not in ('lock_row_lock_time','lock_row_lock_time_avg','lock_row_lock_time_max','lock_row_lock_waits','buffer_pool_reads','buffer_pool_read_requests','buffer_pool_write_requests','buffer_pool_wait_free','buffer_pool_read_ahead','buffer_pool_read_ahead_evicted','buffer_pool_pages_total','buffer_pool_pages_misc','buffer_pool_pages_data','buffer_pool_bytes_data','buffer_pool_pages_dirty','buffer_pool_bytes_dirty','buffer_pool_pages_free','buffer_pages_created','buffer_pages_written','buffer_pages_read','buffer_data_reads','buffer_data_written','file_num_open_files','os_log_bytes_written','os_log_fsyncs','os_log_pending_fsyncs','os_log_pending_writes','log_waits','log_write_requests','log_writes','innodb_dblwr_writes','innodb_dblwr_pages_written','innodb_page_size'))) union all (select 'memory_current_allocated' AS `Variable_name`,sum(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `Variable_value`,'Performance Schema' AS `Type`,if(((select count(0) from `performance_schema`.`setup_instruments` where ((`performance_schema`.`setup_instruments`.`NAME` like 'memory/%') and (`performance_schema`.`setup_instruments`.`ENABLED` = 'YES'))) = 0),'NO',if(((select count(0) from `performance_schema`.`setup_instruments` where ((`performance_schema`.`setup_instruments`.`NAME` like 'memory/%') and (`performance_schema`.`setup_instruments`.`ENABLED` = 'YES'))) = (select count(0) from `performance_schema`.`setup_instruments` where (`performance_schema`.`setup_instruments`.`NAME` like 'memory/%'))),'YES','PARTIAL')) AS `Enabled` from `performance_schema`.`memory_summary_global_by_event_name`) union all (select 'memory_total_allocated' AS `Variable_name`,sum(`performance_schema`.`memory_summary_global_by_event_name`.`SUM_NUMBER_OF_BYTES_ALLOC`) AS `Variable_value`,'Performance Schema' AS `Type`,if(((select count(0) from `performance_schema`.`setup_instruments` where ((`performance_schema`.`setup_instruments`.`NAME` like 'memory/%') and (`performance_schema`.`setup_instruments`.`ENABLED` = 'YES'))) = 0),'NO',if(((select count(0) from `performance_schema`.`setup_instruments` where ((`performance_schema`.`setup_instruments`.`NAME` like 'memory/%') and (`performance_schema`.`setup_instruments`.`ENABLED` = 'YES'))) = (select count(0) from `performance_schema`.`setup_instruments` where (`performance_schema`.`setup_instruments`.`NAME` like 'memory/%'))),'YES','PARTIAL')) AS `Enabled` from `performance_schema`.`memory_summary_global_by_event_name`) union all (select 'NOW()' AS `Variable_name`,now(3) AS `Variable_value`,'System Time' AS `Type`,'YES' AS `Enabled`) union all (select 'UNIX_TIMESTAMP()' AS `Variable_name`,round(unix_timestamp(now(3)),3) AS `Variable_value`,'System Time' AS `Type`,'YES' AS `Enabled`) order by `Type`,`Variable_name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `processlist`
--

/*!50001 DROP VIEW IF EXISTS `processlist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `processlist` AS select `pps`.`THREAD_ID` AS `thd_id`,`pps`.`PROCESSLIST_ID` AS `conn_id`,if((`pps`.`NAME` = 'thread/sql/one_connection'),concat(`pps`.`PROCESSLIST_USER`,'@',`pps`.`PROCESSLIST_HOST`),replace(`pps`.`NAME`,'thread/','')) AS `user`,`pps`.`PROCESSLIST_DB` AS `db`,`pps`.`PROCESSLIST_COMMAND` AS `command`,`pps`.`PROCESSLIST_STATE` AS `state`,`pps`.`PROCESSLIST_TIME` AS `time`,`sys`.`format_statement`(`pps`.`PROCESSLIST_INFO`) AS `current_statement`,if(isnull(`esc`.`END_EVENT_ID`),`sys`.`format_time`(`esc`.`TIMER_WAIT`),NULL) AS `statement_latency`,if(isnull(`esc`.`END_EVENT_ID`),round((100 * (`estc`.`WORK_COMPLETED` / `estc`.`WORK_ESTIMATED`)),2),NULL) AS `progress`,`sys`.`format_time`(`esc`.`LOCK_TIME`) AS `lock_latency`,`esc`.`ROWS_EXAMINED` AS `rows_examined`,`esc`.`ROWS_SENT` AS `rows_sent`,`esc`.`ROWS_AFFECTED` AS `rows_affected`,`esc`.`CREATED_TMP_TABLES` AS `tmp_tables`,`esc`.`CREATED_TMP_DISK_TABLES` AS `tmp_disk_tables`,if(((`esc`.`NO_GOOD_INDEX_USED` > 0) or (`esc`.`NO_INDEX_USED` > 0)),'YES','NO') AS `full_scan`,if((`esc`.`END_EVENT_ID` is not null),`sys`.`format_statement`(`esc`.`SQL_TEXT`),NULL) AS `last_statement`,if((`esc`.`END_EVENT_ID` is not null),`sys`.`format_time`(`esc`.`TIMER_WAIT`),NULL) AS `last_statement_latency`,`sys`.`format_bytes`(`mem`.`current_allocated`) AS `current_memory`,`ewc`.`EVENT_NAME` AS `last_wait`,if((isnull(`ewc`.`END_EVENT_ID`) and (`ewc`.`EVENT_NAME` is not null)),'Still Waiting',`sys`.`format_time`(`ewc`.`TIMER_WAIT`)) AS `last_wait_latency`,`ewc`.`SOURCE` AS `source`,`sys`.`format_time`(`etc`.`TIMER_WAIT`) AS `trx_latency`,`etc`.`STATE` AS `trx_state`,`etc`.`AUTOCOMMIT` AS `trx_autocommit`,`conattr_pid`.`ATTR_VALUE` AS `pid`,`conattr_progname`.`ATTR_VALUE` AS `program_name` from (((((((`performance_schema`.`threads` `pps` left join `performance_schema`.`events_waits_current` `ewc` on((`pps`.`THREAD_ID` = `ewc`.`THREAD_ID`))) left join `performance_schema`.`events_stages_current` `estc` on((`pps`.`THREAD_ID` = `estc`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `esc` on((`pps`.`THREAD_ID` = `esc`.`THREAD_ID`))) left join `performance_schema`.`events_transactions_current` `etc` on((`pps`.`THREAD_ID` = `etc`.`THREAD_ID`))) left join `sys`.`x$memory_by_thread_by_current_bytes` `mem` on((`pps`.`THREAD_ID` = `mem`.`thread_id`))) left join `performance_schema`.`session_connect_attrs` `conattr_pid` on(((`conattr_pid`.`PROCESSLIST_ID` = `pps`.`PROCESSLIST_ID`) and (`conattr_pid`.`ATTR_NAME` = '_pid')))) left join `performance_schema`.`session_connect_attrs` `conattr_progname` on(((`conattr_progname`.`PROCESSLIST_ID` = `pps`.`PROCESSLIST_ID`) and (`conattr_progname`.`ATTR_NAME` = 'program_name')))) order by `pps`.`PROCESSLIST_TIME` desc,`last_wait_latency` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ps_check_lost_instrumentation`
--

/*!50001 DROP VIEW IF EXISTS `ps_check_lost_instrumentation`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `ps_check_lost_instrumentation` AS select `performance_schema`.`global_status`.`VARIABLE_NAME` AS `variable_name`,`performance_schema`.`global_status`.`VARIABLE_VALUE` AS `variable_value` from `performance_schema`.`global_status` where ((`performance_schema`.`global_status`.`VARIABLE_NAME` like 'perf%lost') and (`performance_schema`.`global_status`.`VARIABLE_VALUE` > 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_auto_increment_columns`
--

/*!50001 DROP VIEW IF EXISTS `schema_auto_increment_columns`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_auto_increment_columns` AS select `information_schema`.`columns`.`TABLE_SCHEMA` AS `table_schema`,`information_schema`.`columns`.`TABLE_NAME` AS `table_name`,`information_schema`.`columns`.`COLUMN_NAME` AS `column_name`,`information_schema`.`columns`.`DATA_TYPE` AS `data_type`,`information_schema`.`columns`.`COLUMN_TYPE` AS `column_type`,(locate('unsigned',`information_schema`.`columns`.`COLUMN_TYPE`) = 0) AS `is_signed`,(locate('unsigned',`information_schema`.`columns`.`COLUMN_TYPE`) > 0) AS `is_unsigned`,((case `information_schema`.`columns`.`DATA_TYPE` when 'tinyint' then 255 when 'smallint' then 65535 when 'mediumint' then 16777215 when 'int' then 4294967295 when 'bigint' then 18446744073709551615 end) >> if((locate('unsigned',`information_schema`.`columns`.`COLUMN_TYPE`) > 0),0,1)) AS `max_value`,`information_schema`.`tables`.`AUTO_INCREMENT` AS `auto_increment`,(`information_schema`.`tables`.`AUTO_INCREMENT` / ((case `information_schema`.`columns`.`DATA_TYPE` when 'tinyint' then 255 when 'smallint' then 65535 when 'mediumint' then 16777215 when 'int' then 4294967295 when 'bigint' then 18446744073709551615 end) >> if((locate('unsigned',`information_schema`.`columns`.`COLUMN_TYPE`) > 0),0,1))) AS `auto_increment_ratio` from (`information_schema`.`columns` join `information_schema`.`tables` on(((`information_schema`.`columns`.`TABLE_SCHEMA` = `information_schema`.`tables`.`TABLE_SCHEMA`) and (`information_schema`.`columns`.`TABLE_NAME` = `information_schema`.`tables`.`TABLE_NAME`)))) where ((`information_schema`.`columns`.`TABLE_SCHEMA` not in ('mysql','sys','INFORMATION_SCHEMA','performance_schema')) and (`information_schema`.`tables`.`TABLE_TYPE` = 'BASE TABLE') and (`information_schema`.`columns`.`EXTRA` = 'auto_increment')) order by (`information_schema`.`tables`.`AUTO_INCREMENT` / ((case `information_schema`.`columns`.`DATA_TYPE` when 'tinyint' then 255 when 'smallint' then 65535 when 'mediumint' then 16777215 when 'int' then 4294967295 when 'bigint' then 18446744073709551615 end) >> if((locate('unsigned',`information_schema`.`columns`.`COLUMN_TYPE`) > 0),0,1))) desc,((case `information_schema`.`columns`.`DATA_TYPE` when 'tinyint' then 255 when 'smallint' then 65535 when 'mediumint' then 16777215 when 'int' then 4294967295 when 'bigint' then 18446744073709551615 end) >> if((locate('unsigned',`information_schema`.`columns`.`COLUMN_TYPE`) > 0),0,1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_index_statistics`
--

/*!50001 DROP VIEW IF EXISTS `schema_index_statistics`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_index_statistics` AS select `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA` AS `table_schema`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_NAME` AS `table_name`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` AS `index_name`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_FETCH` AS `rows_selected`,`sys`.`format_time`(`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_FETCH`) AS `select_latency`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_INSERT` AS `rows_inserted`,`sys`.`format_time`(`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_INSERT`) AS `insert_latency`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_UPDATE` AS `rows_updated`,`sys`.`format_time`(`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_UPDATE`) AS `update_latency`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_DELETE` AS `rows_deleted`,`sys`.`format_time`(`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_INSERT`) AS `delete_latency` from `performance_schema`.`table_io_waits_summary_by_index_usage` where (`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` is not null) order by `performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_object_overview`
--

/*!50001 DROP VIEW IF EXISTS `schema_object_overview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_object_overview` AS select `information_schema`.`routines`.`ROUTINE_SCHEMA` AS `db`,`information_schema`.`routines`.`ROUTINE_TYPE` AS `object_type`,count(0) AS `count` from `information_schema`.`routines` group by `information_schema`.`routines`.`ROUTINE_SCHEMA`,`information_schema`.`routines`.`ROUTINE_TYPE` union select `information_schema`.`tables`.`TABLE_SCHEMA` AS `TABLE_SCHEMA`,`information_schema`.`tables`.`TABLE_TYPE` AS `TABLE_TYPE`,count(0) AS `COUNT(*)` from `information_schema`.`tables` group by `information_schema`.`tables`.`TABLE_SCHEMA`,`information_schema`.`tables`.`TABLE_TYPE` union select `information_schema`.`statistics`.`TABLE_SCHEMA` AS `TABLE_SCHEMA`,concat('INDEX (',`information_schema`.`statistics`.`INDEX_TYPE`,')') AS `CONCAT('INDEX (', INDEX_TYPE, ')')`,count(0) AS `COUNT(*)` from `information_schema`.`statistics` group by `information_schema`.`statistics`.`TABLE_SCHEMA`,`information_schema`.`statistics`.`INDEX_TYPE` union select `information_schema`.`triggers`.`TRIGGER_SCHEMA` AS `TRIGGER_SCHEMA`,'TRIGGER' AS `TRIGGER`,count(0) AS `COUNT(*)` from `information_schema`.`triggers` group by `information_schema`.`triggers`.`TRIGGER_SCHEMA` union select `information_schema`.`events`.`EVENT_SCHEMA` AS `EVENT_SCHEMA`,'EVENT' AS `EVENT`,count(0) AS `COUNT(*)` from `information_schema`.`events` group by `information_schema`.`events`.`EVENT_SCHEMA` order by `db`,`object_type` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_redundant_indexes`
--

/*!50001 DROP VIEW IF EXISTS `schema_redundant_indexes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_redundant_indexes` AS select `redundant_keys`.`table_schema` AS `table_schema`,`redundant_keys`.`table_name` AS `table_name`,`redundant_keys`.`index_name` AS `redundant_index_name`,`redundant_keys`.`index_columns` AS `redundant_index_columns`,`redundant_keys`.`non_unique` AS `redundant_index_non_unique`,`dominant_keys`.`index_name` AS `dominant_index_name`,`dominant_keys`.`index_columns` AS `dominant_index_columns`,`dominant_keys`.`non_unique` AS `dominant_index_non_unique`,if((`redundant_keys`.`subpart_exists` or `dominant_keys`.`subpart_exists`),1,0) AS `subpart_exists`,concat('ALTER TABLE `',`redundant_keys`.`table_schema`,'`.`',`redundant_keys`.`table_name`,'` DROP INDEX `',`redundant_keys`.`index_name`,'`') AS `sql_drop_index` from (`sys`.`x$schema_flattened_keys` `redundant_keys` join `sys`.`x$schema_flattened_keys` `dominant_keys` on(((`redundant_keys`.`table_schema` = `dominant_keys`.`table_schema`) and (`redundant_keys`.`table_name` = `dominant_keys`.`table_name`)))) where ((`redundant_keys`.`index_name` <> `dominant_keys`.`index_name`) and (((`redundant_keys`.`index_columns` = `dominant_keys`.`index_columns`) and ((`redundant_keys`.`non_unique` > `dominant_keys`.`non_unique`) or ((`redundant_keys`.`non_unique` = `dominant_keys`.`non_unique`) and (if((`redundant_keys`.`index_name` = 'PRIMARY'),'',`redundant_keys`.`index_name`) > if((`dominant_keys`.`index_name` = 'PRIMARY'),'',`dominant_keys`.`index_name`))))) or ((locate(concat(`redundant_keys`.`index_columns`,','),`dominant_keys`.`index_columns`) = 1) and (`redundant_keys`.`non_unique` = 1)) or ((locate(concat(`dominant_keys`.`index_columns`,','),`redundant_keys`.`index_columns`) = 1) and (`dominant_keys`.`non_unique` = 0)))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_table_lock_waits`
--

/*!50001 DROP VIEW IF EXISTS `schema_table_lock_waits`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_table_lock_waits` AS select `g`.`OBJECT_SCHEMA` AS `object_schema`,`g`.`OBJECT_NAME` AS `object_name`,`pt`.`THREAD_ID` AS `waiting_thread_id`,`pt`.`PROCESSLIST_ID` AS `waiting_pid`,`sys`.`ps_thread_account`(`p`.`OWNER_THREAD_ID`) AS `waiting_account`,`p`.`LOCK_TYPE` AS `waiting_lock_type`,`p`.`LOCK_DURATION` AS `waiting_lock_duration`,`sys`.`format_statement`(`pt`.`PROCESSLIST_INFO`) AS `waiting_query`,`pt`.`PROCESSLIST_TIME` AS `waiting_query_secs`,`ps`.`ROWS_AFFECTED` AS `waiting_query_rows_affected`,`ps`.`ROWS_EXAMINED` AS `waiting_query_rows_examined`,`gt`.`THREAD_ID` AS `blocking_thread_id`,`gt`.`PROCESSLIST_ID` AS `blocking_pid`,`sys`.`ps_thread_account`(`g`.`OWNER_THREAD_ID`) AS `blocking_account`,`g`.`LOCK_TYPE` AS `blocking_lock_type`,`g`.`LOCK_DURATION` AS `blocking_lock_duration`,concat('KILL QUERY ',`gt`.`PROCESSLIST_ID`) AS `sql_kill_blocking_query`,concat('KILL ',`gt`.`PROCESSLIST_ID`) AS `sql_kill_blocking_connection` from (((((`performance_schema`.`metadata_locks` `g` join `performance_schema`.`metadata_locks` `p` on(((`g`.`OBJECT_TYPE` = `p`.`OBJECT_TYPE`) and (`g`.`OBJECT_SCHEMA` = `p`.`OBJECT_SCHEMA`) and (`g`.`OBJECT_NAME` = `p`.`OBJECT_NAME`) and (`g`.`LOCK_STATUS` = 'GRANTED') and (`p`.`LOCK_STATUS` = 'PENDING')))) join `performance_schema`.`threads` `gt` on((`g`.`OWNER_THREAD_ID` = `gt`.`THREAD_ID`))) join `performance_schema`.`threads` `pt` on((`p`.`OWNER_THREAD_ID` = `pt`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `gs` on((`g`.`OWNER_THREAD_ID` = `gs`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `ps` on((`p`.`OWNER_THREAD_ID` = `ps`.`THREAD_ID`))) where (`g`.`OBJECT_TYPE` = 'TABLE') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_table_statistics`
--

/*!50001 DROP VIEW IF EXISTS `schema_table_statistics`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_table_statistics` AS select `pst`.`OBJECT_SCHEMA` AS `table_schema`,`pst`.`OBJECT_NAME` AS `table_name`,`sys`.`format_time`(`pst`.`SUM_TIMER_WAIT`) AS `total_latency`,`pst`.`COUNT_FETCH` AS `rows_fetched`,`sys`.`format_time`(`pst`.`SUM_TIMER_FETCH`) AS `fetch_latency`,`pst`.`COUNT_INSERT` AS `rows_inserted`,`sys`.`format_time`(`pst`.`SUM_TIMER_INSERT`) AS `insert_latency`,`pst`.`COUNT_UPDATE` AS `rows_updated`,`sys`.`format_time`(`pst`.`SUM_TIMER_UPDATE`) AS `update_latency`,`pst`.`COUNT_DELETE` AS `rows_deleted`,`sys`.`format_time`(`pst`.`SUM_TIMER_DELETE`) AS `delete_latency`,`fsbi`.`count_read` AS `io_read_requests`,`sys`.`format_bytes`(`fsbi`.`sum_number_of_bytes_read`) AS `io_read`,`sys`.`format_time`(`fsbi`.`sum_timer_read`) AS `io_read_latency`,`fsbi`.`count_write` AS `io_write_requests`,`sys`.`format_bytes`(`fsbi`.`sum_number_of_bytes_write`) AS `io_write`,`sys`.`format_time`(`fsbi`.`sum_timer_write`) AS `io_write_latency`,`fsbi`.`count_misc` AS `io_misc_requests`,`sys`.`format_time`(`fsbi`.`sum_timer_misc`) AS `io_misc_latency` from (`performance_schema`.`table_io_waits_summary_by_table` `pst` left join `sys`.`x$ps_schema_table_statistics_io` `fsbi` on(((`pst`.`OBJECT_SCHEMA` = `fsbi`.`table_schema`) and (`pst`.`OBJECT_NAME` = `fsbi`.`table_name`)))) order by `pst`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_table_statistics_with_buffer`
--

/*!50001 DROP VIEW IF EXISTS `schema_table_statistics_with_buffer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_table_statistics_with_buffer` AS select `pst`.`OBJECT_SCHEMA` AS `table_schema`,`pst`.`OBJECT_NAME` AS `table_name`,`pst`.`COUNT_FETCH` AS `rows_fetched`,`sys`.`format_time`(`pst`.`SUM_TIMER_FETCH`) AS `fetch_latency`,`pst`.`COUNT_INSERT` AS `rows_inserted`,`sys`.`format_time`(`pst`.`SUM_TIMER_INSERT`) AS `insert_latency`,`pst`.`COUNT_UPDATE` AS `rows_updated`,`sys`.`format_time`(`pst`.`SUM_TIMER_UPDATE`) AS `update_latency`,`pst`.`COUNT_DELETE` AS `rows_deleted`,`sys`.`format_time`(`pst`.`SUM_TIMER_DELETE`) AS `delete_latency`,`fsbi`.`count_read` AS `io_read_requests`,`sys`.`format_bytes`(`fsbi`.`sum_number_of_bytes_read`) AS `io_read`,`sys`.`format_time`(`fsbi`.`sum_timer_read`) AS `io_read_latency`,`fsbi`.`count_write` AS `io_write_requests`,`sys`.`format_bytes`(`fsbi`.`sum_number_of_bytes_write`) AS `io_write`,`sys`.`format_time`(`fsbi`.`sum_timer_write`) AS `io_write_latency`,`fsbi`.`count_misc` AS `io_misc_requests`,`sys`.`format_time`(`fsbi`.`sum_timer_misc`) AS `io_misc_latency`,`sys`.`format_bytes`(`ibp`.`allocated`) AS `innodb_buffer_allocated`,`sys`.`format_bytes`(`ibp`.`data`) AS `innodb_buffer_data`,`sys`.`format_bytes`((`ibp`.`allocated` - `ibp`.`data`)) AS `innodb_buffer_free`,`ibp`.`pages` AS `innodb_buffer_pages`,`ibp`.`pages_hashed` AS `innodb_buffer_pages_hashed`,`ibp`.`pages_old` AS `innodb_buffer_pages_old`,`ibp`.`rows_cached` AS `innodb_buffer_rows_cached` from ((`performance_schema`.`table_io_waits_summary_by_table` `pst` left join `sys`.`x$ps_schema_table_statistics_io` `fsbi` on(((`pst`.`OBJECT_SCHEMA` = `fsbi`.`table_schema`) and (`pst`.`OBJECT_NAME` = `fsbi`.`table_name`)))) left join `sys`.`x$innodb_buffer_stats_by_table` `ibp` on(((`pst`.`OBJECT_SCHEMA` = `ibp`.`object_schema`) and (`pst`.`OBJECT_NAME` = `ibp`.`object_name`)))) order by `pst`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_tables_with_full_table_scans`
--

/*!50001 DROP VIEW IF EXISTS `schema_tables_with_full_table_scans`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_tables_with_full_table_scans` AS select `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA` AS `object_schema`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_NAME` AS `object_name`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` AS `rows_full_scanned`,`sys`.`format_time`(`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_WAIT`) AS `latency` from `performance_schema`.`table_io_waits_summary_by_index_usage` where (isnull(`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME`) and (`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` > 0)) order by `performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `schema_unused_indexes`
--

/*!50001 DROP VIEW IF EXISTS `schema_unused_indexes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `schema_unused_indexes` AS select `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA` AS `object_schema`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_NAME` AS `object_name`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` AS `index_name` from `performance_schema`.`table_io_waits_summary_by_index_usage` where ((`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` is not null) and (`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_STAR` = 0) and (`performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA` <> 'mysql') and (`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` <> 'PRIMARY')) order by `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_NAME` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `session`
--

/*!50001 DROP VIEW IF EXISTS `session`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `session` AS select `processlist`.`thd_id` AS `thd_id`,`processlist`.`conn_id` AS `conn_id`,`processlist`.`user` AS `user`,`processlist`.`db` AS `db`,`processlist`.`command` AS `command`,`processlist`.`state` AS `state`,`processlist`.`time` AS `time`,`processlist`.`current_statement` AS `current_statement`,`processlist`.`statement_latency` AS `statement_latency`,`processlist`.`progress` AS `progress`,`processlist`.`lock_latency` AS `lock_latency`,`processlist`.`rows_examined` AS `rows_examined`,`processlist`.`rows_sent` AS `rows_sent`,`processlist`.`rows_affected` AS `rows_affected`,`processlist`.`tmp_tables` AS `tmp_tables`,`processlist`.`tmp_disk_tables` AS `tmp_disk_tables`,`processlist`.`full_scan` AS `full_scan`,`processlist`.`last_statement` AS `last_statement`,`processlist`.`last_statement_latency` AS `last_statement_latency`,`processlist`.`current_memory` AS `current_memory`,`processlist`.`last_wait` AS `last_wait`,`processlist`.`last_wait_latency` AS `last_wait_latency`,`processlist`.`source` AS `source`,`processlist`.`trx_latency` AS `trx_latency`,`processlist`.`trx_state` AS `trx_state`,`processlist`.`trx_autocommit` AS `trx_autocommit`,`processlist`.`pid` AS `pid`,`processlist`.`program_name` AS `program_name` from `sys`.`processlist` where ((`processlist`.`conn_id` is not null) and (`processlist`.`command` <> 'Daemon')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `session_ssl_status`
--

/*!50001 DROP VIEW IF EXISTS `session_ssl_status`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `session_ssl_status` AS select `sslver`.`THREAD_ID` AS `thread_id`,`sslver`.`VARIABLE_VALUE` AS `ssl_version`,`sslcip`.`VARIABLE_VALUE` AS `ssl_cipher`,`sslreuse`.`VARIABLE_VALUE` AS `ssl_sessions_reused` from ((`performance_schema`.`status_by_thread` `sslver` left join `performance_schema`.`status_by_thread` `sslcip` on(((`sslcip`.`THREAD_ID` = `sslver`.`THREAD_ID`) and (`sslcip`.`VARIABLE_NAME` = 'Ssl_cipher')))) left join `performance_schema`.`status_by_thread` `sslreuse` on(((`sslreuse`.`THREAD_ID` = `sslver`.`THREAD_ID`) and (`sslreuse`.`VARIABLE_NAME` = 'Ssl_sessions_reused')))) where (`sslver`.`VARIABLE_NAME` = 'Ssl_version') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `statement_analysis`
--

/*!50001 DROP VIEW IF EXISTS `statement_analysis`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `statement_analysis` AS select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`) AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,if(((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` > 0) or (`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` > 0)),'*','') AS `full_scan`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` AS `err_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` AS `warn_count`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`MAX_TIMER_WAIT`) AS `max_latency`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`AVG_TIMER_WAIT`) AS `avg_latency`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`SUM_LOCK_TIME`) AS `lock_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` AS `rows_sent`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `rows_sent_avg`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` AS `rows_examined`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `rows_examined_avg`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_AFFECTED` AS `rows_affected`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_AFFECTED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `rows_affected_avg`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` AS `tmp_tables`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` AS `tmp_disk_tables`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` AS `rows_sorted`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES` AS `sort_merge_passes`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen` from `performance_schema`.`events_statements_summary_by_digest` order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `statements_with_errors_or_warnings`
--

/*!50001 DROP VIEW IF EXISTS `statements_with_errors_or_warnings`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `statements_with_errors_or_warnings` AS select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`) AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` AS `errors`,(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100) AS `error_pct`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` AS `warnings`,(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100) AS `warning_pct`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where ((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` > 0) or (`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` > 0)) order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` desc,`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `statements_with_full_table_scans`
--

/*!50001 DROP VIEW IF EXISTS `statements_with_full_table_scans`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `statements_with_full_table_scans` AS select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`) AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`) AS `total_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` AS `no_index_used_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` AS `no_good_index_used_count`,round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100),0) AS `no_index_used_pct`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` AS `rows_sent`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` AS `rows_examined`,round((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` / `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`),0) AS `rows_sent_avg`,round((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` / `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`),0) AS `rows_examined_avg`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where (((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` > 0) or (`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` > 0)) and (not((`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` like 'SHOW%')))) order by round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100),0) desc,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `statements_with_runtimes_in_95th_percentile`
--

/*!50001 DROP VIEW IF EXISTS `statements_with_runtimes_in_95th_percentile`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `statements_with_runtimes_in_95th_percentile` AS select `sys`.`format_statement`(`stmts`.`DIGEST_TEXT`) AS `query`,`stmts`.`SCHEMA_NAME` AS `db`,if(((`stmts`.`SUM_NO_GOOD_INDEX_USED` > 0) or (`stmts`.`SUM_NO_INDEX_USED` > 0)),'*','') AS `full_scan`,`stmts`.`COUNT_STAR` AS `exec_count`,`stmts`.`SUM_ERRORS` AS `err_count`,`stmts`.`SUM_WARNINGS` AS `warn_count`,`sys`.`format_time`(`stmts`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`stmts`.`MAX_TIMER_WAIT`) AS `max_latency`,`sys`.`format_time`(`stmts`.`AVG_TIMER_WAIT`) AS `avg_latency`,`stmts`.`SUM_ROWS_SENT` AS `rows_sent`,round(ifnull((`stmts`.`SUM_ROWS_SENT` / nullif(`stmts`.`COUNT_STAR`,0)),0),0) AS `rows_sent_avg`,`stmts`.`SUM_ROWS_EXAMINED` AS `rows_examined`,round(ifnull((`stmts`.`SUM_ROWS_EXAMINED` / nullif(`stmts`.`COUNT_STAR`,0)),0),0) AS `rows_examined_avg`,`stmts`.`FIRST_SEEN` AS `first_seen`,`stmts`.`LAST_SEEN` AS `last_seen`,`stmts`.`DIGEST` AS `digest` from (`performance_schema`.`events_statements_summary_by_digest` `stmts` join `sys`.`x$ps_digest_95th_percentile_by_avg_us` `top_percentile` on((round((`stmts`.`AVG_TIMER_WAIT` / 1000000),0) >= `top_percentile`.`avg_us`))) order by `stmts`.`AVG_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `statements_with_sorting`
--

/*!50001 DROP VIEW IF EXISTS `statements_with_sorting`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `statements_with_sorting` AS select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`) AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`) AS `total_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES` AS `sort_merge_passes`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `avg_sort_merges`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_SCAN` AS `sorts_using_scans`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_RANGE` AS `sort_using_range`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` AS `rows_sorted`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `avg_rows_sorted`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where (`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` > 0) order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `statements_with_temp_tables`
--

/*!50001 DROP VIEW IF EXISTS `statements_with_temp_tables`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `statements_with_temp_tables` AS select `sys`.`format_statement`(`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT`) AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT`) AS `total_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` AS `memory_tmp_tables`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` AS `disk_tmp_tables`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `avg_tmp_tables_per_query`,round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES`,0)),0) * 100),0) AS `tmp_tables_to_disk_pct`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where (`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` > 0) order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` desc,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_summary`
--

/*!50001 DROP VIEW IF EXISTS `user_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `user_summary` AS select if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) AS `user`,sum(`stmt`.`total`) AS `statements`,`sys`.`format_time`(sum(`stmt`.`total_latency`)) AS `statement_latency`,`sys`.`format_time`(ifnull((sum(`stmt`.`total_latency`) / nullif(sum(`stmt`.`total`),0)),0)) AS `statement_avg_latency`,sum(`stmt`.`full_scans`) AS `table_scans`,sum(`io`.`ios`) AS `file_ios`,`sys`.`format_time`(sum(`io`.`io_latency`)) AS `file_io_latency`,sum(`performance_schema`.`accounts`.`CURRENT_CONNECTIONS`) AS `current_connections`,sum(`performance_schema`.`accounts`.`TOTAL_CONNECTIONS`) AS `total_connections`,count(distinct `performance_schema`.`accounts`.`HOST`) AS `unique_hosts`,`sys`.`format_bytes`(sum(`mem`.`current_allocated`)) AS `current_memory`,`sys`.`format_bytes`(sum(`mem`.`total_allocated`)) AS `total_memory_allocated` from (((`performance_schema`.`accounts` left join `sys`.`x$user_summary_by_statement_latency` `stmt` on((if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) = `stmt`.`user`))) left join `sys`.`x$user_summary_by_file_io` `io` on((if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) = `io`.`user`))) left join `sys`.`x$memory_by_user_by_current_bytes` `mem` on((if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) = `mem`.`user`))) group by if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) order by sum(`stmt`.`total_latency`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_summary_by_file_io`
--

/*!50001 DROP VIEW IF EXISTS `user_summary_by_file_io`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `user_summary_by_file_io` AS select if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) AS `user`,sum(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR`) AS `ios`,`sys`.`format_time`(sum(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`)) AS `io_latency` from `performance_schema`.`events_waits_summary_by_user_by_event_name` where (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') group by if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) order by sum(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_summary_by_file_io_type`
--

/*!50001 DROP VIEW IF EXISTS `user_summary_by_file_io_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `user_summary_by_file_io_type` AS select if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) AS `user`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) AS `latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency` from `performance_schema`.`events_waits_summary_by_user_by_event_name` where ((`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` like 'wait/io/file%') and (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_summary_by_stages`
--

/*!50001 DROP VIEW IF EXISTS `user_summary_by_stages`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `user_summary_by_stages` AS select if(isnull(`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`) AS `user`,`performance_schema`.`events_stages_summary_by_user_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_stages_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_stages_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_stages_summary_by_user_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency` from `performance_schema`.`events_stages_summary_by_user_by_event_name` where (`performance_schema`.`events_stages_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_stages_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_summary_by_statement_latency`
--

/*!50001 DROP VIEW IF EXISTS `user_summary_by_statement_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `user_summary_by_statement_latency` AS select if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`) AS `user`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`COUNT_STAR`) AS `total`,`sys`.`format_time`(sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`)) AS `total_latency`,`sys`.`format_time`(sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`MAX_TIMER_WAIT`)) AS `max_latency`,`sys`.`format_time`(sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_LOCK_TIME`)) AS `lock_latency`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_SENT`) AS `rows_sent`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_EXAMINED`) AS `rows_examined`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_AFFECTED`) AS `rows_affected`,(sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_INDEX_USED`) + sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_GOOD_INDEX_USED`)) AS `full_scans` from `performance_schema`.`events_statements_summary_by_user_by_event_name` group by if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`) order by sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `user_summary_by_statement_type`
--

/*!50001 DROP VIEW IF EXISTS `user_summary_by_statement_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `user_summary_by_statement_type` AS select if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`) AS `user`,substring_index(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`EVENT_NAME`,'/',-(1)) AS `statement`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,`sys`.`format_time`(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_LOCK_TIME`) AS `lock_latency`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_SENT` AS `rows_sent`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_EXAMINED` AS `rows_examined`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_AFFECTED` AS `rows_affected`,(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_INDEX_USED` + `performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_GOOD_INDEX_USED`) AS `full_scans` from `performance_schema`.`events_statements_summary_by_user_by_event_name` where (`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `version`
--

/*!50001 DROP VIEW IF EXISTS `version`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `version` AS select '1.5.0' AS `sys_version`,version() AS `mysql_version` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `wait_classes_global_by_avg_latency`
--

/*!50001 DROP VIEW IF EXISTS `wait_classes_global_by_avg_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `wait_classes_global_by_avg_latency` AS select substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`,'/',3) AS `event_class`,sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`) AS `total`,`sys`.`format_time`(cast(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) as unsigned)) AS `total_latency`,`sys`.`format_time`(min(`performance_schema`.`events_waits_summary_global_by_event_name`.`MIN_TIMER_WAIT`)) AS `min_latency`,`sys`.`format_time`(ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) / nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`),0)),0)) AS `avg_latency`,`sys`.`format_time`(cast(max(`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT`) as unsigned)) AS `max_latency` from `performance_schema`.`events_waits_summary_global_by_event_name` where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0) and (`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle')) group by `event_class` order by ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) / nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`),0)),0) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `wait_classes_global_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `wait_classes_global_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `wait_classes_global_by_latency` AS select substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`,'/',3) AS `event_class`,sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`) AS `total`,`sys`.`format_time`(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`)) AS `total_latency`,`sys`.`format_time`(min(`performance_schema`.`events_waits_summary_global_by_event_name`.`MIN_TIMER_WAIT`)) AS `min_latency`,`sys`.`format_time`(ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) / nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`),0)),0)) AS `avg_latency`,`sys`.`format_time`(max(`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT`)) AS `max_latency` from `performance_schema`.`events_waits_summary_global_by_event_name` where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0) and (`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle')) group by substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`,'/',3) order by sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `waits_by_host_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `waits_by_host_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `waits_by_host_by_latency` AS select if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) AS `host`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` AS `event`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency` from `performance_schema`.`events_waits_summary_by_host_by_event_name` where ((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` <> 'idle') and (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `waits_by_user_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `waits_by_user_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `waits_by_user_by_latency` AS select if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) AS `user`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` AS `event`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency` from `performance_schema`.`events_waits_summary_by_user_by_event_name` where ((`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` <> 'idle') and (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER` is not null) and (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `waits_global_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `waits_global_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `waits_global_by_latency` AS select `performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` AS `events`,`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR` AS `total`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_global_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency`,`sys`.`format_time`(`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency` from `performance_schema`.`events_waits_summary_global_by_event_name` where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle') and (`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0)) order by `performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$host_summary`
--

/*!50001 DROP VIEW IF EXISTS `x$host_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$host_summary` AS select if(isnull(`performance_schema`.`accounts`.`HOST`),'background',`performance_schema`.`accounts`.`HOST`) AS `host`,sum(`stmt`.`total`) AS `statements`,sum(`stmt`.`total_latency`) AS `statement_latency`,(sum(`stmt`.`total_latency`) / sum(`stmt`.`total`)) AS `statement_avg_latency`,sum(`stmt`.`full_scans`) AS `table_scans`,sum(`io`.`ios`) AS `file_ios`,sum(`io`.`io_latency`) AS `file_io_latency`,sum(`performance_schema`.`accounts`.`CURRENT_CONNECTIONS`) AS `current_connections`,sum(`performance_schema`.`accounts`.`TOTAL_CONNECTIONS`) AS `total_connections`,count(distinct `performance_schema`.`accounts`.`USER`) AS `unique_users`,sum(`mem`.`current_allocated`) AS `current_memory`,sum(`mem`.`total_allocated`) AS `total_memory_allocated` from (((`performance_schema`.`accounts` join `sys`.`x$host_summary_by_statement_latency` `stmt` on((`performance_schema`.`accounts`.`HOST` = `stmt`.`host`))) join `sys`.`x$host_summary_by_file_io` `io` on((`performance_schema`.`accounts`.`HOST` = `io`.`host`))) join `sys`.`x$memory_by_host_by_current_bytes` `mem` on((`performance_schema`.`accounts`.`HOST` = `mem`.`host`))) group by if(isnull(`performance_schema`.`accounts`.`HOST`),'background',`performance_schema`.`accounts`.`HOST`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$host_summary_by_file_io`
--

/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_file_io`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$host_summary_by_file_io` AS select if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) AS `host`,sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR`) AS `ios`,sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `io_latency` from `performance_schema`.`events_waits_summary_by_host_by_event_name` where (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') group by if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) order by sum(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$host_summary_by_file_io_type`
--

/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_file_io_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$host_summary_by_file_io_type` AS select if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) AS `host`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency` from `performance_schema`.`events_waits_summary_by_host_by_event_name` where ((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` like 'wait/io/file%') and (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$host_summary_by_stages`
--

/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_stages`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$host_summary_by_stages` AS select if(isnull(`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`) AS `host`,`performance_schema`.`events_stages_summary_by_host_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_stages_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_stages_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_stages_summary_by_host_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency` from `performance_schema`.`events_stages_summary_by_host_by_event_name` where (`performance_schema`.`events_stages_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_stages_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_stages_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$host_summary_by_statement_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_statement_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$host_summary_by_statement_latency` AS select if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`) AS `host`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`COUNT_STAR`) AS `total`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_LOCK_TIME`) AS `lock_latency`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_SENT`) AS `rows_sent`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_EXAMINED`) AS `rows_examined`,sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_AFFECTED`) AS `rows_affected`,(sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_INDEX_USED`) + sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_GOOD_INDEX_USED`)) AS `full_scans` from `performance_schema`.`events_statements_summary_by_host_by_event_name` group by if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`) order by sum(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$host_summary_by_statement_type`
--

/*!50001 DROP VIEW IF EXISTS `x$host_summary_by_statement_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$host_summary_by_statement_type` AS select if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`) AS `host`,substring_index(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`EVENT_NAME`,'/',-(1)) AS `statement`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_LOCK_TIME` AS `lock_latency`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_SENT` AS `rows_sent`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_EXAMINED` AS `rows_examined`,`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_ROWS_AFFECTED` AS `rows_affected`,(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_INDEX_USED` + `performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_NO_GOOD_INDEX_USED`) AS `full_scans` from `performance_schema`.`events_statements_summary_by_host_by_event_name` where (`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_statements_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_statements_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$innodb_buffer_stats_by_schema`
--

/*!50001 DROP VIEW IF EXISTS `x$innodb_buffer_stats_by_schema`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$innodb_buffer_stats_by_schema` AS select if((locate('.',`ibp`.`TABLE_NAME`) = 0),'InnoDB System',replace(substring_index(`ibp`.`TABLE_NAME`,'.',1),'`','')) AS `object_schema`,sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`)) AS `allocated`,sum(`ibp`.`DATA_SIZE`) AS `data`,count(`ibp`.`PAGE_NUMBER`) AS `pages`,count(if((`ibp`.`IS_HASHED` = 'YES'),1,0)) AS `pages_hashed`,count(if((`ibp`.`IS_OLD` = 'YES'),1,0)) AS `pages_old`,round(ifnull((sum(`ibp`.`NUMBER_RECORDS`) / nullif(count(distinct `ibp`.`INDEX_NAME`),0)),0),0) AS `rows_cached` from `information_schema`.`innodb_buffer_page` `ibp` where (`ibp`.`TABLE_NAME` is not null) group by `object_schema` order by sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`)) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$innodb_buffer_stats_by_table`
--

/*!50001 DROP VIEW IF EXISTS `x$innodb_buffer_stats_by_table`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$innodb_buffer_stats_by_table` AS select if((locate('.',`ibp`.`TABLE_NAME`) = 0),'InnoDB System',replace(substring_index(`ibp`.`TABLE_NAME`,'.',1),'`','')) AS `object_schema`,replace(substring_index(`ibp`.`TABLE_NAME`,'.',-(1)),'`','') AS `object_name`,sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`)) AS `allocated`,sum(`ibp`.`DATA_SIZE`) AS `data`,count(`ibp`.`PAGE_NUMBER`) AS `pages`,count(if((`ibp`.`IS_HASHED` = 'YES'),1,0)) AS `pages_hashed`,count(if((`ibp`.`IS_OLD` = 'YES'),1,0)) AS `pages_old`,round(ifnull((sum(`ibp`.`NUMBER_RECORDS`) / nullif(count(distinct `ibp`.`INDEX_NAME`),0)),0),0) AS `rows_cached` from `information_schema`.`innodb_buffer_page` `ibp` where (`ibp`.`TABLE_NAME` is not null) group by `object_schema`,`object_name` order by sum(if((`ibp`.`COMPRESSED_SIZE` = 0),16384,`ibp`.`COMPRESSED_SIZE`)) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$innodb_lock_waits`
--

/*!50001 DROP VIEW IF EXISTS `x$innodb_lock_waits`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$innodb_lock_waits` AS select `r`.`trx_wait_started` AS `wait_started`,timediff(now(),`r`.`trx_wait_started`) AS `wait_age`,timestampdiff(SECOND,`r`.`trx_wait_started`,now()) AS `wait_age_secs`,`rl`.`lock_table` AS `locked_table`,`rl`.`lock_index` AS `locked_index`,`rl`.`lock_type` AS `locked_type`,`r`.`trx_id` AS `waiting_trx_id`,`r`.`trx_started` AS `waiting_trx_started`,timediff(now(),`r`.`trx_started`) AS `waiting_trx_age`,`r`.`trx_rows_locked` AS `waiting_trx_rows_locked`,`r`.`trx_rows_modified` AS `waiting_trx_rows_modified`,`r`.`trx_mysql_thread_id` AS `waiting_pid`,`r`.`trx_query` AS `waiting_query`,`rl`.`lock_id` AS `waiting_lock_id`,`rl`.`lock_mode` AS `waiting_lock_mode`,`b`.`trx_id` AS `blocking_trx_id`,`b`.`trx_mysql_thread_id` AS `blocking_pid`,`b`.`trx_query` AS `blocking_query`,`bl`.`lock_id` AS `blocking_lock_id`,`bl`.`lock_mode` AS `blocking_lock_mode`,`b`.`trx_started` AS `blocking_trx_started`,timediff(now(),`b`.`trx_started`) AS `blocking_trx_age`,`b`.`trx_rows_locked` AS `blocking_trx_rows_locked`,`b`.`trx_rows_modified` AS `blocking_trx_rows_modified`,concat('KILL QUERY ',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_query`,concat('KILL ',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_connection` from ((((`information_schema`.`innodb_lock_waits` `w` join `information_schema`.`innodb_trx` `b` on((`b`.`trx_id` = `w`.`blocking_trx_id`))) join `information_schema`.`innodb_trx` `r` on((`r`.`trx_id` = `w`.`requesting_trx_id`))) join `information_schema`.`innodb_locks` `bl` on((`bl`.`lock_id` = `w`.`blocking_lock_id`))) join `information_schema`.`innodb_locks` `rl` on((`rl`.`lock_id` = `w`.`requested_lock_id`))) order by `r`.`trx_wait_started` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$io_by_thread_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$io_by_thread_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$io_by_thread_by_latency` AS select if(isnull(`performance_schema`.`threads`.`PROCESSLIST_ID`),substring_index(`performance_schema`.`threads`.`NAME`,'/',-(1)),concat(`performance_schema`.`threads`.`PROCESSLIST_USER`,'@',`performance_schema`.`threads`.`PROCESSLIST_HOST`)) AS `user`,sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`COUNT_STAR`) AS `total`,sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,min(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`MIN_TIMER_WAIT`) AS `min_latency`,avg(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`AVG_TIMER_WAIT`) AS `avg_latency`,max(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID` AS `thread_id`,`performance_schema`.`threads`.`PROCESSLIST_ID` AS `processlist_id` from (`performance_schema`.`events_waits_summary_by_thread_by_event_name` left join `performance_schema`.`threads` on((`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID` = `performance_schema`.`threads`.`THREAD_ID`))) where ((`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and (`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT` > 0)) group by `performance_schema`.`events_waits_summary_by_thread_by_event_name`.`THREAD_ID`,`performance_schema`.`threads`.`PROCESSLIST_ID`,`user` order by sum(`performance_schema`.`events_waits_summary_by_thread_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$io_global_by_file_by_bytes`
--

/*!50001 DROP VIEW IF EXISTS `x$io_global_by_file_by_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$io_global_by_file_by_bytes` AS select `performance_schema`.`file_summary_by_instance`.`FILE_NAME` AS `file`,`performance_schema`.`file_summary_by_instance`.`COUNT_READ` AS `count_read`,`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` AS `total_read`,ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` / nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_READ`,0)),0) AS `avg_read`,`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE` AS `count_write`,`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE` AS `total_written`,ifnull((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE` / nullif(`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`,0)),0.00) AS `avg_write`,(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` + `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`) AS `total`,ifnull(round((100 - ((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` / nullif((`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` + `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`),0)) * 100)),2),0.00) AS `write_pct` from `performance_schema`.`file_summary_by_instance` order by (`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ` + `performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$io_global_by_file_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$io_global_by_file_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$io_global_by_file_by_latency` AS select `performance_schema`.`file_summary_by_instance`.`FILE_NAME` AS `file`,`performance_schema`.`file_summary_by_instance`.`COUNT_STAR` AS `total`,`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`file_summary_by_instance`.`COUNT_READ` AS `count_read`,`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_READ` AS `read_latency`,`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE` AS `count_write`,`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WRITE` AS `write_latency`,`performance_schema`.`file_summary_by_instance`.`COUNT_MISC` AS `count_misc`,`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_MISC` AS `misc_latency` from `performance_schema`.`file_summary_by_instance` order by `performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$io_global_by_wait_by_bytes`
--

/*!50001 DROP VIEW IF EXISTS `x$io_global_by_wait_by_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$io_global_by_wait_by_bytes` AS select substring_index(`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME`,'/',-(2)) AS `event_name`,`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`file_summary_by_event_name`.`MIN_TIMER_WAIT` AS `min_latency`,`performance_schema`.`file_summary_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency`,`performance_schema`.`file_summary_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency`,`performance_schema`.`file_summary_by_event_name`.`COUNT_READ` AS `count_read`,`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ` AS `total_read`,ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_READ`,0)),0) AS `avg_read`,`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE` AS `count_write`,`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` AS `total_written`,ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE`,0)),0) AS `avg_written`,(`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` + `performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`) AS `total_requested` from `performance_schema`.`file_summary_by_event_name` where ((`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and (`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` > 0)) order by (`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` + `performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$io_global_by_wait_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$io_global_by_wait_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$io_global_by_wait_by_latency` AS select substring_index(`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME`,'/',-(2)) AS `event_name`,`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`file_summary_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency`,`performance_schema`.`file_summary_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency`,`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_READ` AS `read_latency`,`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WRITE` AS `write_latency`,`performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_MISC` AS `misc_latency`,`performance_schema`.`file_summary_by_event_name`.`COUNT_READ` AS `count_read`,`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ` AS `total_read`,ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_READ` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_READ`,0)),0) AS `avg_read`,`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE` AS `count_write`,`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` AS `total_written`,ifnull((`performance_schema`.`file_summary_by_event_name`.`SUM_NUMBER_OF_BYTES_WRITE` / nullif(`performance_schema`.`file_summary_by_event_name`.`COUNT_WRITE`,0)),0) AS `avg_written` from `performance_schema`.`file_summary_by_event_name` where ((`performance_schema`.`file_summary_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') and (`performance_schema`.`file_summary_by_event_name`.`COUNT_STAR` > 0)) order by `performance_schema`.`file_summary_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$latest_file_io`
--

/*!50001 DROP VIEW IF EXISTS `x$latest_file_io`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$latest_file_io` AS select if(isnull(`information_schema`.`processlist`.`ID`),concat(substring_index(`performance_schema`.`threads`.`NAME`,'/',-(1)),':',`performance_schema`.`events_waits_history_long`.`THREAD_ID`),concat(`information_schema`.`processlist`.`USER`,'@',`information_schema`.`processlist`.`HOST`,':',`information_schema`.`processlist`.`ID`)) AS `thread`,`performance_schema`.`events_waits_history_long`.`OBJECT_NAME` AS `file`,`performance_schema`.`events_waits_history_long`.`TIMER_WAIT` AS `latency`,`performance_schema`.`events_waits_history_long`.`OPERATION` AS `operation`,`performance_schema`.`events_waits_history_long`.`NUMBER_OF_BYTES` AS `requested` from ((`performance_schema`.`events_waits_history_long` join `performance_schema`.`threads` on((`performance_schema`.`events_waits_history_long`.`THREAD_ID` = `performance_schema`.`threads`.`THREAD_ID`))) left join `information_schema`.`processlist` on((`performance_schema`.`threads`.`PROCESSLIST_ID` = `information_schema`.`processlist`.`ID`))) where ((`performance_schema`.`events_waits_history_long`.`OBJECT_NAME` is not null) and (`performance_schema`.`events_waits_history_long`.`EVENT_NAME` like 'wait/io/file/%')) order by `performance_schema`.`events_waits_history_long`.`TIMER_START` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$memory_by_host_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `x$memory_by_host_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$memory_by_host_by_current_bytes` AS select if(isnull(`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`) AS `host`,sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_COUNT_USED`) AS `current_count_used`,sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `current_allocated`,ifnull((sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) / nullif(sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_COUNT_USED`),0)),0) AS `current_avg_alloc`,max(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `current_max_alloc`,sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`SUM_NUMBER_OF_BYTES_ALLOC`) AS `total_allocated` from `performance_schema`.`memory_summary_by_host_by_event_name` group by if(isnull(`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`memory_summary_by_host_by_event_name`.`HOST`) order by sum(`performance_schema`.`memory_summary_by_host_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$memory_by_thread_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `x$memory_by_thread_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$memory_by_thread_by_current_bytes` AS select `t`.`THREAD_ID` AS `thread_id`,if((`t`.`NAME` = 'thread/sql/one_connection'),concat(`t`.`PROCESSLIST_USER`,'@',`t`.`PROCESSLIST_HOST`),replace(`t`.`NAME`,'thread/','')) AS `user`,sum(`mt`.`CURRENT_COUNT_USED`) AS `current_count_used`,sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `current_allocated`,ifnull((sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) / nullif(sum(`mt`.`CURRENT_COUNT_USED`),0)),0) AS `current_avg_alloc`,max(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `current_max_alloc`,sum(`mt`.`SUM_NUMBER_OF_BYTES_ALLOC`) AS `total_allocated` from (`performance_schema`.`memory_summary_by_thread_by_event_name` `mt` join `performance_schema`.`threads` `t` on((`mt`.`THREAD_ID` = `t`.`THREAD_ID`))) group by `t`.`THREAD_ID`,if((`t`.`NAME` = 'thread/sql/one_connection'),concat(`t`.`PROCESSLIST_USER`,'@',`t`.`PROCESSLIST_HOST`),replace(`t`.`NAME`,'thread/','')) order by sum(`mt`.`CURRENT_NUMBER_OF_BYTES_USED`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$memory_by_user_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `x$memory_by_user_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$memory_by_user_by_current_bytes` AS select if(isnull(`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`) AS `user`,sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_COUNT_USED`) AS `current_count_used`,sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `current_allocated`,ifnull((sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) / nullif(sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_COUNT_USED`),0)),0) AS `current_avg_alloc`,max(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `current_max_alloc`,sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`SUM_NUMBER_OF_BYTES_ALLOC`) AS `total_allocated` from `performance_schema`.`memory_summary_by_user_by_event_name` group by if(isnull(`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`memory_summary_by_user_by_event_name`.`USER`) order by sum(`performance_schema`.`memory_summary_by_user_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$memory_global_by_current_bytes`
--

/*!50001 DROP VIEW IF EXISTS `x$memory_global_by_current_bytes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$memory_global_by_current_bytes` AS select `performance_schema`.`memory_summary_global_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_COUNT_USED` AS `current_count`,`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` AS `current_alloc`,ifnull((`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` / nullif(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_COUNT_USED`,0)),0) AS `current_avg_alloc`,`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_COUNT_USED` AS `high_count`,`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_NUMBER_OF_BYTES_USED` AS `high_alloc`,ifnull((`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_NUMBER_OF_BYTES_USED` / nullif(`performance_schema`.`memory_summary_global_by_event_name`.`HIGH_COUNT_USED`,0)),0) AS `high_avg_alloc` from `performance_schema`.`memory_summary_global_by_event_name` where (`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` > 0) order by `performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$memory_global_total`
--

/*!50001 DROP VIEW IF EXISTS `x$memory_global_total`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$memory_global_total` AS select sum(`performance_schema`.`memory_summary_global_by_event_name`.`CURRENT_NUMBER_OF_BYTES_USED`) AS `total_allocated` from `performance_schema`.`memory_summary_global_by_event_name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$processlist`
--

/*!50001 DROP VIEW IF EXISTS `x$processlist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$processlist` AS select `pps`.`THREAD_ID` AS `thd_id`,`pps`.`PROCESSLIST_ID` AS `conn_id`,if((`pps`.`NAME` = 'thread/sql/one_connection'),concat(`pps`.`PROCESSLIST_USER`,'@',`pps`.`PROCESSLIST_HOST`),replace(`pps`.`NAME`,'thread/','')) AS `user`,`pps`.`PROCESSLIST_DB` AS `db`,`pps`.`PROCESSLIST_COMMAND` AS `command`,`pps`.`PROCESSLIST_STATE` AS `state`,`pps`.`PROCESSLIST_TIME` AS `time`,`pps`.`PROCESSLIST_INFO` AS `current_statement`,if(isnull(`esc`.`END_EVENT_ID`),`esc`.`TIMER_WAIT`,NULL) AS `statement_latency`,if(isnull(`esc`.`END_EVENT_ID`),round((100 * (`estc`.`WORK_COMPLETED` / `estc`.`WORK_ESTIMATED`)),2),NULL) AS `progress`,`esc`.`LOCK_TIME` AS `lock_latency`,`esc`.`ROWS_EXAMINED` AS `rows_examined`,`esc`.`ROWS_SENT` AS `rows_sent`,`esc`.`ROWS_AFFECTED` AS `rows_affected`,`esc`.`CREATED_TMP_TABLES` AS `tmp_tables`,`esc`.`CREATED_TMP_DISK_TABLES` AS `tmp_disk_tables`,if(((`esc`.`NO_GOOD_INDEX_USED` > 0) or (`esc`.`NO_INDEX_USED` > 0)),'YES','NO') AS `full_scan`,if((`esc`.`END_EVENT_ID` is not null),`esc`.`SQL_TEXT`,NULL) AS `last_statement`,if((`esc`.`END_EVENT_ID` is not null),`esc`.`TIMER_WAIT`,NULL) AS `last_statement_latency`,`mem`.`current_allocated` AS `current_memory`,`ewc`.`EVENT_NAME` AS `last_wait`,if((isnull(`ewc`.`END_EVENT_ID`) and (`ewc`.`EVENT_NAME` is not null)),'Still Waiting',`ewc`.`TIMER_WAIT`) AS `last_wait_latency`,`ewc`.`SOURCE` AS `source`,`etc`.`TIMER_WAIT` AS `trx_latency`,`etc`.`STATE` AS `trx_state`,`etc`.`AUTOCOMMIT` AS `trx_autocommit`,`conattr_pid`.`ATTR_VALUE` AS `pid`,`conattr_progname`.`ATTR_VALUE` AS `program_name` from (((((((`performance_schema`.`threads` `pps` left join `performance_schema`.`events_waits_current` `ewc` on((`pps`.`THREAD_ID` = `ewc`.`THREAD_ID`))) left join `performance_schema`.`events_stages_current` `estc` on((`pps`.`THREAD_ID` = `estc`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `esc` on((`pps`.`THREAD_ID` = `esc`.`THREAD_ID`))) left join `performance_schema`.`events_transactions_current` `etc` on((`pps`.`THREAD_ID` = `etc`.`THREAD_ID`))) left join `sys`.`x$memory_by_thread_by_current_bytes` `mem` on((`pps`.`THREAD_ID` = `mem`.`thread_id`))) left join `performance_schema`.`session_connect_attrs` `conattr_pid` on(((`conattr_pid`.`PROCESSLIST_ID` = `pps`.`PROCESSLIST_ID`) and (`conattr_pid`.`ATTR_NAME` = '_pid')))) left join `performance_schema`.`session_connect_attrs` `conattr_progname` on(((`conattr_progname`.`PROCESSLIST_ID` = `pps`.`PROCESSLIST_ID`) and (`conattr_progname`.`ATTR_NAME` = 'program_name')))) order by `pps`.`PROCESSLIST_TIME` desc,`last_wait_latency` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$ps_digest_95th_percentile_by_avg_us`
--

/*!50001 DROP VIEW IF EXISTS `x$ps_digest_95th_percentile_by_avg_us`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$ps_digest_95th_percentile_by_avg_us` AS select `s2`.`avg_us` AS `avg_us`,ifnull((sum(`s1`.`cnt`) / nullif((select count(0) from `performance_schema`.`events_statements_summary_by_digest`),0)),0) AS `percentile` from (`sys`.`x$ps_digest_avg_latency_distribution` `s1` join `sys`.`x$ps_digest_avg_latency_distribution` `s2` on((`s1`.`avg_us` <= `s2`.`avg_us`))) group by `s2`.`avg_us` having (ifnull((sum(`s1`.`cnt`) / nullif((select count(0) from `performance_schema`.`events_statements_summary_by_digest`),0)),0) > 0.95) order by `percentile` limit 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$ps_digest_avg_latency_distribution`
--

/*!50001 DROP VIEW IF EXISTS `x$ps_digest_avg_latency_distribution`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$ps_digest_avg_latency_distribution` AS select count(0) AS `cnt`,round((`performance_schema`.`events_statements_summary_by_digest`.`AVG_TIMER_WAIT` / 1000000),0) AS `avg_us` from `performance_schema`.`events_statements_summary_by_digest` group by `avg_us` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$ps_schema_table_statistics_io`
--

/*!50001 DROP VIEW IF EXISTS `x$ps_schema_table_statistics_io`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$ps_schema_table_statistics_io` AS select `extract_schema_from_file_name`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`) AS `table_schema`,`extract_table_from_file_name`(`performance_schema`.`file_summary_by_instance`.`FILE_NAME`) AS `table_name`,sum(`performance_schema`.`file_summary_by_instance`.`COUNT_READ`) AS `count_read`,sum(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_READ`) AS `sum_number_of_bytes_read`,sum(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_READ`) AS `sum_timer_read`,sum(`performance_schema`.`file_summary_by_instance`.`COUNT_WRITE`) AS `count_write`,sum(`performance_schema`.`file_summary_by_instance`.`SUM_NUMBER_OF_BYTES_WRITE`) AS `sum_number_of_bytes_write`,sum(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_WRITE`) AS `sum_timer_write`,sum(`performance_schema`.`file_summary_by_instance`.`COUNT_MISC`) AS `count_misc`,sum(`performance_schema`.`file_summary_by_instance`.`SUM_TIMER_MISC`) AS `sum_timer_misc` from `performance_schema`.`file_summary_by_instance` group by `table_schema`,`table_name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$schema_flattened_keys`
--

/*!50001 DROP VIEW IF EXISTS `x$schema_flattened_keys`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$schema_flattened_keys` AS select `information_schema`.`statistics`.`TABLE_SCHEMA` AS `table_schema`,`information_schema`.`statistics`.`TABLE_NAME` AS `table_name`,`information_schema`.`statistics`.`INDEX_NAME` AS `index_name`,max(`information_schema`.`statistics`.`NON_UNIQUE`) AS `non_unique`,max(if(isnull(`information_schema`.`statistics`.`SUB_PART`),0,1)) AS `subpart_exists`,group_concat(`information_schema`.`statistics`.`COLUMN_NAME` order by `information_schema`.`statistics`.`SEQ_IN_INDEX` ASC separator ',') AS `index_columns` from `information_schema`.`statistics` where ((`information_schema`.`statistics`.`INDEX_TYPE` = 'BTREE') and (`information_schema`.`statistics`.`TABLE_SCHEMA` not in ('mysql','sys','INFORMATION_SCHEMA','PERFORMANCE_SCHEMA'))) group by `information_schema`.`statistics`.`TABLE_SCHEMA`,`information_schema`.`statistics`.`TABLE_NAME`,`information_schema`.`statistics`.`INDEX_NAME` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$schema_index_statistics`
--

/*!50001 DROP VIEW IF EXISTS `x$schema_index_statistics`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$schema_index_statistics` AS select `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA` AS `table_schema`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_NAME` AS `table_name`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` AS `index_name`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_FETCH` AS `rows_selected`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_FETCH` AS `select_latency`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_INSERT` AS `rows_inserted`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_INSERT` AS `insert_latency`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_UPDATE` AS `rows_updated`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_UPDATE` AS `update_latency`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_DELETE` AS `rows_deleted`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_INSERT` AS `delete_latency` from `performance_schema`.`table_io_waits_summary_by_index_usage` where (`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME` is not null) order by `performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$schema_table_lock_waits`
--

/*!50001 DROP VIEW IF EXISTS `x$schema_table_lock_waits`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$schema_table_lock_waits` AS select `g`.`OBJECT_SCHEMA` AS `object_schema`,`g`.`OBJECT_NAME` AS `object_name`,`pt`.`THREAD_ID` AS `waiting_thread_id`,`pt`.`PROCESSLIST_ID` AS `waiting_pid`,`sys`.`ps_thread_account`(`p`.`OWNER_THREAD_ID`) AS `waiting_account`,`p`.`LOCK_TYPE` AS `waiting_lock_type`,`p`.`LOCK_DURATION` AS `waiting_lock_duration`,`pt`.`PROCESSLIST_INFO` AS `waiting_query`,`pt`.`PROCESSLIST_TIME` AS `waiting_query_secs`,`ps`.`ROWS_AFFECTED` AS `waiting_query_rows_affected`,`ps`.`ROWS_EXAMINED` AS `waiting_query_rows_examined`,`gt`.`THREAD_ID` AS `blocking_thread_id`,`gt`.`PROCESSLIST_ID` AS `blocking_pid`,`sys`.`ps_thread_account`(`g`.`OWNER_THREAD_ID`) AS `blocking_account`,`g`.`LOCK_TYPE` AS `blocking_lock_type`,`g`.`LOCK_DURATION` AS `blocking_lock_duration`,concat('KILL QUERY ',`gt`.`PROCESSLIST_ID`) AS `sql_kill_blocking_query`,concat('KILL ',`gt`.`PROCESSLIST_ID`) AS `sql_kill_blocking_connection` from (((((`performance_schema`.`metadata_locks` `g` join `performance_schema`.`metadata_locks` `p` on(((`g`.`OBJECT_TYPE` = `p`.`OBJECT_TYPE`) and (`g`.`OBJECT_SCHEMA` = `p`.`OBJECT_SCHEMA`) and (`g`.`OBJECT_NAME` = `p`.`OBJECT_NAME`) and (`g`.`LOCK_STATUS` = 'GRANTED') and (`p`.`LOCK_STATUS` = 'PENDING')))) join `performance_schema`.`threads` `gt` on((`g`.`OWNER_THREAD_ID` = `gt`.`THREAD_ID`))) join `performance_schema`.`threads` `pt` on((`p`.`OWNER_THREAD_ID` = `pt`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `gs` on((`g`.`OWNER_THREAD_ID` = `gs`.`THREAD_ID`))) left join `performance_schema`.`events_statements_current` `ps` on((`p`.`OWNER_THREAD_ID` = `ps`.`THREAD_ID`))) where (`g`.`OBJECT_TYPE` = 'TABLE') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$schema_table_statistics`
--

/*!50001 DROP VIEW IF EXISTS `x$schema_table_statistics`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$schema_table_statistics` AS select `pst`.`OBJECT_SCHEMA` AS `table_schema`,`pst`.`OBJECT_NAME` AS `table_name`,`pst`.`SUM_TIMER_WAIT` AS `total_latency`,`pst`.`COUNT_FETCH` AS `rows_fetched`,`pst`.`SUM_TIMER_FETCH` AS `fetch_latency`,`pst`.`COUNT_INSERT` AS `rows_inserted`,`pst`.`SUM_TIMER_INSERT` AS `insert_latency`,`pst`.`COUNT_UPDATE` AS `rows_updated`,`pst`.`SUM_TIMER_UPDATE` AS `update_latency`,`pst`.`COUNT_DELETE` AS `rows_deleted`,`pst`.`SUM_TIMER_DELETE` AS `delete_latency`,`fsbi`.`count_read` AS `io_read_requests`,`fsbi`.`sum_number_of_bytes_read` AS `io_read`,`fsbi`.`sum_timer_read` AS `io_read_latency`,`fsbi`.`count_write` AS `io_write_requests`,`fsbi`.`sum_number_of_bytes_write` AS `io_write`,`fsbi`.`sum_timer_write` AS `io_write_latency`,`fsbi`.`count_misc` AS `io_misc_requests`,`fsbi`.`sum_timer_misc` AS `io_misc_latency` from (`performance_schema`.`table_io_waits_summary_by_table` `pst` left join `sys`.`x$ps_schema_table_statistics_io` `fsbi` on(((`pst`.`OBJECT_SCHEMA` = `fsbi`.`table_schema`) and (`pst`.`OBJECT_NAME` = `fsbi`.`table_name`)))) order by `pst`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$schema_table_statistics_with_buffer`
--

/*!50001 DROP VIEW IF EXISTS `x$schema_table_statistics_with_buffer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$schema_table_statistics_with_buffer` AS select `pst`.`OBJECT_SCHEMA` AS `table_schema`,`pst`.`OBJECT_NAME` AS `table_name`,`pst`.`COUNT_FETCH` AS `rows_fetched`,`pst`.`SUM_TIMER_FETCH` AS `fetch_latency`,`pst`.`COUNT_INSERT` AS `rows_inserted`,`pst`.`SUM_TIMER_INSERT` AS `insert_latency`,`pst`.`COUNT_UPDATE` AS `rows_updated`,`pst`.`SUM_TIMER_UPDATE` AS `update_latency`,`pst`.`COUNT_DELETE` AS `rows_deleted`,`pst`.`SUM_TIMER_DELETE` AS `delete_latency`,`fsbi`.`count_read` AS `io_read_requests`,`fsbi`.`sum_number_of_bytes_read` AS `io_read`,`fsbi`.`sum_timer_read` AS `io_read_latency`,`fsbi`.`count_write` AS `io_write_requests`,`fsbi`.`sum_number_of_bytes_write` AS `io_write`,`fsbi`.`sum_timer_write` AS `io_write_latency`,`fsbi`.`count_misc` AS `io_misc_requests`,`fsbi`.`sum_timer_misc` AS `io_misc_latency`,`ibp`.`allocated` AS `innodb_buffer_allocated`,`ibp`.`data` AS `innodb_buffer_data`,(`ibp`.`allocated` - `ibp`.`data`) AS `innodb_buffer_free`,`ibp`.`pages` AS `innodb_buffer_pages`,`ibp`.`pages_hashed` AS `innodb_buffer_pages_hashed`,`ibp`.`pages_old` AS `innodb_buffer_pages_old`,`ibp`.`rows_cached` AS `innodb_buffer_rows_cached` from ((`performance_schema`.`table_io_waits_summary_by_table` `pst` left join `sys`.`x$ps_schema_table_statistics_io` `fsbi` on(((`pst`.`OBJECT_SCHEMA` = `fsbi`.`table_schema`) and (`pst`.`OBJECT_NAME` = `fsbi`.`table_name`)))) left join `sys`.`x$innodb_buffer_stats_by_table` `ibp` on(((`pst`.`OBJECT_SCHEMA` = `ibp`.`object_schema`) and (`pst`.`OBJECT_NAME` = `ibp`.`object_name`)))) order by `pst`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$schema_tables_with_full_table_scans`
--

/*!50001 DROP VIEW IF EXISTS `x$schema_tables_with_full_table_scans`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$schema_tables_with_full_table_scans` AS select `performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_SCHEMA` AS `object_schema`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`OBJECT_NAME` AS `object_name`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` AS `rows_full_scanned`,`performance_schema`.`table_io_waits_summary_by_index_usage`.`SUM_TIMER_WAIT` AS `latency` from `performance_schema`.`table_io_waits_summary_by_index_usage` where (isnull(`performance_schema`.`table_io_waits_summary_by_index_usage`.`INDEX_NAME`) and (`performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` > 0)) order by `performance_schema`.`table_io_waits_summary_by_index_usage`.`COUNT_READ` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$session`
--

/*!50001 DROP VIEW IF EXISTS `x$session`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$session` AS select `x$processlist`.`thd_id` AS `thd_id`,`x$processlist`.`conn_id` AS `conn_id`,`x$processlist`.`user` AS `user`,`x$processlist`.`db` AS `db`,`x$processlist`.`command` AS `command`,`x$processlist`.`state` AS `state`,`x$processlist`.`time` AS `time`,`x$processlist`.`current_statement` AS `current_statement`,`x$processlist`.`statement_latency` AS `statement_latency`,`x$processlist`.`progress` AS `progress`,`x$processlist`.`lock_latency` AS `lock_latency`,`x$processlist`.`rows_examined` AS `rows_examined`,`x$processlist`.`rows_sent` AS `rows_sent`,`x$processlist`.`rows_affected` AS `rows_affected`,`x$processlist`.`tmp_tables` AS `tmp_tables`,`x$processlist`.`tmp_disk_tables` AS `tmp_disk_tables`,`x$processlist`.`full_scan` AS `full_scan`,`x$processlist`.`last_statement` AS `last_statement`,`x$processlist`.`last_statement_latency` AS `last_statement_latency`,`x$processlist`.`current_memory` AS `current_memory`,`x$processlist`.`last_wait` AS `last_wait`,`x$processlist`.`last_wait_latency` AS `last_wait_latency`,`x$processlist`.`source` AS `source`,`x$processlist`.`trx_latency` AS `trx_latency`,`x$processlist`.`trx_state` AS `trx_state`,`x$processlist`.`trx_autocommit` AS `trx_autocommit`,`x$processlist`.`pid` AS `pid`,`x$processlist`.`program_name` AS `program_name` from `sys`.`x$processlist` where ((`x$processlist`.`conn_id` is not null) and (`x$processlist`.`command` <> 'Daemon')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$statement_analysis`
--

/*!50001 DROP VIEW IF EXISTS `x$statement_analysis`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$statement_analysis` AS select `performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,if(((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` > 0) or (`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` > 0)),'*','') AS `full_scan`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` AS `err_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` AS `warn_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_statements_summary_by_digest`.`MAX_TIMER_WAIT` AS `max_latency`,`performance_schema`.`events_statements_summary_by_digest`.`AVG_TIMER_WAIT` AS `avg_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_LOCK_TIME` AS `lock_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` AS `rows_sent`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `rows_sent_avg`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` AS `rows_examined`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `rows_examined_avg`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_AFFECTED` AS `rows_affected`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_AFFECTED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `rows_affected_avg`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` AS `tmp_tables`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` AS `tmp_disk_tables`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` AS `rows_sorted`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES` AS `sort_merge_passes`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen` from `performance_schema`.`events_statements_summary_by_digest` order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$statements_with_errors_or_warnings`
--

/*!50001 DROP VIEW IF EXISTS `x$statements_with_errors_or_warnings`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$statements_with_errors_or_warnings` AS select `performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` AS `errors`,(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100) AS `error_pct`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` AS `warnings`,(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100) AS `warning_pct`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where ((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` > 0) or (`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` > 0)) order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_ERRORS` desc,`performance_schema`.`events_statements_summary_by_digest`.`SUM_WARNINGS` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$statements_with_full_table_scans`
--

/*!50001 DROP VIEW IF EXISTS `x$statements_with_full_table_scans`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$statements_with_full_table_scans` AS select `performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` AS `no_index_used_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` AS `no_good_index_used_count`,round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100),0) AS `no_index_used_pct`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` AS `rows_sent`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` AS `rows_examined`,round((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_SENT` / `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`),0) AS `rows_sent_avg`,round((`performance_schema`.`events_statements_summary_by_digest`.`SUM_ROWS_EXAMINED` / `performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`),0) AS `rows_examined_avg`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where (((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` > 0) or (`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_GOOD_INDEX_USED` > 0)) and (not((`performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` like 'SHOW%')))) order by round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_NO_INDEX_USED` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0) * 100),0) desc,`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$statements_with_runtimes_in_95th_percentile`
--

/*!50001 DROP VIEW IF EXISTS `x$statements_with_runtimes_in_95th_percentile`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$statements_with_runtimes_in_95th_percentile` AS select `stmts`.`DIGEST_TEXT` AS `query`,`stmts`.`SCHEMA_NAME` AS `db`,if(((`stmts`.`SUM_NO_GOOD_INDEX_USED` > 0) or (`stmts`.`SUM_NO_INDEX_USED` > 0)),'*','') AS `full_scan`,`stmts`.`COUNT_STAR` AS `exec_count`,`stmts`.`SUM_ERRORS` AS `err_count`,`stmts`.`SUM_WARNINGS` AS `warn_count`,`stmts`.`SUM_TIMER_WAIT` AS `total_latency`,`stmts`.`MAX_TIMER_WAIT` AS `max_latency`,`stmts`.`AVG_TIMER_WAIT` AS `avg_latency`,`stmts`.`SUM_ROWS_SENT` AS `rows_sent`,round(ifnull((`stmts`.`SUM_ROWS_SENT` / nullif(`stmts`.`COUNT_STAR`,0)),0),0) AS `rows_sent_avg`,`stmts`.`SUM_ROWS_EXAMINED` AS `rows_examined`,round(ifnull((`stmts`.`SUM_ROWS_EXAMINED` / nullif(`stmts`.`COUNT_STAR`,0)),0),0) AS `rows_examined_avg`,`stmts`.`FIRST_SEEN` AS `first_seen`,`stmts`.`LAST_SEEN` AS `last_seen`,`stmts`.`DIGEST` AS `digest` from (`performance_schema`.`events_statements_summary_by_digest` `stmts` join `sys`.`x$ps_digest_95th_percentile_by_avg_us` `top_percentile` on((round((`stmts`.`AVG_TIMER_WAIT` / 1000000),0) >= `top_percentile`.`avg_us`))) order by `stmts`.`AVG_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$statements_with_sorting`
--

/*!50001 DROP VIEW IF EXISTS `x$statements_with_sorting`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$statements_with_sorting` AS select `performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES` AS `sort_merge_passes`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_MERGE_PASSES` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `avg_sort_merges`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_SCAN` AS `sorts_using_scans`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_RANGE` AS `sort_using_range`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` AS `rows_sorted`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `avg_rows_sorted`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where (`performance_schema`.`events_statements_summary_by_digest`.`SUM_SORT_ROWS` > 0) order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$statements_with_temp_tables`
--

/*!50001 DROP VIEW IF EXISTS `x$statements_with_temp_tables`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$statements_with_temp_tables` AS select `performance_schema`.`events_statements_summary_by_digest`.`DIGEST_TEXT` AS `query`,`performance_schema`.`events_statements_summary_by_digest`.`SCHEMA_NAME` AS `db`,`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR` AS `exec_count`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` AS `memory_tmp_tables`,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` AS `disk_tmp_tables`,round(ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`COUNT_STAR`,0)),0),0) AS `avg_tmp_tables_per_query`,round((ifnull((`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` / nullif(`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES`,0)),0) * 100),0) AS `tmp_tables_to_disk_pct`,`performance_schema`.`events_statements_summary_by_digest`.`FIRST_SEEN` AS `first_seen`,`performance_schema`.`events_statements_summary_by_digest`.`LAST_SEEN` AS `last_seen`,`performance_schema`.`events_statements_summary_by_digest`.`DIGEST` AS `digest` from `performance_schema`.`events_statements_summary_by_digest` where (`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` > 0) order by `performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_DISK_TABLES` desc,`performance_schema`.`events_statements_summary_by_digest`.`SUM_CREATED_TMP_TABLES` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$user_summary`
--

/*!50001 DROP VIEW IF EXISTS `x$user_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$user_summary` AS select if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) AS `user`,sum(`stmt`.`total`) AS `statements`,sum(`stmt`.`total_latency`) AS `statement_latency`,ifnull((sum(`stmt`.`total_latency`) / nullif(sum(`stmt`.`total`),0)),0) AS `statement_avg_latency`,sum(`stmt`.`full_scans`) AS `table_scans`,sum(`io`.`ios`) AS `file_ios`,sum(`io`.`io_latency`) AS `file_io_latency`,sum(`performance_schema`.`accounts`.`CURRENT_CONNECTIONS`) AS `current_connections`,sum(`performance_schema`.`accounts`.`TOTAL_CONNECTIONS`) AS `total_connections`,count(distinct `performance_schema`.`accounts`.`HOST`) AS `unique_hosts`,sum(`mem`.`current_allocated`) AS `current_memory`,sum(`mem`.`total_allocated`) AS `total_memory_allocated` from (((`performance_schema`.`accounts` left join `sys`.`x$user_summary_by_statement_latency` `stmt` on((if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) = `stmt`.`user`))) left join `sys`.`x$user_summary_by_file_io` `io` on((if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) = `io`.`user`))) left join `sys`.`x$memory_by_user_by_current_bytes` `mem` on((if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) = `mem`.`user`))) group by if(isnull(`performance_schema`.`accounts`.`USER`),'background',`performance_schema`.`accounts`.`USER`) order by sum(`stmt`.`total_latency`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$user_summary_by_file_io`
--

/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_file_io`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$user_summary_by_file_io` AS select if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) AS `user`,sum(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR`) AS `ios`,sum(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) AS `io_latency` from `performance_schema`.`events_waits_summary_by_user_by_event_name` where (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` like 'wait/io/file/%') group by if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) order by sum(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$user_summary_by_file_io_type`
--

/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_file_io_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$user_summary_by_file_io_type` AS select if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) AS `user`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` AS `latency`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency` from `performance_schema`.`events_waits_summary_by_user_by_event_name` where ((`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` like 'wait/io/file%') and (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$user_summary_by_stages`
--

/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_stages`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$user_summary_by_stages` AS select if(isnull(`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`) AS `user`,`performance_schema`.`events_stages_summary_by_user_by_event_name`.`EVENT_NAME` AS `event_name`,`performance_schema`.`events_stages_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_stages_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_stages_summary_by_user_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency` from `performance_schema`.`events_stages_summary_by_user_by_event_name` where (`performance_schema`.`events_stages_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_stages_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_stages_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$user_summary_by_statement_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_statement_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$user_summary_by_statement_latency` AS select if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`) AS `user`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`COUNT_STAR`) AS `total`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_LOCK_TIME`) AS `lock_latency`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_SENT`) AS `rows_sent`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_EXAMINED`) AS `rows_examined`,sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_AFFECTED`) AS `rows_affected`,(sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_INDEX_USED`) + sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_GOOD_INDEX_USED`)) AS `full_scans` from `performance_schema`.`events_statements_summary_by_user_by_event_name` group by if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`) order by sum(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$user_summary_by_statement_type`
--

/*!50001 DROP VIEW IF EXISTS `x$user_summary_by_statement_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$user_summary_by_statement_type` AS select if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`) AS `user`,substring_index(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`EVENT_NAME`,'/',-(1)) AS `statement`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_LOCK_TIME` AS `lock_latency`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_SENT` AS `rows_sent`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_EXAMINED` AS `rows_examined`,`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_ROWS_AFFECTED` AS `rows_affected`,(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_INDEX_USED` + `performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_NO_GOOD_INDEX_USED`) AS `full_scans` from `performance_schema`.`events_statements_summary_by_user_by_event_name` where (`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` <> 0) order by if(isnull(`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_statements_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_statements_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$wait_classes_global_by_avg_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$wait_classes_global_by_avg_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$wait_classes_global_by_avg_latency` AS select substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`,'/',3) AS `event_class`,sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`) AS `total`,sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,min(`performance_schema`.`events_waits_summary_global_by_event_name`.`MIN_TIMER_WAIT`) AS `min_latency`,ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) / nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`),0)),0) AS `avg_latency`,max(`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency` from `performance_schema`.`events_waits_summary_global_by_event_name` where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0) and (`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle')) group by `event_class` order by ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) / nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`),0)),0) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$wait_classes_global_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$wait_classes_global_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=TEMPTABLE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$wait_classes_global_by_latency` AS select substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`,'/',3) AS `event_class`,sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`) AS `total`,sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) AS `total_latency`,min(`performance_schema`.`events_waits_summary_global_by_event_name`.`MIN_TIMER_WAIT`) AS `min_latency`,ifnull((sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) / nullif(sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR`),0)),0) AS `avg_latency`,max(`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT`) AS `max_latency` from `performance_schema`.`events_waits_summary_global_by_event_name` where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0) and (`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle')) group by substring_index(`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME`,'/',3) order by sum(`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$waits_by_host_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$waits_by_host_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$waits_by_host_by_latency` AS select if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`) AS `host`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` AS `event`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency`,`performance_schema`.`events_waits_summary_by_host_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency` from `performance_schema`.`events_waits_summary_by_host_by_event_name` where ((`performance_schema`.`events_waits_summary_by_host_by_event_name`.`EVENT_NAME` <> 'idle') and (`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),'background',`performance_schema`.`events_waits_summary_by_host_by_event_name`.`HOST`),`performance_schema`.`events_waits_summary_by_host_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$waits_by_user_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$waits_by_user_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$waits_by_user_by_latency` AS select if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`) AS `user`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` AS `event`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency`,`performance_schema`.`events_waits_summary_by_user_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency` from `performance_schema`.`events_waits_summary_by_user_by_event_name` where ((`performance_schema`.`events_waits_summary_by_user_by_event_name`.`EVENT_NAME` <> 'idle') and (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER` is not null) and (`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` > 0)) order by if(isnull(`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),'background',`performance_schema`.`events_waits_summary_by_user_by_event_name`.`USER`),`performance_schema`.`events_waits_summary_by_user_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `x$waits_global_by_latency`
--

/*!50001 DROP VIEW IF EXISTS `x$waits_global_by_latency`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`mysql.sys`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `x$waits_global_by_latency` AS select `performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` AS `events`,`performance_schema`.`events_waits_summary_global_by_event_name`.`COUNT_STAR` AS `total`,`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` AS `total_latency`,`performance_schema`.`events_waits_summary_global_by_event_name`.`AVG_TIMER_WAIT` AS `avg_latency`,`performance_schema`.`events_waits_summary_global_by_event_name`.`MAX_TIMER_WAIT` AS `max_latency` from `performance_schema`.`events_waits_summary_global_by_event_name` where ((`performance_schema`.`events_waits_summary_global_by_event_name`.`EVENT_NAME` <> 'idle') and (`performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` > 0)) order by `performance_schema`.`events_waits_summary_global_by_event_name`.`SUM_TIMER_WAIT` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-12  3:27:38
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: driverschool_main
-- ------------------------------------------------------
-- Server version	5.7.9-enterprise-commercial-advanced-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `logs_2016_10`
--

DROP TABLE IF EXISTS `logs_2016_10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs_2016_10` (
  `id` varchar(36) NOT NULL,
  `operName` longtext,
  `operParams` longtext,
  `operResult` varchar(50) DEFAULT NULL,
  `operTime` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `resultMsg` longtext,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_2016_10`
--

LOCK TABLES `logs_2016_10` WRITE;
/*!40000 ALTER TABLE `logs_2016_10` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs_2016_10` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_2016_11`
--

DROP TABLE IF EXISTS `logs_2016_11`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs_2016_11` (
  `id` varchar(36) NOT NULL,
  `operName` longtext,
  `operParams` longtext,
  `operResult` varchar(50) DEFAULT NULL,
  `operTime` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `resultMsg` longtext,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_2016_11`
--

LOCK TABLES `logs_2016_11` WRITE;
/*!40000 ALTER TABLE `logs_2016_11` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs_2016_11` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_2016_12`
--

DROP TABLE IF EXISTS `logs_2016_12`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs_2016_12` (
  `id` varchar(36) NOT NULL,
  `operName` longtext,
  `operParams` longtext,
  `operResult` varchar(50) DEFAULT NULL,
  `operTime` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `resultMsg` longtext,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_2016_12`
--

LOCK TABLES `logs_2016_12` WRITE;
/*!40000 ALTER TABLE `logs_2016_12` DISABLE KEYS */;
INSERT INTO `logs_2016_12` VALUES ('00425cb6-7d5c-48db-8749-e927b3a4177b','save','com.tuocheng.pageModel.demo.Reservation@7e52055c','success','2016-12-12 02:12:07','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@7e52055c','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('07823f6d-12b4-4673-9051-9a2335a206d8','deleteTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 15:29:14','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('08293e0d-f301-4dcf-8b0e-854648874ffa','delete','92f97251-b2eb-4e0f-a877-d99cc77af7f5','success','2016-12-12 02:18:59','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('0a4b0219-91e4-4477-8239-1ccf96fb021a','save','com.tuocheng.pageModel.demo.TrainerArrange@7f31429c','success','2016-12-10 13:33:26','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.TrainerArrange@7f31429c','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('0cd47854-1887-4a37-89d1-fef157c6dc21','save','com.tuocheng.pageModel.demo.TrainerArrange@5da19ba2','success','2016-12-10 13:38:28','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.TrainerArrange@5da19ba2','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('0d8c4755-550f-4178-8c5e-8acb1d70f204','save','com.tuocheng.pageModel.demo.Reservation@5a94fc27','failure','2016-12-12 02:25:11','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','a different object with the same identifier value was already associated with the session: [com.tuocheng.model.demo.TpersonalTiming#19b4887f-9f82-4f8b-b16f-a0ea52098dd3]','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('0de06201-cda0-40e8-a986-333d376ccd95','delete','8d159d14-7bb3-46f9-9946-9e273e27bd90,0063edbb-1a4d-417c-8ecb-6f1f4ce2e126','success','2016-12-12 00:49:00','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('0efb34bc-d602-495b-a023-730a559ba86c','save','com.tuocheng.pageModel.demo.Reservation@68395eb4','success','2016-12-10 15:27:05','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@68395eb4','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('0fefa19e-6fbd-431f-9a95-d40997676ac2','save','com.tuocheng.pageModel.demo.TrainerArrange@768e0397','success','2016-12-10 13:44:35','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','com.tuocheng.pageModel.demo.TrainerArrange@768e0397','c0510169-8edc-470e-b038-c299c7735bty'),('111ee6c1-5aec-4eb4-afc2-a9d700e25837','save','com.tuocheng.pageModel.demo.Reservation@25ce025c','success','2016-12-10 15:42:26','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@25ce025c','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('1133e493-3fe9-46f9-aed0-fc91928d9b43','save','com.tuocheng.pageModel.demo.Reservation@9b6b0a4','success','2016-12-12 02:36:07','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@9b6b0a4','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('11d2b8ab-66d3-450e-9dab-4b9b29549ee7','deleteTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 12:17:34','bsxq:0f62c1a8-28f0-44c8-8de7-1da56c927201',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('13c488fb-5b17-47d1-8a21-c13091c1ccfe','save','com.tuocheng.pageModel.demo.Reservation@7daa1e6f','failure','2016-12-12 02:23:10','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('13f6d188-cfa4-495a-b415-da276654e80a','saveOrUpdateTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 12:17:39',NULL,NULL,NULL),('1562e8a7-8f84-4a3b-b432-ffff7067fd25','save','com.tuocheng.pageModel.demo.Reservation@75216c92','success','2016-12-12 02:53:51','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('1653a484-98c4-435a-9174-1a51217dfe43','save','com.tuocheng.pageModel.demo.Reservation@7644de51','success','2016-12-12 01:09:21','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@7644de51','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('17877ea3-0104-4869-bde4-0d4504df6143','save','com.tuocheng.pageModel.demo.User@28ca01ba','success','2016-12-10 12:30:18','admin:0','com.tuocheng.pageModel.demo.User@28ca01ba','超级管理员'),('1cb72668-9389-4031-bc67-bd8e1144a18f','delete','rows[0].reservationFieldId8','failure','2016-12-12 02:54:47','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('1e10aedd-7332-4db4-a7df-ef7a57963523','save','com.tuocheng.pageModel.demo.Reservation@1d77f0e2','success','2016-12-10 15:46:28','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@1d77f0e2','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('1ece05f8-d08c-45f0-9b16-223d0fda5fb4','save','com.tuocheng.pageModel.demo.Reservation@26e60dae','success','2016-12-12 00:48:43','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@26e60dae','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('1f41ddb2-cd20-4afa-a153-101d36fbc1e7','save','com.tuocheng.pageModel.demo.Reservation@74260899','success','2016-12-12 02:39:25','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('1f61dbe8-fe66-44c0-83e3-6e003bb98824','save','com.tuocheng.pageModel.demo.Reservation@7a145819','success','2016-12-10 15:45:56','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@7a145819','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('20760413-8a2a-47ec-bdcd-fe46e8184c6c','save','com.tuocheng.pageModel.demo.Reservation@254789c1','success','2016-12-12 02:30:35','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@254789c1','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('22076f8f-8beb-4fea-ba95-f2134407010e','delete','e47fbd8a-8697-478d-ae95-767f53db7bf7','success','2016-12-12 03:03:26','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('22a7c02f-c9bd-4049-bd72-a52160abeae0','save','com.tuocheng.pageModel.demo.Reservation@6d013b0d','success','2016-12-12 02:54:30','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@6d013b0d','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('230337ef-8436-4e01-a476-73972a90f35d','save','com.tuocheng.pageModel.demo.Reservation@3247c372','success','2016-12-12 00:51:09','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@3247c372','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('2464afa7-7835-4b2c-9df2-08d5df3d1340','deleteTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 12:31:58','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('272365c8-0ba4-49af-932c-8940c9ffb477','delete','5487e403-e90d-4ff9-8b6f-639f18093c66,1ae773c1-f27e-43f7-b966-e8f1084342c7,4e95d486-81cf-48a3-865f-5d22c07cc566,fb27a94e-77e6-40b5-9fab-8547776068f9','success','2016-12-10 15:47:31','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('28acd18d-dd86-41dc-b63b-35ca1bf0c57e','save','com.tuocheng.pageModel.demo.Reservation@7e6487e5','success','2016-12-12 02:04:58','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@7e6487e5','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('290cb3f6-f574-4f35-a35e-d7df81184ae0','save','com.tuocheng.pageModel.demo.Reservation@4d03a596','success','2016-12-12 02:39:05','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@4d03a596','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('2a9f0c18-6e10-4d37-b8ac-52ab96db0868',NULL,NULL,'failure','2016-12-10 12:31:44',NULL,NULL,NULL),('2d87ab89-7901-4a27-b6b6-43e932d77abb','save','com.tuocheng.pageModel.demo.Reservation@6b384001','success','2016-12-12 00:54:46','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@6b384001','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('2f8247a4-b7ed-4a9b-9c7f-2ff0033b73ab','save','com.tuocheng.pageModel.demo.Reservation@6cdb9b3a','success','2016-12-12 03:02:34','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@6cdb9b3a','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('3218eb8b-465c-41a7-80a5-3c9488105be1','save','com.tuocheng.pageModel.demo.TrainerArrange@6ba79dc5','success','2016-12-10 13:32:41','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('3336b8af-a5a1-41cd-9757-00692de2f350','save','com.tuocheng.pageModel.demo.Reservation@2ff0bf0','failure','2016-12-12 02:38:29','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('3488df84-c798-4346-bf45-97beb7ea7ca3','save','com.tuocheng.pageModel.demo.Reservation@66a3dc91','success','2016-12-12 02:54:40','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@66a3dc91','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('35b1fe87-a428-417c-85f3-4c71376483a4','save','com.tuocheng.pageModel.demo.Reservation@2610bcae','success','2016-12-12 02:50:19','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2610bcae','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('37de1794-3481-43f4-80b2-f17a66864449','deleteTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 15:19:51',NULL,NULL,NULL),('39dca1e6-335b-459c-b97a-f1ae58db8d82','save','com.tuocheng.pageModel.demo.TrainerArrange@3c196477','success','2016-12-10 13:43:18','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','com.tuocheng.pageModel.demo.TrainerArrange@3c196477','c0510169-8edc-470e-b038-c299c7735bty'),('39ec7459-5fea-4c68-b750-008aa0a5c6cc','save','com.tuocheng.pageModel.demo.Reservation@4fddbd0b','success','2016-12-12 02:00:36','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@4fddbd0b','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('3b645e45-8fc4-4130-896b-87bb078622c7','save','com.tuocheng.pageModel.demo.Reservation@5b9084f6','success','2016-12-12 00:48:52','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@5b9084f6','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('3f7da318-b92f-45c7-8621-12fcd045ab22','save','com.tuocheng.pageModel.demo.Reservation@1a0b0070','success','2016-12-12 01:31:57','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@1a0b0070','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('3fbaec24-ecfd-4281-ab71-275dd097c8ef','save','com.tuocheng.pageModel.demo.Reservation@466216ca','success','2016-12-10 15:51:14','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@466216ca','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('40c45937-892f-4116-bbe4-d832103ef312','edit','com.tuocheng.pageModel.demo.Role@5a81551b','success','2016-12-10 12:37:25','admin:0','com.tuocheng.pageModel.demo.Role@5a81551b','超级管理员'),('44730756-4664-4109-ba8c-f814ea763261','save','com.tuocheng.pageModel.demo.Reservation@364a211b','success','2016-12-10 15:26:52','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@364a211b','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('447eb531-0280-4d54-bf8e-229464c0100b','delete','fae4e1bb-3570-4b11-b732-c7d4f4c1746e,439005eb-8905-44bb-b5ba-2eda317f4729','success','2016-12-10 15:43:14','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('485703ab-5561-4104-85c8-fe0c853a5548','save','com.tuocheng.pageModel.demo.Reservation@2c5bba30','failure','2016-12-12 02:37:48','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('486d6be6-47a1-4878-bf37-463ed5466380','saveOrUpdateTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 12:54:52',NULL,NULL,NULL),('48da418c-8cbe-4603-bb8a-132369fd3724','edit','com.tuocheng.pageModel.demo.Role@6490df2e','success','2016-12-10 12:37:52','admin:0','com.tuocheng.pageModel.demo.Role@6490df2e','超级管理员'),('4b72596a-c8ec-4a65-b67c-7203f3d8e849','save','com.tuocheng.pageModel.demo.TrainerArrange@72633d16','success','2016-12-10 13:45:10','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','com.tuocheng.pageModel.demo.TrainerArrange@72633d16','c0510169-8edc-470e-b038-c299c7735bty'),('4baf73ed-4c1c-4c41-9c33-7bc34001a21c','delete','150fab2e-a990-4b77-9303-d3ef14757960','success','2016-12-12 03:03:30','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('50980630-a2cc-4d68-9fdf-4ff0f0f1e0d3','save','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"driverType\":6,\"email\":\"\",\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"identityId\":\"451024198612011001\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张三\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"bsxq\",\"organizationName\":\"b78ffef2-7c54-40fe-be4b-1910a87c8bbs\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"BSF1001\",\"trainerName\":\"\"}','success','2016-12-10 13:39:44','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"createTime\":1481299200530,\"driverType\":6,\"email\":\"\",\"examSelected\":0,\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"id\":\"a1b1478c-2a2f-4e54-97ad-64f65453ad48\",\"identityId\":\"451024198612011001\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张三\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"bsxq\",\"organizationName\":\"b78ffef2-7c54-40fe-be4b-1910a87c8bbs\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"restTiming\":1,\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"BSF1001\",\"trainerName\":\"\"}','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('527e54e1-3760-48a9-b5ea-22f3f4df8e35','delete','26132e6f-8902-4d88-9cd0-a962a0949e28','success','2016-12-12 02:01:53','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('53233b9e-a650-4727-a2db-90cdc69b2320','delete','b16899af-0b30-465f-9e84-cd1dcbe16c6f,55bc38a3-5c00-4558-a854-a3b834cae51f','success','2016-12-10 16:04:30','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('53e38146-8596-4764-afa7-6f50a2dc71f3',NULL,NULL,'failure','2016-12-10 13:48:43',NULL,NULL,NULL),('562b80ef-7684-475c-a7cf-3d11246bb68c','delete','44f626f7-54a3-4aad-a5d9-a871afee6ec1','success','2016-12-12 03:03:14','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('564f89a2-51a0-410b-9f38-f3a477f9c618','save','com.tuocheng.pageModel.demo.Reservation@3f132e97','success','2016-12-12 00:51:51','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@3f132e97','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('57ac1ec9-5f26-4466-8f51-5c5de0798b53','save','com.tuocheng.pageModel.demo.Reservation@18c18a3e','failure','2016-12-12 02:39:44','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('5b55d85b-ff4c-42c1-8363-31b51bd36c98','save','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"driverType\":6,\"email\":\"\",\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"identityId\":\"451024198612011003\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"丁关根\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"bsxq\",\"organizationName\":\"b78ffef2-7c54-40fe-be4b-1910a87c8bbs\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"BSF1003\",\"trainerName\":\"\"}','success','2016-12-10 13:40:19','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"createTime\":1481299200065,\"driverType\":6,\"email\":\"\",\"examSelected\":0,\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"id\":\"a5283ccd-c9eb-41c3-85d4-eab90a0daec5\",\"identityId\":\"451024198612011003\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"丁关根\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"bsxq\",\"organizationName\":\"b78ffef2-7c54-40fe-be4b-1910a87c8bbs\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"restTiming\":1,\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"BSF1003\",\"trainerName\":\"\"}','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('5ba58445-fe28-4421-9eca-60440269d8fe','save','com.tuocheng.pageModel.demo.Reservation@515083bf','success','2016-12-12 01:18:20','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@515083bf','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('5bb1b858-e651-4345-a5b2-49b4fb508c4c','save','com.tuocheng.pageModel.demo.Reservation@bb6bca4','success','2016-12-12 02:26:16','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@bb6bca4','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('60939f08-a9c8-43bc-be06-5c1777973231','delete','null','success','2016-12-12 03:04:35','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('61653973-b649-48ac-ba33-199b68a5423b','delete','63c88525-c1b0-4265-8218-097492585c46','success','2016-12-10 13:47:02','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109',NULL,'c0510169-8edc-470e-b038-c299c7735bty'),('630bc9d0-2f4e-4a42-aea1-a852f9dec01e','save','com.tuocheng.pageModel.demo.TrainerArrange@253aca2b','success','2016-12-10 13:32:40','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('63429cfc-1e88-487d-83d4-ac1bf50e22a9','delete','eb64b258-c808-47cd-abca-f0b2b8df8d27','success','2016-12-12 02:00:22','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('650507e9-47ba-403e-826b-b6370b6ec72a','save','com.tuocheng.pageModel.demo.Reservation@98e967','success','2016-12-12 03:21:04','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@98e967','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('6aa1c7df-1cf5-48ef-ac0a-d24cb23c63f7','save','com.tuocheng.pageModel.demo.Reservation@4c33030e','failure','2016-12-12 02:54:05','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('6ca348c3-ea02-42d5-b0da-b01786061107','save','com.tuocheng.pageModel.demo.Reservation@3fb40c52','success','2016-12-12 02:53:43','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('6cf4e150-fa07-4e2d-9877-a0236c80a21a','delete','ecfe0f4d-2d2f-45e0-877c-47e34d3fe163','success','2016-12-12 03:03:20','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('6d2fb9fb-84a4-4b3c-823f-b02d88650698',NULL,NULL,'failure','2016-12-10 15:22:36',NULL,NULL,NULL),('712ee28d-cf5d-4346-8860-d91109c06645','save','com.tuocheng.pageModel.demo.Reservation@e6c150','success','2016-12-12 02:11:45','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@e6c150','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('71a06f7d-914a-4774-81ec-fe8d56af6afc','delete','8898d560-2c8f-45e9-9cb2-086fbfe3445d','success','2016-12-12 00:46:13','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('73105642-6f15-4455-8185-bf9fff137e04','delete','084a699b-f0e8-48f2-b83e-379f453ac236','success','2016-12-12 03:03:35','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('74132c71-a13d-40ff-b577-3dd7b92c396f','delete','null','success','2016-12-12 03:04:28','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('76bb712b-9069-4b0e-b43b-cc44d091a93d','delete','1e43d2f3-59b1-43cf-8b5b-5c8ba3a7e634','success','2016-12-12 02:18:44','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('76e264e2-f91b-45c2-8adf-70f888f25c50','save','com.tuocheng.pageModel.demo.Reservation@1d34ea4','success','2016-12-12 02:32:23','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('7a85bbe1-9f71-4835-84e3-c01ef7c7eb9d','save','{\"address\":\"广西百色市田阳县江与城小区\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"driverType\":6,\"email\":\"\",\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"identityId\":\"452526198612011001\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张成晚\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1001\",\"trainerName\":\"\"}','success','2016-12-10 13:47:38','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','{\"address\":\"广西百色市田阳县江与城小区\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"createTime\":1481299200825,\"driverType\":6,\"email\":\"\",\"examSelected\":0,\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"id\":\"37f6d0a7-210c-418a-ab79-7aaab89b5a44\",\"identityId\":\"452526198612011001\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张成晚\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"restTiming\":1,\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1001\",\"trainerName\":\"\"}','c0510169-8edc-470e-b038-c299c7735bty'),('7b166550-53ea-4657-b739-6ea53ac882cf','delete','8a6379f8-c23b-4f6a-a7df-61a84e0a3d0f','success','2016-12-12 03:05:26','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('7c8ae2ad-71a3-47b0-ae5e-17fe6c4343f4','save','com.tuocheng.pageModel.demo.TrainerArrange@7905f423','success','2016-12-10 13:44:02','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','com.tuocheng.pageModel.demo.TrainerArrange@7905f423','c0510169-8edc-470e-b038-c299c7735bty'),('7d2370ae-211f-433d-85e3-cc0c75849a97','saveOrUpdateTonlineByLoginNameAndIp','tyxq,本地','success','2016-12-10 13:40:36',NULL,NULL,NULL),('7e55e664-7227-40c9-92b8-ac4fd17ce7e3','save','com.tuocheng.pageModel.demo.Reservation@4d60061','success','2016-12-12 03:09:47','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@4d60061','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('800324cb-bad7-49cd-9669-f54d40a19391','saveOrUpdateTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 13:18:41',NULL,NULL,NULL),('8153300d-7526-46fe-a144-140ed847ad91','save','com.tuocheng.pageModel.demo.Reservation@2c6e5d90','success','2016-12-10 15:46:02','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2c6e5d90','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('829d4287-42e0-439d-8614-6dcff96942d9',NULL,NULL,'failure','2016-12-12 02:35:37',NULL,NULL,NULL),('856cb401-2f24-4f4c-ac4a-5387e2a781b7',NULL,NULL,'failure','2016-12-12 02:29:30',NULL,NULL,NULL),('85d80074-bcee-4e60-88bd-18c6971b8f6b','delete','f3de2581-4e2c-4ff1-86e6-14ce20e3a60d,3ac332a3-f6fe-4250-a836-1dcc93eb42fa,b1966cd9-759a-418c-8d70-6b8a3ad78259,f41868e0-0f19-400e-8421-bde9fced6feb,0cacb93f-4a84-4ca6-92c4-e4313b04b9b7','success','2016-12-10 15:27:47','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('8711729e-7a04-4150-88a7-df4bfae97991','save','{\"address\":\"广西百色市田阳县江与城小区\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"driverType\":6,\"email\":\"\",\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"identityId\":\"452526198612011003\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"丁关根\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1003\",\"trainerName\":\"\"}','success','2016-12-10 13:48:31','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','{\"address\":\"广西百色市田阳县江与城小区\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"createTime\":1481299200208,\"driverType\":6,\"email\":\"\",\"examSelected\":0,\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"id\":\"62c3bf3b-6654-4007-9f3b-e10362552005\",\"identityId\":\"452526198612011003\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"丁关根\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"restTiming\":1,\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1003\",\"trainerName\":\"\"}','c0510169-8edc-470e-b038-c299c7735bty'),('87755e57-a2f2-497f-b694-786d5f397534','save','com.tuocheng.pageModel.demo.Reservation@5c588ded','failure','2016-12-12 02:19:40','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('8a0d2537-0d10-473a-9512-320b9b975712','delete','e53025e7-df8e-4c2d-b60c-f7cf7baa31dd,4b850f87-fb50-431f-a4fb-5bb0c942af73','success','2016-12-12 00:58:17','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('8ad5e64d-5880-4dfa-bccb-c99e42bd2917','saveOrUpdateTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 15:29:21',NULL,NULL,NULL),('8c4386b0-0638-4d15-84cc-d23dc3f28645','deleteTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 12:53:16',NULL,NULL,NULL),('8ce3413d-1a5e-46da-b922-5673e41fe762','save','com.tuocheng.pageModel.demo.Reservation@2ad48d17','success','2016-12-12 03:08:05','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2ad48d17','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('8cece9f2-7998-47d9-a02a-59629b239092','delete','rows[0].reservationFieldId8','failure','2016-12-12 02:53:35','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('8e2f67bc-2829-4e42-88d7-d781ee204ef5','save','com.tuocheng.pageModel.demo.Reservation@6f9ef30f','success','2016-12-12 00:55:41','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@6f9ef30f','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('918d9554-6dac-4264-8a7e-5fe3fcaa4fa9','save','com.tuocheng.pageModel.demo.Reservation@75c78536','success','2016-12-12 00:45:18','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@75c78536','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('9218c9ca-762e-44e6-8a69-53f74c301fe4','save','com.tuocheng.pageModel.demo.Reservation@3100726d','success','2016-12-12 03:05:16','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@3100726d','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('92c30e41-987b-4aeb-9ed0-a256ae77ea0c','save','com.tuocheng.pageModel.demo.Reservation@2500c40a','success','2016-12-12 00:51:02','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2500c40a','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('990b5699-f3c5-4705-90e6-cd93b30ec714','delete','null','success','2016-12-12 03:04:22','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('a3b5e39a-b2d8-40ee-a130-0b11bd57233e','save','com.tuocheng.pageModel.demo.Reservation@35e4d41a','success','2016-12-12 03:05:11','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@35e4d41a','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('a3f2eacf-3d92-46d1-bcd3-7855d85b0a80','deleteTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 17:49:16',NULL,NULL,NULL),('a54930db-2a0a-44bb-8d2b-e2293f4238bf','delete','69a006c1-2f37-4c8c-989a-bf208a112028,b9f35016-b737-4dc6-8bd8-5d4c7dfdb0ad,73dce7e5-7ad0-4f24-a098-4e4fc99d5f38','success','2016-12-12 00:54:22','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('a5b21c32-c32d-442c-8374-68ef25582745','delete','rows[0].reservationFieldId8','failure','2016-12-12 02:53:10','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('a65ed615-6064-44bf-9cfe-eec4a7a43152','save','{\"address\":\"广西百色市田阳县江与城小区\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"driverType\":6,\"email\":\"\",\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"identityId\":\"452526198612011002\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张国立\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1002\",\"trainerName\":\"\"}','success','2016-12-10 13:48:06','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','{\"address\":\"广西百色市田阳县江与城小区\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"createTime\":1481299200703,\"driverType\":6,\"email\":\"\",\"examSelected\":0,\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"id\":\"c5de01a4-b7da-4979-afd4-d56dcc560aa5\",\"identityId\":\"452526198612011002\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张国立\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"restTiming\":1,\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1002\",\"trainerName\":\"\"}','c0510169-8edc-470e-b038-c299c7735bty'),('a8f436fc-12e0-4230-b8e4-a77077734123','save','com.tuocheng.pageModel.demo.TrainerArrange@535e7b65','success','2016-12-10 13:32:38','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('a934c9e8-570a-4701-9093-f35a58b84987','deleteTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 13:18:36','admin:0',NULL,'超级管理员'),('aa336c9f-694e-41cc-a0fd-36f78fc03e04','save','com.tuocheng.pageModel.demo.Reservation@77c1df60','success','2016-12-10 15:42:36','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@77c1df60','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('aa46e5a7-4f5f-4791-92ac-ecb45c9cd3f6','save','com.tuocheng.pageModel.demo.Reservation@4ba0ca2','success','2016-12-10 15:25:41','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@4ba0ca2','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('abd64706-5cac-4230-aeea-f87c551ca6f6','save','com.tuocheng.pageModel.demo.Reservation@102ab5ee','success','2016-12-12 03:02:45','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@102ab5ee','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('abf71c40-34ed-4d10-a4bf-dcd7afe247c1',NULL,NULL,'failure','2016-12-12 02:24:45',NULL,NULL,NULL),('ac41d08d-65cc-4949-9e89-4c1ac28fa6ee','delete','8bdc5a29-b0c9-47b4-b840-e7f5603b4e6e','success','2016-12-12 01:08:34','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('ad16ce63-48e0-432a-957a-346effabb4bd','edit','com.tuocheng.pageModel.demo.Role@6dce14e1','success','2016-12-10 12:37:51','admin:0','com.tuocheng.pageModel.demo.Role@6dce14e1','超级管理员'),('adaa1347-eb60-4625-b153-d0452d5c2c20','save','com.tuocheng.pageModel.demo.Reservation@2b4aa36f','success','2016-12-12 01:06:13','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2b4aa36f','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('ae3a5210-e8fa-48d4-96d0-d845377415bb','save','com.tuocheng.pageModel.demo.Reservation@5282a30','failure','2016-12-12 02:39:31','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('ae5b7880-74f9-4520-b37e-1ee27000f8ac','save','com.tuocheng.pageModel.demo.Car@70d05c4c','success','2016-12-10 13:42:34','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','com.tuocheng.pageModel.demo.Car@70d05c4c','c0510169-8edc-470e-b038-c299c7735bty'),('ae5c08ae-db86-4b06-a4a3-e6e509aa4c8a','save','com.tuocheng.pageModel.demo.Reservation@47c57373','failure','2016-12-12 02:39:36','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('afd2c7e2-d40f-4f4d-b21c-008b0050ff8b','save','com.tuocheng.pageModel.demo.Reservation@21bf67a7','failure','2016-12-12 02:54:01','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('b18177dd-76c3-4f84-b1a7-8f279d856f8f','save','com.tuocheng.pageModel.demo.Reservation@743d9349','failure','2016-12-12 02:37:53','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('b226bf6a-00fa-4889-9c57-4e68bf9e54a4','save','com.tuocheng.pageModel.demo.Reservation@22271ddb','failure','2016-12-12 02:38:06','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('b33ac466-d0ee-42f2-81db-50a14e64f434',NULL,NULL,'failure','2016-12-10 14:13:07',NULL,NULL,NULL),('b455e75a-58a9-4104-a525-978ea27ace63','save','com.tuocheng.pageModel.demo.Reservation@724e79c2','success','2016-12-12 02:16:30','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@724e79c2','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('b59c7417-5b23-4a23-9ecf-fc83273a1cc9','save','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"driverType\":6,\"email\":\"\",\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"identityId\":\"451024198612011002\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张四\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"bsxq\",\"organizationName\":\"b78ffef2-7c54-40fe-be4b-1910a87c8bbs\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"BSF1002\",\"trainerName\":\"\"}','success','2016-12-10 13:40:04','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"createTime\":1481299200001,\"driverType\":6,\"email\":\"\",\"examSelected\":0,\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"id\":\"00243719-b5db-49e5-ab21-915961bfe1a5\",\"identityId\":\"451024198612011002\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"张四\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"bsxq\",\"organizationName\":\"b78ffef2-7c54-40fe-be4b-1910a87c8bbs\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"restTiming\":1,\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"BSF1002\",\"trainerName\":\"\"}','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('b76cba4f-c6e3-4408-9922-3000580bf661','save','com.tuocheng.pageModel.demo.Reservation@5a31d946','success','2016-12-10 15:26:17','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@5a31d946','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('b8af3ac7-8284-4c40-9a98-a9c8e03d1ed8','delete','5f62b504-bf4c-4b92-9f8a-e66ee73f20f4','success','2016-12-12 03:05:20','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('ba647b0a-6ebe-4a6d-82d5-04f58cdcdd2f','save','com.tuocheng.pageModel.demo.Reservation@6488e968','success','2016-12-12 02:37:40','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('bad652ef-011a-4c6a-84ce-2d8446047b30','delete','80eeac54-79a0-417a-ae09-373ba5805645','success','2016-12-12 03:15:01','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('bbd35551-34a5-4b69-a787-a80b049eca65','save','com.tuocheng.pageModel.demo.Reservation@459477e6','success','2016-12-10 15:26:13','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@459477e6','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('bbecee9f-2039-4172-807b-2bddfc9b17c8','save','com.tuocheng.pageModel.demo.Reservation@7aca353','failure','2016-12-12 02:38:14','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('bc342197-af83-4a7c-a869-7a9ebc9cce74','save','com.tuocheng.pageModel.demo.Reservation@eae785b','success','2016-12-12 02:50:25','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@eae785b','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('bc37e6a4-b3a4-490c-b4c7-b7ddf3de9ef7','delete','null','success','2016-12-12 03:05:05','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('be62ba81-0c0d-4a5d-96fc-eeef9ee68cea','save','com.tuocheng.pageModel.demo.Reservation@65460fc0','success','2016-12-12 02:16:10','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@65460fc0','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('be694b1c-0316-407d-bfd7-bcefd2709b9c','save','com.tuocheng.pageModel.demo.Reservation@4a9a8653','success','2016-12-12 03:02:39','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@4a9a8653','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('bfdb2a40-7037-4818-81cc-4aac8b7b9104','delete','7e58cc70-dced-4077-bf15-c0e210e36408','success','2016-12-12 03:02:11','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c149f909-c76e-42c2-9561-1f490700504a','deleteTonlineByLoginNameAndIp','tyxq,本地','success','2016-12-10 13:48:38','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109',NULL,'c0510169-8edc-470e-b038-c299c7735bty'),('c1eec9fd-2ceb-442a-b5fb-f882c6028fb9','save','com.tuocheng.pageModel.demo.Reservation@3d90f290','success','2016-12-12 01:18:02','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@3d90f290','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c27f011a-9ce0-4cfe-9115-e2a3c0871697','save','com.tuocheng.pageModel.demo.Reservation@73fa81a','failure','2016-12-12 02:39:15','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c336df8a-fe78-44fc-85bd-b0c1a9b90117','save','com.tuocheng.pageModel.demo.Car@7aa7be43','success','2016-12-10 13:42:44','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','com.tuocheng.pageModel.demo.Car@7aa7be43','c0510169-8edc-470e-b038-c299c7735bty'),('c4fa429c-b119-477b-92f3-631f53253a37','delete','29c30c6c-aa1f-49fb-bbd4-49b071834e86,d36a5f8f-7c63-49f5-bbff-ba1a7d73c2b3','success','2016-12-12 02:13:39','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c62468ce-b246-4701-b21e-88fcd0a0857d','save','com.tuocheng.pageModel.demo.Reservation@2aa0f4af','success','2016-12-12 02:19:29','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2aa0f4af','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c647d2cd-ad95-4222-be14-63965bd926e8','save','com.tuocheng.pageModel.demo.Reservation@1cbe810b','success','2016-12-10 15:47:53','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@1cbe810b','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c7bb2202-a0ba-437b-b5aa-58c19ebedc7e','delete','eb64b258-c808-47cd-abca-f0b2b8df8d27,26132e6f-8902-4d88-9cd0-a962a0949e28','failure','2016-12-12 02:01:47','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c7d457b0-1d00-4287-8f74-aa80da2e55f2',NULL,NULL,'failure','2016-12-10 17:19:50',NULL,NULL,NULL),('c894a972-b948-4a3a-83bc-3323bd280597','save','com.tuocheng.pageModel.demo.TrainerArrange@7aeb4593','success','2016-12-10 13:27:02','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.TrainerArrange@7aeb4593','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c89e659b-93b4-4702-b7f6-f0a52617833f','save','com.tuocheng.pageModel.demo.Reservation@7c5179ac','success','2016-12-12 03:02:27','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@7c5179ac','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c96bba1d-deb7-4356-abf1-7e076bc558a5','save','com.tuocheng.pageModel.demo.TrainerArrange@6a031813','success','2016-12-10 13:36:03','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.TrainerArrange@6a031813','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('c9fbcc59-39c8-424f-ad4f-3ff32675316f','deleteTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 12:31:40','admin:0',NULL,'超级管理员'),('ca3700d1-967e-498d-b694-18c4b59d8269',NULL,NULL,'failure','2016-12-12 02:00:10',NULL,NULL,NULL),('ca88ed8e-7ca1-41f7-bff9-2fb36c50454e','edit','com.tuocheng.pageModel.demo.Role@2db3145f','success','2016-12-10 13:18:00','admin:0','com.tuocheng.pageModel.demo.Role@2db3145f','超级管理员'),('cbefa0c4-3a45-4fe6-8d29-65e9c71ae364','deleteTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 15:38:56','admin:0',NULL,'超级管理员'),('cc3a7cb7-932a-4ab2-8bfd-e507e16e5585','save','com.tuocheng.pageModel.demo.Reservation@2e7df6fc','success','2016-12-10 15:51:02','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2e7df6fc','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('cd63e665-59b1-4f9a-ab1f-118a6a1d8c4a','delete','4e95d486-81cf-48a3-865f-5d22c07cc566,32f8bc2e-a1e9-452f-a12a-19edc45fe6c7,6ab20e9a-2565-4d6c-a55c-31e82ec5b635','success','2016-12-10 15:48:17','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('cf0bb542-fbdb-4b7c-b413-6ab155b1b8c4','save','com.tuocheng.pageModel.demo.Reservation@1d34fcb1','failure','2016-12-12 02:36:11','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d08dce5c-92df-4d7d-8b6c-f88031e36b73','save','com.tuocheng.pageModel.demo.TrainerArrange@5ab79af7','success','2016-12-10 13:38:57','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.TrainerArrange@5ab79af7','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d0dadbd1-2b64-4a32-8072-05dc7b3b9796','save','com.tuocheng.pageModel.demo.Reservation@25c01012','success','2016-12-12 03:09:22','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@25c01012','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d152e2ca-bc64-42e3-8dba-353b95b5d38c','saveOrUpdateTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 15:39:05',NULL,NULL,NULL),('d401bb59-9bc5-4827-8a9a-c8662ddb6738','save','com.tuocheng.pageModel.demo.TrainerArrange@22dea18e','success','2016-12-10 13:35:10','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.TrainerArrange@22dea18e','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d53f2f07-561d-49e2-a38d-c86b19e2372d','save','com.tuocheng.pageModel.demo.Reservation@c583bf6','success','2016-12-12 01:15:36','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@c583bf6','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d564d55a-b724-4704-a44c-f43533f9aba3','save','com.tuocheng.pageModel.demo.Reservation@7e020fff','success','2016-12-12 02:29:50','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d5c3368c-87cb-4ae7-8de2-dc466674ffc0','save','com.tuocheng.pageModel.demo.Reservation@15ec45f5','success','2016-12-10 15:47:58','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@15ec45f5','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d69d161a-e194-48cd-aa78-c6a3afeb347e','delete','5487e403-e90d-4ff9-8b6f-639f18093c66,1ae773c1-f27e-43f7-b966-e8f1084342c7,4e95d486-81cf-48a3-865f-5d22c07cc566,fb27a94e-77e6-40b5-9fab-8547776068f9,32f8bc2e-a1e9-452f-a12a-19edc45fe6c7,6ab20e9a-2565-4d6c-a55c-31e82ec5b635','failure','2016-12-10 15:48:11','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('d6b718bb-0014-4bea-9cd2-fe659fd6a761','saveOrUpdateTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 12:32:11',NULL,NULL,NULL),('d72771ac-d6e8-4de8-959e-6dc94c4be990',NULL,NULL,'failure','2016-12-10 12:38:47',NULL,NULL,NULL),('d764dc05-ada5-4d7a-bcdb-ce75bceec210','delete','aa75e1c0-f8df-4a30-9010-d1f3ba0893e3','success','2016-12-12 03:09:44','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('daeb811c-d303-4073-a629-bf7f0d8e53b3','save','com.tuocheng.pageModel.demo.Reservation@72a1e744','success','2016-12-10 15:46:23','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@72a1e744','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('dcb81b54-5ed9-4021-982d-040d52fadac0','delete','0466f19c-7885-4de4-bc98-aa11277a99bb','success','2016-12-12 02:59:45','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('dfd33f21-5247-41ca-8c79-56c6ca9fd477','save','com.tuocheng.pageModel.demo.Reservation@31083bbf','success','2016-12-12 03:02:50','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@31083bbf','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e0541a34-0dcd-47c1-bccb-abb6ee9b527f','save','com.tuocheng.pageModel.demo.Reservation@384ebc84','success','2016-12-12 03:08:08','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@384ebc84','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e19fd2a0-796a-4415-bfb2-dfcca60ebc34','deleteTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 13:40:25','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e341982e-7fa9-4d89-92a2-90edbc2aa919','delete','2eed844f-a155-4c28-874c-e53fc8755455','success','2016-12-12 03:02:19','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e3a1d8b4-b50e-43f4-b8c4-d1b5de505d25','save','com.tuocheng.pageModel.demo.Reservation@7a346fe7','success','2016-12-12 00:56:52','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@7a346fe7','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e3cf70ab-afc2-457b-9863-0c0e4601f26a','delete','ade34693-70a6-46ba-979a-95dc01390bfb','success','2016-12-12 00:55:54','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e403cd46-f573-4a64-8bf2-7d2223c0bef8','save','com.tuocheng.pageModel.demo.Car@1541b3c5','success','2016-12-10 13:24:25','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Car@1541b3c5','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e517d102-5a9e-47e4-8b41-5090dda3f13b','save','com.tuocheng.pageModel.demo.Reservation@70e4afa6','success','2016-12-12 03:14:52','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@70e4afa6','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e51c4dda-6de6-4ae6-8287-b7ed6454d15d','save','com.tuocheng.pageModel.demo.User@528c2446','success','2016-12-10 12:31:30','admin:0','com.tuocheng.pageModel.demo.User@528c2446','超级管理员'),('e63e5bdc-9c7c-4676-9699-ad65167682a2','delete','c0da9b17-8b35-49e0-9d29-117661dae502','success','2016-12-12 02:30:17','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e8177db3-5d97-4b80-8b5f-d73c52701a24','save','com.tuocheng.pageModel.demo.Reservation@69311304','success','2016-12-10 15:47:50','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@69311304','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e82ba18e-204e-4dea-8b22-dd44f1224211','deleteTonlineByLoginNameAndIp','admin,本地','success','2016-12-10 12:54:40','admin:0',NULL,'超级管理员'),('e86e8a21-3452-4eda-b3c7-572fd22e26d1','save','com.tuocheng.pageModel.demo.Reservation@1e67c4b4','success','2016-12-12 02:36:19','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e96a3601-0051-4035-b246-8111a7a921de','delete','117977e9-ea05-4817-8819-f57df2882264','success','2016-12-12 00:44:50','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('e9af406b-bc7d-4ae7-afb7-01a944a60573',NULL,NULL,'failure','2016-12-12 00:43:26',NULL,NULL,NULL),('e9f6123e-f410-47a5-8a8e-2a1540fb5906','save','com.tuocheng.pageModel.demo.Car@56b8a767','success','2016-12-10 13:24:13','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Car@56b8a767','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('eaff6baf-d5c6-4e0e-87a3-7cf08d0f64ef','save','com.tuocheng.pageModel.demo.Reservation@2a0ce699','success','2016-12-12 00:56:59','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@2a0ce699','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('ef955741-3a86-46c3-898d-a28b0b7cad38','save','com.tuocheng.pageModel.demo.Reservation@592d615c','success','2016-12-12 02:37:27','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@592d615c','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('efdd9236-e6eb-4d3b-9110-2de966077cb1','deleteTonlineByLoginNameAndIp','bsxq,本地','success','2016-12-10 17:15:15',NULL,NULL,NULL),('f0b4d521-8ef7-4933-8de2-42cffa13ebae','save','com.tuocheng.pageModel.demo.Reservation@833dbbf','success','2016-12-12 03:09:53','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@833dbbf','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('f6e985b4-ca36-40ce-85f6-420871378e91','save','com.tuocheng.pageModel.demo.Reservation@415c6e0a','success','2016-12-12 02:19:18','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@415c6e0a','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('f7619cb3-9104-429a-8171-71f596d26745','save','com.tuocheng.pageModel.demo.Reservation@7082607c','success','2016-12-12 02:54:26','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@7082607c','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('f78c1dbb-c305-4d0a-a114-d7e7f736b0cd','save','com.tuocheng.pageModel.demo.Car@2fb2a5c5','success','2016-12-10 13:24:40','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Car@2fb2a5c5','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('f8c274d2-4e2d-4061-a75a-2dced103fe54','save','com.tuocheng.pageModel.demo.Car@6e319b92','success','2016-12-10 13:42:20','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','com.tuocheng.pageModel.demo.Car@6e319b92','c0510169-8edc-470e-b038-c299c7735bty'),('f987d24f-2008-4bec-b75f-8df83dd0fa94','delete','b98e9305-b187-4f4c-8b38-754b8a812d00,ee3ebf12-4dd5-438f-9391-e43986ef9022,b581ff48-4bd3-427f-af88-658eb9463c24,7247fe56-afa4-412a-811d-9a23a77f2299','success','2016-12-12 03:22:42','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('f99ec35f-c10a-479a-bbda-1b3ff6d21551','save','com.tuocheng.pageModel.demo.Reservation@70c93b09','success','2016-12-12 00:43:57','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@70c93b09','b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('fca5a3d9-3342-4975-a681-3311fe2e7e5c','save','com.tuocheng.pageModel.demo.TrainerArrange@391e3a2a','success','2016-12-10 13:32:48','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('fed4dc76-987d-4936-a5d6-f405fd0471fb','save','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"driverType\":6,\"email\":\"\",\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"identityId\":\"452526198612011001\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"李克农\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1001\",\"trainerName\":\"\"}','success','2016-12-10 13:45:45','tyxq:659633b3-93a8-4dfb-957f-8a0abe7fd109','{\"address\":\"广西百色市城北一路29号\",\"analyColor\":\"合格\",\"applyType\":1,\"body\":\"合格\",\"clazzName\":\"\",\"comment\":\"\",\"createTime\":1481299200822,\"driverType\":6,\"email\":\"\",\"examSelected\":0,\"eyeRedress\":\"合格\",\"hearing\":\"合格\",\"hight\":\"\",\"hospital\":\"\",\"id\":\"63c88525-c1b0-4265-8218-097492585c46\",\"identityId\":\"452526198612011001\",\"imageId\":\"\",\"learningTime\":20,\"leftDownLimb\":\"\",\"leftEye\":\"合格\",\"leftUpperLimb\":\"合格\",\"name\":\"李克农\",\"natively\":\"本地\",\"nowState\":1,\"operator\":\"tyxq\",\"organizationName\":\"c0510169-8edc-470e-b038-c299c7735bty\",\"ownFeed\":0,\"page\":0,\"personName\":\"\",\"phone\":\"13324769338\",\"residenceAddr\":\"\",\"residenceId\":\"\",\"restTiming\":1,\"rightDownLimb\":\"合格\",\"rightEye\":\"合格\",\"rightUpperLimb\":\"合格\",\"rows\":0,\"sex\":\"\",\"studentNo\":\"TY1001\",\"trainerName\":\"\"}','c0510169-8edc-470e-b038-c299c7735bty'),('ff54f6cc-e994-4124-9fb3-462b19584420','save','com.tuocheng.pageModel.demo.Reservation@788a0a98','success','2016-12-12 02:50:54','bsxq:c2ef98ca-2dbf-452c-850e-493272135cc0','com.tuocheng.pageModel.demo.Reservation@788a0a98','b78ffef2-7c54-40fe-be4b-1910a87c8bbs');
/*!40000 ALTER TABLE `logs_2016_12` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_2017_01`
--

DROP TABLE IF EXISTS `logs_2017_01`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs_2017_01` (
  `id` varchar(36) NOT NULL,
  `operName` longtext,
  `operParams` longtext,
  `operResult` varchar(50) DEFAULT NULL,
  `operTime` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `resultMsg` longtext,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_2017_01`
--

LOCK TABLES `logs_2017_01` WRITE;
/*!40000 ALTER TABLE `logs_2017_01` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs_2017_01` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs_2017_02`
--

DROP TABLE IF EXISTS `logs_2017_02`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs_2017_02` (
  `id` varchar(36) NOT NULL,
  `operName` longtext,
  `operParams` longtext,
  `operResult` varchar(50) DEFAULT NULL,
  `operTime` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `resultMsg` longtext,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs_2017_02`
--

LOCK TABLES `logs_2017_02` WRITE;
/*!40000 ALTER TABLE `logs_2017_02` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs_2017_02` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_arrangedexamination`
--

DROP TABLE IF EXISTS `tb_arrangedexamination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_arrangedexamination` (
  `id` varchar(36) NOT NULL,
  `applyDate` datetime DEFAULT NULL,
  `applyTotal` int(11) DEFAULT NULL,
  `approveDate` datetime DEFAULT NULL,
  `approveTotal` int(11) DEFAULT NULL,
  `arrangedNo` varchar(36) DEFAULT NULL,
  `bussinessType` int(11) DEFAULT NULL,
  `examinationSubject` int(11) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_arrangedexamination`
--

LOCK TABLES `tb_arrangedexamination` WRITE;
/*!40000 ALTER TABLE `tb_arrangedexamination` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_arrangedexamination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_article`
--

DROP TABLE IF EXISTS `tb_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_article` (
  `id` varchar(36) NOT NULL,
  `author` varchar(40) DEFAULT NULL,
  `content` longtext,
  `createTime` datetime DEFAULT NULL,
  `sortNo` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `typeNo` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_article`
--

LOCK TABLES `tb_article` WRITE;
/*!40000 ALTER TABLE `tb_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_assess`
--

DROP TABLE IF EXISTS `tb_assess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_assess` (
  `id` varchar(36) NOT NULL,
  `comments` longtext,
  `createTime` datetime DEFAULT NULL,
  `evaluate` longtext,
  `openId` varchar(100) DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_assess`
--

LOCK TABLES `tb_assess` WRITE;
/*!40000 ALTER TABLE `tb_assess` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_assess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_auth`
--

DROP TABLE IF EXISTS `tb_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_auth` (
  `CID` varchar(36) NOT NULL,
  `CDESC` varchar(200) DEFAULT NULL,
  `CNAME` varchar(100) NOT NULL,
  `CSEQ` decimal(22,0) DEFAULT NULL,
  `CURL` varchar(200) DEFAULT NULL,
  `menuId` varchar(36) DEFAULT NULL,
  `CPID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`CID`),
  KEY `FKA4F65CD999D15AB4` (`CPID`),
  CONSTRAINT `FKA4F65CD999D15AB4` FOREIGN KEY (`CPID`) REFERENCES `tb_auth` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_auth`
--

LOCK TABLES `tb_auth` WRITE;
/*!40000 ALTER TABLE `tb_auth` DISABLE KEYS */;
INSERT INTO `tb_auth` VALUES ('0','拓程科技软件研发部','驾校一体化管理系统',1,'',NULL,NULL),('bugadd','','上报BUG',4,'/demo/bugAction!add.action',NULL,'buggl'),('bugaddym','','上报BUG页面',3,'/demo/bugAction!bugAdd.action',NULL,'buggl'),('bugcx','','BUG查询',2,'/demo/bugAction!datagrid.action',NULL,'buggl'),('bugdelete','','BUG删除',7,'/demo/bugAction!delete.action',NULL,'buggl'),('bugdesc','','查看BUG描述',9,'/demo/bugAction!showCdesc.action',NULL,'buggl'),('bugedit','','BUG编辑',6,'/demo/bugAction!edit.action',NULL,'buggl'),('bugeditym','','编辑BUG页面',5,'/demo/bugAction!bugEdit.action',NULL,'buggl'),('buggl','','BUG管理',5,'','buggl','xtgl'),('bugglym','','BUG管理页面',1,'/demo/bugAction!bug.action',NULL,'buggl'),('bugupload','','BUG上传',8,'/demo/bugAction!upload.action',NULL,'buggl'),('carMangerMsg','','车辆信息管理',1,'',NULL,'carMsg'),('carMangerMsg01','','车辆信息主页',1,'/demo/carAction!car.action',NULL,'carMangerMsg'),('carMangerMsg02','','添加页面',2,'/demo/carAction!toAddPage.action',NULL,'carMangerMsg'),('carMangerMsg03','','更新页面',3,'/demo/carAction!toEditPage.action',NULL,'carMangerMsg'),('carMangerMsg04','','车辆信息列表',4,'/demo/carAction!datagrid.action',NULL,'carMangerMsg'),('carMangerMsg05','','添加车辆信息',5,'/demo/carAction!add.action',NULL,'carMangerMsg'),('carMangerMsg06','','更新车辆信息',6,'/demo/carAction!edit.action',NULL,'carMangerMsg'),('carMangerMsg07','','删除车辆',7,'/demo/carAction!delete.action',NULL,'carMangerMsg'),('carMangerMsg08','','导入车辆信息',8,'/demo/carAction!toUploadPage.action',NULL,'carMangerMsg'),('carMsg','','车辆管理',5,'','carmanager','0'),('carUsingMsg','','车辆使用情况',2,'',NULL,'carMsg'),('carUsingMsg01','','查询信息',1,'/demo/usingCarAction!datagrid.action',NULL,'carUsingMsg'),('carUsingMsg02','','主页面',2,'/demo/usingCarAction!toUsingCarPage.action',NULL,'carUsingMsg'),('carUsingMsg03','','添加页面',3,'/demo/usingCarAction!toAddPage.action',NULL,'carUsingMsg'),('carUsingMsg04','','添加信息',4,'/demo/usingCarAction!add.action',NULL,'carUsingMsg'),('carUsingMsg05','','更新页面',5,'/demo/usingCarAction!toEditPage.action',NULL,'carUsingMsg'),('carUsingMsg06','','更新信息',6,'/demo/usingCarAction!edit.action',NULL,'carUsingMsg'),('carUsingMsg07','','查询信息',7,'/demo/usingCarAction!delete.action',NULL,'carUsingMsg'),('carUsingMsg08','','选择车辆页面',8,'/demo/usingCarAction!toSelectCarInputPage.action',NULL,'carUsingMsg'),('carUsingMsg09','','获取车辆信息',9,'/demo/usingCarAction!getCarDataBySchoolArea.action',NULL,'carUsingMsg'),('carUsingMsg10','','选择教练页面',10,'/demo/usingCarAction!toSelectTrainerInputPage.action',NULL,'carUsingMsg'),('carUsingMsg11','','获取教练信息',11,'/demo/usingCarAction!getTrainerDataBySchoolArea.action',NULL,'carUsingMsg'),('cdadd','','菜单添加',4,'/demo/menuAction!add.action',NULL,'cdgl'),('cdaddym','','添加菜单页面',3,'/demo/menuAction!menuAdd.action',NULL,'cdgl'),('cdcx','','菜单查询',2,'/demo/menuAction!treegrid.action',NULL,'cdgl'),('cddelete','','菜单删除',7,'/demo/menuAction!delete.action',NULL,'cdgl'),('cdedit','','菜单编辑',6,'/demo/menuAction!edit.action',NULL,'cdgl'),('cdeditym','','编辑菜单页面',5,'/demo/menuAction!menuEdit.action',NULL,'cdgl'),('cdgl','','菜单管理',4,'','cdgl','xtgl'),('cdglym','','菜单管理页面',1,'/demo/menuAction!menu.action',NULL,'cdgl'),('classMangerMsg01','','班级页面',1,'/demo/classDesignAction!toClassDesignPage.action',NULL,'classMsg'),('classMangerMsg02','','添加页面',2,'/demo/classDesignAction!toClassDesignAddPage.action',NULL,'classMsg'),('classMangerMsg03','','更新页面',3,'/demo/classDesignAction!toClassDesignEditPage.action',NULL,'classMsg'),('classMangerMsg04','','班级列表',4,'/demo/classDesignAction!datagrid.action',NULL,'classMsg'),('classMangerMsg05','','添加班级',5,'/demo/classDesignAction!add.action',NULL,'classMsg'),('classMangerMsg06','','更新班级',6,'/demo/classDesignAction!edit.action',NULL,'classMsg'),('classMangerMsg07','','删除班级',7,'/demo/classDesignAction!delete.action',NULL,'classMsg'),('classMsg','','班级开设',2,'','zbxychx','studentMsgmy'),('commentStudentMsg','','点评学员',5,'','commentStudent','trainerMsg'),('commentStudentMsg01','','点评页面',1,'/demo/commentStudentAction!studentList.action',NULL,'commentStudentMsg'),('commentStudentMsg02','','读取学员',2,'/demo/commentStudentAction!getStudentDatagrid.action',NULL,'commentStudentMsg'),('commentStudentMsg04','','点评列表',4,'/demo/commentStudentAction!datagrid.action',NULL,'commentStudentMsg'),('commentStudentMsg05','','更新点评',5,'/demo/commentStudentAction!edit.action',NULL,'commentStudentMsg'),('commentStudentMsg06','','删除点评',6,'/demo/commentStudentAction!delete.action',NULL,'commentStudentMsg'),('commentStudentMsg07','','获取URL',7,'/demo/commentStudentAction!getUrl.action',NULL,'commentStudentMsg'),('commonMsg','','公共资源',14,'',NULL,'0'),('commonMsg01','icon-filter','学员校区标识',1,'/demo/studentAnalysisAction!getStudentBySchoolArea.action',NULL,'commonMsg'),('commonMsg02','icon-filter','获取学员总数',2,'/demo/studentAnalysisAction!getAllStudentCount.action',NULL,'commonMsg'),('commonMsg03','icon-filter','用户校区标识',3,'/demo/studentAction!getSchoolArea.action',NULL,'commonMsg'),('commonMsg04','icon-filter','读取考试科目',4,'/demo/studentAnalysisAction!getSubjectCountBySchoolId.action',NULL,'commonMsg'),('commonMsg05','icon-filter','读取文明考试',5,'/demo/studentAnalysisAction!getSubjectFourCountBySchoolId.action',NULL,'commonMsg'),('commonMsg06','icon-filter','读取路考信息',6,'/demo/studentAnalysisAction!getSubjectThreeCountBySchoolId.action',NULL,'commonMsg'),('commonMsg07','icon-filter','读取五项信息',7,'/demo/studentAnalysisAction!getSubjectTwoCountBySchoolId.action',NULL,'commonMsg'),('commonMsg08','icon-filter','单个学员查找',8,'/demo/studentAnalysisAction!getSignupStudent.action',NULL,'commonMsg'),('commonMsg09','icon-filter','分类统计学员',9,'/demo/studentAnalysisAction!getStudentCountByDriverType.action',NULL,'commonMsg'),('commonMsg10','icon-filter','分类统计车辆',10,'/demo/studentAnalysisAction!getCarBySchoolArea.action',NULL,'commonMsg'),('commonMsg11','icon-filter','统计车辆总数',11,'/demo/studentAnalysisAction!getAllCarCount.action',NULL,'commonMsg'),('commonMsg12','icon-filter','统计教练总数',12,'/demo/studentAnalysisAction!getAllTrainerCount.action',NULL,'commonMsg'),('commonMsg13','icon-filter','分类统计教练',13,'/demo/studentAnalysisAction!getTrainerBySchoolArea.action',NULL,'commonMsg'),('commonMsg14','icon-filter','分类统计教练2',14,'/demo/studentAnalysisAction!getTrainerCountByDriverType.action',NULL,'commonMsg'),('commonMsg15','icon-filter','选择排班教练',15,'/demo/trainerArrangeAction!toSelectTrainerPage.action',NULL,'commonMsg'),('commonMsg16','icon-filter','读取机构信息',16,'/demo/organizationAction!getAllOrganization.action',NULL,'commonMsg'),('commonMsg17','icon-filter','校验身份证',17,'/demo/studentAction!identityIdExistOrNot.action',NULL,'commonMsg'),('commonMsg18','icon-filter','校验身份证',18,'/demo/trainerAction!identityIdExistOrNot.action',NULL,'commonMsg'),('commonMsg19','icon-filter','校验身份证',19,'/demo/personAction!identityIdExistOrNot.action',NULL,'commonMsg'),('driverLicenseMsg','','驾驶证管理',4,'','studentjszgl','0'),('driverLicenseMsg01','','驾驶证主页',1,'/demo/driverLicenseAction!toDriverLicensePage.action',NULL,'driverLicenseMsg'),('driverLicenseMsg02','','添加页面',2,'/demo/driverLicenseAction!toDriverLicenseAddPage.action',NULL,'driverLicenseMsg'),('driverLicenseMsg03','','添加输入页面',3,'/demo/driverLicenseAction!toDriverLicenseAddInputPage.action',NULL,'driverLicenseMsg'),('driverLicenseMsg04','','更新页面',4,'/demo/driverLicenseAction!toDriverLicenseEditPage.action',NULL,'driverLicenseMsg'),('driverLicenseMsg05','','详细信息',5,'/demo/driverLicenseAction!toDriverLicenseDetailPage.action',NULL,'driverLicenseMsg'),('driverLicenseMsg06','','驾驶证列表',6,'/demo/driverLicenseAction!datagrid.action',NULL,'driverLicenseMsg'),('driverLicenseMsg07','','更新驾驶证',7,'/demo/driverLicenseAction!edit.action',NULL,'driverLicenseMsg'),('driverLicenseMsg08','','驾驶证管理',8,'/demo/driverLicenseAction!delete.action',NULL,'driverLicenseMsg'),('driverLicenseMsg09','','获取学员信息',9,'/demo/driverLicenseAction!getAllGraduateStudents.action',NULL,'driverLicenseMsg'),('driverLicenseMsg10','','获取单个学员',10,'/demo/driverLicenseAction!getSingStudentInformation.action',NULL,'driverLicenseMsg'),('driverLicenseMsg11','','添加',11,'/demo/driverLicenseAction!add.action',NULL,'driverLicenseMsg'),('examinationjhMsg','','考试计划',1,'',NULL,'examinationMsg'),('examinationjhMsg01','','添加计划',1,'/demo/arrangedExaminationAction!add.action',NULL,'examinationjhMsg'),('examinationjhMsg02','','更新计划',2,'/demo/arrangedExaminationAction!edit.action',NULL,'examinationjhMsg'),('examinationjhMsg03','','添加计划',3,'/demo/arrangedExaminationAction!delete.action',NULL,'examinationjhMsg'),('examinationjhMsg04','','计划主页面',4,'/demo/arrangedExaminationAction!toArrangedExaminationPage.action',NULL,'examinationjhMsg'),('examinationjhMsg05','','添加页面',5,'/demo/arrangedExaminationAction!toArrangedExaminationAddPage.action',NULL,'examinationjhMsg'),('examinationjhMsg06','','更新页面',6,'/demo/arrangedExaminationAction!toArrangedExaminationEditPage.action',NULL,'examinationjhMsg'),('examinationjhMsg07','','更新输入页',7,'/demo/arrangedExaminationAction!toUpdateAddInput.action',NULL,'examinationjhMsg'),('examinationjhMsg08','','考试计划列表',8,'/demo/arrangedExaminationAction!datagrid.action',NULL,'examinationjhMsg'),('examinationjhMsg09','','更新页面删除学员',9,'/demo/arrangedExaminationAction!deleteFromUpdateDatagrid.action',NULL,'examinationjhMsg'),('examinationjhMsg10','','查看学员明细',10,'/demo/arrangedExaminationAction!getStudentDetailMsg.action',NULL,'examinationjhMsg'),('examinationjhMsg11','','提取理考学员',11,'/demo/arrangedExaminationAction!getArrangedExaminationStudentByDatagird.action',NULL,'examinationjhMsg'),('examinationjhMsg12','','提取五项学员',12,'/demo/arrangedExaminationAction!getSubTwoFromSbuOne.action',NULL,'examinationjhMsg'),('examinationjhMsg13','','提取路考学员',13,'/demo/arrangedExaminationAction!getSubThreeFromSbuOne.action',NULL,'examinationjhMsg'),('examinationjhMsg14','','提取文明学员',14,'/demo/arrangedExaminationAction!getSubFourFromSbujectThree.action',NULL,'examinationjhMsg'),('examinationjhMsg15','','提取资格学员',15,'/demo/arrangedExaminationAction!getQualificationFromSbujectFour.action',NULL,'examinationjhMsg'),('examinationjhMsg16','','理论更新数据',16,'/demo/arrangedExaminationAction!getTheoryArrangeUpdateData.action',NULL,'examinationjhMsg'),('examinationjhMsg17','','五项更新数据',17,'/demo/arrangedExaminationAction!getSubjectTwoArrangeUpdateData.action',NULL,'examinationjhMsg'),('examinationjhMsg18','','路考更新数据',18,'/demo/arrangedExaminationAction!getSubjectThreeArrangeUpdateData.action',NULL,'examinationjhMsg'),('examinationjhMsg19','','文明更新数据',19,'/demo/arrangedExaminationAction!getSubjectFourArrangeUpdateData.action',NULL,'examinationjhMsg'),('examinationjhMsg20','','资格更新数据',20,'/demo/arrangedExaminationAction!getQualificationArrangeUpdateData.action',NULL,'examinationjhMsg'),('examinationjhMsg21','','获取机构信息',21,'/demo/arrangedExaminationAction!getOrganizationByArrangedNo.action',NULL,'examinationjhMsg'),('examinationjhMsg22','','获取考试类型',22,'/demo/arrangedExaminationAction!getExaminationSubjectByArrangedNo.action',NULL,'examinationjhMsg'),('examinationMsg','','考试管理',4,'','exammng','0'),('examinationMsgSub2','','五项成绩管理',3,'',NULL,'examinationMsg'),('examinationMsgSub3','','路考成绩管理',4,'',NULL,'examinationMsg'),('examinationMsgSub4','','文明成绩管理',5,'',NULL,'examinationMsg'),('examinationMsgSub5','','资格成绩管理',6,'',NULL,'examinationMsg'),('examinationSub1Msg','','理论成绩管理',2,'',NULL,'examinationMsg'),('examinationSub1Msg01','','打开主页',1,'/demo/theoryExaminationAction!toTheoryExaminationPage.action',NULL,'examinationSub1Msg'),('examinationSub1Msg02','','五项成绩列表',2,'/demo/theoryExaminationAction!datagrid.action',NULL,'examinationSub1Msg'),('examinationSub1Msg03','','查看详细信息',3,'/demo/theoryExaminationAction!toTheoryExaminationDetailPage.action',NULL,'examinationSub1Msg'),('examinationSub1Msg04','','更新页面',4,'/demo/theoryExaminationAction!toTheoryExaminationEditPage.action',NULL,'examinationSub1Msg'),('examinationSub1Msg05','','更新理论成绩',5,'/demo/theoryExaminationAction!edit.action',NULL,'examinationSub1Msg'),('examinationSub1Msg06','','获取五项学员',6,'/demo/theoryExaminationAction!getTheoryStudentByDatagird.action',NULL,'examinationSub1Msg'),('examinationSub1Msg07','','获取计划编号',7,'/demo/theoryExaminationAction!getAllArrangedNo.action',NULL,'examinationSub1Msg'),('examinationSub1Msg08','','批量更新页面',8,'/demo/theoryExaminationAction!toEditAllPage.action',NULL,'examinationSub1Msg'),('examinationSub1Msg09','','批量更新',9,'/demo/theoryExaminationAction!editAll.action',NULL,'examinationSub1Msg'),('examinationSub2Msg01','','打开主页',1,'/demo/subjectTwoAction!toSubjectTwoPage.action',NULL,'examinationMsgSub2'),('examinationSub2Msg02','','五项成绩列表',2,'/demo/subjectTwoAction!datagrid.action',NULL,'examinationMsgSub2'),('examinationSub2Msg03','','查看详细信息',3,'/demo/subjectTwoAction!toPersonalDetailSubjectTwoPage.action',NULL,'examinationMsgSub2'),('examinationSub2Msg04','','更新页面',4,'/demo/subjectTwoAction!toSubjectTwoEditPage.action',NULL,'examinationMsgSub2'),('examinationSub2Msg05','','更新五项成绩',5,'/demo/subjectTwoAction!edit.action',NULL,'examinationMsgSub2'),('examinationSub2Msg06','','获取五项学员',6,'/demo/subjectTwoAction!getSubjectTwoStudentByDatagird.action',NULL,'examinationMsgSub2'),('examinationSub2Msg07','','获取计划编号',7,'/demo/subjectTwoAction!getAllArrangedNo.action',NULL,'examinationMsgSub2'),('examinationSub2Msg08','','批量更新页面',8,'/demo/subjectTwoAction!toEditAllPage.action',NULL,'examinationMsgSub2'),('examinationSub2Msg09','','批量更新',9,'/demo/subjectTwoAction!editAll.action',NULL,'examinationMsgSub2'),('examinationSub3Msg01','','打开主页',1,'/demo/subjectThreeAction!toSubjectThreePage.action',NULL,'examinationMsgSub3'),('examinationSub3Msg02','','路考成绩列表',2,'/demo/subjectThreeAction!datagrid.action',NULL,'examinationMsgSub3'),('examinationSub3Msg03','','查看详细信息',3,'/demo/subjectThreeAction!toSubjectThreeDetailPage.action',NULL,'examinationMsgSub3'),('examinationSub3Msg04','','更新页面',4,'/demo/subjectThreeAction!toSubjectThreeEditPage.action',NULL,'examinationMsgSub3'),('examinationSub3Msg05','','更新路考成绩',5,'/demo/subjectThreeAction!edit.action',NULL,'examinationMsgSub3'),('examinationSub3Msg06','','获取路考学员',6,'/demo/subjectThreeAction!getSubjectThreeStudentByDatagird.action',NULL,'examinationMsgSub3'),('examinationSub3Msg07','','获取计划编号',7,'/demo/subjectThreeAction!getAllArrangedNo.action',NULL,'examinationMsgSub3'),('examinationSub3Msg08','','批量更新頁面',8,'/demo/subjectThreeAction!toEditAllPage.action',NULL,'examinationMsgSub3'),('examinationSub3Msg09','','批量更新',9,'/demo/subjectThreeAction!editAll.action',NULL,'examinationMsgSub3'),('examinationSub4Msg01','','打开主页',1,'/demo/subjectFourAction!toSubjectFourPage.action',NULL,'examinationMsgSub4'),('examinationSub4Msg02','','文明成绩列表',2,'/demo/subjectFourAction!datagrid.action',NULL,'examinationMsgSub4'),('examinationSub4Msg03','','查看详细信息',3,'/demo/subjectFourAction!toSubjectFourDetailPage.action',NULL,'examinationMsgSub4'),('examinationSub4Msg04','','更新页面',4,'/demo/subjectFourAction!toSubjectFourEditPage.action',NULL,'examinationMsgSub4'),('examinationSub4Msg05','','更新文明成绩',5,'/demo/subjectFourAction!edit.action',NULL,'examinationMsgSub4'),('examinationSub4Msg06','','获取文明学员',6,'/demo/subjectFourAction!getSubjectFourStudentByDatagird.action',NULL,'examinationMsgSub4'),('examinationSub4Msg07','','获取计划编号',7,'/demo/subjectFourAction!getAllArrangedNo.action',NULL,'examinationMsgSub4'),('examinationSub4Msg08','','批量更新頁面',8,'/demo/subjectFourAction!toEditAllPage.action',NULL,'examinationMsgSub4'),('examinationSub4Msg09','','批量更新',9,'/demo/subjectFourAction!editAll.action',NULL,'examinationMsgSub4'),('examinationSub5Msg01','','打开主页',1,'/demo/qualificationAction!toQualificationPage.action',NULL,'examinationMsgSub5'),('examinationSub5Msg02','','资格成绩列表',2,'/demo/qualificationAction!datagrid.action',NULL,'examinationMsgSub5'),('examinationSub5Msg03','','查看详细信息',3,'/demo/qualificationAction!toQualificationDetailPage.action',NULL,'examinationMsgSub5'),('examinationSub5Msg04','','更新页面',4,'/demo/qualificationAction!toQualificationEditPage.action',NULL,'examinationMsgSub5'),('examinationSub5Msg05','','更新资格成绩',5,'/demo/qualificationAction!edit.action',NULL,'examinationMsgSub5'),('examinationSub5Msg06','','获取五项学员',6,'/demo/qualificationAction!getQualificationStudentByDatagird.action',NULL,'examinationMsgSub5'),('examinationSub5Msg07','','获取计划编号',7,'/demo/qualificationAction!getAllArrangedNo.action',NULL,'examinationMsgSub5'),('examinationSub5Msg08','','批量更新頁面',8,'/demo/qualificationAction!toEditAllPage.action',NULL,'examinationMsgSub5'),('examinationSub5Msg09','','批量更新',9,'/demo/qualificationAction!editAll.action',NULL,'examinationMsgSub5'),('fileMsgAuth','','档案管理',7,'',NULL,'0'),('jsadd','','角色添加',4,'/demo/roleAction!add.action',NULL,'jsgl'),('jsaddym','','添加角色页面',3,'/demo/roleAction!roleAdd.action',NULL,'jsgl'),('jscx','','角色查询',2,'/demo/roleAction!datagrid.action',NULL,'jsgl'),('jsdelete','','角色删除',7,'/demo/roleAction!delete.action',NULL,'jsgl'),('jsedit','','角色编辑',6,'/demo/roleAction!edit.action',NULL,'jsgl'),('jseditym','','编辑角色页面',5,'/demo/roleAction!roleEdit.action',NULL,'jsgl'),('jsgl','','角色管理',2,'','jsgl','xtgl'),('jsglym','','角色管理页面',1,'/demo/roleAction!role.action',NULL,'jsgl'),('ljcjk','可查看数据库链接信息','连接池监控',1,'/datasourceAction!druid.action',NULL,'sjkgl'),('logMsg','','日志管理',13,'','rzgl','0'),('logMsg01','icon-filter','日志列表頁面',1,'/demo/logAction!toLogPage.action',NULL,'logMsg'),('logMsg02','icon-filter','日志列表',2,'/demo/logAction!datagrid.action',NULL,'logMsg'),('maxIdListMsg','','学编号',7,'/demo/studentAction!getMaxStudentNo.action',NULL,'studentMsgMng'),('netMsg','','网站管理',9,'','wzgl','0'),('netMsg01','','网站管理主页',1,'/demo/articleAction!article.action',NULL,'netMsg'),('netMsg02','','网站添加页面',2,'/demo/articleAction!articleAdd.action',NULL,'netMsg'),('netMsg03','','网站更新页面',3,'/demo/articleAction!articleEdit.action',NULL,'netMsg'),('netMsg04','','网站管理列表',4,'/demo/articleAction!datagrid.action',NULL,'netMsg'),('netMsg05','','添加网站信息',5,'/demo/articleAction!save.action',NULL,'netMsg'),('netMsg06','','删除网站信息',6,'/demo/articleAction!delete.action',NULL,'netMsg'),('organizationMangerMsg','','机构管理',10,'',NULL,'0'),('organizationMsg','','组织机构',4,'/demo/organizationAction!getAllOrganization.action',NULL,'studentMsgMng'),('organizationMsg01','','机构管理主页',1,'/demo/organizationAction!organization.action',NULL,'organizationMsgGl'),('organizationMsg02','','机构添加页面页',2,'/demo/organizationAction!organizationAdd.action',NULL,'organizationMsgGl'),('organizationMsg03','','机构编辑页面',3,'/demo/organizationAction!organizationEdit.action',NULL,'organizationMsgGl'),('organizationMsg04','','机构管理列表',4,'/demo/organizationAction!treegrid.action',NULL,'organizationMsgGl'),('organizationMsg05','','添加机构管理',5,'/demo/organizationAction!add.action',NULL,'organizationMsgGl'),('organizationMsg06','','编辑机构管理',6,'/demo/organizationAction!edit.action',NULL,'organizationMsgGl'),('organizationMsg07','','删除机构管理',7,'/demo/organizationAction!delete.action',NULL,'organizationMsgGl'),('organizationMsg08','','按标识获取机构',8,'/demo/organizationAction!getDepartmentNameById.action',NULL,'organizationMsgGl'),('organizationMsg09','','获取机构名称',9,'/demo/organizationAction!getAllOrganization.action',NULL,'organizationMsgGl'),('organizationMsg10','','获取机构树',10,'/demo/organizationAction!doNotNeedSession_tree.action',NULL,'organizationMsgGl'),('organizationMsg11','','获取递归树',11,'/demo/organizationAction!doNotNeedSession_treeRecursive.action',NULL,'organizationMsgGl'),('organizationMsgGl','','机构信息管理',1,'','orgmsgmanager','organizationMangerMsg'),('personalMsgGl','','人员信息管理',2,'','personmsg','organizationMangerMsg'),('personalMsgGl01','','人员信息主页',1,'/demo/personAction!toPersonPage.action',NULL,'personalMsgGl'),('personalMsgGl02','','添加页面',2,'/demo/personAction!toPersonAddPage.action',NULL,'personalMsgGl'),('personalMsgGl03','','编辑页面',3,'/demo/personAction!toPersonEditPage.action',NULL,'personalMsgGl'),('personalMsgGl04','','人员信息主页',4,'/demo/personAction!datagrid.action',NULL,'personalMsgGl'),('personalMsgGl05','','添加人员信息',5,'/demo/personAction!add.action',NULL,'personalMsgGl'),('personalMsgGl06','','编辑人员信息',6,'/demo/personAction!edit.action',NULL,'personalMsgGl'),('personalMsgGl07','','删除人员信息',7,'/demo/personAction!delete.action',NULL,'personalMsgGl'),('personalMsgGl08','','获取机构',8,'/demo/personAction!getDepartmentNameById.action',NULL,'personalMsgGl'),('progressSchoolMsg','','学员进度管理',5,'','studentprogress','studentMsgmy'),('progressSchoolMsg01','','主页',1,'/demo/progressAction!toProgressPage.action',NULL,'progressSchoolMsg'),('progressSchoolMsg02','','学员进度列表',2,'/demo/progressAction!datagrid.action',NULL,'progressSchoolMsg'),('progressSchoolMsg03','','详细信息表',3,'/demo/progressAction!toProgressDetailPage.action',NULL,'progressSchoolMsg'),('qualiLicenseMsg','','资格证管理',5,'','studentzhgzgl','0'),('qualiLicenseMsg01','','资格证主页',1,'/demo/qualificationLicenseAction!toQualificationLicensePage.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg02','','添加页面',2,'/demo/qualificationLicenseAction!toQualificationLicenseAddPage.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg03','','添加输入页',3,'/demo/qualificationLicenseAction!toQualificationLicenseAddInputPage.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg04','','更新页面',4,'/demo/qualificationLicenseAction!toQualificationLicenseEditPage.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg05','','详细信息',5,'/demo/qualificationLicenseAction!toQualificationLicenseDetailPage.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg06','','资格证列表',6,'/demo/qualificationLicenseAction!datagrid.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg07','','添加资格证',7,'/demo/qualificationLicenseAction!add.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg08','','更新资格证',8,'/demo/qualificationLicenseAction!edit.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg09','','删除资格证',9,'/demo/qualificationLicenseAction!delete.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg10','','获取学员信息',10,'/demo/qualificationLicenseAction!getAllGraduateStudents.action',NULL,'qualiLicenseMsg'),('qualiLicenseMsg11','','查看学员信息',11,'/demo/qualificationLicenseAction!getSingStudentInformation.action',NULL,'qualiLicenseMsg'),('quickSchoolMsg','','退学登记',3,'','txdj','studentMsgmy'),('quickSchoolMsg01','','退学主页',1,'/demo/quitschoolAction!toQuitschoolPage.action',NULL,'quickSchoolMsg'),('quickSchoolMsg02','','添加页面',2,'/demo/quitschoolAction!toQuitschoolAddPage.action',NULL,'quickSchoolMsg'),('quickSchoolMsg03','','添加输入页',3,'/demo/quitschoolAction!toQuitschoolAddInputPage.action',NULL,'quickSchoolMsg'),('quickSchoolMsg04','','更新页面',4,'/demo/quitschoolAction!toQuitschoolEditPage.action',NULL,'quickSchoolMsg'),('quickSchoolMsg05','','退学列表',5,'/demo/quitschoolAction!datagrid.action',NULL,'quickSchoolMsg'),('quickSchoolMsg06','','添加信息',6,'/demo/quitschoolAction!add.action',NULL,'quickSchoolMsg'),('quickSchoolMsg07','','删除退学',7,'/demo/quitschoolAction!delete.action',NULL,'quickSchoolMsg'),('quickSchoolMsg08','','获取学员',8,'/demo/quitschoolAction!getAllStudentByState.action',NULL,'quickSchoolMsg'),('quickSchoolMsg09','','获取1个学员',9,'/demo/quitschoolAction!getSingStudentInformation.action',NULL,'quickSchoolMsg'),('quickSchoolMsg10','','更新退学',10,'/demo/quitschoolAction!edit.action',NULL,'quickSchoolMsg'),('quickSchoolMsg11','','详细信息',11,'/demo/quitschoolAction!toQuitschoolDetailPage.action',NULL,'quickSchoolMsg'),('qxadd','','权限添加',4,'/demo/authAction!add.action',NULL,'qxgl'),('qxaddym','','添加权限页面',3,'/demo/authAction!authAdd.action',NULL,'qxgl'),('qxcx','','权限查询',2,'/demo/authAction!treegrid.action',NULL,'qxgl'),('qxdelete','','权限删除',7,'/demo/authAction!delete.action',NULL,'qxgl'),('qxedit','','权限编辑',6,'/demo/authAction!edit.action',NULL,'qxgl'),('qxeditym','','编辑权限页面',5,'/demo/authAction!authEdit.action',NULL,'qxgl'),('qxgl','','权限管理',3,'','qxgl','xtgl'),('qxglym','','权限管理页面',1,'/demo/authAction!auth.action',NULL,'qxgl'),('sjkgl','可查看数据库链接信息，SQL语句执行情况','数据库管理',1,'','sjkgl','0'),('studentfileMsgAuth','','学员档案管理',1,'','studentDagl','0'),('studentfileMsgAuth01','','档案列表',1,'/demo/studentFileAction!datagrid.action',NULL,'studentfileMsgAuth'),('studentfileMsgAuth02','','学员档案主页',2,'/demo/studentFileAction!toStudentFilePage.action',NULL,'studentfileMsgAuth'),('studentfileMsgAuth03','','更新页面',3,'/demo/studentFileAction!toStudentFileEditPage.action',NULL,'studentfileMsgAuth'),('studentfileMsgAuth04','','更新学员档案',4,'/demo/studentFileAction!edit.action',NULL,'studentfileMsgAuth'),('studentfileMsgAuth05','','更新学员档案',5,'/demo/studentFileAction!toStudentFileDetailPage.action',NULL,'studentfileMsgAuth'),('studentListMsg','','学员信息列表',5,'/demo/studentAction!datagrid.action',NULL,'studentMsgMng'),('studentMangerMsg','','学员信息中心',11,'',NULL,'0'),('studentMangerMsg01','','基本信息',1,'/demo/studentAction!toMyMessagePage.action','studentMsgzx01','studentMangerMsg'),('studentMangerMsg02','','基本信息列表',2,'/demo/studentAction!studentMsgDatagrid.action',NULL,'studentMangerMsg01'),('studentMangerMsg03','','个人详细信息',3,'/demo/studentAction!toMyMessageDetailPage.action',NULL,'studentMangerMsg01'),('studentMangerMsg04','','个人进度页面',4,'/demo/progressAction!toPersonalProgressPage.action','studentMsgzx03','studentMangerMsg'),('studentMangerMsg05','','个人进度详细信息',5,'/demo/progressAction!toPersonalProgressDetailPage.action',NULL,'studentMangerMsg04'),('studentMangerMsg06','','个人进度列表',6,'/demo/progressAction!personalDatagrid.action',NULL,'studentMangerMsg04'),('studentMangerMsg07','','个人理论成绩列表',7,'/demo/theoryExaminationAction!personalDatagrid.action','studentMsgzx04','studentMangerMsg'),('studentMangerMsg08','','个人理论成绩页面',8,'/demo/theoryExaminationAction!toPersonalTheoryPage.action',NULL,'studentMangerMsg07'),('studentMangerMsg09','','个人理论详细信息',9,'/demo/theoryExaminationAction!toPersonalDetailTheoryPage.action',NULL,'studentMangerMsg07'),('studentMangerMsg10','','个人五项成绩列表',10,'/demo/subjectTwoAction!personalDatagrid.action','studentMsgzx05','studentMangerMsg'),('studentMangerMsg11','','个人五项成绩页面',11,'/demo/subjectTwoAction!toPersonalSubjectTwoPage.action',NULL,'studentMangerMsg10'),('studentMangerMsg12','','五项详细信息',12,'/demo/subjectTwoAction!toMydetailPage.action',NULL,'studentMangerMsg10'),('studentMangerMsg13','','个人路考成绩列表',13,'/demo/subjectThreeAction!personalDatagrid.action','studentMsgzx06','studentMangerMsg'),('studentMangerMsg14','','个人路考成绩页面',14,'/demo/subjectThreeAction!toPersonalSubjectThreePage.action',NULL,'studentMangerMsg13'),('studentMangerMsg15','','个人路考详细信息',15,'/demo/subjectThreeAction!toPersonalDetailSubjectThreePage.action',NULL,'studentMangerMsg13'),('studentMangerMsg16','','个人文明成绩列表',16,'/demo/subjectFourAction!personalDatagrid.action','studentMsgzx07','studentMangerMsg'),('studentMangerMsg17','','个人文明成绩页面',17,'/demo/subjectFourAction!toPersonalSubjectFourPage.action',NULL,'studentMangerMsg16'),('studentMangerMsg18','','个人文明详细信息',18,'/demo/subjectFourAction!toSubjectFourDetailPage.action',NULL,'studentMangerMsg16'),('studentMangerMsg19','studentMsgzx08','学时信息',19,'/demo/timingAction!toMyTimingPage.action','studentMsgzx08','studentMangerMsg'),('studentMangerMsg20','','学时信息',20,'/demo/timingAction!personalDatagrid.action',NULL,'studentMangerMsg19'),('studentMangerMsg21','','学时信息',21,'/demo/personalTimingAction!toMyDetailTimingPage.action',NULL,'studentMangerMsg19'),('studentMangerMsg22','','驾驶证页面',22,'/demo/driverLicenseAction!toMyDriverLicensePage.action','studentMsgzx10','studentMangerMsg'),('studentMangerMsg23','','驾驶证详细信息',23,'/demo/driverLicenseAction!toMyDetailPage.action',NULL,'studentMangerMsg22'),('studentMangerMsg24','','驾驶证列表',24,'/demo/driverLicenseAction!personalDatagrid.action',NULL,'studentMangerMsg22'),('studentMangerMsg25','','学员预约页面',25,'/demo/reservationAction!toMyReservationPage.action','studentMsgzx09','studentMangerMsg'),('studentMangerMsg26','','学员预约列表',26,'/demo/reservationAction!personalDatagrid.action',NULL,'studentMangerMsg25'),('studentMangerMsg27','','读取学员信息',27,'/demo/studentAction!getSingleStudent.action',NULL,'studentMangerMsg25'),('studentMangerMsg28','','学员计时卡',28,'/demo/studentTimerCardAction!toStudentPersonalTimerCardPage.action','studentMsgzx02','studentMangerMsg'),('studentMangerMsg29','','计时卡列表',29,'/demo/studentTimerCardAction!personalDatagrid.action',NULL,'studentMangerMsg28'),('studentMangerMsg30','','学时信息',30,'/demo/personalTimingAction!datagrid.action',NULL,'studentMangerMsg19'),('studentMangerMsg31','','学时信息',31,'/demo/subjectTwoAction!toSubjectTwoDetailPage.action',NULL,'studentMangerMsg19'),('studentMsg001','','选择教练',8,'/demo/studentAction!toSelectTrainerPage.action',NULL,'studentMsgMng'),('studentMsg002','','选择业务员',9,'/demo/studentAction!toSelectPersonPage.action',NULL,'studentMsgMng'),('studentMsg003','','选择班级',10,'/demo/studentAction!toSelectClazzPage.action',NULL,'studentMsgMng'),('studentMsg004','','打开更新页',11,'/demo/studentAction!toStudentUpdatePage.action',NULL,'studentMsgMng'),('studentMsg005','','更新学员',12,'/demo/studentAction!edit.action',NULL,'studentMsgMng'),('studentMsg006','','选择班级',13,'/demo/studentAction!getAllClass.action',NULL,'studentMsgMng'),('studentMsg007','','业务员选择',14,'/demo/studentAction!getAllPersonsForCombobox.action',NULL,'studentMsgMng'),('studentMsg008','','获取班级',15,'/demo/studentAction!getClazzDatabyType.action',NULL,'studentMsgMng'),('studentMsg009','','获取教练',16,'/demo/studentAction!getTrainerDatabyType.action',NULL,'studentMsgMng'),('studentMsg010','','获取业务员',17,'/demo/studentAction!getAllPersons.action',NULL,'studentMsgMng'),('studentMsg011','','查询学员编号',18,'/demo/studentAction!studentNoExistOrNot.action',NULL,'studentMsgMng'),('studentMsg012','','删除学员',19,'/demo/studentAction!delete.action',NULL,'studentMsgMng'),('studentMsg13','','详细信息',20,'/demo/studentAction!toStudentDetailPage.action',NULL,'studentMsgMng'),('studentMsgMng','','学员信息管理',1,'','xshbm','studentMsgmy'),('studentMsgMngAdd','','添加学员',3,'/demo/studentAction!add.action',NULL,'studentMsgMng'),('studentMsgMngAddPage','','添加学员页面',2,'/demo/studentAction!toStudentAddPage.action',NULL,'studentMsgMng'),('studentMsgMngMain','','学员信息主页',1,'/demo/studentAction!toStudentPage.action',NULL,'studentMsgMng'),('studentMsgmy','','学员管理',3,'',NULL,'0'),('studentReservationMsg','','学员预约管理',3,'','studentXsyygl','0'),('studentReservationMsg01','','学员预约主页',1,'/demo/reservationAction!toReservationPage.action',NULL,'studentReservationMsg'),('studentReservationMsg02','','预约添加主页',2,'/demo/reservationAction!toReservationAddPage.action',NULL,'studentReservationMsg'),('studentReservationMsg03','','预约添加输入页',3,'/demo/reservationAction!toReservationAddInputPage.action',NULL,'studentReservationMsg'),('studentReservationMsg04','','选择预约学员',4,'/demo/reservationAction!toReservationSelectStudentInputPage.action',NULL,'studentReservationMsg'),('studentReservationMsg05','','选择教练页面',5,'/demo/reservationAction!ToSelectTrainerInputPage.action',NULL,'studentReservationMsg'),('studentReservationMsg06','','预约更新页面',6,'/demo/reservationAction!toReservationEditPage.action',NULL,'studentReservationMsg'),('studentReservationMsg07','','教练预约明细',7,'/demo/reservationDetailAction!toTrainerReservationDetailPage.action',NULL,'studentReservationMsg'),('studentReservationMsg08','','学员预约列表',8,'/demo/reservationAction!datagrid.action',NULL,'studentReservationMsg'),('studentReservationMsg09','','添加预约',9,'/demo/reservationAction!add.action',NULL,'studentReservationMsg'),('studentReservationMsg10','','编辑教练预约',10,'/demo/reservationAction!editReservationDetail.action',NULL,'studentReservationMsg'),('studentReservationMsg11','','更新预约',11,'/demo/reservationAction!edit.action',NULL,'studentReservationMsg'),('studentReservationMsg12','','删除预约',12,'/demo/reservationAction!delete.action',NULL,'studentReservationMsg'),('studentReservationMsg13','','获取预约学员信息',13,'/demo/reservationAction!getAllReservationStudents.action',NULL,'studentReservationMsg'),('studentReservationMsg14','','获取预约教练信息',14,'/demo/reservationAction!getTrainerArrangeForReservation.action',NULL,'studentReservationMsg'),('studentReservationMsg15','','读取单个学员',15,'/demo/reservationAction!getSingStudentInformation.action',NULL,'studentReservationMsg'),('studentReservationMsg16','','读取学员教练',16,'/demo/reservationDetailAction!getStudentByTrainerId.action',NULL,'studentReservationMsg'),('studentReservationMsg17','','读取教练预约明细',17,'/demo/reservationDetailAction!getDetailReservationByTrainerId.action',NULL,'studentReservationMsg'),('studentReservationMsg18','','读取学员信息',18,'/demo/reservationAction!getStudentByTrainerId.action',NULL,'studentReservationMsg'),('studentReservationMsg19','','读取学员信息2',19,'/demo/reservationAction!getTrainerByStudentId.action',NULL,'studentReservationMsg'),('studentReservationMsg20','','预约明细信息',20,'/demo/reservationAction!getDetailReservationByTrainerId.action',NULL,'studentReservationMsg'),('studentReservationMsg21','','详细信息',21,'/demo/reservationAction!toReservationDetailPage.action',NULL,'studentReservationMsg'),('studentTimeCount','','学员学时管理',2,'','studentXsgl','0'),('studentTimeCount01','','学时计时主页',1,'/demo/timingAction!toTimingPage.action',NULL,'studentTimeCount'),('studentTimeCount02','','学时计时列表',2,'/demo/timingAction!datagrid.action',NULL,'studentTimeCount'),('studentTimeCount03','','学员计时明细',3,'/demo/timingAction!toPersonalTimingPage.action',NULL,'studentTimeCount'),('studentTimeCount04','','修改计时页面',4,'/demo/timingAction!toTimingEditPage.action',NULL,'studentTimeCount'),('studentTimeCount05','','修改学时计时',5,'/demo/timingAction!edit.action',NULL,'studentTimeCount'),('studentTimeCount06','','删除学时计时',6,'/demo/timingAction!edit.action',NULL,'studentTimeCount'),('studentTimeCount07','','添加学时明细页面',7,'/demo/personalTimingAction!toPersonalTimingAddPage.action',NULL,'studentTimeCount'),('studentTimeCount08','','计时明细输入',8,'/demo/personalTimingAction!toPersonalTimingAddInputPage.action',NULL,'studentTimeCount'),('studentTimeCount09','','明细更新页面',9,'/demo/personalTimingAction!toPersonalTimingUpdateInputPage.action',NULL,'studentTimeCount'),('studentTimeCount10','','学时明细列表',10,'/demo/personalTimingAction!datagrid.action',NULL,'studentTimeCount'),('studentTimeCount11','','添加学时明细',11,'/demo/personalTimingAction!add.action',NULL,'studentTimeCount'),('studentTimeCount12','','更新学时明细',12,'/demo/personalTimingAction!edit.action',NULL,'studentTimeCount'),('studentTimeCount13','','删除学时明细',13,'/demo/personalTimingAction!delete.action',NULL,'studentTimeCount'),('studentTimeCount14','','删除学时明细',14,'/demo/personalTimingAction!toPersonalBuyTimingPage.action',NULL,'studentTimeCount'),('studentTimeMsg','','计时卡管理',4,'','stdotimercard','studentMsgmy'),('studentTimeMsg01','','主页',1,'/demo/studentTimerCardAction!studentTimerCard.action',NULL,'studentTimeMsg'),('studentTimeMsg02','','计时卡列表',2,'/demo/studentTimerCardAction!datagrid.action',NULL,'studentTimeMsg'),('studentTimeMsg03','','更新',3,'/demo/studentTimerCardAction!edit.action',NULL,'studentTimeMsg'),('studentTimeMsg04','','计时卡主页',4,'/demo/studentTimerCardAction!toStudentPersonalTimerCardPage.action',NULL,'studentTimeMsg'),('studentTimeMsg05','','个人计时卡',5,'/demo/studentTimerCardAction!personalDatagrid.action',NULL,'studentTimeMsg'),('subjectTimeArrageMsg','','科目学员安排',6,'','timercard','0'),('subjectTimeArrageMsg01','','科目学员安排表',1,'/demo/timerCardAction!timerCard.action',NULL,'subjectTimeArrageMsg'),('subjectTimeArrageMsg02','','科目安排列表',2,'/demo/timerCardAction!datagrid.action',NULL,'subjectTimeArrageMsg'),('subjectTimeArrageMsg03','','添加科目安排',3,'/demo/timerCardAction!add.action',NULL,'subjectTimeArrageMsg'),('subjectTimeArrageMsg04','','更新科目安排',4,'/demo/timerCardAction!edit.action',NULL,'subjectTimeArrageMsg'),('subjectTimeArrageMsg05','','删除科目安排',5,'/demo/timerCardAction!delete.action',NULL,'subjectTimeArrageMsg'),('trainerArrangeMsg','','教练排班',2,'','workarrange','trainerMsg'),('trainerArrangeMsg01','','排班主页',1,'/demo/trainerArrangeAction!toTrainerArrangePage.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg02','','添加页面',2,'/demo/trainerArrangeAction!toTrainerArrangeAddPage.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg03','','更新页面',3,'/demo/trainerArrangeAction!toTrainerArrangeEditPage.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg04','','排班信息列表',4,'/demo/trainerArrangeAction!datagrid.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg05','','添加排班',5,'/demo/trainerArrangeAction!add.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg06','','更新排班',6,'/demo/trainerArrangeAction!edit.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg07','','删除排班',7,'/demo/trainerArrangeAction!delete.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg08','','获取教练名',8,'/demo/trainerArrangeAction!getTrainterNames.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg09','','获取教练信息',9,'/demo/trainerArrangeAction!getTrainerDatabyType.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg10','','教练排班明细',10,'/demo/trainerArrangeAction!toTrainerArrangeDetailPage.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg11','','排班明细列表',11,'/demo/trainerArrangeAction!detailDatagrid.action',NULL,'trainerArrangeMsg'),('trainerArrangeMsg12','','排班排班条件',12,'/demo/trainerArrangeAction!trainerArrangeOrNot.action',NULL,'trainerArrangeMsg'),('trainerCenterMsg','','教练信息中心',12,'',NULL,'0'),('trainerCenterMsg01','','教练信息页面',1,'/demo/trainerAction!toMyTrainerPage.action','trainerMsgzx01','trainerCenterMsg'),('trainerCenterMsg02','','详细信息页面',2,'/demo/trainerAction!toMyDetailPage.action',NULL,'trainerCenterMsg01'),('trainerCenterMsg03','','教练信息列表',3,'/demo/trainerAction!personalDatagrid.action',NULL,'trainerCenterMsg01'),('trainerCenterMsg04','','教练排班页面',4,'/demo/trainerArrangeAction!toMyTrainerArrangePage.action','trainerMsgzx02','trainerCenterMsg'),('trainerCenterMsg05','','详细信息页面',5,'/demo/trainerArrangeAction!toMyDetailPage.action',NULL,'trainerCenterMsg04'),('trainerCenterMsg06','','教练排班列表',6,'/demo/trainerArrangeAction!personalDatagrid.action',NULL,'trainerCenterMsg04'),('trainerCenterMsg07','','教练排班明细列表',7,'/demo/trainerArrangeAction!detailDatagrid.action',NULL,'trainerCenterMsg04'),('trainerCenterMsg08','','教练预约页面',8,'/demo/trainerReservationAction!toMyTrainerReservationPage.action','trainerMsgzx03','trainerCenterMsg'),('trainerCenterMsg09','','详细信息页面',9,'/demo/reservationDetailAction!toMyDetailPage.action',NULL,'trainerCenterMsg08'),('trainerCenterMsg10','','教练预约列表',10,'/demo/trainerReservationAction!personalDatagrid.action',NULL,'trainerCenterMsg08'),('trainerCenterMsg11','','教练预约明细列表',11,'/demo/reservationDetailAction!personalDatagrid.action',NULL,'trainerCenterMsg08'),('trainerCenterMsg12','','读取教练标识',12,'/demo/reservationDetailAction!getStudentByTrainerId.action',NULL,'trainerCenterMsg08'),('trainerCenterMsg13','','读取学员信息',13,'/demo/reservationDetailAction!getStudentByTrainerId.action',NULL,'trainerCenterMsg08'),('trainerCenterMsg14','','学员列表页面',14,'/demo/studentAction!toTrainerStudentsPage.action','trainerMsgzx04','trainerCenterMsg'),('trainerCenterMsg15','','学员列表',15,'/demo/studentAction!trainerStudentsDatagrid.action',NULL,'trainerCenterMsg14'),('trainerListMsg','','教练选择菜单',6,'/demo/studentAction!getAllTrainersForCombobox.action',NULL,'studentMsgMng'),('trainerMangerMsg','','教练信息管理',1,'','newtrainer','trainerMsg'),('trainerMangerMsg01','','添加页面',1,'/demo/trainerAction!toAddPage.action',NULL,'trainerMangerMsg'),('trainerMangerMsg02','','更新页面',2,'/demo/trainerAction!toUpdatePage.action',NULL,'trainerMangerMsg'),('trainerMangerMsg03','','教练主页面',3,'/demo/trainerAction!toTrainerPage.action',NULL,'trainerMangerMsg'),('trainerMangerMsg04','','教练信息表列',4,'/demo/trainerAction!datagrid.action',NULL,'trainerMangerMsg'),('trainerMangerMsg05','','添加教练',5,'/demo/trainerAction!add.action',NULL,'trainerMangerMsg'),('trainerMangerMsg06','','更新教练',6,'/demo/trainerAction!edit.action',NULL,'trainerMangerMsg'),('trainerMangerMsg07','','教练信息表列',7,'/demo/trainerAction!delete.action',NULL,'trainerMangerMsg'),('trainerMangerMsg08','','获取教练编号',8,'/demo/trainerAction!getMaxTrainerNo.action',NULL,'trainerMangerMsg'),('trainerMsg','','教练管理',6,'',NULL,'0'),('trainerReservationMsg','','教练预约',3,'','traienrDetail','trainerMsg'),('trainerReservationMsg01','','教练预约页面',1,'/demo/trainerReservationAction!toTrainerReservationPage.action',NULL,'trainerReservationMsg'),('trainerReservationMsg02','','预约列表',2,'/demo/trainerReservationAction!datagrid.action',NULL,'trainerReservationMsg'),('trainerReservationMsg03','','添加预约',3,'/demo/trainerReservationAction!add.action',NULL,'trainerReservationMsg'),('trainerReservationMsg04','','更新预约',4,'/demo/trainerReservationAction!edit.action',NULL,'trainerReservationMsg'),('trainerReservationMsg05','','删除预约',5,'/demo/trainerReservationAction!delete.action',NULL,'trainerReservationMsg'),('trainerReservationMsg06','','删除预约',6,'/demo/reservationDetailAction!datagrid.action',NULL,'trainerReservationMsg'),('wechatCommentTrainerMsg','','评价教练',2,'',NULL,'wechatMsg'),('wechatDiyMenuMsg','','微信自定义菜单',5,'',NULL,'wechatMsg'),('wechatDiyMenuMsg01','','微信菜单主页',1,'/demo/weixinMenuAction!menu.action',NULL,'wechatDiyMenuMsg'),('wechatDiyMenuMsg02','','微信菜单列表',2,'/demo/weixinMenuAction!datagrid.action',NULL,'wechatDiyMenuMsg'),('wechatDiyMenuMsg03','','编辑微信菜单',3,'/demo/weixinMenuAction!edit.action',NULL,'wechatDiyMenuMsg'),('wechatDiyMenuMsg04','','自定菜单到微信',4,'/demo/weixinMenuAction!push.action',NULL,'wechatDiyMenuMsg'),('wechatDiyMenuMsg05','','获取菜单列表',5,'/demo/weixinMenuAction!getListByCidOrderBy.action',NULL,'wechatDiyMenuMsg'),('wechatDiyMenuMsg06','','获取菜单信息',6,'/demo/weixinMenuAction!findSettingsByList.action',NULL,'wechatDiyMenuMsg'),('wechatDiyMenuMsg07','','获取菜单URL',7,'/demo/weixinMenuAction!getUrl.action',NULL,'wechatDiyMenuMsg'),('wechatDiyMenuMsg08','','获取菜单选项',8,'/demo/weixinSettingsAction!comboboxData.action',NULL,'wechatDiyMenuMsg'),('wechatHardReverseMsg','','难点反馈',3,'',NULL,'wechatMsg'),('wechatMsg','','微信服务',8,'','wxfw','0'),('wechatSingupMsg','','微信学车报名',1,'',NULL,'wechatMsg'),('wechatSingupMsg01','','添加报名学员',1,'/guest/signUpAction!add.action',NULL,'wechatMsg'),('wechatUserMsg','','微信用户管理',4,'/demo/weixinUserAction!userList.action',NULL,'wechatMsg'),('wechatUserMsg01','','微信用户管理',1,'/demo/weixinUserAction!datagrid.action',NULL,'wechatMsg'),('wechatUserMsg02','','微信用户列表',2,'/demo/weixinUserAction!datagrid.action',NULL,'wechatMsg'),('wechatUserMsg03','','微信用户主页',3,'/demo/weixinUserAction!userList.action',NULL,'wechatMsg'),('wechatUserMsg04','','用户消息主页',4,'/demo/weixinUserAction!message.action',NULL,'wechatMsg'),('wechatUserMsg05','','获取用户信息',5,'/demo/weixinUserAction!getMessageListByOpenId.action',NULL,'wechatMsg'),('wechatUserMsg06','','删除用户信息',6,'/demo/weixinUserAction!delete.action',NULL,'wechatMsg'),('xtgl','','系统管理',2,'',NULL,'0'),('yhadd','','用户添加',4,'/demo/userAction!add.action',NULL,'yhgl'),('yhaddym','','添加用户页面',3,'/demo/userAction!userAdd.action',NULL,'yhgl'),('yhcx','','用户查询',2,'/demo/userAction!datagrid.action',NULL,'yhgl'),('yhedit','','用户修改',6,'/demo/userAction!edit.action',NULL,'yhgl'),('yheditym','','修改用户页面',5,'/demo/userAction!userEdit.action',NULL,'yhgl'),('yhgl','','用户管理',1,'','yhgl','xtgl'),('yhglym','','用户管理页面',1,'/demo/userAction!user.action',NULL,'yhgl'),('yhsc','','用户删除',7,'/demo/userAction!delete.action',NULL,'yhgl'),('yhxgjs','批量修改用户角色','修改角色',9,'/demo/userAction!roleEdit.action',NULL,'yhgl'),('yhxgjsym','批量修改用户角色','修改角色页面',8,'/demo/userAction!userRoleEdit.action',NULL,'yhgl');
/*!40000 ALTER TABLE `tb_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_bug`
--

DROP TABLE IF EXISTS `tb_bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_bug` (
  `CID` varchar(36) NOT NULL,
  `CCREATEDATETIME` datetime DEFAULT NULL,
  `CDESC` longtext,
  `CNAME` varchar(100) NOT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bug`
--

LOCK TABLES `tb_bug` WRITE;
/*!40000 ALTER TABLE `tb_bug` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cars`
--

DROP TABLE IF EXISTS `tb_cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cars` (
  `id` varchar(36) NOT NULL,
  `businessExpireDay` datetime DEFAULT NULL,
  `buyDate` datetime DEFAULT NULL,
  `carNo` varchar(36) NOT NULL,
  `carType` int(11) DEFAULT NULL,
  `comment` longtext,
  `examinedDay` datetime DEFAULT NULL,
  `insuranceExpireDay` datetime DEFAULT NULL,
  `licenseNo` varchar(36) NOT NULL,
  `maintainDay` datetime DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `transportNo` varchar(36) DEFAULT NULL,
  `useType` int(11) DEFAULT NULL,
  `usingState` int(11) DEFAULT NULL,
  `vehicleBrands` varchar(36) NOT NULL,
  `vehicleType` varchar(36) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA4F6FA50D48AFC01` (`trainerId`),
  CONSTRAINT `FKA4F6FA50D48AFC01` FOREIGN KEY (`trainerId`) REFERENCES `tb_trainers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cars`
--

LOCK TABLES `tb_cars` WRITE;
/*!40000 ALTER TABLE `tb_cars` DISABLE KEYS */;
INSERT INTO `tb_cars` VALUES ('3e5b09b5-1c12-417e-8df4-0268df2ed994',NULL,NULL,'桂L.FM130学',6,'',NULL,NULL,'SD45465dFdf50217',NULL,'tyxq','c0510169-8edc-470e-b038-c299c7735bty','',6,2,'','','0afc253f-6ad5-44b8-9a0c-0638359aace1'),('560cdb7f-6903-496a-9ce9-d0d04e1d1437',NULL,NULL,'桂L.FM128',6,'',NULL,NULL,'SD45465dFdf5028',NULL,'bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs','',6,2,'','','3f4966b5-3b3f-47ca-9c21-e28b73ef86b7'),('9bea3d66-b4c5-45bf-b6cb-35fa291ac75c',NULL,NULL,'桂L.FM123',6,'',NULL,NULL,'SD45465dFdf5033',NULL,'bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs','',6,2,'','','5addced0-3ccf-4864-83ca-b14bb3c2862a'),('ac25e070-d459-4c5d-ab29-a72208818f56',NULL,NULL,'桂L.FM120',6,'',NULL,NULL,'SD45465dFdf5033',NULL,'tyxq','c0510169-8edc-470e-b038-c299c7735bty','',6,2,'','','6d67eefe-3b91-4f63-9619-54f2a06e6e25'),('e05d12ed-223e-432b-b5a3-834adf005fb0',NULL,NULL,'桂L.FM121',6,'',NULL,NULL,'SD45465dFdf5028',NULL,'tyxq','c0510169-8edc-470e-b038-c299c7735bty','',6,2,'','','56431f9d-53cd-4b71-8434-9cbd8c737d36'),('ed7da286-dc1c-45b7-8676-7c9674827683',NULL,NULL,'桂L.FM130学',6,'',NULL,NULL,'SD45465dFdf5028',NULL,'bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs','',6,2,'','','e57707e7-52cf-4b05-b58e-7bfd95f12a4c');
/*!40000 ALTER TABLE `tb_cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_classes`
--

DROP TABLE IF EXISTS `tb_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_classes` (
  `id` varchar(36) NOT NULL,
  `classType` int(11) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `costTime` varchar(20) DEFAULT NULL,
  `fee` double(10,2) DEFAULT '0.00',
  `name` varchar(50) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `orderNo` varchar(36) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_classes`
--

LOCK TABLES `tb_classes` WRITE;
/*!40000 ALTER TABLE `tb_classes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_comment_student`
--

DROP TABLE IF EXISTS `tb_comment_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_comment_student` (
  `id` varchar(36) NOT NULL,
  `commentTime` datetime DEFAULT NULL,
  `comments` longtext,
  `isRead` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `studentId` varchar(36) NOT NULL,
  `trainId` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_comment_student`
--

LOCK TABLES `tb_comment_student` WRITE;
/*!40000 ALTER TABLE `tb_comment_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_comment_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_difficulty`
--

DROP TABLE IF EXISTS `tb_difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_difficulty` (
  `id` varchar(36) NOT NULL,
  `comments` longtext,
  `createTime` datetime DEFAULT NULL,
  `difficulty` varchar(100) DEFAULT NULL,
  `openId` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_difficulty`
--

LOCK TABLES `tb_difficulty` WRITE;
/*!40000 ALTER TABLE `tb_difficulty` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_difficulty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_driverlicenses`
--

DROP TABLE IF EXISTS `tb_driverlicenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_driverlicenses` (
  `id` varchar(36) NOT NULL,
  `comment` longtext,
  `driverType` int(11) DEFAULT NULL,
  `firstGetDate` datetime DEFAULT NULL,
  `grantDate` datetime DEFAULT NULL,
  `grantState` int(11) DEFAULT NULL,
  `numbering` varchar(36) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `recieveDate` datetime DEFAULT NULL,
  `schoolArea` varchar(60) DEFAULT NULL,
  `validBeginDate` datetime DEFAULT NULL,
  `validYear` int(11) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentId` (`studentId`),
  KEY `FK18EC4E8BF89D7D8D` (`studentId`),
  CONSTRAINT `FK18EC4E8BF89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_driverlicenses`
--

LOCK TABLES `tb_driverlicenses` WRITE;
/*!40000 ALTER TABLE `tb_driverlicenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_driverlicenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_log`
--

DROP TABLE IF EXISTS `tb_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_log` (
  `id` varchar(36) NOT NULL,
  `operName` longtext,
  `operParams` longtext,
  `operResult` varchar(50) DEFAULT NULL,
  `operTime` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `resultMsg` longtext,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_log`
--

LOCK TABLES `tb_log` WRITE;
/*!40000 ALTER TABLE `tb_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_loguserlogin`
--

DROP TABLE IF EXISTS `tb_loguserlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_loguserlogin` (
  `CID` varchar(36) NOT NULL,
  `CDATETIME` datetime DEFAULT NULL,
  `CMSG` varchar(200) DEFAULT NULL,
  `CNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_loguserlogin`
--

LOCK TABLES `tb_loguserlogin` WRITE;
/*!40000 ALTER TABLE `tb_loguserlogin` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_loguserlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_loguserreg`
--

DROP TABLE IF EXISTS `tb_loguserreg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_loguserreg` (
  `CID` varchar(36) NOT NULL,
  `CDATETIME` datetime DEFAULT NULL,
  `CMSG` varchar(200) DEFAULT NULL,
  `CNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_loguserreg`
--

LOCK TABLES `tb_loguserreg` WRITE;
/*!40000 ALTER TABLE `tb_loguserreg` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_loguserreg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_menu` (
  `CID` varchar(36) NOT NULL,
  `CICONCLS` varchar(100) DEFAULT NULL,
  `CNAME` varchar(100) NOT NULL,
  `CSEQ` decimal(22,0) DEFAULT NULL,
  `CURL` varchar(200) DEFAULT NULL,
  `CPID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`CID`),
  KEY `FKA4FB949099D6926B` (`CPID`),
  CONSTRAINT `FKA4FB949099D6926B` FOREIGN KEY (`CPID`) REFERENCES `tb_menu` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES ('0','icon-man','驾校一体化管理系统',1,'',NULL),('arrangeexam','icon-filter','考试计划',1,'/demo/arrangedExaminationAction!toArrangedExaminationPage.action','exammng'),('buggl','icon-sum','BUG管理',5,'/demo/bugAction!bug.action','xtgl'),('carmanager','icon-sum','车辆管理',3,'','0'),('cdgl','icon-filter','菜单管理',4,'/demo/menuAction!menu.action','xtgl'),('commentStudent','icon-filter','点评学员',4,'/demo/commentStudentAction!studentList.action','trainermng'),('druidIndex','icon-filter','druid监控',1,'/datasourceAction!druid.action','sjkgl'),('exammng','icon-sum','考试管理',2,'','0'),('fxexamcount','','分校考试通过率',8,'','tjfxgl'),('fxxycount','','分校学员统计',7,'','tjfxgl'),('jsgl','icon-filter','角色管理',2,'/demo/roleAction!role.action','xtgl'),('kmone','icon-filter','理论成绩管理',2,'/demo/theoryExaminationAction!toTheoryExaminationPage.action','exammng'),('kmtwo','icon-filter','五项成绩管理',3,'/demo/subjectTwoAction!toSubjectTwoPage.action','exammng'),('ndfk',NULL,'难点反馈',3,'/demo/difficultyAction!difficultyList.action','wxfw'),('newcarmanager','icon-filter','车辆信息管理',1,'/demo/carAction!car.action','carmanager'),('newtrainer','icon-filter','教练信息管理',1,'/demo/trainerAction!toTrainerPage.action','trainermng'),('organization','icon-filter','机构管理',7,'','0'),('orgmsgmanager','icon-filter','机构信息管理',1,'/demo/organizationAction!organization.action','organization'),('personmsg','icon-filter','人员信息管理',2,'/demo/personAction!toPersonPage.action','organization'),('pjjl',NULL,'评价教练',2,'/demo/assessAction!assessList.action','wxfw'),('qxgl','icon-filter','权限管理',3,'/demo/authAction!auth.action','xtgl'),('roadexam','icon-filter','路考成绩管理',4,'/demo/subjectThreeAction!toSubjectThreePage.action','exammng'),('rxtjnbj',NULL,'报名统计年报表',5,'','tjfxgl'),('rxtjrbj',NULL,'报名统计日报表',2,'','tjfxgl'),('rxtjybj',NULL,'报名统计月报表',4,'','tjfxgl'),('rxtjzhbj',NULL,'报名统计周报表',3,'','tjfxgl'),('rzgl','icon-sum','日志管理',9,'','0'),('sjkgl','icon-filter','数据库管理',8,'','0'),('stdotimercard','icon-filter','学员计时卡管理',5,'/demo/studentTimerCardAction!studentTimerCard.action','xygl'),('studentDagl','icon-filter','学员档案管理',1,'/demo/studentFileAction!toStudentFilePage.action','xydagl'),('studentjszgl','icon-filter','驾驶证管理',4,'/demo/driverLicenseAction!toDriverLicensePage.action','xydagl'),('studentMsgzx','icon-filter','学员信息中心',13,'','0'),('studentMsgzx01','icon-filter','基本信息',1,'/demo/studentAction!toMyMessagePage.action','studentMsgzx'),('studentMsgzx02','icon-filter','计时卡',2,'/demo/studentTimerCardAction!toStudentPersonalTimerCardPage.action','studentMsgzx'),('studentMsgzx03','icon-filter','我的进度',3,'/demo/progressAction!toPersonalProgressPage.action','studentMsgzx'),('studentMsgzx04','icon-filter','理论成绩',4,'/demo/theoryExaminationAction!toPersonalTheoryPage.action','studentMsgzx'),('studentMsgzx05','icon-filter','五项成绩',5,'/demo/subjectTwoAction!toPersonalSubjectTwoPage.action','studentMsgzx'),('studentMsgzx06','icon-filter','路考成绩',6,'/demo/subjectThreeAction!toPersonalSubjectThreePage.action','studentMsgzx'),('studentMsgzx07','icon-filter','文明成绩',7,'/demo/subjectFourAction!toPersonalSubjectFourPage.action','studentMsgzx'),('studentMsgzx08','icon-filter','学时信息',8,'/demo/timingAction!toMyTimingPage.action','studentMsgzx'),('studentMsgzx09','icon-filter','预约信息',9,'/demo/reservationAction!toMyReservationPage.action','studentMsgzx'),('studentMsgzx10','icon-filter','驾驶证信息',10,'/demo/driverLicenseAction!toMyDriverLicensePage.action','studentMsgzx'),('studentMsgzx11','icon-filter','通知信息',11,'','studentMsgzx'),('studentprogress','icon-filter','学员进度管理',6,'/demo/progressAction!toProgressPage.action','xygl'),('studentXsgl','icon-filter','学员学时管理',2,'/demo/timingAction!toTimingPage.action','xydagl'),('studentXsyygl','icon-filter','学员预约管理',3,'/demo/reservationAction!toReservationPage.action','xydagl'),('studentzhgzgl','icon-filter','资格证管理',5,'/demo/qualificationLicenseAction!toQualificationLicensePage.action','xydagl'),('timercard','icon-filter','科目学时安排表',6,'/demo/timerCardAction!timerCard.action','xydagl'),('tjfxgl','icon-filter','统计分析管理',6,'','0'),('traienrDetail','icon-filter','教练预约',3,'/demo/trainerReservationAction!toTrainerReservationPage.action','trainermng'),('trainermng','icon-sum','教练管理',4,'','0'),('trainerMsgzx','icon-filter','教练信息中心',14,'','0'),('trainerMsgzx01','icon-man','基本信息',1,'/demo/trainerAction!toMyTrainerPage.action','trainerMsgzx'),('trainerMsgzx02','icon-man','排班信息',2,'/demo/trainerArrangeAction!toMyTrainerArrangePage.action','trainerMsgzx'),('trainerMsgzx03','icon-man','预约信息',3,'/demo/trainerReservationAction!toMyTrainerReservationPage.action','trainerMsgzx'),('trainerMsgzx04','icon-man','学员列表',4,'/demo/studentAction!toTrainerStudentsPage.action','trainerMsgzx'),('trainerMsgzx05','icon-man','通知信息',5,'','trainerMsgzx'),('txdj','icon-filter','退学登记',4,'/demo/quitschoolAction!toQuitschoolPage.action','xygl'),('useringcar','icon-filter','车辆使用情况',2,'/demo/usingCarAction!toUsingCarPage.action','carmanager'),('wmksh','icon-filter','文明成绩管理',5,'/demo/subjectFourAction!toSubjectFourPage.action','exammng'),('workarrange','icon-filter','教练排班',2,'/demo/trainerArrangeAction!toTrainerArrangePage.action','trainermng'),('wxfw','icon-filter','微信服务',11,'','0'),('wxxcbm',NULL,'微信学车报名',1,'/demo/signUpAction!signUpList.action','wxfw'),('wxyhgl',NULL,'微信用户管理',4,'/demo/weixinUserAction!userList.action','wxfw'),('wxzdycd',NULL,'微信自定义菜单',5,'/demo/weixinMenuAction!menu.action','wxfw'),('wzgl','icon-filter','网站管理',12,'','0'),('wznrgl',NULL,'网站内容管理',1,'/demo/articleAction!article.action','wzgl'),('xshbm','icon-filter','学员信息管理',1,'/demo/studentAction!toStudentPage.action','xygl'),('xtgl','icon-sum','系统管理',9,'','0'),('xydagl','icon-sum','档案管理',5,'','0'),('xygl','icon-sum','学员管理',1,'','0'),('xytjgl',NULL,'学员统计',1,'','tjfxgl'),('yhdlrz',NULL,'日志列表',1,'/demo/logAction!toLogPage.action','rzgl'),('yhgl','icon-filter','用户管理',1,'/demo/userAction!user.action','xtgl'),('zbxychx','icon-filter','班级开设管理',2,'/demo/classDesignAction!toClassDesignPage.action','xygl'),('zgksh','icon-filter','资格成绩管理',6,'/demo/qualificationAction!toQualificationPage.action','exammng'),('zhshqktj',NULL,'招生情况统计报表',6,'','tjfxgl');
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_online`
--

DROP TABLE IF EXISTS `tb_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_online` (
  `CID` varchar(36) NOT NULL,
  `CDATETIME` datetime NOT NULL,
  `CIP` varchar(50) NOT NULL,
  `CNAME` varchar(100) NOT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_online`
--

LOCK TABLES `tb_online` WRITE;
/*!40000 ALTER TABLE `tb_online` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_online` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_organizations`
--

DROP TABLE IF EXISTS `tb_organizations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_organizations` (
  `id` varchar(36) NOT NULL,
  `iconcls` varchar(200) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `sequence` decimal(22,0) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `prentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEFBDC18F9CBA1E7B` (`prentId`),
  CONSTRAINT `FKEFBDC18F9CBA1E7B` FOREIGN KEY (`prentId`) REFERENCES `tb_organizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_organizations`
--

LOCK TABLES `tb_organizations` WRITE;
/*!40000 ALTER TABLE `tb_organizations` DISABLE KEYS */;
INSERT INTO `tb_organizations` VALUES ('357c1d29-c991-4eed-a331-949f3eb4e9db','icon-man','德保校区',5,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('357c1d29-c991-4eed-a331-949f3eb4e9ll','icon-man','隆林校区',10,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('357c1d29-c991-4eed-a331-949f3eb4e9ly','icon-man','乐业校区',11,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('357c1d29-c991-4eed-a331-949f3eb4e9np','icon-man','那坡校区',7,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('357c1d29-c991-4eed-a331-949f3eb4e9tl','icon-man','田林校区',8,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('357c1d29-c991-4eed-a331-949f3eb4e9xl','icon-man','西林校区',9,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('357c1d29-c991-4eed-a881-949f3eb008ly','icon-man','凌云校区',12,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('4e5e35c0-2f4b-42fe-b04b-df357ff74e7c','icon-filter','广西驰程交通驾驶员培训有限责任公司',1,NULL,NULL),('606437a6-574c-4b0b-b79f-1a98bd4a45pg','icon-man','平果校区',4,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('6b698583-3aad-4702-8aa5-6e55b27df8jx','icon-man','靖西校区',6,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('7d15f11b-2928-41e8-8406-8b49cf3939td','icon-man','田东校区',3,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('b78ffef2-7c54-40fe-be4b-1910a87c8bbs','icon-man','百色校区',1,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c'),('c0510169-8edc-470e-b038-c299c7735bty','icon-man','田阳校区',2,NULL,'4e5e35c0-2f4b-42fe-b04b-df357ff74e7c');
/*!40000 ALTER TABLE `tb_organizations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_personaltimings`
--

DROP TABLE IF EXISTS `tb_personaltimings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_personaltimings` (
  `id` varchar(36) NOT NULL,
  `createWay` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `numbering` varchar(36) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `reservationAddFlag` varchar(36) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  `teachingContent` varchar(100) DEFAULT NULL,
  `timeItem` varchar(30) DEFAULT NULL,
  `timingType` int(11) DEFAULT NULL,
  `timingUsingType` int(11) DEFAULT NULL,
  `useTiming` int(11) DEFAULT NULL,
  `timingId` varchar(36) NOT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5C76CB38D48AFC01` (`trainerId`),
  KEY `FK5C76CB385FCC2CF3` (`timingId`),
  CONSTRAINT `FK5C76CB385FCC2CF3` FOREIGN KEY (`timingId`) REFERENCES `tb_timings` (`id`),
  CONSTRAINT `FK5C76CB38D48AFC01` FOREIGN KEY (`trainerId`) REFERENCES `tb_trainers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_personaltimings`
--

LOCK TABLES `tb_personaltimings` WRITE;
/*!40000 ALTER TABLE `tb_personaltimings` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_personaltimings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_persons`
--

DROP TABLE IF EXISTS `tb_persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_persons` (
  `id` varchar(36) NOT NULL,
  `accountNature` varchar(30) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `birthPlace` varchar(200) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `comment` longtext,
  `computerLevel` varchar(20) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `driverLevel` varchar(20) DEFAULT NULL,
  `duty` varchar(20) DEFAULT NULL,
  `educationLevel` varchar(20) DEFAULT NULL,
  `email` varchar(36) DEFAULT NULL,
  `employDay` datetime DEFAULT NULL,
  `graduateDay` datetime DEFAULT NULL,
  `graduateSchool` varchar(60) DEFAULT NULL,
  `identityId` varchar(100) NOT NULL,
  `languageLevel` varchar(20) DEFAULT NULL,
  `marriageState` varchar(20) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `political` varchar(20) DEFAULT NULL,
  `profession` varchar(100) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `startedJob` datetime DEFAULT NULL,
  `organizationId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD79936ED78732D85` (`organizationId`),
  CONSTRAINT `FKD79936ED78732D85` FOREIGN KEY (`organizationId`) REFERENCES `tb_organizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_persons`
--

LOCK TABLES `tb_persons` WRITE;
/*!40000 ALTER TABLE `tb_persons` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_progress`
--

DROP TABLE IF EXISTS `tb_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_progress` (
  `id` varchar(36) NOT NULL,
  `graduateDate` datetime DEFAULT NULL,
  `quitSchoolDate` datetime DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `sinupdateDate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `subject1Date` datetime DEFAULT NULL,
  `subject2Date` datetime DEFAULT NULL,
  `subject3Date` datetime DEFAULT NULL,
  `subject4Date` datetime DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC57840DEF89D7D8D` (`studentId`),
  CONSTRAINT `FKC57840DEF89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_progress`
--

LOCK TABLES `tb_progress` WRITE;
/*!40000 ALTER TABLE `tb_progress` DISABLE KEYS */;
INSERT INTO `tb_progress` VALUES ('1bef122c-7557-43c8-8b80-e084fc6b1d64',NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs','2016-12-10 13:39:44',1,NULL,NULL,NULL,NULL,'a1b1478c-2a2f-4e54-97ad-64f65453ad48'),('36e3437d-2b85-4b8f-9eff-3fcc6ee857f6',NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty','2016-12-10 13:47:38',1,NULL,NULL,NULL,NULL,'37f6d0a7-210c-418a-ab79-7aaab89b5a44'),('692e0192-6e07-4fc8-86ed-ee18017b5c25',NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs','2016-12-10 13:40:19',1,NULL,NULL,NULL,NULL,'a5283ccd-c9eb-41c3-85d4-eab90a0daec5'),('69e06dc1-ea60-45fa-99f9-66fdf9f85a57',NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty','2016-12-10 13:48:06',1,NULL,NULL,NULL,NULL,'c5de01a4-b7da-4979-afd4-d56dcc560aa5'),('89033197-9840-4c20-b42a-2b324860b1a8',NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs','2016-12-10 13:40:04',1,NULL,NULL,NULL,NULL,'00243719-b5db-49e5-ab21-915961bfe1a5'),('a4200191-8619-455a-951c-9e4dc1cf6367',NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty','2016-12-10 13:48:31',1,NULL,NULL,NULL,NULL,'62c3bf3b-6654-4007-9f3b-e10362552005');
/*!40000 ALTER TABLE `tb_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_qualificationlicenses`
--

DROP TABLE IF EXISTS `tb_qualificationlicenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_qualificationlicenses` (
  `id` varchar(36) NOT NULL,
  `comment` longtext,
  `driverType` int(11) DEFAULT NULL,
  `firstGetDate` datetime DEFAULT NULL,
  `grantDate` datetime DEFAULT NULL,
  `grantState` int(11) DEFAULT NULL,
  `numbering` varchar(36) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `recieveDate` datetime DEFAULT NULL,
  `schoolArea` varchar(60) DEFAULT NULL,
  `validBeginDate` datetime DEFAULT NULL,
  `validYear` int(11) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBBA15050F89D7D8D` (`studentId`),
  CONSTRAINT `FKBBA15050F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_qualificationlicenses`
--

LOCK TABLES `tb_qualificationlicenses` WRITE;
/*!40000 ALTER TABLE `tb_qualificationlicenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_qualificationlicenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_qualifications`
--

DROP TABLE IF EXISTS `tb_qualifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_qualifications` (
  `id` varchar(36) NOT NULL,
  `arrangedNo` varchar(36) DEFAULT NULL,
  `comment` longtext,
  `examDate` datetime DEFAULT NULL,
  `examScoke` int(11) DEFAULT NULL,
  `examSelected` int(11) DEFAULT NULL,
  `examType` int(11) DEFAULT NULL,
  `makeupExameDate` datetime DEFAULT NULL,
  `makeupExameScoke` int(11) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `passFlag` int(11) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `arrangedId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentId` (`studentId`),
  KEY `FKB0B11655F89D7D8D` (`studentId`),
  KEY `FKB0B1165534C51CA8` (`arrangedId`),
  CONSTRAINT `FKB0B1165534C51CA8` FOREIGN KEY (`arrangedId`) REFERENCES `tb_arrangedexamination` (`id`),
  CONSTRAINT `FKB0B11655F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_qualifications`
--

LOCK TABLES `tb_qualifications` WRITE;
/*!40000 ALTER TABLE `tb_qualifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_qualifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_quitschools`
--

DROP TABLE IF EXISTS `tb_quitschools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_quitschools` (
  `id` varchar(36) NOT NULL,
  `comment` longtext,
  `debitFee` double DEFAULT NULL,
  `numbering` varchar(36) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `quitCount` int(11) DEFAULT NULL,
  `quitDate` datetime DEFAULT NULL,
  `quitFee` double DEFAULT NULL,
  `quitReason` longtext,
  `schoolArea` varchar(60) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK347BC1FFF89D7D8D` (`studentId`),
  CONSTRAINT `FK347BC1FFF89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_quitschools`
--

LOCK TABLES `tb_quitschools` WRITE;
/*!40000 ALTER TABLE `tb_quitschools` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_quitschools` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_reservations`
--

DROP TABLE IF EXISTS `tb_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_reservations` (
  `id` varchar(36) NOT NULL,
  `comment` longtext,
  `date` datetime DEFAULT NULL,
  `fieldOptionFlag` int(11) DEFAULT NULL,
  `numbering` varchar(36) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `reservationState` int(11) DEFAULT NULL,
  `reservationType` int(11) DEFAULT NULL,
  `reservationWay` int(11) DEFAULT NULL,
  `schoolArea` varchar(60) DEFAULT NULL,
  `studentConfirm` int(11) DEFAULT '0',
  `timeEnd` varchar(20) DEFAULT NULL,
  `timeStart` varchar(20) DEFAULT NULL,
  `trainerConfirm` int(11) DEFAULT '0',
  `trainerReservationDetailId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68C5F458D48AFC01` (`trainerId`),
  KEY `FK68C5F458F89D7D8D` (`studentId`),
  CONSTRAINT `FK68C5F458D48AFC01` FOREIGN KEY (`trainerId`) REFERENCES `tb_trainers` (`id`),
  CONSTRAINT `FK68C5F458F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_reservations`
--

LOCK TABLES `tb_reservations` WRITE;
/*!40000 ALTER TABLE `tb_reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role` (
  `CID` varchar(36) NOT NULL,
  `CDESC` varchar(200) DEFAULT NULL,
  `CNAME` varchar(100) NOT NULL,
  `roleValue` varchar(64) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES ('0','拥有系统所有权限','超级管理员',NULL,NULL),('00695ac0-9d20-4509-aa57-d86532a81250','','管理员(百色)',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs'),('31ed8b2e-ce2a-4094-8eb6-c30270cccae5','','管理员',NULL,'c0510169-8edc-470e-b038-c299c7735bty');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_auth`
--

DROP TABLE IF EXISTS `tb_role_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role_auth` (
  `CID` varchar(36) NOT NULL,
  `CAUTHID` varchar(36) DEFAULT NULL,
  `CROLEID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`CID`),
  KEY `FK374B4740BAD8B352` (`CAUTHID`),
  KEY `FK374B4740D78A7D6E` (`CROLEID`),
  CONSTRAINT `FK374B4740BAD8B352` FOREIGN KEY (`CAUTHID`) REFERENCES `tb_auth` (`CID`),
  CONSTRAINT `FK374B4740D78A7D6E` FOREIGN KEY (`CROLEID`) REFERENCES `tb_role` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_auth`
--

LOCK TABLES `tb_role_auth` WRITE;
/*!40000 ALTER TABLE `tb_role_auth` DISABLE KEYS */;
INSERT INTO `tb_role_auth` VALUES ('0001016e-099a-40f0-a79f-7c255e32688f','driverLicenseMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('003e9bc3-6b51-403e-952b-b76bbbedbd92','trainerArrangeMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('00491733-7862-4c68-b7b5-63d757a0f24e','carUsingMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('0049f31f-e034-4bd1-8b2a-f3bf67062704','studentMangerMsg10','0'),('00808688-5771-4659-a3ba-2679ff843394','examinationjhMsg22','00695ac0-9d20-4509-aa57-d86532a81250'),('00b5b63d-247a-48be-9e37-88dc2b9415f4','organizationMsg02','0'),('00bb76b4-65df-4c1f-920b-ebd9a09ee815','qualiLicenseMsg05','0'),('018623cc-a1cd-410a-ac53-f931cacc69aa','personalMsgGl03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('01d2c13a-ad47-427c-a76d-aab4501704c0','trainerArrangeMsg12','0'),('01ea4fc4-692c-4daa-83d0-56d903976cef','trainerMangerMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('021ac501-15e7-4f3e-8360-6774daefc78f','examinationjhMsg13','0'),('024ddd7a-fc58-4acb-833d-43a4628a26bd','trainerMangerMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('036bd142-542b-4403-882a-9e84ed806202','classMangerMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('037d4f35-b7fd-4c52-bc5f-117bb93c792c','yhsc','0'),('04c6180a-0b0d-430e-9f5b-4e3986c36f9a','examinationjhMsg19','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('04dbfda9-da08-42a6-9098-dfc0a4a64372','trainerArrangeMsg07','0'),('05023e2e-e2c0-47c3-94ba-13703cbccdf5','studentMangerMsg12','0'),('05cc83b8-2477-44ba-97c3-176648cd6c59','examinationjhMsg18','0'),('05e4332d-2c96-48eb-a700-5e2bf3ec86fb','examinationSub2Msg07','00695ac0-9d20-4509-aa57-d86532a81250'),('06180540-331e-496d-a152-f8408131b604','bugdesc','0'),('061b3b85-5b5a-4082-a55a-a436e395d0cd','cdedit','0'),('0642bde9-78ad-4551-b5f1-56a0c366d84f','studentMsg004','00695ac0-9d20-4509-aa57-d86532a81250'),('065d8586-e992-4185-b4fd-1a835bce05d1','examinationjhMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('06630d31-5293-4a0d-aecb-bacf5d7f5026','trainerReservationMsg','0'),('06d6a6f5-98df-4164-ad40-49d2c4cd2bcc','trainerReservationMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('070e8d11-fdde-4290-b61a-8e62e08a9eb6','personalMsgGl01','00695ac0-9d20-4509-aa57-d86532a81250'),('0756e703-3436-4eb2-afb2-f90ca76d574d','trainerMangerMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('076adf3c-0fe7-4785-8167-970c0af77f98','commentStudentMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('077b6293-84fa-49b9-a8bb-cdf3cf937703','driverLicenseMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('07862236-ff24-4168-9265-34bd6a94380e','studentTimeMsg03','0'),('07aff14c-e176-4e65-b012-9b1772c81028','studentMsg002','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('07cd3d5c-e5f4-4262-a8cc-6845633c6b11','carUsingMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('082cfdd5-3ec9-4ef4-a8f0-8efe32570896','quickSchoolMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('083007f0-2f01-4505-b134-b52c0997a7d8','jscx','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('0834a1ce-d1da-4c85-a2a1-6c97900fe71f','studentReservationMsg19','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('08728e10-7989-46d7-bb56-5a1c4cbcb72d','wechatUserMsg04','0'),('08a9cfa9-4a4e-4646-8aa4-e87ec5779bc6','studentMsg012','00695ac0-9d20-4509-aa57-d86532a81250'),('08d98536-d5ff-4624-a130-c9ce4de584c0','progressSchoolMsg03','0'),('08f75e5f-b601-4f7e-8d62-403c931be862','carUsingMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('095708e6-c996-4496-a347-a3fbe0a368d4','studentTimeCount01','00695ac0-9d20-4509-aa57-d86532a81250'),('0a0fc458-8b15-4632-8362-f72ef02b806c','studentTimeMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('0a4f5172-8ed6-4b9a-8d95-c5716bf89b11','examinationSub3Msg08','00695ac0-9d20-4509-aa57-d86532a81250'),('0a5e7738-d363-4a6f-bbcd-fc00d999cc22','netMsg02','0'),('0a6a3668-849c-41d9-867f-9ccdcb0b3f22','studentMangerMsg26','0'),('0b2e95c5-d659-44c5-9724-c91f6b247418','examinationjhMsg01','0'),('0b4448c5-0a3d-42df-af26-80cdf35cfc4e','driverLicenseMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('0b6acaf1-3b5d-4d7c-a9bc-8b61e9008ffc','trainerReservationMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('0c49b93e-9c11-42c8-b0f5-66247503ce3f','classMangerMsg05','0'),('0c947b6b-0a21-49de-8e15-3f0b4c48a7e1','studentTimeCount05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('0ca8b65e-29af-48de-816e-ca2ee44622b1','trainerMangerMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('0ce1192d-094e-482c-a7a5-2eb0894bdc8f','driverLicenseMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('0d06b536-47a2-4475-b4a2-dfcc9582e3f6','trainerReservationMsg03','0'),('0d4d4272-6e45-4b92-b2d1-6a65354d815b','commonMsg19','00695ac0-9d20-4509-aa57-d86532a81250'),('0d6fc2e4-b275-492c-8cd5-7509b74f24d7','trainerMangerMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('0d829db5-82d0-4f97-8d5c-492da2a675b2','studentTimeCount03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('0e6a3feb-d6bd-416e-ad9d-50418ef7fb75','organizationMangerMsg','0'),('0ecdb094-bee2-42a4-9aef-0084e3e7baa0','commentStudentMsg','0'),('0ee5610e-3e68-4490-97a5-a8c78dd7ed4f','examinationSub5Msg07','00695ac0-9d20-4509-aa57-d86532a81250'),('0f71c5d4-ea62-4998-8547-2ddc2e9e46e0','trainerArrangeMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('0fc9359d-6959-4e04-910c-33e7bb6dab69','wechatDiyMenuMsg01','0'),('101c9ba0-ee30-4cae-8cde-b04b0877e62f','examinationjhMsg10','0'),('1031393c-3a98-4ff8-adb1-a38e7b75524f','logMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1067bad5-a09f-4cfd-b9f2-9755451cedac','trainerMsg','0'),('108a0713-1a7e-44b6-bd0e-e4f5f7051b98','quickSchoolMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('10b25812-8449-44b4-8fa7-73c361820de8','yhxgjs','00695ac0-9d20-4509-aa57-d86532a81250'),('10ecaea9-70a2-44f1-a818-8c4389c7cd3e','subjectTimeArrageMsg04','0'),('116218d7-b50b-4c17-8f88-03beb5c6fc9d','classMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('117203ee-df79-4279-9e96-18f917101164','studentMsgMngAddPage','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('11b6a215-5a6b-4e15-a400-712c58dbf13b','examinationMsgSub3','0'),('125cc2d5-55c0-46c3-bf0f-347e2b707ae8','trainerCenterMsg04','0'),('1290c6bf-8767-44aa-b61b-0ac40d0a087f','commonMsg15','0'),('12bcc48d-049b-431b-9d92-64e1f581e80f','organizationMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('131752e1-4e6d-4cfb-ac43-fe57662eba56','studentfileMsgAuth04','00695ac0-9d20-4509-aa57-d86532a81250'),('13c24069-fae2-4a76-9cf4-856f27a5f3ef','trainerArrangeMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('13e3f8ee-712c-4bdc-b740-4dcefb8678a2','examinationSub1Msg06','00695ac0-9d20-4509-aa57-d86532a81250'),('1475ae6b-d673-4ad8-9b71-beed3ef223cf','trainerReservationMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('15082f1b-3d30-4c5e-875b-66186ac8a560','trainerCenterMsg11','0'),('15114c0f-cdfb-41ea-bf6e-77a5494f36f4','studentReservationMsg11','0'),('152941c6-227c-40ce-bae3-c7325e68aa57','maxIdListMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('157c22fc-5975-4c74-86d7-a15b6ed81e19','logMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('15a89ce0-2140-4330-a698-f57968631d91','studentReservationMsg12','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('15e31caf-2117-4bce-8a90-5d8e75015b7e','studentMangerMsg24','0'),('15ed6114-a536-4b78-b331-0bbced4cc283','studentMsgMng','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('15f642db-c309-4df3-a51e-172faafac5f2','trainerReservationMsg01','0'),('16ae0232-a849-4021-b089-0bbf501d4c93','commonMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('16c37d4b-f738-43e8-9ff8-bef252855eec','examinationSub5Msg05','0'),('16e2795e-844f-458b-b584-fb379afd281c','trainerArrangeMsg12','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1708724e-6ae8-4321-9077-49de39fe2747','sjkgl','0'),('17329d92-2314-4ff6-9bc0-bb71f595495a','carMangerMsg04','0'),('17d0768a-e74e-4434-b0b6-0fb12cd1184f','commonMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1885ffb0-fcf5-4112-b000-7c61e6fed2c2','qxdelete','0'),('18ce8368-7c27-42a9-9704-5564318439cd','yhedit','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('18e39dd6-200f-416f-a843-1ba06d4e7f23','trainerMangerMsg06','0'),('18e466d0-e96c-4bb4-ba65-8b01a4b3dd39','trainerMangerMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('1911f92e-23fe-48e7-8c1d-1ad45f63006b','studentReservationMsg15','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('192ccd41-352e-4113-8416-2d21e9adc036','commonMsg14','00695ac0-9d20-4509-aa57-d86532a81250'),('1954da2c-090c-4798-b886-211c3d7c2c2d','classMangerMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('198fe6e3-52f2-46d3-9058-c63c9581b634','carUsingMsg07','0'),('1a53caa2-891a-405e-9096-9d30ef02535a','commonMsg18','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1a746b76-5023-4539-8af6-7e7e11e177dc','commonMsg12','0'),('1adba147-4e2e-48b4-bfb8-26e6cf11b050','carMangerMsg08','0'),('1afc287a-d77a-486e-8ee1-1dd67513583c','carUsingMsg05','0'),('1b307b25-d65a-48bd-8ea8-5c67e24a4d39','trainerListMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1b52d378-3bff-4bcc-8c5a-a4f7fac78ba4','studentReservationMsg16','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1b605a77-fdec-4dff-90db-f4853f46ba92','quickSchoolMsg11','0'),('1b62b68a-702c-44c7-95f1-89793b5dce59','studentReservationMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('1c3a8175-958a-4d1d-b7d9-df9ddb5718cf','jscx','00695ac0-9d20-4509-aa57-d86532a81250'),('1c3eccdc-1c0f-4cf5-b990-8d3af4476d01','examinationSub4Msg05','0'),('1c825121-315d-4b78-bf44-99f1610324d8','driverLicenseMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1cae0af8-39f9-474f-88b4-ebad4d71733e','jsadd','0'),('1cbe603d-91c4-4572-bf99-e32765863b47','studentTimeMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1cda650f-13ec-4162-bca5-6c8ba322dca6','progressSchoolMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1dff6853-8fea-4ad9-86bf-5076371e13f0','examinationSub5Msg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1e2ee458-2209-4877-b6d5-8797cec57ceb','0','0'),('1e39214c-e600-4f37-8fda-3b60aef743f9','studentMsg13','0'),('1ef5f07c-d360-465b-8314-0b73e8505204','studentMangerMsg13','0'),('1efe6d6d-5481-44a3-babd-125ee71e8d43','studentTimeMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1f56e3a2-0f66-408c-a063-39e074162e59','trainerMangerMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('1f6fcee8-a114-491a-b1cc-08b503da2eff','trainerArrangeMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('1f960145-a6a7-4372-ad33-ee5277f76b0d','studentMsg007','00695ac0-9d20-4509-aa57-d86532a81250'),('1f99f0c6-99ed-468b-a2ca-b03849d1c6ee','commonMsg16','00695ac0-9d20-4509-aa57-d86532a81250'),('1fbc23e3-8891-4691-b57c-87eabf4e8810','carMangerMsg06','0'),('200f8dca-33d1-4cf8-b1c8-3f900c7e0b2b','organizationMsg','0'),('202c525d-34ea-440d-8781-b432da4fecd0','logMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('20705683-6d20-4ec9-8138-3abd790a5a70','subjectTimeArrageMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('20d6cddd-9995-48a0-92e4-2f6988bb60f2','wechatCommentTrainerMsg','0'),('2128e778-72b3-4a71-b231-8dd3f3cd1dcb','quickSchoolMsg09','0'),('2138256e-ced8-4df9-bc17-30a446aa852e','studentTimeCount13','0'),('214263e4-e997-47b0-a746-f89ca3bea9de','subjectTimeArrageMsg02','0'),('21520902-3c00-4cd1-8306-a13b7713eee2','driverLicenseMsg08','0'),('218c279c-950d-4c73-bd7e-5faa8cd6979a','studentMsg006','00695ac0-9d20-4509-aa57-d86532a81250'),('21c3b131-2343-40e0-8243-e3490538bcff','examinationSub2Msg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('22040704-6288-41a2-877e-42269e187608','trainerArrangeMsg01','0'),('220f89a0-5ee5-4b5c-81d2-81d35e8cde76','studentReservationMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('221599f8-cdf0-415b-bdb2-44f99e544a88','cdeditym','0'),('221c7764-b726-4965-8522-86f19462f618','commonMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('223c4bea-c2dc-4ed3-afe6-be120eb2cce1','classMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('228d9d35-daa1-4dc2-af71-16a4f78298c9','bugdelete','0'),('22959605-990d-4bb2-aad2-25e20aea0341','personalMsgGl06','00695ac0-9d20-4509-aa57-d86532a81250'),('22ad59b1-11b1-4bca-b850-64c370e1c4bd','commonMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('231b06ea-72e2-4630-88a5-354ce17bf7f7','yhglym','0'),('231d7dc0-8943-41c5-94bf-231492728db2','subjectTimeArrageMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('2325b3a8-6e64-451e-8aad-91efa02b1949','quickSchoolMsg','0'),('23fa56df-84fe-4a5e-b5c7-e61240f3ba8e','examinationSub1Msg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('25745f78-233d-4e10-a1a6-100147f9130e','examinationSub2Msg05','0'),('25919af9-65f0-4a91-80e6-166623341b0f','examinationMsgSub4','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2601eb57-322d-4d07-bb5e-f9497afa32cc','examinationjhMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('26326d9e-e9b2-413a-8d51-8c823da47289','carUsingMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('26922c09-5c27-4a73-a8a9-058c2bea7a45','studentTimeCount11','0'),('26efa736-bf34-4bf2-a928-94600033a002','studentReservationMsg15','00695ac0-9d20-4509-aa57-d86532a81250'),('278ed505-9268-4950-bbd7-6432890cf002','trainerCenterMsg06','0'),('27ab8330-d619-480d-a89e-bb2e212b226d','qualiLicenseMsg09','0'),('27e3ffcc-f7ce-4671-9d0a-894f5df364ac','commonMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('27f46876-07e7-4719-8f7b-093916b95cd2','wechatUserMsg','0'),('28c4421e-d1f5-4325-9919-3b293dbaf4b3','studentTimeCount01','0'),('29436f01-5361-46fd-8896-9de28ad4e6d2','studentMangerMsg07','0'),('2951d565-1d50-4171-9440-68218cfe2abd','commonMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2952f77c-2aea-446a-8254-4bf3a6617ad4','commonMsg14','0'),('295811d2-901a-456a-a717-d50ec043e9a6','studentfileMsgAuth01','0'),('29bd56b3-706a-453d-8777-320c505b5bf1','studentReservationMsg12','0'),('29c825fc-95e1-4e4a-b372-c1ff11bc8a6f','examinationSub5Msg06','00695ac0-9d20-4509-aa57-d86532a81250'),('2a274eef-a876-4bca-b690-38f291d5470b','studentTimeCount06','0'),('2a48123f-0750-4ece-af1d-73736a8c1afe','subjectTimeArrageMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('2abd6d72-c854-495f-9d28-2059967e931d','examinationjhMsg06','0'),('2af00dd8-fd14-4991-b303-86a6cd7fc869','yhcx','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2b08e905-3aaa-4d3e-831e-467ee830bf7f','carUsingMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('2b4ee02b-1317-4858-bd39-9d8966ec9a83','carMangerMsg07','0'),('2b7ee63a-2a92-4b75-86e8-f99b5e134908','examinationSub3Msg07','00695ac0-9d20-4509-aa57-d86532a81250'),('2bd48169-45f9-488e-829d-3892a24e1e26','studentReservationMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('2c072b5e-1e02-4a5c-9d66-33a3a67aeca3','examinationSub3Msg07','0'),('2c412eeb-c23a-42a5-aac3-d171e16089a0','qualiLicenseMsg','0'),('2d58a673-d053-4ec7-9505-b109fdf42f9b','organizationMsg10','0'),('2d5eaacd-ae5f-4af1-9c63-1fd3b2652f88','studentMsg012','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2d96e4ee-4462-4331-94a5-5a7abb9cd21c','studentMangerMsg29','0'),('2d9a8bcb-a65d-40f7-8f39-acdeacf7db16','examinationSub3Msg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2e5adc57-93ad-4bab-9800-5382466bfea7','jseditym','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2e6c224e-f21e-4ec7-8fd0-b380a1895c6f','driverLicenseMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2e92f65e-20c0-402b-afca-1f5f9f5ac696','carUsingMsg04','0'),('2ed23237-92ba-4830-b7b3-aea9ac18b524','examinationSub1Msg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('2ed66c7c-d336-4381-92ac-6d3a27b243ab','trainerArrangeMsg11','0'),('2edfcc10-ac0c-46a8-ad41-a5999cc8a911','personalMsgGl02','0'),('2fb73f92-8ef4-4277-b285-49d78c8e379e','commentStudentMsg02','0'),('2fd1c99c-1825-4311-bd87-4ce076898c72','studentMangerMsg03','0'),('307990f6-ac31-4d05-86b4-18cdb002f708','bugaddym','0'),('30a47abd-4434-4830-adfd-75bc8dceca7d','wechatUserMsg06','0'),('310eda90-2f78-445c-8806-2877f6c4231e','examinationSub5Msg02','00695ac0-9d20-4509-aa57-d86532a81250'),('312cb438-df94-4d63-ac4a-e02b292fafca','studentMsg001','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('314695eb-066d-4c18-a38f-4d1d536e712d','studentReservationMsg18','0'),('314d4960-9645-4d12-bbc2-97f4ae213721','quickSchoolMsg06','0'),('31abcb07-a353-4025-aa72-f2f491528bd9','studentMangerMsg01','0'),('31c103bd-d7fc-4a20-a9e8-de1bab7bbcd8','personalMsgGl01','0'),('31c1320e-928a-44ca-93f7-d1f72ad99e2b','examinationSub5Msg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('32131d87-8a03-4374-9954-be2226b65f8c','commentStudentMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('3285c8e6-7085-455c-b319-29afa69da1bf','personalMsgGl06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('32c9e44c-e52c-41c1-a14a-322e18e111df','trainerArrangeMsg04','0'),('33353fd1-41c0-4adc-910e-398fcf2ccbde','trainerMangerMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('333abbd7-cf3e-4933-be32-e0ac8ca375f6','studentMsg007','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('333dd06c-f697-4866-908e-b99763d7640c','studentMsg13','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('335b8d43-8d74-489b-9c41-968c3d793b93','studentReservationMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('335dacdb-2a5a-4864-bea9-d4385096119f','studentReservationMsg17','00695ac0-9d20-4509-aa57-d86532a81250'),('33645976-3460-4302-96d6-9978f760eb32','carMangerMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('33b83dd7-7ef5-46aa-9659-cf3f271f8c51','examinationSub4Msg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('33ec6da5-eaf8-4919-9840-4b44768f5ae1','trainerMangerMsg08','0'),('33f03309-5434-4040-be37-86f622712e9d','qualiLicenseMsg08','0'),('34201ba4-32f2-45e0-a777-960597b4743b','quickSchoolMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('34238b95-6fa6-4c75-bc64-9db88528824f','studentReservationMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('342dc6ce-65f9-41d9-bb13-015ecf049a2a','cdaddym','0'),('349f0eed-a656-4782-89f7-6c3df005d2e6','carUsingMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('34abeaae-92d8-43bb-ba74-998ff3b55917','studentReservationMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('352b9999-35ad-4e2d-89ba-bc91188eb71c','examinationjhMsg20','00695ac0-9d20-4509-aa57-d86532a81250'),('3533d412-2332-48ef-8bd6-bb5d62c2de0c','personalMsgGl03','00695ac0-9d20-4509-aa57-d86532a81250'),('35720c2b-e034-45e1-9aa7-2abe3ccc123e','commentStudentMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('36103044-c0c1-4593-b234-71d393b18703','studentTimeCount09','0'),('3649ef7c-736c-4149-8d84-029ee4139249','quickSchoolMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('36b07628-5410-48e6-b570-6b9b7d2dec2a','examinationSub1Msg09','00695ac0-9d20-4509-aa57-d86532a81250'),('36b4718e-3d7c-4e65-817e-ddb3a162b2e0','examinationMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('3729a46f-2a2f-4a37-a2e5-489796f46284','examinationSub5Msg08','00695ac0-9d20-4509-aa57-d86532a81250'),('3758e378-b68c-4fd2-af64-f572712b8bd4','logMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('3766e03c-a4b1-4cfb-9323-88dad64e4450','yhcx','0'),('3779ea7d-2157-402e-8133-a08a973073e0','trainerCenterMsg09','0'),('37a88ede-24c8-4e40-96c2-72482efa6451','trainerReservationMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('37e5013c-b8fc-4b58-9ac8-f744d878ab27','trainerCenterMsg15','0'),('38816f2f-09d5-4c4e-83d3-357bbba3f997','yhxgjs','0'),('3892e2ab-13e3-463a-b50f-3b85c6efd90a','organizationMsg07','0'),('38bc7dc0-16d6-47b9-b22a-5f14078f90a9','examinationSub4Msg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('38c6c980-585e-4791-9380-d4bd1fc364a5','examinationjhMsg03','0'),('38d60c05-0bc2-4f21-9195-6a8caec485ec','examinationSub1Msg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('395c4d8e-aa3d-4eea-89f2-b9b6226b7168','qualiLicenseMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('398c8bd0-26d4-483f-a958-7783f6f5e74a','commonMsg08','0'),('39948ae5-f925-44c6-bc60-9296bb67f35c','examinationSub3Msg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('3a2ac7c1-85a9-496d-a41e-084fd0feb42b','trainerReservationMsg02','0'),('3a98fb63-4d49-4864-8395-370363ef01f7','examinationSub5Msg04','00695ac0-9d20-4509-aa57-d86532a81250'),('3aa3ba39-088f-4caa-811d-e34cdd6e0fae','trainerReservationMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('3ab1c1c9-6571-406b-95ec-93ce2bdd2191','trainerMangerMsg01','0'),('3aba9d4c-791b-47c0-9f13-2890cc8441bd','examinationSub1Msg','00695ac0-9d20-4509-aa57-d86532a81250'),('3abd9d0e-9eba-411b-b300-08bed773c541','driverLicenseMsg07','0'),('3adb8952-373d-4a13-a997-79f50fe86825','wechatDiyMenuMsg07','0'),('3aec0d9f-416f-41ef-97a4-b1dd0c76ab5c','commentStudentMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('3b1ba13a-db56-4eb7-8bed-46794800e5d5','studentReservationMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('3b1d6b2c-9263-446b-9e31-8632a052c235','personalMsgGl07','0'),('3b5d90ca-a962-455a-8cb0-0e12af4d9c9e','subjectTimeArrageMsg05','0'),('3b7abf2b-56d8-461c-bb6e-d44e4c7c7998','examinationSub4Msg09','00695ac0-9d20-4509-aa57-d86532a81250'),('3b7e5e22-367e-412b-bc45-b19c86c99f56','carMangerMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('3bc2a028-99df-4674-8891-04d54ed4d965','studentTimeMsg','0'),('3c432a77-53dd-4204-b3f4-60ab29c4bc64','commonMsg01','0'),('3cb51f02-d6ad-4f48-90f8-35decf8ef2bf','examinationMsgSub5','0'),('3cb94456-b4fc-4323-9ea3-aa0a4f35e361','progressSchoolMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('3d794fc3-90a2-4d06-8d7d-806c547d759e','examinationSub1Msg02','0'),('3de60b0d-7b21-4b6a-a051-b601f66b048a','carUsingMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('3deb8990-10f8-437b-94f9-f156aecc40fc','examinationSub2Msg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('3e0ce925-1d9d-405e-a730-4a19a8188c44','examinationjhMsg','0'),('3e1adf52-e9b0-4828-b978-4bbbc65b6da0','examinationSub2Msg02','00695ac0-9d20-4509-aa57-d86532a81250'),('3e31619e-aa31-411f-9003-7b7507fbce0f','qualiLicenseMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('3e40cca1-fc8b-4e98-b8bb-c0b0a0a70a1f','studentMangerMsg09','0'),('3e428540-d6f3-4788-85f4-ebfc36b277f2','yhaddym','00695ac0-9d20-4509-aa57-d86532a81250'),('3eb21f6d-296e-41fb-9401-65dacd8ce466','carMangerMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('3ef62a7e-09e2-45b4-9120-660b2ef9359e','studentMsg009','0'),('3f29e4df-c1f9-4e83-bec2-0b76531fdfa0','organizationMsg03','0'),('3f95007c-34f1-4a26-adce-e9c61ee3c8f6','maxIdListMsg','0'),('3ff79952-9990-415c-b546-5178785c15d3','driverLicenseMsg01','0'),('3ff7a7af-df5b-450b-b4ce-17a04de2aafb','studentReservationMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('40b8009d-5e27-4382-bc9f-3b54413691c2','carUsingMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('40f53ea8-cd90-4119-91df-4901ea5f6e51','yheditym','0'),('410a161f-bb7e-4667-9c63-38fbe12e4575','carUsingMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('41268969-cc27-47e4-84ea-4e1622f1be03','studentReservationMsg18','00695ac0-9d20-4509-aa57-d86532a81250'),('4160f0e9-b685-40c3-8a39-492f5f304e48','examinationSub1Msg01','00695ac0-9d20-4509-aa57-d86532a81250'),('4181055b-ea3f-41dc-9566-7d098067ee51','carMangerMsg02','0'),('41d5ba7f-76c8-408e-8232-13031a16425e','carUsingMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('41ea0187-b7aa-4478-9558-e2ba6dbea7da','quickSchoolMsg10','0'),('42123f2a-80ca-4b11-90a1-612611db78dd','examinationSub2Msg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('428a8926-6083-4426-8707-07ab50492193','subjectTimeArrageMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('42ac6edb-f284-4f38-a974-312fa78ac642','quickSchoolMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('42df9408-f895-4c48-a866-ba68051a5d7c','studentMsg011','0'),('43b42fd7-eb48-4ef7-b332-34c78bf04d38','commonMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('43c73c9b-0ae9-4e57-ae6d-d9362f4be633','driverLicenseMsg02','0'),('43dbb7e0-4d65-4b69-9569-edfac0421bae','organizationMsg06','0'),('43f2d43a-cdff-4ec1-99ab-dfbcaff049fa','commentStudentMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('442d8249-5c22-431e-aee5-859afdc1109e','studentReservationMsg07','0'),('442f6a52-b80c-45d0-a862-382efec1ab5f','carUsingMsg02','0'),('4433208f-684a-4ddb-a840-435c7be92c85','subjectTimeArrageMsg01','0'),('4436c0dd-51d7-4a84-a3d0-44e991128419','carMangerMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('444a9159-9688-4d86-a225-803ab9598e90','studentTimeCount14','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('444bd6f8-f898-4297-936d-83f5be6097ec','studentfileMsgAuth04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('444e521b-1995-42e5-8303-0c1e838428b6','studentReservationMsg17','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('446fd42f-383d-4a01-80c2-9c293fb8a3c7','driverLicenseMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('446fe762-908b-4dd1-990d-62a6bd962f86','qualiLicenseMsg01','0'),('44c99345-2065-4e93-b2f4-273565fc5ae7','trainerArrangeMsg12','00695ac0-9d20-4509-aa57-d86532a81250'),('45e19655-ec0f-4525-9e72-1e4276c30fe4','examinationSub3Msg05','0'),('45ef4212-9c25-4934-b822-a1176bc3d71b','commonMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4669d6e7-66df-4c3e-a10c-48af01083a9f','studentMangerMsg23','0'),('4670d5f8-4ec2-499f-b99a-be743e9ee397','examinationSub3Msg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('467c83fe-ddfd-4e22-b829-71b8cf488d73','examinationMsgSub3','00695ac0-9d20-4509-aa57-d86532a81250'),('46b3f394-4de6-4bbe-bc67-9097f45e87ff','trainerReservationMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('46e39a4b-c6f0-4a3d-83f9-a17f24cdc681','trainerListMsg','0'),('4765dc18-a54e-43dc-a3c8-55f88fbbab44','studentTimeCount02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4846d886-e130-44f1-893e-38e8d328f51b','examinationjhMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('485171ae-eb66-4f53-8e11-df075b8a501a','studentfileMsgAuth05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('487cdb8e-6a6b-42fa-951b-3840fe845674','examinationSub5Msg02','0'),('488088d5-5ac1-45a7-b033-49583d930160','organizationMsg09','0'),('4885ed66-8408-48ae-943f-5098357006c2','studentMsg009','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('48aaaa38-62a8-4e3f-bde5-7fa4ceef1c07','trainerMangerMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('48af9085-1334-4b11-b4c1-54392f57671e','wechatUserMsg02','0'),('48cbfe6e-52d3-45e7-a1a3-f8843f4faec4','examinationSub2Msg09','0'),('490ef846-c000-4dc4-92ec-3cc30058d3e2','examinationSub4Msg03','00695ac0-9d20-4509-aa57-d86532a81250'),('491d0276-5bd6-4ca9-8355-bb965e342641','jsadd','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('495e1247-0ac2-4dfd-99bc-7445d38b7b78','carUsingMsg11','0'),('496328e1-c9ff-419e-aed4-f80601fc48da','examinationSub3Msg03','00695ac0-9d20-4509-aa57-d86532a81250'),('49a5ab74-c126-4e35-b022-c54faebcd06d','studentMsgMng','0'),('49a8ba6c-b7e9-4a0f-85c1-3b28b06d1190','commonMsg16','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('49db2fe3-e2d2-4759-a2aa-f15f33863125','qualiLicenseMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4a0fb09e-ec78-45fe-9381-f69b94b4db7a','xtgl','0'),('4aa6e295-f479-4b1b-9c48-789a811e78de','carMangerMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4ae62918-5768-470d-8e66-847f86126f31','commonMsg10','0'),('4aed4611-0ee1-4d09-aea6-20fe8e7b9559','studentMangerMsg20','0'),('4b0f749a-b6de-4110-be01-927d49064d98','classMangerMsg07','0'),('4b249c12-06df-41b9-9f64-608e06985af3','qualiLicenseMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4b685970-be30-49a6-856b-de1f481c970f','trainerReservationMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('4b9527cd-76a9-4195-a5ac-3b6044bd4d19','jsdelete','0'),('4bd124bc-673d-4552-b713-2386076fe523','studentTimeMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4beabebd-376f-480e-ada0-36c8b82bada0','yhedit','00695ac0-9d20-4509-aa57-d86532a81250'),('4c64218a-3185-405c-9ef7-02b2753eea58','studentReservationMsg13','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4c920b23-7421-46fa-9587-47297386a9e4','ljcjk','0'),('4cd19586-a385-47d7-a48b-5142a3f0d16b','jseditym','0'),('4cfdb93f-c7c0-494b-87f4-c2604eb425fd','yhxgjs','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4d61629f-cf21-4066-a50a-54b8f6465874','trainerArrangeMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('4deadd2e-8e20-4e0f-97fe-fd0208cc23f4','studentReservationMsg21','00695ac0-9d20-4509-aa57-d86532a81250'),('4e127df3-2aff-4dc0-a82f-7227cf53c2b5','personalMsgGl07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4e4d64f9-ecb9-46ed-bb55-f02033b564b8','examinationjhMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4e55baaf-f06a-4077-8f8d-d354b4b13827','trainerArrangeMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('4e63077c-fd71-4c9f-96fa-1374848456e2','examinationSub3Msg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4e6ae597-3015-4b02-a550-bffc6be449e2','trainerMangerMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('4ec86849-5995-474a-98a1-8392f4ae4936','examinationSub3Msg02','00695ac0-9d20-4509-aa57-d86532a81250'),('4edf2901-a3ad-4268-b28b-a37c15df7bde','examinationSub1Msg07','00695ac0-9d20-4509-aa57-d86532a81250'),('4ee7ca4a-226b-4d25-95d7-3acdef5782f2','bugeditym','0'),('4f7d0303-436a-48fe-a563-4ae9b99fc6c0','commonMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('4f8b334a-c2b1-4863-87d8-835abe6b1c51','studentMsg005','00695ac0-9d20-4509-aa57-d86532a81250'),('50228a17-949c-48b7-8685-898cd55e4319','classMangerMsg02','0'),('504b10c3-b104-418b-b038-54eaaa08715a','driverLicenseMsg06','0'),('508c3cba-2805-4ff9-81c0-e858fbc37a1e','studentMangerMsg08','0'),('50c1cc5a-3900-4d8b-adef-856feed0d168','examinationjhMsg13','00695ac0-9d20-4509-aa57-d86532a81250'),('50d69e42-46fd-4f10-87e7-48d584bcef2b','studentTimeCount10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('515ff0ba-f5ef-46f4-a523-044c8124f499','examinationSub3Msg09','00695ac0-9d20-4509-aa57-d86532a81250'),('519b2aeb-bf77-402a-b507-c656a27d57c5','quickSchoolMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('519dca35-96c5-4af6-a125-6c3782cadb64','studentMsgMngAddPage','00695ac0-9d20-4509-aa57-d86532a81250'),('51c279a4-c849-48d5-b200-5a36db97a73e','yhadd','00695ac0-9d20-4509-aa57-d86532a81250'),('520b6b03-0211-4973-9a18-af3082517a15','examinationSub4Msg06','0'),('5228f391-f18a-4f51-9fca-aca05eef4f7c','cdglym','0'),('524f3317-3224-4980-bc20-60459a17227e','trainerArrangeMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('5262b632-e600-497b-a4e5-4df8f53770f5','carMangerMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5266f242-9351-45b9-8f74-488ae2552931','examinationSub4Msg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5269879d-2ebb-4624-8a76-6a4525eb1467','trainerReservationMsg06','0'),('531caa79-109d-468d-8e30-3d50ac75daf9','organizationMsg01','0'),('531e2abc-fb5a-4e5f-9d1c-0babc37643a2','jscx','0'),('53a80900-328d-45b1-8237-92b7cb9ff7f7','studentTimeCount08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('53b8b15f-5c15-4547-bbf8-919df6638cf6','studentTimeMsg01','0'),('53d04c1a-7710-471b-893c-cf6391f7076b','classMsg','0'),('542e2859-f008-47a3-bbae-7939a64e1f51','driverLicenseMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('54a1542f-04d4-4436-acec-e69b72b87940','yhedit','0'),('54fadb86-9d4f-4e47-8b93-05ac8408fb48','personalMsgGl05','00695ac0-9d20-4509-aa57-d86532a81250'),('55666cf6-2b2e-495a-9d3a-e0ea98bb8e20','examinationSub1Msg04','0'),('5586209d-a3b6-4065-8041-7d8b815df8e3','examinationjhMsg17','0'),('55a12c9c-cb32-4494-bffa-8211ad166057','examinationjhMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('55c9df5c-29df-451b-98f2-6c3ca0be2eda','studentTimeCount11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5647881c-dd94-4eb8-8cb0-4147bf0292e6','driverLicenseMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('56571d7f-9df0-45ac-a472-55faaebcc477','studentMsg001','00695ac0-9d20-4509-aa57-d86532a81250'),('576e5ef1-0f7b-4225-956f-eb6556d13b06','studentMangerMsg','0'),('5849b1ce-8fb2-4182-9b61-bc63824a7d37','examinationSub1Msg01','0'),('5886a771-9012-4699-9713-571391d616ba','studentMangerMsg25','0'),('592a5c19-e69e-4d16-8db6-33dc25e72571','classMangerMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('595e36d9-8d91-4112-9652-7c32170e54dc','jsedit','00695ac0-9d20-4509-aa57-d86532a81250'),('59bc4115-3f62-4a7f-ad6f-ba101265238f','examinationSub1Msg02','00695ac0-9d20-4509-aa57-d86532a81250'),('5ac50d68-689a-43ca-b8a5-bc6d6c2c748d','trainerCenterMsg12','0'),('5ac7e8ba-79f8-443c-9265-2e3f907c4290','studentfileMsgAuth03','00695ac0-9d20-4509-aa57-d86532a81250'),('5aecfd75-f002-4b3d-815f-854bade32a56','examinationSub3Msg03','0'),('5b0f52df-8095-4c76-930b-b6cc78df2565','studentMsgmy','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5b4f09a2-10a6-42cc-a745-50c1d1d8b513','examinationMsg','0'),('5bd6002d-482d-4cf5-b87a-8a55cf8d7643','classMangerMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5be11afb-be48-4327-a4a2-3ccafad97ad4','qxglym','0'),('5bfc5e5a-48b7-41f5-a3c6-a5f896fdfac6','examinationMsgSub2','0'),('5c7bdb9f-28a8-4a4e-bd53-0d492b817269','examinationjhMsg21','00695ac0-9d20-4509-aa57-d86532a81250'),('5c8494db-5e24-4ff6-a775-5dbae8a2dcf3','examinationMsgSub3','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5d0b54b2-f4b9-4409-a2e9-533667027cf6','examinationSub3Msg05','00695ac0-9d20-4509-aa57-d86532a81250'),('5d14f4c5-e438-43d4-ad32-15cbe04cda8b','trainerReservationMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5d578139-4166-468b-bf7d-c5fdda0f962f','examinationSub1Msg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5d5a4f8a-1e1e-4ac6-a9b8-2efb50e3c37f','examinationSub2Msg04','00695ac0-9d20-4509-aa57-d86532a81250'),('5e29ad0d-b5b4-46ba-9ec7-505081937548','studentMsgMngMain','0'),('5e77362a-9245-42e4-9ecb-889c3c205116','wechatDiyMenuMsg02','0'),('5e91f569-f9b5-4feb-b081-5a9b1ef50f11','examinationSub2Msg07','0'),('5ebf0714-bfec-4b10-a7b5-abc155718df6','examinationjhMsg04','0'),('5fa086d9-93be-4b7f-b43e-4cb55b1ae423','trainerCenterMsg07','0'),('5fc349fa-a6cb-4bad-b492-193b2fa907b0','examinationSub3Msg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('5ff9dd1c-c576-4787-8118-11110e469a6c','examinationjhMsg08','0'),('5ffeb62f-a0c0-4400-82b4-ccfbc6afb166','jsedit','0'),('60252a29-b9dd-4868-8628-c7a6fe5b897b','studentMsg003','00695ac0-9d20-4509-aa57-d86532a81250'),('603888f4-b342-41a1-a404-2802f10b3f87','commentStudentMsg07','0'),('60a4f64a-28da-4aec-9426-216bfa80f5d2','driverLicenseMsg11','0'),('60dac19e-548a-44b9-ace0-f8aa051ece39','yhgl','0'),('61306afe-c139-4d58-920c-31eab0d07662','examinationSub4Msg04','00695ac0-9d20-4509-aa57-d86532a81250'),('619f984d-092f-4162-a0f8-9582734f5faa','carMangerMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('61e37334-ebaa-4842-8747-4b4636b932de','commonMsg13','0'),('622b71a7-e4fd-4290-ae1d-653fd1bc5115','commonMsg05','0'),('6260123e-f3ea-47e1-b33d-b16bcc0305fd','studentMsgMngAdd','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6263762f-f2c1-4c75-9ce0-cc8b8c27ac12','carUsingMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('627ccef1-8ae5-43ab-b25b-99010f5d8f8a','qxgl','0'),('6307e3e0-67ba-489f-9ce9-1c1d82cfdc49','examinationjhMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('634f6197-ed16-49af-8f03-ec731307b375','commentStudentMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('6352e0cf-372f-458b-b284-4434af7bc55f','examinationSub4Msg06','00695ac0-9d20-4509-aa57-d86532a81250'),('638ebefb-50b0-45c6-97d8-eb26895524b7','studentfileMsgAuth02','00695ac0-9d20-4509-aa57-d86532a81250'),('64a2d2de-fe62-442e-a74c-56646113d704','commonMsg13','00695ac0-9d20-4509-aa57-d86532a81250'),('6520ece4-4acb-49ff-96d8-9c771068ed97','cddelete','0'),('653762a0-52e0-4cad-ba2e-2e5cb555ea03','quickSchoolMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('653dfe13-62d9-4ba4-b9fd-9f7b1e13b28a','examinationSub3Msg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('656303bb-dd81-43d8-a3d8-17f86a773ddc','classMangerMsg03','0'),('663e06a4-dd3d-4aa2-a038-1058b87f7b93','commentStudentMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('667a4804-2161-4544-9998-9e4381fc1f35','examinationjhMsg22','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('668c222f-2fd2-486d-b6af-570bfab0614f','qualiLicenseMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('66b08f3f-43c1-4252-b63f-e2227dc1f72a','qualiLicenseMsg02','0'),('66dee843-3acf-4778-a259-39aa8b2d7b91','carUsingMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('66ed674b-343c-497c-957d-0502ca084938','wechatHardReverseMsg','0'),('672635fe-1f7d-4c33-a6c1-9067a03bab18','studentMsg002','00695ac0-9d20-4509-aa57-d86532a81250'),('673e7b44-65e1-4816-a117-03eb5e744751','studentTimeCount06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6744e945-afb1-4074-9576-7cb85a1300e6','examinationSub1Msg03','0'),('67bef527-516d-423d-8a32-7a51a3119427','quickSchoolMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('67c005e3-0316-4a5f-8d65-4850897dbdd6','classMangerMsg01','0'),('67c838b1-a593-42e4-985f-557468b9621b','maxIdListMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6832fc47-742a-4ee5-8e9d-554d68224365','examinationSub1Msg03','00695ac0-9d20-4509-aa57-d86532a81250'),('68408bfe-30ff-4242-8c7c-39711ef3286e','commentStudentMsg05','0'),('68565dab-e794-4819-a22a-808d80e6ac79','classMangerMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('68791fee-4d06-42f7-ba3f-c37b001262bf','commonMsg03','0'),('688e2fb6-cffb-4e9a-9f4f-4a827b729544','studentReservationMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('698f675e-e101-49fe-a437-4c95ced0c4e1','personalMsgGl06','0'),('69cbd485-3899-40ba-9b67-66ce15d4b390','studentTimeCount05','0'),('69e45e0a-6468-446b-85e2-1204f7e0381c','trainerMangerMsg05','0'),('6a0628bf-a883-481b-92ee-e9a33ce46ca9','driverLicenseMsg10','0'),('6a5b18ab-5d5f-4f4f-9586-ee5b99d9ce4c','studentTimeCount','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6a668710-fe7a-410f-aac2-391a065ce63d','subjectTimeArrageMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6ab421d5-ff3a-4f79-ba6d-d07570457c6e','trainerArrangeMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6adb3080-ca09-4c9a-a67e-3ccd73697af5','examinationSub4Msg07','0'),('6af7682f-8ae3-4985-a43c-dd9f14d8e0ef','examinationSub2Msg01','00695ac0-9d20-4509-aa57-d86532a81250'),('6b0d0a63-467f-4e20-97fe-b2ef986438d8','quickSchoolMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('6b1a5bf5-592c-4445-b2ae-dae9c2834b01','examinationSub5Msg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6b1d50fb-44d3-492c-a16c-008e41a1e0d2','qualiLicenseMsg10','0'),('6b254186-4837-444a-a4ee-8f0b85e7484c','examinationjhMsg22','0'),('6b7b2730-e721-4d47-bf07-024e82434195','examinationjhMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6bb0865a-fc16-4435-83d1-be9527331d0e','studentMangerMsg27','0'),('6c01c071-3e1c-4476-98b9-89a890aec974','trainerArrangeMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6c22917a-9c0d-47bc-99dc-df76674d641d','yhaddym','0'),('6c2a907d-fc34-432e-82c6-3dbd941abdc2','examinationjhMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6c3123ec-314f-42f8-a22d-e1d735f3db43','commonMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('6c31e190-d01b-46c3-8794-4a7d3a0419d7','examinationjhMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('6c6beb54-3c87-4b96-82ea-a268b08f3500','studentMsg009','00695ac0-9d20-4509-aa57-d86532a81250'),('6c7914bf-b3ee-496b-9afe-0d976bb6564a','commonMsg18','0'),('6ca6170d-ca51-4f81-95b1-0d092a5ee476','studentMsg011','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6cb34fbe-5c3d-4071-863c-e55d77e34d22','wechatDiyMenuMsg06','0'),('6cd009fb-0b46-4260-80a0-c76814ec85ae','studentReservationMsg06','0'),('6cdc3b54-7953-4130-83f5-c274914b4ac1','yhadd','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6d85f310-715b-4a8c-b99e-e4b0ad62b354','studentReservationMsg17','0'),('6df5e4f5-9695-4012-8a0b-9f55a632e1b7','studentfileMsgAuth04','0'),('6e438d88-93e3-454d-a952-d9dc02ee00a8','quickSchoolMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6e74cc12-1d07-46b8-b427-c13d285bc372','examinationSub3Msg09','0'),('6e8dbf1f-e777-4652-9450-e4e8cefd0321','qualiLicenseMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6e93fc80-3ae1-41b3-863d-d2263e15083e','examinationjhMsg15','0'),('6ea8b99e-890c-49e6-86d8-e35b1b98bdef','studentTimeCount06','00695ac0-9d20-4509-aa57-d86532a81250'),('6ebfa391-8525-484e-8ba4-9aeaf89897e0','driverLicenseMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('6ec1f66a-0801-4ac8-9575-c03c2766e967','carUsingMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6ee7741d-6468-4407-9664-75304a02ea8c','driverLicenseMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6eec8dad-3065-4169-ab1a-08ef7c92a148','trainerMangerMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6f1b7014-46f1-4410-bd87-6e3c080bf82d','studentfileMsgAuth03','0'),('6f4d2b85-1fd1-40dc-a88d-8a71f496fb8b','studentMsg006','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6f6ef772-17cc-4030-af96-fd15189a07f9','studentReservationMsg18','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('6ff99f39-99fe-4a91-b2b4-8924bf05a99c','examinationjhMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('6ffd227f-c296-44a9-b778-308caf4d30d4','studentReservationMsg14','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('702695e6-b96a-46c2-b396-0a931c71afb2','commonMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7060bf9a-b947-41bd-9c1a-edd323f50754','driverLicenseMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('70a4463b-9fef-403d-8131-7b4fc8c5bcdb','subjectTimeArrageMsg','0'),('725de990-2032-4e6e-a08c-8f3a28762300','examinationSub1Msg05','0'),('72851428-3bfb-4df0-908e-77a0c41b93ca','studentMsg010','0'),('729b5b92-abcb-4ae9-aff5-8bb9f1481b9c','qxadd','0'),('72f0e7bf-08d3-4187-88c5-171812e0a660','examinationSub5Msg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7314563c-2add-434a-8fcc-881fc7ba8d58','quickSchoolMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('73539805-f7f0-445a-97fe-f82f11d1b038','examinationjhMsg12','0'),('7388d45d-497d-4e71-a3d5-d662cff1826c','studentMangerMsg15','0'),('739b34e0-725e-4e39-937f-7e852dbb37e1','carUsingMsg03','0'),('73ee29a7-37b2-4262-b387-1c52b1be7dc9','driverLicenseMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7443ec0b-ccc2-4ddb-aa03-9abe60bcb254','wechatUserMsg05','0'),('7497dc24-5b7f-43f9-8528-5981f5b55b60','examinationjhMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('759d5019-913f-4c4e-9194-51608ec08778','examinationSub2Msg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('759f16d1-9f1a-4229-81d8-1ff1a3a337fb','yhxgjsym','0'),('75fc2138-6183-456f-8fdc-0b9d219a00dc','commonMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('769a421f-6557-4a10-8d7e-375d31e72cde','examinationSub5Msg09','00695ac0-9d20-4509-aa57-d86532a81250'),('76d9e8b6-c04d-4ea4-a5c3-78b95eb30d68','examinationSub5Msg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('76e20da9-e37a-4ac7-83b8-5211fde8e946','examinationSub5Msg04','0'),('76e42a17-be40-41d5-8110-d0394d4cf9db','studentMsg012','0'),('772abd2a-e5bd-4bc8-933d-d3fe598ea65f','examinationSub4Msg01','00695ac0-9d20-4509-aa57-d86532a81250'),('77390bf7-430c-4ed1-baea-5799b73fde07','trainerMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('777d4748-4594-488e-904f-260769403d4a','studentMsgmy','0'),('7781ae26-9ad9-41ce-b630-978023cef765','examinationSub3Msg06','0'),('77930858-622b-48a3-8acc-3d421732eacc','bugedit','0'),('78b54c80-a0d3-4a5c-b0bc-f68c45d15838','quickSchoolMsg04','0'),('78f6345c-6384-4a5f-a646-c79561a84799','commentStudentMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('795ab8cb-a8fe-47dd-828b-6f447995d592','subjectTimeArrageMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('79a100fa-18fb-4228-a9b0-a4909b549804','studentReservationMsg02','0'),('7a064a67-0bb5-46d0-ba08-fb2859f3c003','personalMsgGl04','00695ac0-9d20-4509-aa57-d86532a81250'),('7a549786-a03b-491f-b5c3-9cc83215021c','commentStudentMsg04','0'),('7a6020a2-00e3-4c6d-9da7-541477433688','cdgl','0'),('7af7f0b0-0858-4713-8961-91bd156b1e23','jsglym','00695ac0-9d20-4509-aa57-d86532a81250'),('7b169fbe-eb5b-42ac-9d81-4fdf39592cdf','wechatDiyMenuMsg','0'),('7baf21cf-1b11-4ecc-95bc-eacf8681d196','studentTimeCount03','00695ac0-9d20-4509-aa57-d86532a81250'),('7c03a278-b5d2-48f3-a75f-7a9b1dbab320','carUsingMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7c355972-8ee7-423a-863a-86b9592355a6','studentMangerMsg18','0'),('7c375ce4-b9d8-4228-a711-7510e594c536','studentTimeCount09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7cb790bc-e0a2-4c7a-8dfe-517c66d44bec','examinationjhMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7cb8fdff-eb9c-43c2-9811-88ed6ba1e48d','carUsingMsg','0'),('7d3c7b51-8375-491f-8704-da3ebcba3780','carMangerMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7d60d974-c090-4586-b406-92d27498c486','wechatSingupMsg','0'),('7d8966da-9f60-4f22-8ef1-3e43a3ab2026','personalMsgGl07','00695ac0-9d20-4509-aa57-d86532a81250'),('7db83a30-115b-465a-88a0-c13a2e809309','examinationSub2Msg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7deccf30-c584-4553-9951-9f3f57927673','trainerArrangeMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('7e09eac2-fdb0-49e9-9aec-15f72c4f798f','jsdelete','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7e0c7815-1041-454d-bfd0-de933df90c86','commonMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('7e2ae797-371c-4a22-9b66-8f09f2a0b2b6','quickSchoolMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7ea5745f-dabc-4dff-83ec-f7ce60280a11','examinationSub2Msg01','0'),('7ec233bd-890d-4843-b128-47658f0bb9bc','studentTimeCount04','0'),('7ee305fe-8639-4165-9f89-a9697102b2d5','qualiLicenseMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7f086faf-aaea-46bc-8f43-038614403332','examinationSub3Msg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7f7905fc-93ce-42a4-ab3a-e4b260741a57','examinationjhMsg07','0'),('7fae8568-add8-4785-a8d7-fc28683bf89f','commonMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('7fce4782-81af-4a99-b6d5-917256cd00d1','trainerCenterMsg08','0'),('8042d75b-ea6f-43cf-9a10-92da1c86e29e','driverLicenseMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('8082254b-109e-4007-8fb1-de39ca0c9a1d','studentTimeCount07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('8097ba9c-9f44-4940-9a1d-77b62f772b68','jsgl','00695ac0-9d20-4509-aa57-d86532a81250'),('80d58d67-996b-4733-9851-916e3dbbc381','jsglym','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('80fd13f2-2590-428b-8ae9-5011b65235d0','examinationjhMsg16','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('812a2a56-ebaf-45c2-b640-404c02c4bf3d','commonMsg15','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('819b8c3f-da84-4c77-b92a-edd4b40e3e28','examinationjhMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('81b98f6d-5170-4bd8-9c1d-65ce9880f33d','commonMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('81ba0265-3f56-459b-982b-1436f8019a3f','quickSchoolMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('81ee23b3-7458-4d4d-aa66-8871c1c9049c','studentfileMsgAuth01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('820696c7-197d-47c6-ad0d-7f85c9f635d1','trainerArrangeMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('827dfb53-8333-4875-a3ea-a8c5932d62a2','trainerArrangeMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('82b6db5f-ac32-4ea2-98a1-59637bdc3fd8','studentReservationMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('834bae91-af09-49fd-8854-c3995307210c','quickSchoolMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('83575e22-32aa-42d7-b090-66e60cae6525','jsdelete','00695ac0-9d20-4509-aa57-d86532a81250'),('836e41f1-c4f6-48d8-96e4-c8b5b2620ccc','jseditym','00695ac0-9d20-4509-aa57-d86532a81250'),('8393a8dc-19f2-473d-8fdd-4919e3c23ac3','driverLicenseMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('8441d4e0-0cd1-41f9-bca5-da9a9700ec17','qualiLicenseMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('84521c18-3b32-4fee-aa4e-e436a0023448','studentReservationMsg05','0'),('8479045c-4468-48a7-8a86-7df9abbfee84','examinationjhMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('8539d14c-af74-4fb9-998d-378c57149add','commonMsg12','00695ac0-9d20-4509-aa57-d86532a81250'),('856d16d4-369f-4c53-8013-a32532ce271e','carMangerMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('85d3a4c2-13a4-4f39-bd6a-3bb14e9d74cb','examinationSub2Msg08','0'),('8670ce4c-77e5-4211-8419-1af513d54bb0','commonMsg17','0'),('86afad5a-6746-4668-9d1f-e37a966b6649','examinationjhMsg19','0'),('86bf091c-3bac-4d9b-9924-b6fc4a139c99','trainerCenterMsg05','0'),('87de182f-fbce-4693-860d-91a461337b21','examinationSub5Msg05','00695ac0-9d20-4509-aa57-d86532a81250'),('8820578b-c72a-4e9a-9de0-132f1495b441','trainerMangerMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('882243fd-e5f1-4d72-8a82-c8eb4d494cc8','studentTimeMsg02','0'),('8893740b-39ad-4113-9839-e2a8a72cf0cc','studentMsg13','00695ac0-9d20-4509-aa57-d86532a81250'),('88d4337e-2958-44e9-8199-1f2463eae075','subjectTimeArrageMsg03','0'),('8901284b-b7a2-4404-8163-5f314f2b866f','wechatDiyMenuMsg04','0'),('89096ad8-e20c-4ffd-8bef-354438addef5','cdadd','0'),('892313dd-4392-475c-b488-9411b472c8be','quickSchoolMsg03','0'),('89534c57-28d7-45af-af05-ee95675fb7a0','studentReservationMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('89664f24-7457-4d0f-849b-247ad213fdbb','studentMangerMsg02','0'),('89e307c3-8595-4fa9-a5ff-c3e40cef7e19','studentReservationMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('8a0c1832-1f38-4334-8f22-f47553078afa','studentMangerMsg31','0'),('8b13e182-17d8-4a5d-9332-5f911541b01a','studentTimeCount14','00695ac0-9d20-4509-aa57-d86532a81250'),('8b40f115-4119-4692-ac60-9f1102d0d825','studentReservationMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('8b4e3cef-65bf-4934-929b-4e7680e593ad','wechatDiyMenuMsg08','0'),('8b88a22f-a30b-49a0-ac36-4a7178a1272b','examinationSub3Msg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('8c161ec3-814f-4181-aafe-7984cba8f3a9','studentTimeCount13','00695ac0-9d20-4509-aa57-d86532a81250'),('8c3482d1-c57e-4c56-836e-9885e6e48d4b','examinationSub2Msg06','0'),('8c5f3cf4-f83f-4dea-bc2c-3d516e4f5c01','trainerReservationMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('8c86e4c3-f573-4093-b699-0c22f72100c8','studentMangerMsg30','0'),('8d301b02-1f7e-4810-a987-2a95fcc3774e','organizationMsg08','0'),('8d8da2ba-88f8-42d9-8e25-974e2d762103','classMangerMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('8ddd2e13-5fb3-4c52-9cfc-1b6aef8ef98a','commonMsg15','00695ac0-9d20-4509-aa57-d86532a81250'),('8df04184-f7b9-48a0-b9f1-4b34b6dcb68a','examinationSub1Msg07','0'),('8df71249-4930-4e65-a727-e280f1d4a920','examinationjhMsg09','0'),('8e2e967f-31c6-4411-83ff-8ff04aed003c','studentfileMsgAuth','0'),('8e4e4f76-d540-4971-9ae3-acf26deb3073','examinationjhMsg12','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('8e6ea1e9-0678-4e73-81dd-504cfaa51964','classMangerMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('8e94b80e-ea01-4ebe-96e7-dcdcae02b458','carUsingMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('8f6c667b-4f37-4a8d-99eb-16b97a421e98','examinationSub5Msg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('8fb7dc35-2397-4dc1-932d-32e88c7e31e0','studentMsg008','00695ac0-9d20-4509-aa57-d86532a81250'),('8ff4c05c-2935-4734-a4d7-7a022265023f','jsgl','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('8fffee1f-858e-4505-a66e-980b9ace574f','studentMsg003','0'),('904082cf-7ec9-42b9-9cd4-40e45cdbc144','studentfileMsgAuth02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9067441f-9ca4-4d00-82bc-0c9c8ea923ef','examinationSub4Msg09','0'),('90bdf3f8-6279-4464-9f1d-4662fda2a5c9','qualiLicenseMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('910a7b0d-e55c-41e3-b1bf-5e780c70369e','buggl','0'),('917de67a-4820-4c5e-b0c3-183c2483a435','qualiLicenseMsg07','0'),('919a5afd-fb74-461e-ab61-b0fb4631b4e2','trainerCenterMsg03','0'),('91a2af37-5ff4-4bc5-8324-ea70ab25d806','examinationjhMsg13','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('91d01094-9c02-45c6-9d24-56b6e00d5041','studentMangerMsg19','0'),('92c4f107-cab4-423d-90f2-00f8f1d076fb','studentTimeMsg04','0'),('92dddac8-4426-419f-a2c6-c12b75f43753','qualiLicenseMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9310b7e9-0e9e-48e7-9723-f0667eb7ce44','wechatSingupMsg01','0'),('9330f4cf-910c-4ef9-8229-08383eb61fd2','jsadd','00695ac0-9d20-4509-aa57-d86532a81250'),('936fdd49-b925-4145-823f-78a91fab79c0','studentMangerMsg04','0'),('93713c07-79f7-4c81-add7-108d2d930428','progressSchoolMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('939798ae-e718-4a1d-8a77-5634d6b1d215','commentStudentMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('93ba8a4a-3547-4e43-bea0-85e0bb680bc7','logMsg02','0'),('9447f1d2-6adf-4241-a1ed-f9f6799565b2','personalMsgGl02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('944e9086-6eeb-4075-9e38-20436d47775f','examinationjhMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9457b78b-f1d1-45ff-a701-45e88653f2ec','subjectTimeArrageMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('947af3d6-dfa9-4ec4-ae1e-fec0359322a7','studentfileMsgAuth05','0'),('94924696-0c62-4dba-aa90-21cade1dc4cf','trainerArrangeMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('94a98444-538e-4c6c-a623-1deafb023f0e','examinationjhMsg21','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('95084a58-a7ac-460f-a543-b832bf8644bd','carUsingMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('9554758e-298e-401b-9eea-b51d6fe40916','studentListMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('959bd0e3-8d22-4343-bc6d-5c933abd9c70','commentStudentMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('960b2098-60b8-4fef-85a6-974a7d0e4f92','examinationjhMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('96155623-8d5d-471d-b4dd-e06679c1b16c','examinationjhMsg17','00695ac0-9d20-4509-aa57-d86532a81250'),('96559422-1e3f-44db-bef1-0c4f43a53744','qualiLicenseMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('965a207f-fd40-4600-8a1f-f0f31aaea46d','wechatUserMsg01','0'),('9681aa4c-afaf-452a-936b-cbfdec621c33','studentReservationMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('96fbde5c-6bbe-4561-8a13-8ded8db7d9db','commonMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('96fcbb70-43e2-4cc6-831a-74bb90711176','examinationSub3Msg08','0'),('97216012-cd91-4b42-9a57-a8c4613f7c23','progressSchoolMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('972d6bf9-b7e8-44ca-af6b-8c457efd33af','studentMsg007','0'),('97623f54-1add-45ec-9ec1-dc0219ab3066','examinationSub1Msg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('980985a7-38b9-42aa-9a18-f6154a0456bc','commonMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('981f499d-c3e9-4da7-948f-1d9acf015087','commonMsg12','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('98641e4d-87c3-441a-a1c7-62a7bb9dada5','studentReservationMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('988a75b9-0102-410a-b5cc-2a529c971eb0','studentMangerMsg11','0'),('98dafa72-d6d1-4bc2-a114-f21da6576f64','studentListMsg','0'),('994ee82e-a910-4014-876a-9fa87e871cff','trainerMangerMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('9968d530-2410-47d4-8052-7f2656308c5e','examinationSub1Msg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('997b70c4-fde1-4a86-9360-2eabb38a353f','carUsingMsg09','0'),('99bf8079-5248-411e-9c71-45c812a0a5e3','commonMsg19','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9a88792b-0496-4248-9570-3e77a3e1ad9b','studentMsgMng','00695ac0-9d20-4509-aa57-d86532a81250'),('9acbb455-8bb9-4dcd-9eba-35d138fb7524','examinationjhMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('9acd4132-8f67-49de-b0d0-358ba6e2cf80','examinationSub3Msg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9ada1576-b422-4c72-9943-d0afb7832a7a','examinationSub4Msg07','00695ac0-9d20-4509-aa57-d86532a81250'),('9b7ecf97-a40c-4778-bdf6-130b3817e9fe','commonMsg14','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9b8e58a9-a3e5-4dd4-9434-de2aab570fd3','trainerMangerMsg02','0'),('9bfb999d-0d2e-438b-9d07-60fb8bcbd888','wechatDiyMenuMsg05','0'),('9cae6d5f-8083-45e4-9c3c-96d7262c69b8','studentMangerMsg17','0'),('9cf22ed2-da58-42bc-bba2-ff53fd1a6409','quickSchoolMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('9cf336e7-4bf1-44b5-a405-00ba651bb4dd','studentMangerMsg21','0'),('9d04030d-d8c0-404e-be42-469b5446af78','examinationSub1Msg','0'),('9da594ec-fcbd-44b4-9ac0-4931ad0f6155','examinationSub1Msg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9dbace88-f772-4eef-ba57-737746cad17b','quickSchoolMsg02','0'),('9e48d721-d90c-4e02-966f-717525f2659b','studentReservationMsg12','00695ac0-9d20-4509-aa57-d86532a81250'),('9e9ab78b-fdd0-498b-b2fe-b9deb83c5a0b','driverLicenseMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9ef81a08-7528-4b23-b321-9461cc4d12fd','examinationSub1Msg09','0'),('9f03dea9-70f0-40d2-99e2-d3933ca31cf0','studentReservationMsg13','00695ac0-9d20-4509-aa57-d86532a81250'),('9f204558-24d3-46ba-809f-40b65e76eb0c','studentfileMsgAuth01','00695ac0-9d20-4509-aa57-d86532a81250'),('9f66ce70-2c04-4e56-94c4-b524d860c50b','trainerArrangeMsg06','0'),('9fcedb03-ebf3-41ab-9a96-4ed35f7963b1','carMangerMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('9ff759c7-ca4c-43bd-857f-3b1ff9299848','trainerArrangeMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('a081e1de-a0c1-4a57-9dfc-8c443577aa9f','classMangerMsg06','0'),('a097c1ef-1f33-44da-a611-cadae75ced17','trainerMangerMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('a10488e2-ffe8-44b3-a66e-82cfa2990799','commentStudentMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a1abc6da-732a-4ba0-a3a0-5b63428e186a','qxcx','0'),('a2116b40-0cc1-4d57-b81c-2faa9581c1c8','studentTimeCount','0'),('a27b00eb-148f-460c-a1d8-1514899253f8','examinationSub2Msg08','00695ac0-9d20-4509-aa57-d86532a81250'),('a2bc3e59-9757-44d8-bb2e-1aba482b1496','trainerArrangeMsg','0'),('a2f372df-bb6b-493f-90b9-a0b08dd449d7','studentMsg010','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a2f71ee1-2ff3-4e35-b18e-b93d72c29004','examinationjhMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a3245889-c23f-4e50-9eb7-bcf4f874a6b7','carUsingMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('a327cebe-1982-4e42-9414-306dd3abe553','studentReservationMsg14','00695ac0-9d20-4509-aa57-d86532a81250'),('a3294ad4-24c9-4835-85e3-8e45e8723347','examinationSub5Msg01','0'),('a35e4a21-ef17-4140-993c-ef142d702411','examinationSub3Msg04','0'),('a381e732-6dcb-4ad3-8f3b-39707ff44fda','examinationSub4Msg02','00695ac0-9d20-4509-aa57-d86532a81250'),('a3bdac32-6555-4f25-881f-62f78548f4ee','personalMsgGl05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a3f4cf77-badd-442c-9c7c-f44505b04230','qualiLicenseMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('a45604bb-e5c4-4160-aaef-cb1b8a2db844','commonMsg13','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a45c29c6-dc66-4b86-96a9-dba41b52d904','trainerArrangeMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a473cad8-89ed-4e5b-98de-5e004cb9cce3','personalMsgGl02','00695ac0-9d20-4509-aa57-d86532a81250'),('a48e51e1-07d5-4d76-a376-168c28e121b1','carMangerMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('a4bb8d2b-b4b5-4ed0-ae54-7ccd69a241b6','examinationjhMsg15','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a4de141e-ea2f-4a76-8d61-2ca870e6fa40','studentReservationMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a4fa27a9-1ab2-4070-b782-b99660dd7d69','commonMsg09','0'),('a51adc9f-19ce-404a-82b1-e476b213dac0','examinationSub5Msg03','0'),('a54b705f-6803-40c7-be86-e57db59f2624','commonMsg07','0'),('a555ff76-3433-415d-8d0b-020b2aea5239','logMsg01','0'),('a56c4360-21db-4853-b236-4ebb5704569b','carUsingMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a592108e-f051-4a1b-8a34-cf614fe624e9','classMangerMsg04','0'),('a5cffc56-af88-43de-9356-c445a2f9d3ef','examinationSub4Msg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a5eab68e-c377-4e55-81c6-390461548f35','commonMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a63035bc-9209-4b24-b60e-09418f3168b8','examinationjhMsg21','0'),('a6f022e8-0f97-4201-a53c-622f2c899bb1','studentTimeCount07','0'),('a73e0579-7277-4bf3-9674-14ec7e0e155c','trainerArrangeMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('a796e274-7d5a-4dcc-bb6f-f7ddae011e7a','studentListMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('a7c03bea-6a18-435e-bd5d-186d5139fe08','progressSchoolMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('a7cc8647-9fba-4845-9ec1-9594662a8ce8','quickSchoolMsg07','0'),('a7db3d02-069b-4795-a1a1-7f2d87684757','yhglym','00695ac0-9d20-4509-aa57-d86532a81250'),('a7ece9ad-b400-4857-964f-cb8f6a07abca','carUsingMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a82c7798-9ed7-4d97-9da3-8f787d4b6482','examinationSub2Msg03','0'),('a8440b57-f7f4-4eb2-91ad-7f6627e8bf0e','classMangerMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('a8e1535b-00bb-4bd3-8998-835ab41e585b','trainerReservationMsg05','0'),('a8fe909e-0df1-4156-bc48-cc578ecce07f','quickSchoolMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a914bd84-3dd4-4e59-94c4-ca68749eb00a','driverLicenseMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('a9408134-974e-4de9-8707-c7683def029d','bugcx','0'),('a9c7e8b5-eddd-4b0b-a703-7b21e4245a4e','studentMsgmy','00695ac0-9d20-4509-aa57-d86532a81250'),('a9fda707-d56e-4d35-87bb-0a0b14933cec','qualiLicenseMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('aa6958ca-d23d-44c2-a4c8-f241c89c4687','trainerArrangeMsg09','0'),('aa88f26c-7342-48e7-a2e8-56b338515a48','progressSchoolMsg','0'),('aa9e958e-50dc-4358-9d88-ab71fb2f892e','studentReservationMsg','0'),('aad0df3d-c6f3-49b8-ad20-d66556b28815','examinationSub4Msg05','00695ac0-9d20-4509-aa57-d86532a81250'),('ab69ed50-311d-42ca-ae2f-31479110216b','examinationjhMsg02','0'),('abe173b8-c25f-4977-afe4-e75c38493ff3','personalMsgGl08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('abf7e9ca-f88e-4556-8814-c34acac8d1ca','examinationSub5Msg01','00695ac0-9d20-4509-aa57-d86532a81250'),('abfe2a26-3bc9-42df-9cb4-034a28b1ce7e','studentReservationMsg08','0'),('ac21caca-6323-42b8-bcd1-e8c0022a316e','studentMangerMsg06','0'),('ac22422e-75f2-4a0c-b5ca-b9a0c6f61708','carMangerMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('ac23f375-c64e-435f-b70c-f25306367386','progressSchoolMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ac747115-2b72-40b1-a4a1-f07b4285e0d6','examinationSub5Msg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ac85648d-48ed-44c4-8a6d-f39e68e8c5c5','studentMsgMngAddPage','0'),('aca46ab3-ba82-4610-9b27-590018556b9d','studentTimeCount03','0'),('acbdf04b-66d8-406e-bf0a-ccfb63dfc87d','driverLicenseMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('acc5c958-b408-45cc-845e-b56849c9d604','bugadd','0'),('acd2907e-e88d-40c8-9557-4973d1f36db5','trainerCenterMsg14','0'),('ad1e7c52-3edd-4b5c-9da8-37c6512b8c64','studentReservationMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ad4e5008-2b10-4102-8a80-93c32b5427fb','studentTimeCount01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ad7c696e-27b5-469e-89f4-b32e87043d64','netMsg03','0'),('adc67697-c81a-4bbc-b309-7e765cfe7a6b','trainerReservationMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('adfb2b02-f55b-4231-9f65-14115f64b1c0','qualiLicenseMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('ae7e15f9-744f-40e8-844d-a5dea40fe967','examinationSub4Msg08','00695ac0-9d20-4509-aa57-d86532a81250'),('aec2b388-32d4-471b-86da-2caa71c6f69c','studentTimeCount04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('aecf647b-75a1-4d2c-9e12-a51014db2090','examinationjhMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('af586ccc-87e1-4496-a903-a03e603357b4','examinationSub4Msg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('afab4822-f90a-46c2-b752-84bdf39a90f9','quickSchoolMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('afcac6a0-2a8a-4fba-8da8-7b3afd22c2fe','examinationSub2Msg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('affe8e33-8855-4116-b0be-90fd7dba1c61','fileMsgAuth','0'),('b0d3d2c5-6aa6-4649-8b89-79e724189126','trainerArrangeMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b17d4073-5a38-4c72-ae73-cb907804dfca','carMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b18ce619-0ec4-45f1-a7bf-2982af87b0fb','examinationMsgSub5','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b210aeab-c395-4a6f-aa9d-95003cb48e9c','trainerArrangeMsg03','0'),('b2b4e6b2-2530-49a9-b377-ad4afa1705ee','quickSchoolMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b33d0b39-2b6f-4d25-9759-90419e68439d','quickSchoolMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('b340c3f4-46cc-4148-bdff-0f5de28fbcc7','examinationSub2Msg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b388a620-3b16-49aa-b7a0-937d9a96c974','commonMsg17','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b43907c2-b215-4052-b6b4-70b0f12497a3','studentReservationMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b4621524-4e39-4462-b92d-8a7112e026c3','jsaddym','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b474a4cf-8dc9-4dd2-b264-7b7d93295c8d','commonMsg18','00695ac0-9d20-4509-aa57-d86532a81250'),('b494037b-1b5f-4549-b549-e818c82eebe6','examinationSub4Msg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b4d72aa8-9bbe-48d0-9e94-a496cba74d02','commonMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('b4d81688-0049-4895-81ef-436d69bb1f46','studentReservationMsg20','00695ac0-9d20-4509-aa57-d86532a81250'),('b4fc5c61-7481-4153-ac6c-ce7af01e3bbb','trainerReservationMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('b51a7951-c4a3-42f3-b5f0-4e33468db1d1','studentTimeMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b521dcc2-44b6-4d25-9a82-f73057dd0d96','examinationSub2Msg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b5ad3f8c-4b4a-4a6d-b8d0-e45b80fa2fec','examinationjhMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('b6081fad-d928-4cec-964c-bbc649c44f66','studentTimeCount12','0'),('b6807524-3ca6-4dff-9d37-99d65d954c4d','examinationjhMsg11','0'),('b69d644e-ca53-44fd-bacf-12b8ea36efcb','carMangerMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('b700a018-e620-4730-8626-7e9a787a2baa','studentMsg001','0'),('b715b9c2-bfd2-4180-b2eb-9ce07dec1032','qualiLicenseMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b763b33a-57d4-4c52-8b3f-a4b696c717c7','trainerCenterMsg01','0'),('b77f23df-b3f4-4d3c-be47-40b996692232','carMangerMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b794883b-142e-416e-acb4-172e2677f161','trainerMangerMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b79e7f30-7135-472c-bde3-8c253311586a','carMangerMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b7c8cbc0-a1e3-4969-bbcd-4b5a49823878','personalMsgGl','00695ac0-9d20-4509-aa57-d86532a81250'),('b8362be1-9506-4c40-bb59-856d0ce86459','quickSchoolMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('b8c8441b-75cf-4ff2-a0f3-99564cb40ebe','examinationSub3Msg02','0'),('b960aa0a-b024-44a7-bbde-737140b5a6a1','classMangerMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('b978e548-208a-4117-81dd-d5946d3bd159','carUsingMsg08','0'),('b9afed75-5811-49ed-aada-7587588f10c8','studentTimeCount','00695ac0-9d20-4509-aa57-d86532a81250'),('ba4a094c-5044-409b-9789-e36cedf8446e','qualiLicenseMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('ba6aa324-72b7-4489-8e12-28f5975ecac2','studentTimeCount12','00695ac0-9d20-4509-aa57-d86532a81250'),('bab67c4f-619c-4d1e-976c-1e9d14596bdf','wechatMsg','0'),('bb2b2c13-50eb-4821-a4a8-969a0bbe8272','personalMsgGl08','00695ac0-9d20-4509-aa57-d86532a81250'),('bb815479-3432-4cd9-9ebd-96d23b0b05d5','examinationjhMsg20','0'),('bbaddbe5-edf9-4af6-ad41-09ab6fadb68d','qualiLicenseMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('bbd03400-106d-441f-9052-ae075549c9f3','studentTimeCount09','00695ac0-9d20-4509-aa57-d86532a81250'),('bbf675de-9753-4c84-9a89-25dabe6ca235','jsglym','0'),('bc243b06-a0a8-4dd5-97aa-c913e1961ec7','studentTimeMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('bc3d0687-55b3-4f10-a2d0-0de906a1b08a','examinationSub1Msg04','00695ac0-9d20-4509-aa57-d86532a81250'),('bc3d19ac-f1c7-4f22-94b7-1f63d55e6709','classMangerMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('bc5832eb-7add-4b62-a63b-7d48e5c53aa3','commentStudentMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('bcafb21b-6a09-4100-9301-45f74a64dca1','trainerArrangeMsg08','0'),('bcc98a85-31a0-4222-a70e-f9155a17a706','examinationSub4Msg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('bcedf2f2-052a-4e70-aac2-84909026de7f','studentTimeCount08','0'),('bd2a9c86-10a1-4020-8441-d6c9faf60a77','carUsingMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('bd8b100c-a272-4a22-b2bb-987ff98ce3c3','carUsingMsg01','0'),('bdf5345a-b39b-4c2a-b849-689af6eff00f','driverLicenseMsg','0'),('be141b4c-04bd-47fc-bb8c-600a6f8f566b','examinationMsgSub5','00695ac0-9d20-4509-aa57-d86532a81250'),('be45a965-34c6-4d65-b657-7795aa8ab2fd','personalMsgGl04','0'),('be734bb7-5822-4518-bf35-869fb1460bc8','examinationSub3Msg01','00695ac0-9d20-4509-aa57-d86532a81250'),('bec72d61-04dd-4ca7-a18e-010922b874c9','qualiLicenseMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('bee74654-08cb-4096-ab20-a8308add23e5','classMangerMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('bef82b4c-78cd-4f4e-8f93-20dac414ac87','driverLicenseMsg04','0'),('bf0e3403-72a4-4e86-a6b2-eae7744caec3','studentTimeCount12','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('bf2ece3c-cb4f-49fe-9508-2bf03ba10157','studentMsg008','0'),('c0079904-fe67-41d8-96ed-2e89c8c34df9','qxedit','0'),('c0206b08-349f-4f4c-8762-92e161ce389f','wechatUserMsg03','0'),('c0592b1a-6924-4400-a540-0e2906c7740b','qualiLicenseMsg03','0'),('c0737eed-7a51-46e2-9bc1-b6b355659d59','trainerArrangeMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c0992def-3d79-4f7d-8303-df4a52ac5e99','examinationSub5Msg03','00695ac0-9d20-4509-aa57-d86532a81250'),('c0bacc0e-4e50-44a0-9931-049596e3e463','studentMsg005','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c0d2825d-ec1f-4a1a-8faf-313998708d74','driverLicenseMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('c1115be7-e178-41ba-9ae9-4fa35db830d8','trainerReservationMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c11fb595-e772-4226-ba0c-797bdedb6b0f','trainerCenterMsg','0'),('c129e2c8-a5a7-49c2-aabf-ae1cfa12ae2d','examinationSub5Msg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c13c1b85-4d6d-44d2-829e-f8549fbf7240','driverLicenseMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c15cb769-d0bd-4248-8808-b2e5cbceecd2','classMangerMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c186ba3d-e802-41c3-8df8-b9db005ee5d9','netMsg','0'),('c1a3b851-f114-4f04-98b7-8653e47346e7','studentMsg011','00695ac0-9d20-4509-aa57-d86532a81250'),('c1bb8ae6-5e21-4d03-81ea-d54eaf2e0b46','qualiLicenseMsg04','0'),('c1f2a4df-5811-4f22-8a97-4c0b3f818c9f','carMangerMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c20f4951-1163-41d0-9364-ec94a27e5c0e','studentTimeCount07','00695ac0-9d20-4509-aa57-d86532a81250'),('c265b1d3-fd18-4740-9c83-1f5a3ddccc56','studentReservationMsg19','0'),('c29f0e63-84c7-4f30-98c6-ff08935bd0d5','commonMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c2e0f189-b023-4459-9147-9db231707a29','studentReservationMsg19','00695ac0-9d20-4509-aa57-d86532a81250'),('c2fcdc60-9bb5-40fa-a034-580d2989ded2','studentTimeMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c31793d0-41a0-45fd-8f2e-426386fb1b53','examinationjhMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c329a35e-8a64-472e-991f-e42c243a27d1','personalMsgGl04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c33677ea-f432-4bc5-a68c-c39dd011dc7e','qualiLicenseMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c33c158e-07a3-41da-be00-5da3d7c64b94','progressSchoolMsg02','0'),('c3d4b304-61f9-4476-9834-08dd5c7fdff2','jsedit','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c4df1ade-312b-4a62-8faa-c595ccac998a','trainerArrangeMsg09','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c5209996-488a-4c24-8014-192f746efdff','qualiLicenseMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('c5d7f6fa-641f-4cc7-a1d7-334908152b43','examinationSub4Msg08','0'),('c608c148-4d2b-4857-827a-ed5478cecb0d','netMsg05','0'),('c6279838-05ca-4a5f-aa0c-95294ea13399','studentMsgMngAdd','00695ac0-9d20-4509-aa57-d86532a81250'),('c6a44115-4f0b-4c6a-a620-121468563474','jsgl','0'),('c6f0c514-d16f-460c-893b-367672838e07','studentMsgMngMain','00695ac0-9d20-4509-aa57-d86532a81250'),('c78976b4-096a-4856-b85d-7aeb61132a90','studentMsgMngMain','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c7a5e84c-531d-4ba8-9665-897e22b556dc','commonMsg19','0'),('c7c7832b-32f1-48a1-90c1-3c448829dbf8','qualiLicenseMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('c7d20655-76ba-4694-9d05-a2baae307a83','netMsg06','0'),('c813c5bd-d4cb-4ffc-94dc-bb76b0279c15','studentReservationMsg13','0'),('c839d532-6888-4b93-ae7d-eabe731c0092','studentTimeCount10','00695ac0-9d20-4509-aa57-d86532a81250'),('c83b81fc-7f9a-4962-96c8-3f194c70dfb1','commentStudentMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c8833961-be19-4575-9b6c-251cc4d95183','examinationSub2Msg04','0'),('c8d9c22b-4e1d-4425-a221-838d866e331e','studentMangerMsg22','0'),('c9395834-bdd5-444a-a9cc-0eb803a47b17','quickSchoolMsg08','0'),('c95cc1f4-43df-403d-850a-e5a6a72793db','commonMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('c9a0939f-dfdc-40f6-846f-2b95505349db','examinationSub1Msg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c9aec86f-537a-4f31-b9f1-a97730d42345','studentMsg008','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('c9d4d81e-7624-4607-982d-94c2753cd581','commonMsg06','0'),('ca341263-85ec-42e3-8498-64bbdcbe4b39','studentfileMsgAuth05','00695ac0-9d20-4509-aa57-d86532a81250'),('ca608fc8-ea0a-44d4-a507-b571d27a74b4','driverLicenseMsg09','0'),('ca99877a-fdae-4bf7-b724-6f7a498bef51','trainerListMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('caacf134-3b60-4f14-83dd-c92d305d98f1','classMangerMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cad01ce6-cd62-4be0-98bc-bcc229f45ac7','studentTimeCount02','00695ac0-9d20-4509-aa57-d86532a81250'),('cb0914af-e56f-4f21-af3b-e0391b44b491','subjectTimeArrageMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cb6012d7-6417-4951-a195-fa1b46d34066','studentTimeMsg05','0'),('cb70a3dd-8200-4367-97f1-da89b7c26ef4','trainerReservationMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cb922467-7496-4e52-bb85-8cc16a44d7e0','examinationSub1Msg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cb972aa5-1d95-462b-ba77-23985423e015','commonMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('cbe1dbb5-da7b-4864-9e74-7d6ac861c6a9','jsaddym','00695ac0-9d20-4509-aa57-d86532a81250'),('cbe87388-cc2d-4e29-b70d-9ee1738e5bf8','trainerReservationMsg04','0'),('cc5da402-6468-416f-8051-d59b0d86dddb','trainerArrangeMsg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ccc4aff7-3411-402d-96ff-deb5af7c53f1','studentMangerMsg28','0'),('cd0782a7-e0ab-4b72-b5cf-8bd23e1053d4','examinationSub2Msg03','00695ac0-9d20-4509-aa57-d86532a81250'),('cd4be608-de32-42d3-a0d1-0fe855223b82','trainerMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cd60d33b-758a-4342-992f-1c908227159f','personalMsgGl05','0'),('cd98594d-255b-4be0-a572-5a8de0874f71','qualiLicenseMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cdaef8e8-4711-4e84-8ae0-0bab5e5a905b','studentReservationMsg21','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cdfa132f-48c6-4803-a307-9c7bb3bc4c30','quickSchoolMsg01','0'),('ce0ab718-9387-45a8-a606-2c44edcd929f','examinationSub1Msg08','00695ac0-9d20-4509-aa57-d86532a81250'),('ce45871d-872e-47b3-b438-9a43917859db','examinationSub4Msg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ce48d0fc-5141-4283-8cb1-bd2a045e8904','quickSchoolMsg05','0'),('cea77360-7165-411c-bd81-e0e43a84b700','trainerMangerMsg04','0'),('ceeebdd6-072d-4a63-86bf-deed4607c2e9','studentReservationMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cef6516e-b945-40a0-bc6f-4d572704fc93','quickSchoolMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cf213aa0-2be9-4025-83b2-d86810459a0b','studentfileMsgAuth','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('cf77ad94-e2c8-4e68-9359-15800dc25118','yhsc','00695ac0-9d20-4509-aa57-d86532a81250'),('cf8edd77-86d5-404c-a25d-d33a37679fbf','personalMsgGl','0'),('cffee4d1-3584-4e25-86ac-f1bb78cea6ce','carMangerMsg05','0'),('d01c3b67-101a-43e3-aec8-262ad30e2440','examinationjhMsg16','0'),('d0a7b3fe-eeaf-49b3-8a0a-80dc38d91539','examinationjhMsg17','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d0ac23bb-de30-4e6a-98b7-48d9daadb368','progressSchoolMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d1815632-f415-4cde-a115-82741962782c','studentReservationMsg07','00695ac0-9d20-4509-aa57-d86532a81250'),('d18f6ea4-5296-4a50-8da6-d5449fb7d94b','examinationSub1Msg08','0'),('d1eac1c6-a744-49e7-bc9b-d94b7336175b','examinationSub5Msg09','0'),('d1f868b4-0320-4f99-9f66-4d527899aab1','yhxgjsym','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d230c06f-4f16-45be-accd-c75351b6b14d','studentReservationMsg09','00695ac0-9d20-4509-aa57-d86532a81250'),('d2b64e78-5a3c-4ac6-b857-04b2c1f01e9a','qualiLicenseMsg11','0'),('d2c1a953-0979-4bda-8cd6-96cfc48bf618','carMangerMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d2d628c4-a69e-427a-80de-4f9d659f4ebb','yhgl','00695ac0-9d20-4509-aa57-d86532a81250'),('d3185a67-907f-4a7d-b772-03a7a47a7cb3','studentMsg006','0'),('d3b85840-9d9b-4244-8dc4-777abeaf49ed','carMangerMsg03','0'),('d3bdd335-15dd-4c98-a619-b4624d9b1b4e','trainerMangerMsg','0'),('d449df46-1d24-4e35-a516-a6095377f136','studentMsg005','0'),('d455888d-6493-4b99-b7f7-2e33310f930e','yhsc','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d4669bd0-880b-4dcd-bb56-04524cff3d00','subjectTimeArrageMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('d46f7af2-7788-470f-a43d-8c6e398a7989','personalMsgGl01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d4b6a7bd-79c7-4797-ae09-6299a3d734e4','quickSchoolMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('d4e78fdc-1850-49c5-875a-762b4bda700b','logMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('d56865d1-be01-4670-8229-e10abc1327ad','examinationjhMsg19','00695ac0-9d20-4509-aa57-d86532a81250'),('d5f0b541-163e-4357-82f8-8abd75d54e00','commonMsg04','0'),('d673801e-82c0-473b-b816-09c685fea227','bugupload','0'),('d6759d47-88ca-4ac7-87f3-040c1cb06b46','examinationjhMsg14','0'),('d67d6edb-7a42-4c9f-b1e0-fd28bc2bf1d5','carUsingMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('d6ad873b-cdf3-4954-a88c-230d72558778','trainerMangerMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('d6d2a762-77f6-4616-bbff-43755c069bad','examinationjhMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('d7579ef6-22b0-42ca-bbb9-b9c9615a26ff','yhcx','00695ac0-9d20-4509-aa57-d86532a81250'),('d7735566-9e60-449c-a08a-b7ad2e77be7f','examinationSub3Msg01','0'),('d7be67dd-1a4a-4446-bcfb-3b421c7865d0','examinationSub1Msg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d7eae908-b520-439c-9fcd-5f7087942478','studentMangerMsg05','0'),('d845faf1-da2b-4322-8505-316b1e8599b4','carMangerMsg01','0'),('d8c2b2c1-f13b-400a-8f1a-9f8cc3f8387d','examinationSub2Msg05','00695ac0-9d20-4509-aa57-d86532a81250'),('d8e8f244-3070-4952-ae2d-1464223373c9','commentStudentMsg01','0'),('d94904a9-8b5a-431e-be9c-422a09334402','subjectTimeArrageMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d956c6ea-3fda-406f-bf64-a6ee1cc2d0c8','yhaddym','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('d9bf97ac-dcef-411d-8acb-38a9b569a9ed','netMsg01','0'),('da40011b-31cc-4c59-bf5f-307e88bc49ca','studentReservationMsg16','00695ac0-9d20-4509-aa57-d86532a81250'),('da76f4fc-3aff-44fa-9ed1-2aacff757d89','examinationMsgSub2','00695ac0-9d20-4509-aa57-d86532a81250'),('da83fc95-5107-4124-b989-017078c398e0','studentReservationMsg03','0'),('da9e4030-fb94-4291-a70d-2f2c174bb1cc','examinationSub4Msg02','0'),('dace8c4f-1142-4b75-8e38-95d7dd5b0870','progressSchoolMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('db8bf8af-397b-4aab-ac3c-2b6b72f76974','commentStudentMsg06','0'),('dbaacc91-50b6-47e2-abd0-24bd8f72184e','studentReservationMsg10','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('dbde8af5-2325-49c6-9f53-dae20dc4d8f6','examinationjhMsg05','0'),('dbee7300-c357-4960-8b1e-c37ac2045cb9','studentReservationMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('dcc49600-9d28-4737-988e-c4cee257c27f','studentReservationMsg16','0'),('dcd2f77d-97e8-4568-bb1b-fb8e12c10a5f','trainerCenterMsg02','0'),('dd19630d-9f4c-47d1-ad59-fb44f2867a0b','qxeditym','0'),('dd37c65c-d147-46a7-8b15-834139a48b0b','studentReservationMsg09','0'),('dd860774-9fd0-412b-a26c-7ebd5fc5ddfe','personalMsgGl03','0'),('dd8dec46-13e7-4db0-966f-2078fad6d5d0','quickSchoolMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('ddbe70d0-2c66-4340-91c0-f69a84c358fc','driverLicenseMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('de3607cd-d94b-4f75-bcbc-b19ddda2c7b6','organizationMsg04','0'),('de8e3cc5-da03-4285-8b2d-c72bbddebe3c','driverLicenseMsg03','0'),('ded0a116-4692-450a-b075-4105da999313','examinationSub2Msg02','0'),('df115bda-adc4-48f0-bfc7-2f481e30e1b2','studentReservationMsg20','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('df12a89d-7e6a-46a7-b66f-6f8f84691d51','wechatDiyMenuMsg03','0'),('df387862-9742-411d-8f6a-7591fff95d54','organizationMsg05','0'),('df8c85ae-5afa-478d-9ff2-71f1690202eb','commonMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('df8d94b9-e71c-40be-907a-04eed15f35d1','trainerArrangeMsg05','0'),('dfbef9f7-5424-4271-8be5-a22cd97b0bc0','examinationMsgSub4','00695ac0-9d20-4509-aa57-d86532a81250'),('dfd07431-e6e4-4358-b9f7-a86197626602','trainerArrangeMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e174e64a-3a1d-4905-b441-cfd1707ebaf8','studentTimeCount14','0'),('e1a6c969-ae2b-4609-8538-d28063d5c931','yhadd','0'),('e1d11292-0d3c-463b-9351-19514132f02c','logMsg','0'),('e2a69651-bb5c-4f5c-8a23-0e940a2c79a3','examinationSub2Msg08','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e2f12411-0af2-45d4-a651-379d8881efc5','studentReservationMsg10','00695ac0-9d20-4509-aa57-d86532a81250'),('e31d3138-1077-43db-9811-9bcc502be7ae','commentStudentMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e323f939-01f3-49e6-bd39-53b6614bd7e2','studentTimeCount02','0'),('e3269a7d-5bea-4817-bb1c-2327ff3741b1','trainerMangerMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e339ad8c-c952-491b-963c-954f9c2e61e3','examinationjhMsg12','00695ac0-9d20-4509-aa57-d86532a81250'),('e36b1d1f-d6ac-49fd-867f-1077a6230f19','studentReservationMsg21','0'),('e38472b9-1cf6-4e31-b8ac-8247c10b0afc','examinationSub5Msg07','0'),('e390c426-58c9-46a5-bb64-531372506e94','qualiLicenseMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('e3dd1a5f-419c-4bcc-8340-e5829530e680','quickSchoolMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('e4a61cb5-f7cc-4eb4-b1cd-32387181ad83','classMangerMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('e4c23d03-e289-4f62-ba9e-86042aa53c4e','jsaddym','0'),('e4d24292-d991-45f0-9110-b60d218a6286','examinationjhMsg15','00695ac0-9d20-4509-aa57-d86532a81250'),('e4d63811-238c-4c83-b5a4-d68405822e3f','carUsingMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e509bc1d-e1fb-4fc6-8b05-de575c114bac','progressSchoolMsg01','0'),('e51ca146-04bd-489a-94d5-777cc551506c','organizationMsgGl','0'),('e52451d3-ebfa-47ed-a03c-892013183d5c','organizationMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e548f54e-c4c7-4134-9810-dd6da4ee3561','driverLicenseMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('e6194d82-7a2b-4e00-92c8-1794eb48b3bb','studentReservationMsg04','0'),('e654f233-84d6-4b40-82b9-ab5d66a96237','bugglym','0'),('e740498b-e3cd-4086-ab44-9ff10ed46e97','commonMsg16','0'),('e77855ad-d542-4e5f-a8b0-ad97271164e4','yhgl','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e79fdf52-98a5-4ce6-9578-ef7e79a9be4c','carMsg','0'),('e7a34cea-3652-4133-9f5b-40f7cb6cf69e','commonMsg','0'),('e7a7b641-27a9-412d-a1f0-b0677ead31fd','examinationMsgSub2','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e7ab5f41-89ab-4ddd-8031-466acbee3114','trainerMangerMsg07','0'),('e89002a3-75f2-45b9-9160-96775573bd60','studentTimeCount04','00695ac0-9d20-4509-aa57-d86532a81250'),('e8f6a463-fe21-4949-ba2a-db984e8708fe','logMsg01','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('e9a064a2-bfe8-4917-9afd-eaf66ef504ea','qxaddym','0'),('e9a99d35-b067-4ce5-95ac-38a819e68ee4','examinationSub5Msg08','0'),('e9b1334a-344d-4dab-870e-1c41c1aaf2e4','carUsingMsg10','0'),('e9b8ef09-08c7-408d-99d9-86226fe034f0','examinationMsgSub4','0'),('e9cfad63-ddb3-49e1-892c-2011520391cf','qualiLicenseMsg06','0'),('e9f7b505-a146-43f8-818c-aad245fae955','trainerReservationMsg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ea6b0837-b190-43ed-b745-c4c43b13971f','trainerMangerMsg03','0'),('eab2a45d-025f-491c-ba42-306f98829aea','trainerArrangeMsg10','0'),('eadc7c8e-e0e0-47d7-9c8f-b3a66f63f28a','trainerArrangeMsg02','0'),('eae28983-2e9b-4322-a8aa-e504fd0ce49e','studentReservationMsg15','0'),('eb1f37fc-9f0c-49f8-951d-a2ed03fa17db','studentTimeCount10','0'),('eb3c71e8-b0be-4f08-9cfc-3368adb7186d','examinationSub4Msg04','0'),('eb65229c-2553-457e-b6f3-f318b1940b23','examinationSub5Msg06','0'),('eb700355-0ee4-4c01-b03c-265aa7beddfe','examinationSub3Msg06','00695ac0-9d20-4509-aa57-d86532a81250'),('ebbff96b-1ec7-4732-b72e-e968ed7e6911','trainerArrangeMsg08','00695ac0-9d20-4509-aa57-d86532a81250'),('ebc0dce6-5c69-4010-99ca-e2c7e00c0b47','studentfileMsgAuth03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ebc5cd1e-10e3-42c3-b7e7-147e72b869ca','carUsingMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('ebd5ecdc-2a6b-409d-82e5-7100b355ec42','examinationjhMsg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ecc7d7f6-16d8-4291-9496-bb955da4fd8a','carUsingMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ece1089f-2bdd-4b98-8108-8b945ee99f34','driverLicenseMsg05','0'),('ed81efe1-e8fd-4ee0-9c08-b9f4f042ffc2','examinationjhMsg18','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('eda4522a-430c-4ad4-830e-48f19da36f7e','carUsingMsg06','0'),('ee11119f-6154-410e-9c06-3e9d1b62f579','carMangerMsg','0'),('ee6a9b96-2e4c-4b56-8100-264b90ef7242','studentfileMsgAuth02','0'),('ee70b159-66d9-4869-b64d-afa10970c946','organizationMsg11','0'),('ee83286e-0226-48d9-8f0c-6c28ae8e43e0','studentTimeMsg04','00695ac0-9d20-4509-aa57-d86532a81250'),('ee9b514b-7ab2-4bc2-8b0c-b54caddc92d5','yhxgjsym','00695ac0-9d20-4509-aa57-d86532a81250'),('eec5f038-d26b-437b-bd86-9f51de2b659a','studentTimeMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('eed5ebd1-4359-40f9-a509-6d093c87beb0','examinationSub3Msg04','00695ac0-9d20-4509-aa57-d86532a81250'),('eee6c631-1800-42f4-a01b-37e59bb14fb4','trainerArrangeMsg11','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ef55313a-b7e8-495f-bb4b-17982a292594','personalMsgGl08','0'),('ef8cbd7a-b33b-424a-822e-14dda8d9dfff','carMangerMsg01','00695ac0-9d20-4509-aa57-d86532a81250'),('ef9119aa-fe95-4655-bf80-53d2a63b2d6a','qualiLicenseMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('efb84469-5f67-470c-8055-19aa09d94e4b','cdcx','0'),('efe74564-7a2e-4659-b71a-d29100fba543','subjectTimeArrageMsg05','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f010a317-f9ff-47f0-a32d-3ebedde58c66','studentTimeCount05','00695ac0-9d20-4509-aa57-d86532a81250'),('f01e733d-014d-4042-b093-e8d6e7783a0a','trainerMangerMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f0691f78-8997-433b-aeee-77a7035a3900','personalMsgGl','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f080b19d-de21-45f7-9706-25942acc16ee','driverLicenseMsg11','00695ac0-9d20-4509-aa57-d86532a81250'),('f0bcefef-b83d-4181-880e-45e3908626b4','examinationjhMsg16','00695ac0-9d20-4509-aa57-d86532a81250'),('f10b7e31-d36f-4597-97c9-242d2136269e','studentMsgMngAdd','0'),('f10b7e35-c816-496e-a44b-e4ae1310fe59','studentMsg004','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f16e32e9-ddda-409b-9a24-5d6308c48fff','studentMsg003','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f1ace075-22e8-4019-b859-f230d8248d0e','subjectTimeArrageMsg03','00695ac0-9d20-4509-aa57-d86532a81250'),('f1c913fc-8dea-4406-8d1d-67ec135587e5','examinationSub2Msg06','00695ac0-9d20-4509-aa57-d86532a81250'),('f24fcf00-a749-4152-b160-fed060574e82','studentReservationMsg01','0'),('f28b26d1-892a-4dfc-b475-e92eaf5568c9','examinationSub1Msg05','00695ac0-9d20-4509-aa57-d86532a81250'),('f28d99ba-4808-4baa-9538-77b66e47ad3d','examinationjhMsg14','00695ac0-9d20-4509-aa57-d86532a81250'),('f2e9481b-753d-4c0f-bfa1-e0a30099ec05','yheditym','00695ac0-9d20-4509-aa57-d86532a81250'),('f3043878-02ce-43f7-a291-06ff5963a9c9','studentTimeCount11','00695ac0-9d20-4509-aa57-d86532a81250'),('f391bc7a-3931-40b8-87f3-73704ad55aba','studentMangerMsg14','0'),('f3978cc5-8fb4-4e65-85b6-5c4d0f76743e','examinationjhMsg18','00695ac0-9d20-4509-aa57-d86532a81250'),('f421150f-c2a8-4e7c-8dea-8202de00d0f7','studentReservationMsg10','0'),('f46a8981-1fbc-4447-8c1b-8636db34fb5e','netMsg04','0'),('f53ade3f-f971-476d-a133-f2aed37748e0','examinationSub5Msg03','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f53c21cb-9efd-4741-884f-e6f3d4608fd0','studentTimeMsg05','00695ac0-9d20-4509-aa57-d86532a81250'),('f564b7fe-5435-4237-b9f6-ad8b975b675e','commonMsg17','00695ac0-9d20-4509-aa57-d86532a81250'),('f670bcde-2f2f-498b-b1d9-cb98f065c849','examinationjhMsg14','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f6a6c550-e449-4850-89a2-c4d50717d63c','studentTimeCount13','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f77caeea-145b-468e-854b-9ff0df2e2a35','studentMsg004','0'),('f789dd1a-db4c-4e74-b045-c95808998a34','yheditym','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f7cc272a-2b16-4014-9e8a-ad2bccc116e9','commonMsg02','0'),('f80376a0-4b42-4b6a-b3a8-bfefbbf1eb2b','yhglym','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f8196fc7-adab-4272-bbd9-aa37677d32f1','driverLicenseMsg02','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('f8b8db1b-41c3-4b3a-a1e3-6ed8d893cdc2','trainerCenterMsg13','0'),('f970e66c-2dc5-4bbf-9a31-e8ebf7b03112','trainerArrangeMsg06','00695ac0-9d20-4509-aa57-d86532a81250'),('f98c6d2f-f549-4609-9e83-d03e7108db99','trainerCenterMsg10','0'),('f9ecc08b-9735-4410-b151-671461a77794','studentMsg010','00695ac0-9d20-4509-aa57-d86532a81250'),('fa009e2d-9294-49d3-ab73-876f66b325ea','examinationjhMsg20','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('fa8888b8-ffc5-4065-a063-15b1b9a9ddc1','studentReservationMsg14','0'),('fac2bbf4-f0a0-4b7f-987d-6d7b01084a23','commonMsg06','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('fafdaaee-4dad-4232-b6ec-469321b60246','studentTimeMsg02','00695ac0-9d20-4509-aa57-d86532a81250'),('fb0790ab-ce96-40f8-b7a0-7692eabcd665','examinationSub4Msg03','0'),('fb4a10d6-3893-41ac-9516-3c68d2aedcd7','examinationSub4Msg01','0'),('fc1b60eb-b58f-4cf8-9c50-05d851f51253','studentMsg002','0'),('fcf398a5-07b5-47f6-9e25-70a34922c70e','examinationjhMsg07','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('fd55b431-3afc-4bb3-a08c-82ee2daa0b95','carMsg','00695ac0-9d20-4509-aa57-d86532a81250'),('fdab6e08-60d6-427d-8f45-cade0f90d20a','examinationSub1Msg06','0'),('fe75350c-5dc2-4588-b4c3-de7596b0dd7f','examinationSub2Msg09','00695ac0-9d20-4509-aa57-d86532a81250'),('fe88f89a-702f-48f1-8a2e-33bba01af4c7','studentfileMsgAuth','00695ac0-9d20-4509-aa57-d86532a81250'),('ff29299b-1598-4a4e-bd67-5522b1264b8d','studentTimeCount08','00695ac0-9d20-4509-aa57-d86532a81250'),('ff29ad8d-7303-4387-888b-686796d3f42b','examinationMsg','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ff49794a-4ce1-4ad1-ad00-49ee35f8990c','commonMsg11','0'),('ff5d4666-45d7-4e41-84a6-e31f5d99c753','examinationSub4Msg04','31ed8b2e-ce2a-4094-8eb6-c30270cccae5'),('ff9e5b61-2920-4279-b9d5-e2d6178943c9','studentReservationMsg20','0'),('ffbd6229-1e72-4d70-a370-a573f6479b24','studentMangerMsg16','0'),('ffe27a6e-2f47-40ac-9f5e-4c548c320b85','studentTimeMsg01','00695ac0-9d20-4509-aa57-d86532a81250');
/*!40000 ALTER TABLE `tb_role_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sign_up`
--

DROP TABLE IF EXISTS `tb_sign_up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sign_up` (
  `id` varchar(36) NOT NULL,
  `comments` longtext,
  `openId` varchar(100) DEFAULT NULL,
  `organizationId` varchar(36) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `upAge` int(11) DEFAULT NULL,
  `upCity` varchar(100) DEFAULT NULL,
  `upMobile` varchar(36) DEFAULT NULL,
  `upName` varchar(100) DEFAULT NULL,
  `upTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sign_up`
--

LOCK TABLES `tb_sign_up` WRITE;
/*!40000 ALTER TABLE `tb_sign_up` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_sign_up` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student`
--

DROP TABLE IF EXISTS `tb_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_student` (
  `id` varchar(36) NOT NULL,
  `address` varchar(200) NOT NULL,
  `analyColor` varchar(7) DEFAULT NULL,
  `applyType` int(11) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `body` varchar(7) DEFAULT NULL,
  `comment` longtext,
  `contry` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `driverType` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `examDay` datetime DEFAULT NULL,
  `examSelected` int(11) DEFAULT NULL,
  `eyeRedress` varchar(7) DEFAULT NULL,
  `feed` double(10,2) DEFAULT '0.00',
  `graduateDate` datetime DEFAULT NULL,
  `hearing` varchar(7) DEFAULT NULL,
  `hight` varchar(36) DEFAULT NULL,
  `hospital` longtext,
  `identityId` varchar(200) NOT NULL,
  `imageId` varchar(100) DEFAULT NULL,
  `leaningTime` int(11) DEFAULT NULL,
  `leftDownLimb` varchar(7) DEFAULT NULL,
  `leftEye` varchar(7) DEFAULT NULL,
  `leftUpperLimb` varchar(7) DEFAULT NULL,
  `mailCode` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `nativeNation` varchar(100) DEFAULT NULL,
  `natively` varchar(10) DEFAULT NULL,
  `nowState` int(11) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `ownFeed` double(10,2) DEFAULT '0.00',
  `phone` varchar(150) DEFAULT NULL,
  `realFeed` double(10,2) DEFAULT '0.00',
  `residenceAddr` varchar(200) DEFAULT NULL,
  `residenceId` varchar(200) DEFAULT NULL,
  `restTiming` int(11) DEFAULT NULL,
  `rightDownLimb` varchar(7) DEFAULT NULL,
  `rightEye` varchar(7) DEFAULT NULL,
  `rightUpperLimb` varchar(7) DEFAULT NULL,
  `sex` varchar(8) NOT NULL,
  `studentNo` varchar(36) NOT NULL,
  `telephone` varchar(150) DEFAULT NULL,
  `clazzId` varchar(36) DEFAULT NULL,
  `organizationId` varchar(36) DEFAULT NULL,
  `personId` varchar(36) DEFAULT NULL,
  `studentTimerCardId` varchar(36) NOT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  `userId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentNo` (`studentNo`),
  UNIQUE KEY `trainerId` (`trainerId`),
  UNIQUE KEY `userId` (`userId`),
  KEY `FK9007E96AD48AFC01` (`trainerId`),
  KEY `FK9007E96AEEBAEC49` (`personId`),
  KEY `FK9007E96A78732D85` (`organizationId`),
  KEY `FK9007E96ACDAEB8A7` (`clazzId`),
  KEY `FK9007E96AD0889F53` (`studentTimerCardId`),
  KEY `FK9007E96A67D6E8B5` (`userId`),
  CONSTRAINT `FK9007E96A67D6E8B5` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`CID`),
  CONSTRAINT `FK9007E96A78732D85` FOREIGN KEY (`organizationId`) REFERENCES `tb_organizations` (`id`),
  CONSTRAINT `FK9007E96ACDAEB8A7` FOREIGN KEY (`clazzId`) REFERENCES `tb_classes` (`id`),
  CONSTRAINT `FK9007E96AD0889F53` FOREIGN KEY (`studentTimerCardId`) REFERENCES `tb_student_timer_card` (`id`),
  CONSTRAINT `FK9007E96AD48AFC01` FOREIGN KEY (`trainerId`) REFERENCES `tb_trainers` (`id`),
  CONSTRAINT `FK9007E96AEEBAEC49` FOREIGN KEY (`personId`) REFERENCES `tb_persons` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student`
--

LOCK TABLES `tb_student` WRITE;
/*!40000 ALTER TABLE `tb_student` DISABLE KEYS */;
INSERT INTO `tb_student` VALUES ('00243719-b5db-49e5-ab21-915961bfe1a5','广西百色市城北一路29号','合格',1,NULL,'合格','',NULL,'2016-12-10 00:00:00',6,'',NULL,0,'合格',NULL,NULL,'合格','','','451024198612011002','',20,'','合格','合格',NULL,'张四',NULL,'本地',1,'bsxq',0.00,'13324769338',NULL,'','',1,'合格','合格','合格','','BSF1002',NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',NULL,'4a6cd859-c6c0-4d0b-a5e4-664566829918',NULL,'8323799a-ab81-47d2-9f46-bdeddb620fb1'),('37f6d0a7-210c-418a-ab79-7aaab89b5a44','广西百色市田阳县江与城小区','合格',1,NULL,'合格','',NULL,'2016-12-10 00:00:00',6,'',NULL,0,'合格',NULL,NULL,'合格','','','452526198612011001','',20,'','合格','合格',NULL,'张成晚',NULL,'本地',1,'tyxq',0.00,'13324769338',NULL,'','',1,'合格','合格','合格','','TY1001',NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty',NULL,'0053bb39-b045-4427-b771-94451ab9967e',NULL,'b5fe4b78-3e45-43e0-a18f-7dbb944a35bc'),('62c3bf3b-6654-4007-9f3b-e10362552005','广西百色市田阳县江与城小区','合格',1,NULL,'合格','',NULL,'2016-12-10 00:00:00',6,'',NULL,0,'合格',NULL,NULL,'合格','','','452526198612011003','',20,'','合格','合格',NULL,'丁关根',NULL,'本地',1,'tyxq',0.00,'13324769338',NULL,'','',1,'合格','合格','合格','','TY1003',NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty',NULL,'cbb15c8a-37d7-48c6-a6c2-935192ae64db',NULL,'ab6bce44-8130-4ac7-a868-b2d967209531'),('a1b1478c-2a2f-4e54-97ad-64f65453ad48','广西百色市城北一路29号','合格',1,NULL,'合格','',NULL,'2016-12-10 00:00:00',6,'',NULL,0,'合格',NULL,NULL,'合格','','','451024198612011001','',20,'','合格','合格',NULL,'张三',NULL,'本地',1,'bsxq',0.00,'13324769338',NULL,'','',1,'合格','合格','合格','','BSF1001',NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',NULL,'e27eb75d-d85e-4e1f-9055-29d1139b1280',NULL,'035a5242-e09e-4935-af50-665fd9f8c06b'),('a5283ccd-c9eb-41c3-85d4-eab90a0daec5','广西百色市城北一路29号','合格',1,NULL,'合格','',NULL,'2016-12-10 00:00:00',6,'',NULL,0,'合格',NULL,NULL,'合格','','','451024198612011003','',20,'','合格','合格',NULL,'丁关根',NULL,'本地',1,'bsxq',0.00,'13324769338',NULL,'','',1,'合格','合格','合格','','BSF1003',NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',NULL,'5cb02c1a-2413-4db1-bff0-657ba292cb94',NULL,'1ae1213d-d900-441e-9c1f-7cb96ae247c4'),('c5de01a4-b7da-4979-afd4-d56dcc560aa5','广西百色市田阳县江与城小区','合格',1,NULL,'合格','',NULL,'2016-12-10 00:00:00',6,'',NULL,0,'合格',NULL,NULL,'合格','','','452526198612011002','',20,'','合格','合格',NULL,'张国立',NULL,'本地',1,'tyxq',0.00,'13324769338',NULL,'','',1,'合格','合格','合格','','TY1002',NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty',NULL,'d3e565ff-788e-45f4-beba-33deca14832b',NULL,'bb648bf8-7ed5-45c1-bdaf-eb907ec31dc1');
/*!40000 ALTER TABLE `tb_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student_timer_card`
--

DROP TABLE IF EXISTS `tb_student_timer_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_student_timer_card` (
  `id` varchar(36) NOT NULL,
  `oneHours` int(11) DEFAULT NULL,
  `threesOperateHours` int(11) DEFAULT NULL,
  `threesSimulatorHours` int(11) DEFAULT NULL,
  `threesTheoryHours` int(11) DEFAULT NULL,
  `totalHours` int(11) DEFAULT NULL,
  `twosOperateHours` int(11) DEFAULT NULL,
  `twosSimulatorHours` int(11) DEFAULT NULL,
  `twosTheoryHours` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_timer_card`
--

LOCK TABLES `tb_student_timer_card` WRITE;
/*!40000 ALTER TABLE `tb_student_timer_card` DISABLE KEYS */;
INSERT INTO `tb_student_timer_card` VALUES ('0053bb39-b045-4427-b771-94451ab9967e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1042cec6-42e2-4e9b-b3c5-f478c5362d24',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4a6cd859-c6c0-4d0b-a5e4-664566829918',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('5cb02c1a-2413-4db1-bff0-657ba292cb94',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('cbb15c8a-37d7-48c6-a6c2-935192ae64db',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('d3e565ff-788e-45f4-beba-33deca14832b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('e27eb75d-d85e-4e1f-9055-29d1139b1280',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_student_timer_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_studentfiles`
--

DROP TABLE IF EXISTS `tb_studentfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_studentfiles` (
  `id` varchar(36) NOT NULL,
  `comment` longtext,
  `contributionTime` datetime DEFAULT NULL,
  `feeState` int(11) DEFAULT NULL,
  `learnTime` int(11) DEFAULT NULL,
  `numbering` varchar(36) DEFAULT NULL,
  `qualificationDate` datetime DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `subjectFourDate` datetime DEFAULT NULL,
  `subjectThreeDate` datetime DEFAULT NULL,
  `subjectTwoDate` datetime DEFAULT NULL,
  `theoryDate` datetime DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB032234DF89D7D8D` (`studentId`),
  CONSTRAINT `FKB032234DF89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_studentfiles`
--

LOCK TABLES `tb_studentfiles` WRITE;
/*!40000 ALTER TABLE `tb_studentfiles` DISABLE KEYS */;
INSERT INTO `tb_studentfiles` VALUES ('46047f01-febf-4e6e-baa8-3f4c401384f0',NULL,NULL,NULL,NULL,NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty',NULL,NULL,NULL,NULL,'37f6d0a7-210c-418a-ab79-7aaab89b5a44'),('4eb1ef79-1bdb-4f2a-b02e-9f4f86247f52',NULL,NULL,NULL,NULL,NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',NULL,NULL,NULL,NULL,'a1b1478c-2a2f-4e54-97ad-64f65453ad48'),('63d71fb4-fb05-4ec7-b7e7-81bd46284652',NULL,NULL,NULL,NULL,NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty',NULL,NULL,NULL,NULL,'c5de01a4-b7da-4979-afd4-d56dcc560aa5'),('69514b8f-21c7-4aa3-b316-3571d391d68d',NULL,NULL,NULL,NULL,NULL,NULL,'c0510169-8edc-470e-b038-c299c7735bty',NULL,NULL,NULL,NULL,'62c3bf3b-6654-4007-9f3b-e10362552005'),('f260c459-b918-4422-911d-bf6089110105',NULL,NULL,NULL,NULL,NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',NULL,NULL,NULL,NULL,'a5283ccd-c9eb-41c3-85d4-eab90a0daec5'),('f46dfb7f-1a4d-4ce1-8524-eb00eeae8f8f',NULL,NULL,NULL,NULL,NULL,NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',NULL,NULL,NULL,NULL,'00243719-b5db-49e5-ab21-915961bfe1a5');
/*!40000 ALTER TABLE `tb_studentfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_subjectfour`
--

DROP TABLE IF EXISTS `tb_subjectfour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_subjectfour` (
  `id` varchar(36) NOT NULL,
  `arrangedNo` varchar(36) DEFAULT NULL,
  `comment` longtext,
  `examDate` datetime DEFAULT NULL,
  `examScoke` int(11) DEFAULT NULL,
  `examSelected` int(11) DEFAULT NULL,
  `examType` int(11) DEFAULT NULL,
  `licensExamSelected` int(11) DEFAULT NULL,
  `makeupExameDate` datetime DEFAULT NULL,
  `makeupExameScoke` int(11) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `passFlag` int(11) DEFAULT NULL,
  `qualiLicensExamSelected` int(11) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `arrangedId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5D999821F89D7D8D` (`studentId`),
  KEY `FK5D99982134C51CA8` (`arrangedId`),
  CONSTRAINT `FK5D99982134C51CA8` FOREIGN KEY (`arrangedId`) REFERENCES `tb_arrangedexamination` (`id`),
  CONSTRAINT `FK5D999821F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_subjectfour`
--

LOCK TABLES `tb_subjectfour` WRITE;
/*!40000 ALTER TABLE `tb_subjectfour` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_subjectfour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_subjectthrees`
--

DROP TABLE IF EXISTS `tb_subjectthrees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_subjectthrees` (
  `id` varchar(36) NOT NULL,
  `arrangedNo` varchar(36) DEFAULT NULL,
  `comment` longtext,
  `examDate` datetime DEFAULT NULL,
  `examScoke` int(11) DEFAULT NULL,
  `examSelected` int(11) DEFAULT NULL,
  `examType` int(11) DEFAULT NULL,
  `makeupExame1Date` datetime DEFAULT NULL,
  `makeupExame1Scoke` int(11) DEFAULT NULL,
  `makeupExame2Date` datetime DEFAULT NULL,
  `makeupExame2Scoke` int(11) DEFAULT NULL,
  `makeupExame3Date` datetime DEFAULT NULL,
  `makeupExame3Scoke` int(11) DEFAULT NULL,
  `makeupExame4Date` datetime DEFAULT NULL,
  `makeupExame4Scoke` int(11) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `passFlag` int(11) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `arrangedId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7513C6D0F89D7D8D` (`studentId`),
  KEY `FK7513C6D034C51CA8` (`arrangedId`),
  CONSTRAINT `FK7513C6D034C51CA8` FOREIGN KEY (`arrangedId`) REFERENCES `tb_arrangedexamination` (`id`),
  CONSTRAINT `FK7513C6D0F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_subjectthrees`
--

LOCK TABLES `tb_subjectthrees` WRITE;
/*!40000 ALTER TABLE `tb_subjectthrees` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_subjectthrees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_subjecttwos`
--

DROP TABLE IF EXISTS `tb_subjecttwos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_subjecttwos` (
  `id` varchar(36) NOT NULL,
  `arrangedNo` varchar(36) DEFAULT NULL,
  `comment` longtext,
  `examDate` datetime DEFAULT NULL,
  `examScoke` int(11) DEFAULT NULL,
  `examSelected` int(11) DEFAULT NULL,
  `examType` int(11) DEFAULT NULL,
  `makeupExame1Date` datetime DEFAULT NULL,
  `makeupExame1Scoke` int(11) DEFAULT NULL,
  `makeupExame2Date` datetime DEFAULT NULL,
  `makeupExame2Scoke` int(11) DEFAULT NULL,
  `makeupExame3Date` datetime DEFAULT NULL,
  `makeupExame3Scoke` int(11) DEFAULT NULL,
  `makeupExame4Date` datetime DEFAULT NULL,
  `makeupExame4Scoke` int(11) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `passFlag` int(11) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `arrangedId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5DA012A2F89D7D8D` (`studentId`),
  KEY `FK5DA012A234C51CA8` (`arrangedId`),
  CONSTRAINT `FK5DA012A234C51CA8` FOREIGN KEY (`arrangedId`) REFERENCES `tb_arrangedexamination` (`id`),
  CONSTRAINT `FK5DA012A2F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_subjecttwos`
--

LOCK TABLES `tb_subjecttwos` WRITE;
/*!40000 ALTER TABLE `tb_subjecttwos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_subjecttwos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_theoryexaminations`
--

DROP TABLE IF EXISTS `tb_theoryexaminations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_theoryexaminations` (
  `id` varchar(36) NOT NULL,
  `arrangedNo` varchar(36) DEFAULT NULL,
  `comment` longtext,
  `examDate` datetime DEFAULT NULL,
  `examScoke` int(11) DEFAULT NULL,
  `examSelected` int(11) DEFAULT NULL,
  `examSelectedSubjectThree` int(11) DEFAULT NULL,
  `examType` int(11) DEFAULT NULL,
  `makeupExameDate` datetime DEFAULT NULL,
  `makeupExameScoke` int(11) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `passFlag` int(11) DEFAULT NULL,
  `schoolArea` varchar(100) DEFAULT NULL,
  `shuttleAddress` varchar(200) DEFAULT NULL,
  `arrangedId` varchar(36) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentId` (`studentId`),
  KEY `FK579FDD78F89D7D8D` (`studentId`),
  KEY `FK579FDD7834C51CA8` (`arrangedId`),
  CONSTRAINT `FK579FDD7834C51CA8` FOREIGN KEY (`arrangedId`) REFERENCES `tb_arrangedexamination` (`id`),
  CONSTRAINT `FK579FDD78F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_theoryexaminations`
--

LOCK TABLES `tb_theoryexaminations` WRITE;
/*!40000 ALTER TABLE `tb_theoryexaminations` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_theoryexaminations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_timer_card`
--

DROP TABLE IF EXISTS `tb_timer_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_timer_card` (
  `id` varchar(36) NOT NULL,
  `driverTypeId` int(11) DEFAULT NULL,
  `driverTypeName` varchar(11) DEFAULT NULL,
  `oneHours` int(11) DEFAULT NULL,
  `threesOperateHours` int(11) DEFAULT NULL,
  `threesSimulatorHours` int(11) DEFAULT NULL,
  `threesTheoryHours` int(11) DEFAULT NULL,
  `totalHours` int(11) DEFAULT NULL,
  `twosOperateHours` int(11) DEFAULT NULL,
  `twosSimulatorHours` int(11) DEFAULT NULL,
  `twosTheoryHours` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `driverTypeId` (`driverTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_timer_card`
--

LOCK TABLES `tb_timer_card` WRITE;
/*!40000 ALTER TABLE `tb_timer_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_timer_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_timings`
--

DROP TABLE IF EXISTS `tb_timings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_timings` (
  `id` varchar(36) NOT NULL,
  `buyTiming` int(11) DEFAULT NULL,
  `carTiming` int(11) DEFAULT NULL,
  `claimTiming` int(11) DEFAULT NULL,
  `comment` longtext,
  `handselTiming` int(11) DEFAULT NULL,
  `numbering` varchar(36) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `reservationTiming` int(11) DEFAULT NULL,
  `restTiming` int(11) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `simulationTiming` int(11) DEFAULT NULL,
  `totalTiming` int(11) DEFAULT NULL,
  `tramTiming` int(11) DEFAULT NULL,
  `usingTiming` int(11) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB1BA8098F89D7D8D` (`studentId`),
  CONSTRAINT `FKB1BA8098F89D7D8D` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_timings`
--

LOCK TABLES `tb_timings` WRITE;
/*!40000 ALTER TABLE `tb_timings` DISABLE KEYS */;
INSERT INTO `tb_timings` VALUES ('1897d3b5-ef36-421c-b23b-87b90654aff5',0,10,NULL,NULL,20,'20161210-134004',NULL,9,20,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',0,20,0,0,'00243719-b5db-49e5-ab21-915961bfe1a5'),('3ad6e225-f8a1-40e0-b67b-f48506c7d728',0,2,NULL,NULL,20,'20161210-134019',NULL,19,20,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',0,20,-1,0,'a5283ccd-c9eb-41c3-85d4-eab90a0daec5'),('443b42ad-a2bf-4dd6-9c03-f14bca40f151',0,0,NULL,NULL,20,'20161210-134738',NULL,20,20,'c0510169-8edc-470e-b038-c299c7735bty',0,20,0,0,'37f6d0a7-210c-418a-ab79-7aaab89b5a44'),('74921098-98fc-4ff9-bf1a-17cf2d5d80b4',0,0,NULL,NULL,20,'20161210-134806',NULL,20,20,'c0510169-8edc-470e-b038-c299c7735bty',0,20,0,0,'c5de01a4-b7da-4979-afd4-d56dcc560aa5'),('80f45989-b8c3-4336-911d-4bd75b792aee',0,4,NULL,NULL,20,'20161210-133944',NULL,17,20,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',0,20,-1,0,'a1b1478c-2a2f-4e54-97ad-64f65453ad48'),('cacbd7e1-c7b7-4089-a3f7-5f347ba6e38a',0,0,NULL,NULL,20,'20161210-134831',NULL,20,20,'c0510169-8edc-470e-b038-c299c7735bty',0,20,0,0,'62c3bf3b-6654-4007-9f3b-e10362552005');
/*!40000 ALTER TABLE `tb_timings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_trainerreservationdetail`
--

DROP TABLE IF EXISTS `tb_trainerreservationdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_trainerreservationdetail` (
  `id` varchar(36) NOT NULL,
  `fiveItemTotal` int(11) DEFAULT NULL,
  `item10Studentid` varchar(36) DEFAULT NULL,
  `item10TrainerContent` int(11) DEFAULT NULL,
  `item11Studentid` varchar(36) DEFAULT NULL,
  `item11TrainerContent` int(11) DEFAULT NULL,
  `item12Studentid` varchar(36) DEFAULT NULL,
  `item12TrainerContent` int(11) DEFAULT NULL,
  `item13Studentid` varchar(36) DEFAULT NULL,
  `item13TrainerContent` int(11) DEFAULT NULL,
  `item14Studentid` varchar(36) DEFAULT NULL,
  `item14TrainerContent` int(11) DEFAULT NULL,
  `item15Studentid` varchar(36) DEFAULT NULL,
  `item15TrainerContent` int(11) DEFAULT NULL,
  `item16Studentid` varchar(36) DEFAULT NULL,
  `item16TrainerContent` int(11) DEFAULT NULL,
  `item17Studentid` varchar(36) DEFAULT NULL,
  `item17TrainerContent` int(11) DEFAULT NULL,
  `item18Studentid` varchar(36) DEFAULT NULL,
  `item18TrainContent` int(11) DEFAULT NULL,
  `item8Studentid` varchar(36) DEFAULT NULL,
  `item8TrainContent` int(11) DEFAULT NULL,
  `item9Studentid` varchar(36) DEFAULT NULL,
  `item9TrainerContent` int(11) DEFAULT NULL,
  `raodExamTotal` int(11) DEFAULT NULL,
  `reservationDate` datetime DEFAULT NULL,
  `reservationFieldId10` varchar(36) DEFAULT NULL,
  `reservationFieldId11` varchar(36) DEFAULT NULL,
  `reservationFieldId12` varchar(36) DEFAULT NULL,
  `reservationFieldId13` varchar(36) DEFAULT NULL,
  `reservationFieldId14` varchar(36) DEFAULT NULL,
  `reservationFieldId15` varchar(36) DEFAULT NULL,
  `reservationFieldId16` varchar(36) DEFAULT NULL,
  `reservationFieldId17` varchar(36) DEFAULT NULL,
  `reservationFieldId18` varchar(36) DEFAULT NULL,
  `reservationFieldId8` varchar(36) DEFAULT NULL,
  `reservationFieldId9` varchar(36) DEFAULT NULL,
  `reservationType` int(11) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  `trainerReservationId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8B683FB92B615EED` (`trainerReservationId`),
  CONSTRAINT `FK8B683FB92B615EED` FOREIGN KEY (`trainerReservationId`) REFERENCES `tb_trainerreservations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_trainerreservationdetail`
--

LOCK TABLES `tb_trainerreservationdetail` WRITE;
/*!40000 ALTER TABLE `tb_trainerreservationdetail` DISABLE KEYS */;
INSERT INTO `tb_trainerreservationdetail` VALUES ('04b6c1ff-3aba-44d1-a909-d3cb1826a36e',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-12 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('06f13f46-bb9e-46f0-9bc9-9fc56409c5e0',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,4,NULL,4,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-16 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('091c8b0c-641e-4d5c-9744-0d4a1b84994d',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,4,NULL,4,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-15 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'6d67eefe-3b91-4f63-9619-54f2a06e6e25','bdd90108-f012-45f1-be3b-db08789da61f'),('0b514b7b-1527-448b-9425-9ae0d3f9b980',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,'2016-12-13 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'56431f9d-53cd-4b71-8434-9cbd8c737d36','408404c5-7395-49a4-a823-a1e725ec7129'),('1292a623-80aa-4bed-ba8d-65970df806e2',NULL,'',3,'',3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,NULL,3,'',3,NULL,'2016-12-15 00:00:00','','','','','','','','','',NULL,'',3,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('23a814b8-91aa-43ca-a9b2-53136bb8a8ba',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,4,NULL,4,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-13 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'6d67eefe-3b91-4f63-9619-54f2a06e6e25','bdd90108-f012-45f1-be3b-db08789da61f'),('23b3d684-4045-483c-9fad-417027c41b62',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-20 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('282e900b-8d00-477c-a944-c061edae598e',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,4,NULL,4,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-14 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'6d67eefe-3b91-4f63-9619-54f2a06e6e25','bdd90108-f012-45f1-be3b-db08789da61f'),('29e50e80-3095-4182-bc86-788845a4156c',NULL,'',4,'',4,'NO',NULL,'NO',NULL,NULL,3,'',3,'',3,'',3,'',3,'',4,'',4,NULL,'2016-12-12 00:00:00','','','','',NULL,'','','','','','',3,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('2f0c5663-3a72-4418-bdae-305b32fba579',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-20 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('3271a1a2-a022-4a8a-ad38-3d71ee0e191c',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-14 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('34442c8b-c8da-4996-be0c-018ad5787daa',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-19 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('36d75409-3df9-46ec-a4cc-d36688f85ba2',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-13 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('39277e59-f136-4c8c-8cdf-a1907dd31a09',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-20 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('3da51876-6217-41bf-a6af-3ac470677255',NULL,'',3,'',3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,NULL,3,'',3,NULL,'2016-12-16 00:00:00','','','','','','','','','',NULL,'',3,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('527c4385-73bc-47b9-9000-b61b8f794b80',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,4,NULL,4,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-12 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'6d67eefe-3b91-4f63-9619-54f2a06e6e25','bdd90108-f012-45f1-be3b-db08789da61f'),('5357322e-08e2-4a08-845d-4db62870c921',NULL,'',1,'',1,'NO',NULL,'NO',NULL,'',2,'',2,'',2,'',2,'',2,NULL,4,'',1,NULL,'2016-12-19 00:00:00','','','','','','','','','',NULL,'',4,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('5de4d3f8-dd13-43d5-adfa-3b7c940f0b32',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,1,NULL,1,NULL,1,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-20 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('70531da0-ca18-4616-93fe-7317a78144c5',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-19 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('7aa99fc3-6ce3-4fe9-8a6d-62ab8b70e837',NULL,'',3,'',3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,'',3,'',3,NULL,'2016-12-13 00:00:00','','','','','','','','','','','',3,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('7aaa5f76-2e28-4e64-9bd1-bdc45a5e806a',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-22 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('7cb07f43-6da2-423b-a89f-64852a6724fc',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,1,NULL,1,NULL,1,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-22 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('84aa6c14-abdf-493a-b63f-59eb2b7b7f2d',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-22 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('8e053746-23d9-40ff-bba1-9dc081fdeec6',NULL,'',3,'',3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,NULL,3,'',3,NULL,'2016-12-14 00:00:00','','','','','','','','','',NULL,'',3,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('9fd5b040-a81a-4627-9138-9822f727fa10',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,'2016-12-15 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'56431f9d-53cd-4b71-8434-9cbd8c737d36','408404c5-7395-49a4-a823-a1e725ec7129'),('ad053816-6973-4bb9-aaa2-15fa847ac322',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-15 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('afd0cf1a-6ed4-46b9-987f-842147ffcd97',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-21 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('b492ffad-8248-4614-b208-d6638ea74b85',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,'2016-12-14 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'56431f9d-53cd-4b71-8434-9cbd8c737d36','408404c5-7395-49a4-a823-a1e725ec7129'),('b551ab0f-dab3-431b-b85c-344363014c63',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,1,NULL,1,NULL,1,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-21 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('b59655c9-13a9-4c81-98c6-496a58dbbfba',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,1,NULL,1,NULL,1,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-23 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('b64926b8-5c66-4d41-adb4-ecd3b7dac842',NULL,'',3,'',3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,NULL,3,'',3,NULL,'2016-12-14 00:00:00','','','','','','','','','',NULL,'',3,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('bad9d7a2-c72e-4760-b4b1-4ed20993e66e',NULL,'',3,'',3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,NULL,3,'',3,NULL,'2016-12-13 00:00:00','','','','','','','','','',NULL,'',3,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('bd8741b1-e3b1-4d69-b13c-72397e3160a8',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-14 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('c68d964c-f463-4f4c-973a-191704298e07',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-21 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('c77f61f3-135f-49dc-84ed-49c1d807c9cb',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-23 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('d00bc40a-3c63-4fbd-8ecb-b37c2444eeef',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-23 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('d194e57c-40f1-4b8c-b54d-115bb84170a4',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,1,NULL,1,NULL,1,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-19 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('d28fdae1-72bf-40a8-9d71-fcf2a01de245',NULL,'',3,'',3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,NULL,3,'',3,NULL,'2016-12-15 00:00:00','','','','','','','','','',NULL,'',3,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('d4302a4b-33a4-4d9f-891f-0270899f2c42',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-22 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('e32eb7f4-036f-4614-ae84-083a10a8db4c',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-21 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'0afc253f-6ad5-44b8-9a0c-0638359aace1','d59820bb-76a7-4f78-9c71-c432ee76abe6'),('e3f1c0b2-6713-46c7-aad2-d3cbb1290d13',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-13 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('e6c7685a-a871-4360-84ce-59ae26399be5',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,'2016-12-12 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'56431f9d-53cd-4b71-8434-9cbd8c737d36','408404c5-7395-49a4-a823-a1e725ec7129'),('f4ee3131-85e3-4c20-9910-9d8e7d9ec124',NULL,NULL,4,NULL,4,'NO',NULL,'NO',NULL,NULL,3,NULL,3,NULL,3,NULL,3,NULL,3,NULL,4,NULL,4,NULL,'2016-12-15 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c','93c52937-65b6-4e72-815f-ffa263ed0bac'),('fa1f02bc-8ed8-46d4-a1c1-8c249f6e85c4',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,NULL,4,NULL,4,NULL,4,NULL,4,NULL,4,NULL,3,NULL,3,NULL,'2016-12-12 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','23ff79b3-ab1f-43c3-888c-ce2b8d14434b'),('fbc1a63d-77ea-4ab0-bca0-6d3ae69775d0',NULL,NULL,1,NULL,1,'NO',NULL,'NO',NULL,NULL,2,NULL,2,NULL,2,NULL,2,NULL,2,NULL,1,NULL,1,NULL,'2016-12-23 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4'),('fefcc42c-79ba-46fb-94f8-dd7818528d01',NULL,NULL,3,NULL,3,'NO',NULL,'NO',NULL,'',4,'',4,'',4,'',4,'',4,'',3,NULL,3,NULL,'2016-12-12 00:00:00',NULL,NULL,'','','','','','','','',NULL,3,'5addced0-3ccf-4864-83ca-b14bb3c2862a','a0036f77-a052-42fb-bae7-4c0c1d5ea3f4');
/*!40000 ALTER TABLE `tb_trainerreservationdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_trainerreservations`
--

DROP TABLE IF EXISTS `tb_trainerreservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_trainerreservations` (
  `id` varchar(36) NOT NULL,
  `comment` longtext,
  `fiveItem` int(11) DEFAULT NULL,
  `roadExam` int(11) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `studentTotal` int(11) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3C50D82BD48AFC01` (`trainerId`),
  CONSTRAINT `FK3C50D82BD48AFC01` FOREIGN KEY (`trainerId`) REFERENCES `tb_trainers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_trainerreservations`
--

LOCK TABLES `tb_trainerreservations` WRITE;
/*!40000 ALTER TABLE `tb_trainerreservations` DISABLE KEYS */;
INSERT INTO `tb_trainerreservations` VALUES ('23ff79b3-ab1f-43c3-888c-ce2b8d14434b',NULL,0,0,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',0,'3f4966b5-3b3f-47ca-9c21-e28b73ef86b7'),('408404c5-7395-49a4-a823-a1e725ec7129',NULL,0,0,'c0510169-8edc-470e-b038-c299c7735bty',0,'56431f9d-53cd-4b71-8434-9cbd8c737d36'),('93c52937-65b6-4e72-815f-ffa263ed0bac',NULL,0,0,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',0,'e57707e7-52cf-4b05-b58e-7bfd95f12a4c'),('a0036f77-a052-42fb-bae7-4c0c1d5ea3f4',NULL,0,0,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',0,'5addced0-3ccf-4864-83ca-b14bb3c2862a'),('bdd90108-f012-45f1-be3b-db08789da61f',NULL,0,0,'c0510169-8edc-470e-b038-c299c7735bty',0,'6d67eefe-3b91-4f63-9619-54f2a06e6e25'),('d59820bb-76a7-4f78-9c71-c432ee76abe6',NULL,0,0,'c0510169-8edc-470e-b038-c299c7735bty',0,'0afc253f-6ad5-44b8-9a0c-0638359aace1');
/*!40000 ALTER TABLE `tb_trainerreservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_trainers`
--

DROP TABLE IF EXISTS `tb_trainers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_trainers` (
  `id` varchar(36) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `arrangeFlag` int(11) DEFAULT '0',
  `birthday` datetime DEFAULT NULL,
  `carBanding` int(11) DEFAULT NULL,
  `codeNo` varchar(36) DEFAULT NULL,
  `comment` longtext,
  `contry` varchar(40) DEFAULT NULL,
  `driverCarType` longtext,
  `driverLicenseId` varchar(40) NOT NULL,
  `driverType` int(11) DEFAULT NULL,
  `driverValidatyEnd` datetime DEFAULT NULL,
  `driverValidatyStart` datetime DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `firstGetDate` datetime DEFAULT NULL,
  `firstTeachingDate` datetime DEFAULT NULL,
  `identity` varchar(36) NOT NULL,
  `jionDay` datetime DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `nation` varchar(32) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `phone` varchar(14) NOT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `teachBalidatyEnd` datetime DEFAULT NULL,
  `teachId` varchar(36) DEFAULT NULL,
  `teachValidatyStart` datetime DEFAULT NULL,
  `telephone` varchar(14) DEFAULT NULL,
  `userId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  KEY `FK4D33790F67D6E8B5` (`userId`),
  CONSTRAINT `FK4D33790F67D6E8B5` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_trainers`
--

LOCK TABLES `tb_trainers` WRITE;
/*!40000 ALTER TABLE `tb_trainers` DISABLE KEYS */;
INSERT INTO `tb_trainers` VALUES ('0afc253f-6ad5-44b8-9a0c-0638359aace1','',2,NULL,1,'TY1003','','','1, 3, 4, 5, 6, 7, 8, 9, 13','452625198612013003',1,NULL,NULL,'',NULL,NULL,'452625198612013003',NULL,'韩教练','','tyxq','13324769338','c0510169-8edc-470e-b038-c299c7735bty','',NULL,'',NULL,'','bca73d8a-6ad3-4d7d-9301-6be691227d7b'),('3f4966b5-3b3f-47ca-9c21-e28b73ef86b7','',2,NULL,1,'BS1003','','','1, 3, 4, 5, 6, 7, 8, 9, 13','452625198612018003',1,NULL,NULL,'',NULL,NULL,'452625198612018003',NULL,'赵教练','','bsxq','13324769338','b78ffef2-7c54-40fe-be4b-1910a87c8bbs','',NULL,'',NULL,'','914ff281-f7bd-4027-a93e-75642de7633e'),('56431f9d-53cd-4b71-8434-9cbd8c737d36','',2,NULL,1,'TY1001','','','1, 3, 4, 5, 6, 7, 8, 9, 13','452625198612013005',1,NULL,NULL,'',NULL,NULL,'452625198612013005',NULL,'张教练','','tyxq','13324769338','c0510169-8edc-470e-b038-c299c7735bty','',NULL,'',NULL,'','f2c335d9-9f67-4b92-9b62-b72a8f2baad5'),('5addced0-3ccf-4864-83ca-b14bb3c2862a','',2,NULL,1,'BS1001','','','1, 3, 4, 5, 6, 7, 8, 9, 13','452625198612018001',1,NULL,NULL,'',NULL,NULL,'452625198612018001',NULL,'李教练','','bsxq','13324769338','b78ffef2-7c54-40fe-be4b-1910a87c8bbs','',NULL,'',NULL,'','5f7c9cb5-6b0f-4a69-8e78-7d48ed550a3a'),('6d67eefe-3b91-4f63-9619-54f2a06e6e25','',2,NULL,1,'TY1002','','','1, 3, 4, 5, 6, 7, 8, 9, 13','452625198612013002',1,NULL,NULL,'',NULL,NULL,'452625198612013002',NULL,'杜教练','','tyxq','13324769338','c0510169-8edc-470e-b038-c299c7735bty','',NULL,'',NULL,'','68f791cb-2feb-42fd-9bfb-debb960c4cf1'),('e57707e7-52cf-4b05-b58e-7bfd95f12a4c','',2,NULL,1,'BS1002','','','1, 3, 4, 5, 6, 7, 8, 9, 13','452625198612018002',1,NULL,NULL,'',NULL,NULL,'452625198612018002',NULL,'韦教练','','bsxq','13324769338','b78ffef2-7c54-40fe-be4b-1910a87c8bbs','',NULL,'',NULL,'','b5fc6b14-037e-42b0-a8c7-9e680af35342');
/*!40000 ALTER TABLE `tb_trainers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tranierarranges`
--

DROP TABLE IF EXISTS `tb_tranierarranges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tranierarranges` (
  `id` varchar(36) NOT NULL,
  `arrangeDate` datetime DEFAULT NULL,
  `firstArrangeDate` datetime DEFAULT NULL,
  `ItemTime10` int(11) DEFAULT NULL,
  `ItemTime10flag` int(11) DEFAULT NULL,
  `ItemTime11` int(11) DEFAULT NULL,
  `ItemTime11flag` int(11) DEFAULT NULL,
  `ItemTime12` int(11) DEFAULT NULL,
  `ItemTime12flag` int(11) DEFAULT NULL,
  `ItemTime13` int(11) DEFAULT NULL,
  `ItemTime13flag` int(11) DEFAULT NULL,
  `ItemTime14` int(11) DEFAULT NULL,
  `ItemTime14flag` int(11) DEFAULT NULL,
  `ItemTime15` int(11) DEFAULT NULL,
  `ItemTime15flag` int(11) DEFAULT NULL,
  `ItemTime16` int(11) DEFAULT NULL,
  `ItemTime16flag` int(11) DEFAULT NULL,
  `ItemTime17` int(11) DEFAULT NULL,
  `ItemTime17flag` int(11) DEFAULT NULL,
  `ItemTime18` int(11) DEFAULT NULL,
  `ItemTime18flag` int(11) DEFAULT NULL,
  `ItemTime8` int(11) DEFAULT NULL,
  `ItemTime8flag` int(11) DEFAULT NULL,
  `ItemTime9` int(11) DEFAULT NULL,
  `ItemTime9flag` int(11) DEFAULT NULL,
  `lastArrangeDate` datetime DEFAULT NULL,
  `month` varchar(12) DEFAULT NULL,
  `No` varchar(36) DEFAULT NULL,
  `noArrangeDates` longtext,
  `operator` varchar(36) DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `teachingType` int(11) DEFAULT NULL,
  `trainTime` longtext,
  `weekth` varchar(4) DEFAULT NULL,
  `workingType` int(11) DEFAULT NULL,
  `year` varchar(12) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCE98EF41D48AFC01` (`trainerId`),
  CONSTRAINT `FKCE98EF41D48AFC01` FOREIGN KEY (`trainerId`) REFERENCES `tb_trainers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tranierarranges`
--

LOCK TABLES `tb_tranierarranges` WRITE;
/*!40000 ALTER TABLE `tb_tranierarranges` DISABLE KEYS */;
INSERT INTO `tb_tranierarranges` VALUES ('11de2bcd-767e-4a61-a8ec-36cba62493a5','2016-12-10 13:45:10','2016-12-12 00:00:00',1,3,1,3,NULL,3,NULL,3,1,4,1,4,1,4,1,4,1,4,1,3,1,3,'2016-12-15 00:00:00','12','20161210-134510','12, 13, 14, 15','tyxq','c0510169-8edc-470e-b038-c299c7735bty',6,NULL,NULL,3,'2016','6d67eefe-3b91-4f63-9619-54f2a06e6e25'),('284c2477-e2f7-4575-9be4-079eb1a9acbb','2016-12-10 13:44:02','2016-12-19 00:00:00',1,4,1,4,NULL,4,NULL,4,1,3,1,3,1,3,1,3,1,3,1,4,1,4,'2016-12-23 00:00:00','12','20161210-134402','19, 20, 21, 22, 23','tyxq','c0510169-8edc-470e-b038-c299c7735bty',6,NULL,NULL,4,'2016','0afc253f-6ad5-44b8-9a0c-0638359aace1'),('28f5a357-0b6e-48de-9268-ff4c1e3e4fbb','2016-12-10 13:36:03','2016-12-19 00:00:00',1,1,1,1,NULL,1,NULL,1,1,2,1,2,1,2,1,2,1,2,1,1,1,1,'2016-12-23 00:00:00','12','20161210-133603','19, 20, 21, 22, 23','bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs',6,NULL,NULL,1,'2016','5addced0-3ccf-4864-83ca-b14bb3c2862a'),('38151bcc-0e83-493b-b9bf-0de8b905dfc0','2016-12-10 13:44:35','2016-12-12 00:00:00',1,3,1,3,NULL,3,NULL,3,1,3,1,3,1,3,1,3,1,3,1,3,1,3,'2016-12-15 00:00:00','12','20161210-134435','12, 13, 14, 15','tyxq','c0510169-8edc-470e-b038-c299c7735bty',6,NULL,NULL,3,'2016','56431f9d-53cd-4b71-8434-9cbd8c737d36'),('4584eede-958c-4da0-8502-939992c7b913','2016-12-10 13:33:26','2016-12-19 00:00:00',1,1,1,1,NULL,1,NULL,1,1,2,1,2,1,2,1,2,1,2,1,1,1,1,'2016-12-23 00:00:00','12','20161210-133326','19, 20, 21, 22, 23','bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs',6,NULL,NULL,1,'2016','3f4966b5-3b3f-47ca-9c21-e28b73ef86b7'),('74afd35f-df39-476a-9819-0f160bdab870','2016-12-10 13:38:28','2016-12-12 00:00:00',1,4,1,4,NULL,4,NULL,4,1,3,1,3,1,3,1,3,1,3,1,4,1,4,'2016-12-15 00:00:00','12','20161210-133828','12, 13, 14, 15','bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs',6,NULL,NULL,4,'2016','e57707e7-52cf-4b05-b58e-7bfd95f12a4c'),('75734389-6e31-4ba7-aa89-7a0deb9ac6a8','2016-12-10 13:35:10','2016-12-12 00:00:00',1,3,1,3,NULL,3,NULL,3,1,4,1,4,1,4,1,4,1,4,1,3,1,3,'2016-12-16 00:00:00','12','20161210-133510','12, 13, 14, 15, 16','bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs',6,NULL,NULL,3,'2016','5addced0-3ccf-4864-83ca-b14bb3c2862a'),('7c9782ae-9e4b-4c55-8bf6-4c0dd1696e82','2016-12-10 13:38:57','2016-12-19 00:00:00',1,1,1,1,NULL,1,NULL,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,'2016-12-23 00:00:00','12','20161210-133857','19, 20, 21, 22, 23','bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs',6,NULL,NULL,1,'2016','e57707e7-52cf-4b05-b58e-7bfd95f12a4c'),('b07a525c-bcf3-4f7f-8481-922cdbea8134','2016-12-10 13:27:02','2016-12-12 00:00:00',1,3,1,3,NULL,3,NULL,3,1,4,1,4,1,4,1,4,1,4,1,3,1,3,'2016-12-16 00:00:00','12','20161210-132702','12, 13, 14, 15, 16','bsxq','b78ffef2-7c54-40fe-be4b-1910a87c8bbs',6,NULL,NULL,3,'2016','3f4966b5-3b3f-47ca-9c21-e28b73ef86b7'),('f045fabe-ecd7-48a7-9fb5-666de0338a40','2016-12-10 13:43:18','2016-12-12 00:00:00',1,3,1,3,NULL,3,NULL,3,1,2,1,2,1,4,1,4,1,4,1,3,1,3,'2016-12-15 00:00:00','12','20161210-134318','12, 13, 14, 15','tyxq','c0510169-8edc-470e-b038-c299c7735bty',6,NULL,NULL,3,'2016','0afc253f-6ad5-44b8-9a0c-0638359aace1');
/*!40000 ALTER TABLE `tb_tranierarranges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `CID` varchar(36) NOT NULL,
  `CCREATEDATETIME` datetime DEFAULT NULL,
  `CMODIFYDATETIME` datetime DEFAULT NULL,
  `CNAME` varchar(100) NOT NULL,
  `CPWD` varchar(100) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `schoolArea` varchar(36) DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  PRIMARY KEY (`CID`),
  UNIQUE KEY `CNAME` (`CNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('0',NULL,NULL,'admin','21232f297a57a5a743894a0e4a801fc3',NULL,NULL,NULL),('035a5242-e09e-4935-af50-665fd9f8c06b','2016-12-10 13:39:44',NULL,'451024198612011001','e10adc3949ba59abbe56e057f20f883e',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',1),('1ae1213d-d900-441e-9c1f-7cb96ae247c4','2016-12-10 13:40:19',NULL,'451024198612011003','e10adc3949ba59abbe56e057f20f883e',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',1),('5f7c9cb5-6b0f-4a69-8e78-7d48ed550a3a','2016-12-10 13:18:58',NULL,'452625198612018001','e10adc3949ba59abbe56e057f20f883e',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',2),('659633b3-93a8-4dfb-957f-8a0abe7fd109','2016-12-10 12:31:30','2016-12-10 12:31:30','tyxq','e10adc3949ba59abbe56e057f20f883e',NULL,'c0510169-8edc-470e-b038-c299c7735bty',3),('68f791cb-2feb-42fd-9bfb-debb960c4cf1','2016-12-10 13:41:30',NULL,'452625198612013002','e10adc3949ba59abbe56e057f20f883e',NULL,'c0510169-8edc-470e-b038-c299c7735bty',2),('8323799a-ab81-47d2-9f46-bdeddb620fb1','2016-12-10 13:40:04',NULL,'451024198612011002','e10adc3949ba59abbe56e057f20f883e',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',1),('914ff281-f7bd-4027-a93e-75642de7633e','2016-12-10 13:22:24',NULL,'452625198612018003','e10adc3949ba59abbe56e057f20f883e',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',2),('ab6bce44-8130-4ac7-a868-b2d967209531','2016-12-10 13:48:31',NULL,'452526198612011003','e10adc3949ba59abbe56e057f20f883e',NULL,'c0510169-8edc-470e-b038-c299c7735bty',1),('b5fc6b14-037e-42b0-a8c7-9e680af35342','2016-12-10 13:22:05',NULL,'452625198612018002','e10adc3949ba59abbe56e057f20f883e',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',2),('b5fe4b78-3e45-43e0-a18f-7dbb944a35bc','2016-12-10 13:47:38',NULL,'452526198612011001','e10adc3949ba59abbe56e057f20f883e',NULL,'c0510169-8edc-470e-b038-c299c7735bty',1),('bb648bf8-7ed5-45c1-bdaf-eb907ec31dc1','2016-12-10 13:48:06',NULL,'452526198612011002','e10adc3949ba59abbe56e057f20f883e',NULL,'c0510169-8edc-470e-b038-c299c7735bty',1),('bca73d8a-6ad3-4d7d-9301-6be691227d7b','2016-12-10 13:41:57',NULL,'452625198612013003','e10adc3949ba59abbe56e057f20f883e',NULL,'c0510169-8edc-470e-b038-c299c7735bty',2),('c2ef98ca-2dbf-452c-850e-493272135cc0','2016-12-10 12:30:18','2016-12-10 12:30:18','bsxq','e10adc3949ba59abbe56e057f20f883e',NULL,'b78ffef2-7c54-40fe-be4b-1910a87c8bbs',3),('f2c335d9-9f67-4b92-9b62-b72a8f2baad5','2016-12-10 13:41:12',NULL,'452625198612013005','e10adc3949ba59abbe56e057f20f883e',NULL,'c0510169-8edc-470e-b038-c299c7735bty',2);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role`
--

DROP TABLE IF EXISTS `tb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_role` (
  `CID` varchar(36) NOT NULL,
  `CROLEID` varchar(36) DEFAULT NULL,
  `CUSERID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`CID`),
  KEY `FK37884BD9DCDFD2D8` (`CUSERID`),
  KEY `FK37884BD9D78A7D6E` (`CROLEID`),
  CONSTRAINT `FK37884BD9D78A7D6E` FOREIGN KEY (`CROLEID`) REFERENCES `tb_role` (`CID`),
  CONSTRAINT `FK37884BD9DCDFD2D8` FOREIGN KEY (`CUSERID`) REFERENCES `tb_user` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role`
--

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;
INSERT INTO `tb_user_role` VALUES ('05e56dee-d16e-4712-b1d2-29cff23beba5','00695ac0-9d20-4509-aa57-d86532a81250','659633b3-93a8-4dfb-957f-8a0abe7fd109'),('3f24fe0c-366d-4f61-86e1-c75e31410573','0','0'),('7a1947ee-e96c-4f5c-be35-23904bc2ebd7','00695ac0-9d20-4509-aa57-d86532a81250','c2ef98ca-2dbf-452c-850e-493272135cc0');
/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usingcars`
--

DROP TABLE IF EXISTS `tb_usingcars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_usingcars` (
  `id` varchar(36) NOT NULL,
  `comment` longtext,
  `endMile` int(11) DEFAULT NULL,
  `endTime` varchar(20) DEFAULT NULL,
  `numbering` varchar(36) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `reason` longtext,
  `schoolArea` varchar(36) DEFAULT NULL,
  `startMile` int(11) DEFAULT NULL,
  `startTime` varchar(20) DEFAULT NULL,
  `usingDate` datetime DEFAULT NULL,
  `usingMile` int(11) DEFAULT NULL,
  `carId` varchar(36) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B78D12D48AFC01` (`trainerId`),
  KEY `FK4B78D12D0C7347F` (`carId`),
  CONSTRAINT `FK4B78D12D0C7347F` FOREIGN KEY (`carId`) REFERENCES `tb_cars` (`id`),
  CONSTRAINT `FK4B78D12D48AFC01` FOREIGN KEY (`trainerId`) REFERENCES `tb_trainers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usingcars`
--

LOCK TABLES `tb_usingcars` WRITE;
/*!40000 ALTER TABLE `tb_usingcars` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_usingcars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_weixin_menu`
--

DROP TABLE IF EXISTS `tb_weixin_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_weixin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `content` longtext,
  `keyname` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `organizationId` varchar(36) DEFAULT NULL,
  `reorder` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_weixin_menu`
--

LOCK TABLES `tb_weixin_menu` WRITE;
/*!40000 ALTER TABLE `tb_weixin_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_weixin_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_weixin_message`
--

DROP TABLE IF EXISTS `tb_weixin_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_weixin_message` (
  `id` varchar(36) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `fromNickName` varchar(100) DEFAULT NULL,
  `fromUserName` varchar(100) DEFAULT NULL,
  `msgContent` varchar(200) DEFAULT NULL,
  `msgExt` longtext,
  `msgId` bigint(20) DEFAULT NULL,
  `msgType` int(11) DEFAULT NULL,
  `openId` varchar(100) DEFAULT NULL,
  `reply` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `toUserName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_weixin_message`
--

LOCK TABLES `tb_weixin_message` WRITE;
/*!40000 ALTER TABLE `tb_weixin_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_weixin_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_weixin_user`
--

DROP TABLE IF EXISTS `tb_weixin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_weixin_user` (
  `id` varchar(36) NOT NULL,
  `city` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `ext` longtext,
  `fromType` int(11) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `lastMsgContent` longtext,
  `lastMsgTime` datetime DEFAULT NULL,
  `lastVisitTime` datetime DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `msgSum` int(11) DEFAULT NULL,
  `msgUnread` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `openId` varchar(100) NOT NULL,
  `organizationId` varchar(36) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `studentId` varchar(36) DEFAULT NULL,
  `trainerId` varchar(36) DEFAULT NULL,
  `unionId` varchar(100) DEFAULT NULL,
  `visit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_weixin_user`
--

LOCK TABLES `tb_weixin_user` WRITE;
/*!40000 ALTER TABLE `tb_weixin_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_weixin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_link`
--

DROP TABLE IF EXISTS `user_role_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_link` (
  `user_id` varchar(36) NOT NULL,
  `role_id` varchar(36) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK716BA6AFEBBBE0BE` (`role_id`),
  KEY `FK716BA6AF90E6A49E` (`user_id`),
  CONSTRAINT `FK716BA6AF90E6A49E` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`CID`),
  CONSTRAINT `FK716BA6AFEBBBE0BE` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_link`
--

LOCK TABLES `user_role_link` WRITE;
/*!40000 ALTER TABLE `user_role_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role_link` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-12  3:27:39
