package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alienware on 2016/11/24.
 */

public class DBhelper extends SQLiteOpenHelper {
    static String name="user.db";
    static int version=1;

    public  DBhelper(Context context){
        super(context,name,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql="create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
}
