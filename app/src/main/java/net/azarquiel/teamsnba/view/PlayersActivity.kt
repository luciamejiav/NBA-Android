package net.azarquiel.teamsnba.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.teamsnba.R
import net.azarquiel.teamsnba.adapter.CustomAdapterPlayer
import net.azarquiel.teamsnba.model.Player
import net.azarquiel.teamsnba.model.Team
import java.net.URL

class PlayersActivity : AppCompatActivity() {

    private lateinit var team: Team
    lateinit var adapter: CustomAdapterPlayer
    lateinit var rvplayers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_players)

        team = intent.getSerializableExtra("team") as Team
        rvplayers = findViewById<RecyclerView>(R.id.rvplayers)

        Toast.makeText(this, "Pulsaste el equipo ${team.name}", Toast.LENGTH_SHORT).show()

        initRVPlayer()
        getDatosPlayer()
    }

    private fun initRVPlayer() {
        adapter = CustomAdapterPlayer(this, R.layout.activity_players)
        rvplayers.adapter = adapter
        rvplayers.layoutManager = LinearLayoutManager(this)
    }

    private fun getDatosPlayer() {
        GlobalScope.launch {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apinba/players/team?name=${team.name}").readText(Charsets.UTF_8) //IMPORTANTE: PONER EL .READTEXT
            launch (Dispatchers.Main){
                val playersarray = Gson().fromJson(jsontxt, Array<Player>::class.java)
                for (player in playersarray) {
                    Log.d("Jugador", player.toString())
                }
                adapter.setPlayers(playersarray.toList())
            }
        }
    }
}