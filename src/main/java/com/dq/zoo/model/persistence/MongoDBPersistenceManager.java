package com.dq.zoo.model.persistence;

import com.dq.zoo.helpers.JsonUtil;
import com.dq.zoo.model.Entity;
import com.dq.zoo.model.PersistenceManager;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBPersistenceManager implements PersistenceManager {

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public MongoDBPersistenceManager() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("zoo-db");
    }

    @Override
    public Entity create(Entity entity) {
        Document doc = entityToDocument(entity);
        MongoCollection<Document> collection = database.getCollection(entity.getClass().getSimpleName());
        collection.insertOne(doc);
        return entity;
    }

    @Override
    public String update(String id, Object idVal, String field, Object newVal, Class<? extends Entity> type) {
        MongoCollection<Document> collection = database.getCollection(type.getSimpleName());
        return collection.updateOne(eq(id, idVal), new Document("$set", new Document(field, newVal))).toString();
    }

    @Override
    public String delete(String id, Object idVal, Class<? extends Entity> type) {
        MongoCollection<Document> collection = database.getCollection(type.getSimpleName());
        return collection.deleteOne(eq(id, idVal)).toString();
    }

    @Override
    public List<String> findAllAsJson(Class<? extends Entity> type) {
        MongoCollection<Document> collection = database.getCollection(type.getSimpleName());
        ArrayList<String> results = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                results.add(cursor.next().toJson());
            }
        }

        return results;
    }

    @Override
    public List<Entity> findAllAsEntity(Class<? extends Entity> type) {
        MongoCollection<Document> collection = database.getCollection(type.getSimpleName());
        ArrayList<Entity> results = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                String json = cursor.next().toJson();
                results.add(JsonUtil.fromJson(json, type));
            }
        }

        return results;
    }

    @Override
    public List<String> find(String id, Object idVal, Class<? extends Entity> type) {
        MongoCollection<Document> collection = database.getCollection(type.getSimpleName());

        ArrayList<String> results = new ArrayList<>();

        collection.find(eq(id, idVal)).forEach((Block<? super Document>) e -> {
            results.add(e.toJson());
        });

        return results;
    }

    @Override
    public void clearData(Class<? extends Entity> type) {
        database.drop();
    }

    private Document entityToDocument(Entity entity) {
        Document doc = new Document();

        for (Field field : entity.getClass().getDeclaredFields()) {
            boolean defaultAccessibility = field.isAccessible();
            field.setAccessible(true);

            try {
                doc.append(field.getName(), field.get(entity).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            field.setAccessible(defaultAccessibility);
        }

        return doc;
    }


}
