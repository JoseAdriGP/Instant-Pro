package com.brogames.xesoku.instant_pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Xesoku on 19/01/2017.
 */

public class ManejadorBD extends SQLiteOpenHelper {
    private static int version = 1;
    private static String name = "InstantGaming";
    private static CursorFactory factory = null;

    public ManejadorBD(Context context) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL("CREATE TABLE JUEGOS(" +
                " id  INTEGER PRIMARY KEY, " +
                " nombre TEXT NOT NULL, " +
                " precio REAL, " +
                " descuento INTEGER, " +
                " plataforma INTEGER, " +
                " keyWord TEXT)");

        db.execSQL("CREATE UNIQUE INDEX nombre ON JUEGOS(nombre ASC)");

        Log.i(this.getClass().toString(), "Tabla JUEGOS creada");

        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(1,'Counter Strike Go', 9.05, 35, 1, 'counter')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(2,'Rocket League', 8.98, 55, 1, 'rocket')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(3,'Dark Souls 3', 24.64, 59, 1, 'dark souls')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(4,'DOOM', 14.97, 75, 1, 'doom')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(5,'The Elder Scrolls V: Skyrim Legendary Edition', 7.38, 84, 1, 'skyrim')");

        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(6,'Battlefield 1', 38.89, 35, 2, 'battlefield')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(7,'Fifa 17', 31.39, 48, 2, 'fifa')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(8,'Los Sims 4', 24.19, 60, 2, 'sim')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(9,'Mass Effect Trilogy', 6.42, 84, 2, 'mas effect')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(10,'Need For Speed Rivals', 5.60, 72, 2, 'need for speed')");

        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(11,'Assassin´s Creed Brotherhood', 2.97, 80, 3, 'assassin')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(12,'Rayman Legends', 3.89, 81, 3, 'rayman')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(13,'Child Of Light', 3.33, 78, 3, 'child')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(14,'Far Cry 3: Blood Dragon', 3.00, 80, 3, 'far cry')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(15,'Might & Magic: Heroes VI', 7.89, 74, 3, 'heroes')");

        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(16,'Overwatch', 37.95, 37, 4, 'overwatch')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(17,'Diablo III + Expansion', 19.19, 36, 4, 'diablo')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(18,'Starcarft II', 18.39, 54, 4, 'star')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(19,'Warcarft III', 3.74, 63, 4, 'dughammer')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(20,'Heroes of the Storm', 5.41, 86, 4, 'hot')");

        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(21,'The Witcher 3', 12.29, 59, 5, 'geralt')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(22,'The Witcher 3: GOTY', 20.25, 59, 5, 'goty')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(23,'Tales of Borderlands', 4.29, 81, 5, 'borderlands')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(24,'Divinity Original Sin', 39.89, 0, 5, 'divinity')");
        db.execSQL("INSERT INTO JUEGOS(id, nombre, precio, descuento, plataforma, keyWord) VALUES(25,'Banished', 5.15, 73, 5, 'banished')");

        Log.i(this.getClass().toString(), "Datos iniciales JUEGOS insertados");
        Log.i(this.getClass().toString(), "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
