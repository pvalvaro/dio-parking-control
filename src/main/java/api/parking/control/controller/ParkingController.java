package api.parking.control.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.parking.control.controller.dto.ParkingCreateDTO;
import api.parking.control.controller.dto.ParkingDTO;
import api.parking.control.controller.mapper.ParkingMapper;
import api.parking.control.model.Parking;
import api.parking.control.services.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping
	@ApiOperation("Find all parkings") 
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	@ApiOperation("Create") 
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
		var parkingCreate = parkingMapper.toParkingCrete(dto);
		var parking = parkingService.create(parkingCreate);
		var result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable String id){
		parkingService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
		var parkingCreate = parkingMapper.toParkingCrete(dto);
		var parking = parkingService.update(id, parkingCreate);
		var result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
	}
	
	/*
	 * @PutMapping("/{id}") public ResponseEntity<ParkingDTO> exit(@PathVariable
	 * String id){ var parking = parkingService.exit(id); return
	 * ResponseEntity.ok(parkingMapper.toParkingDTO(parking)); }
	 */
}
