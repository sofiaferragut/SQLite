package ort.edu.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBContactos {

   //Nombre columnas
    public static final String KEY_FILA_ID="_id";
    public static final String KEY_FILA_NAME="_name";
    public static final String KEY_FILA_EMAIL="_email";

    // Datos de la tabla
    private final String DB_NAME="DBContactos1";
    private final String TABLE_NAME="TablaContactos1";
    private final int DATABASE_VERSION=1;

    //PARAMETROS
    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    //CONSTRUCTOR
    public DBContactos(Context context){
        ourContext=context;
    }

    //CLASE PRIVADA DBHELPER
    private class DBHelper extends SQLiteOpenHelper{

        //CONSTRUCTOR
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DATABASE_VERSION);
        }
        //CREACIÓN DE LA TABLA
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        * CREATE TABLE ContactsTable (
                _id 	INTEGER PRIMARY KEY AUTOINCREMENT,
                _name TEXT NOT NULL,
                _email TEXT NOT NULL
            );
        * */
        String sqlCode= "CREATE TABLE "+TABLE_NAME+"( "+KEY_FILA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        KEY_FILA_NAME+" TEXT NOT NULL, "+KEY_FILA_EMAIL+" TEXT NOT NULL );";
        sqLiteDatabase.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            String sqlCode= "DROP TABLE IF EXISTS "+TABLE_NAME+";" ;
            sqLiteDatabase.execSQL(sqlCode);
            onCreate(sqLiteDatabase);
        }
    }

    public DBContactos open() throws SQLException {
        ourHelper= new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }






    public long insert(String nombre, String email){
        ContentValues cv = new ContentValues();
        cv.put(KEY_FILA_NAME,nombre);
        cv.put(KEY_FILA_EMAIL,email);
        return ourDatabase.insert(TABLE_NAME,null,cv);
    }

    public String[] getData(){

        String[] columnas= new String[]{KEY_FILA_ID,KEY_FILA_NAME,KEY_FILA_EMAIL};

        Cursor c =ourDatabase.query(TABLE_NAME,columnas,null,
                null,null,null,null);

        String[] resultado=new String[c.getCount()];

        int iColId=c.getColumnIndex(KEY_FILA_ID);
        int iColName=c.getColumnIndex(KEY_FILA_NAME);
        int iColEmail = c.getColumnIndex(KEY_FILA_EMAIL);
        int i=0;
        for (c.moveToFirst();!c.isAfterLast(); c.moveToNext()){
            resultado[i]="ID: "+ c.getString(iColId) + " : "+
                      c.getString(iColName)+" - "+c.getString(iColEmail);
            i++;
        }
        return resultado;
    }
    public String getFirstRecord() throws Exception{
        String[] columnas= new String[]{KEY_FILA_ID};
        Cursor c =ourDatabase.query(TABLE_NAME,columnas,null,
                null,null,null,null);
        int iColId=c.getColumnIndex(KEY_FILA_ID);
        if (c.moveToFirst()){
            return c.getString(iColId);
        }
        throw new Exception("Problema para obtener el primer registro de la tabla");
    }

    public long deleteEntry(String rowID){
        return ourDatabase.delete(TABLE_NAME, KEY_FILA_ID + "=?",
                new String[]{rowID} );
    }

    public long updateEntry(String rowID, String name, String email){
        ContentValues cv=new ContentValues();
        cv.put(KEY_FILA_NAME,name);
        cv.put(KEY_FILA_EMAIL,email);
        return ourDatabase.update(TABLE_NAME, cv,KEY_FILA_ID + "=?",
                new String[]{rowID} );
    }
}
