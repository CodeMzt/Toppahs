package indi.mzt.toppahs.plugin.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Switch {
    String value();
}
