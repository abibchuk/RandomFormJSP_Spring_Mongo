package com.andersen.web;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;


@Controller
public class MainController {

    MongoClient mongo = new MongoClient();
    MongoDatabase db = mongo.getDatabase("mydb");
    MongoCollection coll = db.getCollection("param");

    public MainController() throws UnknownHostException {
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm() {
        return "add_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveParam(@RequestParam Map<String, String> params) {
        Document document = new Document();
        for (String key: params.keySet()) {
            new Thread(){
                public void run(){
                    System.out.println("Thread: " + getName() + " running");
                    document.put(key, params.get(key));
                    try{
                        coll.insertOne(document);
                    }
                    catch (MongoWriteException ex){
                        coll.replaceOne(eq("_id",document.getObjectId("_id")),document);
                    }
                }
            }.start();

        }
        return "add_form";
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView showParam (){
        ModelAndView model = new ModelAndView("all");

        FindIterable it = coll.find().projection(excludeId());
        ArrayList docs = new ArrayList();
        it.into(docs);
        model.addObject("list", docs);

        return model;
    }

}