package com.team6.quickcashteam6;

import java.util.ArrayList;

public class RecommendationService {

    private static ArrayList<Employee> recommendedEmployees;
    private static ArrayList<JobData> recommendedJobs;

    static ArrayList<Employee> employerRecommendation(ArrayList<String> skills, ArrayList<Employee> employees) {
        recommendedEmployees = new ArrayList<>();
        for (Employee employee: employees){
            int count=0;
            for (String skill : skills){

                if (employee.getSkills().contains(skill)){
                    count++;
                }
            }
            if (count>=2){
                recommendedEmployees.add(employee);
            }

        }
        return recommendedEmployees;
    }

    static ArrayList<JobData> employeeRecommendation(ArrayList<JobData> jobs, ArrayList<String> employeeSkills){
        recommendedJobs = new ArrayList<>();
        for (JobData job : jobs){
            int count = 0;
            for (String skill : employeeSkills){
                if (employeeSkills.contains(job.getSkills())){
                    recommendedJobs.add(job);
                }
            }
        }
        return recommendedJobs;
    }
}
