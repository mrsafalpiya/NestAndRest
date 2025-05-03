package com.nestandrest.util;

import java.util.ArrayList;
import java.util.List;
import com.nestandrest.model.Product;

public class ProductData {
    public static List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();

        // Sofa
        list.add(new Product("Two Sitter Sofa", "TwoSitterSofa.png", "sofa", 9000));
        list.add(new Product("Apple Sofa Set 1", "AppleSofaSet1.jpeg", "sofa", 190000));
        list.add(new Product("Apple Sofa Set 2", "AppleSofaSet2.jpeg", "sofa", 9000));
        list.add(new Product("Apple Sofa Set 3", "AppleSofaSet3.jpeg", "sofa", 9000));
        list.add(new Product("Bamboo and Rattan Sofa Set", "BambooandRattanSofaSet.jpeg", "sofa", 9000));
        list.add(new Product("Bamboo Sofa Set", "BambooSofaSet.jpeg", "sofa", 9000));
        list.add(new Product("Rattan and Cane Sofa Set", "RattanandCaneSofaSet.jpeg", "sofa", 9000));
        list.add(new Product("Rattan Sofa Set", "RattanSofaSet.jpeg", "sofa", 9000));
        list.add(new Product("Rattan Sofa Set 2", "RattanSofaSet2.jpeg", "sofa", 9000));

        // Chair
        list.add(new Product("One Sitter Chair", "OneSitterChair.png", "chair", 4500));
        list.add(new Product("Round Chair", "RoundChair.png", "chair", 8000));

        // ✅ Stool
        list.add(new Product("Cane Rattan Stool", "CaneRattanStool.png", "stool", 2000));
        list.add(new Product("Cane Stool", "CaneStool.png", "stool", 2800));
        list.add(new Product("Rattan Stool", "RattanStool.png", "stool", 3500));

        // ✅ Rack
        list.add(new Product("Mandir Rack", "MandirRack.png", "rack", 3000));

        // ✅ Hanger
        list.add(new Product("Wall Hanger", "WallHanger.png", "hanger", 3500));
        list.add(new Product("Bangles Stand", "BanglesStand.png", "hanger", 4500));

        // ✅ Table
        list.add(new Product("Tea Table Set", "TeaTableSet.jpeg", "table", 6000));

        return list;
    }
}