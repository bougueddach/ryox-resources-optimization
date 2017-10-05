package com.sillaps.intern.planner.services

import com.sillaps.intern.planner.PlacesData
import com.sillaps.intern.planner.model.Location
import com.sillaps.intern.planner.problems.tsp.Deliverer
import com.sillaps.intern.planner.problems.tsp.Place
import com.sillaps.intern.planner.problems.tsp.TspProblem
import org.optaplanner.core.api.solver.SolverFactory
import org.springframework.stereotype.Service


/**
 * Optmization Resource Service
 */
@Service
class ORService {

    companion object {
        // TODO: Créer en Java la config (Drools + Solver)
        val TSP_SOLVER_FACTORY = SolverFactory.createFromXmlResource<TspProblem>("solvers/tsp/solver.xml")!!
        val TSP_SOLVER = TSP_SOLVER_FACTORY.buildSolver()!!
        // TODO: More solvers ...
        // val VR_SOLVER_FACTORY = SolverFactory.createFromXmlResource<VehicleRoutingProblem>("solvers/vehicleRouting/solver.xml")!!
        // val VR_SOLVER = VR_SOLVER_FACTORY.buildSolver()!!

    }
    // fun vehicleRouting(customers: List<Customer> , vehicles: List<Vehicle>) = VR_SOLVER.solve(VehicleRoutingProblem(customers, vehicles))
    // TODO: More solvers

    fun tsp(places: List<Place>): List<Location> {
        PlacesData.createData(places)
        var tspSolution = TSP_SOLVER.solve(TspProblem(places))
        tspSolution!!.places!!.first { it.origin == true }.previousPoint = null
        var lastPlace = Place()
        for (eachPlace in tspSolution.places!!) {
            var cpt: Int = 0
            var p = eachPlace
            while (p.previousPoint != null) {
                p = p.previousPoint!!
                cpt++
                if (cpt == places.size - 1) {
                    lastPlace = eachPlace
                }
            }
        }
        var result = mutableListOf<Location>()
        while (lastPlace.previousPoint != null) {
            result.add(0,lastPlace)
            lastPlace = lastPlace.previousPoint!!
        }
        result.add(0,tspSolution!!.places!!.first { it.origin == true })
        return result
    }

    /*fun getVisitsListByDeliverer(places:MutableList<Place> , deliverers: List<Deliverer>):List<Deliverer>
    {
        for (deliverer in deliverers)
        {
            if(places.size > 1 )
            {
                var tspList = tsp(places)
                deliverer.placesTovisitInOrder.add(tspList!!.places!!.first { it.origin == true})
                var time = 0L
                while (time <= (deliverer.workHours!! - deliverer.timeSpent) && places.size > 1)
                {
                    var place = deliverer.placesTovisitInOrder.last()
                    time = 0L
                    time += place.getTimeFromPreviousPoint()
                    time += place.previousPoint!!.duration
                    if(time <= (deliverer.workHours!! - deliverer.timeSpent))
                    {
                        deliverer.placesTovisitInOrder.add(place.previousPoint!!)
                        deliverer.timeSpent += time
                        var first = places.first { it.longitude == place.previousPoint!!.longitude && it.latitude == place.previousPoint!!.latitude}
                        if( first != null){
                            places.remove(first)
                        }
                    }
                }
            }
        }
        return deliverers
    }*/


}