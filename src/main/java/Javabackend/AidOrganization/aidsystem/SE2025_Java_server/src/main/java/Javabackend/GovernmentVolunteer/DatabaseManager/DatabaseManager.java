package Javabackend.GovernmentVolunteer.DatabaseManager;


import Javabackend.JavaBackendException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

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

    public Document getDocumentById(String id, String collectionName) throws JavaBackendException {
        if (!checkExistCollection(collectionName)) {
            throw new JavaBackendException("Collection does not exist");
        }
        MongoCollection<Document> collection = db.getCollection(collectionName);
        for (Document doc : collection.find()) {
            if (doc.get("_id").toString().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    //TODO: implement insertOne and insertMultiple methods
   public void insertOne(Document object, String collectionName) throws JavaBackendException {

        if (!checkExistCollection(collectionName)) {
            createCollection(collectionName);
        }
        MongoCollection<Document> collection = db.getCollection(collectionName);
        //check if the object already exists
       try {
           if(getDocumentById(object.getString("_id"), collectionName) != null){
               throw new JavaBackendException("Document already exists");
           }
       } catch (JavaBackendException e) {
           throw new RuntimeException(e);
       }
       collection.insertOne(object);
    }

    public void updateOne(Document object, String collectionName) throws JavaBackendException {
        if (!checkExistCollection(collectionName)) {
            throw new JavaBackendException("Collection does not exist");
        }
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document doc = getDocumentById(object.getString("_id"), collectionName);
        if (doc != null) {
            collection.replaceOne(doc, object);
        } else {
            throw new JavaBackendException("Document not found");
        }
    }

    /*public void insertMultiple(List<MongoObject> objects) {
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
