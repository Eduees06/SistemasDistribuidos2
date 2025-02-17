package com.ms.user.controllers;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import com.ms.user.services.NameService; // Importando o NameService
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final UserService userService;
    final NameService nameService; // Declaração do NameService

    public UserController(UserService userService, NameService nameService) {
        this.userService = userService;
        this.nameService = nameService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);

        // Chama o NameService para preencher país e significado
        nameService.setCountryAndMeaning(userModel);

        // Salva o usuário com as informações adicionais
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
}