package api.parking.control.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import api.parking.control.exception.ParkingNotFoundException;
import api.parking.control.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap<>();
	
	static {
		var id = getUUID();
		var id1 = getUUID();
		Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
		//Parking parking1 = new Parking(id1, "PLO-2H15", "BA", "RENAULT", "PRATA");
		parkingMap.put(id, parking);
		//parkingMap.put(id1, parking1);
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList()); //pega tudo que esta no map e transforma em uma lista
	}
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public Parking findById(String id) {
		Parking parking = parkingMap.get(id);
		if(parking == null) {
			throw new ParkingNotFoundException(id);
		}
		return parking;
	}
	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		return parkingCreate;
	}
	public void delete(String id) {
		Parking parking = findById(id);
		parkingMap.remove(id);
	}
	
	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parkingMap.replace(id, parking);
		return parking;
	}
	public Parking exit(String id) {
		//recuperar o estacionado
		//atualizar data saida
		//calcular valor
		return null;
	}
}
