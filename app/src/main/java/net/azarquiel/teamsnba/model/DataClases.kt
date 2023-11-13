package net.azarquiel.teamsnba.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
        var id:String,
        var name: String,
        var conference: String,
        var record: String,
        var teamLogoUrl: String):Serializable

    data class Player(
        var id:String,
        var firstName: String,
        var lastName: String,
        var team: String,
        var position: String,
        var dateOfBirth: String,
        var height: String,
        var weight: String,
        var jerseyNumber: String,
        var age: Int,
        var headShotUrl: String)




