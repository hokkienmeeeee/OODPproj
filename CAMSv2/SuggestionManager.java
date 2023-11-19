package CAMSv2;

import java.util.Scanner;

/**
 * Singleton class responsible for managing Suggestion utility methods
 */
public class SuggestionManager {
    private static SuggestionManager instance;
    //attribute
    private SuggestionManager() {
    }

    // can remove filePath argument and instead intialize into the class itself
    public static SuggestionManager getInstance() {
        if (instance == null) {
            instance = new SuggestionManager();
        }
        return instance;
    }
    //methods
    public void createSuggestion(String campName, String suggestion,String name){
        //student will have their own method called makeSuggestion which calls this method,
        //append student's suggestion to their own list of suggestion
        //find the correct camp
        //append this suggestion to that particular camp
        Advice newAdvice = new Advice(suggestion);
        Suggestion newSuggestion = new Suggestion(newAdvice,name);
        Camp camp = CampManager.getCamp(campName);
        camp.addSuggestion(newSuggestion);
    }

    //staff fucntion
    public void viewSuggestionForStaff(String campName, String staffName){
        //printing and logic will occur in this methoed
        // staff->viewEn->thisviewEnq->getcamp->
        //for loop to iterate arraylist of suggestion
        for(int i=0;i<CampManager.getCampList().size();i++){

            if(CampManager.getStaffinCharge(campName,staffName)){

                Camp camp = CampManager.getCamp(campName);
                System.out.println(campName + " suggestion");

                for(int j=0;j<camp.getSuggestions().size();j++){

                    for(int k=0;k<camp.getSuggestions().get(j).getAdviceList().size();k++){

                    System.out.println("Suggestion " + j+1 + " Advice " + k+1 + "- " + camp.getSuggestions().get(j).getAdviceList().get(k));

                    }//inner for
                }//mid for
            }//if

           

        }//outer for
    }//viewSuggestion

    //approve suggestion
    //staff.approvesuggestion-> suggManager.approveAdvice->
    public Suggestion approveAdvice(String campName, String staffName){
        Scanner sc = new Scanner(System.in);

        Camp curCamp = CampManager.getCamp(campName);

        int suggIndex;
        int adviceIndex;

        this.viewSuggestionForStaff(campName, staffName);

        System.out.println("Which suggestion would you like to attend to?");
        //value taken is +1 of actual index
        suggIndex = sc.nextInt();
        suggIndex--; //now is correct index

        System.out.println("Which advice would you like to approve?");
        adviceIndex = sc.nextInt();
        adviceIndex--;

        String sample = "approved";
        String approval;
        boolean approved;
        System.out.println("Input approved to approve advice");
        approval = sc.nextLine();
        approved = sample.equals(approval);

        curCamp.getSuggestions().get(suggIndex).getAdviceList().get(adviceIndex).setApproval(approved);

        if(approved){
            CampManager.editCamp(campName, staffName);
            //edit camp
        }
        return curCamp.getSuggestions().get(suggIndex);
        //sc.close();
    }

    //committee functions
    public void viewSuggestionForCommitteeMember(String committeeMemberName,String campName){
        //printing and logic will occur in this method
        // staff->viewEn->thisviewEnq->getcamp->
        //for loop to iterate arraylist of suggestion
        for(int i=0;i<CampManager.getCampList().size();i++){
            Camp camp = CampManager.getCamp(campName);
            System.out.println(campName + "suggestion");
                for(int j=0;j<camp.getSuggestions().size();j++){
                    if(camp.getSuggestions().get(i).getStudent() == committeeMemberName){
                      for(int k=0;k<camp.getSuggestions().get(j).getAdviceList().size();k++){
                        System.out.println("Advice " + k+1 + "- " + camp.getSuggestions().get(j).getAdviceList().get(k));

                    }//inner for
                }//if
            }//mid for
        }//outer for
    }//viewSuggestion

    public void editSuggestionForCommitteeMember(String studentName, String campName){
         int advIndex;
         String newAdvice;
         Scanner sc = new Scanner(System.in);
         this.viewSuggestionForCommitteeMember(studentName,campName);
         System.out.println("which advice would you like to edit");
         advIndex = sc.nextInt();
         advIndex--; //now is correct index

         System.out.println("Enter your new advice");
         newAdvice = sc.nextLine();

         Camp camp = new Camp();
         camp.editSuggestion(studentName,newAdvice,advIndex);


    }

    public void deleteSuggestionForCommitteeMember(String studentName, String campName){
        int advIndex;
        String newAdvice;
        Scanner sc = new Scanner(System.in);
        this.viewSuggestionForCommitteeMember(studentName,campName);
        System.out.println("which advice would you like to delete");
        advIndex = sc.nextInt();
        advIndex--; //now is correct index

        Camp camp = new Camp();
        camp.deleteSuggestion(studentName,advIndex);
    }
   

    
}
