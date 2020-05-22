package com.dq.zoo.model.persistence;

import com.dq.zoo.helpers.JsonUtil;
import com.dq.zoo.model.Entity;
import com.dq.zoo.model.PersistenceManager;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.exc.ReqlOpFailedError;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RethinkDBPersistenceManager implements PersistenceManager {

    public static final RethinkDB r = RethinkDB.r;
    private static Connection connection;

    public RethinkDBPersistenceManager(List<Class<? extends Entity>> supportedEntities) {
        connection = r.connection().hostname("localhost").port(28015).connect();

        try {
            r.dbCreate("rethinkZooDB").run(connection);
        } catch (ReqlOpFailedError e) {
        }

        r.db("rethinkZooDB");

        for (Class<?> o : supportedEntities) {
            try {
                r.tableCreate(o.getSimpleName()).run(connection);
            } catch (ReqlOpFailedError e) {
            }
        }
    }

    @Override
    public Entity create(Entity entity) {
        MapObject map = entityToMapObject(entity);
        r.table(entity.getClass().getSimpleName()).insert(map).run(connection);
        return entity;
    }

    @Override
    public String update(String id, Object idVal, String field, Object newVal, Class<? extends Entity> type) {
        HashMap result = r.table(type.getSimpleName())
                .filter(row -> row.g(id).eq(idVal))
                .update(r.hashMap(field, newVal)).run(connection);

        return result.toString();
    }

    @Override
    public String delete(String id, Object idVal, Class<? extends Entity> type) {
        HashMap result = r.table(type.getSimpleName())
                .filter(row -> row.g(id).eq(idVal))
                .delete().run(connection);

        return result.toString();
    }

    @Override
    public List<String> findAllAsJson(Class<? extends Entity> type) {
        Cursor cursor = r.table(type.getSimpleName()).run(connection);
        List<String> results = new ArrayList<>();
        for (Object obj : cursor) {
            results.add(obj.toString());
        }
        return results;
    }

    @Override
    public List<Entity> findAllAsEntity(Class<? extends Entity> type) {
        Cursor cursor = r.table(type.getSimpleName()).run(connection);
        List<Entity> results = new ArrayList<>();
        for (Object obj : cursor) {
            String json = obj.toString();
            results.add(JsonUtil.fromJson(json, type));
        }
        return results;
    }

    @Override
    public List<String> find(String id, Object idVal, Class<? extends Entity> type) {
        Cursor cursor = r.table(type.getSimpleName())
                .filter(row -> row.g(id).eq(idVal))
                .run(connection);

        List<String> results = new ArrayList<>();
        for (Object obj : cursor) {
            results.add(obj.toString());
        }
        return results;
    }

    @Override
    public void clearData(Class<? extends Entity> type) {
        r.tableDrop(type.getSimpleName()).run(connection);
    }

    private MapObject entityToMapObject(Entity entity) {
        MapObject mapObject = r.hashMap();

        for (Field field : entity.getClass().getDeclaredFields()) {
            boolean defaultAccessibility = field.isAccessible();
            field.setAccessible(true);

            try {
                mapObject.with(field.getName(), field.get(entity).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            field.setAccessible(defaultAccessibility);
        }

        return mapObject;
    }
}
