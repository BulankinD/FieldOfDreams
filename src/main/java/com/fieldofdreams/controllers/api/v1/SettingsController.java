package com.fieldofdreams.controllers.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fieldofdreams.entities.Difficulty;
import com.fieldofdreams.services.SettingsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@RestController
@Slf4j
@RequestMapping("/api/v1/settings")
public class SettingsController
{
    @Autowired
    SettingsService settingsService;

    @GetMapping("/difficulties")
    List<Difficulty> getDifficulties() {
        return settingsService.getAllDifficulties();
    }
}
