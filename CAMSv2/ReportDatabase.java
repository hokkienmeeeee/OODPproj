package CAMSv2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class ReportDatabase {
    static ReportDatabase instance;

    private ReportDatabase() {}

    static ReportDatabase getInstance() {
        if (instance == null) {
            instance = new ReportDatabase();
        }
        return instance;
    }
    public void generateCampReportCSV(Camp camp, ReportFilter filter){

        String projectDirectory = System.getProperty("user.dir");
        String filename = projectDirectory + "\\CAMSv2\\Data CSV\\" + camp.getCampName() + "_Camp_Report" + ".csv";

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filename, false))) {
            printWriter.println("Student Name, Student Role");

            for (Student student : filter.getFilteredList(camp.getStudentList())) {
                printWriter.println(student.getName() + "," + student.getRole().toString());
            }

            printWriter.println("Camp Dates");
            // Write dates into csv
            // Assuming dates is a list of dates
            /*for (Date date : campDates) {
                printWriter.println(date.toString());
            }*/

            printWriter.println("Registration Deadline,User Group,Location of Camp,Camp Slots,Camp Description");
            printWriter.println(camp.getRegistrationClosingDate() + "," + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getTotalSlots() + "," + camp.getDescription());

            System.out.println("CSV file created successfully: " + filename);
        } catch (IOException e) {
            System.out.println("CSV file not found");
        }

    }

    public void generateCCMReportCSV(Camp camp){

        String projectDirectory = System.getProperty("user.dir");
        String filename = projectDirectory + "\\CAMSv2\\Data CSV\\" + camp.getCampName() + "_CCM_Report" + ".csv";

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filename, false))) {
            printWriter.println("Camp Committee Member Name, Points");

            for (CampCommitteeMember campCommitteeMember : camp.getCampCommitteeSlots()) {
                printWriter.println(campCommitteeMember.getName() + "," + campCommitteeMember.getPoints());
            }


            System.out.println("CSV file created successfully: " + filename);
        } catch (IOException e) {
            System.out.println("CSV file not found");
        }

    }

      public void generateStudentsEnquiryReport(Camp camp){

        String projectDirectory = System.getProperty("user.dir");
        String filename = projectDirectory + "\\CAMSv2\\Data CSV\\" + camp.getCampName() + "_Enquiries_Report" + ".csv";

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filename, false))) {
            printWriter.println("Student Name, Description, Status");

            for (Enquiries enquiries : camp.getEnquiries()) {
                for (Question question : enquiries.getQuestions()) {
                    printWriter.println(enquiries.getEnqurier() + "," + question.getQuestion() + "," + question.getStatus());                    
                }
            }


            System.out.println("CSV file created successfully: " + filename);
        } catch (IOException e) {
            System.out.println("CSV file not found");
        }

    }

}