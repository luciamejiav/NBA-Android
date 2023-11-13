package net.azarquiel.teamsnba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.teamsnba.R
import net.azarquiel.teamsnba.model.Team

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Team> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setTeams(teams: List<Team>) {
        this.dataList = teams
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Team){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivrowteam = itemView.findViewById(R.id.ivteam) as ImageView
            val tvnombrerow = itemView.findViewById(R.id.tvname) as TextView
            val tvconference = itemView.findViewById(R.id.tvconference) as TextView
            val tvrecord = itemView.findViewById(R.id.tvrecord) as TextView


            tvnombrerow.text = dataItem.name
            tvconference.text = dataItem.conference
            tvrecord.text = dataItem.record

            // foto de internet a traves de Picasso
            val foto = "${dataItem.teamLogoUrl}"
            Picasso.get().load(foto).into(ivrowteam) //teamLogoUrl es la url que llamamos de dataclass

            itemView.tag = dataItem //no quitar nunca aunque no se necesite

        }
    }
}