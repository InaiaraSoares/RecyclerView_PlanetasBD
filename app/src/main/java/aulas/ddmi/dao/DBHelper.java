package aulas.ddmi.dao;

/**
 * Created by Vagner on 17/08/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import aulas.ddmi.recyclerview_planetasbd.R;

/**
 * Classe responsável pela criação e upgrade do banco de dados no dispositivo.
 *
 * @author Vagner
 */
public class DBHelper extends SQLiteOpenHelper {

    // atributos e/ou constantes da classe
    private static DBHelper myHelper; // objeto que auxilia na criação e/ou
    // upgrade do banco de dados

    /**
     * Método construtor privado para implementar o padrão de projeto Singleton.
     *
     * @param context
     */
    private DBHelper(Context context) {
        super(context, Contract.BD_NOME, null, Contract.BD_VERSAO);
    }

    /**
     * Método responsável por obter a instância do objeto ou contruí-lo, se este
     * ainda não tiver sido contruído.
     *
     * @param context
     * @return ctoDAO
     */
    public static DBHelper getInstance(Context context) {
        if (myHelper == null) {
            myHelper = new DBHelper(context);
            return myHelper;
        }

        return myHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //cria a tabela personagem
        String sql = "create table " + Contract.Planeta.TABELA_NOME + " ("
                + Contract.Planeta.COLUNA_ID + " integer primary key autoincrement,"
                + Contract.Planeta.COLUNA_NOME + " text,"
                + Contract.Planeta.COLUNA_IMAGEM + " integer);";

        db.execSQL(sql);
        Log.i("Exemplo", "Executou o script de criação da tabela " + Contract.Planeta.TABELA_NOME);

        //insere registros na tabela Personagem
        ContentValues personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Mercúrio");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_01_mercurio);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Vênus");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_02_venus);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Terra");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_03_terra);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Marte");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_04_marte);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Júpiter");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_05_jupiter);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Saturno");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_06_saturno);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Urano");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_07_urano);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Neptuno");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_08_neptuno);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

        personagem = new ContentValues();
        personagem.put(Contract.Planeta.COLUNA_NOME, "Plutão");
        personagem.put(Contract.Planeta.COLUNA_IMAGEM, R.drawable.planeta_09_plutao);
        db.insertOrThrow(Contract.Planeta.TABELA_NOME, null, personagem);
        Log.i("Exemplo", "Executou o script de inserção em " + Contract.Planeta.TABELA_NOME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("aulas", "Upgrade da versão " + oldVersion + " para "
                + newVersion + ", destruindo tudo.");
        db.execSQL("DROP TABLE IF EXISTS " + Contract.Planeta.TABELA_NOME);
        onCreate(db); // chama onCreate e recria o banco de dados
        Log.i("aulas", "Executou o script de upgrade da tabela " + Contract.Planeta.TABELA_NOME);
    }

}
