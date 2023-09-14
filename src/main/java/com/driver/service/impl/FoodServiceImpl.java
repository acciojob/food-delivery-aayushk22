package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodRepository foodRepository;
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodId(food.getFoodId());
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodPrice(food.getFoodPrice());
        foodEntity.setFoodCategory(food.getFoodCategory());

        FoodEntity savedFoodEntity = foodRepository.save(foodEntity);

        foodEntity.setId(savedFoodEntity.getId());

        FoodDto response = new FoodDto();
        response.setFoodId(savedFoodEntity.getFoodId());
        response.setFoodName(savedFoodEntity.getFoodName());
        response.setId(savedFoodEntity.getId());
        response.setFoodCategory(savedFoodEntity.getFoodCategory());
        response.setFoodPrice(savedFoodEntity.getFoodPrice());

        return response;
    }

    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

        if (foodEntity == null) {
            throw new Exception("food not found");
        }

        FoodDto foodDto = new FoodDto();
        foodDto.setFoodPrice(foodEntity.getFoodPrice());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setId(foodEntity.getId());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        foodDto.setFoodId(foodEntity.getFoodId());

        return foodDto;
    }

    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        if (foodEntity == null) {
            throw new Exception("food not found");
        }

        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());

        FoodEntity updated = foodRepository.save(foodEntity);

        FoodDto response = new FoodDto();
        response.setFoodId(updated.getFoodId());
        response.setId(updated.getId());
        response.setFoodCategory(updated.getFoodCategory());
        response.setFoodName(updated.getFoodName());
        response.setFoodPrice(updated.getFoodPrice());

        return response;
    }

    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        if (foodEntity == null) throw new Exception("invalid");
        foodRepository.delete(foodEntity);
    }

    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntityList = (List<FoodEntity>) foodRepository.findAll();

        List<FoodDto> response = new ArrayList<>();

        for (FoodEntity food: foodEntityList) {
            FoodDto f = new FoodDto();
            f.setFoodPrice(food.getFoodPrice());
            f.setFoodCategory(food.getFoodCategory());
            f.setFoodName(food.getFoodName());
            f.setFoodId(food.getFoodId());
            f.setId(food.getId());

            response.add(f);
        }

        return response;

    }
}