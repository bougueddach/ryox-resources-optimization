package com.sillaps.intern.planner.problems.tsp


import com.sillaps.intern.planner.model.Location
import org.optaplanner.core.api.score.buildin.simplelong.SimpleLongScore
import org.optaplanner.core.impl.score.director.incremental.IncrementalScoreCalculator

class ScoreTsp: IncrementalScoreCalculator<TspProblem> {

    var score: Long= 0L
    var origin: Place?=null
    lateinit var places : Collection<Place>


    override fun resetWorkingSolution(workingSolution: TspProblem?) {
        this.places = workingSolution!!.places!!
        origin = workingSolution.places!!.first { it.origin == true }
        for(place in workingSolution.places!!){
            score + updateScore(place)
        }
    }

    override fun beforeVariableChanged(entity: Any, variableName: String) {
        score -= updateScore(entity as Place)
    }

    override fun afterVariableChanged(entity: Any?, variableName: String?) {
        score += updateScore(entity as Place)
    }

    override fun beforeEntityRemoved(entity: Any?) {
        println("")
    }

    override fun afterEntityRemoved(entity: Any?) {
        println("")
    }

    override fun afterEntityAdded(entity: Any?) {
        println("")
    }

    override fun beforeEntityAdded(entity: Any?) {
        println("")
    }

    fun updateScore(place: Place): Long {
        var localScore = 0L
        if (place.previousPoint != null) {
            localScore -= place.getDistanceFromPreviousPoint()
            localScore += place.previousPoint!!.getDistanceTo(origin as Location)
        }
        if (place.previousPoint != null && place.previousPoint != place) {
            localScore -= place.getDistanceTo(origin as Location)
        }
        return localScore
    }

    override fun calculateScore(): SimpleLongScore {
        return SimpleLongScore.valueOf(score)
    }

}