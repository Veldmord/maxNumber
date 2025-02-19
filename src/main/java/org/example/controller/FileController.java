package org.example.controller;

import org.example.model.FileRequest;
import org.example.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Tag(name = "File Controller", description = "API для обработки XLSX файлов")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/find-nth-max")
    @Operation(summary = "Найти N-е максимальное число в файле",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно"),
                    @ApiResponse(responseCode = "400", description = "Ошибка ввода"),
                    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
            })
    public ResponseEntity<?> findNthMax(@RequestBody FileRequest request) {
        try {
            if (request.getFilePath() == null || request.getFilePath().isEmpty()) {
                return ResponseEntity.badRequest().body("Путь к файлу не может быть пустым.");
            }
            if (request.getN() <= 0) {
                return ResponseEntity.badRequest().body("Значение N должно быть положительным числом.");
            }

            Integer result = fileService.findNthMaxNumber(request.getFilePath(), request.getN());
            if (result == null) {
                return ResponseEntity.badRequest().body("N больше, чем количество чисел в файле.");
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }
}
