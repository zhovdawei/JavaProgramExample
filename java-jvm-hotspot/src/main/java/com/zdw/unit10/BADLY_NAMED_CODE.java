package com.zdw.unit10;

public class BADLY_NAMED_CODE {

    enum  colors{
        red,blue,green;
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE(){
        return;
    }

    public void NOTcamelCASEmethodNAME(){
        return;
    }

}


// javac -encoding utf-8 com\\zdw\\unit10\\NameCheck.java
// javac -encoding utf-8 com\\zdw\\unit10\\NameCheckProcessor.java
// avac -processor com.zdw.unit10.NameCheckProcessor com/zdw/unit10/BADLY_NAMED_CODE.java

