package com.sillaps.intern.planner.problems.vehicleRouting

import com.sillaps.intern.planner.model.Location
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator


class ScoreVehicleRouting: EasyScoreCalculator<VehicleRoutingProblem> {
    override fun calculateScore(solution: VehicleRoutingProblem?): HardSoftLongScore {
        val customerList = solution!!.customers
        val vehicleList = solution.vehicles

        val vehicleDemandMap = HashMap<Vehicle, Int>(vehicleList!!.size)
        for (vehicle in vehicleList) {
            vehicleDemandMap.put(vehicle, 0)
        }
        var hardScore = 0L
        var softScore = 0L
        for (customer in customerList!!) {
            val previousStandstill = customer.previousCustomer
            if (previousStandstill != null) {
                val vehicle = customer.vehicle
                vehicleDemandMap.put(vehicle!! , vehicleDemandMap.get(vehicle)!!.plus(customer.demand!!))
                // Score constraint distanceToPreviousStandstill
                softScore -= customer.getDistanceTo(customer.previousCustomer as Location)
                if (customer.nextCustomer== null) {
                    // Score constraint distanceFromLastCustomerToDepot
                    softScore -= customer.getDistanceTo(vehicle.depot!!)
                }
            }
        }
        for (entry in vehicleDemandMap.entries) {
            val capacity = entry.key.capacity
            val demand = entry.value
            if (demand > capacity!!) {
                // Score constraint vehicleCapacity
                hardScore -= (demand - capacity).toLong()
            }
        }
        // Score constraint arrivalAfterDueTimeAtDepot is a built-in hard constraint in VehicleRoutingImporter
        return HardSoftLongScore.valueOf(hardScore, softScore)
    }

    }

