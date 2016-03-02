package br.com.livroandroid.carros.domain;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordy on 1/13/16.
 */
public class CarroDB extends SQLiteOpenHelper {
    public static final String TAG = "sql";
    // Nome do banco
    public static final String NOME_BANCO = "livro_android.sqlite";
    private static final int VERSAO = 1;

    public CarroDB(Context context) {
        super(context, NOME_BANCO,null, VERSAO);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a tabela dos Carros");
        db.execSQL("create table if not exists carro (_id integer primary key autoincrement," +
                " nome text, desc text, url_foto text, url_info text, " +
                "url_video text, latitude text, longitude text, tipo text);");
        Log.d(TAG, "Tabela de carros criada com sucesso!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso mude a versão do banco de dados, podemos executar um sql aqui.
    }

    public long save(Carro carro)
    {
        long id = carro.id;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome",carro.nome);
            values.put("desc",carro.desc);
            values.put("url_foto",carro.urlFoto);
            values.put("url_info",carro.urlInfo);
            values.put("url_video",carro.urlVideo);
            values.put("latitude",carro.latitude);
            values.put("longitude",carro.longitude);
            values.put("tipo",carro.tipo);
            if(id != 0)
            {
                String _id = String.valueOf(carro.id);
                String[] whereArgs = new String[]{_id};
                // update carro set values = ... where _id=?
                int count = db.update("carro",values,"_id=?",whereArgs);
                return count;
            }
            else
            {
                // insert into carros values ...
                id =  db.insert("carros","",values);
                return id;
            }

        }
        finally {
            db.close();
        }

    }

    public int delete(Carro carro)
    {
        SQLiteDatabase db = getWritableDatabase();
        try
        {
            //delete from carro where _id=?
            int count = db.delete("carro","_id=?",new String[]{String.valueOf(carro.id)});
            Log.i(TAG, "Deletou o carro de id = " + count);
            return count;
        }
        finally {
            db.close();
        }
    }

    public int deleteCarrosByTipo(String tipo)
    {
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.query("carro", null, null, null, null, null, null);
            return toList(c);
        }
        finally {
            db.close();
        }
    }

    public List<Carro> findAllByTipo(String tipo)
    {
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor c = db.query("carro",null,"tipo = '" + tipo + "'",null,null,null,null);
            return toList(c);
        }
        finally {
            db.close();
        }
    }

    // Lê o cursor e cria a lista de carros
    private List<Carro> toList(Cursor c)
    {
        List<Carro> carros = new ArrayList<Carro>();
        if (c.moveToFirst())
        {
            do {
                Carro carro = new Carro();
                carros.add(carro);
                //recupera os atributos do carro
                carro.id        = c.getLong(c.getColumnIndex("_id"));
                carro.nome      = c.getString(c.getColumnIndex("nome"));
                carro.desc      = c.getString(c.getColumnIndex("desc"));
                carro.urlInfo   = c.getString(c.getColumnIndex("urlInfo"));
                carro.urlFoto   = c.getString(c.getColumnIndex("urlFoto"));
                carro.urlVideo  = c.getString(c.getColumnIndex("urlVideo"));
                carro.latitude  = c.getString(c.getColumnIndex("latitude"));
                carro.longitude = c.getString(c.getColumnIndex("longitude"));
                carro.tipo      = c.getString(c.getColumnIndex("tipo"));

            } while(c.moveToNext());
        }
        return carros;
    }

    //Executa um SQL
    public void execSQL(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.execSQL(sql);
        }
        finally {
            db.close();
        }
    }

    public void execSQL(String sql, Object[] args)
    {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql,args);
        }
        finally {
            db.close();
        }
    }
}
