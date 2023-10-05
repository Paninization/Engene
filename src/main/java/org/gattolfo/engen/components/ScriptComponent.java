package org.gattolfo.engen.components;

import com.badlogic.ashley.core.Component;

public class ScriptComponent implements Component {

    public Script script;

    public ScriptComponent(Script script){
        this.script = script;
    }



}
