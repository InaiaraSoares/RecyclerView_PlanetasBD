package aulas.ddmi.dao;

/**
 * Created by Vagner on 17/08/2015.
 */

import android.provider.BaseColumns;

/**
 * Esta classe define o schema do banco de dados.
 * A vantagem de se criar uma classe de contrato está na manutenção da aplicação. Pois
 * toda mudança que se fizer na estrutura do banco de dados (nessa classe) irá se
 * propagar pela aplicação.
 *
 * @author Vagner
 */
public final class Contract {
    public static final String BD_NOME = "planetas.db"; //nome do banco de dados
    public static final int BD_VERSAO = 1; //versão do banco (útil para alterações no banco de dados)

    //para impedir que acidentalmente se instancie a classe Contract dá-se a ela um construtor privado
    private Contract() {
    }

    /**
     * Cada tabela deve estar em uma classe interna e implementar BaseColumns.
     *
     * @author Vagner
     */
    public static abstract class Planeta implements BaseColumns {
        public static final String TABELA_NOME = "planetas";
        public static final String COLUNA_ID = "_id";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_IMAGEM = "id_r_imagem";

    }

}

