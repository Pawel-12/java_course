package com.solvd.laba.block1.task2.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.solvd.laba.block1.task2.Main.LOGGER;
import static java.lang.Character.isAlphabetic;
import static org.apache.commons.io.FileUtils.*;

public class FileStats {
    public static void countUniqueWords(String filePath) {
        File file = getFile(filePath);

        try {
            Map<String, Integer> words = new HashMap<>();

            String data = readFileToString(file, StandardCharsets.UTF_8);
            String[] splitedData = StringUtils.splitByCharacterTypeCamelCase(data);

            for (String s : splitedData) {
                if (isAlphabetic(s.charAt(0)))
                    if (words.containsKey(s))
                        words.replace(s, words.get(s) + 1);
                    else
                        words.put(s, 1);
            }


            writeStringToFile(new File("data/stats.txt"), words.toString(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("countUniqueWords() error ", e);
        }
    }
}