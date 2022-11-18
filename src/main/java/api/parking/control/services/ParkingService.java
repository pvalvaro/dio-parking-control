package api.parking.control.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import api.parking.control.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap<>();
	
	static {
		var id = getUUID();
		Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
		parkingMap.put(id, parking);
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList()); //pega tudo que esta no map e transforma em uma lista
	}
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
