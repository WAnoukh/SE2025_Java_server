package Javabackend.GovernmentVolunteer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private MongoClient client = null;
    private MongoDatabase db = null;
    private static DatabaseManager instance = null;
    private DatabaseManager() {
        connectionToDatabase();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void connectionToDatabase() {
        client = MongoClients.create("mongodb+srv://hacs:BAyjXN6QIXLZJN96@hacs.eeo77.mongodb.net");
        db = client.getDatabase("test");
    }
    public boolean checkExistCollection(String name) {
        return db.listCollectionNames().into(new ArrayList<>()).contains(name);
    }

    public void createCollection(String name) {
        if (!checkExistCollection(name)){
            db.createCollection(name);
        }
    }

    //TODO: implement insertOne and insertMultiple methods
   /*public void insertOne(MongoObject object) {
        MongoCollection<Document> collection = db.getCollection(object.getCollectionName());
        collection.insertOne(object.toJson());
    }

    public void insertMultiple(List<MongoObject> objects) {
        if (!objects.isEmpty()) {
            List<Document> documents = objects.stream().map(MongoObject::toJson).toList();
            MongoCollection<Document> collection = db.getCollection(objects.get(0).getCollectionName());
            collection.insertMany(documents);
        }
    }*/

    public MongoCollection<Document> getCollection(String collectionName) {
        return db.getCollection(collectionName);
    }
}
