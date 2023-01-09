package com.example.project4task2;

import org.bson.Document;
import org.bson.types.ObjectId;

//reference: https://jojozhuang.github.io/programming/building-website-with-jsp-and-mongodb/

/**
 * @author Wei Huang
 *  * AndrewID: weih2
 * Transfer the object structure between request/ response object and mongoDB document object.
 */
public class MongoConvert {
    /**
     * Convert response object to document object.
     */
    public static Document resToD(APIResponse p) {
        Document doc = new Document("RequestID", p.getRequestID()).append("Date", p.getDate())
                .append("Data", p.getData());
        if (p.getId() != null) {
            doc.append("_id", new ObjectId(p.getId()));
        }
        return doc;
    }
    /**
     * Convert document object to response object.
     */
    // convert MongoDB Document to Product
    // take special note of converting ObjectId to String
    public static APIResponse DToRes(Document doc) {
        APIResponse p = new APIResponse();
        p.setRequestID((String) doc.get("RequestID"));
        p.setDate((String) doc.get("Date"));
        p.setData((String) doc.get("Data"));
        p.setId(((ObjectId) doc.get("_id")).toString());
        return p;
    }
    /**
     * Convert request object to document object.
     */

    public static Document reqToD(APIRequest p) {
        Document doc = new Document("Method", p.getMethod()).append("Date", p.getDate())
                .append("Data", p.getData());
        if (p.getId() != null) {
            doc.append("_id", new ObjectId(p.getId()));
        }
        return doc;
    }
    /**
     * Convert document object to request object.
     */
    public static APIRequest DToReq(Document doc) {
        APIRequest p = new APIRequest();
        p.setMethod((String) doc.get("Method"));
        p.setDate((String) doc.get("Date"));
        p.setData((String) doc.get("Data"));
        p.setId(((ObjectId) doc.get("_id")).toString());
        return p;
    }
}
