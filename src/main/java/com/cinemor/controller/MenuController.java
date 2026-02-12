package com.cinemor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuController {

    @GetMapping("/items")
    public ResponseEntity<List<Map<String, Object>>> getMenuItems() {
        try {
            String menuPath = "cinemor-react/public/images/menu";
            Path[] possiblePaths = {
                Paths.get(menuPath),
                Paths.get("../cinemor-react/public/images/menu"),
                Paths.get("../../cinemor-react/public/images/menu"),
                Paths.get("public/images/menu"),
                Paths.get("src/main/resources/static/images/menu")
            };
            
            Path menuDir = null;
            for (Path path : possiblePaths) {
                if (Files.exists(path) && Files.isDirectory(path)) {
                    menuDir = path;
                    break;
                }
            }
            
            if (menuDir == null) {
                return ResponseEntity.ok(getDefaultMenuItems());
            }
            
            List<Map<String, Object>> menuItems = new ArrayList<>();
            File[] files = menuDir.toFile().listFiles((dir, name) -> 
                name.toLowerCase().endsWith(".png") || 
                name.toLowerCase().endsWith(".jpg") || 
                name.toLowerCase().endsWith(".jpeg")
            );
            
            if (files != null) {
                for (File file : files) {
                    Map<String, Object> item = new HashMap<>();
                    String fileName = file.getName();
                    String nameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));
                    String displayName = formatMenuName(nameWithoutExt);
                    item.put("id", nameWithoutExt.toLowerCase().replaceAll("[^a-z0-9]", "-"));
                    item.put("name", displayName);
                    item.put("image", "/images/menu/" + fileName);
                    item.put("price", getDefaultPrice(displayName));
                    item.put("description", getDefaultDescription(displayName));
                    
                    menuItems.add(item);
                }
            }
            
            return ResponseEntity.ok(menuItems.isEmpty() ? getDefaultMenuItems() : menuItems);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(getDefaultMenuItems());
        }
    }
    
    private String formatMenuName(String fileName) {
        String[] parts = fileName.split("[_-]");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    result.append(part.substring(1));
                }
                result.append(" ");
            }
        }
        return result.toString().trim();
    }
    
    private Double getDefaultPrice(String name) {
        String lowerName = name.toLowerCase();
        if (lowerName.contains("extra") && lowerName.contains("groß")) return 18.50;
        if (lowerName.contains("extra")) return 15.90;
        if (lowerName.contains("groß")) return 14.50;
        if (lowerName.contains("kinder")) return 9.90;
        if (lowerName.contains("rio") || lowerName.contains("santo")) return 16.90;
        return 12.90;
    }
    
    private String getDefaultDescription(String name) {
        String lowerName = name.toLowerCase();
        if (lowerName.contains("kinder")) return "Kinderfreundliches Menü";
        if (lowerName.contains("rio") || lowerName.contains("santo")) return "Rio Santo Spezial Menü";
        if (lowerName.contains("extra") && lowerName.contains("groß")) return "Extra großes Menü mit Getränk";
        if (lowerName.contains("extra")) return "Extra Menü mit Getränk";
        if (lowerName.contains("groß")) return "Großes Menü mit Getränk";
        return "Menü mit Getränk";
    }
    
    private List<Map<String, Object>> getDefaultMenuItems() {
        List<Map<String, Object>> defaultItems = new ArrayList<>();
        
        String[] defaultMenus = {
            "extra_großes_menu.png", "extra_menu.png", "goßes_menu.png", 
            "kinder_menu.png", "rio_santo_menu.png"
        };
        
        double[] prices = {18.50, 15.90, 14.50, 9.90, 16.90};
        String[] names = {
            "Extra Großes Menu", "Extra Menu", "Großes Menu", 
            "Kinder Menu", "Rio Santo Menu"
        };
        String[] descriptions = {
            "Extra großes Menü mit Getränk", "Extra Menü mit Getränk", 
            "Großes Menü mit Getränk", "Kinderfreundliches Menü", 
            "Rio Santo Spezial Menü"
        };
        
        for (int i = 0; i < defaultMenus.length; i++) {
            Map<String, Object> item = new HashMap<>();
            String fileName = defaultMenus[i];
            String nameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));
            
            item.put("id", nameWithoutExt.toLowerCase().replaceAll("[^a-z0-9]", "-"));
            item.put("name", names[i]);
            item.put("image", "/images/menu/" + fileName);
            item.put("price", prices[i]);
            item.put("description", descriptions[i]);
            
            defaultItems.add(item);
        }
        
        return defaultItems;
    }
}
