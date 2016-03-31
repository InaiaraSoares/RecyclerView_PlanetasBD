package aulas.ddmi.adapter;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import aulas.ddmi.dao.Contract;
import aulas.ddmi.recyclerview_planetasbd.R;


/*
    Adaptador dados - RecyclerView.
    Neste exeplo os dados vem de uma lista de objetos planetas.
 */
// Herda de RecyclerView.Adapter e declara o tipo genérico <PlanetaAdapter.PlanetasViewHolder>
public class PlanetaAdapter extends RecyclerView.Adapter<PlanetaAdapter.PlanetasViewHolder> {
    protected static final String TAG = "RecyclerView_PlanetasBD"; //TAG para uso no LogCat
    private final Cursor cursor; //fonte de dados, um cursor do BD
    private final Context context; //contexto da app
    private final PlanetaOnClickListener onClickListener; //tratador do evento onClick no item da RecyclerView

    //interface para o tratamento do entento onClick no item da lista
    /*
        A classe RecyclerView não implementa o tratamento de eventos onItemClick, como a ListView. Logo, uma forma de abortdar
        este problema é criar uma interface no adaptador para que a classe que o utilizar possa ter acesso a este tipo de tra-
        tamento de eventos.
     */
    public interface PlanetaOnClickListener {
        public void onClickPlaneta(PlanetasViewHolder holder, int idx);
    }

    //construtor da classe Adapter. Recebe o contexto, a fonte de dados, e o ouvinte do envento onClick
    public PlanetaAdapter(Context context, Cursor cursor, PlanetaOnClickListener onClickListener) {
        this.context = context;
        this.cursor = cursor;
        this.onClickListener = onClickListener;
    }

    //o Android irá chamar este método para criar um ViewHolder
    @Override
    public PlanetasViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view definida no arquivo de layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_planeta, viewGroup, false);

        PlanetasViewHolder viewHolder = new PlanetasViewHolder(view); // Passa esta View para o ViewHolder

        return viewHolder; //retorna o ViewHolder construído na classe interna PlanetasViewHolder
    }

    //o Android irá chamar este método para vincular os dados à View do ViewHolder
    //o ViewHolder recebido no primeiro parâmetro é o viewHolder devolvido no método onCreateViewHolder
    @Override
    public void onBindViewHolder(final PlanetasViewHolder viewHolder, final int position) {
        // utilza o índice atual da RecyclerView para obter os dados na fonte de dados
        cursor.moveToPosition(position); //posiciona o cursor no registro indexado pela posição atual
        // Atualizada os valores nas views
        viewHolder.tNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Planeta.COLUNA_NOME))); //utiliza este objeto para passar os dados da fonte de dados para a ViewHolder
        viewHolder.img.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Planeta.COLUNA_IMAGEM))); //utiliza este objeto para passar os dados da fonte de dados para a ViewHolder

        // Associa um tratador de eventos a View do Item
        if (onClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { //associa um tratador de eventos a View atual no ViewHolder
                @Override
                public void onClick(View v) { //trata o evento
                    // repassa esta atividade para o método da interface. Assim, o que for definido na classe que a utilizar é o que estará valendo.
                    onClickListener.onClickPlaneta(viewHolder, position);
                }
            });
        }
    }

    //retorna a quantidade de ViewHolder
    @Override
    public int getItemCount() {
        return this.cursor != null ? this.cursor.getCount() : 0;
    }

    // Subclasse de RecyclerView.ViewHolder. Contém as Views para cada item.
    public static class PlanetasViewHolder extends RecyclerView.ViewHolder {
        TextView tNome;
        ImageView img;
        ProgressBar progress;
        private View view;

        public PlanetasViewHolder(View view) {
            super(view);
            this.view = view;
            // Cria as views no ViewHolder
            tNome = (TextView) view.findViewById(R.id.tNome);
            img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progress);
        }
    }


}