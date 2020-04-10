package mutithread;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Point{

    private final int x;
    private final int y;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "Point[x="+x+" , y="+y+"]";
    }

}

public class EntrustDemo {

    private final ConcurrentHashMap<String,Point> map;

    private final Map<String,Point> mapYes;

    public EntrustDemo(Map<String,Point> map){
        this.map = new ConcurrentHashMap<>(map);
        this.mapYes = Collections.unmodifiableMap(map);
    }

    public Map getOriginalMap(){
        return this.mapYes;
    }

    public Map<String,Point> getLocations(){
        return Collections.unmodifiableMap(new HashMap<>(map));
    }

    public void setPoint(String key,int x,int y){

        Point point = (Point)map.replace(key,new Point(x,y));

        if(point== null){
            throw new IllegalArgumentException("No key : "+key);
        }
    }

    public static void main(String[] args) {
        HashMap<String,Point> mapObj = new HashMap<>();
        mapObj.put("大众",new Point(23,14));
        mapObj.put("五菱宏光",new Point(43,45));
        mapObj.put("特斯拉",new Point(13,233));
        mapObj.put("奔驰",new Point(234,12));

        EntrustDemo demo = new EntrustDemo(mapObj);
        Map<String,Point> readMap = demo.getLocations();
        for (String s : readMap.keySet()) {
            System.out.println(s+" : " +readMap.get(s));
        }

        System.out.println("============================================");

        demo.setPoint("大众",111111,333333);

        for (String s : demo.map.keySet()) {
            System.out.println(s+" : " +demo.map.get(s));
        }

        System.out.println("============================================");

        readMap.replace("大众",new Point(12345,23456));
        ///


    }

}
