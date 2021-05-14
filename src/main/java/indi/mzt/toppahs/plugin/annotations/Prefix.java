package indi.mzt.toppahs.plugin.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Prefix {
    String value();
}
