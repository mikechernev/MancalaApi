package com.mikechernev.mancala.api.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by mike on 23/04/16.
 */

@Entity
public class Player {
    @Id
    private ObjectId _id;
    private String id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        if (this.id == null) {
            this.id = this._id.toString();
        }

        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Player && this._id.equals(((Player) obj)._id);
    }

}
