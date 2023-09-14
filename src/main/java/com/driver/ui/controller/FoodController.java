package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodService foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		try {
			FoodDto foodDto = foodService.getFoodById(id);
			FoodDetailsResponse response = new FoodDetailsResponse();
			response.setFoodId(foodDto.getFoodId());
			response.setFoodPrice(foodDto.getFoodPrice());
			response.setFoodName(foodDto.getFoodName());
			response.setFoodCategory(foodDto.getFoodCategory());
			return response;
		}
		catch (Exception e) {
			return null;
		}

	}

//	@GetMapping(path="/{id}")
//	public ResponseEntity getFood(@PathVariable String id) throws Exception{
//		try {
//			FoodDto foodDto = foodService.getFoodById(id);
//			FoodDetailsResponse response = new FoodDetailsResponse();
//			response.setFoodId(foodDto.getFoodId());
//			response.setFoodPrice(foodDto.getFoodPrice());
//			response.setFoodName(foodDto.getFoodName());
//			response.setFoodCategory(foodDto.getFoodCategory());
//			return new ResponseEntity<>(response, HttpStatus.FOUND);
//		}
//		catch (Exception e) {
//			return new ResponseEntity<>("Food Not Found",HttpStatus.NOT_FOUND);
//		}
//
//	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		try {
			//converting fooddetailsrequest to food dto

			FoodDto foodDto = new FoodDto();
			foodDto.setFoodCategory(foodDetails.getFoodCategory());
			foodDto.setFoodName(foodDetails.getFoodName());
			foodDto.setFoodPrice(foodDetails.getFoodPrice());
			foodDto.setFoodId(String.valueOf(UUID.randomUUID()));

			FoodDto savedFoodEntity = foodService.createFood(foodDto);

			FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
			foodDetailsResponse.setFoodCategory(savedFoodEntity.getFoodCategory());
			foodDetailsResponse.setFoodName(savedFoodEntity.getFoodName());
			foodDetailsResponse.setFoodPrice(savedFoodEntity.getFoodPrice());
			foodDetailsResponse.setFoodId(savedFoodEntity.getFoodId());
			return foodDetailsResponse;
		}

		catch (Exception e) {
			return null;
		}


	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = new FoodDto();
		foodDto.setFoodCategory(foodDetails.getFoodCategory());
		foodDto.setFoodName(foodDetails.getFoodName());
		foodDto.setFoodPrice(foodDetails.getFoodPrice());
		try {
			FoodDto foodDto1 = foodService.updateFoodDetails(id,foodDto);
			FoodDetailsResponse response = new FoodDetailsResponse();
			response.setFoodId(foodDto1.getFoodId());
			response.setFoodPrice(foodDto1.getFoodPrice());
			response.setFoodName(foodDto1.getFoodName());
			response.setFoodCategory(foodDto1.getFoodCategory());
			return response;
		}
		catch (Exception e) {
			return null;
		}
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel operationStatusModel = new OperationStatusModel();

		try {
			foodService.deleteFoodItem(id);
			operationStatusModel.setOperationName(String.valueOf(RequestOperationName.DELETE));
			operationStatusModel.setOperationResult(String.valueOf(RequestOperationStatus.SUCCESS));
		}
		catch (NullPointerException nullPointerException) {
			operationStatusModel.setOperationName(String.valueOf(RequestOperationName.DELETE));
			operationStatusModel.setOperationResult(String.valueOf(RequestOperationStatus.ERROR));
		}
		catch (Exception e) {
			operationStatusModel.setOperationName(String.valueOf(RequestOperationName.DELETE));
			operationStatusModel.setOperationResult(String.valueOf(RequestOperationStatus.ERROR));
		}
		return operationStatusModel;
	}
	
	@GetMapping("/get-all-foods")
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> foodDtoList = foodService.getFoods();
		List<FoodDetailsResponse> foodDetailsResponses = new ArrayList<>();

		for (FoodDto foodDto: foodDtoList) {
			FoodDetailsResponse f = new FoodDetailsResponse();
			f.setFoodId(foodDto.getFoodId());
			f.setFoodCategory(foodDto.getFoodCategory());
			f.setFoodName(foodDto.getFoodName());
			f.setFoodPrice(foodDto.getFoodPrice());

			foodDetailsResponses.add(f);
		}

		return foodDetailsResponses;
	}
}
