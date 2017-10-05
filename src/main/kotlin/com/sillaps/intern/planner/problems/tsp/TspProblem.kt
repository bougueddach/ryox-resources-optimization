package com.sillaps.intern.planner.problems.tsp

import com.sillaps.intern.planner.problems.tsp.Place
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.simplelong.SimpleLongScore

@PlanningSolution
class TspProblem {

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "places")
    var places: List<Place>? = null //Solution

    @Suppress("unused")
    @PlanningScore
    lateinit var score: SimpleLongScore

    @ProblemFactProperty
    var origin: Place? = null //Problem


    constructor()

    constructor(places: List<Place>) {
        this.places = places
        this.origin = places.first { it.origin == true }
    }

}