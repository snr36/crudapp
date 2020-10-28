package com.crudapplication.crudapp.controller.Requests;

import java.util.List;



public class ContainerRequest<T> {
    
    private List<T> containerrequest;

    public ContainerRequest(List<T> containerrequest) {
        this.containerrequest = containerrequest;
    }

    public ContainerRequest() { }
    

    public List<T> getContainerrequest() {
        return containerrequest;
    }

    public void setContainerrequest(List<T> containerrequest) {
        this.containerrequest = containerrequest;
    }


}
