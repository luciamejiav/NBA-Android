package net.azarquiel.teamsnba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.teamsnba.R
import net.azarquiel.teamsnba.model.Player

class CustomAdapterPlayer(val context: Context,
                          val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterPlayer.ViewHolder>() {

    private var dataList: List<Player> = emptyList()

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

    internal fun setPlayers(players: List<Player>) {
        this.dataList = players
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Player){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivrowplayer = itemView.findViewById(R.id.ivplayer) as ImageView
            val tvfirstnameplayer = itemView.findViewById(R.id.tvfirstnameplayer) as TextView
            val tvlastnameplayer = itemView.findViewById(R.id.tvlastnameplayer) as TextView
            val tvteamplayer = itemView.findViewById(R.id.tvteamplayer) as TextView
            val tvjerseyNumberplayer = itemView.findViewById(R.id.tvjerseyNumberplayer) as TextView
            val tvpositionplayer = itemView.findViewById(R.id.tvpositionplayer) as TextView


            tvfirstnameplayer.text = dataItem.firstName
            tvlastnameplayer.text = dataItem.lastName
            tvteamplayer.text = dataItem.team
            tvjerseyNumberplayer.text = dataItem.jerseyNumber
            tvpositionplayer.text = dataItem.position


            // foto de internet a traves de Picasso
            val foto = "${dataItem.headShotUrl}"
            Picasso.get().load(foto).into(ivrowplayer) //teamLogoUrl es la url que llamamos de dataclass

            itemView.tag = dataItem //no quitar nunca aunque no se necesite

        }


    }
}