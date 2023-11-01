package CAMSv2;

import java.util.ArrayList;

public class Suggestion {
    private ArrayList<Advice> AdviceList;
    
    //methods
    public Suggestion(Advice advice){
        this.AdviceList.add(advice);
    }

    public ArrayList<Advice> getAdviceList(){
        return AdviceList;
    }
}
