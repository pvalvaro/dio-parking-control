package api.parking.control.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import api.parking.control.model.Parking;

public interface ParkingRepository extends JpaRepository<Parking, String>{

}
