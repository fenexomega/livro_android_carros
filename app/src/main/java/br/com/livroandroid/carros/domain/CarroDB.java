package br.com.livroandroid.carros.domain;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

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
}
