package com.example.menu.dto;

import java.time.LocalDateTime;

public class MenuDto {
    public static class Simple {
        public String name;
        public int price;
    }

    public static class Detail {
        public int id;
        public int shopId;
        public String name;
        public int price;
        public LocalDateTime createdAt;
        public LocalDateTime updatedAt;
    }

    public static class Upsert {
        public String name;
        public int price;
    }
}
