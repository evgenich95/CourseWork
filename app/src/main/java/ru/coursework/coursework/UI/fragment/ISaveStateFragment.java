package ru.coursework.coursework.UI.fragment;

import ru.coursework.coursework.entity.Memento.IMemento;

/**
 * Created by Anton on 25.02.2016.
 */
public interface ISaveStateFragment {

    IMemento createMemento();

    void setMemento(IMemento state);

}
