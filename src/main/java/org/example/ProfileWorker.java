package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileWorker {

    private final Notebook notebook;

    public ProfileWorker(Notebook notebook) {
        this.notebook = notebook;
    }

    public void doWork() {
        notebook.init();
    }


}
