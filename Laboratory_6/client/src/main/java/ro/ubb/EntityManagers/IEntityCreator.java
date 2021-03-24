package ro.ubb.EntityManagers;

import ro.ubb.Model.BaseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class IEntityCreator<ID, K extends BaseEntity<ID>>{
    public BufferedReader bufferRead;

    public IEntityCreator() {
        this.bufferRead = new BufferedReader(new InputStreamReader(System.in));
    }

    public abstract K createEntity();
    public abstract ID createID();
}
