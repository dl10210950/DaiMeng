package com.duanlian.daimeng.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库
 */

public class DataBase extends SQLiteOpenHelper {
    //数据库名字
    public static final String DB_NAME = "DaiMeng.db";
    //数据库版本
    public static final int DB_VERSION = 1;
    //表名,关注的表,用来储存用户选择过的新闻频道
    public static final String TABLE_NAME = "channel";
    public static DataBase mDataBase;

    /**
     * 单例模式返回数据库
     *
     * @param context 上下文
     * @return 数据库对象
     */
    public static DataBase getInstances(Context context) {
        if (mDataBase == null) {
            return new DataBase(context);
        } else {
            return mDataBase;
        }
    }


    //上下文,数据库名字,数据库工厂,版本号
    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //此方法中创建表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //赛事id,
        sqLiteDatabase.execSQL("create table" + " " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT,channel text);");

    }

    /**
     * 用来更新数据库版本的
     * onCreate方法只是在第一次安装app的时候会调用,之后的数据库要更改的话,必须使用数据库版本上升,或者卸载了重新安装
     *
     * @param sqLiteDatabase 数据库
     * @param oldVersion     老的版本号
     * @param newVersion     更新后的版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            //删除老表
            sqLiteDatabase.execSQL("drop" + TABLE_NAME);
            //重新创建表
            onCreate(sqLiteDatabase);
        }

    }

    /**
     * 创建一个用来插入数据的方法
     */
    public void insert(String channel) {
        //让数据库可写
        SQLiteDatabase database = getWritableDatabase();
        /*
        类似于HashMap 都有键值对
        key 对应的列表中的某一列的名称,字段
        value 对应字段要插入的值
         */
        ContentValues values = new ContentValues();
        values.put("channel", channel);
        //插入
        database.insert(TABLE_NAME, null, values);
        //插入完成后关闭,以免造成内存泄漏
        database.close();

    }


    /**
     * 创建一个查找数据库的方法
     * 各个参数的意义说明：
     * 参数table:表名称
     * 参数columns:列名称数组
     * 参数selection:条件字句，相当于where
     * 参数selectionArgs:条件字句，参数数组
     * 参数groupBy:分组列
     * 参数having:分组条件
     * 参数orderBy:排序列
     * 参数limit:分页查询限制
     * 参数Cursor:返回值，相当于结果集ResultSet
     * Cursor是一个游标接口，提供了遍历查询结果的方法，如移动指针方法move()，获得列值方法getString()等.
     */
    public Cursor query() {
        //数据库可读
        SQLiteDatabase database = getReadableDatabase();
        //查找
        Cursor query = database.query(TABLE_NAME, null, null, null, null, null, null);
        return query;
    }

    /**
     * 创建一个删除数据的方法,传入的参数越多,删除时越精确的找到要删除的哪一行
     */
    public void delete(String channel) {
        SQLiteDatabase database = getWritableDatabase();
        /*
        //当条件满足gameid = 传入的参数的时候,就删除那整行数据,有可能有好几行都满足这个条件,满足的全部都删除
         */
        String where = "channel = ?";
        //删除条件的参数
        String[] whereArgs = {channel + ""};
        database.delete(TABLE_NAME, where, whereArgs);
        database.close();
    }


    /**
     * 创建一个修改数据的方法
     */
    public void updata(String channel) {
        SQLiteDatabase database = getWritableDatabase();
//        update(String table,ContentValues values,String  whereClause, String[]  whereArgs)
        String where = "channel = ?";
        String[] whereArgs = {channel + ""};
        ContentValues values = new ContentValues();
        values.put("channel", channel);
        database.update(TABLE_NAME, values, where, whereArgs);
        database.close();
    }
}
