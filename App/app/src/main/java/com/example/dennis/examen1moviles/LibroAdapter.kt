package com.example.dennis.examen1moviles

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class PeliculaAdapter(private val peliculasList: List<Pelicula>) : RecyclerView.Adapter<PeliculaAdapter.MyViewHolder>() {

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        var nombre: TextView
        var fechaPublicacion: TextView
        var editorial: TextView
        var detalles: Button
        lateinit var pelicula: Pelicula

        init {
            nombre = view.findViewById(R.id.txt_1) as TextView
            fechaPublicacion = view.findViewById(R.id.txt_2) as TextView
            editorial = view.findViewById(R.id.txt_3) as TextView
            detalles = view.findViewById(R.id.btn_1)as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.item_menu_compartir, Menu.NONE, R.string.menu_share)
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, R.string.menu_edit)
            menu?.add(Menu.NONE, R.id.item_menu_borrar, Menu.NONE, R.string.menu_delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pelicula = peliculasList[position]
        holder.nombre.text = pelicula.nombre
        holder.fechaPublicacion.text = pelicula.fechaPublicacion
        holder.editorial.text = pelicula.nombreEditorial
        holder.pelicula = pelicula
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetailsMovieActivity::class.java)
            intent.putExtra("pelicula", pelicula)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return peliculasList.size
    }

}