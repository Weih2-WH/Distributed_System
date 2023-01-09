package com.example.project4task2;


import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wei Huang
 *  * AndrewID: weih2
 * The allowing method for the user used the mongoDB - request.
 */
//reference: https://jojozhuang.github.io/programming/building-website-with-jsp-and-mongodb/

public class RequestDao {
    // url for the cloud mongoDB
    String uri = "mongodb://chunchusweih2:cw20210830@ac-toar1vl-shard-00-00.tdatapo.mongodb.net:27017,ac-toar1vl-shard-00-01.tdatapo.mongodb.net:27017,ac-toar1vl-shard-00-02.tdatapo.mongodb.net:27017/Task1?w=majority&retryWrites=true&tls=true&authMechanism=SCRAM-SHA-1";
    //connect to the mongoDB
    MongoClient mongoClient = MongoClients.create(uri);
    MongoCollection<Document> coll;

    /**
     * go to the Database Task2 and collection APIRequest
     */
    public RequestDao() {

        this.coll = mongoClient.getDatabase("Task2").getCollection("APIRequest");
    }

    /**
     * Insert data to the mongoDB
     * @param p the data information
     * @return the request ID in mongoDB
     */

    public String create(APIRequest p) {
        Document doc = MongoConvert.reqToD(p);
        this.coll.insertOne(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        p.setId(id.toString());
        return p.getId();
    }

    /**
     * Get all the request data
     * @return list of all request data
     */
    public List<APIRequest> getList() {
        List<APIRequest> list = new ArrayList<APIRequest>();
        MongoCursor<Document> cursor = coll.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                APIRequest p = MongoConvert.DToReq(doc);
                list.add(p);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

}

