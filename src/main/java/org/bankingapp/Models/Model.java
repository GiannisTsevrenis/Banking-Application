package org.bankingapp.Models;
import org.bankingapp.Views.ViewFactory;

public class Model {
    private final ViewFactory viewFactory;

    private Model( ) {
        this.viewFactory = new ViewFactory();
    }

    private static final class ModelHolder {
        private static final Model model = new Model();
    }

    public static synchronized Model getInstance(){
        return ModelHolder.model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
