package net.azarquiel.teamsnba.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.teamsnba.R
import net.azarquiel.teamsnba.adapter.CustomAdapter
import net.azarquiel.teamsnba.model.Team
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter
    private lateinit var rvteams: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvteams = findViewById<RecyclerView>(R.id.rvteams)
        initRV()
        getDatos()
    }

    private fun getDatos() {
        GlobalScope.launch {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apinba/teams").readText(Charsets.UTF_8) //IMPORTANTE: PONER EL .READTEXT
            launch (Main){
                val teamsarray = Gson().fromJson(jsontxt, Array<Team>::class.java)
                for (team in teamsarray) {
                    Log.d("Equipo", team.toString())
                }
                adapter.setTeams(teamsarray.toList())
            }
        }
    }

    private fun initRV() {
        adapter = CustomAdapter(this, R.layout.rowteams)
        rvteams.adapter = adapter
        rvteams.layoutManager = LinearLayoutManager(this)
    }


    fun onclickteam(v: View){
        val team = v.tag as Team
        //Toast.makeText(this, "Pulsaste el equipo ${team.name}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, PlayersActivity::class.java) //dentro va desde la actividad que estamos (this) hasta a la que queremos ir
        intent.putExtra("team", team) //hay que serializar las clases para poder pasarlas dentro de un int
        startActivity(intent)

    }
}