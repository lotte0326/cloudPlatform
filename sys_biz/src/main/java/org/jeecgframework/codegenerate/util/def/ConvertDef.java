//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.codegenerate.util.def;

public interface ConvertDef {
    String FIELD_NULL_ABLE_Y = "Y";
    String FIELD_NULL_ABLE_N = "N";
    String DATABASE_TYPE_MYSQL = "mysql";
    String DATABASE_TYPE_ORACLE = "oracle";
    String DATABASE_TYPE_SQL_SERVER = "sqlserver";
    String DATABASE_TYPE_postgresql = "postgresql";
    String mysql_db_sql = "select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}";
    String oracle_db_sql = " select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}";
    String sqlserver_db_sql = "select cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,\'\'\'Precision\'\'\') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,\'\'\'Scale\'\'\') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then \'\'\'y\'\'\' else \'\'\'n\'\'\' end) nullable   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join sys.objects c on a.object_id=c.object_id and c.type=\'\'\'U\'\'\' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0}";
    String PostgreSQL_db_sql = "SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ";
    String mysql_db_sql_queryName = "select distinct table_name from information_schema.columns where table_schema = {0}";
    String oracle_db_sql_queryName = " select distinct colstable.table_name as  table_name from user_tab_cols colstable order by colstable.table_name";
    String sqlserver_db_sql_queryName = "select distinct c.name as  table_name from sys.objects c ";
    String PostgreSQL_db_sql_queryName = "SELECT distinct c.relname AS  table_name FROM pg_class c";
}
