package com.example.splash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaJugadoresAdapter extends RecyclerView.Adapter<ListaJugadoresAdapter.ContactoViewHolder> {
    ArrayList<JugadoresDTO> listaJugadores;
    ArrayList<JugadoresDTO> listaOriginal;// esta es otra lista para cuando buscamos un registro


    public ListaJugadoresAdapter(ArrayList<JugadoresDTO> listaJugadores) {
        this.listaJugadores = listaJugadores;
        listaOriginal= new ArrayList<>();
        listaOriginal.addAll(listaJugadores);//le agregamos lista contactos porque es donde tenemos los datos.

    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_principal, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewPais.setText(listaJugadores.get(position).getPais());
        holder.viewRegion.setText(listaJugadores.get(position).getRegion());
        holder.viewCapitan.setText(listaJugadores.get(position).getCapitan());
        holder.viewRanking.setText(listaJugadores.get(position).getRanking());
        holder.viewMundiales.setText(listaJugadores.get(position).getMundiales_Ganados());
    }
    public void filtrado(String txtbuscar){
        //hacemos validacion
        int longitud = txtbuscar.length();
        if (longitud == 0) {
            listaJugadores.clear();
            listaJugadores.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<JugadoresDTO> collecion = listaJugadores.stream()
                        .filter(i -> i.getPais().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());//por si el usuario ingresa minisculas
                listaJugadores.clear();
                listaJugadores.addAll(collecion);
            } else {
                for (JugadoresDTO c : listaOriginal) {
                    if (c.getPais().toLowerCase().contains(txtbuscar.toLowerCase())) {
                        listaJugadores.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewPais, viewRegion, viewCapitan,viewRanking,viewMundiales;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPais = itemView.findViewById(R.id.viewPais);
            viewRegion= itemView.findViewById(R.id.viewRegion);
            viewCapitan = itemView.findViewById(R.id.viewCapitan);
            viewRanking = itemView.findViewById(R.id.viewRanking);
            viewMundiales = itemView.findViewById(R.id.viewMundiales);
// al momento de darle clic se dispara este evento de editar
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//cuando le demos click nos va enviar a los detalles de la informacion
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);//cuando demos click que se vaya a esa clase
                    intent.putExtra("ID", listaJugadores.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });

        }
    }
}
