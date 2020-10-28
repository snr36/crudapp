package com.crudapplication.crudapp.repository.dao;

import java.util.List;


public class Container<T> {
    private List<T> container;


    public Container(List<T> container) {
        this.container = container;
    }

    public Container() { }

    public List<T> getContainer() {
        return container;
    }

    public void setContainer(List<T> container) {
        this.container = container;
    }

	public int size() {
		return container.size();
	}

	
}
