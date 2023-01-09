package com.example.project4task2;


import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Wei Huang
 *  * AndrewID: weih2
 * The allowing method for the user used the mongoDB - response.
 */
//reference: https://jojozhuang.github.io/programming/building-website-with-jsp-and-mongodb/

public class ResponseDao {
    // url for the cloud mongoDB
    String uri = "mongodb://chunchusweih2:cw20210830@ac-toar1vl-shard-00-00.tdatapo.mongodb.net:27017,ac-toar1vl-shard-00-01.tdatapo.mongodb.net:27017,ac-toar1vl-shard-00-02.tdatapo.mongodb.net:27017/Task1?w=majority&retryWrites=true&tls=true&authMechanism=SCRAM-SHA-1";
    //connect to the mongoDB
    MongoClient mongoClient = MongoClients.create(uri);
    MongoCollection<Document> coll;

    /**
     * go to the Database Task2 and collection APIResponse
     */
    public ResponseDao() {
        this.coll = mongoClient.getDatabase("Task2").getCollection("APIResponse");
    }

    /**
     * Insert data to the mongoDB
     * @param p the data information
     */
    public void create(APIResponse p) {
        Document doc = MongoConvert.resToD(p);
        this.coll.insertOne(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        p.setId(id.toString());
    }
    /**
     * Get all the response data
     * @return list of all response data
     */
    public List<APIResponse> getList() {
        List<APIResponse> list = new ArrayList<APIResponse>();
        MongoCursor<Document> cursor = coll.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                APIResponse p = MongoConvert.DToRes(doc);
                list.add(p);
            }
        } finally {
            cursor.close();
        }
        return list;
    }
}

