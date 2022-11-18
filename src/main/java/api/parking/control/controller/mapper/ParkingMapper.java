package api.parking.control.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import api.parking.control.controller.dto.ParkingCreateDTO;
import api.parking.control.controller.dto.ParkingDTO;
import api.parking.control.model.Parking;

@Component
public class ParkingMapper {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	//esse metodo é responsavel por inicializar os objeto e passar todos atributos para outro objeto
	public ParkingDTO toParkingDTO(Parking parking) {
		return MODEL_MAPPER.map(parking, ParkingDTO.class);
	}
	
	//convert lista em DTOList
	public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
		return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
	}

	//converte dto em um objeto
	public Parking toParking(ParkingDTO dto) {
		return MODEL_MAPPER.map(dto, Parking.class);
	}

	public Parking toParkingCrete(ParkingCreateDTO dto) {
		return MODEL_MAPPER.map(dto, Parking.class);
	}
}
