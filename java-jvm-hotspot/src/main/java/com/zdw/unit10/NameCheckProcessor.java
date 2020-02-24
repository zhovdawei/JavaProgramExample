package com.zdw.unit10;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {

    private NameCheck nameCheck;

    @Override
    public void init(ProcessingEnvironment processingEnv){
        super.init(processingEnv);
        nameCheck = new NameCheck(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if(!roundEnv.processingOver()){
            for (Element element : roundEnv.getRootElements()) {
                nameCheck.checkNames(element);
            }
        }
        return false;
    }
}
