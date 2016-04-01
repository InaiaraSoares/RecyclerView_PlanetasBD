package aulas.ddmi.control;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import aulas.ddmi.adapter.PlanetaAdapter;
import aulas.ddmi.dao.Contract;
import aulas.ddmi.recyclerview_planetasbd.R;
import aulas.ddmi.dao.PlanetaDAO;

public class MainActivity extends AppCompatActivity {


    private PlanetaDAO planetaDAO; //acesso ao banco
    private Cursor cursor; //cusor com os dados da fonte de dados
    private RecyclerView recyclerView; //RecyclerView presente no layout associado a esta Activity
    private PlanetaAdapter adapter; //adaptador RecyclerView -> fonte de dados
    SwipeRefreshLayout swipeRefreshLayout; //instância do swipeRefreshLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_recycler_view);

        //mapeira a RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //associa um gerenciador de layout Linear ao recyclerView.
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //associa um tipo de animação ao recyclerView.
        recyclerView.setHasFixedSize(true);


        //acesso ao banco
        planetaDAO = PlanetaDAO.getInstance(this);
        planetaDAO.open();

        cursor = planetaDAO.getLista(); //obtám a lista de planetas da fonte de dados
        adapter = new PlanetaAdapter(this, cursor, onClickPlaneta()); //cria um adaptador RecyclerView -> Fonte de dados. O Terceiro argumento é um método da Atividade que
                                                                    //implementa a interface disponibilizada no adaptador.
        recyclerView.setAdapter(adapter); //associa o adaptador a recyclerView.

        //configura o SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simplerefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
        swipeRefreshLayout.setColorSchemeColors(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);

    }

    /*
        Este método utiliza a interface declarada na classe PlanetaAdapter para tratar o evento onClick do item da lista.
     */
    protected PlanetaAdapter.PlanetaOnClickListener onClickPlaneta() {

        final Intent intent = new Intent(getBaseContext(), PlanetaActivity.class); //intenção de abrir outra atividade.

        return new PlanetaAdapter.PlanetaOnClickListener() { //chama o contrutor da interface (implícito) para cria uma instância da interface declarada no adaptador.
            // Aqui trata o evento onItemClick.
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClickPlaneta(PlanetaAdapter.PlanetasViewHolder holder, int idx) { //concretiza o método da interface.
                cursor.moveToPosition(idx); //coloca o cursor na posição clicada

                intent.putExtra("imgPlaneta", cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Planeta.COLUNA_IMAGEM))); //coloca a imagem como metadados da intent



                startActivity(intent); //solicita ao Android que inicie a nova atividade


            }
        };
    }

    /*
     Este método trata o evento onRefresh() do SwipeRefreshLayout.
     Ele acontece quando o usuário faz um swipe com o dedo para baixo na View.
    */
    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cursor = planetaDAO.getLista(); //obtám a lista de planetas da fonte de dados
                adapter = new PlanetaAdapter(MainActivity.this, cursor, onClickPlaneta()); //cria um adaptador RecyclerView -> Fonte de dados. O Terceiro argumento é um método da Atividade que
                //implementa a interface disponibilizada no adaptador.
                recyclerView.setAdapter(adapter); //associa o adaptador a recyclerView.
                //implementa a interface disponibilizada no adaptador.
                recyclerView.setAdapter(adapter); //associa o adaptador a recyclerView.
                swipeRefreshLayout.setRefreshing(false); //para a animação da swipeRefrech
            }
        };
    }
}
