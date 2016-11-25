package Data;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alienware on 2016/11/24.
 */

public class UserSet {

        public DBhelper dBhelper;
        public UserSet(Context context){
            dBhelper=new DBhelper(context);
        }
        public boolean login(String username,String userpwd){
            SQLiteDatabase db=dBhelper.getReadableDatabase();
            String sql="select * from user where username=? and password=?";
            Cursor cursor=db.rawQuery(sql,new String[]{username,userpwd});
            if (cursor.moveToFirst()){
                cursor.close();
                return true;
            }else {
                return false;}
        }
        public boolean register(User user){
            SQLiteDatabase db=dBhelper.getReadableDatabase();
            String sql="insert into user(username,password) values(?,?)";
            Object object[]={user.getUsername(),user.getPassword()};
            db.execSQL(sql,object);
            return true;
        }
    }

