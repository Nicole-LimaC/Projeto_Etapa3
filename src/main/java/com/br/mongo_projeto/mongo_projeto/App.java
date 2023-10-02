package com.br.mongo_projeto.mongo_projeto;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Conectar ao banco de dados
            MongoDatabase database = mongoClient.getDatabase("filmesBancoDeDados");

            // Obter uma referência para a coleção
            MongoCollection<Document> collection = database.getCollection("filmes");

            // Ler o conteúdo do arquivo filmes.json
            String jsonFilePath = "D:\\Computação\\Programação\\Java\\mongo-projeto\\filmes.json";
            File jsonFile = new File(jsonFilePath);
            String jsonContent = new String(Files.readAllBytes(jsonFile.toPath()));

            // Converter o JSON em um documento BSON
            Document documento = Document.parse(jsonContent);

            // Inserir o documento na coleção
            collection.insertOne(documento);

            // Consultar e exibir todos os documentos na coleção
            FindIterable<Document> documentos = collection.find();

            for (Document doc : documentos) {
                System.out.println(doc.toJson());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
