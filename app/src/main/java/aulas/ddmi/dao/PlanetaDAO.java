package aulas.ddmi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import aulas.ddmi.modelo.Planeta;

/**
 * Created by Vagner on 17/08/2015.
 */
public class PlanetaDAO {
    //atributos e/ou constantes da classe
    private static PlanetaDAO persDAO; //objeto da própria classe
    private final Context context; //o contexto da aplicação
    private DBHelper myHelper; //objeto que auxilia na criação e/ou upgrade do banco de dados
    private SQLiteDatabase database; //objeto de conexão com o banco de dados
    private Cursor listaByNome;

    /**
     * Método construtor privado para implementar o padrão de projeto Singleton.
     *
     * @param context
     */
    private PlanetaDAO(Context context) {
        this.context = context; //recebe o contexto da Activity que o chamou
    }

    /**
     * Método responsável por obter a instância do objeto ou contruí-lo, se este ainda não tiver sido contruído.
     *
     * @param context
     * @return persDAO
     */
    public static PlanetaDAO getInstance(Context context) {
        if (persDAO == null) {
            persDAO = new PlanetaDAO(context);
            return persDAO;
        }

        return persDAO;
    }

    /**
     * Método responsável por abrir uma conexão com o banco de dados.
     */
    public void open() {
        myHelper = DBHelper.getInstance(context); //instância um objeto que auxilia na criação e/ou atualização
        database = myHelper.getWritableDatabase(); //devolve uma conexão para o banco de dados
    }

    /**
     * Método responsável por liberar a conexão com o banco de dados.
     */
    public void close() {
        database.close(); //libera o recurso
    }

    /**
     * Método para salvar um Planeta (o que inclui as operacoes Inserir e Alterar).
     *
     * @param planeta
     * @return Boolean
     */
    public Boolean salvar(Planeta planeta) {
        if (planeta._id== null) {
            return inserir(planeta); //se não tem id
        } else {
            return alterar(planeta); //se tem id
        }
    }

    /**
     * Método para inserir um Planeta.
     *
     * @param planeta
     * @return Boolean
     */
    public Boolean inserir(Planeta planeta) {
        ContentValues valores = new ContentValues(); //objeto tupla (label da coluna, valor)

        valores.put(Contract.Planeta.COLUNA_NOME, planeta.nome); //insere valor na tupla
        valores.put(Contract.Planeta.COLUNA_IMAGEM, planeta.id_r_imagem); //insere o valor (a imagem) na tupla)

        database.insert(Contract.Planeta.TABELA_NOME, null, valores); //insere no banco de dados

        return true; //se inseriu
    }

    /**
     * Método para alterar um Planeta.
     *
     * @param planeta
     * @return Boolean
     */
    public Boolean alterar(Planeta planeta) {
        ContentValues valores = new ContentValues(); //objeto tupla (label da coluna, valor)

        valores.put(Contract.Planeta.COLUNA_NOME, planeta.nome); //insere valor na tupla
        valores.put(Contract.Planeta.COLUNA_IMAGEM, planeta.id_r_imagem); //insere o valor (a imagem) na tupla)

        database.update(Contract.Planeta.TABELA_NOME, valores, Contract.Planeta.COLUNA_ID + " = " + planeta._id, null); //altera o valor

        return true; //se alterou
    }

    /**
     * Método para excluir um Planeta do banco de dados.
     * @param planeta
     * @return
     */
    public Integer excluir(Planeta planeta) {
        //exclui no banco de dados
        return database.delete(Contract.Planeta.TABELA_NOME, Contract.Planeta.COLUNA_ID + " = " + planeta._id, null);
    }

    /**
     * Método para obter a lista de Planetas, sem filtros.
     *
     * @return Cursor
     */
    public Cursor getLista() {
        //retorna o Cursor para os registros contidos no banco de dados
        return database.rawQuery("SELECT  * FROM " + Contract.Planeta.TABELA_NOME, null);
    }

    /**
     *  Método para obter a lista de Planetas, baseado no criterio "nome"
     * @param nome
     * @return
     */
    public Cursor getListaByNome(String nome) {
        //retorna o Cursor para os registros contidos no banco de dados baseado no criterio "nome"
        return database.rawQuery("SELECT  * FROM " + Contract.Planeta.TABELA_NOME + " WHERE nome LIKE '" + nome + "%'", null);
    }
}
