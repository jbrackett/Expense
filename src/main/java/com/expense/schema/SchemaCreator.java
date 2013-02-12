package com.expense.schema;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import javax.persistence.Entity;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.EnversSchemaGenerator;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.expense.config.AppConfig;

public class SchemaCreator {

  public static void run() throws IOException {
    final Configuration config = new Configuration();
    config.setProperty("hibernate.dialect", AppConfig.HIBERNATE_DIALECT);

    Path dir = FileSystems.getDefault().getPath("src", "main", "java", "com",
        "expense", "domain");
    Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
          throws IOException {
        Class<?> clazz = null;
        try {
          String filePath = file.getParent().toString()
              .replace("src/main/java/", "").replace("/", ".");
          String fileName = file.getName(file.getNameCount() - 1).toString()
              .replace(".java", "");
          clazz = Class.forName(filePath + "." + fileName);
        }
        catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
        if (clazz != null) {
          Entity entity = clazz.getAnnotation(Entity.class);
          if (entity != null) {
            config.addAnnotatedClass(clazz);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    });
    new EnversSchemaGenerator(config);
    SchemaExport schemaExport = new SchemaExport(config);
    schemaExport.setDelimiter(";");
    schemaExport.setFormat(true);
    Path schemaFile = FileSystems.getDefault().getPath("src", "main",
        "resources", "import.sql");
    schemaExport.setOutputFile(schemaFile.toString());
    schemaExport.create(false, false);
  }

  public static void main(String[] args) {
    try {
      SchemaCreator.run();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
