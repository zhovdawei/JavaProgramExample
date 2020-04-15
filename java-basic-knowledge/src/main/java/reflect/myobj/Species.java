package reflect.myobj;

public class Species {

    private String type;
    public int flag;

    public Species() {
    }

    public Species(String type, int flag) {
        this.type = type;
        this.flag = flag;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public int getFlag() {
        return flag;
    }
}
