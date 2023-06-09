package com.example.xtypee.Service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.xtypee.contract.UsuariosContract;

public class UsuariosDBService extends SQLiteOpenHelper {
    public static final String TAG = "UsuariosDBService";
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "REGISTROS.db";

    public static final String TABLE_USERS = "t_usuarios";
    public static final String TABLE_CONTRA = "t_contras";

    public UsuariosDBService(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UsuariosContract.UsuarioEntry.getCreateTable());
        sqLiteDatabase.execSQL(UsuariosContract.MyDataEntry.getCreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTRA);
        onCreate(sqLiteDatabase);
    }
}
