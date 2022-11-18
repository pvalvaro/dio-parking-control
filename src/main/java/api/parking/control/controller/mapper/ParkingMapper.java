package api.parking.control.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api.parking.control.controller.dto.ParkingDTO;
import api.parking.control.model.Parking;

@Component
public class ParkingMapper {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	//esse metodo é responsavel por inicializar os objeto e passar todos atributos para outro objeto
	public ParkingDTO parkingDTO(Parking parking) {
		return MODEL_MAPPER.map(parking, ParkingDTO.class);
	}
	
	public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
		return parkingList.stream().map(this::parkingDTO).collect(Collectors.toList());
	}

}
