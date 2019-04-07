package com.lib.windows;

import javafx.stage.Stage;

public interface IWindow<T> {
    Stage getWindow();
    T controller();
}
