package com.zdw.oom;

public class TestStackOverflow2 {

    private static int stackLength = 0;

    public static void test(){
        long unuse1,unuse2,unuse3,unuse4,unuse5,unuse6,unuse7,unuse8,unuse9,unuse10,
             unuse11,unuse12,unuse13,unuse14,unuse15,unuse16,unuse17,unuse18,unuse19,unuse20,
             unuse21,unuse22,unuse23,unuse24,unuse25,unuse26,unuse27,unuse28,unuse29,unuse30,
             unuse31,unuse32,unuse33,unuse34,unuse35,unuse36,unuse37,unuse38,unuse39,unuse40,
             unuse41,unuse42,unuse43,unuse44,unuse45,unuse46,unuse47,unuse48,unuse49,unuse50,
             unuse51,unuse52,unuse53,unuse54,unuse55,unuse56,unuse57,unuse58,unuse59,unuse60,
             unuse61,unuse62,unuse63,unuse64,unuse65,unuse66,unuse67,unuse68,unuse69,unuse70,
             unuse71,unuse72,unuse73,unuse74,unuse75,unuse76,unuse77,unuse78,unuse79,unuse80,
             unuse81,unuse82,unuse83,unuse84,unuse85,unuse86,unuse87,unuse88,unuse89,unuse90,
             unuse91,unuse92,unuse93,unuse94,unuse95,unuse96,unuse97,unuse98,unuse99,unuse100;
        stackLength ++;
        test();
        unuse1=unuse2=unuse3=unuse4=unuse5=unuse6=unuse7=unuse8=unuse9=unuse10=
        unuse11=unuse12=unuse13=unuse14=unuse15=unuse16=unuse17=unuse18=unuse19=unuse20=
        unuse21=unuse22=unuse23=unuse24=unuse25=unuse26=unuse27=unuse28=unuse29=unuse30=
        unuse31=unuse32=unuse33=unuse34=unuse35=unuse36=unuse37=unuse38=unuse39=unuse40=
        unuse41=unuse42=unuse43=unuse44=unuse45=unuse46=unuse47=unuse48=unuse49=unuse50=
        unuse51=unuse52=unuse53=unuse54=unuse55=unuse56=unuse57=unuse58=unuse59=unuse60=
        unuse61=unuse62=unuse63=unuse64=unuse65=unuse66=unuse67=unuse68=unuse69=unuse70=
        unuse71=unuse72=unuse73=unuse74=unuse75=unuse76=unuse77=unuse78=unuse79=unuse80=
        unuse8=unuse82=unuse83=unuse84=unuse85=unuse86=unuse87=unuse88=unuse89=unuse90=
        unuse91=unuse92=unuse93=unuse94=unuse95=unuse96=unuse97=unuse98=unuse99=unuse100 = 0;
    }

    public static void main(String[] args) {
        try {
            test();
        }catch (Error e){
            System.out.println("stack length : "+stackLength);
            throw e;
        }
    }

}
