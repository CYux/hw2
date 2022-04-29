package com.doublefakefrog.hw2.Models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TopicModel implements Externalizable {
    private int topic_id;
    private String topic_name;

    public TopicModel() {
    }

    public TopicModel(int topic_id, String topic_name) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
    }

    public int getTopicId() {
        return topic_id;
    }

    public String getTopicName() {
        return topic_name;
    }

    public void setTopicId(int topic_id) {
        this.topic_id = topic_id;
    }

    public void setTopicName(String topic_name) {
        this.topic_name = topic_name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
