package indi.mzt.toppahs.plugin.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Switch {
    String value();
}
