package com.example.demo1;

import com.example.demo1.Product;
import com.example.demo1.dao.Impl.ProductDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonMapper {

    public static void parse() {
        try {
            File folder = new File("inputs");
            File[] listOfFiles = folder.listFiles();
            Arrays.sort(listOfFiles,(a,b)->Long.compare(b.lastModified(),a.lastModified()));
            List<File> lastFive= new ArrayList<>();
            for(int i =0;i<5 && i<listOfFiles.length;i++)
            {
                lastFive.add(listOfFiles[i]);
            }

            ProductDaoImpl dao = new ProductDaoImpl();
            for (File f : lastFive) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    List<Product> products = Arrays.asList(mapper.readValue(Paths.get(f.getAbsolutePath()).toFile(), Product[].class));

                    Files.move(Paths.get("inputs/" + f.getName()), Paths.get("done/" + f.getName()));

                    for (Product product : products)
                    {
                        dao.update(product);
                    }
                } catch (Exception e) {
                    Files.move(Paths.get("inputs/" + f.getName()), Paths.get("error/" + f.getName()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



